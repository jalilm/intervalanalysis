package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import intervalAnalysis.IntervalAnalysis;
import intervalAnalysis.State;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soot.Body;
import soot.BodyTransformer;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.Transform;
import soot.options.Options;
import soot.toolkits.graph.BriefUnitGraph;


public class LogicalTests {
	static String classpath = ".";
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void testIfGreater() throws Exception {
		runTest("IfGt");
	}	
	
	@Test
	public void testIfGreaterTrueFalsePath() throws Exception {
		runTest("IfGtFalse");
	}
	
	@Test
	public void testIfEqual() throws Exception {
		runTest("IfEq");
	}
	
	
	@Test
	public void testLookupSwitch() throws Exception {
		runTest("LookupSwitch");
	}
	
	@Test
	public void testTableSwitch() throws Exception {
		runTest("TableSwitch");
	}
	
	@Test
	public void testSwitchWithNoDefault() throws Exception {
		runTest("SwitchNoDefault");
	}
		
	@Test
	public void testAssign() throws Exception {
		runTest("Assign");
	}
	
   @Test
    public void testNegAssign() throws Exception {
        runTest("NegAssign");
    }
	
	@Test
    public void widenAssign() throws Exception {
        runTest("Widen");
    }
	
	@Test
	public void testExample1() throws Exception {
		runTest("test1");
	}
	
	@Test
	public void testExample2() throws Exception {
		runTest("test2");
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
        sootOptions.set_src_prec(Options.src_prec_java);
        sootOptions.set_output_format(Options.output_format_jimple);
        sootOptions.set_output_dir("./tests");
        sootOptions.set_soot_classpath(classpath);
        sootOptions.set_whole_program(true);
        sootOptions.set_verbose(false);
        sootOptions.classes().add(classname);
        sootOptions.set_main_class(classname);

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
		String classname = String.format("tests.%s",testName);
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
