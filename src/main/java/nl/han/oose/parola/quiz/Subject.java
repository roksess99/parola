package nl.han.oose.parola.quiz;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void sendUpdate();
}
