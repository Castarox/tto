package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameControllerTest {
    private GameController gameController;

    @BeforeEach
    void setup() {
        this.gameController = new GameController();
    }

    @Test
    void testGetAndSetViewConsoleWorkCorrectly() {
        ViewConsole viewConsole = mock(ViewConsole.class);
        this.gameController.setViewConsole(viewConsole);

        assertEquals(viewConsole, this.gameController.getViewConsole());
    }

    @Test
    void testCreateFirstPlayerReturnCorrectPlayer() {
        Player expected = new Player("first", Seed.CROSS);
        Player player = this.gameController.createFirstPlayer("first");

        assertEquals(expected, player);
    }

    @Test
    void testCreateSecondPlayerReturnCorrectPlayer() {
        Player expected = new Player("second", Seed.NOUGHT);
        Player player = this.gameController.createSecondPlayer("second");

        assertEquals(expected, player);
    }

    @Test
    void testGetPlayersReturnCorrectListOfPlayers() {
        List<Player> expected = new ArrayList<>();
        expected.add(new Player("sample", Seed.CROSS));
        expected.add(new Player("sample", Seed.NOUGHT));

        UserInput userInput = mock(UserInput.class);
        ViewConsole viewConsole = mock(ViewConsole.class);

        this.gameController.setViewConsole(viewConsole);
        this.gameController.setUserInput(userInput);

        when(userInput.getName()).thenReturn("sample");

        assertEquals(expected, this.gameController.getPlayers());
    }

    @Test
    void testGetPreparedBoardReturnNotNull() {
        assertNotNull(this.gameController.getPreparedBoard());
    }

    @Test
    void testGetPreparedUserInputReturnNotNull() {
        assertNotNull(this.gameController.getPreparedUserInput());
    }

    @Test
    void testGetPreparedViewConsoleReturnNotNull() {
        assertNotNull(this.gameController.getPreparedViewConsole());
    }

    @Test
    void testGetPreparedGameReturnNotNull() {
        List<Player> playerList = new ArrayList<>();

        Player firstPlayer = mock(Player.class);
        Player secondPlayer = mock(Player.class);

        playerList.add(firstPlayer);
        playerList.add(secondPlayer);

        this.gameController.setPlayerList(playerList);
        when(this.gameController.randomPlayer(playerList)).thenReturn(null);

        assertNotNull(this.gameController.getPreparedGame());
    }


    @Test
    void testGetUserMoveReturnCorrectMapOfMove() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("column", 0);
        expected.put("row", 0);

        ViewConsole viewConsole = mock(ViewConsole.class);
        UserInput userInput = mock(UserInput.class);

        when(userInput.getInteger()).thenReturn(1);

        this.gameController.setViewConsole(viewConsole);
        this.gameController.setUserInput(userInput);

        when(this.gameController.getColumnFromPlayer()).thenReturn(1);
        when(this.gameController.getRowFromPlayer()).thenReturn(1);

        assertEquals(expected, this.gameController.getUserMove());
    }

}