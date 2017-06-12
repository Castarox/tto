import game.Cell;
import game.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class BoardTest {

    @Test
    void testInitMethodCreateCorrectCells(){
        Borad borad = new Board();
        borad.init();
        Cell[][] cells = createPrimitiveListWithMockedCells();
        Cell cell = mock(Cell.class);
        when(new Cell(anyInt(), anyInt())).thenReturn(cell);
        assertEquals(cells ,borad.getBoard);
    }

    private Cell[][] createPrimitiveListWithMockedCells(){
        Cell[][] cells = new Cell[3][3];
        for (Cell[] rowCells : cells) {
            for (Cell cell : rowCells) {
                cell = mock(Cell.class);
            }
        }
        return cells;
    }
}
