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

    public void checkForChangeState() {
        Map<Point, String> mapOfPosition = fillMapWithPosition();

    }

    public boolean updateGame() {
        if (!updateBoard(this.currentPlayer)) {
            return false;
        }
        checkForChangeState();
    }
}
