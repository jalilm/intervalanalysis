package intervalAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    static String allTestsFileName = "test_all";
    static String testsDescriptorDirecotry = "scripts";

    public static void main(String[] args) throws IOException {
        String testFileName;
        FileInputStream testFileStream;
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(testsDescriptorDirecotry + File.separator
                        + allTestsFileName)));
        while ((testFileName = reader.readLine()) != null) {
            testFileStream = new FileInputStream(testsDescriptorDirecotry
                    + File.separator + testFileName);
            streaming_main(testFileStream, System.out);
            testFileStream.close();
        }
        reader.close();
    }

    private static void streaming_main(InputStream input, PrintStream output)
            throws IOException {
        output.println("Jimple Integer Interval Analyzer");
        output.println("--------------------------------");

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        output.println("(Current path is: " + System.getProperty("user.dir")
                + ")");
        output.print("Enter the classpath of the Class to analyze (use \""
                + File.pathSeparator + "\" for multiples): ");
        String classpath = reader.readLine();
        if (classpath.trim().isEmpty()) {
            output.println("Empty classpath given, using '.'");
            classpath = ".";
        }
        output.print("Enter the name of the Class to analyze: ");
        String classname = reader.readLine();
        output.print("Enter the name of the Method to analyze: ");
        String methodname = reader.readLine();

        output.println(getRtJarPath() + File.pathSeparator + classpath);
        initializeSoot(getRtJarPath() + File.pathSeparator + classpath,
                classname);
        SootClass c = loadClass(classname);
        @SuppressWarnings("unused")
        // TODO: how to ask for only one method
        SootMethod m = c.getMethodByName(methodname);

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
