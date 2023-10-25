package nl.han.oose.parola.utils;

import nl.han.oose.parola.speler.Speler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SpelerWriter {

    public void registerSpeler(List<Speler> spelers) {
        String path = "src/main/resources/quiz/speler.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            for (Speler speler :
                    spelers) {
                writer.write(speler.getGebruikersnaam() + "," + speler.getWachtwoord() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
