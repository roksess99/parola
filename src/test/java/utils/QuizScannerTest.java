package utils;

import nl.han.oose.parola.utils.QuizScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuizScannerTest {

    QuizScanner sut;

    @Test
    void leesVragenReturnsValuesFromCSV() {
        sut = new QuizScanner();

        var vragen = sut.leesVragen();

        Assertions.assertNotNull(vragen);
    }
}
