package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        
        getImage();
        setDialogue();
    }

    public void getImage(){

        up1 = setup("/npc/oldman/back_1.png");
        up2 = setup("/npc/oldman/back_2.png");
        down1 = setup("/npc/oldman/front_1.png");
        down2 = setup("/npc/oldman/front_2.png");
        left1 = setup("/npc/oldman/left_1.png");
        left2 = setup("/npc/oldman/left_2.png");
        right1 = setup("/npc/oldman/right_1.png");
        right2 = setup("/npc/oldman/right_2.png");
    }

    public void setDialogue(){
        dialogues[0] = "Hello, Forsaken One.";
        dialogues[1] = "The path ahead is veiled in shadows.";
        dialogues[2] = "Do you hear the whispers \n beneath the earth?";
        dialogues[3] = "Few dare to tread these \nforsaken lands.";
        dialogues[4] = "Once, this kingdom shone \nwith light...  now only echoes remain.";
        dialogues[5] = "Tread lightly, wanderer. \n Danger lurks ahead.";
        /*
        dialogues[5] = "Every step you take binds you tighter to fate.";
        dialogues[6] = "The air here reeks of forgotten blood.";
        dialogues[7] = "Do not trust the silence. It watches you.";
        dialogues[8] = "Those who linger too long are claimed by the abyss.";
        dialogues[9] = "You carry a burden heavier than any blade.";
        dialogues[10] = "Many sought the truth here. None returned unchanged.";
        dialogues[11] = "The old king still waits, deep below.";
        dialogues[12] = "Even stars die, little one. What hope has man?";
        dialogues[13] = "If you falter, the void shall embrace you.";
        dialogues[14] = "Not all doors should be opened.";
        dialogues[15] = "The bells toll for the lost souls wandering still.";
        */
    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 90){

            Random random = new Random();
            int i = random.nextInt(100)+1; //pick a number from 1 to 100
    
            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75){
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    public void speak(){

        //do this character specific stuff

        super.speak();
    }
}
