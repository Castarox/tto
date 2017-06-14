package game;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Game {
    private List<Player> playerList;
    private Player currentPlayer;
    private GameState currentState;
    private Board board;
    private Integer moveCounter;

    public Game(Player currentPlayer, GameState gameState, Board board, Integer moveCounter, List<Player> playerList) {
        this.currentPlayer = currentPlayer;
        this.currentState = gameState;
        this.board = board;
        this.moveCounter = moveCounter;
        this.playerList = playerList;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void incrementMoves() {
        this.moveCounter++;
    }

    public boolean isWin(){
        if (isWinPossible()) {
            return this.isWinCondition(this.currentPlayer);
        }
        return false;
    }

    public boolean isWinPossible(){
        if (this.moveCounter > 4) {
            return true;
        }
        return false;
    }

    public void setCurrentState(GameState state) {
        this.currentState = state;
    }

    public boolean updateBoard(Player player) {
        Integer rowIndexToUpdate = player.getMove().get("row") - 1;
        Integer columnIndexToUpdate = player.getMove().get("column") - 1;
        Seed seed = player.getSeed();
        try {
            this.board.updateBoard(rowIndexToUpdate, columnIndexToUpdate, seed);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public Map<Point, String> fillMapWithPosition(){
        Map<Point, String> mapOfPosition = new HashMap<>();
        mapOfPosition.put(new Point(0,0), "topLeftCorner");
        mapOfPosition.put(new Point(0,1), "topMiddle");
        mapOfPosition.put(new Point(0,2), "topRightCorner");
        mapOfPosition.put(new Point(1,0), "middleLeft");
        mapOfPosition.put(new Point(1,1), "center");
        mapOfPosition.put(new Point(1,2), "middleRight");
        mapOfPosition.put(new Point(2,0), "bottomLeftCorner");
        mapOfPosition.put(new Point(2,1), "bottomMiddle");
        mapOfPosition.put(new Point(2,2), "bottomRightCorner");
        return mapOfPosition;
    }

    public boolean isWinCondition(Player player) {
        Map<Point, String> mapOfPosition = fillMapWithPosition();
        Integer rowPosition = player.getMove().get("row");
        Integer columnPosition = player.getMove().get("column");
        Seed playerSeed = player.getSeed();
        Map<String, Integer> lastMove = player.getMove();
        Point playerMovePosition =  new Point(rowPosition, columnPosition);
        switch (mapOfPosition.get(playerMovePosition)){
            case "topMiddle":
            case "middleLeft":
            case "middleRight":
            case "bottomMiddle":
                return this.board.testWinforExtremeMiddleCells(playerSeed, lastMove);
            case "topLeftCorner":
            case "bottomRightCorner":
                return this.board.testTopLeftOrLowerRightCorrnerForWin(playerSeed, lastMove);
            case "topRightCorner":
            case "bottomLeftCorner":
                return this.board.testTopRightOrBottomLeftCorrnerForWin(playerSeed, lastMove);
            case "center":
                return this.board.testMiddleForWin(playerSeed, lastMove);
        }

        return false;
    }

    public boolean isDraw(){
        return isDrawPossible();
    }

    public boolean isDrawPossible(){
        if (this.moveCounter == 9) {
            return true;
        }
        return false;
    }

    public void switchPlayer(){
        Integer indexOfCurrentPlayer = this.playerList.indexOf(this.currentPlayer);
        ListIterator<Player> playerIterator = this.playerList.listIterator(indexOfCurrentPlayer);
        if (playerIterator.hasNext()) {
            this.currentPlayer = playerIterator.next();
        }
        this.currentPlayer = playerIterator.previous();
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setPlayerWinState(Player player){
        if (player.getSeed() == Seed.CROSS) {
            setCurrentState(GameState.CROSS_WON);
        } else {
            setCurrentState(GameState.NOUGHT_WON);
        }
    }

    public boolean isForCheckPlayerWinCondition() {
        Integer minimumNumberOfMoveToWin = 5;
        Integer maximumNumberOfMoves = 9;
        return this.moveCounter >= minimumNumberOfMoveToWin && this.moveCounter < maximumNumberOfMoves;
    }

    public boolean isForCheckStateCondition() {
        Integer minimumNumberOfMoveToChangeState = 5;
        return this.moveCounter >= minimumNumberOfMoveToChangeState;
    }

}
