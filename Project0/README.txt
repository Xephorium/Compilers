
/////////////////////////
// Compilers Project 0 //
/////////////////////////

Description:

	This program builds a parse tree of bidirectional (binary) nodes from
	a user provided list of strings. It then traverses that tree in multiple
	ways, printing each walk to a separate output file. Nodes of the tree
	represent two letters and accumulate strings whose first two characters
	match. Construction is alphabetical and works as follows:

	Input: george adam ala 12 ada 125 john haus geode

	Resulting Tree:                 Node 1:ge-george,geode
	                                 /                  \
	                Node 2:ad-adam,ada                  Node 5:jo-john
	               /                  \                /
	     Node 4: 12-12,125       Node 3:al-ala    Node 6:ha-haus

	Input may be provided as a file of extension .sp2020, whose name must be
	sent as a first parameter to the program. If no extension is supplied, 
	.sp2020 is assumed. Input may also be provided via the keyboard as an
	input stream. The program will continue reading until an End of File char
	is reached. Compilation and execution are handled by the scripts below.

Execution Scripts:
    - To clean project, execute "clean.bat" on Windows or "./clean.sh" on Linux.
    - To run project, execute "run.bat" on Windows or "./run.sh" on Linux.
        > Run script automatically cleans project.
        > Run script handles parameters as if passed directly to program.

            Windows Examples:
                run test\input
                run test\input.sp2020
                run < test\input.sp2020

            Linux Examples:
                ./run.sh test/input
                ./run.sh test/input.sp2020
                ./run.sh < test/input.sp2020


Other Notes:
    - Project build (.class) files are created in the "build" directory.
    - Project output (.xorder) files are created in the "out" directory.