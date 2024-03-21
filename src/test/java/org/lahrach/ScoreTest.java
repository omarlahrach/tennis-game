package org.lahrach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ScoreTest {
    private Score score;

    @BeforeEach
    public void setUp() {
        score = new Score();
    }

    @Test
    public void incrementCurrentScore_shouldIncrementScoreByOne() {
        // Given
        int initialScore = score.getCurrentScore();

        // When
        score.incrementCurrentScore();

        // Then
        assertThat(score.getCurrentScore()).isEqualTo(initialScore + 1);
    }

    @Test
    public void decrementCurrentScore_shouldDecrementScoreByOne() {
        // Given
        score.setCurrentScore(5);
        int initialScore = score.getCurrentScore();

        // When
        score.decrementCurrentScore();

        // Then
        assertThat(score.getCurrentScore()).isEqualTo(initialScore - 1);
    }

    @Test
    public void incrementGamesWon_shouldIncrementGamesWonByOne() {
        // Given
        int initialGamesWon = score.getGamesWon();

        // When
        score.incrementGamesWon();

        // Then
        assertThat(score.getGamesWon()).isEqualTo(initialGamesWon + 1);
    }

    @Test
    public void decrementGamesWon_shouldDecrementGamesWonByOne() {
        // Given
        score.setGamesWon(3);
        int initialGamesWon = score.getGamesWon();

        // When
        score.decrementGamesWon();

        // Then
        assertThat(score.getGamesWon()).isEqualTo(initialGamesWon - 1);
    }
}
