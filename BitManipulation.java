package TimeAndSpace.DSA;

import java.util.Scanner;

public class BitManipulation {

    // Check if a number is even or odd using bitwise operators
    public static void checkEvenOdd(int num) {
        if ((num & 1) == 0) {
            System.out.println(num + " is even.");
        } else {
            System.out.println(num + " is odd.");
        }
    }

    // Count the number of set bits (1s) in a number using Brian Kernighan's algorithm
    public static void countSetBits(int num) {
        int count = 0;
        while (num > 0) {
            num = num & (num - 1);  // Remove the rightmost set bit
            count++;
        }
        System.out.println("Number of set bits: " + count);
    }

    // Flip all the bits of a number
    public static void flipBits(int num) {
        int flipped = ~num;
        System.out.println("Flipped bits: " + flipped);
    }

    // Check if a number is a power of 2
    public static void checkPowerOfTwo(int num) {
        if ((num & (num - 1)) == 0) {
            System.out.println(num + " is a power of 2.");
        } else {
            System.out.println(num + " is not a power of 2.");
        }
    }

    // Toggle the nth bit of a number
    public static void toggleBit(int num, int n) {
        int toggled = num ^ (1 << n);
        System.out.println("Number after toggling bit " + n + ": " + toggled);
    }

    // Set the nth bit of a number
    public static void setBit(int num, int n) {
        int set = num | (1 << n);
        System.out.println("Number after setting bit " + n + ": " + set);
    }

    // Clear the nth bit of a number
    public static void clearBit(int num, int n) {
        int cleared = num & ~(1 << n);
        System.out.println("Number after clearing bit " + n + ": " + cleared);
    }

    // Swap two numbers using XOR
    public static void swapNumbers(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("Swapped numbers: a = " + a + ", b = " + b);
    }

    // Get the bit at the nth position
    public static void getBitAtPosition(int num, int n) {
        int bit = (num >> n) & 1;
        System.out.println("Bit at position " + n + ": " + bit);
    }

    // Find the most significant set bit
    public static void mostSignificantBit(int num) {
        int msb = 31 - Integer.numberOfLeadingZeros(num);
        System.out.println("Most significant bit is at position: " + msb);
    }

    // Find the rightmost set bit
    public static void findRightmostSetBit(int num) {
        int rightmostSetBit = num & -num;
        System.out.println("Rightmost set bit: " + rightmostSetBit);
    }

    // Clear the rightmost set bit
    public static void clearRightmostSetBit(int num) {
        int cleared = num & (num - 1);
        System.out.println("After clearing the rightmost set bit: " + cleared);
    }

    // Left shift a number by n bits
    public static void leftShift(int num, int n) {
        int shifted = num << n;
        System.out.println("After left shifting by " + n + " bits: " + shifted);
    }

    // Right shift a number by n bits
    public static void rightShift(int num, int n) {
        int shifted = num >> n;
        System.out.println("After right shifting by " + n + " bits: " + shifted);
    }

    // Rotate the bits of a number to the left by n bits
    public static void rotateLeft(int num, int n) {
        int result = (num << n) | (num >> (32 - n));
        System.out.println("After rotating left by " + n + " bits: " + result);
    }

    // Rotate the bits of a number to the right by n bits
    public static void rotateRight(int num, int n) {
        int result = (num >> n) | (num << (32 - n));
        System.out.println("After rotating right by " + n + " bits: " + result);
    }

    // Check if two numbers have opposite signs
    public static void checkOppositeSigns(int num1, int num2) {
        if ((num1 ^ num2) < 0) {
            System.out.println("The numbers have opposite signs.");
        } else {
            System.out.println("The numbers have the same sign.");
        }
    }

    // Find the number of trailing zeros in the binary representation of a number
    public static void countTrailingZeros(int num) {
        if (num == 0) {
            System.out.println("Number has infinite trailing zeros.");
        } else {
            int count = 0;
            while ((num & 1) == 0) {
                count++;
                num >>= 1;
            }
            System.out.println("Trailing zeros: " + count);
        }
    }

    // Main method to test the bit manipulation operations
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a number:");
        int num = sc.nextInt();

        System.out.println("Enter the position of the bit (for set/clear/toggle operations):");
        int n = sc.nextInt();

        System.out.println("Select an option:");
        System.out.println("1. Check Even/Odd");
        System.out.println("2. Count Set Bits");
        System.out.println("3. Flip Bits");
        System.out.println("4. Check Power of 2");
        System.out.println("5. Toggle Bit");
        System.out.println("6. Set Bit");
        System.out.println("7. Clear Bit");
        System.out.println("8. Swap Numbers");
        System.out.println("9. Get Bit at Position");
        System.out.println("10. Find Most Significant Bit");
        System.out.println("11. Find Rightmost Set Bit");
        System.out.println("12. Clear Rightmost Set Bit");
        System.out.println("13. Left Shift");
        System.out.println("14. Right Shift");
        System.out.println("15. Rotate Left");
        System.out.println("16. Rotate Right");
        System.out.println("17. Check if Two Numbers Have Opposite Signs");
        System.out.println("18. Count Trailing Zeros");

        int option = sc.nextInt();

        switch (option) {
            case 1:
                checkEvenOdd(num);
                break;
            case 2:
                countSetBits(num);
                break;
            case 3:
                flipBits(num);
                break;
            case 4:
                checkPowerOfTwo(num);
                break;
            case 5:
                toggleBit(num, n);
                break;
            case 6:
                setBit(num, n);
                break;
            case 7:
                clearBit(num, n);
                break;
            case 8:
                System.out.println("Enter another number to swap:");
                int b = sc.nextInt();
                swapNumbers(num, b);
                break;
            case 9:
                getBitAtPosition(num, n);
                break;
            case 10:
                mostSignificantBit(num);
                break;
            case 11:
                findRightmostSetBit(num);
                break;
            case 12:
                clearRightmostSetBit(num);
                break;
            case 13:
                System.out.println("Enter number of positions to left shift:");
                int leftShiftBy = sc.nextInt();
                leftShift(num, leftShiftBy);
                break;
            case 14:
                System.out.println("Enter number of positions to right shift:");
                int rightShiftBy = sc.nextInt();
                rightShift(num, rightShiftBy);
                break;
            case 15:
                System.out.println("Enter number of positions to rotate left:");
                int rotateLeftBy = sc.nextInt();
                rotateLeft(num, rotateLeftBy);
                break;
            case 16:
                System.out.println("Enter number of positions to rotate right:");
                int rotateRightBy = sc.nextInt();
                rotateRight(num, rotateRightBy);
                break;
            case 17:
                System.out.println("Enter another number to check signs:");
                int num2 = sc.nextInt();
                checkOppositeSigns(num, num2);
                break;
            case 18:
                countTrailingZeros(num);
                break;
            default:
                System.out.println("Invalid option!");
        }

        sc.close();
    }
}
