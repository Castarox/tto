package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PrimitiveIterator;

public class Board {
    private List<List<Cell>> cells;

    public void init(){
        setCells(fillBoardWithCells());
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    private List<List<Cell>> fillBoardWithCells(){
        List<List<Cell>> cells = new ArrayList<>();
        Integer rowRange = 3;
        Integer columnRange = 3;
        for (int row = 0; row < rowRange; row++) {
            List<Cell> rowCells = new ArrayList<>();
            for (int column = 0; column < columnRange; column++) {
                rowCells.add(new Cell(row, column));
            }
            cells.add(rowCells);
        }
        return cells;
    }

    public void updateBoard(Integer rowToUpdate, Integer columnToUpdate, Seed newSeed) throws IllegalArgumentException{
        // TODO pytanie do Rafała
        Cell cellToUpdate = getCells().get(rowToUpdate).get(columnToUpdate);
        if (cellToUpdate.getSeed() == Seed.EMPTY){
            cellToUpdate.setSeed(newSeed);
        }
        else {
            throw new IllegalArgumentException();
        }

    }


    public List<List<Cell>> getCells() {
        return cells;
    }

//     public boolean hasWon(Seed seed, Integer row, Integer column) {

//     }
}

