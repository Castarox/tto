package game;

import java.util.Iterator;
import java.util.List;

public class BoardIterator implements Iterator {
    private Integer currentRow;
    private Integer currentColumn;
    private List<List<Cell>> twoDimensionList;

    public BoardIterator(List<List<Cell>> twoDimensionList) {
        this.twoDimensionList = twoDimensionList;
        this.currentRow = 0;
        this.currentColumn = 0;
    }

    public boolean hasNextInRow(Integer row) {
        if (this.twoDimensionList.size() == 0){
            return false;
        }
        Integer maxColumnIndex = this.twoDimensionList.get(row).size();
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
        Integer maxRowIndex = this.twoDimensionList.size();
        if (this.currentRow < maxRowIndex) {
            Integer maxColumnIndex = this.twoDimensionList.get(this.currentRow).size() - 1;
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
        Integer maxRowIndex = this.twoDimensionList.size();
        Integer maxColumnIndex = this.twoDimensionList.get(this.currentRow).size();
        if (this.currentRow < maxRowIndex){
            if (this.currentColumn < maxColumnIndex){
                return true;
            }
            return false;
        }
        return false;
    }

    public Cell nextInCrossDown() {
        if (hasNext()) {
            return twoDimensionList.get(this.currentRow++).get(this.currentColumn++);
        }

        return null;
    }

    public boolean hasNextInCrossUp() {
        if (this.twoDimensionList.size() == 0) {
            return false;
        }
        Integer maxRowIndex = this.twoDimensionList.size() - 1;
        Integer maxColumnIndex = this.twoDimensionList.get(this.currentRow).size();
        if (this.currentRow < maxRowIndex){
            if (this.currentColumn < maxColumnIndex){
                return true;
            }
            return false;
        }
        return false;
    }

    public Cell nextInCrossUp() {
        if (hasNext()) {
            return twoDimensionList.get(this.currentRow++).get(this.currentColumn++);
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
        Integer maxRowIndex = this.twoDimensionList.size() - 1;
        Integer maxColumnIndex = this.twoDimensionList.get(this.currentRow).size();
        if (this.currentColumn < maxColumnIndex) {
            return true;
        }
        this.currentColumn = 0;
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
