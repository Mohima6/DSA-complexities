package TimeAndSpace.DSA;

import java.util.Scanner;
import java.util.regex.*;
import java.nio.charset.StandardCharsets;

public class Strings {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input for the base string
        System.out.print("Enter a string: ");
        String userInput = scanner.nextLine();

        // Menu for string operations
        int option;
        do {
            System.out.println("\nChoose an operation:");
            System.out.println("1. StringBuffer and StringBuilder");
            System.out.println("2. String Comparison");
            System.out.println("3. Regular Expressions");
            System.out.println("4. String Formatting");
            System.out.println("5. String Join");
            System.out.println("6. Interning Strings");
            System.out.println("7. Character Encoding/Decoding");
            System.out.println("8. Substring");
            System.out.println("9. String to Character Array");
            System.out.println("10. Lowercase and Uppercase");
            System.out.println("11. String Trimming");
            System.out.println("12. String Split");
            System.out.println("13. String Replace");
            System.out.println("14. Check Empty or Blank");
            System.out.println("15. String Equals Ignore Case");
            System.out.println("16. Exit");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (option) {
                case 1:
                    // StringBuffer and StringBuilder
                    StringBuffer sb = new StringBuffer(userInput);
                    sb.append(" - Appended using StringBuffer");
                    System.out.println("StringBuffer: " + sb);
                    StringBuilder sb2 = new StringBuilder(userInput);
                    sb2.append(" - Appended using StringBuilder");
                    System.out.println("StringBuilder: " + sb2);
                    break;

                case 2:
                    // String Comparison
                    System.out.print("Enter another string to compare: ");
                    String comparisonString = scanner.nextLine();
                    System.out.println("Equals (case-sensitive): " + userInput.equals(comparisonString));
                    System.out.println("Equals Ignore Case: " + userInput.equalsIgnoreCase(comparisonString));
                    System.out.println("CompareTo Ignore Case: " + userInput.compareToIgnoreCase(comparisonString));
                    break;

                case 3:
                    // Regular Expressions
                    System.out.print("Enter a regular expression to match the string: ");
                    String regex = scanner.nextLine();
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(userInput);
                    System.out.println("Matches the input string with regex: " + matcher.matches());
                    break;

                case 4:
                    // String Formatting
                    System.out.print("Enter a floating point number to format: ");
                    double number = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline
                    String formattedString = String.format("This is a number: %.2f", number);
                    System.out.println(formattedString);
                    System.out.printf("Formatted output with printf: %.2f\n", number);
                    break;

                case 5:
                    // String Join
                    System.out.print("Enter multiple words separated by space: ");
                    String[] words = scanner.nextLine().split(" ");
                    String joined = String.join(" ", words);
                    System.out.println("Joined String: " + joined);
                    break;

                case 6:
                    // Interning Strings
                    String s1 = new String(userInput);
                    String s2 = new String(userInput);
                    System.out.println(s1 == s2);  // false (different objects)
                    String s3 = s1.intern();
                    String s4 = s2.intern();
                    System.out.println(s3 == s4);  // true (same reference from string pool)
                    break;

                case 7:
                    // Character Encoding/Decoding
                    byte[] bytes = userInput.getBytes(StandardCharsets.UTF_8);
                    System.out.println("Encoded Bytes: " + new String(bytes));
                    String decoded = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("Decoded String: " + decoded);
                    break;

                case 8:
                    // Substring
                    System.out.print("Enter start index for substring extraction: ");
                    int startIndex = scanner.nextInt();
                    System.out.print("Enter end index for substring extraction: ");
                    int endIndex = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    String substring = userInput.substring(startIndex, endIndex);
                    System.out.println("Substring: " + substring);
                    break;

                case 9:
                    // String to Character Array
                    char[] charArray = userInput.toCharArray();
                    System.out.println("Character Array: ");
                    for (char c : charArray) {
                        System.out.print(c + " ");
                    }
                    System.out.println();
                    break;

                case 10:
                    // Lowercase and Uppercase
                    System.out.println("Lowercase: " + userInput.toLowerCase());
                    System.out.println("Uppercase: " + userInput.toUpperCase());
                    break;

                case 11:
                    // String Trimming
                    System.out.println("Trimmed String: " + userInput.trim());
                    break;

                case 12:
                    // String Split
                    System.out.print("Enter a delimiter to split the string: ");
                    String delimiter = scanner.nextLine();
                    String[] splitStrings = userInput.split(delimiter);
                    System.out.println("Split Strings: ");
                    for (String s : splitStrings) {
                        System.out.println(s);
                    }
                    break;

                case 13:
                    // String Replace
                    System.out.print("Enter a string to replace: ");
                    String toReplace = scanner.nextLine();
                    System.out.print("Enter replacement string: ");
                    String replaceWith = scanner.nextLine();
                    System.out.println("Replaced String: " + userInput.replace(toReplace, replaceWith));
                    break;

                case 14:
                    // Check Empty or Blank
                    System.out.println("Is empty: " + userInput.isEmpty());
                    System.out.println("Is blank: " + userInput.isBlank());
                    break;

                case 15:
                    // String Equals Ignore Case
                    System.out.print("Enter another string to compare ignoring case: ");
                    String ignoreCaseString = scanner.nextLine();
                    System.out.println("Equals (ignoring case): " + userInput.equalsIgnoreCase(ignoreCaseString));
                    break;

                case 16:
                    // Exit the program
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (option != 16);

        scanner.close();
    }
}
