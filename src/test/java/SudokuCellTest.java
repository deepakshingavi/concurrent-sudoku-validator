import model.CELL_ID;
import model.SudokuCell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuCellTest {

    @Test
    public void generateIdTest(){
        int row =0;
        int column =0;
        char value =1;
        CELL_ID cellId =CELL_ID.ROW;

        SudokuCell sudokuCell = new SudokuCell(row, column, value, cellId);
        Assertions.assertEquals(row,sudokuCell.getRow());
        Assertions.assertEquals(column,sudokuCell.getColumn());
        Assertions.assertEquals(value,sudokuCell.getValue());
        Assertions.assertEquals(cellId,sudokuCell.getCellId());
        Assertions.assertEquals("ROW ("+row+")",sudokuCell.getId());

        sudokuCell = new SudokuCell(row, column, value, CELL_ID.COLUMN);
        Assertions.assertEquals("COLUMN ("+column+")",sudokuCell.getId());

        sudokuCell = new SudokuCell(row, column, value, CELL_ID.BOARD);
        Assertions.assertEquals("BOARD ("+row+", "+column+")",sudokuCell.getId());

    }

    @Test
    public void cellEqualityTest(){
        int row =0;
        int column =0;
        char value =1;
        CELL_ID cellId =CELL_ID.ROW;
        SudokuCell sudokuCell1 = new SudokuCell(row, column, value, cellId);
        SudokuCell sudokuCell2 = new SudokuCell(row, column, value, cellId);

        Assertions.assertEquals(sudokuCell1,sudokuCell2);

        SudokuCell sudokuCell3 = new SudokuCell(row, column, '9', cellId);
        Assertions.assertNotEquals(sudokuCell1,sudokuCell3);

        SudokuCell sudokuCell4 = new SudokuCell(row, 1, value, cellId);
        Assertions.assertEquals(sudokuCell1, sudokuCell4);

        SudokuCell sudokuCell5 = new SudokuCell(row, column, value, CELL_ID.COLUMN);
        Assertions.assertNotEquals(sudokuCell1, sudokuCell5);

        SudokuCell sudokuCell6 = new SudokuCell(row, column, value, CELL_ID.BOARD);
        Assertions.assertNotEquals(sudokuCell1, sudokuCell6);
    }
}
