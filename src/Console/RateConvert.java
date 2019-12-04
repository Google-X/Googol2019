package Console;

public class RateConvert {

    public RateConvert(String cmd) {
        boolean run = true;

        while (run) {
            String CUR1 = "";
            String CUR2 = "";
            double CUR1Rate = 0.0;
            double CUR2Rate = 0.0;
            double rate = 0.0;
            String[] temp = cmd.split(" ");

            if (temp.length < 3) {
                System.out.println("Please include two currencies in your sentence. (eg. 1USD -> MYR or 1 EUR to MYR)");
                run = false;
                break;
            }

            String getAmount = "";
            double amount = 0;

            for (int i = 0; i < cmd.length(); i++) {
                if (isNumeric(String.valueOf(cmd.charAt(i)))) {
                    getAmount += cmd.charAt(i);
                } else if (cmd.charAt(i) == '.') {
                    getAmount += ".";
                }
            }

            amount = Double.parseDouble(getAmount);

            for (int i = 0; i < temp.length; i++) {
                if (temp[i].equalsIgnoreCase("to") || temp[i].equals("->")) {
                    CUR2 = temp[i + 1].toUpperCase();
                    break;
                }
            }

            for (int j = temp.length - 1; j >= 0; j--) {
                if (temp[j].equalsIgnoreCase("to") || temp[j].equals("->")) {
                    CUR1 = temp[j - 1].toUpperCase();

                    if (CUR1.contains(getAmount)) {
                        CUR1 = CUR1.replaceAll(getAmount, "");
                        CUR1 = CUR1.replaceAll(" ", "");
                    }

                    break;
                }
            }

            RateLoad a = new RateLoad();

            // Converting MYR to other currency
            if (CUR1.equals("MYR")) {
                CUR2Rate = a.load(CUR2);

                rate = 1 / CUR2Rate;
                double converted = amount * rate;
                System.out.printf("%f %s = %.3f %s\n", amount, CUR1, converted, CUR2);

            } else {
                CUR1Rate = a.load(CUR1);

                // Converting other currency to other or MYR
                if (CUR2.equals("MYR")) {

                    System.out.printf("%f %s = %f %s\n", amount, CUR1, (amount * CUR1Rate), CUR2);

                } else {
                    CUR2Rate = a.load(CUR2);

                    if (CUR2Rate == 0) {
                        System.out.println("Sorry! Currency " + CUR2 + " is not found in our database.");
                        run = false;
                        break;
                    }

                    rate = CUR1Rate * (1 / CUR2Rate);
                    double converted = amount * rate;
                    System.out.printf("%f %s = %.3f %s\n", amount, CUR1, converted, CUR2);
                }
            }
                run = false;
                break;
        }
    }
    public boolean isNumeric(String strNum) {

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
