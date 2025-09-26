package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);
        this.keyH= keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues(){

        worldX = gp.tileSize * 5; //5 tiles away from left
        worldY = gp.tileSize * 15; //5 tiles away from top
        speed = 5;
        direction = "down";

        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        
    }

    public void getPlayerImage(){

        up1 = setup("/player/back_1.png");
        up2 = setup("/player/back_2.png");
        down1 = setup("/player/front_1.png");
        down2 = setup("/player/front_2.png");
        left1 = setup("/player/left_1.png");
        left2 = setup("/player/left_2.png");
        right1 = setup("/player/right_1.png");
        right2 = setup("/player/right_2.png");
    }

    public void update (){
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){ //não alternar enquanto parado
            if(keyH.upPressed == true)      {direction="up";    }
            if(keyH.downPressed == true)    {direction="down";  }
            if(keyH.leftPressed == true)    {direction="left";  }
            if(keyH.rightPressed == true)   {direction="right"; }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex); //interact with NPC

            //CHECK EVENT
            gp.eHandler.checkEvent();

            keyH.actionPressed = false;


            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                switch (direction) {
                    case "up":      worldY = worldY - speed; break;
                    case "down":    worldY = worldY + speed; break;
                    case "left":    worldX = worldX - speed; break;
                    case "right":   worldX = worldX + speed; break;  
                }
            }

            spriteCounter++;
            if(spriteCounter > 15){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else{
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){

        if(i != 999){

            
        }
    }

    public void draw(Graphics2D g2){
        
        BufferedImage image = null;

        switch (direction) {
            case "up": 
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
            break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
        //HITBOX DEBUG
        if (keyH.debug == true){
            g2.setColor(Color.red);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }

    public void interactNPC(int i){
        if(i != 999){
            if(keyH.actionPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }


}
