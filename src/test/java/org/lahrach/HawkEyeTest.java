package org.lahrach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class HawkEyeTest {
    private HawkEye hawkEye;

    @BeforeEach
    public void setUp() {
        hawkEye = new HawkEye();
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1, true",
            "0, 2, true",
            "0, 3, true",
            "1, 0, true",
            "1, 2, true",
            "1, 3, true",
            "2, 0, true",
            "2, 1, true",
            "2, 3, true",
            "3, 0, true",
            "3, 1, true",
            "3, 2, true",
    })
    public void shouldIndicatePointsDifferent(int player1Point, int player2Point, boolean expected) {
        // Arrange
        hawkEye.setScore1(new Score(player1Point, 0));
        hawkEye.setScore2(new Score(player2Point, 0));

        // Act & Assert
        assertThat(hawkEye.isPointsDifferent()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "1, 1, true",
            "2, 2, true",
            "3, 1, false",
            "3, 3, false",
            "5, 4, false",
    })
    public void shouldIndicatePointsTied(int player1Point, int player2Point, boolean expected) {
        // Arrange
        hawkEye.setScore1(new Score(player1Point, 0));
        hawkEye.setScore2(new Score(player2Point, 0));

        // Act & Assert
        assertThat(hawkEye.isPointsTied()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 3, true",
            "4, 4, true",
            "5, 5, true",
            "2, 3, false",
            "3, 2, false",
            "4, 2, false"
    })
    public void shouldIndicateDeuce(int player1Point, int player2Point, boolean expected) {
        // Arrange
        hawkEye.setScore1(new Score(player1Point, 0));
        hawkEye.setScore2(new Score(player2Point, 0));

        // Act & Assert
        assertThat(hawkEye.isDeuce()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "4, 3, true",
            "3, 4, true",
            "5, 4, true",
            "4, 5, true",
            "3, 2, false",
            "2, 3, false"
    })
    public void shouldIndicateAdvantage(int player1Point, int player2Point, boolean expected) {
        // Arrange
        hawkEye.setScore1(new Score(player1Point, 0));
        hawkEye.setScore2(new Score(player2Point, 0));

        // Act & Assert
        assertThat(hawkEye.hasAdvantage()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "4, 2, true",
            "6, 4, true",
            "4, 6, true",
            "7, 5, true",
            "5, 7, true",
            "4, 3, false",
            "3, 4, false",
            "2, 0, false",
            "3, 1, false"
    })
    public void shouldIndicateGameWinner(int player1Point, int player2Point, boolean expected) {
        // Arrange
        hawkEye.setScore1(new Score(player1Point, 0));
        hawkEye.setScore2(new Score(player2Point, 0));

        // Act & Assert
        assertThat(hawkEye.hasGameWinner()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 6, true",
            "6, 5, true",
            "4, 4, false",
            "6, 4, false"
    })
    public void shouldIndicateExtraGame(int player1GamesWon, int player2GamesWon, boolean expected) {
        // Arrange
        hawkEye.setScore1(new Score(0, player1GamesWon));
        hawkEye.setScore2(new Score(0, player2GamesWon));

        // Act & Assert
        assertThat(hawkEye.isInExtraGame()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "6, 6, true",
            "5, 5, false",
            "7, 6, false",
            "6, 7, false"
    })
    public void shouldIndicateTieBreak(int player1GamesWon, int player2GamesWon, boolean expected) {
        // Arrange
        hawkEye.setScore1(new Score(0, player1GamesWon));
        hawkEye.setScore2(new Score(0, player2GamesWon));

        // Act & Assert
        assertThat(hawkEye.isInTieBreak()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "7, 5, true",
            "5, 7, true",
            "7, 6, true",
            "6, 7, true",
            "6, 4, true",
            "4, 6, true",
            "5, 5, false",
            "6, 6, false"
    })
    public void shouldIndicateSetWinner(int player1GamesWon, int player2GamesWon, boolean expected) {
        // Arrange
        hawkEye.setScore1(new Score(0, player1GamesWon));
        hawkEye.setScore2(new Score(0, player2GamesWon));

        // Act & Assert
        assertThat(hawkEye.hasSetWinner()).isEqualTo(expected);
    }
}
