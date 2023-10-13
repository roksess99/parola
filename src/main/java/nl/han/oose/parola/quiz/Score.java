package nl.han.oose.parola.quiz;

import nl.han.oose.parola.speler.Speler;

public class Score {
    private int aantalGoedeAntwoorden;
    private String scoreWoord;
    private double speelTijd;
    private int score;
    private ScoreStrategy strategy;

    public void setStrategy(ScoreStrategy strategy) {
        this.strategy = strategy;
    }

    //    private char voegKlinkerToe(){
//
//    }
//    private boolean bevatScoreWoordJuisteLetters(String woord){}
//    public void addScoreLetter(char letter){}
//    public int getScore(String woord){}
//    public boolean bestaatWoord(String woord){}
//    public char getScoreLetters(){}
//    private boolean bevattenLettersEenKlinker(char[] scoreLetters){}
//    public Score(double speelTijd, Speler speler){
//        this.speelTijd = speelTijd;
//        this.speler = speler;
//    }
}
