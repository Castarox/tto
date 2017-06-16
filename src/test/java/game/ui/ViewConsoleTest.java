package game.ui;

import game.enums.Seed;
import game.model.Cell;
import game.ui.ViewConsole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@DisplayName("Test for Correct Prints: ")
class ViewConsoleTest {
    private final ByteArrayOutputStream OUTPUT_STREAM = new ByteArrayOutputStream();
    private ViewConsole viewConsole;

    @BeforeEach
    void setup() {
        viewConsole = new ViewConsole(new PrintStream(OUTPUT_STREAM));
    }

    @Test
    @DisplayName("Test drawMessage Method")
    void testDrawMessagePrintCorrectValue() {
        viewConsole.drawMessage();
        String excepted = "DRAW\n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @ParameterizedTest
    @MethodSource(names = "createArgumentsContainsIndexToTest")
    @DisplayName("Test getNameMessage Method" )
    void testGetNameMessagePrintCorrectValue(Integer index) {
        viewConsole.getNameMessage(index);
        Integer playerNumber = index + 1;
        String excepted = "Player " + playerNumber.toString() + " enter Name\n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @Test
    @DisplayName("Test printBoard with empty Seeds Method")
    void testPrintBoardPrintCorrectValueIfSeedsEmpty(){
        viewConsole.printBoard(createListFiledWithCellsContainsEmptySeed());
        String excepted = " | | \n" +
                "-----\n" +
                " | | \n" +
                "-----\n" +
                " | | \n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @Test
    @DisplayName("Test printBoard with cross Seeds Method")
    void testPrintBoardPrintCorrectValueIfSeedsAreCross(){
        viewConsole.printBoard(createListFiledWithCellsContainsCrossSeed());
        String excepted = "X|X|X\n" +
                "-----\n" +
                "X|X|X\n" +
                "-----\n" +
                "X|X|X\n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @Test
    @DisplayName("Test printBoard with nought Seeds Method")
    void testPrintBoardPrintCorrectValueIfSeedsAreNought(){
        viewConsole.printBoard(createListFiledWithCellsContainsNoughtSeed());
        String excepted = "O|O|O\n" +
                "-----\n" +
                "O|O|O\n" +
                "-----\n" +
                "O|O|O\n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @Test
    @DisplayName("Test wrongInputMessage Method")
    void testWrongInputMessagePrintCorrectValue(){
        String excepted = "Wrong input try again\n";
        viewConsole.wrongInputMessage();
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @Test
    @DisplayName("Test getColumnMessage Method")
    void testGetColumnMessagePrintCorrectValue(){
        viewConsole.getColumnMessage();
        String excepted = "Enter column number (1,3)\n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @Test
    @DisplayName("Test getRowMessage Method")
    void testGetRowMessagePrintCorrectValue(){
        viewConsole.getRowMessage();
        String excepted = "Enter row number (1,3)\n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @ParameterizedTest
    @MethodSource(names = "createArgumentsContainsNamesToTest")
    @DisplayName("Test winMessage Method")
    void testWinMessagePrintCorrectValue(String name){
        viewConsole.winMessage(name);
        String excepted = "Player " + name + " WIN!!!\n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @ParameterizedTest
    @MethodSource(names = "createArgumentsContainsNamesToTest")
    @DisplayName("Test turnMessage Method")
    void testTurnMessagePrintCorrectValue(String name){
        viewConsole.turnMessage(name);
        String excepted = "Player " + name + " turn\n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @Test
    @DisplayName("Test startMessage Method")
    void testStartMessagePrintCorrectValue(){
        viewConsole.startMessage();
        String excepted = "Welcome to tic tac toe game\n" +
                "Rules:" +
                "1) If you try to cheat you lose move\n" +
                "2) GAME HAVE ONLY 9 ROUNDS\n";
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }

    @Test
    @DisplayName("Test errorMessage Method")
    void testErrorMessagePrintCorrectValue(){
        String excepted = "Error\n";
        String errorMessage = "Error";
        viewConsole.errorMessage(errorMessage);
        assertEquals(excepted, OUTPUT_STREAM.toString());
    }
    private static Stream<Arguments> createArgumentsContainsIndexToTest() {
        return Stream.of(
                ObjectArrayArguments.create(0),
                ObjectArrayArguments.create(1),
                ObjectArrayArguments.create(2)
        );
    }

    private static Stream<Arguments> createArgumentsContainsNamesToTest() {
        return Stream.of(
                ObjectArrayArguments.create("Kamil"),
                ObjectArrayArguments.create("Sebastian"),
                ObjectArrayArguments.create("Codecool")
        );
    }

    private List<List<Cell>> createListFiledWithCellsContainsEmptySeed(){
        List<List<Cell>> cells = new ArrayList<>();
        Integer rowRange = 3;
        Integer columnRange = 3;
        for (int row = 0; row < rowRange; row++) {
            List<Cell> rowCells = new ArrayList<>();
            for (int column = 0; column < columnRange; column++) {
                Cell cell = mock(Cell.class);
                when(cell.getSeed()).thenReturn(Seed.EMPTY);
                rowCells.add(cell);
            }
            cells.add(rowCells);
        }
        return cells;
    }

    private List<List<Cell>> createListFiledWithCellsContainsCrossSeed() {
        List<List<Cell>> cells = new ArrayList<>();
        Integer rowRange = 3;
        Integer columnRange = 3;
        for (int row = 0; row < rowRange; row++) {
            List<Cell> rowCells = new ArrayList<>();
            for (int column = 0; column < columnRange; column++) {
                Cell cell = mock(Cell.class);
                when(cell.getSeed()).thenReturn(Seed.CROSS);
                rowCells.add(cell);
            }
            cells.add(rowCells);
        }
        return cells;
    }

    private List<List<Cell>> createListFiledWithCellsContainsNoughtSeed() {
        List<List<Cell>> cells = new ArrayList<>();
        Integer rowRange = 3;
        Integer columnRange = 3;
        for (int row = 0; row < rowRange; row++) {
            List<Cell> rowCells = new ArrayList<>();
            for (int column = 0; column < columnRange; column++) {
                Cell cell = mock(Cell.class);
                when(cell.getSeed()).thenReturn(Seed.NOUGHT);
                rowCells.add(cell);
            }
            cells.add(rowCells);
        }
        return cells;
    }
}