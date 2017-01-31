package com.mycompany;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Enemy {

	int x;
	int y;
	int dx;
	int dy;
	int width;
	int height;
	BufferedImage bimg;
	JPanel jpanel;
	String imagePath = "images/enemy.png";
	int speed = 3;
	boolean visibel;

	Random rnd = new Random();
	public Enemy(JPanel jpanel) {
		this.jpanel = jpanel;
		x = rnd.nextInt(300);
		y = rnd.nextInt(10);
		loadImage(imagePath);
		width = bimg.getWidth();
		height = bimg.getHeight();

	}

	public void loadImage(String pathToImage) {
		try {
			bimg = ImageIO.read(new File(pathToImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void move() {
		y += speed;
		if (getY() == 0)
			visibel = false;
	}

	public void draw(Graphics2D g2d) {

		g2d.drawImage(bimg, x, y, null);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public BufferedImage getImg() {
		return bimg;
	}

	public void setImg(BufferedImage img) {
		this.bimg = img;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isVisibel() {
		return visibel;
	}

	public void setVisibel(boolean visibel) {
		this.visibel = visibel;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public boolean intersectsWith(Rectangle rect) {
		return getBounds().intersects(rect);
	}
	
}
