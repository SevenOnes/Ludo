/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.0	
 * 	Date	: 12 / 11 / 2016
 */


package GameEntities;

import java.util.ArrayList;

public class Board {

	private ArrayList<House> houses; 
	private Slot[] slots;
	private Slot[][] endingSlots;
	private int mapId;
	private Die die;

	final int NUMBEROFSLOT = 52;

	public Board(int mapId){
		this.mapId = mapId;
		
		houses = new ArrayList<House>();
		for(int i = 0; i < 4 ; i++){
			if(i == 0){
				houses.add(new House("Red"));
			}
			if(i == 0){
				houses.add(new House("Yellow"));
			}
			if(i == 0){
				houses.add(new House("Green"));
			}
			if(i == 0){
				houses.add(new House("Blue"));
			}
		}
		
		//Slot declarition
		slots = new Slot[NUMBEROFSLOT];
		// When mapId = 1
		// https://lh3.ggpht.com/P8kmRP1PrZCZGNjrRfotBfy51uJ-_zNm3QjaqThdLj5y9Cp4vDq0e7Hqn9Guu_BIdw=w170
		if(mapId == 1){
			for(int i = 0; i < NUMBEROFSLOT; i++){
				if(i == 0)
					slots[i] = new StartingSlot(mapId,"Red");
				else if(i == 13){
					slots[i] = new StartingSlot(mapId,"Yellow");
				}
				else if(i == 26){
					slots[i] = new StartingSlot(mapId,"Green");
				}
				else if(i == 39){
					slots[i] = new StartingSlot(mapId,"Blue");
				}
				else if(i == 50){
					slots[i] = new EndingSlot(mapId,"Red");
				}
				else if(i == 11){
					slots[i] = new EndingSlot(mapId,"Yellow");
				}
				else if(i == 24){
					slots[i] = new EndingSlot(mapId,"Green");
				}
				else if(i == 37){
					slots[i] = new EndingSlot(mapId,"Blue");
				}
				else{
					slots[i] = new Slot();	
				}
			}
		}
		else if(mapId == 2){
			//TO-DO not implemented
		}
		//Ending Slots declearation
		endingSlots = new Slot[4][5];
		for(int i =0 ; i < 4 ; i++){
			for(int k = 0; k < 5 ; k++){
				endingSlots[i][k] = new Slot();
			}
		}
		
		Die die = new Die();
	}
	

	
	public String toString(){
		String str = "Board consist of \n";
		for(int i = 0; i < houses.size(); i++){
			str += houses.get(i).toString() + "\n";
		}
		for(int i = 0; i < slots.length; i++){
			str += slots[i].toString() + "\n";
		}
		
		return str;
		
	}
	
	// Test code
	// Result : Working
	
	public static void main(String[] arg){
		Board board = new Board(1);
		System.out.println(board);
	}
	
}




