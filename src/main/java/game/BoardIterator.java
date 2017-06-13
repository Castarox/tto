package game;

import java.util.Iterator;
import java.util.List;

public class BoardIterator implements Iterator {
    /**
     * This Iterator work only for list that have same number of rows and columns
     */
    private Integer currentRow;
    private Integer currentColumn;
    private List<List<Cell>> twoDimensionList;
    private Integer maxRowIndex;
    private Integer maxColumnIndex;

    public BoardIterator(List<List<Cell>> twoDimensionList) {
        this.twoDimensionList = twoDimensionList;
        this.currentRow = 0;
        this.currentColumn = 0;
        this.maxRowIndex = twoDimensionList.size();
        this.maxColumnIndex = twoDimensionList.get(0).size();
    }

    public boolean hasNextInRow(Integer row) {
        if (this.twoDimensionList.size() == 0){
            return false;
        }
        if (this.currentColumn < maxColumnIndex) {
            return true;
        }
        return false;
    }

    public Cell nextInRow(Integer row){
        if (hasNextInRow(row)) {
            return twoDimensionList.get(row).get(this.currentColumn++);
        }

        return null;
    }

    public boolean hasNextInColumn(Integer column) {
        if (this.twoDimensionList.size() == 0){
            return false;
        }
        if (this.currentRow < maxRowIndex) {
            if (column < maxColumnIndex) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean hasNextInCrossDown() {
        if (this.twoDimensionList.size() == 0) {
            return false;
        }
        if (this.currentRow < maxRowIndex){
            if (this.currentColumn < maxColumnIndex){
                return true;
            }
            return false;
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
        if (this.maxRowIndex >= 0){
            if (this.currentColumn < maxColumnIndex){
                return true;
            }
            return false;
        }
        return false;
    }

    public Cell nextInCrossUp() {
        if (hasNextInCrossUp()) {
            return twoDimensionList.get(--this.maxRowIndex).get(this.currentColumn++);
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
        if (this.currentColumn < maxColumnIndex) {
            return true;
        }
        this.currentColumn = 0;
        if (this.currentRow < maxRowIndex - 1) {
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
