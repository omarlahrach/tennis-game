package org.lahrach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;
    private Score scoreMock;

    @BeforeEach
    public void setUp() {
        scoreMock = mock(Score.class);
        player = new Player("TestPlayer", scoreMock);
    }

    @Test
    public void winsPoint_shouldIncrementCurrentScore() {
        // When
        player.winsPoint();

        // Then
        verify(scoreMock, times(1)).incrementCurrentScore();
    }

    @Test
    public void losesPoint_shouldDecrementCurrentScore() {
        // When
        player.losesPoint();

        // Then
        verify(scoreMock, times(1)).decrementCurrentScore();
    }

    @Test
    public void winsGame_shouldIncrementGamesWon() {
        // When
        player.winsGame();

        // Then
        verify(scoreMock, times(1)).incrementGamesWon();
    }

    @Test
    public void losesGame_shouldDecrementGamesWon() {
        // When
        player.losesGame();

        // Then
        verify(scoreMock, times(1)).decrementGamesWon();
    }
}
