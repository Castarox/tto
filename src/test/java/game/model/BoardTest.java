package game.model;

import game.model.Board;
import game.model.Cell;
import game.enums.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp(){
        board = new Board();
    }

    @Test
    @DisplayName("Test init method create correct board")
    void testInitMethodCreateCorrectCells(){
        board.init();
        assertTrue(isCellObjectsInArrayAreEquals(board.getCells()));
    }

    @Test
    @DisplayName("Test update method correctly set new cell on board")
    void testUpdateMethodCorrectlySetCellInBoard(){
        Integer exceptedUpdatedRow = 1;
        Integer exceptedUpdatedColumn = 1;
        Seed exceptedSeed = Seed.CROSS;
        board.setCells(createFilledListWithCellsContainsEmptySeed());
        board.updateBoard(exceptedUpdatedRow, exceptedUpdatedColumn, exceptedSeed);
        assertEquals(exceptedSeed, board.getCells().get(exceptedUpdatedRow).get(exceptedUpdatedColumn).getSeed());
    }

    @Test
    @DisplayName("Test update method throws IllegalArgument Exception if Cell is Busy")
    void testUpdateMethodThrowsExceptionIfCellIsBusy(){
        Integer exceptedUpdatedRow = 1;
        Integer exceptedUpdatedColumn = 1;
        Seed exceptedSeed = Seed.CROSS;
        board.setCells(createFilledListWithCellsContainsCrossSeed());
        assertThrows(IllegalArgumentException.class, () -> {
            board.updateBoard(exceptedUpdatedRow, exceptedUpdatedColumn, exceptedSeed);
        });
    }

    @Nested
    @DisplayName("Test win condition testing methods")
    class winConditionTest{
        private Integer firstRowIndex;
        private Integer middleRowIndex;
        private Integer lastRowIndex;
        private Integer firstColumnIndex;
        private Integer middleColumnIndex;
        private Integer lastColumnIndex;
        private Map<String, Integer> testMove;
        private Seed testSeed;

        @BeforeEach
        void setup(){
            firstRowIndex = 0;
            firstColumnIndex = 0;
            middleColumnIndex = 1;
            middleRowIndex = 1;
            lastRowIndex = 2;
            lastColumnIndex = 2;
            testMove = new HashMap<>();
            testSeed = Seed.CROSS;
        }

        @Test
        @DisplayName("testTopLeftOrLowerRight method return false if cells have empty seeds")
        void testTestTopLeftOrLowerRightCornerReturnFallsIfCellsHaveEmptySeed(){
            testMove.put("row", firstRowIndex);
            testMove.put("column", firstColumnIndex);
            board.setCells(createFilledListWithCellsContainsEmptySeed());
            assertFalse(board.testTopLeftOrLowerRightCornerForWin(testSeed, testMove));

        }

        @Test
        @DisplayName("testTopLeftOrLowerRight method return true if cells have seed equal to passed seed")
        void testTestTopLeftOrLowerRightCornerReturnTrueIfCellsHaveSeedEqualToPassedSeed(){
            testMove.put("row", firstRowIndex);
            testMove.put("column", firstColumnIndex);
            board.setCells(createFilledListWithCellsContainsCrossSeed());
            assertTrue(board.testTopLeftOrLowerRightCornerForWin(testSeed, testMove));
        }

        @Test
        @DisplayName("testWinForExtremeMiddleCells return false if cells have empty seeds")
        void testTestWinForExtremeMiddleCellsReturnFalseIfCellsHaveEmptySeed(){
            testMove.put("row", middleRowIndex);
            testMove.put("column", firstColumnIndex);
            board.setCells(createFilledListWithCellsContainsEmptySeed());
            assertFalse(board.testWinForExtremeMiddleCells(testSeed, testMove));
        }

        @Test
        @DisplayName("testWinForExtremeMiddleCells return true if cells have seed equal to passed seed")
        void testTestWinForExtremeMiddleCellsReturnTrueIfCellsHaveSeedEqualToPassedSeed(){
            testMove.put("row", firstRowIndex);
            testMove.put("column", middleColumnIndex);
            board.setCells(createFilledListWithCellsContainsCrossSeed());
            assertTrue(board.testWinForExtremeMiddleCells(testSeed, testMove));
        }

        @Test
        @DisplayName("testTopRightOrBottomLeftCorner return false if cells have empty seeds")
        void testTestTopRightOrBottomLeftCornerForWinReturnFalseIfCellHaveEmptySeed(){
            testMove.put("row", lastRowIndex);
            testMove.put("column", firstColumnIndex);
            board.setCells(createFilledListWithCellsContainsEmptySeed());
            assertFalse(board.testTopRightOrBottomLeftCornerForWin(testSeed, testMove));
        }

        @Test
        @DisplayName("testTopRightOrBottomLeftCorner return true if cells have seed equal to passed seed")
        void testTestTopRightOrBottomLeftCornerForWinReturnTrueIfCellHaveSeedEqualToPassedSeed(){
            testMove.put("row", firstRowIndex);
            testMove.put("column", lastColumnIndex);
            board.setCells(createFilledListWithCellsContainsCrossSeed());
            assertTrue(board.testTopRightOrBottomLeftCornerForWin(testSeed, testMove));
        }

        @Test
        @DisplayName("testMiddleForWin return false if cells have empty seeds")
        void testTestMiddleForWinReturnFalseIfCellHaveEmptySeed(){
            testMove.put("row", middleRowIndex);
            testMove.put("column", middleColumnIndex);
            board.setCells(createFilledListWithCellsContainsEmptySeed());
            assertFalse(board.testMiddleForWin(testSeed, testMove));
        }

        @Test
        @DisplayName("testMiddleForWin return true if cells have seed equal to passed seed")
        void testTestMiddleForWinReturnTrueIfCellHaveSeedEqualToPassedSeed(){
            testMove.put("row", middleColumnIndex);
            testMove.put("column", middleColumnIndex);
            board.setCells(createFilledListWithCellsContainsCrossSeed());
            assertTrue(board.testMiddleForWin(testSeed, testMove));
        }

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

    private List<List<Cell>> createFilledListWithCellsContainsCrossSeed(){
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
