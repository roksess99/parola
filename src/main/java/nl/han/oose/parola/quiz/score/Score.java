package nl.han.oose.parola.quiz.score;

import nl.han.oose.parola.utils.GespeeldeQuizzenCSV;
import nl.han.oose.parola.utils.WoordenlijstScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Score {
    private int aantalGoedeAntwoorden;
    private String scoreWoord;
    private int speelTijd;
    private int score;

    private String spelernaam;

    private List<Character> scoreLetters;
    private ScoreStrategy strategy;

    private String quizNaam;

    public Score(int speelTijd, String spelernaam, String quizNaam){
        this.speelTijd = speelTijd;
        this.spelernaam = spelernaam;
        this.quizNaam = quizNaam;

        scoreLetters = new ArrayList<>();
        setStrategy(new NormaleScoreStrategy());
    }

    public Score(int aantalGoedeAntwoorden, String scoreWoord, int speelTijd, int scoreAantal, String[] letters, String spelernaam, String quizNaam){
        this.scoreWoord = scoreWoord;
        this.speelTijd = speelTijd;
        this.spelernaam = spelernaam;
        this.quizNaam = quizNaam;
        this.score = scoreAantal;

        scoreLetters = new ArrayList<>();
        for (int letter = 0; letter < letters.length; letter++) {
            Character scoreLetter = (letters[letter]).charAt(0);
            scoreLetters.add(scoreLetter);
        }
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
            writeGespeeldeQuiz();
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

    public String getSpeler() {
        return spelernaam;
    }

    private void writeGespeeldeQuiz(){
        GespeeldeQuizzenCSV csv = new GespeeldeQuizzenCSV();

        String[] scoreArray = new String[5];
        scoreArray[0] = String.valueOf(this.aantalGoedeAntwoorden);
        scoreArray[1] = this.scoreWoord;
        scoreArray[2] = String.valueOf(this.speelTijd);
        scoreArray[3] = String.valueOf(this.score);
        scoreArray[4] = this.scoreLetters.toString();
        csv.writeGespeeldeQuiz(quizNaam, spelernaam, scoreArray);
    }
}
