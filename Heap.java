package TimeAndSpace.DSA;

import java.util.Scanner;

public class Heap {

    private int[] heapArray;
    private int size;
    private int capacity;
    private boolean isMaxHeap;  // Flag to determine if the heap is max heap or min heap

    // Constructor to initialize the heap with a specific capacity
    public Heap(int capacity, boolean isMaxHeap) {
        this.capacity = capacity;
        this.size = 0;
        this.isMaxHeap = isMaxHeap;
        heapArray = new int[capacity];
    }

    // Helper method to get the parent index of a node
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Helper method to get the left child index of a node
    private int leftChild(int index) {
        return (2 * index) + 1;
    }

    // Helper method to get the right child index of a node
    private int rightChild(int index) {
        return (2 * index) + 2;
    }

    // Helper method to compare two values based on heap type
    private boolean compare(int child, int parent) {
        return isMaxHeap ? child > parent : child < parent;
    }

    // Method to insert a value into the heap
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full! Cannot insert new element.");
            return;
        }
        heapArray[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    // Method to heapify up after inserting a new element
    private void heapifyUp(int index) {
        while (index != 0 && compare(heapArray[index], heapArray[parent(index)])) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    // Method to remove the root element (Max or Min) depending on the heap type
    public int extract() {
        if (size <= 0) {
            return Integer.MIN_VALUE;
        }
        if (size == 1) {
            size--;
            return heapArray[0];
        }

        int root = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        size--;
        heapifyDown(0);

        return root;
    }

    // Method to heapify down after extracting the root element
    private void heapifyDown(int index) {
        int left = leftChild(index);
        int right = rightChild(index);
        int extremum = index;

        if (left < size && compare(heapArray[left], heapArray[extremum])) {
            extremum = left;
        }
        if (right < size && compare(heapArray[right], heapArray[extremum])) {
            extremum = right;
        }
        if (extremum != index) {
            swap(index, extremum);
            heapifyDown(extremum);
        }
    }

    // Method to display the heap
    public void display() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return;
        }
        System.out.print("Current Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heapArray[i] + " ");
        }
        System.out.println();
    }

    // Method to swap two elements in the heap
    private void swap(int index1, int index2) {
        int temp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = temp;
    }

    // Method to get the root element
    public int getRoot() {
        if (size > 0) {
            return heapArray[0];
        }
        return Integer.MIN_VALUE;
    }

    // Method to check if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Main method to interact with the user
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user whether they want a Max-Heap or Min-Heap
        System.out.println("Welcome to the Heap Operations Program!");
        System.out.println("==========================================");
        System.out.println("Please choose the type of heap:");
        System.out.println("1. Max-Heap");
        System.out.println("2. Min-Heap");

        int choice;
        do {
            System.out.print("Enter your choice (1 or 2): ");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);

        boolean isMaxHeap = (choice == 1);

        // Ask for the capacity of the heap
        System.out.print("Enter the capacity of the heap: ");
        int capacity = scanner.nextInt();

        // Create the heap instance
        Heap heap = new Heap(capacity, isMaxHeap);

        // Loop to allow the user to perform heap operations
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Insert");
            System.out.println("2. Extract Root");
            System.out.println("3. Display Heap");
            System.out.println("4. Get Root");
            System.out.println("5. Check if Heap is Empty");
            System.out.println("6. Exit");

            System.out.print("Enter your choice (1-6): ");
            int operation = scanner.nextInt();

            switch (operation) {
                case 1: {
                    // Insert element into the heap
                    System.out.print("Enter value to insert: ");
                    int value = scanner.nextInt();
                    heap.insert(value);
                    System.out.println("Element " + value + " inserted.");
                    break;
                }
                case 2: {
                    // Extract the root element
                    int extracted = heap.extract();
                    if (extracted == Integer.MIN_VALUE) {
                        System.out.println("Heap is empty.");
                    } else {
                        System.out.println("Extracted root: " + extracted);
                    }
                    break;
                }
                case 3: {
                    // Display the current heap
                    heap.display();
                    break;
                }
                case 4: {
                    // Get the root element
                    int root = heap.getRoot();
                    if (root == Integer.MIN_VALUE) {
                        System.out.println("Heap is empty.");
                    } else {
                        System.out.println("Root of the heap: " + root);
                    }
                    break;
                }
                case 5: {
                    // Check if the heap is empty
                    if (heap.isEmpty()) {
                        System.out.println("Heap is empty.");
                    } else {
                        System.out.println("Heap is not empty.");
                    }
                    break;
                }
                case 6: {
                    // Exit the program
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                }
                default: {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
