package game.model;

import game.enums.Seed;

public class Cell {
    private Seed seed;
    private Integer row;
    private Integer column;

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Cell(Integer row, Integer column){
        setRow(row);
        setColumn(column);
        setSeed(Seed.EMPTY);
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    void setRow(Integer row) throws IllegalArgumentException {
        if(row < 0 || row > 2){
            throw new IllegalArgumentException("Given parameters out of range (0,2)");
        }
        this.row = row;
    }

    void setColumn(Integer column) throws IllegalArgumentException {
        if(column < 0 || column > 2){
            throw new IllegalArgumentException("Given parameters out of range (0,2)");
        }
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (seed != cell.seed) return false;
        if (row != null ? !row.equals(cell.row) : cell.row != null) return false;
        return column != null ? column.equals(cell.column) : cell.column == null;
    }
}
