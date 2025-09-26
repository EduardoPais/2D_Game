package main;

import enemy.Enemy_GreenSlime;
import entity.NPC_OldMan;
import object.OBJ_Chest;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Sword;

public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){

        gp.obj[0]= new OBJ_Chest(gp);
        gp.obj[0].worldX = gp.tileSize * 10;
        gp.obj[0].worldY = gp.tileSize * 16;
    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 7; //7 tiles away from left
        gp.npc[0].worldY = gp.tileSize * 16; //15 tiles away from top
    }

    public void setEnemy(){
        gp.enemy[0] = new  Enemy_GreenSlime(gp);
        gp.enemy[0].worldX = gp.tileSize * 14;
        gp.enemy[0].worldY = gp.tileSize * 16;
    }

}
