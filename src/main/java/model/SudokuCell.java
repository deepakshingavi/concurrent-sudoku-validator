package model;

import java.util.Objects;

/**
 * Model object to capture cell details like
 * row
 * column
 * value
 * row identifier
 * column identifier
 * board identifier
 */
public class SudokuCell {

    //Sudoku cell's row
    private final int row;

    //Sudoku cell's column
    private final int column;

    //Sudoku cell's value
    private final char value;

    //Sudoku cell's identifier
    private final String id;

    private final CELL_ID cellId;

    //Sudoku divisor to identify the board
    private static final int CELL_BOARD_DIVIDER = 3;

    public SudokuCell(int row, int column, char value, CELL_ID cellId) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.cellId = cellId;
        this.id = generateId(row, column, cellId);
    }

    /**
     * Cell identifier generator
     * @param row   - Cell's row number
     * @param column - Cell's column number
     * @param cellId - CELL identifier object
     * @return - Unique string generated for identifying the object.
     */
    private String generateId(int row, int column, CELL_ID cellId) {
        StringBuilder id = new StringBuilder(cellId.toString()).append(" (");
        switch (cellId) {
            case ROW:
                id.append(row);
                break;
            case COLUMN:
                id.append(column);
                break;
            case BOARD:
                id.append(row/CELL_BOARD_DIVIDER).append(", ").append(column/CELL_BOARD_DIVIDER);
                break;
        }
        return id.append(")").toString();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getValue() {
        return value;
    }

    public CELL_ID getCellId() {
        return cellId;
    }

    public String getId() {
        return id;
    }

    /**
     * Object equality is based on its value and Cell identifier
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuCell that = (SudokuCell) o;
        return  value == that.value &&
                Objects.equals(id, that.id);
    }

    /**
     * Object's hash will be only equal if value and identifier are same.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash( value, id);
    }
}
