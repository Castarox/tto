package game;

import java.util.Arrays;

public class Board {
    private Cell[][] cells;

    public Board(){

    }

    public void init(){
        cells = fillBoardWithCells();
    }

    private Cell[][] fillBoardWithCells(){
        Cell[][] cells = new Cell[3][3];
        Integer rowRange = 3;
        Integer columnRange = 3;
        for (int row = 0; row < rowRange; row++) {
            for (int column = 0; column < columnRange; column++) {
                cells[row][column] = new Cell(row, column);
            }
        }
        return cells;
    }

    public Cell[][] getCells() {
        return cells;
    }
}

