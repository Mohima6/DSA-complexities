package TimeAndSpace.DSA;

import java.util.Scanner;
import java.util.InputMismatchException;

class Stack {
    private int[] stack;
    private int top;
    private int capacity;

    // Constructor to initialize the stack with an initial capacity
    public Stack(int initialCapacity) {
        capacity = initialCapacity;
        stack = new int[capacity];
        top = -1; // Stack is initially empty
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Method to check if the stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Method to increase the stack size when it is full
    private void resize() {
        int newCapacity = capacity * 2;
        int[] newStack = new int[newCapacity];
        System.arraycopy(stack, 0, newStack, 0, capacity);
        stack = newStack;
        capacity = newCapacity;
        System.out.println("Stack capacity increased to " + newCapacity);
    }

    // Method to push an element into the stack
    public void push(int value) {
        if (isFull()) {
            resize(); // Increase capacity if the stack is full
        }
        stack[++top] = value;
        System.out.println("Pushed " + value + " into the stack.");
    }

    // Method to pop an element from the stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Cannot pop.");
            return -1; // Return an invalid value when the stack is empty
        }
        int poppedValue = stack[top--];
        return poppedValue;
    }

    // Method to peek at the top element of the stack
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Nothing to peek.");
            return -1; // Return an invalid value when the stack is empty
        }
        return stack[top];
    }

    // Method to get the size of the stack
    public int size() {
        return top + 1;
    }

    // Method to display the stack contents
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
        } else {
            System.out.print("Stack elements: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i] + " ");
            }
            System.out.println();
        }
    }
}

public class StackDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user to enter the initial size of the stack
        int size = 0;
        while (size <= 0) {
            try {
                System.out.print("Enter a positive size for the stack: ");
                size = scanner.nextInt();
                if (size <= 0) {
                    System.out.println("Please enter a valid positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Create a stack object with the specified size
        Stack stack = new Stack(size);

        while (true) {
            // Menu for user input
            System.out.println("\nChoose an operation:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Size");
            System.out.println("5. Display Stack");
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
                    // Push an element
                    System.out.print("Enter a value to push: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case 2:
                    // Pop an element
                    int poppedValue = stack.pop();
                    if (poppedValue != -1) {
                        System.out.println("Popped: " + poppedValue);
                    }
                    break;
                case 3:
                    // Peek at the top element
                    int peekedValue = stack.peek();
                    if (peekedValue != -1) {
                        System.out.println("Top element: " + peekedValue);
                    }
                    break;
                case 4:
                    // Get size of the stack
                    System.out.println("Stack size: " + stack.size());
                    break;
                case 5:
                    // Display the stack contents
                    stack.display();
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
