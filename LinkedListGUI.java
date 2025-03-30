package TimeAndSpace.DSA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//node class for linkedlist
class Node {
    int data;
    Node next;

    public Node (int data) {
        this.data = data;
        this.next = null;
    }
}

//linkedlist with operations
class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    //insert at beginning
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    //insert at end
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    //delete a node
    public boolean deleteNode(int data) {
        if(head == null) return false;
        if (head.data == data) {
            head = head.next;
            return true;
        }

        Node temp = head;
        while (temp.next != null && temp.next.data !=data) {
            temp = temp.next;
        }

        if (temp.next == null) {
            return false;
        } else {
            temp.next = temp.next.next;
            return true;
        }

    }

    //search for a node
    public boolean search(int data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) return true;
            temp = temp.next;
            
        }

        return false;
    }

    //reverse the linkedlist
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next;
        while (current != null) {
            next = current.next; //store next node
            current.next = prev; //reverse the link
            prev = current; //move prev one step forward
            current = next; //move current one step forward
        }

        head = prev;
    }

    //display the linked list as a string
    public String display() {
        if(head == null) return "List is empty!";
        StringBuilder result = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            result.append(temp.data).append(" -> ");
            temp = temp.next;
        }

        result.append("null");
        return result.toString();

    }
}

//main gui class
public class LinkedListGUI extends JFrame {
    private LinkedList linkedList;
    private JTextArea displayArea;

    public LinkedListGUI() {
        linkedList = new LinkedList();
        setTitle("LinkedList Operations");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        //display area for the linked list
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        //input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7,2,5,5));

        //input fields & buttons
        JTextField inputField = new JTextField();
        JButton insertAtBeginningButton = new JButton("insert at beginning");
        JButton insertAtEndButton = new JButton("insert at end");
        JButton deleteButton = new JButton("delete");
        JButton searchButton = new JButton("search");
        JButton clearButton = new JButton("clear");
        JButton refreshButton = new JButton("refresh display");
        JButton reverseButton = new JButton("reverse list");


        //add components to input panel
        inputPanel.add(new JLabel("Value:"));
        inputPanel.add(inputField);
        inputPanel.add(insertAtBeginningButton);
        inputPanel.add(insertAtEndButton);
        inputPanel.add(deleteButton);
        inputPanel.add(searchButton);
        inputPanel.add(refreshButton);
        inputPanel.add(clearButton);
        inputPanel.add(new JLabel()); //empty placeholder
        inputPanel.add(reverseButton);


        add(inputPanel, BorderLayout.SOUTH);


        //BUTTON ACTION LISTENERS
        insertAtBeginningButton.addActionListener(e -> {
            try {
                int value=
            }

        });

        
    }
}




        // Button action listeners
        insertAtBeginningButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                linkedList.insertAtBeginning(value);
                updateDisplay();
                inputField.setText("");
            } catch (NumberFormatException ex) {
                showError("Please enter a valid integer.");
            }
        });

        insertAtEndButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                linkedList.insertAtEnd(value);
                updateDisplay();
                inputField.setText("");
            } catch (NumberFormatException ex) {
                showError("Please enter a valid integer.");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                boolean result = linkedList.deleteNode(value);
                if (result) {
                    showInfo("Node deleted successfully.");
                } else {
                    showError("Node not found.");
                }
                updateDisplay();
                inputField.setText("");
            } catch (NumberFormatException ex) {
                showError("Please enter a valid integer.");
            }
        });

        searchButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                boolean found = linkedList.search(value);
                if (found) {
                    showInfo("Node found in the list.");
                } else {
                    showError("Node not found.");
                }
                inputField.setText("");
            } catch (NumberFormatException ex) {
                showError("Please enter a valid integer.");
            }
        });

        clearButton.addActionListener(e -> {
            linkedList = new LinkedList();
            updateDisplay();
        });

        refreshButton.addActionListener(e -> updateDisplay());

        reverseButton.addActionListener(e -> {
            linkedList.reverse();
            updateDisplay();
        });

        updateDisplay();
    }

    // Update the display area
    private void updateDisplay() {
        displayArea.setText(linkedList.display());
    }

    // Show error message
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Show info message
    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LinkedListGUI frame = new LinkedListGUI();
            frame.setVisible(true);
        });
    }
}
