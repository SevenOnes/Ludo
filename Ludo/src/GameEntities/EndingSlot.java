package GameEntities;

public class EndingSlot extends Slot {
	
	//Private
	private String color; 
	private int mapId;
	
	//Constructor
	public EndingSlot(int mapId,String color) {
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
		return "This is a ending slot with color " + color + " " +super.toString();
	}

}
