package main;

import entity.Entity;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; //16 px * 16 px 
    int scale = 3; //scale para o jogo ficar pixelado mas pequeno
    
    public int tileSize = originalTileSize*scale; //48px * 48 px
    public  int maxScrenCol = 16; //original 16
    public  int maxScrenRow = 12; //original 12
    public  int screenWidth = tileSize*maxScrenCol;   //  768 px - 1920 px
    public  int screenHeight = tileSize*maxScrenRow;  //  576 px - 1056 px

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 20;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int fps =60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public ColisionChecker cChecker = new ColisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;
    public Font arial_20;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //display 10 objects
    public Entity npc[] = new Entity[10];           //display 10 npcs

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel(){
        this.setPreferredSize((new Dimension(screenWidth,screenHeight)));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();
        playMusic(5);
        gameState = titleState;
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void run(){

        double drawInterval = 1000000000/fps;
        double nexDrawTime = System.nanoTime() + drawInterval;
        arial_20 = new Font("Arial", Font.PLAIN, 20);

        while(gameThread != null){
            
            update();

            repaint();

            try { //time traking system

                double remainingTime = nexDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep ((long) remainingTime);

                nexDrawTime += drawInterval; 

            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }
    }

    public void update() {

        if (gameState == playState){
            //Player
            player.update();

            //NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }

        }
        else if (gameState == pauseState){
            //nothing
        }
        else if (gameState == dialogueState){
            //nothing
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Debug
        long drawStart = 0;
        if (keyH.debug == true){
            drawStart = System.nanoTime();
        }

        //Title Screen
        if (gameState == titleState){
            ui.draw(g2);
        }
        //Others
        else{
            //Tile
            tileM.draw(g2);

            //Objects
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }
            //NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].draw(g2);
                }
            }

            //Player
            player.draw(g2);

            //UI
            ui.draw(g2);

        }
        //Debug
        if (keyH.debug == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.setFont( arial_20);
            g2.drawString("Draw Time: " + passed, 10, 548);
            System.out.println("Draw Time: " + passed);
        }

        


        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        se.setFile(i);
        se.play();
    }

}
