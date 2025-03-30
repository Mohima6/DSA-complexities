package TimeAndSpace.DSA;

import java.util.Scanner;
import java.util.Arrays;

public class Array {
     //method to traverse & print the array
     public static void traverse(int[] arr) {
        System.out.print("Array: ");
        for(int num : arr){
            System.out.print(num + " ");
        }
        System.out.println();
     }


     //method to insert
public static int[] insertElement(int[] arr, int element, int position) {
int[] newArr = new int[arr.length + 1];
for (int i = 0, j = 0; i < arr.length; i++, j++) { // Initialize i and j correctly
if (j == position) {
newArr[j++] = element; // Insert the new element and increment j
    }
newArr[j] = arr[i]; // Copy elements from the original array
    }
    
// If the position is at the end of the array
if (position >= arr.length) {
newArr[arr.length] = element;
    }
    
   return newArr;
    }

    

//method to delete
public static int[] deleteElement(int[] arr, int position) {
    if (position < 0 || position >= arr.length) {
        System.out.println("Invalid position!");
        return arr; // Return the same array if the position is invalid
    }

    int[] newArr = new int[arr.length - 1];
    for (int i = 0, j = 0; i < arr.length; i++) { // Initialize i and j correctly
        if (i == position) {
            continue; // Skip the element at the specified position
        }
        newArr[j++] = arr[i]; // Copy elements except the one at 'position'
    }
    return newArr;
}


//method to reverse the array
public static int[] reverse (int[]arr){
    int[] newArr = new int[arr.length];
    int j = 0;
    for (int i = arr.length - 1; i >= 0 ; i--) {
        newArr[j++] = arr[i];
    }
    return newArr;
}

//method to rotate left

public static int[] rotateLeft(int[] arr, int d) {
    int[] newArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) { // Use arr.length instead of array.length
        newArr[i] = arr[(i + d) % arr.length];
    }
    return newArr;
}


//rotate right
public static int[] rotateRight(int[] arr, int d){
    int[] newArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
        newArr[i] = arr[(i - d + arr.length) % arr.length];
    }
    return newArr;
}

//method to merge two arrays
public static int[] mergeArrays(int[] arr1, int[] arr2){
    int[] newArr = new int[arr1.length + arr2.length];
    System.arraycopy(arr1, 0, newArr, 0, arr1.length);
    System.arraycopy(arr2, 0, newArr, arr1.length, arr2.length);
    return newArr;
}

//method to sort the array
public static void sortArray(int[] arr) {
    Arrays.sort(arr);
}

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    //initialize the array
    System.out.print("Enter size of array:");
    int n = scanner.nextInt();

    int[] arr = new int[n];
    System.out.println("Enter elements of array:");
    for (int i = 0; i < n; i++) {
        arr[i] = scanner.nextInt();
    } 

    while (true) {
        System.out.println("\nSelect an operation:");
        System.out.println("1. Traverse array");
        System.out.println("2. Insert an element");
        System.out.println("3. Delete an element");
        System.out.println("4. Reverse array");
        System.out.println("5. Rotate array left");
        System.out.println("6. Rotate array right");
        System.out.println("7. Merge arrays");
        System.out.println("8. Sort array");
        System.out.println("9. Exit");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();


        switch (choice) {
            case 1:
            traverse(arr);   
            break;
        
            case 2:
            System.out.print("Enter element to insert: ");
            int element = scanner.nextInt();
            System.out.print("Enter position: ");
            int position = scanner.nextInt();

            arr = insertElement(arr, element, position);
            traverse(arr);
            break;

            
            case 3:
            System.out.print("Enter position to delete: ");
            int deletePos = scanner.nextInt();
            arr = deleteElement(arr, deletePos);
            traverse(arr);
            break;

            
            case 4:
            arr = reverse(arr);
            traverse(arr);
            break;


            case 5:
            System.out.print("Enter number of positions to rotate left: ");
            int leftRot = scanner.nextInt();
            arr = rotateLeft(arr, leftRot);
            traverse(arr);
            break;


            case 6:
            System.out.print("Enter number of position to rotate right: ");
            int rightRot = scanner.nextInt();
            arr = rotateRight(arr, rightRot);
            traverse(arr);
            break;


            case 7:
            System.out.print("Enter size of second array: ");
            int m = scanner.nextInt();
            int[] arr2 = new int[m];
            System.out.println("Enter elements of the second array: ");
            for (int i = 0; i < m; i++) {
                arr2[i] = scanner.nextInt();

            }

            arr = mergeArrays(arr,arr2);
            traverse(arr);
            break;

            
            case 8:
            sortArray(arr);
            System.out.println("Array sorted in ascending order.");
            traverse(arr);
            break;


            case 9:
            System.out.println("Exiting...see u soon!");
            scanner.close();
            return;



            default:
            System.out.println("Invalid choice! Try again, dear!");
            
             }
        }
    }    
}


//done