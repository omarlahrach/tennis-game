package org.lahrach;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    private final PrintStream originalOut = System.out;

    private Board board;

    @BeforeEach
    public void setUp() {
        System.setOut(printStream);
        board = new Board();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldDisplayPlayersWithThereScore() {
        // Arrange
        Player player1 = new Player("Sarah");
        Player player2 = new Player("Bernard");
        
        player1.setScore(new Score(30, 1));
        player2.setScore(new Score(15, 3));

        // Act
        board.display(player1, player2);

        // Assert
        String expectedOutput =
                "+----------+------------+----------------+\r\n" +
                "| Player   | Games Won  | Current Point  |\r\n" +
                "+----------+------------+----------------+\r\n" +
                "| Sarah    | 1          | 30             |\r\n" +
                "| Bernard  | 3          | 15             |\r\n" +
                "+----------+------------+----------------+";
        assertThat(outputStream.toString().trim()).isEqualTo(expectedOutput);
    }
}
