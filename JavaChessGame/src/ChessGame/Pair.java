package ChessGame;

public class Pair {
	private Move firstObject;
	private Move secondObject;
	
	public Pair(Move first, Move second)
	{
		this.firstObject = first;
		this.secondObject = second;
	}
	
	public Move getFirst() {return this.firstObject;}
	public Move getSecond() {return this.secondObject;}
}
