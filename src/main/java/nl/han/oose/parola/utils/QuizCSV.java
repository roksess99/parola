package nl.han.oose.parola.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizCSV {
    final String path = "src/main/resources/quiz/quiz.csv";
    public List<String[]> leesVragen() {
        try(CSVReader reader = new CSVReader(new FileReader(path))) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<String> leesQuiznamen() {
        try(CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> rows = reader.readAll();
            List<String> quiznamen = new ArrayList<>();
            for (int quiz = 0; quiz < rows.size(); quiz += 8) {
                String[] row = rows.get(quiz);
                quiznamen.add(row[0]);
            }
            return quiznamen;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


}
