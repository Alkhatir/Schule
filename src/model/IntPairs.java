package model;

public class IntPairs
{
	int x,y;
	public IntPairs(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "["+x+","+y+"]";
	}
}