package nl.han.oose.parola.quiz.vraag;

public class KortantwoordVraag extends Vraag {
    private String juisteAntwoord;

    public void setJuisteAntwoord(String juisteAntwoorden) {
        this.juisteAntwoord = juisteAntwoorden;
        sendUpdate();
    }

    public String getJuisteAntwoord() {
        return juisteAntwoord;
    }
}
