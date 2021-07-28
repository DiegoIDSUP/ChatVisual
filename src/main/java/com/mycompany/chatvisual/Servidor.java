/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatvisual;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author dianf
 */
public class Servidor {
    static ArrayList<Socket> conectados= new ArrayList<Socket>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerSocket serve = null;
        Socket sc = null;
        DataInputStream in;
        final int PORT = 5000;
        try {
            serve = new ServerSocket(PORT);
            System.out.println("Servidor Iniciado");
            int iduser=0;
            while (true) {
                sc = serve.accept();
                in = new DataInputStream((sc.getInputStream()));
                String mensaje = in.readUTF();
                iduser++;
                System.out.println("Usuario "+mensaje+"#"+iduser+" en el Server");
                conectados.add(sc);
                new Thread(new Usuario(mensaje,sc,iduser)).start();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
