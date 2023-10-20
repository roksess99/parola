package nl.han.oose.parola.speler;

public class SpelerAntwoord {
    private char letter;

    public char getLetter() {
        return letter;
    }

    public char[] getScoreLetter(String[] antwoorden){
        if (isAntwoordenCorrect(antwoorden)){
            letter= getLetter();
        }
        return null;
    }
    public boolean isAntwoordenCorrect(String[] sAntwoorden){
        //logica
    }
}
