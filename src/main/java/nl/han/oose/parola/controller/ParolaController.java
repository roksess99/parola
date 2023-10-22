package nl.han.oose.parola.controller;

/*
* Deze klasse genaamd ParolaController wordt gebruikt om de gegeven
* 'main'-klasse te laten communiceren met de gemaakte backend code.
*
* In feite wordt hiermee het Adapter pattern van GoF toegepast,
* alleen met uitzondering van de interface, aangezien uitbereiding
* door het toevoegen van meerdere adapters niet nodig is.
*
* Alle methodes uit de 'main'-klasse komen hierin terug en roepen
* zelf geschreven methodes uit de klasse Parola aan (de oorspronkelijke controller).
 */

import java.util.List;

public class ParolaController {
    private static ParolaController instance = null;
    private final Parola PAROLA = new Parola();

    public static ParolaController getInstance() {
        if (instance == null) {
            instance = new ParolaController();
        }
        return instance;
    }

    public void startQuiz(String playername) {
        if (PAROLA.getSpeler(playername) == null) {
            PAROLA.registreerSpeler(playername, "");
        }
        PAROLA.startQuiz(playername);
    }

    public String nextQuestion(String playername) {
        return PAROLA.getVraag(playername);
    }

    public void processAnswer(String playername, String answer) {
        PAROLA.beantwoordVraag(playername, answer);
    }

    public boolean quizFinished(String playername) {
        return PAROLA.isQuizAfgelopen(playername);
    }

    public String getLettersForRightAnswers(String playername) {
        List<Character> letters = PAROLA.verwerkAntwoorden(playername);
        return String.valueOf(letters);
    }

    public int calculateScore(String playername, String word) {
        return PAROLA.geefScorewoord(playername, word);
    }
}


