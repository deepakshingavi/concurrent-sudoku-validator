# simple-sudoku-validator
Validates the user input sudoku board(9x9)

### Pre-Requisites
* Java 8+
* Maven 3+

#### Project Details
* src/main/java - Contains Java source code
* src/test/java - Contains Java test source code  
* scripts - Contains support shell script to build and run the project

#### Build 
```shell script
./scripts/build.sh
```
It should compile and generate a new jar with dependencies at 
`target/simple-sudoku-validator-1.0-jar-with-dependencies.jar`


#### Run
```shell script
./scripts/run.sh
```
This should start a CLI for user.
Here user can input the Sudoku board e.g.
``` 
1 5 7 6 4 9 8 2 3
2 9 4 8 1 3 5 6 7
6 8 3 7 5 2 4 1 9
5 2 0 3 9 6 7 4 1 
7 4 9 5 8 1 2 3 6
3 6 1 2 7 4 9 5 8
4 1 6 9 2 8 3 7 5
8 3 5 4 6 7 1 9 2
9 7 2 1 3 5 6 8 4  
``` 
and the program should return a result message of the validation. 

