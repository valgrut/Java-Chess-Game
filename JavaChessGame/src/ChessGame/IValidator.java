package ChessGame;

public interface IValidator {
	boolean validateLine(String line); // "1. Kd8 De4"     vs     Long_notation
	boolean validateMove(String move);
}
