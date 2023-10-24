package nl.han.oose.parola.quiz.vraag;

public class KortantwoordVraag extends Vraag {

    public KortantwoordVraag (String antwoord) {
        setJuisteAntwoorden(antwoord);
        super.setActief(true);
    }
}
