package it.polimi.ingsw.LM26.model.Serialization;

public class ColoredDiagonals extends Effect{

    private String effect4="Colored diagonals";

    public ColoredDiagonals() {
        this.typeEffect = "ColoredDiagonals";
    }

    public String getE(){
        return effect4;
    }

    protected void resolve(){}

    @Override
    public void rewrite() {

        this.typeEffect = "ColoredDiagonals";

    }
}
