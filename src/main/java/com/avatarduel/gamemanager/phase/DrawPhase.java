package com.avatarduel.gamemanager.phase;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.*;

public class DrawPhase extends Phase {
    // ctor
    public DrawPhase(GameManager game) {
        super(game, PhaseType.DRAW);
    }
    public void nextPhase() {
        game.changePhase(new MainPhase(game));
    }
    public void phaseInfo() {
        System.out.println("Starting draw phase");
    }
    public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) throws Exception {
        Player player;
        player = game.getPlayer();
        player.draw();
        player.resetPower();
        for(int i = 0; i < 6; i++){
            try{
                player.getCharacterAtPos(i).setJustSummoned(false);
            } catch (Exception e){

            }
        }
    }

}