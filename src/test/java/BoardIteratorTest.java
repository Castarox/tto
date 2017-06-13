import game.BoardIterator;
import game.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BoardIteratorTest {
    private List<List<Cell>> testList;
    private Integer maxRowIndex;
    private Integer maxColumnIndex;
    private BoardIterator boardIterator;

    private List<List<Cell>> createTestList(){
        List<List<Cell>> testList = new ArrayList<>();
        Integer rowNumber = 3;
        Integer columnNumber = 3;
        Integer currentRowIndex;
        Integer currentColumnIndex;
        for(currentRowIndex = 0; currentRowIndex < rowNumber; currentRowIndex++){
            List<Cell> row = new ArrayList<>();
            for(currentColumnIndex = 0; currentColumnIndex < columnNumber; currentColumnIndex++){
                Cell cell = new Cell(currentRowIndex, currentColumnIndex);
                row.add(cell);
            }
            testList.add(row);
        }
        return testList;
    }

    @BeforeEach
    void setup(){
        testList = createTestList();
        maxRowIndex = testList.size() - 1;
        maxColumnIndex = testList.get(0).size() - 1;
        boardIterator = new BoardIterator(testList);
    }

    @Test
    void testNextMethodIterateThroughWholeListAndReturnLastObject(){
        Cell exceptedCell = new Cell(maxRowIndex, maxColumnIndex);
        Cell result = null;
        while (boardIterator.hasNext()){
            result = boardIterator.next();
        }
        assertEquals(exceptedCell, result);

    }

    @ParameterizedTest
    @MethodSource(names = "createArgumentsContainsIndexToTest")
    void testNextInRowMethodIterateThroughWholeRowAndReturnLastObject(Integer testRowIndex){
        Cell exceptedCell = new Cell(testRowIndex, maxColumnIndex);
        Cell result = null;
        while (boardIterator.hasNextInRow(testRowIndex)){
            result = boardIterator.nextInRow(testRowIndex);
        }
        assertEquals(exceptedCell, result);
    }

    @ParameterizedTest
    @MethodSource(names = "createArgumentsContainsIndexToTest")
    void testNextInColumnMethodIterateThroughWholeRowAndReturnLastObject(Integer testColumnIndex){
        Cell exceptedCell = new Cell(maxRowIndex, testColumnIndex);
        Cell result = null;
        while (boardIterator.hasNextInColumn(testColumnIndex)){
            result = boardIterator.nextInColumn(testColumnIndex);
        }
        assertEquals(exceptedCell, result);
    }

    @Test
    void testNextInCrossDownMethodIterateThroughWholeRowAndReturnLastObject(){
        Cell exceptedCell = new Cell(maxRowIndex, maxColumnIndex);
        Cell result = null;
        while (boardIterator.hasNextInCrossDown()){
            result = boardIterator.nextInCrossDown();
            Integer dupa = result.getColumn();
            Integer lol = result.getRow();
        }
        assertEquals(exceptedCell, result);
    }

    @Test
    void testNextInCrossUpMethodIterateThroughWholeRowAndReturnLastObject(){
        Integer exceptedRowIndex = 0;
        Cell exceptedCell = new Cell(exceptedRowIndex, maxColumnIndex);
        Cell result = null;
        while (boardIterator.hasNextInCrossUp()){
            result = boardIterator.nextInCrossUp();
            Integer dupa = result.getColumn();
            Integer lol = result.getRow();
        }
        assertEquals(exceptedCell, result);
    }

    private static Stream<Arguments> createArgumentsContainsIndexToTest(){
        return Stream.of(
                ObjectArrayArguments.create(0),
                ObjectArrayArguments.create(1),
                ObjectArrayArguments.create(2)
        );
    }

}
