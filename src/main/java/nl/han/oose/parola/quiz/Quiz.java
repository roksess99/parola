package nl.han.oose.parola.quiz;

import nl.han.oose.parola.quiz.score.Score;
import nl.han.oose.parola.quiz.vraag.KortantwoordVraag;
import nl.han.oose.parola.quiz.vraag.MeerkeuzeVraag;
import nl.han.oose.parola.quiz.vraag.Vraag;
import nl.han.oose.parola.speler.Speler;
import nl.han.oose.parola.utils.GespeeldeQuizzenCSV;
import nl.han.oose.parola.utils.QuizCSV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Quiz implements Observer {
    private final int kosten = 40;
    private String quizNaam;
    private List<Score> scores = new ArrayList<>();
    private List<Vraag> quizVragen = new ArrayList<>();

    private List<SpelerSpel> spellen = new ArrayList<>();

    public Quiz (String quizNaam) {
        this.quizNaam = quizNaam;
        voegVragenUitCSVToe();
        voegScoresUitCSVToe();
    }

    @Override
    public void update() {
        //CRUD functionaliteit is niet geïmplementeerd
    }

    public void startSpeeltijd(String spelernaam) {
        spellen.add(new SpelerSpel(spelernaam));
    }

    public List<Character> verwerkAntwoorden(Speler speler){
        SpelerSpel spel = getSpelerSpel(speler.getGebruikersnaam());

        Score score = new Score(spel.getSpeeltijd(), speler.getGebruikersnaam(), quizNaam);
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
        return (getSpelerSpel(spelernaam).getVraagNr()) > quizVragen.size() - 1;
    }

    public void bewaarSpelerAntwoord(Speler speler, String antwoord) {
        SpelerSpel spel = getSpelerSpel(speler.getGebruikersnaam());
        int vraagNr = spel.getVraagNr();
        String vraagString = quizVragen.get(vraagNr-1).getVraag();
        speler.bewaarSpelerAntwoord(antwoord, vraagString);
    }

    public Integer getScore(String spelernaam, String woord) {
        Score spelerScore = getSpelerScore(spelernaam);
        return spelerScore.getScore(woord);
    }

    public String getQuiznaam() {
        return quizNaam;
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


    private void voegVragenUitCSVToe() {
        QuizCSV csv = new QuizCSV();
        List<String[]> csvLijst = csv.leesVragen();
        for (String[] csvVraag : csvLijst) {
            if (Objects.equals(csvVraag[0], quizNaam)) {
                Vraag vraag;
                if (Objects.equals(csvVraag[1], "kort_antwoord")) {
                    vraag = new KortantwoordVraag(csvVraag[7]);

                } else {
                    String[] opties = {csvVraag[3], csvVraag[4], csvVraag[5], csvVraag[6]};
                    vraag = new MeerkeuzeVraag(opties, csvVraag[7]);
                }
                vraag.setTekst(csvVraag[2]);
                quizVragen.add(vraag);
            }
        }
    }

    private void voegScoresUitCSVToe() {
        GespeeldeQuizzenCSV csv = new GespeeldeQuizzenCSV();
        List<String[]> csvLijst = csv.getGespeeldeQuizzenQuiznaam(quizNaam);
        for (String[] bestaandeScore : csvLijst) {
            String[] letters = bestaandeScore[6].split(",");

            Score score = new Score(
                    Integer.parseInt(bestaandeScore[2]),
                    bestaandeScore[3],
                    Integer.parseInt(bestaandeScore[4]),
                    Integer.parseInt(bestaandeScore[5]),
                    letters,
                    bestaandeScore[0],
                    bestaandeScore[1]
            );
            scores.add(score);
        }
    }
}
