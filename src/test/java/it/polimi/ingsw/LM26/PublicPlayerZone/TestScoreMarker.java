package it.polimi.ingsw.LM26.PublicPlayerZone;

import it.polimi.ingsw.LM26.Cards.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestScoreMarker {

   private ScoreMarker newScoreMarker;

    @Test
    public void checkScoreMarker(){

        newScoreMarker = new ScoreMarker(Color.ANSI_BLUE);

        newScoreMarker.incrementScore(30);

        newScoreMarker.incrementScore(40);

        if(newScoreMarker.getRealPoints()==70 )
            System.out.println("ok");
        else System.out.println("no");

        if(newScoreMarker.getPoints()==20 )
            System.out.println("ok");
        else System.out.println("no");

        if(newScoreMarker.isMore50()==true)
            System.out.println("ok");
        else System.out.println("no");
        }

}