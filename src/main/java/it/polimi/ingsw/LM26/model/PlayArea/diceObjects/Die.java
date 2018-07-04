package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.util.Random;

/**
 * Die class
 * @author Chiara Criscuolo
 * It contains all informations about a single die
 */
public class Die extends DieInt {

    private Color color;

    private String face;

    private int number = 0;

    public Die() {

    }

    public static final String[] faces = {

            "\u2680",

            "\u2681",

            "\u2682",

            "\u2683",

            "\u2684",

            "\u2685"};

    /**
     * It assign to a die a random face
     */
    public void roll() {

        int count = faces.length;

        Random rand = new Random();

        int index = rand.nextInt(count);

        this.face = faces[index];

        setNumber();
    }

    /**
     * @return the value of the die
     */

    public int getValue() {

        int value = 0;

        if (face == "\u2680" || number == 1) value = 1;

        if (face == "\u2681" || number == 2) value = 2;

        if (face == "\u2682" || number == 3) value = 3;

        if (face == "\u2683" || number == 4) value = 4;

        if (face == "\u2684" || number == 5) value = 5;

        if (face == "\u2685" || number == 6) value = 6;

        return value;
    }

    /**
     * Transform the face in the corresponding number
     * And assign it to the die
     */

    public void setNumber() {

        if (face == "\u2680" ) number = 1;

        if (face == "\u2681" ) number = 2;

        if (face == "\u2682" ) number = 3;

        if (face == "\u2683" ) number = 4;

        if (face == "\u2684" ) number = 5;

        if (face == "\u2685" ) number = 6;
    }

    /**
     * Increment 1 the value of the face
     * then call setNumber();
     */

    public void increment() {

        if (face == "\u2685") face = "\u2680";

        else if (face == "\u2684") face = "\u2685";

        else if (face == "\u2683") face = "\u2684";

        else if (face == "\u2682") face = "\u2683";

        else if (face == "\u2681") face = "\u2682";

        else if (face == "\u2680") face = "\u2681";

        setNumber();
    }

    /**
     * Decrement 1 the value of the face
     * then call setNumber();
     */

    public void decrement() {

        if (face == "\u2680") face = "\u2685";

        else if (face == "\u2681") face = "\u2680";

        else if (face == "\u2682") face = "\u2681";

        else if (face == "\u2683") face = "\u2682";

        else if (face == "\u2684") face = "\u2683";

        else if (face == "\u2685") face = "\u2684";

        setNumber();
    }

    /**
     * Takes value and assign the corresponding face to the die
     * @param value value number
     */

    public void setRoll(int value) {

        if (value == 1) face = "\u2680";

        if (value == 2) face = "\u2681";

        if (value == 3) face = "\u2682";

        if (value == 4) face = "\u2683";

        if (value == 5) face = "\u2684";

        if (value == 6) face = "\u2685";

        setNumber();
    }

    /**
     * Rewrite field type for Json
     */

    @Override
    public void rewrite() {

        this.typeDie = "Die";
    }

    /**
     * Constructor
     * @param color color to assign
     */

    public Die(Color color) {

        this.color = color;

        this.typeDie = "Die";
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumber() {
        return this.number;
    }


    @Override
    public String toString() {

        String escape = this.color.escape();

        return escape + "[" + face + "]" + Color.RESET;
    }

    /**
     * Shortcut to print die
     */

    public void dump() {

        System.out.println(this);
    }
}