//-----------------------------------------------------
// Title: Main class for the first question
// Authors: Burak Koç - Furkan Safa Altunyuva
// Section: 4
// Assignment: 4
// Description: This class is the main class for the program that does the intended String operation for the String values that user gave
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.Scanner;

public class MainQ1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputOrder = new ArrayList<>(); //Creating an ArrayList to store the order of the user input
        ArrayList<Integer> countPrefixResultList = new ArrayList<>(); //Creating an ArrayList to store the resulting elements for the countPrefix() method

        Trie<Integer> trie = new Trie<>(); //Creating Trie Structure

        int number_of_words = scanner.nextInt(); //Taking the number of words to read

        for(int i = 0; i < number_of_words; i++){
            String word = scanner.next(); //Scanning the current word
            inputOrder.add(word); //Adding them into ArrayList with respect to their input order (To print in this order for the second method)
            trie.put(word, i); //Adding the current word to trie with value i
            countPrefixResultList.add(0); //To prevent the index out of bounds when accessing the random locations
        }

        System.out.println("Choose the function you want to use:");
        System.out.println();
        System.out.println("1) Search \n2) Count Prefix \n3) Find Reverse");

        System.out.println();
        int functionNumber = scanner.nextInt(); //Taking the input to display intended function's result

        switch (functionNumber){
            case 1:
                String valueToSearch = scanner.next();
                System.out.println();
                if(trie.search(valueToSearch)) //We placed the search method into Trie class to access necessary values easily
                    System.out.println("True"); //In expected output, boolean's first letter is uppercase
                else
                    System.out.println("False"); //In expected output, boolean's first letter is uppercase
                break;
            case 2:
                System.out.println();
                countPrefix(trie, inputOrder, countPrefixResultList);
                break;
            case 3:
                reverseFind(scanner.next(), trie);
                break;
            default:
                System.out.println("Wrong type of input.");
                break;
        }

    }

    public static void countPrefix(Trie<Integer> trieST, ArrayList<String> inputOrder, ArrayList<Integer> result){
        for(String value: trieST.keys()){ //trieST.keys contains the elements in alphabetical order but output must be in the order of input
            Iterable<String> keysContainingValue = trieST.keysWithPrefix(value); //Keys that contains the prefix of the current value

            int count = 0;
            for(String val: keysContainingValue){ //Counting the number of prefixes that contains the current value
                count++;
            }

            count--; //Deleting one from the count because it counts itself

            int orderIndexOfValue = inputOrder.indexOf(value); //Finding the order of the current value in the input
            result.set(orderIndexOfValue, count); //Setting the "orderIndexOfValue"th element to the count
        }

        System.out.println(result); //Printing the result for the second method
    }

    public static void reverseFind (String suffix, Trie<Integer> trieST){
        Trie<Integer> reverseTrieST = new Trie<>(); //Creating a Trie structure to store the reverse of the trieST
        ArrayList<String> result = new ArrayList<>(); //Creating an ArrayList to store the result

        int i = 0;

        //Creation of reverse Trie
        for(String value: trieST.keys()){
            StringBuilder reversedValue = new StringBuilder(); //Using StringBuilder class to reverse the String easily
            reversedValue.append(value);
            reversedValue.reverse();

            reverseTrieST.put(String.valueOf(reversedValue), i++); //Adding reversed string to the reverse Trie
        }
        //#Creation of reverse Trie

        //Reversing the suffix's itself too
        StringBuilder reversedSuffix = new StringBuilder();
        reversedSuffix.append(suffix);
        reversedSuffix.reverse();
        //#Reversing the suffix's itself too

        for(String value: reverseTrieST.keysWithPrefix(String.valueOf(reversedSuffix))){ //Reversing the reversed result to get the not reversed result (kcirb -> brick)
            StringBuilder reversedResult = new StringBuilder();
            reversedResult.append(value);
            reversedResult.reverse();
            result.add(String.valueOf(reversedResult));
        }

        if(result.isEmpty()) System.out.println("No result"); //If there is no element in the ArrayList, there is no result
        else System.out.println(result); //If there are elements in the ArrayList, print them

    }
}


