package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

class xorChipher {
    public static String encrypt(String text, String key) {
        String encrypHexa = "";
        int keyItr = 0;
        for (int i = 0; i < text.length(); i++) {
            int temp = text.charAt(i) ^ key.charAt(keyItr);

            encrypHexa += String.format("%02x", (byte)temp);
            keyItr++;
            if(keyItr >= key.length()){
                keyItr = 0;
            }
        }

        System.out.println("Encrypted Text: " + encrypHexa);

        return encrypHexa;
    }

    public static String decipher(String text, String key) {
        String hexToDeci = "";
        for (int i = 0; i < text.length()-1; i+=2) {
            // splitting hex into a pair of two
            String output = text.substring(i, (i+2));
            int decimal = Integer.parseInt(output, 16);
            hexToDeci += (char)decimal;
        }

        // decryption
        String decrypText = "";
        int keyItr = 0;
        for (int i = 0; i < hexToDeci.length(); i++) {
            // XOR Operation
            int temp = hexToDeci.charAt(i) ^ key.charAt(keyItr);

            decrypText += (char)temp;
            keyItr++;
            if(keyItr >= key.length()){
                keyItr = 0;
            }
        }

        System.out.println("Decrypted Text: " + decrypText);

        return decrypText;
    }

}

public class Controller {

    @FXML
    private Button encrypt_btn;

    @FXML
    private TextArea input_textarea;

    @FXML
    private Label output;

    @FXML
    private Button decipher_btn;

    @FXML
    private TextField keyField;

    @FXML
    void initialize() {

        encrypt_btn.setOnAction(actionEvent -> {
            String inputText = input_textarea.getText();
            String key = String.valueOf(keyField.getText());
            System.out.println("Key = " + key);
            String outputText = xorChipher.encrypt(inputText,key);
            System.out.println(outputText);
            output.setText(String.valueOf(outputText));
        });

        decipher_btn.setOnAction(actionEvent -> {
            String inputText = input_textarea.getText();
            String key = String.valueOf(keyField.getText());
            System.out.println(key);
            String outputText = xorChipher.decipher(inputText,key);
            System.out.println(outputText);
            output.setText(outputText);
        });

    }
}