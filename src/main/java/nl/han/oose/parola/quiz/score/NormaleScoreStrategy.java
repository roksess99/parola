package nl.han.oose.parola.quiz.score;

public class NormaleScoreStrategy implements ScoreStrategy {
    @Override
    public int berekenEindScore(int speelTijd, String scoreWoord, int aantalGoedeAntwoorden) {
        final int BONUSPUNTEN_ANTWOORDEN = 100;
        final int BONUSPUNTEN_SCOREWOORD = 75;
        final int AFTREKPUNTEN_SECONDE_SPEELTIJD = 1;

        int score = 0;

        score += (aantalGoedeAntwoorden * BONUSPUNTEN_ANTWOORDEN);
        score += (scoreWoord.length() * BONUSPUNTEN_SCOREWOORD);
        score -= (speelTijd * AFTREKPUNTEN_SECONDE_SPEELTIJD);

        return score;
    }
}
