package org.lahrach;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lahrach.pattern.Observer;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlayerTest {
    private Player player;
    
    @Mock private Score score;
    @Mock private Observer<Player> observer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        player = new Player("TestPlayer");
        player.setScore(score);
        player.setObserver(observer);
    }

    @Test
    public void shouldWinsPointAndNotifyObserver() {
        // Act
        player.winsPoint();

        // Assert
        verify(score, times(1)).incrementCurrentPoint();
        verify(observer, times(1)).update(player);
    }

    @Test
    public void shouldWinsGame() {
        // Act
        player.winsGame();

        // Assert
        verify(score, times(1)).incrementGamesWon();
    }
}
