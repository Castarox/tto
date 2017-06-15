package game.controller;

import game.controller.GameController;
import game.enums.Seed;
import game.model.Player;
import game.ui.UserInput;
import game.ui.ViewConsole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void testCreateFirstPlayerReturnCorrectPlayer() {
        Player expected = new Player("first", Seed.CROSS);
        Player player = gameController.createFirstPlayer("first");
        assertEquals(expected, player);
    }

    @Test
    void testCreateSecondPlayerReturnCorrectPlayer() {
        Player expected = new Player("second", Seed.NOUGHT);
        Player player = gameController.createSecondPlayer("second");
        assertEquals(expected, player);
    }

    @Test
    void testGetPlayersReturnCorrectListOfPlayers() {
        List<Player> expected = new ArrayList<>();
        expected.add(new Player("sample", Seed.CROSS));
        expected.add(new Player("sample", Seed.NOUGHT));

        UserInput userInput = mock(UserInput.class);
        ViewConsole viewConsole = mock(ViewConsole.class);

        gameController.setViewConsole(viewConsole);
        gameController.setUserInput(userInput);

        when(userInput.getName()).thenReturn("sample");

        assertEquals(expected, gameController.getPlayers());
    }

    @Test
    void testGetPreparedBoardReturnNotNull() {
        assertNotNull(gameController.getPreparedBoard());
    }

    @Test
    void testGetPreparedUserInputReturnNotNull() {
        assertNotNull(gameController.getPreparedUserInput());
    }

    @Test
    void testGetPreparedViewConsoleReturnNotNull() {
        assertNotNull(gameController.getPreparedViewConsole());
    }

    @Test
    void testGetPreparedGameReturnNotNull() {
        List<Player> playerList = new ArrayList<>();

        Player firstPlayer = mock(Player.class);
        Player secondPlayer = mock(Player.class);

        playerList.add(firstPlayer);
        playerList.add(secondPlayer);

        gameController.setPlayerList(playerList);
        when(gameController.randomPlayer(playerList)).thenReturn(null);

        assertNotNull(gameController.getPreparedGame());
    }

    @Test
    void testGetUserMoveReturnCorrectMapOfMove() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("column", 0);
        expected.put("row", 0);

        ViewConsole viewConsole = mock(ViewConsole.class);
        UserInput userInput = mock(UserInput.class);

        when(userInput.getInteger()).thenReturn(1);

        gameController.setViewConsole(viewConsole);
        gameController.setUserInput(userInput);

        when(gameController.getColumnFromPlayer()).thenReturn(1);
        when(gameController.getRowFromPlayer()).thenReturn(1);

        assertEquals(expected, gameController.getUserMove());
    }

}