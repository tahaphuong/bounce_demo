import javax.swing.JButton;
import javax.swing.JFrame;
public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Bounce");
		JFrame startScreen = new JFrame("Bounce");
		BlockBreakerPanel panel = new BlockBreakerPanel(frame, startScreen);

		JButton startButton = new JButton("Let's start");
		startButton.addActionListener(l -> {
			startScreen.setVisible(false);
			frame.setVisible(true);
		});
		
		startScreen.getContentPane().add(startButton);
		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreen.setVisible(true);
		startScreen.setSize(500, 600);
		startScreen.setResizable(false);
		
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);
		frame.setSize(500, 600);
		frame.setResizable(false);
	} 
}
