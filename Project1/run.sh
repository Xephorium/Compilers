#!/bin/bash

# Clean Project
./clean.sh

# Compile Project (Targets Sent to "build" Directory)
javac -d build src/TestScanner.java

# Print Update
echo Project compiled.
echo Running project.

# Run Project (Binaries Read from "build" Directory)
java -cp build TestScanner $@
