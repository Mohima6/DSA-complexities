package TimeAndSpace.DSA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

public class TreesGUI extends JPanel {
    private TreeNode root;
    private TreeNode searchHighlight;

    public TreesGUI() {
        this.root = null;
        this.searchHighlight = null;
    }

    public void insert(int value) {
        root = insertRec(root, value);
        repaint();
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    public void delete(int value) {
        root = deleteRec(root, value);
        searchHighlight = null;
        repaint();
    }

    private TreeNode deleteRec(TreeNode root, int value) {
        if (root == null) return null;
        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }

    private int minValue(TreeNode root) {
        int min = root.value;
        while (root.left != null) {
            root = root.left;
            min = root.value;
        }
        return min;
    }

    public boolean search(int value) {
        searchHighlight = searchRec(root, value);
        repaint();
        return searchHighlight != null;
    }

    private TreeNode searchRec(TreeNode root, int value) {
        if (root == null || root.value == value) return root;
        return value < root.value ? searchRec(root.left, value) : searchRec(root.right, value);
    }

    public void reverseTree() {
        root = reverseRec(root);
        searchHighlight = null;
        repaint();
    }

    private TreeNode reverseRec(TreeNode node) {
        if (node == null) return null;
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        reverseRec(node.left);
        reverseRec(node.right);
        return node;
    }

    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(TreeNode node) {
        return node == null ? 0 : 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    public int treeHeight() {
        return treeHeightRec(root);
    }

    private int treeHeightRec(TreeNode node) {
        return node == null ? 0 : 1 + Math.max(treeHeightRec(node.left), treeHeightRec(node.right));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        if (root != null) {
            drawTree(g, root, getWidth() / 2, 50, getWidth() / 4);
        }
    }

    private void drawTree(Graphics g, TreeNode node, int x, int y, int xOffset) {
        if (node == null) return;
        g.setColor(node == searchHighlight ? Color.RED : Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(node.value), x - g.getFontMetrics().stringWidth(Integer.toString(node.value)) / 2, y + 5);

        if (node.left != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x - xOffset, y + 50);
            drawTree(g, node.left, x - xOffset, y + 50, xOffset / 2);
        }
        if (node.right != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x + xOffset, y + 50);
            drawTree(g, node.right, x + xOffset, y + 50, xOffset / 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tree Visualization");
        TreesGUI treeGUI = new TreesGUI();

        JTextField inputField = new JTextField(10);
        JButton insertButton = new JButton("Insert");
        JButton deleteButton = new JButton("Delete");
        JButton reverseButton = new JButton("Reverse");
        JButton searchButton = new JButton("Search");
        JButton nodeCountButton = new JButton("Node Count");
        JButton treeHeightButton = new JButton("Tree Height");

        insertButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText().trim());
                treeGUI.insert(value);
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText().trim());
                treeGUI.delete(value);
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter a valid number!");
            }
        });

        reverseButton.addActionListener(e -> {
            treeGUI.reverseTree();
            JOptionPane.showMessageDialog(frame, "Tree reversed!");
        });

        searchButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText().trim());
                if (treeGUI.search(value)) {
                    JOptionPane.showMessageDialog(frame, "Value " + value + " found in the tree!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Value " + value + " not found in the tree!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter a valid number!");
            }
        });

        nodeCountButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Total Nodes: " + treeGUI.countNodes()));
        treeHeightButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Tree Height: " + treeGUI.treeHeight()));

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Value:"));
        controlPanel.add(inputField);
        controlPanel.add(insertButton);
        controlPanel.add(deleteButton);
        controlPanel.add(reverseButton);
        controlPanel.add(searchButton);
        controlPanel.add(nodeCountButton);
        controlPanel.add(treeHeightButton);

        frame.setLayout(new BorderLayout());
        frame.add(treeGUI, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


//done