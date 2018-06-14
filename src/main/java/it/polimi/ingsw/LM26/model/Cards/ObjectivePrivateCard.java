package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.Serialization.Elements.elements;

import static it.polimi.ingsw.LM26.model.Serialization.Elements.elements.*;

public class ObjectivePrivateCard implements CardInt {

    private String type;

    private elements colour= null;

    private boolean inUse= false;

    int id;

    public ObjectivePrivateCard(int id, elements colour) {

        this.colour=colour;
        this.id=id;
    }
    public Color getColour() {

        if(colour==BLUE)
            return Color.ANSI_BLUE;

        else if(colour==VIOLET)
            return Color.ANSI_PURPLE;

        else if(colour==RED)
            return Color.ANSI_RED;

        else if(colour==GREEN)
            return Color.ANSI_GREEN;

        else if(colour==YELLOW)
            return Color.ANSI_YELLOW;

        else return null;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getId() {
        return id;
    }

    public void printCard(){

        System.out.println(getId());
        System.out.println(getColour());
    }


    @Override
    public void rewrite() {

        this.type="ObjectivePrivateCard";
    }
}
