package object;

import main.GamePanel;

public class OBJ_Heart extends SuperObject {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {

        this.gp = gp;

        name = "Heart";
        try {
            image1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/heart_empty.png"));
            image1 = uTool.scaleImage(image1,gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2,gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3,gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
