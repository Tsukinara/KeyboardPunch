import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PianoPanel extends JPanel {
	private static final long serialVersionUID = 3875360370099517808L;

	final int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
			.getWidth(); // size of panel
	final int keylength = 200; // size of keys
	final int offset = width / 30; // offset for formatting
	final int leftOffset=10;
	int widthWhite = width / 45;
	int widthBlack = widthWhite * 2 / 3;
	int space = widthWhite * 7;
	ArrayList<Integer> whiteNotes;
	ArrayList<Integer> blackNotes;
	int[] whiteSpaces;
	int[] whiteSpacesColor;
	int[] blackSpaces;
	int[] blackSpacesColor;

	public PianoPanel() {
		super();
		whiteNotes = new ArrayList<Integer>();
		blackNotes = new ArrayList<Integer>();
		whiteNotes.add(1);
		whiteNotes.add(3);
		whiteNotes.add(5);
		whiteNotes.add(7);
		whiteNotes.add(8);
		whiteNotes.add(10);
		whiteNotes.add(0);
		blackNotes.add(2);
		blackNotes.add(4);
		blackNotes.add(6);
		blackNotes.add(9);
		blackNotes.add(11);

		whiteSpaces = new int[45];
		blackSpaces = new int[44];
		whiteSpacesColor = new int[45];
		blackSpacesColor = new int[44];

		setBackground(new Color(0xbbddff));

	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawLine(0, 0, getWidth(), 0);
		for (int i = 0; i < 45; i++) {
			drawWhite(i * widthWhite+leftOffset, offset, g);
		}

		// groups of 3
		for (int i = (2 * widthWhite - widthBlack / 2); i < (width - 3 * widthWhite); i += space) {
			drawBlack(i+leftOffset, offset, g);
			drawBlack(i +leftOffset+ widthWhite, offset, g);
			drawBlack(i +leftOffset+ 2 * widthWhite, offset, g);
		}

		// groups of 2
		for (int i = (6 * widthWhite - widthBlack / 2); i < width; i += space) {
			drawBlack(i+leftOffset, offset, g);
			drawBlack(i + widthWhite+leftOffset, offset, g);
		}

		// last key (black)
		drawBlack(width - widthWhite * 2+leftOffset, offset, g);

		// array of pointers
		drawArray(g);
			
		
	}

	public void drawWhite(int x, int y, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x+leftOffset, y, widthWhite, keylength);
		g.setColor(Color.BLACK);
		g.drawRect(x+leftOffset, y, widthWhite, keylength);
	}

	public void drawArray(Graphics g) {
		// white keys
		for (int i = 0; i < 45; i++) {
			if (whiteSpaces[i] == 1) {
				if (whiteSpacesColor[i] == 0)
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.RED);
				g.fillRect(i * widthWhite+leftOffset, offset + keylength + 5, widthWhite,
						5);

			}
		}

		for (int i = 0; i < 44; i++) {
			if (blackSpaces[i] == 1) {
				if (blackSpacesColor[i] == 0)
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.RED);
				g.fillRect(widthWhite * i + widthWhite - widthBlack / 2+leftOffset,
						offset - 8, widthBlack, 5);
			}
		}

	}

	public void drawBlack(int x, int y, Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x+leftOffset, y, widthBlack, keylength * 3 / 5);

	}

	private int whitePosition(int i) {
		int whitePos;
		switch (i) {

		case 1:
			whitePos = 1;
			break;
		case 3:
			whitePos = 2;
			break;
		case 5:
			whitePos = 3;
			break;
		case 7:
			whitePos = 4;
			break;
		case 8:
			whitePos = 5;
			break;
		case 10:
			whitePos = 6;
			break;
		case 0:
			whitePos = 7;
			break;

		default:
			whitePos = 1;
		}
		return whitePos;
	}

	private int blackPosition(int i) {
		int blackPos;
		switch (i) {

		case 2:
			blackPos = 1;
			break;
		case 4:
			blackPos = 2;
			break;
		case 6:
			blackPos = 3;
			break;
		case 9:
			blackPos = 5;
			break;
		case 11:
			blackPos = 6;
			break;

		default:
			blackPos = 1;
		}
		return blackPos;
	}

	// lowest 28 E
	// highest 103 G
	public void notePlayed(int note, int code) {
		note = note - 28;

		if (note == 0) {
			whiteSpaces[0] = 1;
			whiteSpacesColor[0] = code;
		}

		else if (whiteNotes.contains((note % 12))) {
			if (note % 12 == 0) {
				whiteSpaces[whitePosition(note % 12) + 7
				            * ((int) note / 12 - 1)] = 1;
				whiteSpacesColor[whitePosition(note % 12) + 7
				                 * ((int) note / 12 - 1)] = code;
			} else {
				whiteSpaces[whitePosition(note % 12) + 7 * ((int) note / 12)] = 1;
				whiteSpacesColor[whitePosition(note % 12) + 7
				                 * ((int) note / 12)] = code;
			}
		} else {
			blackSpaces[blackPosition(note % 12) + 7 * ((int) note / 12)] = 1;
			blackSpacesColor[blackPosition(note % 12) + 7 * ((int) note / 12)] = code;
		}
		repaint();

	}

	public void noteReleased(int note) {
		note = note - 28;

		if (note == 0) {
			whiteSpaces[0] = 0;
		}

		else if (whiteNotes.contains((note % 12))) {
			if (note % 12 == 0) {
				whiteSpaces[whitePosition(note % 12) + 7
				            * ((int) note / 12 - 1)] = 0;
			} else {
				whiteSpaces[whitePosition(note % 12) + 7 * ((int) note / 12)] = 0;
			}
		} else {
			blackSpaces[blackPosition(note % 12) + 7 * ((int) note / 12)] = 0;

		}
		repaint();

	}

}