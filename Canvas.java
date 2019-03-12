import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Canvas extends JPanel implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	Timer timer;
	int cels;
	int size;
	Boolean[][] mat;
	int p = 0;

	Canvas(int size) {

		timer = new Timer(10, this);
		timer.start();
		setFocusable(true);
		setBackground(Color.BLACK);
		cels = 100;
		this.size = size / cels;
		addKeyListener(this);

		mat = new Boolean[cels][cels];
		for (int i = 0; i < cels; i++) {
			for (int j = 0; j < cels; j++) {
				Random r = new Random();
				mat[i][j] = r.nextBoolean();
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < cels; i++) {
			for (int j = 0; j < cels; j++) {
				if (mat[i][j]) {
					g2d.setColor(Color.white);
					g2d.fillRect(i * size, j * size, size - 1, size - 1);
				}
			}
		}
		update();
		// g2d.fillRect(p, 150, 20, 20);
		// p++;
	}

	public void update() {
		ArrayList<Boolean> l;
		Boolean[][] next = new Boolean[cels][cels];
		int changes = 0;
		for (int i = 0; i < cels; i++) {
			for (int j = 0; j < cels; j++) {
				l = getN(i, j);
				// System.out.println(l.size());
				if (mat[i][j]) {
					int s = 0;
					for (int z = 0; z < l.size(); z++) {
						if (l.get(z)) {
							s++;
						}
					}

					if (s < 2 || s > 3) {
						// System.out.println("hello");
						next[i][j] = false;
						changes++;
					} else {
						next[i][j] = true;
					}
				} else {
					int s = 0;
					for (int z = 0; z < l.size(); z++) {
						if (l.get(z)) {
							s++;
						}
					}

					if (s == 3) {
						next[i][j] = true;
						changes++;
					} else {
						next[i][j] = false;
					}
				}
			}
		}

		mat = next;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	private ArrayList<Boolean> getN(int i, int j) {

		ArrayList<Boolean> aux = new ArrayList<>();

		if (i == 0 && j == 0) {
			aux.add(mat[1][0]);
			aux.add(mat[0][1]);
			aux.add(mat[1][1]);
			aux.add(mat[cels - 1][cels - 1]);
			aux.add(mat[cels - 1][0]);
			aux.add(mat[0][cels - 1]);
			aux.add(mat[1][cels - 1]);
			aux.add(mat[cels - 1][1]);
		} else if (i == cels - 1 && j == 0) {
			aux.add(mat[i - 1][j]);
			aux.add(mat[i - 1][j + 1]);
			aux.add(mat[i][j + 1]);
			aux.add(mat[0][j]);
			aux.add(mat[0][j + 1]);
			aux.add(mat[0][cels - 1]);
			aux.add(mat[i][cels - 1]);
			aux.add(mat[i - 1][cels - 1]);
		} else if (i == 0 && j == cels - 1) {
			aux.add(mat[i][j - 1]);
			aux.add(mat[i + 1][j - 1]);
			aux.add(mat[i + 1][j]);
			aux.add(mat[i + 1][0]);
			aux.add(mat[i][0]);
			aux.add(mat[cels - 1][j - 1]);
			aux.add(mat[cels - 1][j]);
			aux.add(mat[cels - 1][0]);
		} else if (i == cels - 1 && j == cels - 1) {
			aux.add(mat[i - 1][j - 1]);
			aux.add(mat[i][j - 1]);
			aux.add(mat[i - 1][j]);
			aux.add(mat[0][j - 1]);
			aux.add(mat[0][j]);
			aux.add(mat[i - 1][0]);
			aux.add(mat[i][0]);
			aux.add(mat[0][0]);
		} else if (i == 0) {
			aux.add(mat[i][j - 1]);
			aux.add(mat[i][j + 1]);
			aux.add(mat[i + 1][j]);
			aux.add(mat[i + 1][j - 1]);
			aux.add(mat[i + 1][j + 1]);
			aux.add(mat[cels - 1][j]);
			aux.add(mat[cels - 1][j - 1]);
			aux.add(mat[cels - 1][j + 1]);
		} else if (i == cels - 1) {
			aux.add(mat[i][j - 1]);
			aux.add(mat[i][j + 1]);
			aux.add(mat[i - 1][j]);
			aux.add(mat[i - 1][j - 1]);
			aux.add(mat[i - 1][j + 1]);
			aux.add(mat[0][j]);
			aux.add(mat[0][j - 1]);
			aux.add(mat[0][j + 1]);
		} else if (j == 0) {
			aux.add(mat[i + 1][j]);
			aux.add(mat[i - 1][j]);
			aux.add(mat[i + 1][j + 1]);
			aux.add(mat[i - 1][j + 1]);
			aux.add(mat[i][j + 1]);
			aux.add(mat[i + 1][cels - 1]);
			aux.add(mat[i - 1][cels - 1]);
			aux.add(mat[i][cels - 1]);
		} else if (j == cels - 1) {
			aux.add(mat[i + 1][j]);
			aux.add(mat[i - 1][j]);
			aux.add(mat[i + 1][j - 1]);
			aux.add(mat[i - 1][j - 1]);
			aux.add(mat[i][j - 1]);
			aux.add(mat[i][0]);
			aux.add(mat[i + 1][0]);
			aux.add(mat[i - 1][0]);
		} else {
			aux.add(mat[i + 1][j]);
			aux.add(mat[i - 1][j]);
			aux.add(mat[i + 1][j - 1]);
			aux.add(mat[i - 1][j - 1]);
			aux.add(mat[i][j - 1]);
			aux.add(mat[i][j + 1]);
			aux.add(mat[i + 1][j + 1]);
			aux.add(mat[i - 1][j + 1]);
		}

		return aux;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int id = e.getKeyCode();
		
		if(id== KeyEvent.VK_SPACE) {
			for (int i = 0; i < cels; i++) {
				for (int j = 0; j < cels; j++) {
					mat[i][j] = new Random().nextBoolean();
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
