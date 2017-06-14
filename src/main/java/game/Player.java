package game;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;
    private Seed seed;
    private Map<String, Integer> move;

    public Player(String name, Seed seed) {
        setName(name);
        setSeed(seed);
        setMove(new HashMap<>());
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Seed getSeed() {
        return seed;
    }

    private void setSeed(Seed seed) {
        this.seed = seed;
    }

    public Map<String, Integer> getMove() {
        return move;
    }

    private void setMove(Map<String, Integer> moves) {
        this.move = moves;
    }


    public void makeMove(Integer row, Integer column) throws IllegalArgumentException {
        if (row < 0 || row > 3) {
            throw new IllegalArgumentException("Given arguments are Illegal");
        }
        else if (column < 0 || column > 3) {
            throw new IllegalArgumentException("Given arguments are Illegal");
        }
        Map<String, Integer> move = new HashMap<>();
        move.put("row", row);
        move.put("column", column);
        setMove(move);
    }
}
