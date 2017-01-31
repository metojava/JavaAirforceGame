package com.mycompany;
import java.util.TimerTask;

import javax.swing.JPanel;

public class EnemyScheduler extends TimerTask {

	VerticalScroller game;
	static int enemyCount=0;

	public EnemyScheduler(VerticalScroller game) {
		this.game = game;
	}

	@Override
	public void run() {
		enemyCount++;
		if(!(enemyCount == 11)){
		Enemy enemy = new Enemy(game);
		enemyCount++;
		game.getEnemies().add(enemy);
		System.out.println("enemy added at - "+enemy.getX()+" - "+enemy.getY());
		}
	}

	public static int getEnemyCount() {
		return enemyCount;
	}

	
}
