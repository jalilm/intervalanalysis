package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import intervalAnalysis.IntervalAnalysis;
import intervalAnalysis.State;

import org.junit.Test;

import soot.Body;
import soot.BodyTransformer;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.Transform;
import soot.options.Options;
import soot.toolkits.graph.BriefUnitGraph;


public class SystemTests {
	static String classpath = ".";
	

	@Test
	public void test1() throws Exception {
		runTest("examples.test1");
	}	
	
	@Test
	public void test2() throws Exception {
		runTest("examples.test2");
	}
	
	protected boolean compareTwoFiles(String file1, String file2) throws IOException
	{
		String input = new String(Files.readAllBytes(Paths.get(file1)));
		String output = new String(Files.readAllBytes(Paths.get(file2)));
		return input.equals(output);
	}
	
    @SuppressWarnings("unchecked")
   void initializeSoot(String classpath, String classname) {
        soot.G.reset();
        Options sootOptions = Options.v();
        sootOptions.set_allow_phantom_refs(true);
        sootOptions.set_src_prec(Options.src_prec_jimple);
        sootOptions.set_output_format(Options.output_format_jimple);
        sootOptions.set_output_dir("./tests");
        sootOptions.set_soot_classpath(classpath);
        sootOptions.set_whole_program(true);
        sootOptions.set_verbose(false);
        sootOptions.classes().add(classname);
        sootOptions.set_main_class(classname);
        sootOptions.set_interactive_mode(false);
        sootOptions.setPhaseOption("jb", "use-original-names:true");
        //sootOptions.setPhaseOption("jb.ls", "enabled:false");
        sootOptions.setPhaseOption("jb.ne", "enabled:false");
        //sootOptions.setPhaseOption("jb.ule", "enabled:false");
        //sootOptions.setPhaseOption("jb.ulp", "enabled:false");
        sootOptions.setPhaseOption("jop", "enabled:false");

    }

    SootClass loadClass(String classname) {
        Scene scene = Scene.v();
        scene.loadNecessaryClasses();
        return scene.getSootClass(classname);
    }

    String getRtJarPath() throws IOException {
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
    
    protected void runTest(String testName) throws IOException
    {
		String classname = String.format("%s",testName);
		String correctFile = String.format("./tests/%s.correct.txt",testName);
		String resultFile = String.format("./tests/%s.result.txt",testName);
		
		final State state = new State();
		
		initializeSoot(getRtJarPath() + File.pathSeparator + classpath, classname);
        loadClass(classname);

        // run soot and print results
        String[] sootArgs = new String[] { classname };

        PackManager.v().getPack("jtp")
                .add(new Transform("jtp.myTransform", new BodyTransformer() {
                    protected void internalTransform(Body body,
                            String phaseName,
                            @SuppressWarnings("rawtypes") Map options) {
                        new IntervalAnalysis(new BriefUnitGraph(body), state);
                    }
                }));

        soot.Main.main(sootArgs);
        
        //write State to file
        Files.write(Paths.get(resultFile), state.print().getBytes());
        assertTrue("Files differ!!!", compareTwoFiles(correctFile, resultFile));
    }
}

