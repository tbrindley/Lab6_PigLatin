/**
 * Created by Travis Brindley on 7/3/2017.
 * Assignment:  Create a program that will translate a sentence into Pig Latin.  The program can identify words with special characters and
 * will not translate those words.  Additionally, the program will not translate anything with a number in it.
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Translator {

    private static String[] GetString() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a sentence you want translated  ");
        String prePig = scan.nextLine();

        while(prePig.length() < 1){ //validates the user entered at least 1 character into their string " == null didn't work for some reason?"
            System.out.println("Invalid input, please enter a sentence you want translated:  ");
            prePig = scan.nextLine();
        }

        String[] preWords = prePig.split(" "); // splits string into individual words, separating them by looking for " "

        return preWords;
    }

    private static String[] GetLowercase(String[] preWords) {
        for (int i = 0; i < preWords.length; i++) {
            preWords[i] = preWords[i].toLowerCase();
        }
        return preWords;
    }

    private static void PigConvert(String[] preWords) {

        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        String[] pigWords = preWords;

        for (int i = 0; i < pigWords.length; i++) {     //cycles through every word in pigWords array
            Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(pigWords[i]);
            boolean b = m.find();

            if (b) { // if string has special character or number, it won't convert to pig latin
                pigWords[i] = preWords[i];
            }
            else {
                boolean vowelStart = false;
                for (int j = 0; j < vowels.length; j++) {     //cycles through every vowel

                    if (pigWords[i].charAt(0) == vowels[j]) {     // checks if the word starts with vowel
                        pigWords[i] = pigWords[i].concat("way");
                        vowelStart = true;
                    }
                }

                int k = 0;   // variable declaration
                Boolean countY = false;
                String addOn = "";
                int kickOut = 0;

                while (!vowelStart) { //if word doesn't start with a vowel, it checks and stores each char until it finds one.

                    if (kickOut == 2) { //kicks us out of the loop if user types in random "word" that doesn't have any vowels or y;
                        break;
                    }
                    if (k > pigWords[i].length() - 1) { //makes y a vowel if program didn't find another vowel during the first iteration.
                        countY = true;
                        k = 0;
                        addOn = "";
                        kickOut++;
                    }

                    if (pigWords[i].charAt(k) == 'a' || pigWords[i].charAt(k) == 'e' || pigWords[i].charAt(k) == 'i' || pigWords[i].charAt(k) == 'o' || pigWords[i].charAt(k) == 'u') {
                        pigWords[i] = pigWords[i].replace(addOn, "");
                        pigWords[i] = pigWords[i].concat(addOn);
                        pigWords[i] = pigWords[i].concat("ay");
                        break;
                    } else if (pigWords[i].charAt(k) == 'y' && countY) {
                        pigWords[i] = pigWords[i].replace(addOn, "");
                        pigWords[i] = pigWords[i].concat(addOn);
                        pigWords[i] = pigWords[i].concat("ay");
                        break;
                    } else if (pigWords[i].charAt(k) == '1') {
                        break;
                    } else {
                        addOn = addOn + pigWords[i].charAt(k);
                        k++;
                    }
                }
            }
            System.out.print(pigWords[i] + " ");
        }
    }

    public static void main(String[] args) {
        boolean keepLooping = true;
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the Grand Circus Translator \n ");

        while (keepLooping){
            String[] preWords = GetString();
            preWords = GetLowercase(preWords);
            PigConvert(preWords);

            System.out.print("\n \nDo you wish to translate something else? (Y/N)  ");
            String yesNo = scan.nextLine();

            while (!yesNo.equalsIgnoreCase("y") && !yesNo.equalsIgnoreCase("n") ){
                System.out.print("\nDo you wish to translate something else? (Y/N)  ");
                yesNo = scan.nextLine();
            }

            if(yesNo.equalsIgnoreCase("n")){
                keepLooping = false;
            }

        }
        System.out.println("Thanks for playing!");
    }

}
