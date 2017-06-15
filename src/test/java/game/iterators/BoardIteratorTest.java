package game.iterators;

import game.iterators.BoardIterator;
import game.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @BeforeEach
    void setup(){
        testList = createTestList();
        maxRowIndex = testList.size() - 1;
        maxColumnIndex = testList.get(0).size() - 1;
        boardIterator = new BoardIterator(testList);
    }

    @Test
    @DisplayName("Test next Method iterate through whole two dimensional List and return last object")
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
    @DisplayName("Test nextInRow Method iterate through whole row two dimensional List and return last object")
    void testNextInRowMethodIterateThroughWholeRowAndReturnLastObject(Integer testRowIndex){
        Cell exceptedCell = new Cell(testRowIndex, maxColumnIndex);
        Cell result = null;
        while (boardIterator.hasNextInRow()){
            result = boardIterator.nextInRow(testRowIndex);
        }
        assertEquals(exceptedCell, result);
    }

    @ParameterizedTest
    @MethodSource(names = "createArgumentsContainsIndexToTest")
    @DisplayName("Test nextInColumn Method iterate through whole column two dimensional List and return last object")
    void testNextInColumnMethodIterateThroughWholeRowAndReturnLastObject(Integer testColumnIndex){
        Cell exceptedCell = new Cell(maxRowIndex, testColumnIndex);
        Cell result = null;
        while (boardIterator.hasNextInColumn(testColumnIndex)){
            result = boardIterator.nextInColumn(testColumnIndex);
        }
        assertEquals(exceptedCell, result);
    }

    @Test
    @DisplayName("Test nextInCrossDown Method iterate from 0,0 Index in two dimensional" +
            " to last row Index and Last column Index List and return last object")
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
    @DisplayName("Test nextInRow Method iterate from last Row Index and First in two dimensional List" +
            "to first row Index and Last column Index and return last object")
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

    private static Stream<Arguments> createArgumentsContainsIndexToTest(){
        Integer firstIndexToTest = 0;
        Integer secondIndexToTest = 1;
        Integer thirdIndexToTest = 2;
        return Stream.of(
                ObjectArrayArguments.create(firstIndexToTest),
                ObjectArrayArguments.create(secondIndexToTest),
                ObjectArrayArguments.create(thirdIndexToTest)
        );
    }

}
