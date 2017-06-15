package game;

import java.util.*;

public class GameController {
    private Game game;
    private UserInput userInput;
    private List<Player> playerList;
    private Board board;
    private ViewConsole viewConsole;

    public ViewConsole getViewConsole() {
        return viewConsole;
    }

    public void setViewConsole(ViewConsole viewConsole) {
        this.viewConsole = viewConsole;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setUserInput(UserInput userInput) {
        this.userInput = userInput;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Board getPreparedBoard() {
        Board board = new Board();
        board.init();
        return board;
    }

    public UserInput getPreparedUserInput(){
        return new UserInput(System.in);
    }

    public String getPlayerName() {
        return this.userInput.getName();
    }

    public Player createFirstPlayer(String name) {
        return new Player(name, Seed.CROSS);
    }

    public Player createSecondPlayer(String name) {
        return new Player(name, Seed.NOUGHT);
    }

    public List<Player> getPlayers(){
        List<Player> playerList = new ArrayList<>();
        this.getViewConsole().getNameMessage(playerList.size());
        Player firstPlayer = createFirstPlayer(getPlayerName());
        playerList.add(firstPlayer);
        this.getViewConsole().getNameMessage(playerList.size());
        Player secondPlayer = createSecondPlayer(getPlayerName());
        playerList.add(secondPlayer);
        return playerList;
    }

    public List<Player> getPreparedPlayers(){
        return getPlayers();
    }

    public ViewConsole getPreparedViewConsole(){
        return new ViewConsole(System.out);
    }

    public Game getPreparedGame(){
        Player currentPlayer = this.randomPlayer(this.playerList);
        GameState gameState = GameState.PLAYING;
        Integer currentMove = 0;
        return new Game(currentPlayer, gameState, this.board, currentMove, this.playerList);
    }

    public void prepareGameController(){
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

    public Map<String, Integer> getUserMove() {
        Map<String, Integer> playerMove = new HashMap<>();
        playerMove.put("row", getRowFromPlayer());
        playerMove.put("column", getColumnFromPlayer());
        return playerMove;
    }

    public Integer getColumnFromPlayer(){
        Integer columnIndex;
        Boolean getColumn = true;
        while (getColumn) {
            this.getViewConsole().getColumnMessage();
            try {
                columnIndex = userInput.getInteger() - 1;
                return columnIndex;
            }catch (InputMismatchException e){
                viewConsole.errorMessage(e.getMessage());

            }
        }
        return null;
    }

    public Integer getRowFromPlayer(){
        Boolean getRow = true;
        Integer rowIndex;
        while (getRow) {
            this.getViewConsole().getRowMessage();
            try {
                rowIndex = userInput.getInteger() - 1;
                getRow = false;
                return rowIndex;
            }catch (InputMismatchException e){
                viewConsole.errorMessage(e.getMessage());
            }
        }
        return null;
    }

    public void playerMove() {
        // TODO Make player make move get hashmap instead of two integers
        Boolean makingMove = true;
        while (makingMove) {
            Map<String, Integer> playerMove = this.getUserMove();
            try {
                this.game.getCurrentPlayer().makeMove(playerMove.get("row"), playerMove.get("column"));
                makingMove = false;
            }catch (IllegalArgumentException e){
                this.getViewConsole().errorMessage(e.getMessage());
            }
        }
    }

    public void updateGame(){
        this.playerMove();
        this.game.updateBoard(this.game.getCurrentPlayer());
        this.game.incrementMoves();
    }

    public void displayBoard(){
        List<List<Cell>> boardToDisplay = this.board.getCells();
        this.getViewConsole().printBoard(boardToDisplay);
    }

    public void runGame() {
        Boolean playing = true;
        while (playing) {
            displayBoard();
            viewConsole.turnMessage(game.getCurrentPlayer().getName());
            updateGame();

            if (Win()) {
                playing = false;
            }

            if (Draw()) {
                break;
            }

            nextPlayer();
        }
    }

    public boolean Win() {
        if (this.game.isWin()) {
            this.getViewConsole().winMessage(game.getCurrentPlayer().getName());
            return true;
        }
        return false;
    }

    public boolean Draw() {
        if (this.game.isDraw()) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    public void nextPlayer() {
        this.game.switchPlayer();
    }

    public Player randomPlayer(List<Player> playerList) {
        Random randomizer = new Random();
        Player randomPlayer = playerList.get(randomizer.nextInt(playerList.size()));
        return randomPlayer;
    }
}
