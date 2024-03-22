package org.lahrach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RefereeTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    private final PrintStream originalOut = System.out;

    private Referee referee;
    private HawkEye hawkEye;
    private Player sarah;
    private Player bernard;

    private @Mock Player player;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(printStream);

        referee = new Referee();
        hawkEye = new HawkEye();
        sarah = new Player("Sarah");
        bernard = new Player("Bernard");

        referee.setSubject(hawkEye);

        hawkEye.setObserver(referee);
        hawkEye.setSubject1(sarah);
        hawkEye.setSubject2(bernard);

        sarah.setObserver(hawkEye);
        bernard.setObserver(hawkEye);    
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1, 'love, fifteen'",
            "0, 2, 'love, thirty'",
            "0, 3, 'love, forty'",
            "1, 0, 'fifteen, love'",
            "1, 2, 'fifteen, thirty'",
            "1, 3, 'fifteen, forty'",
            "2, 0, 'thirty, love'",
            "2, 1, 'thirty, fifteen'",
            "2, 3, 'thirty, forty'",
            "3, 0, 'forty, love'",
            "3, 1, 'forty, fifteen'",
            "3, 2, 'forty, thirty'",
    })
    public void shouldAnnoucePointsDifferent(int sarahPoint, int bernardPoint, String expected) {
        // Arrange
        sarah.setScore(new Score(sarahPoint, 0));
        bernard.setScore(new Score(bernardPoint, 0));

        // Act
        referee.update(hawkEye);

        // Assert
        assertThat(outputStream.toString().trim()).isEqualTo(expected);
    }

    @Test
    public void shouldAnnoucePointsTied() {
        // Act
        hawkEye.setCurrentState(HawkEye.State.POINTS_TIED);
        referee.update(hawkEye);

        // Assert
        assertThat(outputStream.toString().trim()).contains("all");
    }

    @Test
    public void shouldAnnounceDeuce() {
        // Act
        hawkEye.setCurrentState(HawkEye.State.DEUCE);
        referee.update(hawkEye);

        // Assert
        assertThat(outputStream.toString().trim()).isEqualTo("Deuce");
    }

    @Test
    public void shouldAnnounceAdvantage() {
        // Act
        hawkEye.setCurrentState(HawkEye.State.ADVANTAGE);
        referee.update(hawkEye);

        // Assert
        assertThat(outputStream.toString().trim()).contains("Advantage:");
    }

    @Test
    public void shouldAnnounceGameWin() {
        // Act
        hawkEye.setCurrentState(HawkEye.State.GAME_WINNER);
        referee.update(hawkEye);

        // Assert
        assertThat(outputStream.toString().trim()).contains("Game:");
        verify(player, times(1)).winsGame();
    }

    @Test
    public void shouldAnnounceExtraGame() {
        // Act
        hawkEye.setCurrentState(HawkEye.State.EXTRA_GAME);
        referee.update(hawkEye);

        // Assert
        assertThat(outputStream.toString().trim()).isEqualTo("Extra Game");
    }

    @Test
    public void shouldAnnounceTieBreak() {
        // Act
        hawkEye.setCurrentState(HawkEye.State.TIE_BREAK);
        referee.update(hawkEye);

        // Assert
        assertThat(outputStream.toString().trim()).isEqualTo("Tie Break");
    }

    @Test
    public void shouldAnnounceSetWin() {
        // Act
        hawkEye.setCurrentState(HawkEye.State.SET_WINNER);
        referee.update(hawkEye);

        // Assert
        assertThat(outputStream.toString().trim()).contains("Game and Set:");
    }
}
