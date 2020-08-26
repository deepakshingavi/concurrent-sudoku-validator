package entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validator.SudokuValidator;

import java.io.FileNotFoundException;

public class FileScanner {
    final static Logger logger = LoggerFactory.getLogger(FileScanner.class.getName());

    public static void main(String[] args)  {
        logger.info("Sudoku file validator start");
        if (args.length == 1) {
            String filePath = args[0];
            try {
                final SudokuValidator sudokuValidator = new SudokuValidator();
                char[][] sudokuMatrix = sudokuValidator.loadSudokuFile(filePath);
                sudokuValidator.validate(sudokuMatrix);
            } catch (FileNotFoundException e) {
                logger.error("File not found at path=" + filePath);
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.error("Incorrect size of Sudoku input.");
                throw e;
            } finally {
                logger.info("Sudoku file validated");
            }
        } else {
            logger.error("Input file path missing!!!");
        }
    }


}
