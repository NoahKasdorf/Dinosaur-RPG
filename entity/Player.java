package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 100 * 10;
        worldY = 100 * 10;
        speed = 4;

        direction = "right";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_left3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/player/cat_right3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update() {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            
            if (keyH.upPressed == true) {
                worldY -= speed;
                direction = "up";
            } else if (keyH.downPressed == true) {
                worldY += speed;
                direction = "down";
            } else if (keyH.leftPressed == true) {
                worldX -= speed;
                direction = "left";
            } else if (keyH.rightPressed == true) {
                worldX += speed;
                direction = "right";
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                } 
                if(spriteNum == 2) { 
                    image = up2;
                } 
                if(spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                } 
                if(spriteNum == 2) {
                    image = down2;
                } 
                if(spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                } 
                if(spriteNum == 2) {
                    image = left2;
                } 
                if(spriteNum == 3) {
                    image = left3;
                }             
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }   
                if(spriteNum == 2) {
                    image = right2;
                } 
                if(spriteNum == 3) {
                    image = right3;
                }              
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
