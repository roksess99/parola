package nl.han.oose.parola.quiz;

import nl.han.oose.parola.quiz.score.Score;
import nl.han.oose.parola.quiz.vraag.Vraag;
import nl.han.oose.parola.speler.Speler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quiz implements Observer {
    private int kosten = 40;
    private List<Score> scores = new ArrayList<>();
    private List<Vraag> quizVragen = new ArrayList<>();

    private List<Speeltijd> speeltijden = new ArrayList<>();

    @Override
    public void update() {
        //CRUD functionaliteit is niet geïmplementeerd
    }

    public void startSpeeltijd(String spelernaam) {
        speeltijden.add(new Speeltijd(spelernaam));
    }

    public List<Character> verwerkAntwoorden(Speler speler){
        int speeltijd = getSpeeltijd(speler.getGebruikersnaam());
        Score score = new Score(speeltijd, speler.getGebruikersnaam());
        scores.add(score);

        for (Vraag vraag : quizVragen) {
            List<String> antwoorden = vraag.getJuisteAntwoorden();
            Character letter = speler.getScoreLetter(vraag.getVraag(), antwoorden);

            if (letter != null){
                score.addScoreLetter(letter);
            }
        }

        return score.getScoreLetters();
    }

    private Integer getSpeeltijd(String spelernaam) {
        for (Speeltijd speeltijd : speeltijden) {
            if (Objects.equals(speeltijd.getSpelernaam(), spelernaam)) {
                speeltijd.getSpeeltijd();
            }
        }
        return null;
    }
}
