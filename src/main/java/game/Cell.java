package game;

public class Cell {
    private Seed seed;
    private Integer row;
    private Integer column;

    public Cell(Integer row, Integer column){
        setRow(row);
        setColumn(column);
    }

    public void clear(){
        setSeed(Seed.EMPTY);
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    void setRow(Integer row) throws IllegalArgumentException {
        if(row < 1 || row > 3){
            throw new IllegalArgumentException("Given parameters out of range (1,3)");
        }
        this.row = row;
    }

    void setColumn(Integer column) throws IllegalArgumentException {
        if(column < 1 || column > 3){
            throw new IllegalArgumentException("Given parameters out of range (1,3)");
        }
        this.row = column;
    }
}
