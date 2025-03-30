package TimeAndSpace.DSA;

import java.util.*;

public class SortingAlgorithms {

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr, minIdx, i);
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // Radix Sort
    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSortByDigit(arr, exp);
        }
    }

    private static void countSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    // Shell Sort
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    // Counting Sort
    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    // Bucket Sort
    public static void bucketSort(int[] arr) {
        if (arr.length <= 0) return;

        int minValue = Arrays.stream(arr).min().getAsInt();
        int maxValue = Arrays.stream(arr).max().getAsInt();
        int bucketCount = (maxValue - minValue) / arr.length + 1;

        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i : arr) {
            buckets[(i - minValue) / arr.length].add(i);
        }

        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int i : bucket) {
                arr[index++] = i;
            }
        }
    }

    // Swap helper function
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Print time and space complexities
    public static void printComplexities(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Bubble Sort Time Complexity: O(n^2)");
                System.out.println("Bubble Sort Space Complexity: O(1)");
                break;
            case 2:
                System.out.println("Selection Sort Time Complexity: O(n^2)");
                System.out.println("Selection Sort Space Complexity: O(1)");
                break;
            case 3:
                System.out.println("Insertion Sort Time Complexity: O(n^2)");
                System.out.println("Insertion Sort Space Complexity: O(1)");
                break;
            case 4:
                System.out.println("Quick Sort Time Complexity: O(n log n) on average, O(n^2) worst case");
                System.out.println("Quick Sort Space Complexity: O(log n) due to recursion");
                break;
            case 5:
                System.out.println("Merge Sort Time Complexity: O(n log n)");
                System.out.println("Merge Sort Space Complexity: O(n)");
                break;
            case 6:
                System.out.println("Heap Sort Time Complexity: O(n log n)");
                System.out.println("Heap Sort Space Complexity: O(1)");
                break;
            case 7:
                System.out.println("Radix Sort Time Complexity: O(nk), where k is the number of digits");
                System.out.println("Radix Sort Space Complexity: O(n + k)");
                break;
            case 8:
                System.out.println("Shell Sort Time Complexity: O(n^2) in worst case, O(n log n) in best case");
                System.out.println("Shell Sort Space Complexity: O(1)");
                break;
            case 9:
                System.out.println("Counting Sort Time Complexity: O(n + k), where k is the range of the input");
                System.out.println("Counting Sort Space Complexity: O(k)");
                break;
            case 10:
                System.out.println("Bucket Sort Time Complexity: O(n + k), where k is the number of buckets");
                System.out.println("Bucket Sort Space Complexity: O(n + k)");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take input from the user
        System.out.println("Enter the size of the array:");
        int size = sc.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        // Menu for different sorting algorithms
        System.out.println("Select Sorting Algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Quick Sort");
        System.out.println("5. Merge Sort");
        System.out.println("6. Heap Sort");
        System.out.println("7. Radix Sort");
        System.out.println("8. Shell Sort");
        System.out.println("9. Counting Sort");
        System.out.println("10. Bucket Sort");

        int choice = sc.nextInt();
        int[] arrCopy = Arrays.copyOf(arr, arr.length);

        switch (choice) {
            case 1:
                bubbleSort(arr);
                break;
            case 2:
                selectionSort(arr);
                break;
            case 3:
                insertionSort(arr);
                break;
            case 4:
                quickSort(arr, 0, arr.length - 1);
                break;
            case 5:
                mergeSort(arr, 0, arr.length - 1);
                break;
            case 6:
                heapSort(arr);
                break;
            case 7:
                radixSort(arr);
                break;
            case 8:
                shellSort(arr);
                break;
            case 9:
                countingSort(arr);
                break;
            case 10:
                bucketSort(arr);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        // Print sorted array and complexities
        printArray(arr);
        printComplexities(choice);
    }
}
