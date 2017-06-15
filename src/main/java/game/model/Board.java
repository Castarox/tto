package game.model;

import game.iterators.BoardIterator;
import game.enums.Seed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Cell cellToUpdate = getCells().get(rowToUpdate).get(columnToUpdate);
        if (cellToUpdate.getSeed() == Seed.EMPTY){
            cellToUpdate.setSeed(newSeed);
        }
        else {
            throw new IllegalArgumentException("Cell are already taken you lose turn (NO MERCY FOR CHEATER :)");
        }

    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public boolean testWinForExtremeMiddleCells(Seed seed, Map<String, Integer> lastMove) {
        return testRowForWin(lastMove.get("row"), seed) || testColumnForWin(lastMove.get("column"), seed);
    }

    public boolean testTopRightOrBottomLeftCornerForWin(Seed seed, Map<String, Integer> lastMove){
        if (testRowForWin(lastMove.get("row"), seed) || testColumnForWin(lastMove.get("column"), seed)) {
            return true;
        }
        return testUpCrossForWin(seed);
    }

    public boolean testTopLeftOrLowerRightCornerForWin(Seed seed, Map<String, Integer> lastMove){
        if (testRowForWin(lastMove.get("row"), seed) || testColumnForWin(lastMove.get("column"), seed)) {
            return true;
        }
        return testDownCrossForWin(seed);
    }

    public boolean testMiddleForWin(Seed seed, Map<String, Integer> lastMove){
        if (testRowForWin(lastMove.get("row"), seed) || testColumnForWin(lastMove.get("column"), seed)) {
            return true;
        }
        return testUpCrossForWin(seed) || testDownCrossForWin(seed);
    }

    private boolean testRowForWin(Integer row, Seed seed){
        BoardIterator boardIterator = new BoardIterator(getCells());
        while (boardIterator.hasNextInRow()) {
            if(boardIterator.nextInRow(row).getSeed() != seed){ return false;}
        }
        return true;
    }

    private boolean testColumnForWin(Integer column, Seed seed){
        BoardIterator boardIterator = new BoardIterator(getCells());
        while (boardIterator.hasNextInColumn(column)){
            if (boardIterator.nextInColumn(column).getSeed() != seed){return false;}
        }
        return true;
    }

    private boolean testDownCrossForWin(Seed seed){
        BoardIterator boardIterator = new BoardIterator(getCells());
        while (boardIterator.hasNextInCrossDown()){
            if (boardIterator.nextInCrossDown().getSeed() != seed) {
                return false;
            }
        }
        return true;
    }

    private boolean testUpCrossForWin(Seed seed){
        BoardIterator boardIterator = new BoardIterator(getCells());
        while (boardIterator.hasNextInCrossUp()){
            if (boardIterator.nextInCrossUp().getSeed() != seed) {
                return false;
            }
        }
        return true;
    }




}

