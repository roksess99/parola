package nl.han.oose.parola.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class QuizScanner {
    final String path = "src/main/resources/quiz/quiz.csv";
    public List<String[]> leesVragen() {
        try(CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> rows = reader.readAll();
            return rows;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return null;
    }


}
