package object;

import main.GamePanel;

public class OBJ_Sword extends SuperObject {

    GamePanel gp;

    public OBJ_Sword(GamePanel gp) {

        this.gp = gp;

        name = "Sword";
        try {
            image1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/sword_1.png"));
            uTool.scaleImage(image1,gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
