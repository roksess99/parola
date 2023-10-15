package nl.han.oose.parola.quiz.score;

public interface ScoreStrategy {
    int berekenEindScore(int speelTijd, String scoreWoord,int aantalGoedeAntwoorden );
}
