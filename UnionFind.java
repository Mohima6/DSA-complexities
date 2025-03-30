package TimeAndSpace.DSA;

import java.util.*;

public class UnionFind {
    private int[] parent;
    private int[] size;

    // Constructor: initializes the Union-Find data structure
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];

        // Initially, each node is its own parent and has size 1
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Find operation with path compression
    public int find(int p) {
        if (parent[p] != p) {
            parent[p] = find(parent[p]); // Path compression
        }
        return parent[p];
    }

    // Union operation with union by size
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        // If they are already in the same set, do nothing
        if (rootP == rootQ) return;

        // Union by size: attach the smaller tree under the larger tree
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    // Check if two elements are in the same set
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Optional: For debugging purposes, print the parent array
    public void printParent() {
        System.out.println("Parent array: " + Arrays.toString(parent));
    }

    // Optional: For debugging purposes, print the size array
    public void printSize() {
        System.out.println("Size array: " + Arrays.toString(size));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        UnionFind uf = new UnionFind(n);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Union (p, q)");
            System.out.println("2. Find (p)");
            System.out.println("3. Check if connected (p, q)");
            System.out.println("4. Print Parent Array");
            System.out.println("5. Print Size Array");
            System.out.println("6. Exit");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter two elements to union (p q): ");
                    int p = scanner.nextInt();
                    int q = scanner.nextInt();
                    uf.union(p, q);
                    System.out.println("Union completed.");
                    break;

                case 2:
                    System.out.print("Enter the element to find its root (p): ");
                    p = scanner.nextInt();
                    int root = uf.find(p);
                    System.out.println("Root of " + p + " is " + root);
                    break;

                case 3:
                    System.out.print("Enter two elements to check if connected (p q): ");
                    p = scanner.nextInt();
                    q = scanner.nextInt();
                    boolean isConnected = uf.connected(p, q);
                    System.out.println("Are " + p + " and " + q + " connected? " + isConnected);
                    break;

                case 4:
                    uf.printParent();
                    break;

                case 5:
                    uf.printSize();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
