package game.model;

import game.enums.Seed;

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

    Map<String, Integer> getMove() {
        return move;
    }

    private void setMove(Map<String, Integer> moves) {
        this.move = moves;
    }

    public void makeMove(Integer row, Integer column) throws IllegalArgumentException {
        Integer minIndex = 0;
        Integer maxIndex = 2;
        if (row < minIndex || row > maxIndex) {
            throw new IllegalArgumentException("Given arguments are Illegal");
        }
        else if (column < minIndex || column > maxIndex) {
            throw new IllegalArgumentException("Given arguments are Illegal");
        }
        Map<String, Integer> move = new HashMap<>();
        move.put("row", row);
        move.put("column", column);
        setMove(move);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (seed != player.seed) return false;
        return move != null ? move.equals(player.move) : player.move == null;
    }
}
