package game.model;

import game.model.Player;
import game.enums.Seed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PlayerTest {

    @ParameterizedTest
    @MethodSource(names = "createWrongIndexForTest")
    @DisplayName("Test makeMove method throw IllegalArgument exception if arguments are less than zero")
    void testMakeMoveThrowIllegalArgumentIfArgumentsAreLT0(Integer row, Integer column) {
        Player player = new Player(null, null);

        assertThrows(IllegalArgumentException.class, () -> {
            player.makeMove(row, column);
        });
    }

    @ParameterizedTest
    @MethodSource(names = "createCorrectIndexForTest")
    @DisplayName("Test makeMove method don't throw IllegalArgument exception if arguments are correct")
    void testMakeMoveDoNotThrowIllegalArgumentIfArgumentAreCorrect(Integer row, Integer column) {
        Player player = new Player(null, null);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("row", row);
        expected.put("column", column);
        player.makeMove(row, column);
        assertEquals(expected, player.getMove());
    }

    @Test
    @DisplayName("Test getName return excepted name")
    void testGetNameReturnExceptedName() {
        Player player = new Player("Name", null);
        String excepted = "Name";
        assertEquals(excepted, player.getName());
    }

    @Test
    @DisplayName("Test getSeed return excepted seed")
    void testGetSeedReturnExceptedSeed(){
        Player player = new Player(null, Seed.CROSS);
        Seed excepted = Seed.CROSS;
        assertEquals(excepted, player.getSeed());
    }

    @Test
    @DisplayName("Test constructor initialize moves as new hash map")
    void testConstructorInitializeMovesAsNewHashMap(){
        Player player = new Player(null, null);
        Map<String, Integer> excepted = new HashMap<>();
        assertEquals(excepted, player.getMove());
    }

    private static Stream<Arguments> createWrongIndexForTest() {
        Integer wrongIndex = 4;
        Integer correctIndex = 0;
        return Stream.of(
                ObjectArrayArguments.create(wrongIndex, correctIndex),
                ObjectArrayArguments.create(correctIndex, wrongIndex),
                ObjectArrayArguments.create(wrongIndex, wrongIndex)
        );
    }

    private static Stream<Arguments> createCorrectIndexForTest() {
        Integer firstCorrectIndex = 0;
        Integer secondCorrectIndex = 1;
        Integer thirdCorrectIndex = 2;
        return Stream.of(
                ObjectArrayArguments.create(firstCorrectIndex, secondCorrectIndex),
                ObjectArrayArguments.create(firstCorrectIndex, thirdCorrectIndex),
                ObjectArrayArguments.create(secondCorrectIndex, secondCorrectIndex),
                ObjectArrayArguments.create(thirdCorrectIndex, thirdCorrectIndex)
        );
    }
}
