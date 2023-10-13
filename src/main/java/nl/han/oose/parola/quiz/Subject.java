package nl.han.oose.parola.quiz;

public interface Subject {
    void attach(Observer observer);
    void deAttach(Observer observer);
    void sendUpdate();
}
