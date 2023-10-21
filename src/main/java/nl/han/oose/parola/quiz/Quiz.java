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

    private List<SpelerSpel> spellen = new ArrayList<>();

    @Override
    public void update() {
        //CRUD functionaliteit is niet geïmplementeerd
    }

    public void startSpeeltijd(String spelernaam) {
        spellen.add(new SpelerSpel(spelernaam));
    }

    public List<Character> verwerkAntwoorden(Speler speler){
        SpelerSpel spel = getSpelerSpel(speler.getGebruikersnaam());

        Score score = new Score(spel.getSpeeltijd(), speler.getGebruikersnaam());
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

    public String getVraag(String spelernaam) {
        SpelerSpel spel = getSpelerSpel(spelernaam);
        Vraag vraag = quizVragen.get(spel.getVraagNr());
        spel.verhoogVraagNr();
        return vraag.getVraag();
    }

    public boolean isQuizAfgelopen(String spelernaam) {
        return (getSpelerSpel(spelernaam).getVraagNr()) > quizVragen.size();
    }

    public void bewaarSpelerAntwoord(Speler speler, String antwoord) {
        SpelerSpel spel = getSpelerSpel(speler.getGebruikersnaam());
        int vraagNr = spel.getVraagNr();
        String vraagString = quizVragen.get(vraagNr).getVraag();
        speler.bewaarSpelerAntwoord(antwoord, vraagString);
    }

    public int getScore(String spelernaam, String woord) {
        Score spelerScore = getSpelerScore(spelernaam);
        return spelerScore.getScore(woord);
    }

    private SpelerSpel getSpelerSpel(String spelernaam) {
        for (SpelerSpel spel : spellen) {
            if (Objects.equals(spel.getSpelernaam(), spelernaam)) {
                return spel;
            }
        }
        return null;
    }

    private Score getSpelerScore(String spelernaam) {
        for (Score score : scores) {
            if (Objects.equals(score.getSpeler(), spelernaam)) {
                return score;
            }
        }
        return null;
    }
}
