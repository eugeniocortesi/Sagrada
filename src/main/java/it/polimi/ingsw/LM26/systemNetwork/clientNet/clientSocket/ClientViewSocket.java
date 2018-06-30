
package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientSocket;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventPlayer;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.EventMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowAnswerMessage;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientViewSocket extends ClientView {

    private ListenerClientView listenerClientView;
    private ViewInterface concreteClientView;
    private int SOCKETPORT;

    private static String address;
    private Socket socket;
    private BufferedReader inSocket;
    private PrintWriter outSocket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private String username;
    private int id;

    private static final Logger LOGGER = Logger.getLogger(ClientViewSocket.class.getName());


    public ClientViewSocket(ViewInterface concreteClientView, DataClientConfiguration data){

        this.concreteClientView = concreteClientView;
        SOCKETPORT = data.getClientSOCKETPORT();
        address = data.getIp();

        register(concreteClientView);
       /* Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        LOGGER.addHandler(handlerObj);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);*/

        //listenerClientView.listen();
    }

    @Override
    public void connect() {

        try {
            LOGGER.log(Level.SEVERE, "I'm trying to connect");

            socket = new Socket(address, SOCKETPORT);
            //canali di comunicazione
            inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
            listenerClientView = new ListenerClientView(this, socket);
            connected();
            listenerClientView.start();

            LOGGER.log(Level.SEVERE,"ClientImplementationSocket connected");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void connected(){
        LOGGER.log(Level.SEVERE,"Client connected");
        ConnectMessage connectMessage = new ConnectMessage("connected", 0);
        connectMessage.dump();
        outSocket.println(connectMessage.serializeClassMessage());
    }

    public void getAvailableId(int id){
        this.id= id;
        requestedLogin();
    }


    @Override
    public void requestedLogin() {
        concreteClientView.showLoginScreen();

    }

    @Override
    public void login(String name) {
        DataMessage message = new DataMessage("login", name);
        message.dump();
        outSocket.println(message.serializeClassMessage());
        //listenerClientView.listen();
    }

    @Override
    public void logged(Boolean l, String name) {
        if (l==true){
            this.username = name;
            concreteClientView.showLoggedScreen();
        }
        else{
            concreteClientView.showAlreadyLoggedScreen();
        }

    }

    @Override
    public void tooManyUsers() {
        concreteClientView.showTooManyUsersScreen();

    }

    @Override
    public void disconnect() {

        LOGGER.log(Level.SEVERE,"Client connected");
        DataMessage dataMessage = new DataMessage("disconnect", username);
        outSocket.println(dataMessage.serializeClassMessage());

    }

    @Override
    public void disconnected() {
        LOGGER.log(Level.SEVERE,"Client disconnected");
        concreteClientView.showDisconnectScreen();
    }

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
        LOGGER.log(Level.SEVERE,"server is asking a window pattern");
        this.id = id;
        concreteClientView.showWindowPattern(user, id, windowDeck);

    }

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {
        WindowAnswerMessage message = new WindowAnswerMessage("send_windowcard", actionEventWindow);
        outSocket.println(message.serializeClassMessage());

    }

    @Override
    public void sendPrivateCard(ObjectivePrivateCard privateCard) {
        //TODO remove it
        //privateCard.printCard();
        concreteClientView.showPrivateCard(username, privateCard);

    }


    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) {
        actionEvent.rewrite();
        EventMessage message = new EventMessage("send_actionevent_from_view", actionEvent);
        LOGGER.log(Level.WARNING, "sending answer from view to Controller");
        outSocket.println(message.serializeClassMessage());
    }


    @Override
    public void sendAnswerFromController(String answer) {
        concreteClientView.showAnswerFromController(username, answer);

    }

    @Override
    public void sendBeginTurn(String name, PlayerZone playerZone) {
        LOGGER.log(Level.SEVERE,"Show set player menu arrived from Controller");
        concreteClientView.showSetPlayerMenu(name, playerZone);
    }

    @Override
    public void sendAddedPlayer(String field1) {
        concreteClientView.showAddedPlayer(field1);
    }

    @Override
    public void sendCurrentMenu(String name) {
        LOGGER.log(Level.SEVERE,"Show current menu is arrived from Controller");
        concreteClientView.showCurrentMenu(name);
    }

    @Override
    public void pong() {

        DataMessage dataMessage = new DataMessage("pong", "pong");

        //LOGGER.log(Level.SEVERE, "Pong arrived");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                outSocket.println(dataMessage.serializeClassMessage());
            }
        });
        t.start();

    }

    @Override
    protected void notify(Model m) {
        LOGGER.log(Level.SEVERE,"Model is arrived from Controller");
        if(m == null)
            System.out.println("Model null");

        LOGGER.log(Level.SEVERE,"playerlist "+ m.getPlayerList());
        for(int i = 0; i<m.getPlayerList().size(); i++){
            LOGGER.log(Level.SEVERE,m.getPlayerList().get(i).getName() + " player");
        }

        super.notify(m);
    }

    @Override
    public void updatePlayers(ActionEventPlayer actionEventPlayer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateAction(ActionEvent actionEvent) {
        sendActionEventFromView(actionEvent);
    }

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateBeginGame(Boolean beginGame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}