package TimeAndSpace.DSA;

import java.util.Scanner;

public class Search {

    // Linear Search: O(n)
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Found
            }
        }
        return -1; // Not found
    }

    // Binary Search: O(log n), requires sorted array
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Found
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Not found
    }

    // Jump Search: O(√n), requires sorted array
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;

        while (arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) return -1;
        }

        // Linear search in the block
        for (int i = prev; i < Math.min(step, n); i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1; // Not found
    }

    // Exponential Search: O(log n)
    public static int exponentialSearch(int[] arr, int target) {
        if (arr[0] == target) {
            return 0; // Found at index 0
        }

        int i = 1;
        while (i < arr.length && arr[i] <= target) {
            i = i * 2;
        }
        return binarySearch(arr, target); // Binary search on the identified subarray
    }

    // Interpolation Search: O(log log n), requires sorted array
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {
            if (low == high) {
                if (arr[low] == target) return low;
                return -1;
            }

            int pos = low + (((high - low) / (arr[high] - arr[low])) * (target - arr[low]));

            if (arr[pos] == target) {
                return pos;
            }

            if (arr[pos] < target) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        return -1; // Not found
    }

    // Ternary Search: O(log3 n), requires sorted array
    public static int ternarySearch(int[] arr, int target) {
        return ternarySearchUtil(arr, target, 0, arr.length - 1);
    }

    private static int ternarySearchUtil(int[] arr, int target, int left, int right) {
        if (right >= left) {
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            // Check if target is at mid1 or mid2
            if (arr[mid1] == target) {
                return mid1;
            } else if (arr[mid2] == target) {
                return mid2;
            }

            // If the target is smaller than mid1, search in the left subarray
            if (target < arr[mid1]) {
                return ternarySearchUtil(arr, target, left, mid1 - 1);
            }
            // If the target is greater than mid2, search in the right subarray
            else if (target > arr[mid2]) {
                return ternarySearchUtil(arr, target, mid2 + 1, right);
            }
            // Otherwise, search in the middle subarray
            else {
                return ternarySearchUtil(arr, target, mid1 + 1, mid2 - 1);
            }
        }
        return -1; // Not found
    }

    // Fibonacci Search: O(log n), requires sorted array
    public static int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;
        int fibMMm2 = 0; // Fibonacci (m-2)th number
        int fibMMm1 = 1; // Fibonacci (m-1)th number
        int fibM = fibMMm2 + fibMMm1; // Fibonacci mth number

        // Find the smallest Fibonacci number greater than or equal to n
        while (fibM < n) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }

        int offset = -1;

        // Continue until we have searched the entire array
        while (fibM > 1) {
            int i = Math.min(offset + fibMMm2, n - 1);

            // If target is found at i
            if (arr[i] == target) {
                return i;
            }

            // If target is greater than i, cut the subarray from [offset, i]
            if (arr[i] < target) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }
            // If target is smaller than i, cut the subarray from [i+1, n]
            else {
                fibM = fibMMm2;
                fibMMm2 = fibMMm1 - fibMMm2;
                fibMMm1 = fibM - fibMMm2;
            }
        }

        // Last comparison
        if (fibMMm1 == 1 && arr[offset + 1] == target) {
            return offset + 1;
        }

        return -1; // Not found
    }

    // Method to display the array
    public static void displayArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main method to take input from user and run searches
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Search Algorithms Program!");
        System.out.println("=========================================");
        System.out.println("Please enter the size of the array:");
        int size = scanner.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        // Sort the array for Binary, Jump, Interpolation, Ternary, and Fibonacci search
        java.util.Arrays.sort(arr);

        System.out.println("Array after sorting:");
        displayArray(arr);

        System.out.println("Enter the element to search for:");
        int target = scanner.nextInt();

        System.out.println("\nChoose the search algorithm:");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search");
        System.out.println("3. Jump Search");
        System.out.println("4. Exponential Search");
        System.out.println("5. Interpolation Search");
        System.out.println("6. Ternary Search");
        System.out.println("7. Fibonacci Search");

        System.out.print("Enter your choice (1-7): ");
        int choice = scanner.nextInt();

        int result = -1;
        switch (choice) {
            case 1:
                result = linearSearch(arr, target);
                System.out.println("Linear Search Result: " + (result == -1 ? "Not found" : "Found at index " + result));
                System.out.println("Time Complexity: O(n), Space Complexity: O(1)");
                break;

            case 2:
                result = binarySearch(arr, target);
                System.out.println("Binary Search Result: " + (result == -1 ? "Not found" : "Found at index " + result));
                System.out.println("Time Complexity: O(log n), Space Complexity: O(1)");
                break;

            case 3:
                result = jumpSearch(arr, target);
                System.out.println("Jump Search Result: " + (result == -1 ? "Not found" : "Found at index " + result));
                System.out.println("Time Complexity: O(√n), Space Complexity: O(1)");
                break;

            case 4:
                result = exponentialSearch(arr, target);
                System.out.println("Exponential Search Result: " + (result == -1 ? "Not found" : "Found at index " + result));
                System.out.println("Time Complexity: O(log n), Space Complexity: O(1)");
                break;

            case 5:
                result = interpolationSearch(arr, target);
                System.out.println("Interpolation Search Result: " + (result == -1 ? "Not found" : "Found at index " + result));
                System.out.println("Time Complexity: O(log log n), Space Complexity: O(1)");
                break;

            case 6:
                result = ternarySearch(arr, target);
                System.out.println("Ternary Search Result: " + (result == -1 ? "Not found" : "Found at index " + result));
                System.out.println("Time Complexity: O(log3 n), Space Complexity: O(1)");
                break;

            case 7:
                result = fibonacciSearch(arr, target);
                System.out.println("Fibonacci Search Result: " + (result == -1 ? "Not found" : "Found at index " + result));
                System.out.println("Time Complexity: O(log n), Space Complexity: O(1)");
                break;

            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
