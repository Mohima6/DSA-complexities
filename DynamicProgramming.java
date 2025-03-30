package TimeAndSpace.DSA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DynamicProgramming {

    // Fibonacci using Dynamic Programming (Memoization)
    public static int fibonacciMemoization(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != -1) {
            return memo[n];  // Return already computed result
        }
        memo[n] = fibonacciMemoization(n - 1, memo) + fibonacciMemoization(n - 2, memo);  // Recursive call
        return memo[n];
    }

    // Fibonacci using Dynamic Programming (Tabulation)
    public static int fibonacciTabulation(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Knapsack Problem (0/1 Knapsack)
    public static int knapsack(int[] weights, int[] values, int capacity, int n) {
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    // Longest Common Subsequence (LCS)
    public static int longestCommonSubsequences(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    // Longest Increasing Subsequence (LIS)
    public static int longestIncreasingSubsequenced(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;  // Initialize each element to 1
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // Matrix Chain Multiplication (Optimal Parenthesization)
    public static int matrixChainMultiplication(int[] dims) {
        int n = dims.length;
        int[][] dp = new int[n - 1][n - 1];
        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = dp[i][k] + dp[k + 1][j] + dims[i - 1] * dims[k] * dims[j];
                    dp[i][j] = Math.min(dp[i][j], q);
                }
            }
        }
        return dp[1][n - 2];
    }

    // Edit Distance (Levenshtein Distance)
    public static int editDistances(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[m][n];
    }


    // Word Break Problem
        public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;  // Empty string can always be segmented

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
            if (dp[j] && wordSet.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
                }
            }
        }
        return dp[s.length()];
    }


    // Rod Cutting Problem
public static int rodCutting(int[] lengths, int[] prices, int n) {
    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < lengths.length; j++) {
            if (lengths[j] <= i) {
                dp[i] = Math.max(dp[i], prices[j] + dp[i - lengths[j]]);
            }
        }
    }
    return dp[n];
}
    
    
    //minimum coins

    public static int coinChanged(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (i >= coin) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    return dp[amount] > amount ? -1 : dp[amount];
}

 

//Palindrome Partitioning
public static int minPalindromeCuts(String s) {
    int n = s.length();
    boolean[][] isPalindrome = new boolean[n][n];
    int[] dp = new int[n];

    for (int len = 1; len <= n; len++) {
        for (int i = 0; i <= n - len; i++) {
            int j = i + len - 1;
            if (s.charAt(i) == s.charAt(j)) {
                isPalindrome[i][j] = len == 1 || isPalindrome[i + 1][j - 1];
            }
        }
    }

    for (int i = 0; i < n; i++) {
        if (isPalindrome[0][i]) {
            dp[i] = 0;
        } else {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
    }
    return dp[n - 1];
}




//Maximum Subarray Problem(kadane's algorithm)
public static int maxSubArray(int[] nums) {
    int maxSoFar = nums[0];
    int maxEndingHere = nums[0];

    for (int i = 1; i < nums.length; i++) {
        maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
        maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }
    return maxSoFar;
}


//edit distance problem
public static int editDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];

    for (int i = 0; i <= word1.length(); i++) {
        for (int j = 0; j <= word2.length(); j++) {
            if (i == 0) {
                dp[i][j] = j;
            } else if (j == 0) {
                dp[i][j] = i;
            } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
    }
    return dp[word1.length()][word2.length()];
}


