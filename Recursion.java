package TimeAndSpace.DSA;

import java.util.Scanner;

public class Recursion {

    // Recursive method to calculate the factorial of a number
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // Recursive method to calculate the nth Fibonacci number
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Recursive method to calculate the greatest common divisor (GCD)
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Recursive method to calculate power of a number
    public static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        return base * power(base, exponent - 1);
    }

    // Recursive method to reverse a string
    public static String reverseString(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    // Recursive method to check if a number is prime
    public static boolean isPrime(int n, int i) {
        if (i == 1) {
            return true;
        }
        if (n % i == 0) {
            return false;
        }
        return isPrime(n, i - 1);
    }

    // Recursive method to find the sum of an array
    public static int arraySum(int[] arr, int n) {
        if (n == 0) {
            return 0;
        }
        return arr[n - 1] + arraySum(arr, n - 1);
    }

    // Recursive method to solve the Tower of Hanoi problem
    public static void towerOfHanoi(int n, char fromRod, char toRod, char auxRod) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + fromRod + " to " + toRod);
            return;
        }
        towerOfHanoi(n - 1, fromRod, auxRod, toRod);
        System.out.println("Move disk " + n + " from " + fromRod + " to " + toRod);
        towerOfHanoi(n - 1, auxRod, toRod, fromRod);
    }

    // Recursive method to find all permutations of a string
    public static void permute(String str, int l, int r) {
        if (l == r) {
            System.out.println(str);
        } else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i); // Backtrack
            }
        }
    }

    // Utility method to swap characters at position i and j in a string
    public static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    // Recursive method for binary search in a sorted array
    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] > target) {
                return binarySearch(arr, left, mid - 1, target);
            }

            return binarySearch(arr, mid + 1, right, target);
        }
        return -1;
    }

    // Recursive method to find the sum of digits of a number
    public static int sumOfDigits(int n) {
        if (n == 0) {
            return 0;
        }
        return n % 10 + sumOfDigits(n / 10);
    }

    // Recursive method to find subsets of a set
    public static void findSubsets(String str, String current, int index) {
        if (index == str.length()) {
            System.out.println("Subset: " + current);
            return;
        }
        findSubsets(str, current, index + 1); // Exclude current character
        findSubsets(str, current + str.charAt(index), index + 1); // Include current character
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a problem to solve:");
        System.out.println("1. Factorial");
        System.out.println("2. Fibonacci");
        System.out.println("3. GCD");
        System.out.println("4. Power");
        System.out.println("5. Reverse String");
        System.out.println("6. Prime Check");
        System.out.println("7. Array Sum");
        System.out.println("8. Tower of Hanoi");
        System.out.println("9. Permutations");
        System.out.println("10. Binary Search");
        System.out.println("11. Sum of Digits");
        System.out.println("12. Subsets of a Set");
        System.out.print("Enter your choice (1-12): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter a number to calculate factorial: ");
                int num1 = scanner.nextInt();
                System.out.println("Factorial of " + num1 + " is: " + factorial(num1));
                break;
            case 2:
                System.out.print("Enter a number to calculate Fibonacci series (n-th term): ");
                int num2 = scanner.nextInt();
                System.out.println("Fibonacci number at position " + num2 + " is: " + fibonacci(num2));
                break;
            case 3:
                System.out.print("Enter two numbers to find GCD: ");
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                System.out.println("GCD of " + a + " and " + b + " is: " + gcd(a, b));
                break;
            case 4:
                System.out.print("Enter base and exponent for power calculation: ");
                int base = scanner.nextInt();
                int exponent = scanner.nextInt();
                System.out.println(base + " raised to the power " + exponent + " is: " + power(base, exponent));
                break;
            case 5:
                System.out.print("Enter a string to reverse: ");
                scanner.nextLine();  // Consume newline
                String str1 = scanner.nextLine();
                System.out.println("Reversed string: " + reverseString(str1));
                break;
            case 6:
                System.out.print("Enter a number to check if it's prime: ");
                int num3 = scanner.nextInt();
                System.out.println("Is " + num3 + " prime? " + isPrime(num3, num3 / 2));
                break;
            case 7:
                System.out.print("Enter the size of the array: ");
                int size = scanner.nextInt();
                int[] arr1 = new int[size];
                System.out.println("Enter the elements of the array: ");
                for (int i = 0; i < size; i++) {
                    arr1[i] = scanner.nextInt();
                }
                System.out.println("Sum of array elements is: " + arraySum(arr1, size));
                break;
            case 8:
                System.out.print("Enter the number of disks for Tower of Hanoi: ");
                int n1 = scanner.nextInt();
                System.out.println("Steps for Tower of Hanoi with " + n1 + " disks:");
                towerOfHanoi(n1, 'A', 'C', 'B');
                break;
            case 9:
                System.out.print("Enter a string to generate its permutations: ");
                scanner.nextLine();  // Consume newline
                String str2 = scanner.nextLine();
                System.out.println("Permutations of " + str2 + ":");
                permute(str2, 0, str2.length() - 1);
                break;
            case 10:
                System.out.print("Enter the size of the array for binary search: ");
                int searchSize = scanner.nextInt();
                int[] arr2 = new int[searchSize];
                System.out.println("Enter the elements of the sorted array: ");
                for (int i = 0; i < searchSize; i++) {
                    arr2[i] = scanner.nextInt();
                }
                System.out.print("Enter the target element to search: ");
                int target = scanner.nextInt();
                int result = binarySearch(arr2, 0, arr2.length - 1, target);
                if (result == -1) {
                    System.out.println("Element not found.");
                } else {
                    System.out.println("Element found at index: " + result);
                }
                break;
            case 11:
                System.out.print("Enter a number to find the sum of its digits: ");
                int num4 = scanner.nextInt();
                System.out.println("Sum of digits of " + num4 + " is: " + sumOfDigits(num4));
                break;
            case 12:
                System.out.print("Enter a string to find all its subsets: ");
                scanner.nextLine();  // Consume newline
                String subsetStr = scanner.nextLine();
                System.out.println("Subsets of " + subsetStr + ":");
                findSubsets(subsetStr, "", 0);
                break;
            default:
                System.out.println("Invalid choice! Please enter a number between 1 and 12.");
        }

        scanner.close();
    }
}
