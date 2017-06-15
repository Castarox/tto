package game.ui;

import game.ui.UserInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;


public class UserInputTest {

    @ParameterizedTest
    @MethodSource(names = "createIntegersToTestGetIntegerMethod")
    @DisplayName("Test getInteger method return excepted Value")
    void testGetIntegerReturnExceptedValue(Integer excepted){
        InputStream testStream = new java.io.ByteArrayInputStream(excepted.toString().getBytes());
        UserInput userInput = new UserInput(testStream);
        assertEquals(excepted, userInput.getInteger());
    }

    @Test
    @DisplayName("Test getInteger throws inputMismatchException if not integer was given")
    void testGetIntegerThrowsInputMismatchExceptionIfStringGiven(){
        String testingValue = "Test";
        InputStream testStream = new java.io.ByteArrayInputStream(testingValue.toString().getBytes());
        UserInput userInput = new UserInput(testStream);
        assertThrows(InputMismatchException.class, () -> {
            userInput.getInteger();
        });
    }

    @ParameterizedTest
    @MethodSource(names = "createNamesToTestGetNameMethod")
    @DisplayName("Test getName return excepted value")
    void testGetNameReturnExceptedValue(String excepted){
        InputStream testStream = new java.io.ByteArrayInputStream(excepted.getBytes());
        UserInput userInput = new UserInput(testStream);
        assertEquals(excepted, userInput.getName());

    }

    private static Stream<Arguments> createNamesToTestGetNameMethod() {
        String firstNameForTest = "Name";
        String secondNameForTest = "Codecool";
        String thirdNameForTest = "Summer";
        return Stream.of(
                ObjectArrayArguments.create(firstNameForTest),
                ObjectArrayArguments.create(secondNameForTest),
                ObjectArrayArguments.create(thirdNameForTest)
        );
    }

    private static Stream<Arguments> createIntegersToTestGetIntegerMethod() {
        Integer firstIntegerForTest = 13;
        Integer secondIntegerForTest = 245;
        Integer thirdIntegerForTest = 1567;
        return Stream.of(
                ObjectArrayArguments.create(firstIntegerForTest),
                ObjectArrayArguments.create(secondIntegerForTest),
                ObjectArrayArguments.create(thirdIntegerForTest)
        );
    }
}
