package game;

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

    public boolean testWinforExtremeMiddleCells(Seed seed, Map<String, Integer> lastMove) {
        return testRowForWin(lastMove.get("row"), seed) || testColumnForWin(lastMove.get("column"), seed);
    }

    private boolean testRowForWin(Integer row, Seed seed){
        BoardIterator boardIterator = new BoardIterator(getCells());
        while (boardIterator.hasNextInRow(row)) {
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

    public boolean testTopLeftOrLowerRightCorrnerForWin(Seed seed, Map<String, Integer> lastMove){
        if (testRowForWin(lastMove.get("row"), seed) || testColumnForWin(lastMove.get("column"), seed)) {
            return true;
        }
        return testDownCrossForWin(seed);
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

    public boolean testTopRightOrBottomLeftCorrnerForWin(Seed seed, Map<String, Integer> lastMove){
        if (testRowForWin(lastMove.get("row"), seed) || testColumnForWin(lastMove.get("column"), seed)) {
            return true;
        }
        return testUpCrossForWin(seed);
    }

    public boolean testMiddleForWin(Seed seed, Map<String, Integer> lastMove){
        if (testRowForWin(lastMove.get("row"), seed) || testColumnForWin(lastMove.get("column"), seed)) {
            return true;
        }
        return testUpCrossForWin(seed) || testDownCrossForWin(seed);
    }




}

