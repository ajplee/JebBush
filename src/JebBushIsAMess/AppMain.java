package JebBushIsAMess;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppMain implements KeyListener, ActionListener {

	Drawing draw = new Drawing();
	JFrame frame = new JFrame("Jeb Bush is a Mess");
	int height = 685; // frame height
	int width = 800; // frame width
	JButton startButton = new JButton("start");
	JPanel jpane = new JPanel();
	ImageIcon startScreen = new ImageIcon("start.jpg");
	ImageIcon background = new ImageIcon("background.jpg");
	ImageIcon gameOver = new ImageIcon("start.jpg");

	List<Enemy> enemies = new ArrayList<>();
	Player player = new Player(10, 450);

	TTimer timer;
	Counter t;

	int gameState = 1; // Start screen
	int level = 1;

	public AppMain() {
		frame.addKeyListener(this);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(draw);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.requestFocus();
	}

	class Drawing extends JComponent {
		public void paint(Graphics g) {
			if (gameState == 1) {
				drawStartScreen();
				g.drawImage(startScreen.getImage(), 0, 0, this);
			} else if (gameState == 2) {
				g.drawImage(background.getImage(), 0, 0, this);
				player.drawPlayer(g);
				for (Enemy e : enemies)
					e.drawEnemy(g);
			} else if (gameState == 3) {
				g.drawImage(gameOver.getImage(), 0, 0, this);
			}
		}

	}

	public void start() {
		timer = new TTimer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (timer) {
					// winConditions();
					// block.fall();
					// if (block.idk)
					// block.idk = timer.fast = false;
				}

				draw.repaint();
			}
		});
		timer.start(); // pauses immediately
		t = new Counter();
		t.start();

	}

	// Timer Class
	// handles the falling of the block timer and in game mechanic thread
	public class TTimer extends Thread {
		long delay;
		boolean pause = false;
		boolean fast = false;
		ActionListener a;

		public TTimer(long adelay, ActionListener aa) {
			delay = adelay;
			a = aa;
		}

		public void run() {
			int i = 0;
			while (i < 100) {
				i += 1;
				try {
				} catch (Exception e) {
				}
				if (!pause)
					a.actionPerformed(null);

			}
			drawGameOver();
		}
	}

	// Counter class (in game time)
	// used to track the time ingame
	public class Counter extends Thread {
		double seconds = 0;
		int minutes = 0;

		public void run() {
			try {
				while (true) {
					sleep(100);
					if (timer != null && !timer.pause)
						seconds += 0.1;
					if (seconds > 59) {
						seconds = 0;
						minutes++;

					}
				}
			} catch (InterruptedException e) {
			}
		}
	}

	void startGame() {
	//	timer.start();
		drawGameBoard();
		makeEnemies(level);
	}

	void drawStartScreen() {
		jpane.setLayout(new GridLayout(1, 1));
		startButton.setOpaque(true);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.addActionListener(this);
		jpane.add(startButton);
		frame.add(jpane);
		frame.revalidate();
		frame.repaint();
	}

	void drawGameBoard() {
		frame.remove(jpane);
		frame.requestFocus();
	}

	void drawGameOver() {

	}

	void makeEnemies(int level) {
		enemies.clear();
		for (int i = 0; i < level; i++) {
			int xLoc = (int)Math.random() * (width - 100);
			enemies.add(new Enemy(xLoc, 1));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			player.moveX(-10);
			break;
		case KeyEvent.VK_RIGHT:
			// block.shift(1);
			player.moveX(10);

			break;
		case KeyEvent.VK_UP:
			// block.rotate();
			break;

		}
		frame.revalidate();
		frame.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == startButton) {
			gameState = 2;
			startGame();
		}
		frame.revalidate();
		frame.repaint();

	}

	public static void main(String[] args) {
		AppMain a = new AppMain();
	}

}
