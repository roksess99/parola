package utils;

import nl.han.oose.parola.utils.QuizCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuizCSVTest {

    QuizCSV sut;

    @Test
    void leesVragenReturnsValuesFromCSV() {
        sut = new QuizCSV();

        var vragen = sut.leesVragen();

        Assertions.assertNotNull(vragen);
    }
}
