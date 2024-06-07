import java.util.*;

/**
 * The main class for executing the program.
 *
 * @author Emma Lee
 * @version 2024
 */
public class Main
{
    // Define constants for argument positions
    private static final int COMMAND_ARG_INDEX = 0;
    private static final int REPEAT_ARG_INDEX = 1;
    private static final int MIN_REQUIRED_ARGS = 1;
    private static final int MIN_ARGS_FOR_REPEAT = 2;

    /**
     * The main method for running the program.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args)
    {
        if (args.length < MIN_REQUIRED_ARGS)
        {
            System.out.println("Please provide at least one argument.");
            return;
        }

        Dictionary dictionary;
        List<String> wordsList;
        String[] wordsArray;

        dictionary = new Dictionary();
        wordsList = Dictionary.getWordsList();
        wordsArray = wordsList.toArray(new String[COMMAND_ARG_INDEX]);
        Wordable wordy;

        // Switch statement to handle different command-line arguments
        switch (args[COMMAND_ARG_INDEX].toLowerCase())
        {
            case "concat":
                // Concatenate all words
                wordy = (inputString, n) -> String.join("", wordsList);
                break;
            case "repeat":
                // Repeat each word a specified number of times
                if (args.length < MIN_ARGS_FOR_REPEAT || !isNumeric(args[REPEAT_ARG_INDEX]))
                {
                    System.out.println("Please provide a valid number for repetition.");
                    return;
                }
                int repeatCount = Integer.parseInt(args[REPEAT_ARG_INDEX]);
                wordy = (inputString, integerParameter) -> {
                    StringBuilder sb;
                    sb = new StringBuilder();
                    for (final String word : wordsList)
                    {
                        sb.append(word.repeat(repeatCount));
                    }
                    return sb.toString();
                };
                break;
            case "nth":
                // Return the nth word from the list
                if (args.length < MIN_ARGS_FOR_REPEAT || !isNumeric(args[REPEAT_ARG_INDEX]))
                {
                    System.out.println("Please provide a valid index.");
                    return;
                }
                int index = Integer.parseInt(args[REPEAT_ARG_INDEX]);
                wordy = (inputString, integerParameter) -> index >= COMMAND_ARG_INDEX && index < wordsList.size() ? wordsList.get(index) : "Index out of bounds";
                break;
            case "reverse":
                // Reverse each word and concatenate them
                wordy = (inputString, integerParameter) -> {
                    StringBuilder sb = new StringBuilder();

                    for (final String word : wordsList)
                    {
                        sb.append(new StringBuilder(word).reverse().toString());
                    }
                    return sb.toString();
                };
                break;
            default:
                System.out.println("Invalid command.");
                return;
        }

        // Print the result of the wordy lambda expression
        System.out.println(dictionary.getWords("", COMMAND_ARG_INDEX, wordy));

        // Print the entire list of words
        wordsList.forEach(System.out::println);

        // Apply reverseString method reference to each word and print
        for (final String word : wordsList)
        {
            String reversed = Dictionary.reverseString(word);
            System.out.println(reversed);
        }

        // Sort words using the alphabeticalOrder method reference
        Arrays.sort(wordsArray, Dictionary::alphabeticalOrder);
        System.out.println("Sorted words: " + Arrays.toString(wordsArray));

        // Print words longer than five characters using method references
        for (final String word : wordsList)
        {
            if (Dictionary.isLengthAboveFive(word))
            {
                System.out.println(word);
            }
        }
    }

    /**
     * Checks if a string is numeric.
     *
     * @param str the string to be checked
     * @return true if the string is numeric, false otherwise
     */
    private static boolean isNumeric(final String str)
    {
        if (str == null || str.isEmpty())
        {
            return false;
        }
        for (final char character : str.toCharArray())
        {
            if (!Character.isDigit(character))
            {
                return false;
            }
        }
        return true;
    }
}
