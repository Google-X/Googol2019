
package Console;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class RateLoad {
    
    private File filename;
    
    public RateLoad(){
        filename = new File("ExchangeRate.dat");
    }
    
    public double load(String CUR) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            HashMap<String, Double> exchangeRate = (HashMap<String, Double>) in.readObject();

            for(String i : exchangeRate.keySet()){
                if(i.equalsIgnoreCase(CUR)){
                    return exchangeRate.get(i);
                }
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
