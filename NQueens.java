package TimeAndSpace.DSA;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NQueens extends JPanel {
    private int size;
    private int[] board; 
    private List<int[]> solutions;
    private int currentSolution;

    //constructor
    public NQueens (int size) {
        this.size = size;
        this.board = new int[size];
        Arrays.fill(board,-1);
        this.solutions = new ArrayList<>();
        this.currentSolution = 0;
        solveNQueens(0);
    }

    //backtracking method to solve N-Queens problem
    private void solveNQueens(int row) {
        if (row == size) {
            //found a solution, add it to the list 
            solutions.add(board.clone());
            return;
        }

        for (int col = 0; col < size; col++) {
            if (isSafe(row, col)) {
                board[row] = col; //place queen
                solveNQueens(row + 1); //use recursion for next row
                board[row] = -1; //backtrack
            }
        }
    }

    //check if placing a queen is safe
    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false; //conflict detected
            }
        }
        return true;
    }

    //paint method to visualize the board , queens
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = Math.min(getWidth(), getHeight()) / size; //calculate cell size
        drawBoard(g, cellSize); //draw the chessboard
        if (!solutions.isEmpty()) {
            drawQueens(g,cellSize); //draw the queens
        }
    }

    //draw the chessboard
    private void drawBoard(Graphics g, int cellSize) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

    //draw queens on the board for the current solution
    private void drawQueens(Graphics g, int cellSize) {
        int[] solution = solutions.get(currentSolution);
        g.setColor(Color.RED);

    for (int row = 0; row < size; row++) {
        int col = solution[row];
    g.fillOval(col * cellSize + cellSize / 4, row * cellSize + cellSize / 4, cellSize / 2, cellSize /2);

        }
    }

    //show the next solution when click the button again
    public void showNextSolution() {
        currentSolution = (currentSolution + 1) % solutions.size();
        repaint(); //refresh gui
    }

    //main method
    public static void main(String[] args) {
        //ask user for the no. of queens
        String input = JOptionPane.showInputDialog("Enter number of Queens:");
        int n;
        try {
            n = Integer.parseInt(input);
            if (n <= 0) {
                throw new NumberFormatException("N must be greater than 0");
            }
        }  catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "invalid input; enter positive integer.");
            return;
        }

        //create the NQueens panel with user input size
        JFrame frame = new JFrame("N-Queens Visualizer");
        NQueens nQueensPanel = new NQueens(n);

        //create a button to cycle through solution
        JButton nextButton = new JButton("Next Solution");
        nextButton.addActionListener(e -> nQueensPanel.showNextSolution());

        //setup gui
        frame.setLayout(new BorderLayout());
        frame.add(nQueensPanel, BorderLayout.CENTER);
        frame.add(nextButton, BorderLayout.SOUTH);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

//done