package TimeAndSpace.DSA;

import java.util.Scanner;
import java.util.InputMismatchException;

class Queue {
    private int[] queue;
    private int front, rear, capacity, size;

    // Constructor to initialize the queue with an initial capacity
    public Queue(int initialCapacity) {
        capacity = initialCapacity;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to check if the queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Method to resize the queue when it is full
    private void resize() {
        int newCapacity = capacity * 2;
        int[] newQueue = new int[newCapacity];
        
        // Copy elements from the old queue to the new queue
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % capacity];
        }
        
        queue = newQueue;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
        
        System.out.println("Queue capacity increased to " + newCapacity);
    }

    // Method to enqueue an element
    public void enqueue(int value) {
        if (isFull()) {
            resize(); // Resize the queue if it's full
        }
        rear = (rear + 1) % capacity; // Circular increment
        queue[rear] = value;
        size++;
        System.out.println("Enqueued " + value + " into the queue.");
    }

    // Method to dequeue an element
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot dequeue.");
            return -1; // Return an invalid value when the queue is empty
        }
        int dequeuedValue = queue[front];
        front = (front + 1) % capacity; // Circular increment
        size--;
        return dequeuedValue;
    }

    // Method to peek at the front element of the queue
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Nothing to peek.");
            return -1; // Return an invalid value when the queue is empty
        }
        return queue[front];
    }

    // Method to get the size of the queue
    public int size() {
        return size;
    }

    // Method to display the queue contents
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
        } else {
            System.out.print("Queue elements: ");
            for (int i = 0; i < size; i++) {
                System.out.print(queue[(front + i) % capacity] + " ");
            }
            System.out.println();
        }
    }
}

public class QueueDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user to enter the initial size of the queue
        int size = 0;
        while (size <= 0) {
            try {
                System.out.print("Enter a positive size for the queue: ");
                size = scanner.nextInt();
                if (size <= 0) {
                    System.out.println("Please enter a valid positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Create a queue object with the specified size
        Queue queue = new Queue(size);

        while (true) {
            // Menu for user input
            System.out.println("\nChoose an operation:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Size");
            System.out.println("5. Display Queue");
            System.out.println("6. Exit");

            int choice = 0;
            while (choice < 1 || choice > 6) {
                try {
                    System.out.print("Enter your choice (1-6): ");
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 6) {
                        System.out.println("Invalid choice! Please select a number between 1 and 6.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                }
            }

            switch (choice) {
                case 1:
                    // Enqueue an element
                    System.out.print("Enter a value to enqueue: ");
                    int value = scanner.nextInt();
                    queue.enqueue(value);
                    break;
                case 2:
                    // Dequeue an element
                    int dequeuedValue = queue.dequeue();
                    if (dequeuedValue != -1) {
                        System.out.println("Dequeued: " + dequeuedValue);
                    }
                    break;
                case 3:
                    // Peek at the front element
                    int peekedValue = queue.peek();
                    if (peekedValue != -1) {
                        System.out.println("Front element: " + peekedValue);
                    }
                    break;
                case 4:
                    // Get size of the queue
                    System.out.println("Queue size: " + queue.size());
                    break;
                case 5:
                    // Display the queue contents
                    queue.display();
                    break;
                case 6:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
