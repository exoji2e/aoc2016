#!/bin/bash
javac A.java
java A 7 < input.txt
java A 12 < input.txt
rm *.class;
