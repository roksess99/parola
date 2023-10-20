package nl.han.oose.parola.quiz.vraag;

import nl.han.oose.parola.quiz.Observer;
import nl.han.oose.parola.quiz.Subject;

import java.util.ArrayList;
import java.util.List;

public class Vraag implements Subject {
    private boolean actief;
    private String tekst;
    private String juisteAntwoorden;
    private String categorie;
    private List<Observer> observers = new ArrayList<>();

    public void setActief(boolean actief) {
        this.actief = actief;
        sendUpdate();
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
        sendUpdate();
    }

    public void setJuisteAntwoorden(String juisteAntwoorden) {
        this.juisteAntwoorden = juisteAntwoorden;
        sendUpdate();
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
        sendUpdate();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deAttach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void sendUpdate() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public String[] getJuisteAntwoorden(){
        return new String[]{juisteAntwoorden};
    }
}
