package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameControllerTest {
    GameController gameController;

    @BeforeEach
    void setup() {
       gameController = new GameController();
    }

    @Test
    void testGetAndSetViewConsoleWorkCorrectly() {
        ViewConsole viewConsole = mock(ViewConsole.class);
        gameController.setViewConsole(viewConsole);
        assertEquals(viewConsole, gameController.getViewConsole());
    }

    @Test
    void testCreatePlayerReturnCorrectPlayer() {

    }
}