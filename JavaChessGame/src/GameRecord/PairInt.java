package GameRecord;

/*
public class Pair<F, S> {
    private F first; //first member of pair
    private S second; //second member of pair

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
*/

/**
 * Object that can hold two values. It is used for returning of two values.
 * @author xpeska05, xhalmo00
 *
 */
public class PairInt {
	private int firstObject;
	private int secondObject;
	
	public PairInt(int i, int j)
	{
		this.firstObject = i;
		this.secondObject = j;
	}
	
	public int getFirst() {return this.firstObject;}
	public int getSecond() {return this.secondObject;}
}



