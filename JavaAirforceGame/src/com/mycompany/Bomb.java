package com.mycompany;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bomb {

	int x;
	int y;
	int dx;
	int dy;
	int width;
	int height;
	BufferedImage bimg;
	Sprite owner;
	String imagePath = "images/bomb.png";
	int speed = -5;
	boolean visibel;
	double angle;

	public Bomb(Sprite owner) {
		this.owner = owner;
		x = owner.getX() + owner.getWidth() / 2;
		y = owner.getY();// + owner.getHeight() / 2;
		loadImage(imagePath);
		width = bimg.getWidth();
		height = bimg.getHeight();
		this.angle = owner.getAngle();

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
		angle = owner.getAngle();
		int vx = (int) (speed * Math.cos(Math.toRadians(angle+90)));
		int vy = (int) (speed * Math.sin(Math.toRadians(angle+90)));
		x += vx;
		y += vy;
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

	public Sprite getOwner() {
		return owner;
	}

	public void setOwner(Sprite owner) {
		this.owner = owner;
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
