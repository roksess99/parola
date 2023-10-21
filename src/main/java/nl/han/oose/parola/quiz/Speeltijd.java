package nl.han.oose.parola.quiz;

public class Speeltijd {
    private String spelernaam;
    private int starttijd;

    public Speeltijd(String spelernaam) {
        this.spelernaam = spelernaam;
        starttijd = (int) System.currentTimeMillis();
    }

    public String getSpelernaam() {
        return spelernaam;
    }

    public int getSpeeltijd() {
        return (((int) System.currentTimeMillis()) - starttijd);
    }
}
