/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.0	
 * 	Date	: 12 / 11 / 2016
 */

package GameEntities;

public class Token {
	
	// Properties
	private String color;		// This is about owner relation
	private boolean available;	// This is related to movement
	
	// Constructor with color
	public Token(String color){
		available = false;
		this.color = color;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getColor() {
		return color;
	}
	
	public String toString(){
		return "Color of Token is " + color + " availablity of Token is + " + available;
	}
	
	
}
