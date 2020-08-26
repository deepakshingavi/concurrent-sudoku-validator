import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import validator.SudokuValidator;

import java.io.FileNotFoundException;

public class SudokuValidatorTest {

    private static SudokuValidator sudokuValidator;

    @BeforeAll
    public static void beforeAll() {
        sudokuValidator = new SudokuValidator();
    }

    @Test
    public void parseAndReturnCellValueTest() {
        char input = ' ';
        Character result = sudokuValidator.parseAndReturnCellValue(0, 0, input + "");
        Assertions.assertEquals(input, result);

        for (int i = 1; i < 10; i++) {
            String inputStr = "" + i;
            result = sudokuValidator.parseAndReturnCellValue(0, 0, inputStr);
            Assertions.assertEquals(inputStr.charAt(0), result);
        }
    }

    @Test
    public void parseAndReturnCellValueFailTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            String inputStr = "" + 10;
            Character resultTemp = sudokuValidator.parseAndReturnCellValue(0, 0, inputStr);
            Assertions.assertEquals(inputStr.charAt(0), resultTemp);
        });

        Assertions.assertThrows(RuntimeException.class, () -> {
            String inputStr = "a";
            Character resultTemp = sudokuValidator.parseAndReturnCellValue(0, 0, inputStr);
            Assertions.assertEquals(inputStr.charAt(0), resultTemp);
        });
    }

    @Test
    public void loadFileTest() throws FileNotFoundException {
        final char[][] result = sudokuValidator.loadSudokuFile("src/test/resources/invalidInput.txt");
        final char[][] expectedOutput = {
                {'5', '3', '1', '6', '7', '8', '9', '1', '2'},
                {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
                {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
                {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
                {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
                {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
                {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
                {'3', '4', '5', '2', '8', '6', '1', '7', '9'}};

        for (int i = 0; i < 9; i++) {
            Assertions.assertArrayEquals(expectedOutput[i], result[i]);
        }
    }

    @Test
    public void duplicateElementTest() throws FileNotFoundException {
        final char[][] result = sudokuValidator.loadSudokuFile("src/test/resources/invalidInput.txt");
        final String validate = sudokuValidator.validate(result);
        Assertions.assertEquals("Cell(0,7) has a duplicate value for ROW (0)", validate);
    }

    @Test
    public void validateRowDupsTest() {
        final char[][] input = {
                {6, 2, 4, 5, 3, 9, 1, 8, 7},
                {5, 1, 9, 7, 2, 8, 6, 3, 4},
                {8, 3, 7, 6, 1, 4, 2, 9, 5},
                {1, 4, 3, 8, 6, 5, 7, 2, 9},
                {9, 5, 8, 2, 4, 7, 3, 6, 1},
                {7, 6, 2, 3, 9, 1, 4, 5, 8},
                {3, 7, 1, 9, 5, 6, 8, 4, 2},
                {4, 9, 6, 1, 8, 2, 5, 7, 3},
                {2, 8, 5, 4, 7, 3, 9, 8, 6}  // Added duplicate value 8
        };

        final String message = sudokuValidator.validate(input);
        Assertions.assertEquals("Cell(8,7) has a duplicate value for ROW (8)", message);
    }

    @Test
    public void validateColumnDupsTest() {
        final char[][] input = {
                {6, 2, 4, 5, 3, 9, 1, 8, 7},
                {5, 6, 9, 7, 2, 8, 1, 3, 4},
                {8, 3, 7, 6, 1, 4, 2, 9, 5},
                {1, 4, 3, 8, 6, 5, 7, 2, 9},
                {9, 5, 8, 2, 4, 7, 3, 6, 1},
                {7, 6, 2, 3, 9, 1, 4, 5, 8},
                {3, 7, 1, 9, 5, 6, 8, 4, 2},
                {4, 9, 6, 1, 8, 2, 5, 7, 3},
                {2, 8, 5, 4, 7, 3, 9, 1, 6}
        };

        final String message = sudokuValidator.validate(input);
        Assertions.assertEquals("Cell(1,1) has a duplicate value for BOARD (0, 0)", message);
    }

    @Test
    public void inValidInput1Test() {
        final char[][] input = {
                {5, 3, 1, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        final String message = sudokuValidator.validate(input);
        Assertions.assertEquals("Cell(0,7) has a duplicate value for ROW (0)", message);
    }

    @Test
    public void inValidInput3Test() {
        final char[][] input = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 5, 3, 4, 2, 8, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        final String message = sudokuValidator.validate(input);
        Assertions.assertEquals("Cell(2,2) has a duplicate value for BOARD (0, 0)", message);
    }

    @Test
    public void validInput1Test() {
        final char[][] input = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        final String message = sudokuValidator.validate(input);
        Assertions.assertEquals("Sudoku is valid.", message);
    }

    @Test
    public void validInput2Test() {
        final char[][] input = {
                {2, 8, 6, 9, 4, 5, 1, 7, 3},
                {7, 1, 4, 6, 3, 2, 9, 5, 8},
                {9, 3, 5, 7, 8, 1, 4, 2, 6},
                {4, 2, 7, 3, 5, 6, 8, 1, 9},
                {6, 5, 8, 1, 9, 7, 3, 4, 2},
                {1, 9, 3, 4, 2, 8, 7, 6, 5},
                {3, 6, 1, 5, 7, 9, 2, 8, 4},
                {5, 4, 2, 8, 1, 3, 6, 9, 7},
                {8, 7, 9, 2, 6, 4, 5, 3, 1}
        };

        final String message = sudokuValidator.validate(input);
        Assertions.assertEquals("Sudoku is valid.", message);
    }

    @Test
    public void validInput3Test() {
        final char[][] input = {
                {6, 2, 4, 5, 3, 9, 1, 8, 7},
                {5, 1, 9, 7, 2, 8, 6, 3, 4},
                {8, 3, 7, 6, 1, 4, 2, 9, 5},
                {1, 4, 3, 8, 6, 5, 7, 2, 9},
                {9, 5, 8, 2, 4, 7, 3, 6, 1},
                {7, 6, 2, 3, 9, 1, 4, 5, 8},
                {3, 7, 1, 9, 5, 6, 8, 4, 2},
                {4, 9, 6, 1, 8, 2, 5, 7, 3},
                {2, 8, 5, 4, 7, 3, 9, 1, 6}
        };

        final String message = sudokuValidator.validate(input);
        Assertions.assertEquals("Sudoku is valid.", message);
    }

}
