package game.iterators;

import game.model.Cell;

import java.util.Iterator;
import java.util.List;

public class BoardIterator implements Iterator {
    /**
     * This Iterator work only for list that have same number of rows and columns
     */
    private Integer currentRow;
    private Integer currentColumn;
    private List<List<Cell>> twoDimensionList;
    private Integer maxRowSize;
    private Integer maxColumnSize;

    public BoardIterator(List<List<Cell>> twoDimensionList) {
        this.twoDimensionList = twoDimensionList;
        this.currentRow = 0;
        this.currentColumn = 0;
        this.maxRowSize = twoDimensionList.size();
        Integer firstRowIndex = 0;
        this.maxColumnSize = twoDimensionList.get(firstRowIndex).size();
    }

    public boolean hasNextInRow() {
        if (this.twoDimensionList.size() == 0){
            return false;
        }
        return this.currentColumn < maxColumnSize;
    }

    public Cell nextInRow(Integer row){
        if (hasNextInRow()) {
            return twoDimensionList.get(row).get(this.currentColumn++);
        }

        return null;
    }

    public boolean hasNextInColumn(Integer column) {
        if (this.twoDimensionList.size() == 0){
            return false;
        }
        if (this.currentRow < maxRowSize) {
            return column < maxColumnSize;
        }
        return false;
    }

    public boolean hasNextInCrossDown() {
        if (this.twoDimensionList.size() == 0) {
            return false;
        }
        if (this.currentRow < maxRowSize){
            return this.currentColumn < maxColumnSize;
        }
        return false;
    }

    public Cell nextInCrossDown() {
        if (hasNextInCrossDown()) {
            return twoDimensionList.get(this.currentRow++).get(this.currentColumn++);
        }

        return null;
    }

    public boolean hasNextInCrossUp() {
        if (this.twoDimensionList.size() == 0) {
            return false;
        }
        if (this.maxRowSize >= 0){
            return this.currentColumn < maxColumnSize;
        }
        return false;
    }

    public Cell nextInCrossUp() {
        if (hasNextInCrossUp()) {
            return twoDimensionList.get(--this.maxRowSize).get(this.currentColumn++);
        }

        return null;
    }

    public Cell nextInColumn(Integer column){
        if (hasNextInColumn(column)) {
            return twoDimensionList.get(this.currentRow++).get(column);
        }

        return null;
    }

    @Override
    public boolean hasNext() {
        if (this.twoDimensionList.size() == 0) {
            return false;
        }
        if (this.currentColumn < maxColumnSize) {
            return true;
        }
        this.currentColumn = 0;
        Integer maxRowIndex = maxRowSize - 1;
        if (this.currentRow < maxRowIndex) {
            this.currentRow++;
            return hasNext();
        }
        return false;
    }

    @Override
    public Cell next() {
        if (hasNext()) {
            return twoDimensionList.get(this.currentRow).get(this.currentColumn++);
        }

        return null;
    }
}
