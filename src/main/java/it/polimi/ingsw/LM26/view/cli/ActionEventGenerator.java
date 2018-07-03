package it.polimi.ingsw.LM26.view.cli;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ActionEventGenerator {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ConsoleTools cTools=new ConsoleTools();
    ToolsActionEventGenerator tceGenerator;

    ActionEventGenerator(){
        tceGenerator= new ToolsActionEventGenerator();
    }

    /**
     * it asks for a die to put into the frame board
     */
    public ActionEvent askForDiePlacing(){
        Box rc;
        DieInt d=null;
        ActionEvent actionEvent = new ActionEvent();
        d=tceGenerator.askForDie(true);
        actionEvent.setDieFromDraft(d);
        rc=tceGenerator.askForRowCol(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix());
        actionEvent.setToBox1(rc);
        actionEvent.setPlayer(ConsoleTools.id);
        actionEvent.setId(1);
        return  actionEvent;
    }

    public ActionEvent loseTurn(){
        ActionEvent ae=new ActionEvent();
        ae.setNoAction(true);
        ae.setPlayer(ConsoleTools.id);
        ae.setId(11);
        return ae;
    }


    /**
     *it asks for the current menù screen
     */
    public ActionEvent askForMenu(boolean explicitly){
        if(explicitly) {
            tceGenerator.askEndMove();
        }
        ActionEvent a= new ActionEvent();
        a.setId(12);
        a.setPlayer(ConsoleTools.id);
        a.setMenu(true);
        return a;
    }

    //controlli roundtrack vuota e frameboard vuota
    public ActionEvent askToolCard(){
        ActionEvent actionEvent=null;
        int n=tceGenerator.askforToolCardOnboard();
        int id=ConsoleTools.model.getOnBoardCards().getToolCardList().get(n-1).getNum();
        switch (id){
            case 2:
            case 3:
            {actionEvent=this.fromToBox1(n-1); break;}
            case 4:
            {actionEvent=this.fromToBox2(n-1); break;}
            case 6:
            {actionEvent=this.addToBox1(this.dieFromDraftPoolEvent(n-1));
            System.out.println("Il dado è stato riposto nella Riserva, accedivi per piazzarlo nella Plancia Vetrata.\n" +
                    "Se non puoi piazzarlo, passa il turno");
            break;}
            case 9:
            {actionEvent=this.addToBox1(this.dieFromDraftPoolEvent(n-1));
            actionEvent.setId(4); break;}
            case 5:
            {actionEvent=this.addDieFromRoundtrack(this.dieFromDraftPoolEvent(n-1));
            actionEvent.setId(5); break;}
            case 1:
            {actionEvent=this.dfdIncrement(n-1); break;}
            case 10:
                actionEvent=this.dieFromDraftPoolEvent(n-1);
            case 11:{
                String fs=tceGenerator.askFirstSecondPart();
                if(fs.equalsIgnoreCase("p")){
                    actionEvent=this.dieFromDraftPoolEvent(n-1);
                    System.out.println("Prendi di nuovo la carta 11 per la seconda parte");
                }
                else {
                    actionEvent=this.addToBox1(this.number(n-1));
                } break;}
            case 8:
            case 7:
            {actionEvent=this.toolCEvent(n-1); break;}
            case 12:
            {actionEvent=this.addDieFromRoundtrack(this.fromToBox2(n-1));
            actionEvent.setId(10); break;}
        }
        return actionEvent;
    }

    private ActionEvent toolCEvent(int tCardPosition){
        ActionEvent acEv = new ActionEvent();
        acEv.setId(8);
        acEv.setPlayer(ConsoleTools.id);
        acEv.setCardID(ConsoleTools.model.getOnBoardCards().getToolArrayList().get(tCardPosition));
        return acEv;
    }

    private ActionEvent dieFromDraftPoolEvent(int tCardPos){
        ActionEvent a=toolCEvent(tCardPos);
        a.setId(7);
        System.out.println("Dado dalla Riserva:");
        cTools.showInstructionsForPlacement();
        a.setDieFromDraft(tceGenerator.askForDie(true));
        return a;
    }

    private ActionEvent fromToBox1(int tCardPos){
        Box box;
        ActionEvent a=toolCEvent(tCardPos);
        a.setId(2);
        System.out.println("Cella da cui vuoi prendere il dado:");
        box=tceGenerator.askForRowCol(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix());
        a.setFromBox1(box);
        System.out.println("Cella in cui vuoi mettere il dado:");
        box=tceGenerator.askForRowCol(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix());
        a.setToBox1(box);
        return a;
    }

    private ActionEvent fromToBox2(int tCardPos){
        Box box;
        ActionEvent ae=fromToBox1(tCardPos);
        ae.setId(3);
        ArrayList<Box> arrayFrom=new ArrayList<Box>();
        arrayFrom.add(ae.getFromBox1());
        ArrayList<Box> arrayTo=new ArrayList<Box>();
        arrayTo.add(ae.getToBox1());
        ae.setFromBox1(null); ae.setFromBox1(null);
        System.out.println("Cella da cui vuoi prendere il dado:");
        box=tceGenerator.askForRowCol(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix());
        arrayFrom.add(box);
        ae.setFromBoxList(arrayFrom);
        System.out.println("Cella in cui vuoi mettere il dado:");
        box=tceGenerator.askForRowCol(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix());
        arrayTo.add(box);
        ae.setToBoxList(arrayTo);
        return ae;
    }

    private ActionEvent addToBox1(ActionEvent ae){
        Box box;
        System.out.println("Cella in cui vuoi mettere il dado:");
        box=tceGenerator.askForRowCol(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix());
        ae.setToBox1(box);
        return ae;
    }

    private ActionEvent dfdIncrement(int tCardPos){
        String str;
        ActionEvent ae=dieFromDraftPoolEvent(tCardPos);
        ae.setId(6);
        str=tceGenerator.askForIncDec();
        ae.setInDeCrement(str);
        return ae;
    }

    private ActionEvent addDieFromRoundtrack(ActionEvent ae){
        System.out.println("Dado dal tracciato dei round:");
        ae.setDieFromRoundTrack(tceGenerator.askForDie(false));
        return ae;
    }

    public ActionEvent number(int tCardPos){
        ActionEvent a=toolCEvent(tCardPos);
        a.setId(9);
        int n=tceGenerator.askNumber();
        a.setNumber(n);
        return a;
    }
}
