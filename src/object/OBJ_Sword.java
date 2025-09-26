package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword extends Entity {

    public OBJ_Sword(GamePanel gp) {

        super(gp);

        name = "Sword";
        down1 = setup("/objects/sword_1.png");

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
