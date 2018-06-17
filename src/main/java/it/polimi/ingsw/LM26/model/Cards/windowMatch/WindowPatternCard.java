package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.Cards.CardInt;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.Serialization.Matrix;


public class WindowPatternCard extends CardInt {

    private int token;
    private String title;
    private Matrix matrix;
    private PatternBox[][] patternMatrix;
    private boolean inUse=false;

    public WindowPatternCard(int token, String title, Matrix matrix) {

        this.token = token;
        this.title = title;
        this.matrix = matrix;
        this.typeCard="WindowPatternCard";
    }

    public void createPattern(){
        patternMatrix = new PatternBox[4][5];
        for(int i=0; i<4; i++)
            for(int j=0; j<5; j++)
                patternMatrix[i][j]= new PatternBox();

        matrix.createPatternMatrix(patternMatrix);
        this.matrix= null;
        }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public String getTitle() {
        return title;
    }

    public int getToken() {
        return token;
    }

    public PatternBox[][] getWindowPatter() {

        return patternMatrix;
        }

    public void printCard() {

        System.out.println(getTitle());
        System.out.println(getToken());
        String escape;
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++) {
                if (patternMatrix[i][j].isColor() == true){
                    escape =patternMatrix[i][j].getColor().escape();
                    System.out.print(escape +"\u25A0" + Color.RESET + " " );
                }
                else if (patternMatrix[i][j].isShade() == true)
                    System.out.print(patternMatrix[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void rewrite() {

        this.typeCard="WindowPatternCard";

    }
}

