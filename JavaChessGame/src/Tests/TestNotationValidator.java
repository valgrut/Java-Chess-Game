package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ChessGame.NotationValidator;

class TestNotationValidator {
	private NotationValidator tester = new NotationValidator();
	
	@Test
	void testMove1() {assertEquals(Boolean.FALSE, tester.validateMove(""), "Empty line");}
	
	@Test
	void testMove2() {assertEquals(Boolean.TRUE, tester.validateMove("e4"), "Pawn move");}
	
	@Test
	void testMove3() {assertEquals(Boolean.TRUE, tester.validateMove("Sc4"), "Strelec basic move");}
	
	@Test
	void testMove4() {assertEquals(Boolean.TRUE, tester.validateMove("Df6"), "Dama basic move");}
	
	@Test
	void testMove5() {assertEquals(Boolean.TRUE, tester.validateMove("dxe8V#"), "Pawn specified move and change of Vez and Checkmate");}
	
	@Test
	void testMove6() {assertEquals(Boolean.TRUE, tester.validateMove("d8D"), "Pawn move and takes Dama");}
	
	@Test
	void testMove7() {assertEquals(Boolean.TRUE, tester.validateMove("V7a4"), "Vez basic move, specified row");}
	
	@Test
	void testMove8() {assertEquals(Boolean.TRUE, tester.validateMove("Dh7#"), "Dama moves and Checkmate");}
	
	@Test
	void testMove9() {assertEquals(Boolean.FALSE, tester.validateMove("Dh7#+"), "Dama moves and Checkmate and check.");}
	
	@Test
	void testMove10() {assertEquals(Boolean.FALSE, tester.validateMove("Dh7+#"), "Dama moves and Checkmate and check.");}
	
	@Test
	void testMove11() {assertEquals(Boolean.FALSE, tester.validateMove("p4"), "Invalid notation range");}
	
	@Test
	void testMove12() {assertEquals(Boolean.FALSE, tester.validateMove("d9"), "Invalid notation range");}
	
	@Test
	void testMove13() {assertEquals(Boolean.FALSE, tester.validateMove("Gh5"), "Invalid figure");}
	
	@Test
	void testMove14() {assertEquals(Boolean.FALSE, tester.validateMove("Ld4"), "Invalid figure");}
	
	@Test
	void testMove15() {assertEquals(Boolean.FALSE, tester.validateMove("Kxf4$"), "Invalid notation");}
	
	/*
	@Test
	void testMove16() {assertEquals(Boolean.TRUE, tester.validateMove("e2e4"), "Valid LONG notation");}
	@Test
	void testMove17() {assertEquals(Boolean.TRUE, tester.validateMove("e7e5"), "Valid LONG notation");}
	@Test
	void testMove18() {assertEquals(Boolean.TRUE, tester.validateMove("Sf1c4"), "Invalid LONG notation");}
	*/
	
	@Test
	void testLine1() {assertEquals(Boolean.FALSE, tester.validateLine(""), "Empty Line");}
	
	@Test
	void testLine2() {assertEquals(Boolean.FALSE, tester.validateLine("x. asdf sdfa"), "Invalid number format");}
	
	@Test
	void testLine3() {assertEquals(Boolean.FALSE, tester.validateLine("1."), "Invalid Number of moves");}
	
	@Test
	void testLine4() {assertEquals(Boolean.FALSE, tester.validateLine("1. asdf"), "Invalid Number of moves");}
	
	@Test
	void testLine5() {assertEquals(Boolean.TRUE, tester.validateLine("1. asdf asdf"), "Valid shape");}
	
	@Test
	void testLine6() {assertEquals(Boolean.TRUE, tester.validateLine("4. Kf4 d3+"), "Valid shape");}
	
	@Test
	void testLine7() {assertEquals(Boolean.TRUE, tester.validateLine("42. asdf asdf"), "Valid number");}
	
	@Test
	void testLine8() {assertEquals(Boolean.FALSE, tester.validateLine("1d8. asdf asdf asdf"), "Invalid number format");}
	
	@Test
	void testLine9() {assertEquals(Boolean.FALSE, tester.validateLine("5 asdf asdf"), "Missing dot after move number.");}
		
}
