package nl.han.oose.parola.quiz.vraag;

public class MeerkeuzeVraag extends Vraag {
    private String[] opties;

    public void setOpties(String[] opties) {
        this.opties = opties;
        sendUpdate();
    }

    public String[] getOpties() {
        return opties;
    }
}
