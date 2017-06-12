import game.Cell;
import game.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class CellTest {

//    @ParameterizedTest
//    @MethodSource(names = "createWordsWithLength")
//    void withMethodSource(String word, int length) { }
//
//    private static Stream<Arguments> createWordsWithLength() {
//        return Stream.of(
//                ObjectArrayArguments.create("Hello", 5),
//                ObjectArrayArguments.create("JUnit 5", 7));
//    }

    private static Stream<Arguments> createWrongConstructorParameters(){
        return Stream.of(
                ObjectArrayArguments.create(-1,2),
                ObjectArrayArguments.create(2,-1),
                ObjectArrayArguments.create(-1,-1)
        );
    }

    @ParameterizedTest
    @MethodSource(names="createWrongConstructorParameters")
    @DisplayName("Constructor throw Exepction when given parameters are below zero and above two")
    void testConstructorThrowsIllegalArgumentExpectionIfGivenParametersIsWrong(Integer row, Integer column){
        assertThrows(IllegalArgumentException.class, () -> {
            new Cell(row, column);
        });
    }

    @Test
    void testClearSetContentParameterToEmptyString(){
        Cell cell = new Cell(1,1);
        cell.setSeed(Seed.CROSS);
        cell.clear();
        assertEquals(Seed.EMPTY ,cell.getSeed());
    }

}
