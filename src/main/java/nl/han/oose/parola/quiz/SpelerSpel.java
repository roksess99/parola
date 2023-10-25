package nl.han.oose.parola.quiz;

public class SpelerSpel {
    private String spelernaam;
    private int starttijd;

    private int huidigeVraagNr;

    public SpelerSpel(String spelernaam) {
        this.spelernaam = spelernaam;
        starttijd = (int) System.currentTimeMillis();
        huidigeVraagNr = 0;
    }

    public String getSpelernaam() {
        return spelernaam;
    }

    public int getSpeeltijd() {
        return (int) ((( System.currentTimeMillis()) - starttijd)) / 1000;
    }

    public int getVraagNr() {
        return huidigeVraagNr;
    }

    public void verhoogVraagNr() {
        huidigeVraagNr++;
    }
}
