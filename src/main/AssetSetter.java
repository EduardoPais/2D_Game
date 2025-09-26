package main;

import entity.NPC_OldMan;

public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        
    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 7; //7 tiles away from left
        gp.npc[0].worldY = gp.tileSize * 16; //15 tiles away from top
    }

}
