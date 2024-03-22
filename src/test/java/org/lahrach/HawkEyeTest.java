package org.lahrach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.lahrach.pattern.Observer;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HawkEyeTest {
    private HawkEye hawkEye;

    @Mock
    private Observer observer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        hawkEye = new HawkEye();
        hawkEye.setObserver(observer);
    }

    @Test
    public void shouldNotifyRefereeWhenUpdated() {
        // Act
        hawkEye.update(new Player());

        // Assert
        verify(observer, times(1)).update(hawkEye);
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
        // Act & Assert
        assertThat(hawkEye.isPointsTied(player1Point, player2Point)).isEqualTo(expected);
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
        // Act & Assert
        assertThat(hawkEye.isDeuce(player1Point, player2Point)).isEqualTo(expected);
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
        // Act & Assert
        assertThat(hawkEye.hasAdvantage(player1Point, player2Point)).isEqualTo(expected);
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
        // Act & Assert
        assertThat(hawkEye.hasGameWinner(player1Point, player2Point)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 6, true",
            "6, 5, true",
            "4, 4, false",
            "6, 4, false"
    })
    public void shouldIndicateExtraGame(int player1GamesWon, int player2GamesWon, boolean expected) {
        // Act & Assert
        assertThat(hawkEye.isInExtraGame(player1GamesWon, player2GamesWon)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "6, 6, true",
            "5, 5, false",
            "7, 6, false",
            "6, 7, false"
    })
    public void shouldIndicateTieBreak(int player1GamesWon, int player2GamesWon, boolean expected) {
        // Act & Assert
        assertThat(hawkEye.isInTieBreak(player1GamesWon, player2GamesWon)).isEqualTo(expected);
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
        // Act & Assert
        assertThat(hawkEye.hasSetWinner(player1GamesWon, player2GamesWon)).isEqualTo(expected);
    }
}
