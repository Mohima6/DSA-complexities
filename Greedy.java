package TimeAndSpace.DSA;

import java.util.*;

public class Greedy {

    // Coin Change Problem: Minimum Coins
    public static void coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                count++;
            }
        }
        if (amount == 0) {
            System.out.println("Minimum coins required: " + count);
        } else {
            System.out.println("Change not possible with given coins.");
        }
    }

    // Kruskal’s Algorithm: Minimum Spanning Tree
    static class Edge {
        int src, dest, weight;
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Subset {
        int parent, rank;
    }

    public static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    public static void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public static void kruskalMST(int v, List<Edge> edges) {
        Collections.sort(edges, Comparator.comparingInt(o -> o.weight));
        Subset[] subsets = new Subset[v];
        for (int i = 0; i < v; i++)
            subsets[i] = new Subset();
        for (int i = 0; i < v; i++) {
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            int x = find(subsets, edge.src);
            int y = find(subsets, edge.dest);
            if (x != y) {
                mst.add(edge);
                union(subsets, x, y);
            }
        }

        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mst)
            System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
    }

    // Dijkstra’s Algorithm: Shortest Path
    public static void dijkstra(int[][] graph, int start) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("Shortest Path from node " + start + ":");
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.println("Node " + i + " is not reachable.");
            else
                System.out.println("Distance from " + start + " to " + i + ": " + dist[i]);
        }
    }

    private static int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Activity Selection Problem
    public static void activitySelection(int[] start, int[] finish) {
        int n = start.length;
        System.out.println("Selected activities:");
        int i = 0;
        System.out.print(i + " ");
        for (int j = 1; j < n; j++) {
            if (start[j] >= finish[i]) {
                System.out.print(j + " ");
                i = j;
            }
        }
        System.out.println();
    }

    // Job Sequencing Problem
    static class Job {
        int id, deadline, profit;
        public Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void jobSequencing(Job[] jobs, int n) {
        Arrays.sort(jobs, Comparator.comparingInt(o -> -o.profit));
        int[] result = new int[n];
        boolean[] slot = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(n, jobs[i].deadline) - 1; j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = jobs[i].id;
                    slot[j] = true;
                    break;
                }
            }
        }

        System.out.println("Job Sequencing with Maximum Profit:");
        for (int i = 0; i < n; i++) {
            if (result[i] != 0)
                System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    // Fractional Knapsack Problem
    public static void fractionalKnapsack(int W, int[] wt, int[] val, int n) {
        double[] ratio = new double[n];
        for (int i = 0; i < n; i++) {
            ratio[i] = (double) val[i] / wt[i];
        }

        double totalValue = 0;
        for (int i = 0; i < n; i++) {
            if (wt[i] <= W) {
                W -= wt[i];
                totalValue += val[i];
            } else {
                totalValue += val[i] * ((double) W / wt[i]);
                break;
            }
        }
        System.out.println("Maximum value in Knapsack = " + totalValue);
    }

    // Huffman Coding
    public static void huffmanCoding(char[] chars, int[] freqs) {
        int n = chars.length;
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.freq));
        for (int i = 0; i < n; i++) {
            pq.add(new HuffmanNode(chars[i], freqs[i]));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode top = new HuffmanNode('-', left.freq + right.freq);
            top.left = left;
            top.right = right;
            pq.add(top);
        }

        HuffmanNode root = pq.poll();
        printHuffmanCodes(root, "");
    }

    static class HuffmanNode {
        char ch;
        int freq;
        HuffmanNode left, right;

        public HuffmanNode(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
            left = right = null;
        }
    }

    public static void printHuffmanCodes(HuffmanNode root, String code) {
        if (root == null)
            return;
        if (root.ch != '-')
            System.out.println(root.ch + ": " + code);
        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an algorithm to run:");
        System.out.println("1. Coin Change Problem");
        System.out.println("2. Kruskal's Algorithm (MST)");
        System.out.println("3. Dijkstra's Algorithm");
        System.out.println("4. Activity Selection Problem");
        System.out.println("5. Job Sequencing Problem");
        System.out.println("6. Fractional Knapsack");
        System.out.println("7. Huffman Coding");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter amount:");
                int amount = scanner.nextInt();
                System.out.println("Enter number of coins:");
                int n = scanner.nextInt();
                int[] coins = new int[n];
                System.out.println("Enter the coins:");
                for (int i = 0; i < n; i++) {
                    coins[i] = scanner.nextInt();
                }
                coinChange(coins, amount);
                break;
            case 2:
                System.out.println("Enter number of vertices:");
                int v = scanner.nextInt();
                List<Edge> edges = new ArrayList<>();
                System.out.println("Enter the edges (src dest weight):");
                while (true) {
                    int src = scanner.nextInt();
                    int dest = scanner.nextInt();
                    int weight = scanner.nextInt();
                    edges.add(new Edge(src, dest, weight));
                    System.out.print("Do you want to add another edge? (y/n): ");
                    char ch = scanner.next().charAt(0);
                    if (ch == 'n' || ch == 'N') break;
                }
                kruskalMST(v, edges);
                break;
            case 3:
                System.out.println("Enter the number of vertices:");
                int vertices = scanner.nextInt();
                int[][] graph = new int[vertices][vertices];
                System.out.println("Enter the adjacency matrix:");
                for (int i = 0; i < vertices; i++) {
                    for (int j = 0; j < vertices; j++) {
                        graph[i][j] = scanner.nextInt();
                    }
                }
                System.out.println("Enter the source vertex:");
                int source = scanner.nextInt();
                dijkstra(graph, source);
                break;
            case 4:
                System.out.println("Enter number of activities:");
                int numActivities = scanner.nextInt();
                int[] start = new int[numActivities];
                int[] finish = new int[numActivities];
                System.out.println("Enter start and finish times for each activity:");
                for (int i = 0; i < numActivities; i++) {
                    start[i] = scanner.nextInt();
                    finish[i] = scanner.nextInt();
                }
                activitySelection(start, finish);
                break;
            case 5:
                System.out.println("Enter number of jobs:");
                int jobCount = scanner.nextInt();
                Job[] jobs = new Job[jobCount];
                System.out.println("Enter jobs (id, deadline, profit):");
                for (int i = 0; i < jobCount; i++) {
                    int id = scanner.nextInt();
                    int deadline = scanner.nextInt();
                    int profit = scanner.nextInt();
                    jobs[i] = new Job(id, deadline, profit);
                }
                jobSequencing(jobs, jobCount);
                break;
            case 6:
                System.out.println("Enter number of items:");
                int itemCount = scanner.nextInt();
                int[] wt = new int[itemCount];
                int[] val = new int[itemCount];
                System.out.println("Enter weight and value of each item:");
                for (int i = 0; i < itemCount; i++) {
                    wt[i] = scanner.nextInt();
                    val[i] = scanner.nextInt();
                }
                System.out.println("Enter knapsack capacity:");
                int W = scanner.nextInt();
                fractionalKnapsack(W, wt, val, itemCount);
                break;
            case 7:
                System.out.println("Enter number of characters:");
                int size = scanner.nextInt();
                char[] chars = new char[size];
                int[] freqs = new int[size];
                System.out.println("Enter characters and their frequencies:");
                for (int i = 0; i < size; i++) {
                    chars[i] = scanner.next().charAt(0);
                    freqs[i] = scanner.nextInt();
                }
                huffmanCoding(chars, freqs);
                break;
            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }
}
