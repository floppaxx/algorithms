import java.util.*;

public class Puzzle {

    private static String[] words;
    private static String uniqueCharacters;
    private static int solutionCount = 0;
    
    public static void generatePermutations(int n, int k, HashSet<Integer> set, int[] permutation) {
        if (set.size() == k) {
            HashMap<Character, Integer> charMap = new HashMap<>();
            for (int i = 0; i < k; i++) {
                charMap.put(uniqueCharacters.charAt(i), permutation[i]);
            }
            checkSolution(n, k, charMap);
        } else {
            for (int i = 0; i < n; i++) {
                if (!set.contains(i)) {
                    permutation[set.size()] = i;
                    set.add(i);
                    generatePermutations(n, k, set, permutation);
                    set.remove(i);
                }
            }
        }
    }

    public static void checkSolution(int n, int k, HashMap<Character, Integer> charMap) {
        long[] values = new long[3];
        for (int j = 0; j < 3; j++) {
            String word = words[j];
            if (charMap.get(word.charAt(0)) == 0)
                return;
            long val = 0;
            for (int i = 0; i < word.length(); i++)
                val = 10 * val + charMap.get(word.charAt(i));
            values[j] = val;
        }
        if (values[0] + values[1] == values[2]) {
            solutionCount++;
            System.out.println(charMap);
            System.out.format("%8d\n+%7d\n=%7d\n", values[0], values[1], values[2]);
            System.out.println("Solutions found so far: " + solutionCount);
        }
    }

    public static void solvePuzzle(String word1, String word2, String word3) {
        words = new String[]{word1, word2, word3};
        solutionCount = 0; //Fixed solution counter

        if (words.length != 3) {
            System.out.println("Program requires three words as arguments.");
            System.exit(1);
        }
        for (int i = 0 ; i < words.length; i++){
            if ( words[i].length() > 18){
                System.out.println("Word: " + words[i] + " is too long.");
                System.exit(1);
            }
        }

        for (int i = 0; i < 3; i++){
            System.out.println(words[i]);
        }
            
        String all = word1 + word2 + word3;
        uniqueCharacters = "";
        for (int i = 0; i < all.length(); i++) {
            char c = all.charAt(i);
            if (uniqueCharacters.indexOf(c) < 0) uniqueCharacters += c;
        }
        HashSet<Integer> set = new HashSet<>();
        int[] permutation = new int[uniqueCharacters.length()];
        generatePermutations(10, uniqueCharacters.length(), set, permutation);
        if (solutionCount == 0) {
            System.out.println("No solutions found.");
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        //solvePuzzle("SEND", "MORE", "MONEY");
        //solvePuzzle("YKS", "KAKS", "KOLM");
        //solvePuzzle("SJKDHFBSJKDHGFSDJKHGFSJKDHFGS", "KSDJFHSKLDJFHSDKLJFHSDLKJFHSDKJ", "SKLDJFHLKSDJHFSDKLJFHSDKLFJ");
        solvePuzzle("ABCDEFGHIJAB", "ABCDEFGHIJA", "ACEHJBDFGIAC");
        //solvePuzzle("CBEHEIDGEI", "CBEHEIDGEI", "BBBBBBBBBB");
        //solvePuzzle(args[0],args[1],args[2]);
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) / 1000000 + " ms");
    }
}
