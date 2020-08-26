package entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validator.SudokuValidator;

import java.util.Scanner;

public class Main {

    final static Logger logger = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.info("Sudoku validator start");
        try (final Scanner scanner = new Scanner(System.in)) {
            int matrixSize = 9;
            while(true) {
                System.out.println("Please enter your sudoku matrix (or Ctrl + C to exit):");
                int[][] sudokuMatrix = new int[matrixSize][matrixSize];
                for (int row = 0; row < matrixSize; row++) {
                    for (int column = 0; column < matrixSize; column++) {
                        sudokuMatrix[row][column] = scanner.nextInt();
                    }
                }
                final SudokuValidator sudokuValidator = new SudokuValidator(sudokuMatrix);
                sudokuValidator.validate();
            }
        } finally {
            logger.info("Sudoku validated");
        }
    }
}
