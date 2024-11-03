# NextGenerationTestRunner
Recruitment task for JetBrains Internship

### Program A: Pseudo-Random Number Generator
Program A will act as a pseudo-random number generator. It reads commands from stdin,
where each command is delimited by a line break, and writes responses to stdout.
The program should handle the following commands:

Hi: Responds with "Hi" on stdout.  
GetRandom: Responds with a pseudo-random integer on stdout.  
Shutdown: Gracefully terminates the program.  
Any unknown commands should be ignored.

### Program B: Controller
Program B will launch Program A as a separate process, provided as an argument.
Once Program A is running, Program B should:

Send the Hi command to Program A and verify the correct response.  
Retrieve 100 random numbers by sending the GetRandom command to Program A 100 times.  
Send the Shutdown command to Program A to terminate it gracefully.  
Sort the list of retrieved random numbers and print the sorted list to the console.  
Calculate and print the median and average of the numbers.

## Usage:
1. Running from IntelliJ
   The Controller program can be run directly from IntelliJ. The arguments can be specified in the
   _RunDebug Configurations_ panel in _Program Arguments_ part. The first argument should be name
   of Java class compiled from Kotlin so name of the ProgramA file + Kt.  
   **Example:** If your ProgramA file is called **ProgramA.kt** then argument should be **ProgramAKt**.  
   If no arguments are given the program will use default: **PseudoRng.kt** as PseudoRng.kt is file I
   created that has requested functionalities of ProgramA. Additional arguments will be ignored.
2. Running from command line
   For this programs to be run from command line both programs need to be compiled into .jar files.  
   They can be compiled by following command:  
   _kotlinc <<PATH_TO_FILE>> -include-runtime -d <<NAME_OF_OUTPUT_JAR>>.jar_  
   **Examples:**  
   _kotlinc .\src\PseudoRng.kt -include-runtime -d PseudoRng.jar_  
   _kotlinc .\src\Controller.kt -include-runtime -d Controller.jar_
    Then files can be run with command:  
   **For windows:**  
      _java -cp "<<NAME_OF_PROGRAM_B_JAR>>.jar;<<NAME_OF_PROGRAM_A_JAR>>.jar" <<NAME_OF_PROGRAM_B_CLASS>> <<(optional)NAME_OF_PROGRAM_A_CLASS>>_  
   **Example:**  
   _java -cp "Controller.jar;PseudoRng.jar" ControllerKt PseudoRngKt_  
   **For linux/macOs:**  
   _java -cp "<<NAME_OF_PROGRAM_B_JAR>>.jar:<<NAME_OF_PROGRAM_A_JAR>>.jar" <<NAME_OF_PROGRAM_B_CLASS>> <<(optional)NAME_OF_PROGRAM_A_CLASS>>_  
   **Example:**  
   _java -cp "Controller.jar:PseudoRng.jar" ControllerKt PseudoRngKt_  