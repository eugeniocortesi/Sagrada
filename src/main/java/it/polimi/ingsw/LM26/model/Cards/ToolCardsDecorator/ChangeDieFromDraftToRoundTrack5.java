package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class ChangeDieFromDraftToRoundTrack5 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public ChangeDieFromDraftToRoundTrack5(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public boolean play(Box fromBox, Box toBox, int player){return false;}
    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){return false;}
    public boolean play(Die dieFromDraft, Box toBox, int player){return false;}
    public boolean play( Die dieFromDraft, String inDeCrement){return false;}
    public boolean play(Die dieFromDraft){return false;}
    public boolean play( int player){return false;}

    public boolean play (Die die1, Die die2) {


        Model model = singletonModel();
        ArrayList<DieInt> inDraft = model.getDraftPool().getInDraft();
        ArrayList<DieInt> diceList = model.getBag().getInBag();
        inDraft.add(die2);
        diceList.add(diceList.indexOf(die2), die1 );
        inDraft.remove(die1);
        return true;


    }
}