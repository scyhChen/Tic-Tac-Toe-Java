package gui;

import controller.Controller;
import model.Game;
import model.Location;
import model.Player;

/**
 * A Controller that queries the user for moves and then submits them.
 */
public class GUIController extends Controller {

	private int x;
	private int y;
	
	public GUIController(Player me) {
		super(me);
		x = y = -1;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	protected Location nextMove(Game g) {
		// while not click - wait here
		while (x == -1 || y == -1) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}

		int rx = x, ry = y;
		x = y = -1;
		return new Location(rx, ry);
	}
}
