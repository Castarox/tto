import com.sun.org.apache.xpath.internal.operations.Bool;
import game.Board;
import game.Cell;
import game.Seed;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoardTest {

    @Test
    void testInitMethodCreateCorrectCells(){
        Board board = new Board();
        board.init();
        assertTrue(isCellObjectsInArrayAreEquals(board.getCells()));
    }

    @Test
    void testUpdateMethodCorrectlySetCellInBoard(){
        Integer exceptedUpdatedRow = 1;
        Integer exceptedUpdatedColumn = 1;
        Seed exceptedSeed = Seed.CROSS;
        Board board = new Board();
        board.setCells(createFilledListWithCellsContainsEmptySeed());
        board.updateBoard(exceptedUpdatedRow, exceptedUpdatedColumn, exceptedSeed);
        assertEquals(exceptedSeed, board.getCells().get(exceptedUpdatedRow).get(exceptedUpdatedColumn).getSeed());
    }

    @Test
    void testUpdateMethodThrowsExceptionIfCellIsBusy(){
        Integer exceptedUpdatedRow = 1;
        Integer exceptedUpdatedColumn = 1;
        Seed exceptedSeed = Seed.CROSS;
        Board board = new Board();
        board.setCells(createFilledListWithCellsContainsNotEmptySeed());
        assertThrows(IllegalArgumentException.class, () -> {
            board.updateBoard(exceptedUpdatedRow, exceptedUpdatedColumn, exceptedSeed);
        });
    }

    private List<List<Cell>> createFilledListWithCellsContainsEmptySeed(){
        List<List<Cell>> cells = new ArrayList<>();
        Integer rowRange = 3;
        Integer columnRange = 3;
        for (int row = 0; row < rowRange; row++) {
            List<Cell> rowCells = new ArrayList<>();
            for (int column = 0; column < columnRange; column++) {
                Cell cell = new Cell(row, column);
                rowCells.add(cell);
            }
            cells.add(rowCells);
        }
        return cells;
    }

    private List<List<Cell>> createFilledListWithCellsContainsNotEmptySeed(){
        List<List<Cell>> cells = new ArrayList<>();
        Integer rowRange = 3;
        Integer columnRange = 3;
        for (int row = 0; row < rowRange; row++) {
            List<Cell> rowCells = new ArrayList<>();
            for (int column = 0; column < columnRange; column++) {
                Cell cell = new Cell(row, column);
                cell.setSeed(Seed.CROSS);
                rowCells.add(cell);
            }
            cells.add(rowCells);
        }
        return cells;
    }

    private boolean isCellObjectsInArrayAreEquals(List<List<Cell>> cells){
        Integer rowRange = 3;
        Integer columnRange = 3;
        for (int row = 0; row < rowRange; row++) {
            for (int column = 0; column < columnRange; column++) {
                Cell cell = new Cell(row, column);
                if(!cells.get(row).get(column).equals(cell)){
                    return false;
                }
            }
        }
        return true;
    }



}
