package game.model;

import game.enums.Seed;
import game.ui.ViewConsole;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> playerList;
    private Player currentPlayer;
    private Board board;
    private Integer moveCounter;

    public Game(Player currentPlayer, Board board, Integer moveCounter, List<Player> playerList) {
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.moveCounter = moveCounter;
        this.playerList = playerList;
    }

    void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void incrementMoves() {
        this.moveCounter++;
    }

    Integer getMoveCounter() {
        return moveCounter;
    }

    void setMoveCounter(Integer moveCounter) {
        this.moveCounter = moveCounter;
    }

    public boolean isWin() {
        return isWinPossible() && this.isWinCondition(this.currentPlayer);
    }

    boolean isWinPossible(){
        Integer minimumNumberOfMovesToWin = 5;
        return this.moveCounter >= minimumNumberOfMovesToWin;
    }

    public Boolean updateBoard(Player player) {
        Integer rowIndexToUpdate = player.getMove().get("row");
        Integer columnIndexToUpdate = player.getMove().get("column");
        Seed seed = player.getSeed();
        try {
            this.board.updateBoard(rowIndexToUpdate, columnIndexToUpdate, seed);
            return true;
        } catch (IllegalArgumentException e) {
            ViewConsole viewConsole = new ViewConsole(System.out);
            viewConsole.errorMessage(e.getMessage());
            return false;
        }
    }

    Map<Point, String> fillMapWithPosition(){
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

    private boolean isWinCondition(Player player) {
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
                return this.board.testWinForExtremeMiddleCells(playerSeed, lastMove);
            case "topLeftCorner":
            case "bottomRightCorner":
                return this.board.testTopLeftOrLowerRightCornerForWin(playerSeed, lastMove);
            case "topRightCorner":
            case "bottomLeftCorner":
                return this.board.testTopRightOrBottomLeftCornerForWin(playerSeed, lastMove);
            case "center":
                return this.board.testMiddleForWin(playerSeed, lastMove);
        }

        return false;
    }

    public boolean isDraw(){
        Integer numberOfMovesToDraw = 9;
        return this.moveCounter.equals(numberOfMovesToDraw);
    }

    public void switchPlayer(){
        Integer indexOfCurrentPlayer = this.playerList.indexOf(this.currentPlayer);
        Integer firstIndex = 0;
        Integer lastIndex = 1;
        if (indexOfCurrentPlayer.equals(firstIndex)) {
            this.setCurrentPlayer(playerList.get(lastIndex));
        }else{
            this.setCurrentPlayer(playerList.get(firstIndex));
        }
    }

    void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }
}
