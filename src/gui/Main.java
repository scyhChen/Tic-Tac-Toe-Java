/* NetId(s): ts648, yc598. Time spent: 20 hours, 00 minutes. */

package gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.*;

import javax.swing.*;

import controller.Controller;
import controller.DumbAI;
import controller.RandomAI;
import controller.SmartAI;
import gui.FirstJFrame;

public class Main extends JFrame implements GameListener{
	
	private ArrayList<Square> grids = new ArrayList<Square>();
	private String status;
	private JLabel title;
	
	/**inner class: square
	 * paint a white square with black border*/
	public class Square extends JPanel implements MouseListener {
		protected final int i;
		protected final int j;
		protected boolean selected;
		protected GUIController gcX;
		protected GUIController gcO;
		protected Game g;

		public Square(int i, int j, GUIController gcX, GUIController gcO, Game g){
			this.i = i;
			this.j = j;
			this.gcX = gcX;
			this.gcO = gcO;
			this.g = g;
			addMouseListener(this);
		}
		public @Override void paint (Graphics p){
			p.setColor(Color.WHITE);
			p.fillRect(0, 0, getWidth()-1, getHeight()-1);
			p.setColor(Color.BLACK);
			p.drawRect(0, 0, getWidth()-1, getHeight()-1);
			Player player = g.getBoard().get(i, j);
			if (player == Player.X) {
				p.drawLine(0, 0, getWidth()-1, getHeight()-1);
				p.drawLine(getWidth()-1, 0, 0, getHeight()-1);
			}
			else if (player == Player.O) {
				p.drawOval(0, 0, getWidth()-1, getHeight()-1);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {			
			Square s = (Square)(e.getSource());
			if (g.nextTurn() == Player.X) gcX.setXY(s.i, s.j);
			else gcO.setXY(s.i, s.j);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	@Override
	public void gameChanged(Game g) {
		for (Square s: grids) {
			  s.repaint();
			}
		switch(g.getBoard().getState()) {
		case HAS_WINNER:
			status = g.getBoard().getWinner().winner + " wins!";
		case DRAW:
			status = "Game ended in a draw!";
		case NOT_OVER:
			status = "It is " + g.nextTurn() + "'s turn";
		}
		title.setText(status);
	}
	
	/**build up a pane for everything to show*/
	public Main(GUIController gcX, GUIController gcO, Game g, FirstJFrame JF) {
		//have a title: Five in a Row
		super("Five in a Row");
		//set the size of the window
		this.setPreferredSize(new Dimension (600,400));
		
		//draw a 9*9 board, put it in the center of the window
		JPanel board = new JPanel();
		board.setLayout(new GridLayout(9,9));
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				Square s = new Square(i,j, gcX, gcO, g);
				board.add(s);
				grids.add(s);
			}
		this.add(board,BorderLayout.CENTER);
		board.setPreferredSize(new Dimension(400,400));
		
		//have an info box, including the player choices, progress of the game
		//put it in the east of the window
		Box infoBox = new Box(BoxLayout.Y_AXIS);
		this.add(infoBox, BorderLayout.EAST);
		Box players = new Box(BoxLayout.X_AXIS);
		Box player1 = new Box(BoxLayout.Y_AXIS);
		Box player2 = new Box(BoxLayout.Y_AXIS);
		infoBox.add(players);
		players.add(player1);
		players.add(player2);
		JLabel player1as = new JLabel("Player 1 plays as");
		JLabel human1 = new JLabel(JF.getPlayer1());
		player1.add(player1as);
		player1.add(human1);
		JLabel player2as = new JLabel("Player 2 plays as");
		JLabel human2 = new JLabel(JF.getPlayer2());
		player2.add(player2as);
		player2.add(human2);
		
		//place to report the progress of the game
		switch(g.getBoard().getState()) {
		case HAS_WINNER:
			status = g.getBoard().getWinner().winner + " wins!";
		case DRAW:
			status = "Game ended in a draw!";
		case NOT_OVER:
			status = "It is " + g.nextTurn() + "'s turn";
		}
		Box progress = new Box(BoxLayout.X_AXIS);
		JLabel space = new JLabel("     ");
		title = new JLabel(status);
		progress.add(space);
		progress.add(title);
		infoBox.add(progress);
		
		pack();		
		
	}

	public static Controller createController(String s, Player p) {
		switch(s) {
			case "human":
				return new GUIController(p);
			case "DumbAI":
				return new DumbAI(p);
			case "SmartAI":
				return new SmartAI(p);
			case "RandomAI":
				return new RandomAI(p);
		}
		return null;
	}
	
	/**Build the GUI and connect it with the game.*/
	public static void main(String[] args) {
		FirstJFrame setPlayer = new FirstJFrame();
		setPlayer.setVisible(true);
		while (setPlayer.getGoClicked() == false) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		
		Controller playerX = createController(setPlayer.getPlayer1(), Player.X);
		Controller playerO = createController(setPlayer.getPlayer2(), Player.O);
		Game g = new Game();
		
		GUIController gcX = new GUIController(Player.X);
		GUIController gcO = new GUIController(Player.O);
		if (playerX instanceof GUIController) gcX = (GUIController) playerX;
		if (playerO instanceof GUIController) gcO = (GUIController) playerO;
		
		Main board = new Main(gcX, gcO, g, setPlayer);
		board.setVisible(true);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		g.addListener(board);
		g.addListener(playerX);
		g.addListener(playerO);
	}

}
