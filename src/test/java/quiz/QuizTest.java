package quiz;

import nl.han.oose.parola.quiz.Quiz;
import org.junit.jupiter.api.Test;

public class QuizTest {

    Quiz sut;

    @Test
    void quizMetNaamQuiz1MoetJuisteVragenBevattenNaAanmaken() {
        String quiznaam = "quiz1";
        sut = new Quiz(quiznaam);
    }
}
