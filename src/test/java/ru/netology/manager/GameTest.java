package ru.netology.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.exeption.NotRegisteredException;
import java.util.ArrayList;
import java.util.Collection;

public class GameTest {

    Player player1 = new Player(1, "Player 1", 7);
    Player player2 = new Player(2, "Player 2", 8);
    Player player3 = new Player(3, "Player 3", 4);
    Player player4 = new Player(4, "Player 4", 7);


    @Test
    public void roundPlayer2Win() {
        Game game = new Game();
        Collection<Player> players = new ArrayList<>();

        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.registerAll(players);
        game.findAll();
        game.findByName("Player 4");
        game.round("Player 4", "Player 2");

        int actual = game.round("Player 4", "Player 2");
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void exceptionPlayer1() {
        Game game = new Game();

        game.findAll();
        game.findByName("Player 4");

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Player 4", "Player 2");
        });
    }

    @Test
    public void exceptionPlayer2() {
        Game game = new Game();

        game.register(player1);
        game.findAll();

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Player 1", "Player 2");
        });
    }

    @Test
    public void roundPlayer1Win() {
        Game game = new Game();
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.findAll();
        game.findByName("Player 1");

        int actual = game.round("Player 1", "Player 3");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void roundDraw() {
        Game game = new Game();
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.findAll();
        game.findByName("Player 1");

        int actual = game.round("Player 1", "Player 4");
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void searchStrengthFall() {
        Game game = new Game();
        game.register(player1);
        game.register(player2);
        game.register(player4);
        game.findAll();
        game.findByName("Player 1");

        int actual = game.findByStrength("Player 3");
        int expected = 0;
        assertEquals(expected, actual);
    }
}
