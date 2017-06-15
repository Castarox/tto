import game.Player;
import game.Seed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class PlayerTest {

    private static Stream<Arguments> createWrongIndexForTest() {
        return Stream.of(
                ObjectArrayArguments.create(-1,2),
                ObjectArrayArguments.create(2,-1),
                ObjectArrayArguments.create(-1,-1)
        );
    }

    private static Stream<Arguments> createCorrectIndexForTest() {
        return Stream.of(
                ObjectArrayArguments.create(0,2),
                ObjectArrayArguments.create(2,2),
                ObjectArrayArguments.create(1,2)

        );
    }

    @ParameterizedTest
    @MethodSource(names = "createWrongIndexForTest")
    void testMakeMoveThrowIllegalArgumentIfArgumentsAreLT0(Integer row, Integer column) {
        Player player = new Player(null, null);

        assertThrows(IllegalArgumentException.class, () -> {
            player.makeMove(row, column);
        });
    }

    @ParameterizedTest
    @MethodSource(names = "createCorrectIndexForTest")
    void testMakeMoveDoNotThrowilleagalArgumentIfArgumentAreCorrect(Integer row, Integer column) {
        Player player = new Player(null, null);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("row", row);
        expected.put("column", column);
        player.makeMove(row, column);
        assertEquals(expected, player.getMove());
    }

    @Test
    void testGetNameReturnExeptedName() {
        Player player = new Player("Name", null);
        String excepted = "Name";
        assertEquals(excepted, player.getName());
    }

    @Test
    void testGetSeedReturnExceptedSeed(){
        Player player = new Player(null, Seed.CROSS);
        Seed excepted = Seed.CROSS;
        assertEquals(excepted, player.getSeed());
    }

    @Test
    void testConstructorInitializeMovesAsNewHashMap(){
        Player player = new Player(null, null);
        Map<String, Integer> excepted = new HashMap<>();
        assertEquals(excepted, player.getMove());
    }

}
