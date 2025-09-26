package main;

import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font Alucrads, GothicByte;
    BufferedImage heart_full, heart_half, heart_empty;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; //0 = first screen, 1 = second screen

    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/Alucrads.ttf");
            Alucrads = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/GothicByte.ttf");
            GothicByte = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //CREATE HUD OBJECT
        Entity heart = new object.OBJ_Heart(gp);
        heart_full = heart.image1;
        heart_half = heart.image2;
        heart_empty = heart.image3;


    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
        
    }

    public void draw(Graphics2D g2){
        
        this.g2 = g2;

        g2.setFont(GothicByte);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState){ //Title State
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState){   //Play State
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState){  //Pause State
            drawPlayerLife();
            drawPauseScreen();
        }
        if(gp.gameState == gp.dialogueState){ //Dialogue State
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x, y;
        g2.setFont(GothicByte.deriveFont(Font.PLAIN, 80F));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //anri aliasing for smoother text

        g2.setColor(Color.GRAY);

        //Shadow
        x = getXforCenteredText(text);
        y = gp.screenHeight/2;
        g2.drawString(text, x+5, y+5);

        //Main
        g2.setColor(Color.BLACK);
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(GothicByte.deriveFont(Font.PLAIN, 36F)); 
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //anri aliasing for smoother text
        Color c = new Color(255,255, 255);
        g2.setColor(c);

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y+= 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(44,44, 84, 200); //last parameter is transparency
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 25, 25);

        c = new Color(116, 116, 180);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 25, 25);
    }

    public int getXforCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void drawTitleScreen(){

        if(titleScreenState == 0){
            drawTitleScreen1();
        }
        else if(titleScreenState == 1){
            drawTitleScreen2();
        }
    }

    public void drawTitleScreen1(){
        g2.setColor(new Color(50,50,90));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //Title Name
        g2.setFont(GothicByte.deriveFont(Font.PLAIN, 96F));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //anri aliasing for smoother text
        String text = "Forsaken One";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        //Shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+5, y+5);

        //Main Text
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //Player Image
        int playerScale = 2;
        x = gp.screenWidth/2 - (gp.tileSize*playerScale)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*playerScale, gp.tileSize*playerScale, null);

        //Menu
        g2.setFont(GothicByte.deriveFont(Font.PLAIN, 48F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y - 1);
        }

        
        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 1.2;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y - 1);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize * 1.2;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y - 1);
        }
    }

    public void drawTitleScreen2(){
        
        g2.setColor(new Color(50,50,90));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setColor(new Color(255,255,255));
        g2.setFont(GothicByte.deriveFont(64F));
        //String text_1 = "Controls";
        String text_1 = "Lore";
        int x = getXforCenteredText(text_1);
        int y = gp.tileSize * 3;
        g2.drawString(text_1, x, y);

        g2.setFont(GothicByte.deriveFont(36F));
        //String text_2 = "Arrow Keys Left/Right - Move Left/Right\nArrow Keys Up/Down - Look Up/Down \nSpace - Jump \nEnter - Action \nX - Attack \nC - Dash \nZ - Heal";
        String text_2 = "Long ago, the kingdom of Elaria shone bright\nBut darkness crept across the land\nA hero rises to restore balance\nRuins hide secrets of lost magic\nShadows grow stronger each day\nOnly the brave can face the trials\n";
        y += 72;
        for(String line : text_2.split("\n")){
            x = getXforCenteredText(line);
            g2.drawString(line, x, y);
            y+= 40;
        }

        
    }

    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        //DRAW MAX LIFE
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //DRAW CURRENT LIFE
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
}

