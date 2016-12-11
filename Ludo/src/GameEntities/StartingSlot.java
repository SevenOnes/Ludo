/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.0	
 * 	Date	: 12 / 11 / 2016
 */

package GameEntities;

public class StartingSlot extends Slot {

	//Private
	private String color; 
	private int mapId;
	
	//Constructor
	public StartingSlot(int mapId,String color) {
		super();
		this.color = color;
		this.mapId = mapId;
		
	}

	public String getColor() {
		return color;
	}

	public int getMapId() {
		return mapId;
	}
	
	public String toString(){
		return "This is a starting slot with color " + color + " " +super.toString();
	}

}
