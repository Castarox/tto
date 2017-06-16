package game.controller;

import game.enums.Seed;
import game.ui.UserInput;
import game.ui.ViewConsole;
import game.model.Board;
import game.model.Cell;
import game.model.Game;
import game.model.Player;

import java.util.*;

public class GameController {
    private Game game;
    private UserInput userInput;
    private List<Player> playerList;
    private Board board;
    private ViewConsole viewConsole;

    ViewConsole getViewConsole() {
        return viewConsole;
    }

    void setViewConsole(ViewConsole viewConsole) {
        this.viewConsole = viewConsole;
    }

    private void setBoard(Board board) {
        this.board = board;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    void setUserInput(UserInput userInput) {
        this.userInput = userInput;
    }

    void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    Board getPreparedBoard() {
        Board board = new Board();
        board.init();

        return board;
    }

    UserInput getPreparedUserInput() {
        return new UserInput(System.in);
    }

    private String getPlayerName() {
        return this.userInput.getName();
    }

    Player createFirstPlayer(String name) {
        return new Player(name, Seed.CROSS);
    }

    Player createSecondPlayer(String name) {
        return new Player(name, Seed.NOUGHT);
    }

    List<Player> getPlayers() {
        List<Player> playerList = new ArrayList<>();

        this.getViewConsole().getNameMessage(playerList.size());

        Player firstPlayer = createFirstPlayer(getPlayerName());
        playerList.add(firstPlayer);

        this.getViewConsole().getNameMessage(playerList.size());

        Player secondPlayer = createSecondPlayer(getPlayerName());
        playerList.add(secondPlayer);

        return playerList;
    }

    private List<Player> getPreparedPlayers() {
        return getPlayers();
    }

    ViewConsole getPreparedViewConsole() {
        return new ViewConsole(System.out);
    }

    Game getPreparedGame() {
        Player currentPlayer = this.randomPlayer(this.playerList);
        Integer currentMove = 0;

        return new Game(currentPlayer, this.board, currentMove, this.playerList);
    }

    private void prepareGameController() {
        this.setViewConsole(getPreparedViewConsole());
        this.setUserInput(getPreparedUserInput());
        this.setPlayerList(getPreparedPlayers());
        this.setBoard(getPreparedBoard());
        this.setGame(getPreparedGame());
    }

    public static void initGame() {
        GameController gameController = new GameController();
        gameController.prepareGameController();
        gameController.runGame();
    }

    Map<String, Integer> getUserMove() {
        Map<String, Integer> playerMove = new HashMap<>();
        playerMove.put("row", getRowFromPlayer());
        playerMove.put("column", getColumnFromPlayer());

        return playerMove;
    }

    Integer getColumnFromPlayer() {
        Integer columnIndex;
        Boolean getColumn = true;

        while (getColumn) {

            this.getViewConsole().getColumnMessage();

            try {
                columnIndex = userInput.getInteger() - 1;
                return columnIndex;
            } catch (InputMismatchException e) {
                viewConsole.errorMessage(e.getMessage());
            }

        }

        return null;
    }

    Integer getRowFromPlayer() {
        Boolean getRow = true;
        Integer rowIndex;

        while (getRow) {

            this.getViewConsole().getRowMessage();

            try {
                rowIndex = userInput.getInteger() - 1;
                getRow = false;
                return rowIndex;
            } catch (InputMismatchException e) {
                viewConsole.errorMessage(e.getMessage());
            }

        }
        return null;
    }

    private void playerMove() {
        Boolean makingMove = true;

        while (makingMove) {

            Map<String, Integer> playerMove = this.getUserMove();

            try {
                this.game.getCurrentPlayer().makeMove(playerMove.get("row"), playerMove.get("column"));
                makingMove = false;
            } catch (IllegalArgumentException e) {
                this.getViewConsole().errorMessage(e.getMessage());
            }

        }
    }

    private void updateGame() {
        this.playerMove();
        this.game.updateBoard(this.game.getCurrentPlayer());
        this.game.incrementMoves();
    }

    private void displayBoard() {
        List<List<Cell>> boardToDisplay = this.board.getCells();
        this.getViewConsole().printBoard(boardToDisplay);
    }

    private void runGame() {
        Boolean playing = true;
        viewConsole.startMessage();
        while (playing) {

            displayBoard();
            viewConsole.turnMessage(game.getCurrentPlayer().getName());
            updateGame();

            if (win()) {
                playing = false;
            }

            else if (draw()) {
                break;
            }

            nextPlayer();
        }
    }

    private boolean win() {
        if (this.game.isWin()) {
            this.getViewConsole().winMessage(game.getCurrentPlayer().getName());
            return true;
        }

        return false;
    }

    private boolean draw() {
        if (this.game.isDraw()) {
            System.out.println("draw");
            return true;
        }

        return false;
    }

    private void nextPlayer() {
        this.game.switchPlayer();
    }

    Player randomPlayer(List<Player> playerList) {
        Random random = new Random();
        Player randomPlayer = playerList.get(random.nextInt(playerList.size()));

        return randomPlayer;
    }
}
