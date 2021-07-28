package com.mycompany.chatvisual;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML TextField TFusername;
    FXMLLoader loader = new FXMLLoader();
    
    final String HOST="127.0.0.1";
    DataOutputStream out;
    final int PORT = 5000;
    Socket sc = null;
    
    @FXML private void Conectar() throws IOException {
        if(!TFusername.getText().isEmpty()){
            System.out.println("That what she said: "+TFusername.getText());
            sc = new Socket(HOST,PORT);
            out = new  DataOutputStream(sc.getOutputStream());
            out.writeUTF(TFusername.getText());
            App.goChat(sc, TFusername.getText());
            TFusername.clear();
        }else
            System.out.println("Esta vacio");
    }
}
