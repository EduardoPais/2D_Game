package main;

public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){

        gp.obj[0] = new object.OBJ_Key(gp);
        gp.obj[0].worldX = 3 * gp.tileSize;
        gp.obj[0].worldY = 17 * gp.tileSize;

        gp.obj[1] = new object.OBJ_Sword(gp);
        gp.obj[1].worldX = 9 * gp.tileSize;
        gp.obj[1].worldY = 16 * gp.tileSize;

        gp.obj[2] = new object.OBJ_Heart(gp);
        gp.obj[2].worldX = 13 * gp.tileSize;
        gp.obj[2].worldY = 17 * gp.tileSize;

        gp.obj[3] = new object.OBJ_Chest(gp);
        gp.obj[3].worldX = 15 * gp.tileSize;
        gp.obj[3].worldY = 17 * gp.tileSize;

    }

}