// Shortest Path in a Grid (BFS)
public static int shortestPathInGrid(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    // If starting or ending cell is blocked, return -1
    if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1) {
        return -1;
    }

    // Directions array for moving up, down, left, right
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // Queue for BFS
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{0, 0, 1}); // {row, col, path_length}


    // Mark the starting cell as visited
    grid[0][0] = 1;

    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int x = current[0], y = current[1], pathLength = current[2];

        // If we've reached the bottom-right corner, return the path length
        if (x == rows - 1 && y == cols - 1) {
            return pathLength;
        }

        // Explore all possible moves
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            // Check bounds and if the cell is unvisited (value 0)
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 0) {
                queue.add(new int[]{newX, newY, pathLength + 1});
                grid[newX][newY] = 1; // Mark the cell as visited
            }
        }
    }

    // If no path exists, return -1
    return -1;
}




    // Main method to test dynamic programming problems
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose a dynamic programming problem:");
        System.out.println("1. Fibonacci (Memoization)");
        System.out.println("2. Fibonacci (Tabulation)");
        System.out.println("3. Knapsack Problem");
        System.out.println("4. Longest Common Subsequence");
        System.out.println("5. Longest Increasing Subsequence");
        System.out.println("6. Coin Change Problem");
        System.out.println("7. Matrix Chain Multiplication");
        System.out.println("8. Edit Distance");
        System.out.println("9. Word Break Problem");
        System.out.println("10. Rod Cutting Problem");
        System.out.println("11. Palindrome Partitioning");
        System.out.println("12. Maximum Subarray Problem");
        System.out.println("13. Edit Distance Problem");


        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter the number for Fibonacci (Memoization):");
                int n1 = sc.nextInt();
                int[] memo1 = new int[n1 + 1];
                for (int i = 0; i <= n1; i++) {
                    memo1[i] = -1;
                }
                System.out.println("Fibonacci (Memoization): " + fibonacciMemoization(n1, memo1));
                break;

            case 2:
                System.out.println("Enter the number for Fibonacci (Tabulation):");
                int n2 = sc.nextInt();
                System.out.println("Fibonacci (Tabulation): " + fibonacciTabulation(n2));
                break;

            case 3:
                System.out.println("Enter number of items and capacity:");
                int n3 = sc.nextInt();
                int capacity = sc.nextInt();
                int[] weights = new int[n3];
                int[] values = new int[n3];
                System.out.println("Enter weights:");
                for (int i = 0; i < n3; i++) {
                    weights[i] = sc.nextInt();
                }
                System.out.println("Enter values:");
                for (int i = 0; i < n3; i++) {
                    values[i] = sc.nextInt();
                }
                System.out.println("Knapsack result: " + knapsack(weights, values, capacity, n3));
                break;


                case 4:
                // Longest Increasing Subsequence
                System.out.println("Enter the size of the array:");
                int lisSize = sc.nextInt();
                int[] lisArr = new int[lisSize];
                System.out.println("Enter the elements:");
                for (int i = 0; i < lisSize; i++) {
                    lisArr[i] = sc.nextInt();
                }
                System.out.println("Longest Increasing Subsequence Length: " + longestIncreasingSubsequenced(lisArr));
                break;
            
                case 5:
                // Longest Common Subsequence
                System.out.println("Enter the first string:");
                String text1 = sc.next();
                System.out.println("Enter the second string:");
                String text2 = sc.next();
                System.out.println("Length of Longest Common Subsequence: " + longestCommonSubsequences(text1, text2));
                break;


            case 6:
                System.out.println("Enter coins and amount for Coin Change:");
                int n5 = sc.nextInt();
                int amount = sc.nextInt();
                int[] coins = new int[n5];
                System.out.println("Enter coin values:");
                for (int i = 0; i < n5; i++) {
                    coins[i] = sc.nextInt();
                }
                System.out.println("Coin change result: " + coinChanged(coins, amount));
                break;

            case 7:
                System.out.println("Enter matrix dimensions for Matrix Chain Multiplication:");
                int n6 = sc.nextInt();
                int[] dims = new int[n6];
                System.out.println("Enter dimensions:");
                for (int i = 0; i < n6; i++) {
                    dims[i] = sc.nextInt();
                }
                System.out.println("Matrix chain multiplication result: " + matrixChainMultiplication(dims));
                break;

            case 8:
                System.out.println("Enter two strings for Edit Distance:");
                String str1 = sc.next();
                String str2 = sc.next();
                System.out.println("Edit Distance: " + editDistance(str1, str2));
                break;

            case 9:
                System.out.println("Enter the string for Word Break:");
                String word = sc.next();
                System.out.println("Enter the number of words in the dictionary:");
                int dictSize = sc.nextInt();
                List<String> wordDict = new ArrayList<>();
                System.out.println("Enter the words in the dictionary:");
                for (int i = 0; i < dictSize; i++) {
                wordDict.add(sc.next());
                }
                System.out.println("Word Break Result: " + wordBreak(word, wordDict));
                break;

            case 10:
                System.out.println("Enter the length of the rod:");
                int rodLength = sc.nextInt();
                System.out.println("Enter the number of available cuts:");
                int cuts = sc.nextInt();
                int[] lengths = new int[cuts];
                int[] prices = new int[cuts];
                System.out.println("Enter the lengths:");
                for (int i = 0; i < cuts; i++) {
                lengths[i] = sc.nextInt();
                }
                System.out.println("Enter the prices:");
                for (int i = 0; i < cuts; i++) {
                prices[i] = sc.nextInt();
                }
                System.out.println("Rod Cutting Result: " + rodCutting(lengths, prices, rodLength));
                break;

            
                
                case 11:
                // Shortest Path in a Grid
                System.out.println("Enter the number of rows:");
                int rows = sc.nextInt();
                System.out.println("Enter the number of columns:");
                int cols = sc.nextInt();

                int[][] grid = new int[rows][cols];
                System.out.println("Enter the grid (0 for open, 1 for blocked):");
                for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                grid[i][j] = sc.nextInt();
                    }
                }

                int shortestPath = shortestPathInGrid(grid);
                if (shortestPath == -1) {
                System.out.println("No valid path exists in the grid.");
                } else {
                System.out.println("Shortest Path Length: " + shortestPath);
                }
                break;



                case 12:
                // Maximum Subarray Problem
                System.out.println("Enter the size of the array:");
                int arraySize = sc.nextInt();
                int[] array = new int[arraySize];
                System.out.println("Enter the elements of the array:");
                for (int i = 0; i < arraySize; i++) {
                array[i] = sc.nextInt();
                 }
                System.out.println("Maximum Subarray Sum: " + maxSubArray(array));
                break;


                case 13:
                // Edit Distance Problem
                System.out.println("Enter the first word:");
                String word1 = sc.next();
                System.out.println("Enter the second word:");
                String word2 = sc.next();
                System.out.println("Minimum Edit Distance: " + editDistance(word1, word2));
                break;

            
            default:
            System.out.println("Invalid choice.");
        }
    } 
         
}
 



            
