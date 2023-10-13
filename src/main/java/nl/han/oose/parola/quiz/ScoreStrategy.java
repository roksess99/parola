package nl.han.oose.parola.quiz;

public interface ScoreStrategy {
    int berekenEindScore(int spelTijd, String scoreWoord,int aantalGoedeAntwoorden );
}
