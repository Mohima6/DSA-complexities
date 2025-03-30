package TimeAndSpace.DSA;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;


public class Hash extends JPanel {
    private JTextField keyField;
    private JTextField valueField;
    private JTextArea outputArea;
    private JButton putButton, getButton, removeButton, searchButton, reverseButton;
    private HashMap<String, String> hashMap;


    public Hash() {
        hashMap = new HashMap<>();
        setLayout(new BorderLayout());

        //create input fields, buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2,2));

        inputPanel.add(new JLabel("Key:"));
        keyField = new JTextField();
        inputPanel.add(keyField);


        inputPanel.add(new JLabel("Value:"));
        valueField = new JTextField();
        inputPanel.add(valueField);


        //create buttons for different operations
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        putButton = new JButton("Put");
        getButton = new JButton("Get");
        removeButton = new JButton("Remove");
        searchButton = new JButton("Search");
        reverseButton = new JButton("Reverse");


        buttonPanel.add(putButton);
        buttonPanel.add(getButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(reverseButton);



        //output area to show results
        outputArea = new JTextArea(10,30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);


        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);


        //add action listener to buttons
        putButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                String value = valueField.getText();
                hashMap.put(key, value);
                outputArea.append("Added (" + key + ", " + value + ")\n");
            }
        });

getButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String key = keyField.getText();
        if (hashMap.containsKey(key)) {
            String value = hashMap.get(key);
            outputArea.append("Key: " + key + " => Value: " + value + "\n");
        } else {
            outputArea.append("Key not found: " + key + "\n");
        }

    }
});


removeButton.addActionListener(new ActionListener() {
    @Override 
    public void actionPerformed(ActionEvent e) {
          String key = keyField.getText();
          if (hashMap.containsKey(key)) {
            hashMap.remove(key);
            outputArea.append("Removed key: " + key + "\n");
          } else {
            outputArea.append("Key not found: " + key + "\n");
          }
    }
});


//search button to find the value with a key
searchButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String key = keyField.getText();
        if (hashMap.containsKey(key)) {
            String value = hashMap.get(key);
            outputArea.append("Search - key: " + key + " => Value: " + value + "\n");
        } else {
            outputArea.append("key not found: " + key + "\n");
        }
    }
});


//reverse button 
reverseButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e){
        HashMap<String, String> reversedMap = new HashMap<>();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            reversedMap.put(entry.getValue(), entry.getKey());
        }

        hashMap = reversedMap; 
        outputArea.append("Reversed HashMap:\n");
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            outputArea.append("Key: " + entry.getKey() + " => Value: " + entry.getValue() + "\n");
            
        }
    }
});


    }
    public static void main(String[] args) {
        //set up gui
        JFrame frame = new JFrame("Hashing and HashMap Operations");
        Hash panel = new Hash();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.add(panel);
        frame.setVisible(true);
    }
}


    //done