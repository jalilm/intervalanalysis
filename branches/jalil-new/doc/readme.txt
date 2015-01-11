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
	
2)  Run Juint of:
		a. Different class unit tests.
		b. Our input tests that reside in "./tests/".
		c. The example tests that reside in "./tests/examples/".
 
-------------------------------
(1) Run the Analysis tests.txt
-------------------------------
0) If you are reading this, then you have already extracted the submission zip file.
1) Make that the current directory is the root of the extracted zip file.
2) Specify the full classes names in the file "./scripts/tests.txt".
3) Run the following in the command line: 
	java -cp .;.\bin;.\jars\soot-2.5.0.jar intervalAnalysis.Main

------------------------
(2) Run the Junit tests
------------------------
In order to run the Junit tests, you should import the project to eclipse.
And run the following Junit Suite Tests:
	a. test.unittests.UnitTests
	b. tests.LogicalTests
	c. tests.SystemTests