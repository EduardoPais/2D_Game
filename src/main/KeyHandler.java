package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    
    public boolean upPressed, downPressed, leftPressed, rightPressed, actionPressed;
    public boolean debug;

    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(gp.gameState == gp.titleState){      //title state
            
            if(gp.ui.titleScreenState == 0){
                if(code == KeyEvent.VK_UP){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_DOWN){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2){
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1;
                        
                    }
                    if(gp.ui.commandNum == 1){
                        //load game
                    }
                    if(gp.ui.commandNum == 2){
                        System.exit(0);
                    }
                }
            }else if(gp.ui.titleScreenState == 1){
                
                if (code == KeyEvent.VK_ENTER){
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(0);
                    
                }
                    
            }

        }else if(gp.gameState == gp.playState){  //play state
            if(code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if (code == KeyEvent.VK_DOWN){
                downPressed = true;
            }
            if (code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if (code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }
            //DEBUG
            if (code == KeyEvent.VK_U){
                if (debug == false){
                    debug = true;
                } else if (debug == true){
                    debug = false;
                }
            }
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            
            }
            if (code == KeyEvent.VK_X){
                actionPressed = true;
            }
        }else if (gp.gameState == gp.pauseState){ //pause state
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }else if (gp.gameState == gp.dialogueState){ //dialogue state
            if (code == KeyEvent.VK_X){
                gp.gameState = gp.playState;
            }
        }
    }

        

    

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }

}
