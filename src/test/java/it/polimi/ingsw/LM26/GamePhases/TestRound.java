package it.polimi.ingsw.LM26.GamePhases;

import it.polimi.ingsw.LM26.PlayArea.*;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestRound {

    private RoundTrack roundTrack = new RoundTrack();
    private RoundTrack roundTrack2 = new RoundTrack();
    private DraftPool draftPool = new DraftPool();
    private String name="name";
    private Round round;
    private int nrounds = 10, contEnd, contBeg;
    private int turn[]={1,2,3,4,4,3,2,1};
    private RoundState roundState;
    private Bag bag;
    private ArrayList<DieInt> dice = new ArrayList<DieInt>();
    private PlayerState playerState;
    private PlayerZone player;

    private ArrayList<PlayerZone> playerZones = new ArrayList<PlayerZone>();

    @Test
    //stampa l'ordine dei 4 giocatori nei 10 turni
    public void checkAssignment4Players(){
        for(int i=0; i<4; i++){
            playerZones.add(new PlayerZone(name+"i", i));
        }
        round=new Round(roundTrack, playerZones, nrounds);
        for(int i=0; i<nrounds; i++){
            round.assignTurn(roundTrack, playerZones, nrounds);
            for(int j=0; j<playerZones.size(); j++){
                System.out.println(playerZones.get(j).getNumber());
            }
            System.out.println("\n");
            roundTrack.addDice(new ArrayList<DieInt>());
            roundTrack.update();
        }
    }

    @Test
    //stampa l'ordine dei 2 o 3 giocatori nei 10 turni
    public void checkAssignment2and3Players(){
        for(int i=0; i<2; i++){
            playerZones.add(new PlayerZone(name+"i", i));
        }
        round=new Round(roundTrack, playerZones, nrounds);
        for(int i=0; i<nrounds; i++){
            round.assignTurn(roundTrack, playerZones, nrounds);
            for(int j=0; j<playerZones.size(); j++){
                System.out.println(playerZones.get(j).getNumber());
            }
            System.out.println("\n");
            roundTrack.addDice(new ArrayList<DieInt>());
            roundTrack.update();
        }
        playerZones.add(new PlayerZone(name, 2));
        Round round2=new Round(roundTrack, playerZones, nrounds);
        for(int i=0; i<nrounds; i++){
            round2.assignTurn(roundTrack, playerZones, nrounds);
            for(int j=0; j<playerZones.size(); j++){
                System.out.println(playerZones.get(j).getNumber());
            }
            System.out.println("\n");
            roundTrack2.addDice(new ArrayList<DieInt>());
            roundTrack2.update();
        }
    }

    @Test
    //4 giocatori, controlla che solo a fine round lo stato sia "FINISHED",
    // che il dado nella draftpool sia trasferito nella roundtrack,
    // controlla che ad ogni turno (senza disconnessioni) 3 giocatori siano in "ENDING" e uno sia in "BEGINNING"
    public void checkEndActionNextPlayer(){
        round=new Round(roundTrack, playerZones, nrounds);
        for(int i=0; i<4; i++){
            player=new PlayerZone(name+"i", i);
            player.setPlayerState(PlayerState.ENDING);
            playerZones.add(player);
        }
        bag= new Bag();
        round = new Round(roundTrack, playerZones, nrounds);
        for(int i=0; i<(turn.length-1); i++){
            roundState=round.endAction(turn, roundTrack, draftPool,round.nextPlayer(playerZones, turn));
            round.nextPlayer(playerZones, turn);
            contBeg=0; contEnd=0;
            for(PlayerZone j : playerZones){
                if(j.getPlayerState().equals(PlayerState.ENDING)) contEnd++;
                else contBeg++;
            }
            assertEquals(3, contEnd);
            assertEquals(1, contBeg);
            assertEquals(RoundState.RUNNING, roundState);
        }
        dice.add(bag.draw());
        draftPool.setInDraft(dice);
        roundState=round.endAction(turn, roundTrack, draftPool,round.nextPlayer(playerZones, turn));
        assertEquals( RoundState.FINISHED, roundState);
        assertEquals(1, roundTrack.getRoundTrackTurn(1).size() );
    }
}