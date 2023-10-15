package nl.han.oose.parola.quiz.score;

import nl.han.oose.parola.utils.WoordenlijstScanner;

import java.util.*;

public class Score {
    private int aantalGoedeAntwoorden;
    private String scoreWoord;
    private int speelTijd;
    private int score;

    private String spelernaam;

    private List<Character> scoreLetters;
    private ScoreStrategy strategy;

    public Score(int speelTijd, String spelernaam){
        this.speelTijd = speelTijd;
        this.spelernaam = spelernaam;

        scoreLetters = new ArrayList<>();
        setStrategy(new NormaleScoreStrategy());
    }

    public void setStrategy(ScoreStrategy strategy) {
        this.strategy = strategy;
    }

    public void voegKlinkerToe() {
         char[] klinkers = {'a', 'e', 'i', 'o', 'u'};

         char klinker = klinkers[new Random().nextInt(klinkers.length)];

         scoreLetters.set(0, klinker);
    }

    private boolean bevatScoreWoordJuisteLetters(String woord) {
        if (woord.length() > scoreLetters.size()) {
            return false;
        }

        boolean[] isScoreLetterGebruikt = new boolean[scoreLetters.size()];
        Arrays.fill(isScoreLetterGebruikt, false);

        for (int letter = 0; letter < woord.length(); letter++) {
            char currentLetter = woord.charAt(letter);

            List<Integer> indexes = new ArrayList<>();
            for (int index = 0; index < scoreLetters.size(); index++) {
                if (currentLetter == scoreLetters.get(index)) {
                    indexes.add(index);
                }
            }

            boolean letterToegewezen = false;
            int huidigeLetter = 0;
            while (!letterToegewezen) {
                if (huidigeLetter + 1 > indexes.size()) {
                    return false;
                }
                if (!isScoreLetterGebruikt[indexes.get(huidigeLetter)]) {
                    isScoreLetterGebruikt[indexes.get(huidigeLetter)] = true;
                    letterToegewezen = true;
                }
                huidigeLetter++;
            }
        }
        return true;
    }

    public void addScoreLetter(char letter){
        this.scoreLetters.add(letter);
        aantalGoedeAntwoorden += 1;
    }

    public Integer getScore(String woord){
        if (bevatScoreWoordJuisteLetters(woord) && bestaatWoord(woord)) {
            scoreWoord = woord;
            this.score = strategy.berekenEindScore(speelTijd, scoreWoord, aantalGoedeAntwoorden);
            return score;
        }
        return null;
    }

    private boolean bestaatWoord(String woord) {
        var scanner = new WoordenlijstScanner();

        return scanner.staatWoordInWoordenlijst(woord);
    }

    public List<Character> getScoreLetters(){
        if (!bevattenLettersEenKlinker(scoreLetters)) {
            voegKlinkerToe();
        }
        return scoreLetters;
    }

    private boolean bevattenLettersEenKlinker(List<Character> scoreLetters) {
        char[] klinkers = {'a', 'e', 'i', 'o', 'u'};

        for (char k : klinkers) {
            if (scoreLetters.contains(k)) {
                return true;
            }
        }
        return false;
    }
}
