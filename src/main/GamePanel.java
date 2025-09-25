package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; //16 px * 16 px 
    final int scale = 3; //scale para o jogo ficar pixelado mas pequeno
    
    public final int tileSize = originalTileSize*scale; //48px * 48 px
    final int maxScrenCol = 40; //original 16
    final int maxScrenRow = 22; //original 12
    final int screenWidth = tileSize*maxScrenCol;   //  768 px - 1920 px
    final int screenHeight = tileSize*maxScrenRow;  //  576 px - 1056 px
    
    //FPS
    int fps =60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel(){
        this.setPreferredSize((new Dimension(screenWidth,screenHeight)));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void run(){

        double drawInterval = 1000000000/fps;
        double nexDrawTime = System.nanoTime() + drawInterval;

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

        player.update();

    }


    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
