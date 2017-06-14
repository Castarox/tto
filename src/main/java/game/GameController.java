package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    private Game game;
    private UserInput userInput;
    private List<Player> playerList;

    public GameController(List<Player> playerList) {
        this.playerList = playerList;
        this.game = new Game(this.randomPlayer(), GameState.PLAYING, new Board(), 0, this.playerList);
        this.userInput = new UserInput(System.in);
    }

    public static void initGame() {
        List<Player> playerList = new ArrayList<>();
        UserInput userInput = new UserInput(System.in);
        String firstPlayerName = userInput.getName();
        String secondPlayerName = userInput.getName();
        Player firstPlayer = new Player(firstPlayerName, Seed.CROSS);
        Player secondPlayer = new Player(secondPlayerName, Seed.NOUGHT);
        playerList.add(firstPlayer);
        playerList.add(secondPlayer);
        GameController gameController = new GameController(playerList);
        gameController.runGame();
    }

    public void runGame() {
        while (true) {
            Integer row = this.userInput.getInteger();
            Integer column = this.userInput.getInteger();
            this.game.getCurrentPlayer().makeMove(row, column);
            this.game.updateBoard(this.game.getCurrentPlayer());
            this.game.incrementMoves();
            if (this.game.isWin()) {
                System.out.println("You won" + this.game.getCurrentPlayer().getName());
                break;
            }
            if (this.game.isDraw()) {
                System.out.println("Draw");
                break;
            }
            this.game.switchPlayer();
        }
    }

    public Player randomPlayer() {
        Random randomizer = new Random();
        Player randomPlayer = this.playerList.get(randomizer.nextInt(this.playerList.size()));
        return randomPlayer;
    }
}
