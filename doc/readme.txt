------------------------------------------
The project had been developed on Windows.
------------------------------------------

Heads Up:
The project prints debug messages to System.err
The project prints progress messages to System.out
The project prints the results to a per test file suffixed "results.txt".
As well, Soot prints its warning and progress to System.out

Now to the real thing, you can run the project in two ways:

1)  Run the analysis on all the the test files in "./scripts/tests.txt".
	This run creates for each "$test" specified in "tests.txt",
	a result file called "$test.result.txt", that contains
	the result of the analysis.
	
2)  Run JUnit of:
		a. Different class unit tests.
		b. Our input tests that reside in "./tests/".
		c. The example tests that reside in "./tests/examples/".
 
-------------------------------
(1) Run the Analysis on tests.txt
-------------------------------
0) Extract zip to folder c:\HW2
1) cd to the folder c:\HW2  
2) Specify the full classes names in the file "scripts/tests.txt".
3) cd scripts
4) Run runAnalysis.bat from scripts folder
The script runs the following command (Windows): 
	java -cp .;.\bin;.\jars\soot-2.5.0.jar intervalAnalysis.Main

------------------------
(2) Run the JUnit tests
------------------------
In order to run the JUnit tests, you should import the project to eclipse.
And run the following JUnit Suite Tests:
	a. test.unittests.UnitTests
	b. tests.LogicalTests
	c. tests.SystemTests