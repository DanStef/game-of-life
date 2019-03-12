import javax.swing.JFrame;

public class Main {
	static int height = 900;
	static int width = 935;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.pack();
		frame.setSize(height, width);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Canvas(900));
		frame.setVisible(true);
	}
}
