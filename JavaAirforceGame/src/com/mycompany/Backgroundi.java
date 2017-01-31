
package com.mycompany;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Backgroundi {

	int bx;
	int by;
	int width;
	int height;
	int yspeed;
	VerticalScroller vs;

	public Backgroundi(VerticalScroller vs, int x, int y) {
		this.bx = x;
		this.by = y;
		this.yspeed = 2;
		this.vs = vs;
	}

	public Backgroundi(VerticalScroller vs) {
		this(vs,0, 0);
	}

	public void update() {
		by += yspeed;
		if (this.by >= vs.getImg().getHeight()) {
			this.by = this.by + (vs.getImg().getHeight() * -2);

		}
	}

	public int getBx() {
		return bx;
	}

	public void setBx(int bx) {
		this.bx = bx;
	}

	public int getBy() {
		return by;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {

		case KeyEvent.VK_SPACE:

			break;

		case KeyEvent.VK_ALT:

			break;

		case KeyEvent.VK_J:

			break;

		case KeyEvent.VK_LEFT:

			break;

		case KeyEvent.VK_RIGHT:
			
			break;

		case KeyEvent.VK_UP:
			System.out.println("Up Pressed");
			yspeed = -12;
			break;

		case KeyEvent.VK_DOWN:

			break;

		}

	}

	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		switch (key) {

		case KeyEvent.VK_UP:
			//yspeed = 0;
			break;
		}
		

	}
}

