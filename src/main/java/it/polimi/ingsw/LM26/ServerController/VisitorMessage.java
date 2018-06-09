package it.polimi.ingsw.LM26.ServerController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowAnswerMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowInitialMessage;

public class VisitorMessage implements VisitorInt {

    Observable observable;

    public VisitorMessage(){
        observable = new Observable();
    }

    @Override
    public Observable getObservable() {
        return observable;
    }

    @Override
    public void visitActionEventTimerEnd(ActionEventTimerEnd actionEventTimerEnd) {
        observable.notify(actionEventTimerEnd);
    }

    @Override
    public void visitActionEvent(ActionEvent actionEvent) {
        observable.notify(actionEvent);
    }

    @Override
    public void visitActionEventWindow(ActionEventWindow actionEventWindow) {
        observable.notify(actionEventWindow);
    }

    @Override
    public void visitActionEventPlayer(ActionEventPlayer actionEventPlayer) {
        observable.notify(actionEventPlayer);
    }

    @Override
    public void visitConnectMessage(ConnectMessage connectMessage) {
        observable.notify(connectMessage);
    }

    @Override
    public void visitDataMessage(DataMessage dataMessage) {
        observable.notify(dataMessage);
    }

    @Override
    public void visitWindowAnswerMessage(WindowAnswerMessage windowAnswerMessage) {
        observable.notify(windowAnswerMessage);
    }

    @Override
    public void visitWindowInitialMessage(WindowInitialMessage windowInitialMessage) {
        observable.notify(windowInitialMessage);
    }

}
