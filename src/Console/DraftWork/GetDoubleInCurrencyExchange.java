
package Console.DraftWork;

public class GetDoubleInCurrencyExchange {
    
    public static void main(String[] args) {
        
        // TEST 1 TEST 1 TEST 1 TEST 1 TEST 1 TEST 1 TEST 1 TEST 1 TEST 1 TEST 1 TEST 1 TEST 1 TEST 1
        String input1 = "convert 100.123USD to MYR";
        String getAmount = "";
        double amount = 0;
        
        for (int i = 0; i < input1.length(); i++) {
            if (isNumeric(String.valueOf(input1.charAt(i)))) {
                getAmount += input1.charAt(i);
            } else if(input1.charAt(i) == '.') {
                getAmount += ".";
            } 
        }
        
        amount = Double.parseDouble(getAmount);
        System.out.println("1 = " + amount);
        
        
        // TEST 2 TEST 2 TEST 2 TEST 2 TEST 2 TEST 2 TEST 2 TEST 2 TEST 2 TEST 2 TEST 2 TEST 2 TEST 2
        String input2 = "convert100.12USD to MYR";
        getAmount = "";
        amount = 0;
        
        for (int i = 0; i < input2.length(); i++) {
            if (isNumeric(String.valueOf(input2.charAt(i)))) {
                getAmount += input2.charAt(i);
            } else if(input2.charAt(i) == '.') {
                getAmount += ".";
            } 
        }
        
        amount = Double.parseDouble(getAmount);
        System.out.println("2 = " + amount);

        
        // TEST 3 TEST 3 TEST 3 TEST 3 TEST 3 TEST 3 TEST 3 TEST 3 TEST 3 TEST 3 TEST 3 TEST 3 TEST 3
        String input3 = "100.1USD to MYR";
        getAmount = "";
        amount = 0;
        
        for (int i = 0; i < input3.length(); i++) {
            if (isNumeric(String.valueOf(input3.charAt(i)))) {
                getAmount += input3.charAt(i);
            } else if(input3.charAt(i) == '.') {
                getAmount += ".";
            } 
        }
        
        amount = Double.parseDouble(getAmount);
        System.out.println("3 = " + amount);
        
    }

    public static boolean isNumeric(String strNum) {

        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
