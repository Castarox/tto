package game;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Player currentPlayer;
    private GameState currentState;
    private Board board;
    private Integer moveCounter;

    public Game(Player currentPlayer, GameState gameState, Board board, Integer moveCounter) {
        this.currentPlayer = currentPlayer;
        this.currentState = gameState;
        this.board = board;
        this.moveCounter = moveCounter;
    }

    public void incrementMoves() {
        this.moveCounter++;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setCurrentState(GameState state) {
        this.currentState = state;
    }

    public boolean updateBoard(Player player) {
        Integer rowToUpdate = player.getMove().get("row");
        Integer columnToUpdate = player.getMove().get("column");
        Seed seed = player.getSeed();
        try {
            this.board.updateBoard(rowToUpdate, columnToUpdate, seed);
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

    public boolean isCheckDrawStateCondition() {

    }

    public boolean isForCheckStateCondition() {
        Integer minimumNumberOfMoveToChangeState = 5;
        return this.moveCounter >= minimumNumberOfMoveToChangeState;
    }

}