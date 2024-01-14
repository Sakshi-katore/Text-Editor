import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;

    // file menu items
    JMenuItem newFile, saveFile, openFile;

    // edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor() {
        //Initializing a frame
        frame = new JFrame();

        //Initializing a MenuBar
        menuBar = new JMenuBar();

        //Initializing a textArea
        textArea = new JTextArea();

        //Initializing a Menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Intitialize file menu item
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add actionListner to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Add menuItems to menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Intitialize edit menu item
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Add actionListner to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Add menu items to menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menu to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        //frame.add(textArea);
        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);
        //Sets dimensions of frame
        frame.setBounds(0, 0, 400, 400);   // x y gives location of window
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            // perform cut operation
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            // perform copy operation
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            // perform paste operation
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            // perform selectAll operation
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            // perform close operation
            System.exit(0); // close the application
        }
        if (actionEvent.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            // if we have clicked on the open option
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // getting selected file
                File file = fileChooser.getSelectedFile();

                // getting path of selected file
                String filePath = file.getPath();

                try {
                    // Initialize file reader
                    FileReader fileReader = new FileReader(filePath);

                    // initialize buffer reader (it's like a scanner)
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate, output = "";

                    // Read contents of the file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }

                    // set the output string to the text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if (actionEvent.getSource() == saveFile) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);

            // if we have clicked on the save option
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // here file not exist already
                // create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                try {
                    // Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);

                    // initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // write contents of the text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}
