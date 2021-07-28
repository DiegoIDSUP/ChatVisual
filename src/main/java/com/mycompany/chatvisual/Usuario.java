 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatvisual;

import io.reactivex.rxjava3.core.Observable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author dianf
 */
public class Usuario implements Runnable{
    String nombre;
    Socket sc;
    int ID;
    Usuario(String nombre,Socket sc,int ID){
        this.nombre=nombre;
        this.sc=sc;
        this.ID=ID;
    }

    @Override
    public void run() {
        Observable<String> inMsn = Observable.create(e -> {
            while (!sc.isClosed()) {                
                try {
                    DataInputStream in = new DataInputStream((this.sc.getInputStream()));
                    String mensaje = in.readUTF();
                    e.onNext(mensaje);
                } catch (IOException ex) {
                    sc.close();
                    System.out.println("El usuario "+nombre+"#"+ID+" se ha desconectado");
                }
            }
        });
        inMsn.subscribe(e->{
            System.out.println(nombre+"#"+ID+": "+e);
            for(Socket online : Servidor.conectados){
                DataOutputStream out = new  DataOutputStream(online.getOutputStream());
                out.writeUTF(nombre+"#"+ID+": "+e);
            }
        });
    }
}
