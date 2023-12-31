package nl.han.oose.parola.controller;

import nl.han.oose.parola.creditwinkel.CreditWinkel;
import nl.han.oose.parola.quiz.Quiz;
import nl.han.oose.parola.speler.Speler;
import nl.han.oose.parola.utils.GespeeldeQuizzenCSV;
import nl.han.oose.parola.utils.QuizCSV;
import nl.han.oose.parola.utils.SpelerCSV;

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

    public Parola() {
        voegQuizzenUitCSVToe();
        voegSpelersUitCSVToe();
    }

    public void registeerSpeler(String gebruikersnaam, String wachtwoord) {
        Speler nieuweSpeler = new Speler(gebruikersnaam, wachtwoord, 1000);
        spelers.add(nieuweSpeler);
        slaSpelersOp();
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

    public Integer geefScorewoord(String spelernaam, String woord){
        return huidigeQuiz.getScore(spelernaam, woord);
    }

    public void beantwoordVraag(String spelernaam, String antwoord){
        Speler speler = getSpeler(spelernaam);
        if (antwoord == null || antwoord.isEmpty()) {
            antwoord = "";
        }
        huidigeQuiz.bewaarSpelerAntwoord(speler, antwoord);
    }

    public boolean startQuiz(String spelernaam){
        Speler speler = getSpeler(spelernaam);
        boolean genoegCredits = speler.checkCredits();
        if (genoegCredits) {
            int quizKosten = 40;
            speler.verlaagSaldo(quizKosten);
            huidigeQuiz = getOngespeeldeQuiz(spelernaam);
            huidigeQuiz.startSpeeltijd(spelernaam);
            slaSpelersOp();
            return true;
        }
        return false;
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
        slaSpelersOp();
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

    private Quiz getOngespeeldeQuiz(String spelernaam){
        GespeeldeQuizzenCSV csv = new GespeeldeQuizzenCSV();
        List<String[]> gespeeldeQuizzen = csv.getGespeeldeQuizzenSpelernaam(spelernaam);

        List<Quiz> beschikbareQuizzen = quizzen.stream()
                .filter(quiz -> !gespeeldeQuizzen.contains(quiz.getQuiznaam()))
                .toList();
        if (!beschikbareQuizzen.isEmpty()){
            return beschikbareQuizzen.get(new Random().nextInt(beschikbareQuizzen.size()));
        }else {
            return quizzen.get(new Random().nextInt(quizzen.size()));
        }
    }

    private void voegQuizzenUitCSVToe() {
        QuizCSV csv = new QuizCSV();
        List<String> quiznamen = csv.leesQuiznamen();
        for (String quiznaam : quiznamen) {
            Quiz quiz = new Quiz(quiznaam);
            quizzen.add(quiz);
        }
    }

    private void voegSpelersUitCSVToe() {
        SpelerCSV csv = new SpelerCSV();
        List<String[]> spelers = csv.leesSpelers();
        for (String[] speler : spelers) {
            Speler nieuweSpeler = new Speler(speler[0], speler[1], Integer.parseInt(speler[2]));
            this.spelers.add(nieuweSpeler);
        }
    }

    private void slaSpelersOp() {
        SpelerCSV csv = new SpelerCSV();
        List<String[]> spelersArray = new ArrayList<>();

        for (Speler speler : this.spelers) {
            String[] spelerArray = new String[3];
            spelerArray[0] = speler.getGebruikersnaam();
            spelerArray[1] = speler.getWachtwoord();
            spelerArray[2] = String.valueOf(speler.getSaldo());
            spelersArray.add(spelerArray);
        }

        csv.slaSpelersOp(spelersArray);
    }
}
