package it.polimi.ingsw.LM26.GamePhases;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;
import org.junit.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CentralPhaseTest {

    private ArrayList<PlayerZone> playerZones= new ArrayList<PlayerZone>();
    private String name="name";
    private int[] v2 ={1,2,2,1};
    private int[] v3 ={1,2,3,3,2,1};
    private int[] v4 ={1,2,3,4,4,3,2,1};

    @Test
    public void checkSetOrder234players(){
        for(int i=0; i<2; i++){
            playerZones.add(new PlayerZone(name, i));
        }
        CentralPhase centralPhase = new CentralPhase(playerZones);
        assertArrayEquals(centralPhase.getTurn(), v2);
        System.out.println(Arrays.toString(centralPhase.getTurn()));
        System.out.println("\n");
        playerZones.add(new PlayerZone(name, 2));
        CentralPhase centralPhase2 = new CentralPhase(playerZones);
        assertArrayEquals(centralPhase2.getTurn(), v3);
        System.out.println(Arrays.toString(centralPhase2.getTurn()));
        System.out.println("\n");
        playerZones.add(new PlayerZone(name, 3));
        CentralPhase centralPhase3 = new CentralPhase(playerZones);
        assertArrayEquals(centralPhase3.getTurn(), v4);
        System.out.println(Arrays.toString(centralPhase3.getTurn()));
    }
}