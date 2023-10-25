package nl.han.oose.parola.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class WoordenlijstScanner {

    public boolean staatWoordInWoordenlijst(String woord) {
        Scanner scanner = null;
        try (FileInputStream file = new FileInputStream("src/main/resources/woordenlijst/basiswoorden-gekeurd.txt")) {
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(woord)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
