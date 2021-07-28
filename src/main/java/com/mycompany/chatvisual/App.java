package com.mycompany.chatvisual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static SecondaryController second;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.setTitle("Ingresar al chat");
        System.out.println(stage.resizableProperty().get());
        setTam(stage,300,400);
        stage.show();
        this.stage=stage;
    }

    static void goChat(Socket sc, String nombre) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        loader.load();
        second = loader.getController();
        second.setSocket(sc);
        scene.setRoot(loader.getRoot());
        setTam(stage,500,350);
        stage.setTitle("Bienvenido "+nombre);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    private static void setTam(Stage stage, double widht, double height){
        stage.setMinWidth(widht);
        stage.setMinHeight(height);
        stage.setMaxWidth(widht);
        stage.setMaxHeight(height);
    }
    
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void stop() throws Exception{
        second.Kill();
        System.out.println("Se cerro la conexion");
    }

}