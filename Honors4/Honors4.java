//*********************************************************************************************************
//  Honors4.java   Author: Kauana dos Santos
//  Class: CS 111B - Section: 002 - Fourth Honors Project
//  Due Date: 12/10/15
//  Demonstrates the use of Recursion in checking wether a String is a valid blurb.
//  Algorithm:
//  1. Create method that checks if String is a Whoozit and if so return Whoozit length. (Whoozit = 'x' + 0 or + 'y's)
//  2. Create method that checks if String is a Whatzit and if so return Whatzit length. (Whatzit = 'q' + 'd' or 'z' + Whoozit)
//  3. Create method that checks (using Recursion) if given String contains only Whatzits after the first Whoozit.
//  4. Create method that checks if given String is a Blurb.
///********************************************************************************************************
import java.util.Scanner;

public class Honors4 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter String you want to check if is a Blurb. Ex: xyyyqzxyqzx, xyyyyyqz, xyqzxyqzxp.");
        String string = keyboard.nextLine();
        System.out.println("Entered String is a valid Blurb? " + isBlurb(string));
    }

    // Checks if given String is a whoozit by checking if the first letter is a 'x'. If first letter is x sum goes up by one.
    // Then, a for loop checks how many times 'y' occurs, and if charAt(i) is not y, break the for loop.
    // Method returns the number of times x, and 'y's occur in the String.
    public static int whoozitLength(String str) {
        int sum = 0;

        if (str.charAt(0) == 'x' && str.length() > 0) {
            sum += 1;
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) == 'y') {
                    sum += 1;
                }
                else {
                    break;
                }
            }
        }
        return sum;
    }

    // Checks if a String is a whatzit by first checking if given String contains at least 3 characters (a whatzit needs at least a 'q', 'z' or 'd' and an 'x')
    // If string is shorter than 2 characters, sum will be 0 (not a whatzit)
    // Then the next if checks if the first characters are 'q', and 'z' or 'd', if so the sum goes up by 2 (because of 'q' + 'd' or 'z')
    // Sum is used to remove the first 2 characters of the String so variable checkWhoozit can check if there is a whoozit after the first 2 characters.
    // Variable whoozitLength checks how long the whoozit in the given String is (and if there is a whoozit). Then, sum is returned (first 2 whatzit characters plus the whoozit length
    public static int whatzitLength(String str) {
        int sum = 0, whoozitLength;
        String checkWhoozit;

        if (str.length() > 2) {
            if (str.charAt(0) == 'q' && (str.charAt(1) == 'z' || str.charAt(1) == 'd')) {
                sum = 2;
                checkWhoozit = str.substring(2);
                whoozitLength = whoozitLength(checkWhoozit);
                sum = sum + whoozitLength;
            }
        }
        return sum;
    }

    // Method isBlurb is responsible for checking if first characters are whoozit and if so characters are removed from the string
    // Then, onlyWhatzit is called to check of the remaining substring is composed of 1 whatzit or more
    public static boolean isBlurb(String str) {
        int whoozitLength = whoozitLength(str);
        boolean checkBlurb = false;

        if (whoozitLength > 0) {
            str = str.substring(whoozitLength);
            if (str.length() > 2) {
            checkBlurb = onlyWhatzit(str);
            }
        }
        return checkBlurb;
    }

    // Recursive method. Checks how many times whatzits happen after the first whoozit.
    // The base case is an empty string because if there are only whatzits in the String, they will be "removed" from the string and only an empty String will remain.
    // For instance, if there is a character such as 'p' that doesn't belong to the whatzit, the method won't process it and string won't be empty.
    // Method checks how long a whatzit is and uses this number to cut a substring out of given string.
    // Recurssive method onlyWhatzit needs to call itself until String is empty or String is not a whatzit, so it can return true or false.
    public static boolean onlyWhatzit(String str) {
        int lengthWhatzit;
        boolean isWhatzit = false;

        if (str.length() == 0) {
            return true;
        }

        lengthWhatzit = whatzitLength(str);
        if (lengthWhatzit != 0) {
            str = str.substring(lengthWhatzit);
            isWhatzit = onlyWhatzit(str);
        }

        return isWhatzit;
    }
}
