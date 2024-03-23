package org.lahrach;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RefereeTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    private final PrintStream originalOut = System.out;

    private Referee referee;
    private Player player1;
    private Player player2;

    private @Mock Player player;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(printStream);

        referee = new Referee();
        player1 = new Player("player1");
        player2 = new Player("player2");
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "0, 2",
            "0, 3",
            "1, 0",
            "1, 2",
            "1, 3",
            "2, 0",
            "2, 1",
            "2, 3",
            "3, 0",
            "3, 1",
            "3, 2",
    })
    public void shouldIndicatePointsDifferent(int player1Point, int player2Point) {
        // Arrange
        player1.setScore(new Score(player1Point, 0));
        player2.setScore(new Score(player2Point, 0));

        // Act
        referee.announceStatement(player1, player2);

        // Assert
        assertThat(outputStream.toString().trim()).contains(",");
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 1",
            "2, 2",
    })
    public void shouldIndicatePointsTied(int player1Point, int player2Point) {
        // Arrange
        player1.setScore(new Score(player1Point, 0));
        player2.setScore(new Score(player2Point, 0));

        // Act
        referee.announceStatement(player1, player2);

        // Assert
        assertThat(outputStream.toString().trim()).contains("all");
    }

    @ParameterizedTest
    @CsvSource({
            "3, 3",
            "4, 4",
            "5, 5",
    })
    public void shouldIndicateDeuce(int player1Point, int player2Point) {
        // Arrange
        player1.setScore(new Score(player1Point, 0));
        player2.setScore(new Score(player2Point, 0));

        // Act
        referee.announceStatement(player1, player2);

        // Assert
        assertThat(outputStream.toString().trim()).isEqualTo("Deuce");
    }

    @ParameterizedTest
    @CsvSource({
            "4, 3",
            "3, 4",
            "5, 4",
            "4, 5",
    })
    public void shouldIndicateAdvantage(int player1Point, int player2Point) {
        // Arrange
        player1.setScore(new Score(player1Point, 0));
        player2.setScore(new Score(player2Point, 0));
        String expected = String.format("Advantage: %s",
                player1Point > player2Point ? player1.getName() : player2.getName());

        // Act
        referee.announceStatement(player1, player2);

        // Assert
        assertThat(outputStream.toString().trim()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "4, 2",
            "6, 4",
            "4, 6",
            "7, 5",
            "5, 7",
    })
    public void shouldIndicateGameWinner(int player1Point, int player2Point) {
        // Arrange
        player1.setScore(new Score(player1Point, 0));
        player2.setScore(new Score(player2Point, 0));
        String expected = String.format("Game: %s",
                player1Point > player2Point ? player1.getName() : player2.getName());

        // Act
        referee.announceStatement(player1, player2);

        // Assert
        assertThat(outputStream.toString().trim()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 6",
            "6, 5",
    })
    public void shouldIndicateExtraGame(int player1GamesWon, int player2GamesWon) {
        // Arrange
        player1.setScore(new Score(0, player1GamesWon));
        player2.setScore(new Score(0, player2GamesWon));

        // Act
        referee.announceStatement(player1, player2);

        // Assert
        assertThat(outputStream.toString().trim()).contains("Extra Game");
    }

    @ParameterizedTest
    @CsvSource({ "6, 6" })
    public void shouldIndicateTieBreak(int player1GamesWon, int player2GamesWon) {
        // Arrange
        player1.setScore(new Score(0, player1GamesWon));
        player2.setScore(new Score(0, player2GamesWon));

        // Act
        referee.announceStatement(player1, player2);

        // Assert
        assertThat(outputStream.toString().trim()).contains("Tie Break");
    }

    @ParameterizedTest
    @CsvSource({
            "7, 5",
            "5, 7",
            "7, 6",
            "6, 7",
            "6, 4",
            "4, 6",
    })
    public void shouldIndicateSetWinner(int player1GamesWon, int player2GamesWon) {
        // Arrange
        player1.setScore(new Score(0, player1GamesWon));
        player2.setScore(new Score(0, player2GamesWon));
        String expected = String.format("Game and Set: %s",
                player1GamesWon > player2GamesWon ? player1.getName() : player2.getName());

        // Act
        referee.announceStatement(player1, player2);

        // Assert
        assertThat(outputStream.toString().trim()).contains(expected);
    }
}
