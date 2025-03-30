package TimeAndSpace.DSA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;

    TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert word into Trie
    public void insert(String word) {
        word = word.toLowerCase();
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Search for a word in Trie
    public boolean search(String word) {
        word = word.toLowerCase();
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    // Delete a word from Trie
    public boolean delete(String word) {
        word = word.toLowerCase();
        return deleteRec(root, word, 0);
    }

    private boolean deleteRec(TrieNode node, String word, int index) {
        if (index == word.length()) {
            if (!node.isEndOfWord) {
                return false;
            }
            node.isEndOfWord = false;
            return nodeIsEmpty(node);
        }
        char ch = word.charAt(index);
        TrieNode childNode = node.children[ch - 'a'];
        if (childNode == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = deleteRec(childNode, word, index + 1);

        if (shouldDeleteCurrentNode) {
            node.children[ch - 'a'] = null;
            return nodeIsEmpty(node) && !node.isEndOfWord;
        }
        return false;
    }

    private boolean nodeIsEmpty(TrieNode node) {
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                return false;
            }
        }
        return true;
    }

    // Reverse a word in Trie
    public boolean reverse(String word) {
        word = word.toLowerCase();
        String reversed = new StringBuilder(word).reverse().toString();
        return search(reversed);
    }

    // Display all words in Trie starting with a given prefix
    public List<String> findWordsWithPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        List<String> result = new ArrayList<>();
        TrieNode node = root;

        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return result;
            }
            node = node.children[index];
        }
        findWordsWithPrefixRec(node, prefix, result);
        return result;
    }

    private void findWordsWithPrefixRec(TrieNode node, String prefix, List<String> result) {
        if (node.isEndOfWord) {
            result.add(prefix);
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                findWordsWithPrefixRec(node.children[i], prefix + (char) (i + 'a'), result);
            }
        }
    }

    // Count the number of words in Trie
    public int countWords() {
        return countWordsRec(root);
    }

    private int countWordsRec(TrieNode node) {
        int count = 0;
        if (node.isEndOfWord) {
            count++;
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                count += countWordsRec(node.children[i]);
            }
        }
        return count;
    }
}

public class TrieGUI {
    private Trie trie;
    private JTextField inputField;
    private JTextArea resultArea;
    private JTextArea suggestionArea;

    public TrieGUI() {
        trie = new Trie();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Trie Operations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Enter Word:");
        inputField = new JTextField(15);
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        suggestionArea = new JTextArea(5, 30);
        suggestionArea.setEditable(false);
        JScrollPane suggestionScrollPane = new JScrollPane(suggestionArea);

        JButton insertButton = new JButton("Insert");
        JButton searchButton = new JButton("Search");
        JButton deleteButton = new JButton("Delete");
        JButton reverseButton = new JButton("Reverse");
        JButton prefixButton = new JButton("Find Prefix");
        JButton countButton = new JButton("Count Words");

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String words = inputField.getText();
                if (!words.isEmpty()) {
                    String[] wordArray = words.split("\\s+"); // Split input into multiple words
                    for (String word : wordArray) {
                        trie.insert(word);
                        resultArea.append("Inserted: " + word + "\n");
                    }
                    inputField.setText(""); // Clear input field after insertion
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String word = inputField.getText();
                if (!word.isEmpty()) {
                    boolean found = trie.search(word);
                    resultArea.append("Search Result: " + (found ? "Found" : "Not Found") + "\n");
                    inputField.setText(""); // Clear input field after search
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String word = inputField.getText();
                if (!word.isEmpty()) {
                    boolean deleted = trie.delete(word);
                    resultArea.append("Delete Result: " + (deleted ? "Deleted" : "Done") + "\n");
                    inputField.setText(""); // Clear input field after deletion
                }
            }
        });

        reverseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String word = inputField.getText();
                if (!word.isEmpty()) {
                    boolean found = trie.reverse(word);
                    resultArea.append("Reverse Search: " + (found ? "Found" : "Not Found") + "\n");
                    inputField.setText(""); // Clear input field after reverse search
                }
            }
        });

        prefixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String prefix = inputField.getText();
                if (!prefix.isEmpty()) {
                    List<String> words = trie.findWordsWithPrefix(prefix);
                    suggestionArea.setText("Suggestions:\n" + String.join("\n", words));
                    inputField.setText(""); // Clear input field after prefix search
                }
            }
        });

        countButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int wordCount = trie.countWords();
                resultArea.append("Total Words in Trie: " + wordCount + "\n");
            }
        });

        panel.add(label);
        panel.add(inputField);
        panel.add(insertButton);
        panel.add(searchButton);
        panel.add(deleteButton);
        panel.add(reverseButton);
        panel.add(prefixButton);
        panel.add(countButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(resultScrollPane, BorderLayout.CENTER);
        frame.add(suggestionScrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TrieGUI();
            }
        });
    }
}
