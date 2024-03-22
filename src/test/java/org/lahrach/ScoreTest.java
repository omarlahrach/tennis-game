package org.lahrach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    private Score score;

    @BeforeEach
    public void setUp() {
        score = new Score(30, 1);
    }

    @Test
    public void shouldIncrementScoreByOne() {
        // Arrange
        int initialScore = score.getCurrentPoint();

        // Act
        score.incrementCurrentPoint();

        // Assert
        assertThat(score.getCurrentPoint()).isEqualTo(initialScore + 1);
    }

    @Test
    public void shouldDecrementScoreByOne() {
        // Arrange
        score.setCurrentPoint(5);
        int initialScore = score.getCurrentPoint();

        // Act
        score.decrementCurrentPoint();

        // Assert
        assertThat(score.getCurrentPoint()).isEqualTo(initialScore - 1);
    }

    @Test
    public void shouldIncrementGamesWonByOne() {
        // Arrange
        int initialGamesWon = score.getGamesWon();

        // Act
        score.incrementGamesWon();

        // Assert
        assertThat(score.getGamesWon()).isEqualTo(initialGamesWon + 1);
    }

    @Test
    public void shouldDecrementGamesWonByOne() {
        // Arrange
        score.setGamesWon(3);
        int initialGamesWon = score.getGamesWon();

        // Act
        score.decrementGamesWon();

        // Assert
        assertThat(score.getGamesWon()).isEqualTo(initialGamesWon - 1);
    }

    @Test
    public void shouldResetPoints() {
        // Arrange
        score.setCurrentPoint(3);

        // Act
        score.resetPoints();

        // Assert
        assertThat(score.getCurrentPoint()).isEqualTo(0);
    }
}
