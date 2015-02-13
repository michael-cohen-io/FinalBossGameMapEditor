package Tests;

import javax.swing.JFrame;

public class ImageSpliterTest {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	
	public static void main(String [] args)
	{
		JFrame frame = new TestWindow(64, 64, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
