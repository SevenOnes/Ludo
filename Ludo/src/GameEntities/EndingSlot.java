/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.1	
 * 	Date	: 20 / 11 / 2016
 */
package GameEntities;

public class EndingSlot extends Slot {
	
	//Private
	private String color; 
	
	//Constructor
	public EndingSlot(String color) {
		super();
		this.color = color;
	}

	public String getColor() {
		return color;
	}
	
	public String toString(){
		return "This is a ending slot with color " + color + " " +super.toString();
	}

}
