package rmiclientsidechat;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import rmicommonchat.ServerInterface;
import rmicommonchat.clientInterface;

public class ClientGUIBase extends AnchorPane {

    protected final TextArea textArea;
    protected final TextField textField;
    protected final Button button;

    public ClientGUIBase(Stage stage) {

        textArea = new TextArea();
        textField = new TextField();
        button = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        textArea.setLayoutX(39.0);
        textArea.setLayoutY(36.0);
        textArea.setPrefHeight(276.0);
        textArea.setPrefWidth(521.0);

        textField.setLayoutX(39.0);
        textField.setLayoutY(334.0);
        textField.setPrefHeight(31.0);
        textField.setPrefWidth(379.0);

        button.setLayoutX(470.0);
        button.setLayoutY(334.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(31.0);
        button.setPrefWidth(91.0);
        button.setText("Send");

        getChildren().add(textArea);
        getChildren().add(textField);
        getChildren().add(button);

        Registry registry;

        try {
            clientInterface clientImplementer = new ClientImplementerClass(this);
            registry = LocateRegistry.getRegistry("127.0.0.1");
            ServerInterface serverInterfaceRefrence = (ServerInterface) registry.lookup("chatService");
            serverInterfaceRefrence.registerClient(clientImplementer);
            button.setOnAction((ActionEvent e) -> {
                String message = textField.getText();
                try {
                    String key = "Bar12345Bar12345";
                    // Create key and cipher
                    Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    // encrypt the text
                    cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                    byte[] encrypted = cipher.doFinal(message.getBytes());
                    System.err.println(new String(encrypted));
                    serverInterfaceRefrence.broadcastMessage(encrypted);
                    textField.clear();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                } catch (NoSuchPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                }
            });
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    try {
                        serverInterfaceRefrence.unregisterClient(clientImplementer);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
