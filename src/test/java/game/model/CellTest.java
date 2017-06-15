package game.model;

import game.model.Cell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class CellTest {

    private static Stream<Arguments> createWrongConstructorParameters(){
        Integer correctIndex = 2;
        Integer wrongIndex = 4;
        return Stream.of(
                ObjectArrayArguments.create(wrongIndex,correctIndex),
                ObjectArrayArguments.create(correctIndex,wrongIndex),
                ObjectArrayArguments.create(wrongIndex,wrongIndex)
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
}
