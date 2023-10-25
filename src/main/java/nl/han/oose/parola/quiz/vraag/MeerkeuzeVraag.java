package nl.han.oose.parola.quiz.vraag;

import java.util.Arrays;

public class MeerkeuzeVraag extends Vraag {
    private String[] opties;

    public MeerkeuzeVraag (String[] opties, String antwoord) {
        setOpties(opties);
        setJuisteAntwoorden(antwoord);
        super.setActief(true);
    }

    public void setOpties(String[] opties) {
        this.opties = opties;
        sendUpdate();
    }

    @Override
    public String getVraag() {
        return tekst + " \nAnswer options: " + Arrays.toString(opties);
    }
}
