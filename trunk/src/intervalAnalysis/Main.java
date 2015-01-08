package intervalAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;

import soot.Body;
import soot.BodyTransformer;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.options.Options;
import soot.toolkits.graph.BriefUnitGraph;

public class Main {

    static String allTestsFileName = "tests.txt";
    static String testsDescriptorDirecotry = "scripts";
    static String classpath = ".";

    public static void main(String[] args) throws IOException {
        String classname;
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(testsDescriptorDirecotry + File.separator
                        + allTestsFileName)));
        while ((classname = reader.readLine()) != null) {
            streaming_main(classname, System.out);
        }
        reader.close();
    }

    private static void streaming_main(String classname, PrintStream output)
            throws IOException {

        initializeSoot(getRtJarPath() + File.pathSeparator + classpath,
                classname);
        loadClass(classname);

        // run soot and print results
        String[] sootArgs = new String[] { classname };

        PackManager.v().getPack("jtp")
                .add(new Transform("jtp.myTransform", new BodyTransformer() {
                    protected void internalTransform(Body body,
                            String phaseName,
                            @SuppressWarnings("rawtypes") Map options) {
                        new IntervalAnalysis(new BriefUnitGraph(body));
                    }
                }));

        soot.Main.main(sootArgs);
    }

    @SuppressWarnings("unchecked")
    static void initializeSoot(String classpath, String classname) {
        soot.G.reset();
        Options sootOptions = Options.v();
        sootOptions.set_allow_phantom_refs(true);
        sootOptions.set_src_prec(Options.src_prec_java);
        sootOptions.set_output_format(Options.output_format_jimple);
        sootOptions.set_output_dir(".");
        sootOptions.set_soot_classpath(classpath);
        sootOptions.set_whole_program(true);
        sootOptions.set_verbose(false);
        sootOptions.classes().add(classname);
        sootOptions.set_main_class(classname);

    }

    static SootClass loadClass(String classname) {
        Scene scene = Scene.v();
        scene.loadNecessaryClasses();
        return scene.getSootClass(classname);
    }

    static String getRtJarPath() throws IOException {
        String java_home = System.getProperty("java.home");
        String[] candidates = new String[] { java_home + "/lib/rt.jar", // Windows,
                                                                        // Linux
                java_home + "/../Classes/alt-rt.jar", // Mac OSX Lion
        };
        for (String candidate : candidates) {
            File f = new File(candidate);
            if (f.exists())
                return f.getCanonicalPath();
        }
        throw new RuntimeException("Cannot find rt.jar");
    }
}
