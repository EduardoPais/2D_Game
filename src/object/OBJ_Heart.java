package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {

        super(gp);

        name = "Heart";
        image1 = setup("/objects/heart_full.png");
        image2 = setup("/objects/heart_half.png");
        image3 = setup("/objects/heart_empty.png");

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
