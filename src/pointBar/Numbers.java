package pointBar;

public enum Numbers {
	
	ZERO("/tilesPoint/numbers/Tiles_numberZero.png", 0),
	ONE("/tilesPoint/numbers/Tiles_numberOne.png", 1),
	TWO("/tilesPoint/numbers/Tiles_numberTwo.png", 2),
	THREE("/tilesPoint/numbers/Tiles_numberThree.png", 3),
	FOUR("/tilesPoint/numbers/Tiles_numberFour.png", 4),
	FIVE("/tilesPoint/numbers/Tiles_numberFive.png", 5),
	SIX("/tilesPoint/numbers/Tiles_numberSix.png", 6),
	SEVEN("/tilesPoint/numbers/Tiles_numberSeven.png", 7),
	EIGTH("/tilesPoint/numbers/Tiles_numberEigth.png", 8),
	NINE("/tilesPoint/numbers/Tiles_numberNine.png", 9);
	
	String path;
	int value;
	
	Numbers(String path, int value){ this.path = path; this.value = value; }

	public String getPath() { return path; }

	public int getValue() { return value; }
}
