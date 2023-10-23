package nl.han.oose.parola.creditwinkel;

import nl.han.oose.parola.speler.Speler;

import java.util.HashMap;
import java.util.Map;

public class CreditWinkel {
    private HashMap<Integer, Double> tarieven;

    public CreditWinkel() {
        tarieven = new HashMap<>();
        tarieven.put(1000, 1.00);
        tarieven.put(2250, 2.00);
        tarieven.put(5000, 4.00);
        tarieven.put(10000, 7.00);
    }

    public HashMap<Integer, Double> getTarieven() {
        return tarieven;
    }
    public void behandelBetaling(Speler speler, int bedrag){

    }
    public Double koopCredits(int credits){
        for (Map.Entry<Integer, Double> entry : tarieven.entrySet()) {
            if (credits == entry.getKey()) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Integer getHoeveelheid(Double bedrag) {
        for (Map.Entry<Integer, Double> entry : tarieven.entrySet()) {
            if (bedrag == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }
}
