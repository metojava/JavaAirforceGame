package com.mycompany;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VerticalScroller extends JPanel implements Runnable {

	Backgroundi bgone;
	Backgroundi bgtwo;
	int mouseX;
	int mouseY;
	AudioInputStream audio;
	Clip clip;
	BufferedImage img;
	String imagePath = "images/background.png";
	Sprite rocket = null;
	List<Enemy> enemies;
	EnemyScheduler enemyScheduler;
	double angle = 0.0;

	public double getAngle() {
		return rocket.getAngle();
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public BufferedImage getImg() {
		return img;
	}

	public void loadImage(String pathToImage) {
		try {
			img = ImageIO.read(new File(pathToImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public VerticalScroller() {
		loadImage(imagePath);
		this.bgone = new Backgroundi(this, 0, 0);
		this.bgtwo = new Backgroundi(this, 0, -1 * img.getHeight());
		
		enemyScheduler = new EnemyScheduler(this);
		Timer timer = new Timer();
		timer.schedule(enemyScheduler, 0, 4000);
		enemies = new ArrayList<Enemy>(20);

		rocket = new Sprite(this);

		new Thread(this).start();
		setVisible(true);

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				
				try {
					audio = AudioSystem.getAudioInputStream(new File("audio/ball.wav"));
					clip = AudioSystem.getClip();
					clip.open(audio);
					clip.loop(0);
					//clip.stop();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				bgone.keyPressed(e);
				bgtwo.keyPressed(e);
				rocket.keyPressed(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				bgone.keyPressed(e);
				bgtwo.keyPressed(e);
				rocket.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				bgone.keyReleased(e);
				bgtwo.keyReleased(e);
				rocket.keyReleased(e);

			}
		});
		setFocusable(true);

	}

	@Override
	public void run() {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}

	public static void main(String[] args) {
		VerticalScroller game = new VerticalScroller();
		JFrame frame = new JFrame("Mini Tennis");

		frame.add(game);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {

			game.update();
			game.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	

	public void update() {
		//paint(g);
		bgone.update();
		bgtwo.update();
		
		updateEntities();
		rocket.update();
		if (enemyScheduler.getEnemyCount() == 10) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameOver();

		}
	}


//	@Override
//	public void paint(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.gray);
//
//		bgone.draw(g2d);
//		bgtwo.draw(g2d);
//		System.out.println(bgone.getBy() + "<-1  2->" + bgtwo.getBy());
//
//		g2d.drawString(mouseX + " - " + mouseY, 10, 10);
//		Toolkit.getDefaultToolkit().sync();
//
//	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.gray);
		
		g.drawImage(img, bgone.getBx(), bgone.getBy(), null);
		g.drawImage(img, bgtwo.getBx(), bgtwo.getBy(), null);
		System.out.println(bgone.getBy()+"<-1  2->"+bgtwo.getBy());
		//rocket.draw(g2d);
		g2d.drawString(mouseX + " - " + mouseY, 10, 10);
		//rocket.draw(g2d);
		rocket.drawTurned(g2d);
		// ArrayList<Bomb> bombs = rocket.getBombs();
		for (Bomb b : rocket.getBombs()) {
			Bomb bom = (Bomb) b;
			bom.draw(g2d);
		}
		for (Enemy e : getEnemies()) {
			e.draw(g2d);
			System.out.println("enemies - "+getEnemies().size());
		}
		Toolkit.getDefaultToolkit().sync();

	}
	
	private void updateEntities() {

		List<Bomb> bombs = rocket.getBombs();
		List<Enemy> enemies = getEnemies();

		for (int i = 0; i < bombs.size(); i++) {
			Bomb bom = bombs.get(i);
			if (bom.isVisibel()) {
				bom.move();
				for (int j = 0; j < enemies.size(); j++) {
					if (enemies.get(j).intersectsWith(bom.getBounds())) {
						bom.setVisibel(false);
						enemies.remove(j);
					}
				}
				System.out.println("position of bomb - " + bom.getX() + " - "
						+ bom.getY());
			} else {
				System.out.println("removed Bomb N - " + i);
				bombs.remove(i);

			}

		}

		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.move();
			System.out.println("position of bomb - " + enemy.getX() + " - "
					+ enemy.getY());
			if (rocket.intersectsWith(enemy.getBounds()))
				gameOver();
			if (enemy.getY() == this.getHeight() - 10)
				enemies.remove(i);

		}

	}
	
	public boolean inWindow(int x,int y)
	{
		int width = getWidth();
		int height = getHeight();
		if(x<0||x>width)return false;
		if(y<0|| y>height)return false;
		return true;
	}
	public void gameOver() {

		JOptionPane.showMessageDialog(this, "Game Over");
		exitGame();

	}

	private void exitGame() {
		System.exit(0);
		// this.dispatchEvent(new WindowEvent((Window) this.getParent(),
		// WindowEvent.WINDOW_CLOSING));
	}
}
