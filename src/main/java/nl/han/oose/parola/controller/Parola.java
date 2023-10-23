package nl.han.oose.parola.controller;

import nl.han.oose.parola.creditwinkel.CreditWinkel;
import nl.han.oose.parola.quiz.Quiz;
import nl.han.oose.parola.speler.Speler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Parola {
    private List<Speler> spelers = new ArrayList<>();

    private List<Quiz> quizzen = new ArrayList<>();

    private Quiz huidigeQuiz;
    private final CreditWinkel creditWinkel = new CreditWinkel();

    public void registeerSpeler(String gebruikersnaam, String wachtwoord){
        Speler nieuweSpeler = new Speler(gebruikersnaam, wachtwoord);
        spelers.add(nieuweSpeler);

        //TODO sla nieuwe speler op in csv
    }


    public boolean login(String gebruikersnaam, String wachtwoord){
        for (Speler speler : spelers) {
            if (Objects.equals(speler.getGebruikersnaam(), gebruikersnaam)
                    && Objects.equals(speler.getWachtwoord(), wachtwoord)) {
                return true;
            }
        }
        return false;
    }

    public int geefScorewoord(String spelernaam, String woord){
        return huidigeQuiz.getScore(spelernaam, woord);
    }

    public void beantwoordVraag(String spelernaam, String antwoord){
        Speler speler = getSpeler(spelernaam);
        huidigeQuiz.bewaarSpelerAntwoord(speler, antwoord);
    }

    private Quiz getOngespeeldeQuiz(String spelernaam){
        Speler speler = getSpeler(spelernaam);
        List<String> gespeeldeQuizzen = speler.getGespeeldeQuizzen();
        List<Quiz> beschikbareQuizzen = quizzen.stream()
                .filter(quiz -> !gespeeldeQuizzen.contains(quiz.getQuiznaam()))
                .collect(Collectors.toList());
        if (!beschikbareQuizzen.isEmpty()){
            return beschikbareQuizzen.get(new Random().nextInt(beschikbareQuizzen.size()));
        }else {
            return quizzen.get(new Random().nextInt(quizzen.size()));
        }
    }

    public void startQuiz(String spelernaam){
        Speler speler = getSpeler(spelernaam);
        boolean genoegCredits = speler.checkCredits();
        if (genoegCredits) {
            int quizKosten = 40;
            speler.verlaagSaldo(quizKosten);
            huidigeQuiz = getOngespeeldeQuiz(spelernaam);
            huidigeQuiz.startSpeeltijd(spelernaam);
        }

    }

    public List<Character> verwerkAntwoorden(String spelernaam){
        Speler speler = getSpeler(spelernaam);
        return huidigeQuiz.verwerkAntwoorden(speler);
    }

    public String openCreditwinkel(){
        var tarieven = creditWinkel.getTarieven();
        return tarieven.keySet()
                .stream()
                .map(key -> "Aantal credits: " + key + " Prijs: " + tarieven.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }

    public Double koopCredits(int hoeveelheid){
        return creditWinkel.koopCredits(hoeveelheid);
    }

    public void betaal(Double bedrag, String spelernaam){
        //Externe betaalservice is niet geïmplementeerd

        int nieuweCredits = creditWinkel.getHoeveelheid(bedrag);
        Speler speler = getSpeler(spelernaam);
        speler.verhoogSaldo(nieuweCredits);
    }

    public Speler getSpeler(String spelernaam) {
        for (Speler speler : spelers) {
            if (Objects.equals(speler.getGebruikersnaam(), spelernaam)) {
                return speler;
            }
        }
        return null;
    }

    public String getVraag(String spelernaam) {
        return huidigeQuiz.getVraag(spelernaam);
    }

    public boolean isQuizAfgelopen(String spelernaam) {
        return huidigeQuiz.isQuizAfgelopen(spelernaam);
    }
}
