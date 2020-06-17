package model;

/**
 * Eine Klasse spielt die Rolle von einer Kante zwichen zwei Knoten
 * 
 * @author Peer Hanna
 *
 */
public class IntPairs {
	int x, y;

	/**
	 * da wird ein Kante erzeugt
	 * 
	 * @param x ist ein Knote
	 * @param y ist ein Knote
	 */
	public IntPairs(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * liefert TRUE zurück, wenn eine Kante hat die selbe Knoten wie diese
	 * 
	 * @param x ist ein Konte
	 * @param y ist ein Knote
	 * @return True oder False
	 */
	public boolean contains(int x, int y) {
		if (this.x == x && this.y == y)
			return true;
		else
			return false;
	}

	/**
	 * liefert ein String mit den Knoten zurück
	 */
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}

	/**
	 * liefert den Knoten X zurück
	 * 
	 * @return x
	 */

	public int getX() {
		return x;
	}

	/**
	 * liefert den Knoten Y zurück
	 * 
	 * @return
	 */

	public int getY() {
		return y;
	}
}