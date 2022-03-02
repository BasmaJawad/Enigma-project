package finalProject;

import java.util.Scanner;

public class CaesarCipher {

    Scanner in = new Scanner(System.in);
    String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
    boolean keepGoing = true;

    public static void main(String[] args) {
        new CaesarCipher().go();

    }

    void go(){ chosenMenuNumber(menuNumber());}

    int menuNumber() {

        int userMenuChoice;
        System.out.println("Please choose from the following options:");
        System.out.println(" 1) Caesar  \n 2) Vigenère \n 3) Number \n 4) Exit");

        userMenuChoice = in.nextInt();
        return userMenuChoice;
    }
    void chosenMenuNumber(int menuNumber) {

        if (menuNumber == 1)
            loopCaesar();

    }


    void loopCaesar(){

        while(keepGoing){
            caesar();
        }
    }
    void caesar() {
        int option;
        System.out.println("\nChoose option: \n 1) Encrypt message \n 2) Decrypt message\n 3) Exit");
        option = in.nextInt();

        if (option == 1)
            caesarEncryption();

        else if (option == 2)
            caesarDecryption();

        else
            keepGoing=false;
    }

    void caesarEncryption(){
        String plaintext = message("plaintext");    //Method message: takes message (input) from user
        int shiftVal = askForShift();               //askForShift: Takes int shift value
        int[] letterValuesInMessage = letterValuesInMessage(plaintext);             //Finding Letter index In Message "plaintext"/input user
        int[] newLetterVal = letterEncryptionVal(letterValuesInMessage,shiftVal);   // Letter Encrypted Val: uses letter index and shift value to find new letter index
        char[] encryptedMessage = newMessage(newLetterVal);
        System.out.println("\nEncrypted message:");
        System.out.println(encryptedMessage);

    }

    void caesarDecryption(){
        String plaintext = message("Chipher text");
        int shiftVal = askForShift();
        int[] letterValuesInMessage = letterValuesInMessage(plaintext);
        int[] newLetterVal = letterDecryptionVal(letterValuesInMessage,shiftVal);
        char[] decryptedMessage = newMessage(newLetterVal);
        System.out.println("\nDecrypted message:");
        System.out.println(decryptedMessage);

    }


    String message(String type){
        String message;
        System.out.println("Please enter your message in " + type + ":" );
        in.nextLine();
        message = in.nextLine();

        return message;
    }
    int askForShift(){
        int shiftVal;
        System.out.print("Please choose code key (1-29): ");
        shiftVal = in.nextInt();

        return shiftVal;
    }


    int[] letterValuesInMessage(String message) {

        int[] letterVal = new int[message.length()];

        for (int i = 0; i < message.length(); i++) {
            int num = charToInt(message.charAt(i));
            letterVal[i] = num;
        }
        return letterVal;
    }

    int charToInt(char letter) {
        letter = Character.toUpperCase(letter);
        int number = alphabet.indexOf(letter);
        return number;
    }


    int[] letterEncryptionVal(int[] IndexOfLetters, int shiftVal) {

        int[] newIndexOfLetters = new int[IndexOfLetters.length];

        for (int i = 0; i < IndexOfLetters.length; i++) {
            int shiftedLetterVal = IndexOfLetters[i] + shiftVal;

            if (IndexOfLetters[i]==0) shiftedLetterVal = 0;
            if (shiftedLetterVal >= alphabet.length())
                shiftedLetterVal = alphabet.length() - IndexOfLetters[i];

            newIndexOfLetters[i] = shiftedLetterVal;
        }
        return newIndexOfLetters;
    }

    int[] letterDecryptionVal(int[] IndexOfLetters, int shiftVal) {

        int[] newIndexOfLetters = new int[IndexOfLetters.length];

        for (int i = 0; i < IndexOfLetters.length; i++) {
            int shiftedLetterVal = IndexOfLetters[i] - shiftVal;

            if (IndexOfLetters[i]==0) shiftedLetterVal = 0;
            else if (shiftedLetterVal < 1)
                shiftedLetterVal = alphabet.length() - 1 + shiftedLetterVal;

            newIndexOfLetters[i] = shiftedLetterVal;
        }
        return newIndexOfLetters;
    }

    char[] newMessage(int[] newLetterVal) {

        char[] newMessage = new char[newLetterVal.length];
        for (int i = 0; i < newLetterVal.length; i++) {
            char newLetter = intToChar(newLetterVal[i]);
            newMessage[i] = newLetter;
        }
        return newMessage;
    }

    char intToChar(int number) {
        char letter = alphabet.charAt(number);
        return letter;
    }

}

