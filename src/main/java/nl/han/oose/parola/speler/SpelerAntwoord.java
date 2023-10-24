package nl.han.oose.parola.speler;

import java.util.List;
import java.util.Objects;

public class SpelerAntwoord {
    private char letter;

    private String antwoord;

    private String vraag;

    public SpelerAntwoord(String antwoord, String vraagString) {
        this.antwoord = antwoord;
        this.vraag = vraagString;
        letter = antwoord.charAt(0);
        if (Character.isDigit(letter)) {
            letter = vraag.charAt(0);
        }
    }

    public Character getScoreLetter(List<String> antwoorden){
        if (isAntwoordCorrect(antwoorden)){
            return letter;
        }
        return null;
    }

    public String getVraag() {
        return vraag;
    }

    private boolean isAntwoordCorrect(List<String> antwoorden){
        for (String juistAntwoord : antwoorden) {
            if (Objects.equals(juistAntwoord, this.antwoord)) {
                return true;
            }
        }
        return false;
    }
}
