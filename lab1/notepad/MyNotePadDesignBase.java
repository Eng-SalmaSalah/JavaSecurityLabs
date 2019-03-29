

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MyNotePadDesignBase extends BorderPane {

    protected final MenuBar menuBar;
    protected final Menu fileMenu;
    protected final MenuItem newFile;
    protected final MenuItem openFile;
    protected final MenuItem saveFile;
    protected final MenuItem exit;
    protected final Menu editMenu;
    protected final MenuItem cut;
    protected final MenuItem copy;
    protected final MenuItem paste;
    protected final MenuItem delete;
    protected final MenuItem selectAll;
    protected final Menu helpMenu;
    protected final MenuItem about;
    protected final TextArea textArea;

    public MyNotePadDesignBase(Stage stage) {

        menuBar = new MenuBar();
        fileMenu = new Menu();
        newFile = new MenuItem();
        openFile = new MenuItem();
        saveFile = new MenuItem();
        exit = new MenuItem();
        editMenu = new Menu();
        cut = new MenuItem();
        copy = new MenuItem();
        paste = new MenuItem();
        delete = new MenuItem();
        selectAll = new MenuItem();
        helpMenu = new Menu();
        about = new MenuItem();
        textArea = new TextArea();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        fileMenu.setMnemonicParsing(false);
        fileMenu.setText("File");

        newFile.setMnemonicParsing(false);
        newFile.setText("New");
        newFile.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        openFile.setMnemonicParsing(false);
        openFile.setText("Open");
        openFile.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        saveFile.setMnemonicParsing(false);
        saveFile.setText("Save");
        saveFile.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        exit.setMnemonicParsing(false);
        exit.setText("Exit");
        exit.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        editMenu.setMnemonicParsing(false);
        editMenu.setText("Edit");

        cut.setMnemonicParsing(false);
        cut.setText("Cut");

        copy.setMnemonicParsing(false);
        copy.setText("Copy");

        paste.setMnemonicParsing(false);
        paste.setText("Paste");

        delete.setMnemonicParsing(false);
        delete.setText("Delete");

        selectAll.setMnemonicParsing(false);
        selectAll.setText("Select All");

        helpMenu.setMnemonicParsing(false);
        helpMenu.setText("Help");

        about.setMnemonicParsing(false);
        about.setText("About");
        about.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        setTop(menuBar);

        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);
        textArea.setPrefHeight(200.0);
        textArea.setPrefWidth(200.0);
        setCenter(textArea);

        fileMenu.getItems().add(newFile);
        fileMenu.getItems().add(openFile);
        fileMenu.getItems().add(saveFile);
        fileMenu.getItems().add(exit);
        menuBar.getMenus().add(fileMenu);
        editMenu.getItems().add(cut);
        editMenu.getItems().add(copy);
        editMenu.getItems().add(paste);
        editMenu.getItems().add(delete);
        editMenu.getItems().add(selectAll);
        menuBar.getMenus().add(editMenu);
        helpMenu.getItems().add(about);
        menuBar.getMenus().add(helpMenu);

        //new
        newFile.setOnAction((ActionEvent e) -> {
            textArea.clear();
        });
        //open    
        openFile.setOnAction((ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt", "*.docx"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            String input = "";
            int c;

            try {
                RandomAccessFile raf = new RandomAccessFile(selectedFile.getAbsoluteFile().toString(), "rw");
                try {
                    while ((c = raf.read()) != -1) {
                        input += (char) c;
                    }
                    textArea.setText(input);

                } catch (IOException IOex) {
                    System.out.println("Cannot read from File");
                }
            } catch (FileNotFoundException FileNotFoundex) {
                System.out.println("Cannot open File");
            }

            /*if (selectedFile != null) {
                try (FileReader fr = new FileReader(selectedFile.getAbsoluteFile().toString())) {
                
                
                // Read and display the file.
                while ((c = fr.read()) != -1) {
                input+=(char)c;
                }
                textArea.setText(input);
                } catch (IOException ex) {
                System.out.println("I/O Error: " + ex);
                }
                
                }*/
 /*FileInputStream fin;
                
                if (selectedFile != null) {
                try {
                fin = new FileInputStream(selectedFile.getAbsoluteFile().toString());
                
                } catch (FileNotFoundException ex) {
                System.out.println("Cannot Open File");
                return;
                }
                try {
                do {
                c = fin.read();
                if (c != -1) {
                input+=(char)c;
                }
                } while (c != -1);
                } catch (IOException exp) {
                System.out.println("Error Reading File");
                }
                textArea.setText(input);
                }*/
        });

        //save
        saveFile.setOnAction((ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
            File createdFile = fileChooser.showSaveDialog(stage);
            /*if (createdFile != null) {
                String input = "";
                int c;

                try {
                    RandomAccessFile raf = new RandomAccessFile(createdFile.getAbsoluteFile().toString(), "rw");
                    try {
                        raf.write(textArea.getText().getBytes());
                    } catch (IOException ex) {
                        System.out.println("Cannot write to File");
                    }
                } catch (FileNotFoundException FileNotFoundex) {
                    System.out.println("Cannot save File");
                }
            }*/

            /*if (createdFile != null) {
                try (FileWriter fr = new FileWriter(createdFile.getAbsoluteFile().toString())) {

                    fr.write(textArea.getText().toString());
                    
                } catch (IOException ex) {
                    System.out.println("I/O Error: " + ex);
                }

            }*/
            
            FileOutputStream fout;
                
                if (createdFile != null) {
                try {
                fout = new FileOutputStream(createdFile.getAbsoluteFile().toString());
                
                } catch (FileNotFoundException ex) {
                System.out.println("Cannot Open File");
                return;
                }
                try {
                fout.write(textArea.getText().getBytes());
                } catch (IOException exp) {
                System.out.println("Error Reading File");
                }
                
                }
        });

        //Exit
        exit.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exit Confirmation");
            alert.setHeaderText("Exit Confirmation");
            alert.setContentText("Do you want to exit?");
            Optional<ButtonType> selectedOption = alert.showAndWait();
            if (selectedOption.get() == ButtonType.OK) {
                Platform.exit();
            }
        });

        //about
        about.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("About NotePad");
            alert.setHeaderText("About NotePad");
            alert.setContentText("This app is created by Salma Salah");
            Optional<ButtonType> selectedOption = alert.showAndWait();
        });

        //copy
        copy.setOnAction((ActionEvent e) -> {
            textArea.copy();
        });
        //cut
        cut.setOnAction((ActionEvent e) -> {
            textArea.cut();
        });
        //paste
        paste.setOnAction((ActionEvent e) -> {
            textArea.paste();
        });
        //Delete
        delete.setOnAction((ActionEvent e) -> {
            textArea.deleteText(textArea.getSelection());
        });
        //Select All
        selectAll.setOnAction((ActionEvent e) -> {
            textArea.selectAll();
        });

    }

}
