package JebBushIsAMess;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import java.awt.Color;

public class Bullet {
	private int x_pos;
	private int y_pos;
	public int dir;
	private int xSpeed = 20;
	private int ySpeed = 10;
	int ground = 550;
	int time = 0;
	ImageIcon poo = new ImageIcon("Poop.png");
	ImageIcon broomL = new ImageIcon("broomL.png");
	ImageIcon broomR = new ImageIcon("broomR.png");
	Image ico;
	int type;
	int width;
	int height;

	public Bullet(int x, int y, int dir, int type) {
		x_pos = x;
		y_pos = y;
		this.dir = dir;
		this.type = type;
		if (type == 1) {
			if (dir == -1)
				ico = broomL.getImage();
			else if (type == 1 && dir == 1)
				ico = broomR.getImage();
			width = 70;
			height = 20;
		} else {
			ico = poo.getImage();
			width = height = 25;
		}
	}

	public void moveShot(int speed) {
		x_pos += speed;
	}

	public void drawShot(Graphics g, ImageObserver img, int type) {
		Image image = broomL.getImage();
		if (type == 1)
			image = broomR.getImage();
		else if (type == 2)
			image = poo.getImage();
		g.drawImage(image, x_pos - 13, y_pos - 13, img);
	}

	public boolean checkPlayerHit(int obj_x, int obj_y){
		//above or below
		if(x_pos <= obj_x + 80 && //far right 
		   x_pos + width >= obj_x && //far left
		   y_pos <= obj_y + 100 && //bottom
		   y_pos + height >= obj_y) // top
			return true;
		return false;
	}

	int deltaY() {
		return (int) (ySpeed * time + 0.5 * -2 * Math.pow(time, 2));
	}

	boolean fly(int MAX_WIDTH) {
		time++;
		x_pos += xSpeed * dir;
		y_pos -= deltaY();
		if (x_pos < 0 || x_pos > MAX_WIDTH || y_pos > ground)
			return true;
		else
			return false;
	}
}
