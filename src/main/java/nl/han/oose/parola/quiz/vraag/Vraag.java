package nl.han.oose.parola.quiz.vraag;

import nl.han.oose.parola.quiz.Observer;
import nl.han.oose.parola.quiz.Subject;

public class Vraag implements Subject {
    private boolean actief;
    private String tekst;
    private String juisteAntwoorden;
    private String categorie;

    @Override
    public void attach(Observer observer) {

    }

    @Override
    public void deAttach(Observer observer) {

    }

    @Override
    public void sendUpdate() {

    }

//    public String[] getJuisteAntwoorden(){
//
//    }
}
