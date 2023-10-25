package nl.han.oose.parola.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import nl.han.oose.parola.speler.Speler;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SpelerCSV {
    String path = "src/main/resources/speler/speler.csv";

    public List<String[]> leesSpelers() {
        try(CSVReader reader = new CSVReader(new FileReader(path))) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void slaSpelersOp(List<Speler> spelers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Speler speler :
                    spelers) {
                writer.write(speler.getGebruikersnaam() + "," + speler.getWachtwoord() + "," + speler.getSaldo() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
