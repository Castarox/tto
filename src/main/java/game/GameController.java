package game;

import java.util.*;

public class GameController {
    private Game game;
    private UserInput userInput;
    private List<Player> playerList;

    public void setGame(Game game) {
        this.game = game;
    }

    public void setUserInput(UserInput userInput) {
        this.userInput = userInput;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public GameController() {
//
//        this.playerList = playerList;
//        this.game = new Game(this.randomPlayer(this.playerList),
// gameState, board, moveCounter, this.playerList);
//        this.userInput = userInput;
    }

//    public Game getPreparedGame() {
//        Game game = new Game();
//        return game;
//    }

    public Board getPreparedBorad() {
        Board board = new Board();
        board.init();
        return board;
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
        Player firstPlayer = createFirstPlayer(getPlayerName());
        Player secondPlayer = createSecondPlayer(getPlayerName());
        playerList.add(firstPlayer);
        playerList.add(secondPlayer);
        return playerList;
    }

    public List<Player> getPreparedPlayers(){
        List<Player> playerList = getPlayers();
        return playerList;
    }

    public static void initGame() {
        GameController gameController = new GameController();
        gameController.runGame();
    }

    public Map<String, Integer> getUserMove(UserInput userInput) {
        Map<String, Integer> playerMove = new HashMap<>();
        System.out.println("row");
        Integer row = userInput.getInteger();
        System.out.println("column");
        Integer column = userInput.getInteger();
        playerMove.put("row", row);
        playerMove.put("column", column);
        return playerMove;
    }

    public void updateGame(){
        Map<String, Integer> playerMove = this.getUserMove(this.userInput);
        this.game.getCurrentPlayer().makeMove(playerMove.get("row"), playerMove.get("column"));
        this.game.updateBoard(this.game.getCurrentPlayer());
        this.game.incrementMoves();
    }

    public void runGame() {
        while (true) {
            updateGame();

            if (Win()) {
                break;
            }

            if (Draw()) {
                break;
            }

            nextPlayer();
        }
    }

    public boolean Win() {
        if (this.game.isWin()) {
            System.out.println("You won" + this.game.getCurrentPlayer().getName());
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
