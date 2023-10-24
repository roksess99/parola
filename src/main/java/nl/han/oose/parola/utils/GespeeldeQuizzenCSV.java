package nl.han.oose.parola.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GespeeldeQuizzenCSV {
    final String path = "src/main/resources/quiz/gespeelde_quizzen.csv";

    public List<String[]> getGespeeldeQuizzenSpelernaam(String gebruikersnaam){
        List<String[]> gespeeldeQuizzen = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String row;
            while ((row = reader.readLine()) != null){
                String[] result = row.split("\\.");
                if (result.length >= 2 && result[0].equals(gebruikersnaam)){
                    gespeeldeQuizzen.add(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gespeeldeQuizzen;
    }

    public List<String[]> getGespeeldeQuizzenQuiznaam(String quiznaam){
        List<String[]> gespeeldeQuizzen = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String row;
            while ((row = reader.readLine()) != null){
                String[] result = row.split("\\.");
                if (result.length >= 2 && result[1].equals(quiznaam)){
                    gespeeldeQuizzen.add(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gespeeldeQuizzen;
    }

    public void writeGespeeldeQuiz(String quizNaam, String spelerNaam, String[] score){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))){
            String aantalGoedeAntwoorden = score[0];
            String scoreWoord = score[1];
            String speeltijd = score[2];
            String scoreAantal = score[3];
            String scoreLetters = score[4];

            writer.write(spelerNaam + "." + quizNaam + "." +
                    aantalGoedeAntwoorden + "." +  scoreWoord + "." +
                    speeltijd + "." +  scoreAantal + "." +  scoreLetters);

            writer.newLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
