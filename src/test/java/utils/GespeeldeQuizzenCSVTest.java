package utils;

import nl.han.oose.parola.utils.GespeeldeQuizzenCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GespeeldeQuizzenCSVTest {

    GespeeldeQuizzenCSV sut;

    @Test
    void gespeeldeQuizzenSpelernaamGeeftScorelijst() {
        sut = new GespeeldeQuizzenCSV();

        List<String[]> results = sut.getGespeeldeQuizzenSpelernaam("test");

        Assertions.assertNotNull(results.get(0));
    }
}
