package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class MoveTwoDice4 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public MoveTwoDice4(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public int getToken(){
        return toolcard.getToken();
    }

    public void setOneToken(PlayerZone player){}

    public void setTwoToken(PlayerZone player){}

    public boolean play(Box fromBox, Box toBox, int player){return false;}
    public boolean play(DieInt dieFromDraft, Box toBox, int player){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}
    public boolean play(int player ){return false;}

    public boolean play (Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int pl ) {

        Model model = singletonModel();
        PlayerZone player = model.getPlayerList().get(pl);
        DieInt die = fromBox1.getDie();
        DieInt die2 =fromBox2.getDie();

        PlaceDie placement = new PlaceDie(die, toBox1, player);

        fromBox1.free();

         if (placement.placeDie() ) {

             placement = new PlaceDie(die2, toBox2, player);
             fromBox2.free();
             if(!(placement.placeDie())){
                 System.out.println("error 2 placement");
                 fromBox2.setDie(die);
                 return false;
             }
             return true;
         }
        System.out.println("error 1 placement ");
        fromBox1.setDie(die);
        return false;

    }
}
