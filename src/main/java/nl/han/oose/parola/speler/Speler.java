package nl.han.oose.parola.speler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Speler {
    private String gebruikersnaam;
    private String wachtwoord;
    private int saldo;
    private List<SpelerAntwoord> quizAntwoorden = new ArrayList<>();

    public Speler (String spelernaam, String wachtwoord) {
        this.gebruikersnaam = spelernaam;
        this.wachtwoord = wachtwoord;
    }

    public void verlaagSaldo(int kosten){
        this.saldo -= kosten;
    }

    public void verhoogSaldo(int nieuweCredits) {
        this.saldo += nieuweCredits;
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

    public List<String> getGespeeldeQuizzen(){
        List<String> gespeeldeQuizzen = new ArrayList<>();
        final String gespeeldeQuizzenCSV = "src/main/resources/quiz/gespeelde_quizzen.csv";
        try(BufferedReader reader = new BufferedReader(new FileReader(gespeeldeQuizzenCSV))) {
            String row;
            while ((row = reader.readLine()) != null){
                String[] result = row.split(",");
                if (result.length >= 2 && result[0].equals(gebruikersnaam)){
                    gespeeldeQuizzen.add(result[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gespeeldeQuizzen;
    }
}
