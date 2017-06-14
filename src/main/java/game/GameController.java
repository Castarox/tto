package game;

import java.util.*;

public class GameController {
    private Game game;
    private UserInput userInput;
    private List<Player> playerList;

    public GameController(List<Player> playerList, GameState gameState, Board board, Integer moveCounter, UserInput userInput) {
        this.playerList = playerList;
        this.game = new Game(this.randomPlayer(this.playerList), gameState, board, moveCounter, this.playerList);
        this.userInput = userInput;
    }

    public static List<Player> getPlayers(){
        List<Player> playerList = new ArrayList<>();
        UserInput userInput = new UserInput(System.in);
        String firstPlayerName = userInput.getName();
        String secondPlayerName = userInput.getName();
        Player firstPlayer = new Player(firstPlayerName, Seed.CROSS);
        Player secondPlayer = new Player(secondPlayerName, Seed.NOUGHT);
        playerList.add(firstPlayer);
        playerList.add(secondPlayer);
        return playerList;
    }

    public static void initGame() {
        List<Player> playerList = GameController.getPlayers();
        GameController gameController = new GameController(playerList, GameState.PLAYING, new Board(), 0, new UserInput(System.in));
        gameController.runGame();
    }

    public Map<String, Integer> getUserMove() {
        Map<String, Integer> playerMove = new HashMap<>();
        Integer row = this.userInput.getInteger();
        Integer column = this.userInput.getInteger();
        playerMove.put("row", row);
        playerMove.put("column", column);
        return playerMove;
    }

    public void updateGame(){
        Map<String, Integer> playerMove = this.getUserMove();
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
