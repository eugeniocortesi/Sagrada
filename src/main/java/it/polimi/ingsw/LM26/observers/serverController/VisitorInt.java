package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.*;

public interface VisitorInt {

    void visitActionEvent(ActionEvent actionEvent);
    void visitActionEventWindow(ActionEventWindow actionEventWindow);
    void visitActionEventPlayer(ActionEventPlayer actionEventPlayer);
    void visitConnectMessage(ConnectMessage connectMessage);
    void visitDataMessage(DataMessage dataMessage);
    void visitWindowAnswerMessage(WindowAnswerMessage windowAnswerMessage);
    void visitWindowInitialMessage(WindowInitialMessage windowInitialMessage);
    public Observable getObservable();

    void visitActionEventTimerEnd(ActionEventTimerEnd actionEventTimerEnd);

    void visitBeginGame(Boolean connection);

    void visitBeginTurnMessage(BeginTurnMessage beginTurnMessage);
}