import game.UserInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class UserInputTest {

    @ParameterizedTest
    @MethodSource(names = "createIntegersToTestGetIntegerMethod")
    void testGetIntegerReturnExceptedValue(Integer excepted){
        InputStream testStream = new java.io.ByteArrayInputStream(excepted.toString().getBytes());
        UserInput userInput = new UserInput(testStream);
        assertEquals(excepted, userInput.getInteger());
    }

    @Test
    void testGetIntegerThrowsInputMismatchExceptionIfStringGiven(){
        String excepted = "Test";
        InputStream testStream = new java.io.ByteArrayInputStream(excepted.toString().getBytes());
        UserInput userInput = new UserInput(testStream);
        assertThrows(InputMismatchException.class, () -> {
            userInput.getInteger();
        });
    }

    @ParameterizedTest
    @MethodSource(names = "createNamesToTestGetNameMethod")
    void testGetNameReturnExceptedValue(String excepted){
        InputStream testStream = new java.io.ByteArrayInputStream(excepted.getBytes());
        UserInput userInput = new UserInput(testStream);
        assertEquals(excepted, userInput.getName());

    }

    private static Stream<Arguments> createNamesToTestGetNameMethod() {
        return Stream.of(
                ObjectArrayArguments.create("Name"),
                ObjectArrayArguments.create("Codecool"),
                ObjectArrayArguments.create("Summer")
        );
    }

    private static Stream<Arguments> createIntegersToTestGetIntegerMethod() {
        return Stream.of(
                ObjectArrayArguments.create(13),
                ObjectArrayArguments.create(256),
                ObjectArrayArguments.create(13)
        );
    }
}
