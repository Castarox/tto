package game.model;

import game.enums.GameState;
import game.enums.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;

import static game.myAssertion.MyAssertion.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {
    private List<Player> playerList;
    private Player currentPlayer;
    private Board board;
    private Game game;

    @BeforeEach
    void setup(){
        currentPlayer = mock(Player.class);
        board = mock(Board.class);
        Player playerOne = mock(Player.class);
        Player playerTwo = mock(Player.class);
        playerList = new ArrayList<>();
        when(playerOne.getName()).thenReturn("player one");
        when(playerTwo.getName()).thenReturn("player two");
        playerList.add(playerOne);
        playerList.add(playerTwo);
        Integer moveCounterForTest = 9;
        game = new Game(
                currentPlayer,
                GameState.PLAYING,
                board,
                moveCounterForTest,
                playerList
        );
    }

    @Test
    @DisplayName("Test Increment moves work increment moves correctly")
    void testIncrementMovesWorkCorrectly(){
        Integer startMoveCounter = 0;
        Integer exceptedMoveCounter = 1;
        game.setMoveCounter(startMoveCounter);
        game.incrementMoves();
        assertEquals(exceptedMoveCounter, game.getMoveCounter());
    }

    @Nested
    @DisplayName("Tests updateBoard method for support IllegalArgumentException")
    class UpdateBoard{

        @Test
        @DisplayName("When cell is busy")
        void testUpdateBoardSupportsIllegalArgumentExceptionIfCellIsBusy(){
            List mockedList = mock(List.class);
            when(board.getCells()).thenReturn(mockedList);
            when(mockedList.get(anyInt())).thenReturn(Seed.NOUGHT);
            Map<String, Integer> moveForTest = new HashMap<>();
            Integer rowIndex = 10;
            Integer columnIndex = 150;
            moveForTest.put("row", rowIndex);
            moveForTest.put("column", columnIndex);
            when(currentPlayer.getMove()).thenReturn(moveForTest);
            assertDoesNotThrow(() -> game.updateBoard(game.getCurrentPlayer()));
        }

        @Test
        @DisplayName("If player move outside the board")
        void testUpdateBoardSupportsIllegalArgumentExceptionIfPlayerMoveIsWrong(){
            Map<String, Integer> moveForTest = new HashMap<>();
            Integer rowIndex = 10;
            Integer columnIndex = 150;
            moveForTest.put("row", rowIndex);
            moveForTest.put("column", columnIndex);
            when(currentPlayer.getMove()).thenReturn(moveForTest);
            assertDoesNotThrow(() -> {
                game.updateBoard(game.getCurrentPlayer());
            });
        }
    }

    @ParameterizedTest
    @MethodSource(names ="createCorrectPointsForFillMapWithPositionTest")
    @DisplayName("test fillMapWithPosition return map with correctly setted Points")
    void testFillMapWithPositionReturnMapWithCorrectPoints(Point point){
        Map<Point, String> mapToTest = game.fillMapWithPosition();
        assertNotNull(mapToTest.get(point));
    }

    @Nested
    @DisplayName("Testing end of game conditions")
    class endOfGameCondition{

        @Test
        void testIsDrawReturnFalseIfMoveCounterIsBelow9(){
            Integer moveCounterForTest = 8;
            game.setMoveCounter(moveCounterForTest);
            assertFalse(game.isDraw());
        }

        @Test
        void testIsDrawReturnTrueIfMoveCounterIsEqualToNine(){
            Integer moveCounterForTest = 9;
            game.setMoveCounter(moveCounterForTest);
            assertTrue(game.isDraw());
        }

        @Test
        void testIsWinPossibleReturnFalseIfGameHaveLTFiveMoves(){
            Integer maxNumberOfMovesWithOutWinPossible = 4;
            game.setMoveCounter(maxNumberOfMovesWithOutWinPossible);
            assertFalse(game.isWinPossible());
        }

        @Test
        void testIsWinPossibleReturnTrueIfGameHaveHTFourMoves(){
            Integer minimumNumberOfMovesToWin = 5;
            game.setMoveCounter(minimumNumberOfMovesToWin);
            assertTrue(game.isWinPossible());
        }

        @Test
        void testIsWinReturnTrueIfWinConditionIsComplete(){
            when(board.testWinForExtremeMiddleCells(any(),any())).thenReturn(true);
            Map<String, Integer> moveForTest = new HashMap<>();
            Integer rowIndex = 0;
            Integer columnIndex = 1;
            moveForTest.put("row", rowIndex);
            moveForTest.put("column", columnIndex);
            when(currentPlayer.getMove()).thenReturn(moveForTest);
            Integer numberOfMoves = 6;
            game.setMoveCounter(numberOfMoves);
            assertTrue(game.isWin());
        }

        @Test
        void testIsWinReturnFalseIfGameHaveLTFiveMoves(){
            Integer maxNumberOfMovesWithOutWinPossible = 4;
            game.setMoveCounter(maxNumberOfMovesWithOutWinPossible);
            assertFalse(game.isWin());
        }

    }

    @Nested
    @DisplayName("Test for switching player")
    class SwitchPlayer{

        private Player playerOne;
        private Player playerTwo;
        private List<Player> playerListForTest;
        @BeforeEach
        void setup(){
            playerListForTest = new ArrayList<>();
            playerOne = new Player("Player one", null);
            playerTwo = new Player("Player two", null);
            playerListForTest.add(playerOne);
            playerListForTest.add(playerTwo);
            game.setPlayerList(playerListForTest);
        }

        @Test
        @DisplayName("Test switching player from second to first Work Correctly")
        void testSwitchPlayerCorrectlySwitchPlayersFromSecondToFirst(){
            game.setCurrentPlayer(playerTwo);;
            game.switchPlayer();
            assertNotEquals(playerTwo, game.getCurrentPlayer());
        }

        @Test
        @DisplayName("Test switching player from first to second Work Correctly")
        void testSwitchPlayerCorrectlySwitchPlayersFromFirstToSecond(){
            game.setCurrentPlayer(playerOne);
            game.switchPlayer();
            assertNotEquals(playerOne, game.getCurrentPlayer());
        }

    }

    private static Stream<Arguments> createCorrectPointsForFillMapWithPositionTest() {
        Point firstPossiblePoint = new Point(0,0);
        Point secondPossiblePoint = new Point(0,1);
        Point thirdPossiblePoint = new Point(0,2);
        Point fourPossiblePoint = new Point(1,0);
        Point fivePossiblePoint = new Point(1,1);
        Point sixPossiblePoint = new Point(1,2);
        Point sevenPossiblePoint = new Point(2,0);
        Point eightPossiblePoint = new Point(2,1);
        Point ninePossiblePoint = new Point(2,2);

        return Stream.of(
                ObjectArrayArguments.create(firstPossiblePoint),
                ObjectArrayArguments.create(secondPossiblePoint),
                ObjectArrayArguments.create(thirdPossiblePoint),
                ObjectArrayArguments.create(fourPossiblePoint),
                ObjectArrayArguments.create(fivePossiblePoint),
                ObjectArrayArguments.create(sixPossiblePoint),
                ObjectArrayArguments.create(sevenPossiblePoint),
                ObjectArrayArguments.create(eightPossiblePoint),
                ObjectArrayArguments.create(ninePossiblePoint)

        );
    }
}