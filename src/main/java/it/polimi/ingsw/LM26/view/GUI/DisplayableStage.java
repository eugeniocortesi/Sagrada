package it.polimi.ingsw.LM26.view.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayableStage {

    private Pane pane;
    private FXMLLoader fxmlLoader;
    private Stage primaryStage;

    public DisplayableStage(String name) {
        this.primaryStage=primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(DisplayableStage.class.getResource(name));
        this.fxmlLoader=fxmlLoader;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public void show(Stage primaryStage){
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public void showAndWait(Stage primaryStage){
        primaryStage.setScene(new Scene(pane));
        primaryStage.showAndWait();
    }
}
