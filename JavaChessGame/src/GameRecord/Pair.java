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


public class Pair {
	private MoveData firstObject;
	private MoveData secondObject;
	
	public Pair(MoveData first, MoveData second)
	{
		this.firstObject = first;
		this.secondObject = second;
	}
	
	public MoveData getFirst() {return this.firstObject;}
	public MoveData getSecond() {return this.secondObject;}
}



