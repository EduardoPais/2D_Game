package object;

import main.GamePanel;

public class OBJ_Chest extends SuperObject{
    
    GamePanel gp;

    public OBJ_Chest(GamePanel gp){ 

        this.gp = gp;

        name = "Chest";
        try {
            image1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/chest_1.png"));
            uTool.scaleImage(image1,gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
