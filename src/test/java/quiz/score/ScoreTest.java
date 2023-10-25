package quiz.score;

import nl.han.oose.parola.quiz.score.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ScoreTest {

    Score sut;

    @Test
    void woordMetVerkeerdeLettersGeeftNull() {
        sut = new Score(0, "test", "testQuiz");

        sut.addScoreLetter('v');
        sut.addScoreLetter('i');
        sut.addScoreLetter('s');

        Assertions.assertNull(sut.getScore("kat"));
    }

    @Test
    void woordMetGoedeLettersGeeftScore() {
        sut = new Score(0, "test", "testQuiz");

        sut.addScoreLetter('v');
        sut.addScoreLetter('i');
        sut.addScoreLetter('s');

        Assertions.assertNotNull(sut.getScore("vis"));
    }

    @Test
    void kleinerWoordMetGoedeLettersGeeftScore() {
        sut = new Score(0, "test", "testQuiz");

        sut.addScoreLetter('v');
        sut.addScoreLetter('i');
        sut.addScoreLetter('s');
        sut.addScoreLetter('q');
        sut.addScoreLetter('g');
        sut.addScoreLetter('p');

        Assertions.assertNotNull(sut.getScore("pis"));
    }

    @Test
    void woordMetDubbeleScoreLettersGeeftScore() {
        sut = new Score(0, "test", "testQuiz");

        sut.addScoreLetter('l');
        sut.addScoreLetter('p');
        sut.addScoreLetter('p');
        sut.addScoreLetter('a');
        sut.addScoreLetter('g');
        sut.addScoreLetter('a');

        Assertions.assertNotNull(sut.getScore("paal"));
    }

    @Test
    void teLangWoordGeeftNull() {
        sut = new Score(0, "test", "testQuiz");

        sut.addScoreLetter('v');
        sut.addScoreLetter('i');
        sut.addScoreLetter('s');

        Assertions.assertNull(sut.getScore("visvis"));
    }

    @Test
    void onbestaandWoordInWoordenlijstGeeftNull() {
        sut = new Score(0, "test", "testQuiz");

        sut.addScoreLetter('q');
        sut.addScoreLetter('e');
        sut.addScoreLetter('g');
        sut.addScoreLetter('t');
        sut.addScoreLetter('h');

        Assertions.assertNull(sut.getScore("thqe"));
    }

    @Test
    void getScoreLettersOpScoreLettersZonderKlinkerGeeftExtraKlinkerTerug() {
        sut = new Score(0, "test", "testQuiz");
        List<Character> scoreletters = new ArrayList<>();

        scoreletters.add('q');
        scoreletters.add('h');
        scoreletters.add('g');
        scoreletters.add('t');
        scoreletters.add('h');

        for (char letter : scoreletters) {
            sut.addScoreLetter(letter);
        }

        Assertions.assertNotSame(scoreletters, sut.getScoreLetters());
    }

    @Test
    void scoreLettersMetLowercaseGeeftGoedeScore() {
        sut = new Score(0, "test", "testQuiz");
        List<Character> scoreletters = new ArrayList<>();

        scoreletters.add('i');
        scoreletters.add('J');
        scoreletters.add('W');
        scoreletters.add('T');
        scoreletters.add('L');
        scoreletters.add('R');

        for (char letter : scoreletters) {
            sut.addScoreLetter(letter);
        }

        int score = sut.getScore("wit");
        Assertions.assertNotNull(score);
    }

}
