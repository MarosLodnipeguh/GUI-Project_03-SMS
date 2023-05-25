package SMS;

import java.util.ArrayList;

public class PhoneBookLogic {
    public static ArrayList<String> recipentBook = new ArrayList<String>();
    public static ArrayList <String> phoneBook = new ArrayList<String>(); // Zawiera wszystkie numery telefonów VBD i VRD

    public static int StationsCounter = 1;

    public static String getRandomRecipent () throws NullRecipentException {
        if (recipentBook.size() == 0) {
//            throw new NullRecipentException();
            return null;
        }
        else {
            return recipentBook.get((int) (Math.random() * recipentBook.size()));

        }
    }

    public static String generateNumber() {
        String number = "";
        for (int i = 0; i < 9; i++) {
            number += (int) (Math.random() * 10);
        }
        if (phoneBook.contains(number)) {
            generateNumber();
        }
        return number;
    }


}
