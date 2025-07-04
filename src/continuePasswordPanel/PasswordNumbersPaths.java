package continuePasswordPanel;

public enum PasswordNumbersPaths {
	
	ZERO("/continuePage/Password_Number0.png"),
	ONE("/continuePage/Password_Number1.png"),
	TWO("/continuePage/Password_Number2.png"),
	THREE("/continuePage/Password_Number3.png"),
	FOUR("/continuePage/Password_Number4.png"),
	FIVE("/continuePage/Password_Number5.png"),
	SIX("/continuePage/Password_Number6.png" ),
	SEVEN("/continuePage/Password_Number7.png");
	
	String path;
	
	PasswordNumbersPaths(String path){ this.path = path; }
	
	// Metodo che restituisce la stringa
	public String getPath() { return path; }
}
