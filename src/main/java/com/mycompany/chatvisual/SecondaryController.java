package com.mycompany.chatvisual;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SecondaryController {
    @FXML TextArea TAchat;
    @FXML TextField TFmsn;
    
    DataOutputStream out;
    DataInputStream in;
    Socket sc = null;
    Thread lector;
    
    @FXML
    private void Enviar() throws IOException {
        if(!TFmsn.getText().isEmpty()){
            System.out.println("That what she said: "+TFmsn.getText());
            out = new  DataOutputStream(sc.getOutputStream());
            out.writeUTF(TFmsn.getText());
            TFmsn.clear();
        }
    }
    
    public void update(String msn){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String msn_chat = TAchat.getText();
                msn_chat += "\n"+msn;
                TAchat.setText(msn_chat);
            }
        });  
    }

    public void setSocket(Socket sc) {
        this.sc = sc;
        lector=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {                            
                            in = new DataInputStream((sc.getInputStream()));
                            String mensaje = in.readUTF();
                            System.out.println(mensaje);
                            update(mensaje);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        lector.start();
    }
    
    public void Kill(){
        try {
            lector.stop();
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}