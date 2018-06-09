package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.ArrayList;

public class View extends ViewInterface{
    private DisplayableStage displayableStage1 = new DisplayableStage("Login.fxml");
    //private DisplayableStage displayableStage2 = new DisplayableStage("MyPlayerzone.fxml");
    private DisplayableStage displayableStageNetChioce = new DisplayableStage("NetChioce.fxml");
    private DisplayableStage displayableStageWPattern = new DisplayableStage("windowPattern.fxml");
    private Stage stage;

    public View(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void showNetChoise() {
        Platform.runLater(new Runnable() {
            public void run() {
                displayableStageNetChioce.show(stage);
            }
        });
    }

    @Override
    public void showLoginScreen() {
       Platform.runLater(new Runnable() {
           public void run() {
               displayableStage1.show(stage);
           }
       });
    }

    @Override
    public void showLoggedScreen() {
        FXMLLoader fLoader=displayableStage1.getFxmlLoader();
        ControllerLogin cl=fLoader.getController();
        cl.loggedScreen();
    }

    @Override
    public void showAlreadyLoggedScreen() {
        FXMLLoader fLoader=displayableStage1.getFxmlLoader();
        ControllerLogin cl=fLoader.getController();
        cl.alreadyLoggedScreen();
    }

    @Override
    public void showTooManyUsersScreen() {
        FXMLLoader fLoader=displayableStage1.getFxmlLoader();
        ControllerLogin cl=fLoader.getController();
        cl.tooManyUsersScreen();
    }

    @Override
    public void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader=displayableStageWPattern.getFxmlLoader();
                windowPatternController wpController=fLoader.getController();
                //wpController.setCardLable(windowDeck);
                wpController.setLable("wella");
                displayableStageWPattern.show(stage);
            }
        });
    }

    @Override
    public void showSetPlayerMenu(String name, PlayerZone player) {

    }

    @Override
    public void showAnswerFromController(String answer) {

    }

    @Override
    public void showEndGame(String name, Object score) {

    }

    @Override
    public void startAcceptor(it.polimi.ingsw.LM26.observers.serverController.Observer observer, ObservableSimple model) {

    }

    @Override
    public void showPrivateCard(String name, ObjectivePrivateCard privateCard) {

    }

    public void  showCentralPhaseScreen() {
        Platform.runLater(new Runnable() {
            public void run() {
                //displayableStage2.show(stage);
            }
        });
    }

    @Override
    public void showDisconnectScreen() {

    }

    @Override
    public void update(Model m) {

    }
}
