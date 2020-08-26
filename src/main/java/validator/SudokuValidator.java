package validator;

import model.CELL_ID;
import model.SudokuCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Class responsible for validating Sudoku board and returning the validating messages.
 */
public class SudokuValidator {

    final static Logger logger = LoggerFactory.getLogger(SudokuValidator.class.getName());

    //Minimum allowed value
    private static final int LOWER_BOUND = 1;

    //Maximum allowed value
    private static final int UPPER_BOUND = 9;

    //Expected no .of unique object to be added for a valid SUDOKU board
    private static final int TOTAL_MATRIX_OBJECTS = 243;


    final static int MATRIX_SIZE = 9;

    /**
     * Parses the Sudoku and looks for duplicates in
     * ROW
     * COLUMN
     * 3x3 board
     * @return Validation message
     * @param sudokuMatrix
     */
    public String validate(char[][] sudokuMatrix) {
        logger.info("Validation begin");
        final Set<SudokuCell> sudokuCells = new HashSet<>(TOTAL_MATRIX_OBJECTS);
        int arrSize = sudokuMatrix.length;

        //Iterate through row
        for (int row = 0; row < arrSize; row++) {
            int rowLength = sudokuMatrix[row].length;

            //Iterate through column
            for (int column = 0; column < sudokuMatrix[row].length; column++) {
                char value = sudokuMatrix[row][column];

                //Create cell object with row id
                final SudokuCell sudokuCellRow = new SudokuCell(row, column, value, CELL_ID.ROW);

                //Check if the value lies between 1 and 9
                if ( value != ' ') {

                    //Check if the value exist in the same row
                    if (sudokuCells.add(sudokuCellRow)) {

                        //Create cell object with column id
                        final SudokuCell sudokuCellColumn = new SudokuCell(row, column, value, CELL_ID.COLUMN);

                        //Check if the value exist in the same column
                        if (sudokuCells.add(sudokuCellColumn)) {

                            //Create cell object with board id
                            final SudokuCell sudokuCellBoard = new SudokuCell(row, column, value, CELL_ID.BOARD);

                            //Check if the value exist in the same board
                            if (!sudokuCells.add(sudokuCellBoard)) {
                                return getErrorMsg(sudokuCellBoard);
                            }
                        } else {
                            return getErrorMsg(sudokuCellColumn);
                        }
                    } else {
                        return getErrorMsg(sudokuCellRow);
                    }
                }
                logger.debug("Cell(" + row + "," + column + ") is valid.");
            }
        }
        logger.info("Validation end");
        logger.info("Sudoku is valid.");
        return "Sudoku is valid.";
    }

    /**
     * Generates error message for duplicate value in a row, column or 3x3 board with cell details.
     * @param cell - Invalid Sudoku cell
     * @return - Error message
     */
    public String getErrorMsg(SudokuCell cell) {
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("Cell(").append(cell.getRow()).append(",").append(cell.getColumn()).append(") has a duplicate value for ").append(cell.getId());
        return logErrorAndReturn(errorMsg);
    }

    /**
     * Generates error message for incorrect row or column size of Sudoku
     * @param length - Row length
     * @return
     */
    public String getIncorrectRowSizeErrMsg(int length) {
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("Incorrect row size ").append(length);
        return logErrorAndReturn(errorMsg);
    }

    /**
     * Generates error message for incorrect column size of Sudoku
     * @param cell -
     * @param length - Row length
     * @return
     */
    public String getIncorrectColumnSizeErrMsg(SudokuCell cell, int length) {
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("Incorrect column size ").append(length).append(" for column ").append(cell.getColumn());
        return logErrorAndReturn(errorMsg);
    }

    /**
     * Get the error StringBuilder and logs it as error and returns as string
     * @param errorMsg - Generated error message
     * @return - return the string value
     */
    private String logErrorAndReturn(StringBuilder errorMsg) {
        logger.error(errorMsg.toString());
        return errorMsg.toString();
    }

    public Character parseAndReturnCellValue(int row,int column,String cellValue) {
        if(null==cellValue){
            throw new RuntimeException("Cell value cannot be NULL.");
        }
        cellValue = cellValue.trim();
        if(cellValue.isEmpty()){
            return ' ';
        }
        final int cellIntValue = Integer.parseInt(cellValue);
        if (LOWER_BOUND > cellIntValue || cellIntValue > UPPER_BOUND) {
            final String errMsg = getInvalidValueMsg(row, column, cellValue);
            throw new RuntimeException(errMsg);
        }

        return (char)(cellIntValue + '0');
    }

    /**
     * Generates error message for any value outside [1-9] with cell details.
     * @param row - Invalid Sudoku row
     * @param column - Invalid Sudoku column
     * @return - Error message
     */
    public String getInvalidValueMsg(int row,int column,String value) {
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("Cell(").append(row).append(",").append(column).append(") has a invalid value ").append(value);
        return logErrorAndReturn(errorMsg);
    }

    /**
     *
     * @param filePath
     * @return
     */
    public char[][] loadSudokuFile( String filePath) throws FileNotFoundException {
        char[][] sudokuMatrix = new char[MATRIX_SIZE][MATRIX_SIZE];
        try (final Scanner scanner = new Scanner(new File(filePath))) {
            int row = 0;
            while (scanner.hasNextLine()) {
                final String[] cellValues = scanner.nextLine().split(",");
                if (cellValues.length != MATRIX_SIZE) {
                    throw new RuntimeException("Incorrect no. of value at line " + row);
                }
                for (int column = 0; column < MATRIX_SIZE; column++) {
                    sudokuMatrix[row][column] = parseAndReturnCellValue(row, column, cellValues[column]);
                }
                row++;
            }
            if (row != MATRIX_SIZE) {
                final String incorrectRowSizeErrMsg = getIncorrectRowSizeErrMsg(row);
                throw new RuntimeException(incorrectRowSizeErrMsg);
            }
        }  finally {
            logger.info("Sudoku file validated");
        }
        return sudokuMatrix;
    }
}
