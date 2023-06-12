//-----------------------------------------------------
// Title: Main class for the second question.
// Authors: Burak Koç - Furkan Safa Altunyuva
// Section: 4
// Assignment: 4
// Description:This class is the runner class. It basically does all the necessary things for the second question
//-----------------------------------------------------

import java.util.*;

public class MainQ2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("First Array");
        String[] firstArray = scanner.nextLine().split(" ");

        // Input for the second array
        System.out.println("Second Array");
        String[] secondArray = scanner.nextLine().split(" ");

        String[] sortedArray = sortStrings(firstArray, secondArray);
        System.out.println("Sorted Array: ");
        for (String element : sortedArray) {
            System.out.print(element + " ");
        }
    }

    public static String[] sortStrings(String[] firstArray, String[] secondArray) {
        Trie<Long> firstTrie = new Trie<Long>();
        Trie<Long> secondTrie = new Trie<Long>();

        // Store the integer values of strings in the first array in the firstTrie
        for (String str : firstArray) {
            long integerValue = calculateIntegerValue(str);
            firstTrie.put(str, integerValue);
        }

        // Store the integer values of strings in the second array in the secondTrie
        for (String str : secondArray) {
            long integerValue = calculateIntegerValue(str);
            secondTrie.put(str, integerValue);
        }

        // Process each element in the arrays
        for (int i = 0; i < secondArray.length; i++) {
            long fVal = firstTrie.get(firstArray[i]); // Get the integer value of the current string from firstTrie
            long scVal = secondTrie.get(secondArray[i]); // Get the integer value of the current string from secondTrie

            if (Math.abs(fVal - scVal) % 2 == 0) {
                // If the absolute difference between the two integer values is even

                char[] secArr = secondArray[i].toCharArray();
                char[] firArr = firstArray[i].toCharArray();

                int place = 0;
                String newStrCond = "";

                // Iterate over each character in the second array element
                for (int j = 0; j < secArr.length; j++) {
                    char c = secArr[j];

                    // Iterate over each character in the first array element
                    for (int k = 0; k < firArr.length; k++) {
                        char d = firArr[k];

                        if (c == d) {
                            // Swap the characters to move 'd' to the correct position in 'firArr'
                            for (int l = k; l > place; l--) {
                                firArr[l] = firArr[l - 1];
                            }
                            firArr[place] = secArr[j];
                            place++;
                        }
                    }
                    newStrCond = String.valueOf(firArr);
                    firstArray[i] = newStrCond;
                }
            } else {
                // If the absolute difference between the two integer values is odd
                String newVers = "";

                // Sort the characters of the first array element
                for (int j = 0; j < firstArray[i].length(); j++) {
                    char[] firArr = firstArray[i].toCharArray();
                    Arrays.sort(firArr);
                    newVers = String.valueOf(firArr);
                }

                firstArray[i] = newVers;
            }
        }

        return firstArray;
    }

    public static long calculateIntegerValue(String str) {
        StringBuilder strVal = new StringBuilder();
        long value = 0;

        // Calculate the integer value of the string by converting each character to its corresponding numerical value
        for (char c : str.toCharArray()) {
            value = c - 'a' + 1;
            strVal.append(String.valueOf(value));
        }

        value = Long.parseLong(strVal.toString());
        return value;
    }
}
