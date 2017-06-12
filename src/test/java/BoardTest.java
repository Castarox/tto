import com.sun.org.apache.xpath.internal.operations.Bool;
import game.Board;
import game.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testInitMethodCreateCorrectCells(){
        Board board = new Board();
        board.init();
        assertTrue(isCellObjectsInArrayAreEquals(board.getCells()));
    }

    private boolean isCellObjectsInArrayAreEquals(Cell[][] cells){
        Integer rowRange = 3;
        Integer columnRange = 3;
        for (int row = 0; row < rowRange; row++) {
            for (int column = 0; column < columnRange; column++) {
                if(!cells[row][column].equals(new Cell(row, column))){
                    return false;
                }
            }
        }
        return true;
    }
}
