
/////////////////////////
// Compilers Project 3 //
/////////////////////////


Execution Scripts:
    - To clean project, execute "clean.bat" on Windows or "./clean.sh" on Linux.
    - To run project, execute "run.bat" on Windows or "./run.sh" on Linux.
        > Run script automatically cleans project.
        > Run script handles parameters as if passed directly to program.

            Windows Examples:
                run test\valid
                run test\valid.sp2020
                run < test\valid.sp2020

            Linux Examples:
                ./run.sh test/valid
                ./run.sh test/valid.sp2020
                ./run.sh < test/valid.sp2020


Build Notes:
    - Project build (.class) files are created in the "build" directory.


Project Notes:
    - Semantics checking for this project is continuous, even if errors are found. This
      was decided for ease of testing based on the instructions given in the P3 lecture
      rather than those in the assignment prompt.