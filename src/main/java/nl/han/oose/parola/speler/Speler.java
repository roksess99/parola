package nl.han.oose.parola.speler;

import nl.han.oose.parola.quiz.vraag.Vraag;

public class Speler {
    private String gebruikersnaam;
    private String wachtwoord;
    private int saldo;
    private char[] letter;
    private SpelerAntwoord spelerAntwoord;


    public void verlaagSaldo(int kosten){
        this.saldo -= kosten;
    }
    public void beantwoordSpelerAntwoord(String antwoord, Vraag vraag){

    }
//    public boolean checkCredits(){}
    public char getScoreLetter(Vraag vraag, String[] antwoorden){
       spelerAntwoord = getSpelerAntwoord(vraag);
       letter = spelerAntwoord.getScoreLetter(antwoorden);
    }
    private SpelerAntwoord getSpelerAntwoord(Vraag vraag){
        return spelerAntwoord;
    }
}
