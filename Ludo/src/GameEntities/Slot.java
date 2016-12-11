/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.0	
 * 	Date	: 12 / 11 / 2016
 */

package GameEntities;

public class Slot {
	
	//Properties
	private boolean occupied;
	private Token insideToken;
	
	// Constructor
	public Slot(){
		occupied = false;
		insideToken = null;
	}
	
	public Token retriveToken(){
		Token returnToken = insideToken;
		insideToken = null;
		occupied = false;
		return returnToken;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public Token getInsideToken() {
		return insideToken;
	}

	public void setInsideToken(Token insideToken) {
		this.insideToken = insideToken;
		setOccupied(true);
	}
	
	public String toString(){
		return "This is a standart slot with Occupied " + occupied ;
	}
	
	
	// Test code 
	// Result  : Working
	/*
	public static void main(String[] args){
		Slot slot = new Slot();
		Token token = new Token("Blue");
		System.out.println(slot);
		slot.setInsideToken(token);
		System.out.println(slot);
		Token tmp = slot.retriveToken();
		System.out.println(tmp);
		System.out.println(slot);
	}
	*/
}
