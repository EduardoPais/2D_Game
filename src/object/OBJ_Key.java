package object;

import main.GamePanel;

public class OBJ_Key extends SuperObject {

    GamePanel gp;

    public OBJ_Key(GamePanel gp) {

        this.gp = gp;

        name = "Key";
        try {
            image1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/key_1.png"));
            uTool.scaleImage(image1,gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
