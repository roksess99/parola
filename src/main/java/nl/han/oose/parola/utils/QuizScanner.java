package nl.han.oose.parola.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class QuizScanner {
    final String path = "src/main/resources/quiz/quiz.csv";
    public void leesVragen() throws FileNotFoundException {
        try(CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> rows = reader.readAll();

            for (int i = 0; i < rows.size(); i += 8) {
                for (int j = i; j < i + 8 && j < rows.size() ; j++) {
                    String[] row = rows.get(j);
                    String quizNaam = row[0];
                    String vraagType = row[1];
                    String vraag = row[2];
                    String[] keuzes = {row[3], row[4], row[5], row[6]};
                    String juisteAntwoord = row[7];
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }


}
