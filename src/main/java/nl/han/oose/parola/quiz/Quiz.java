package nl.han.oose.parola.quiz;

import nl.han.oose.parola.quiz.score.Score;
import nl.han.oose.parola.quiz.vraag.Vraag;
import nl.han.oose.parola.speler.Speler;
import nl.han.oose.parola.speler.SpelerAntwoord;

import java.security.PrivateKey;
import java.sql.PreparedStatement;
import java.util.List;

public class Quiz implements Observer {
    private int kosten;
    private Score score;
    private Vraag vraag;
    private Speler speler;
    private char letter;
    private List<Character> scoreLetters;
    private char[] letters;
    private String[] antwoorden;
    private char nullChar = '\0';

    public void setKosten(int kosten) {
        this.kosten = kosten;
        update();
    }
    public void setScoreLetters(char[] scoreLetters) {
        this.letters = scoreLetters;
    }

    @Override
    public void update() {
        char[] verwerkteAntwoorden = verwerkAntwoorden();
    }

    public char[] verwerkAntwoorden(){
       score = new Score(11,"fgh");
        for (int i = 0; i < 8; i++) {
            antwoorden = vraag.getJuisteAntwoorden();

            letter = speler.getScoreLetter(vraag, antwoorden);
            if (letter!=nullChar){
                score.addScoreLetter(letter);
            }
        }
        scoreLetters = score.getScoreLetters();

    }
}
