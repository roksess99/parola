package nl.han.oose.parola.speler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Speler {
    private String gebruikersnaam;
    private String wachtwoord;
    private int saldo;
    private List<SpelerAntwoord> quizAntwoorden = new ArrayList<>();

    public Speler (String spelernaam, String wachtwoord, int saldo) {
        this.gebruikersnaam = spelernaam;
        this.wachtwoord = wachtwoord;
        this.saldo = saldo;
    }

    public void verlaagSaldo(int kosten){
        this.saldo -= kosten;
        //sla op naar csv
    }

    public void verhoogSaldo(int nieuweCredits) {
        this.saldo += nieuweCredits;
        //sla op naar csv
    }

    public void bewaarSpelerAntwoord(String antwoord, String vraagString){
        SpelerAntwoord quizAntwoord = new SpelerAntwoord(antwoord, vraagString);
        quizAntwoorden.add(quizAntwoord);
    }

    public boolean checkCredits(){
        int quizKosten = 40;
        return saldo >= quizKosten;
    }
    public Character getScoreLetter(String vraagString, List<String> antwoorden){
       SpelerAntwoord spelerAntwoord = getSpelerAntwoord(vraagString);
       return spelerAntwoord.getScoreLetter(antwoorden);
    }
    private SpelerAntwoord getSpelerAntwoord(String vraagString){
        for (SpelerAntwoord antwoord : quizAntwoorden) {
            if (Objects.equals(antwoord.getVraag(), vraagString)) {
                return antwoord;
            }
        }
        return null;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setSaldo(int credits) {
        this.saldo = credits;
        //sla op naar csv
    }

    public int getSaldo() {
        return this.saldo;
    }
}
