package com.mycompany;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Sprite {

	List<Bomb> bombs;
	int x;
	int y;
	int dx;
	int dy;
	int width;
	int height;
	boolean visi;
	int step = 5;
	String imagePath = "images/airforce.png";
	JPanel jpanel;
	BufferedImage img;
	double angle = 0.0;
	
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public JPanel getJpanel() {
		return jpanel;
	}

	public Sprite(JPanel jpanel) {
		bombs = new ArrayList<Bomb>();
		this.jpanel = jpanel;
		x = 210;
		y = 210;
		try {
			loadImage(imagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = img.getWidth();
		height = img.getHeight();

	}

	public void loadImage(String pathToImage) throws IOException {
		//img = ImageIO.read(new File(pathToImage));
		img = Transparency.makeTransparentBackground(pathToImage);
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void move() {
		x += dx;
		if (x < 0)
			x = 0;
		if (x > getJpanel().getWidth() - this.getWidth() / 2) {
			x = getJpanel().getWidth() - this.getWidth();
		}
		y += dy;
		if (y < 0)
			y = 0;
		if (y > getJpanel().getHeight() - this.getHeight() / 2)
			y = getJpanel().getHeight() - this.getHeight() / 2;
	}

	public void draw(Graphics2D g2d) {

		g2d.drawImage(img, x, y, null);
	}
	
	public void drawTurned(Graphics2D g2d)
	{
		AffineTransform backup = g2d.getTransform();
		AffineTransform trans = new AffineTransform ();
		trans.rotate(Math.toRadians(angle),this.getX(),this.getY());
		g2d.transform(trans);
		
		g2d.drawImage(img, x,y,null);
		g2d.setTransform(backup);
		
	}

	// if ((x > panel.getWidth() - 30) || x < 0)
	// xa *= -1;
	// if ((y > panel.getHeight() - 30) || y < 0)
	// ya *= -1;
	// x = x + xa;
	// y = y + ya;
	// if(collides(panel.getBase().getBounds()))ya *= -1;
	// if(y > panel.getBase().getBaseY())
	// {panel.gameOver();}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		switch (key) {

		case KeyEvent.VK_T:
			angle+=5.0;
			if(angle==360)
				angle=0;
			break;
			
		case KeyEvent.VK_SPACE:
			shoot();
			break;

		case KeyEvent.VK_LEFT:
			dx = -2;
			break;

		case KeyEvent.VK_RIGHT:
			dx = 2;
			break;

		case KeyEvent.VK_UP:
			dy = -2;
			break;

		case KeyEvent.VK_DOWN:
			dy = 2;
			break;

		}

	}

	public List<Bomb> getBombs() {
		return bombs;
	}

	public void setBombs(List<Bomb> bombs) {
		this.bombs = bombs;
	}

	private void shoot() {
		Bomb bom = new Bomb(this);
		bom.setVisibel(true);
		bombs.add(bom);
		System.out.println("have bombs - " + bombs.size() + " - bomb added "
				+ " -at position " + bom.getX() + " " + bom.getY());

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {

		case KeyEvent.VK_LEFT:
			dx = 0;
			break;

		case KeyEvent.VK_RIGHT:
			dx = 0;
			break;

		case KeyEvent.VK_UP:
			dy = 0;
			break;

		case KeyEvent.VK_DOWN:
			dy = 0;
			break;

		}
	}

	
	public boolean isVisi() {
		return visi;
	}

	public void setVisi(boolean visi) {
		this.visi = visi;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public boolean intersectsWith(Rectangle rect) {
		return getBounds().intersects(rect);
	}

	public void update() {

		
		move();

	}

	

}
