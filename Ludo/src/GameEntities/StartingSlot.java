/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.0	
 * 	Date	: 12 / 11 / 2016
 */

package GameEntities;

public class StartingSlot extends Slot {

	//Private
	private String color;
	
	//Constructor
	public StartingSlot(String color) {
		super();
		this.color = color;
	}

	public String getColor() {
		return color;
	}
	
	public String toString(){
		return "This is a starting slot with color " + color + " " +super.toString();
	}

}
