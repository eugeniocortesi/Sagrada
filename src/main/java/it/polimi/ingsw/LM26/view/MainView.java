package it.polimi.ingsw.LM26.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainView extends Application {

    private View view;

    public static void main(String[] args){

        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        view = new View(primaryStage);
        view.showLoginScreen();
    }
}