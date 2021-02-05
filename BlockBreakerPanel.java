import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener {
	final int STEP = 15;
	ArrayList<Block> blocks;
	Block ball;
	Block paddle;
	
	Thread thread;
	JFrame frame, startScreen;	
	
	void reset() {
		blocks = new ArrayList<Block>();
		ball = new Block(237,435,25,25,"ball.png");
		paddle = new Block(190,480,120,7,"paddle.jpg");
		
		for (int i=0; i<10; i++)
			blocks.add(new Block(i*50, 0, 50, 25, "pink.jpg"));
		for (int i=0; i<10; i++)
			blocks.add(new Block(i*50, 25, 50, 25, "yellow.jpg"));
		for (int i=0; i<10; i++)
			blocks.add(new Block(i*50, 50, 50, 25, "green.jpg"));
		for (int i=0; i<10; i++)
			blocks.add(new Block(i*50, 75, 50, 25, "blue.jpg"));
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	BlockBreakerPanel(JFrame fr, JFrame start) {
		this.frame = fr;
		this.startScreen = start;
		thread = null;
		reset();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		blocks.forEach(bl -> bl.draw(g, this));
		ball.draw(g, this);
		paddle.draw(g, this);
	}

	public void update() {
		ball.x += ball.movX;
		
		if(ball.x > (getWidth() - ball.width) || ball.x < 0) 
			ball.movX *= -1; ball.y += ball.movY/2;
		
		if(ball.y < 0 || ball.intersects(paddle)) 
			ball.movY *= -1;
		
		ball.y += ball.movY;
		
		if(ball.y > getHeight()) {
			frame.setVisible(false);
			thread = null;
			reset();
			startScreen.setVisible(true);
		}
		
		blocks.forEach(bl -> {
			if(ball.intersects(bl) && !bl.destroyed) {
				bl.destroyed = true;
				ball.movY *= -1;
			}
		});
		
		repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			thread = new Thread(() -> {
				while(true) {
					update();
					try {
						Thread.sleep(10);
					} catch(InterruptedException err) {
						err.printStackTrace();
					}
				}
			});
			thread.start();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width - STEP)) {
			paddle.x += STEP;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
			paddle.x -= STEP;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
