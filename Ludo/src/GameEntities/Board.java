/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.1	
 * 	Date	: 20 / 11 / 2016
 */


package GameEntities;

import java.util.ArrayList;

public class Board {

	
	//Properties
	private ArrayList<House> houses; 
	private Slot[] slots;
	private Slot[][] endingSlots;
	private int mapId;
	private Die die;

	final int NUMBEROFSLOT = 52;

	// Constructor
	public Board(int mapId){
		// Setting mapId and Die
		this.mapId = mapId;
		die = new Die();
		
		//Setting Houses
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
		// This create a new Array with slots 
		if(mapId == 1){
			for(int i = 0; i < NUMBEROFSLOT; i++){
				// Those are starting points
				if(i == 0)
					slots[i] = new StartingSlot("Red");
				else if(i == 13){
					slots[i] = new StartingSlot("Yellow");
				}
				else if(i == 26){
					slots[i] = new StartingSlot("Green");
				}
				else if(i == 39){
					slots[i] = new StartingSlot("Blue");
				}
				//Those are ending points
				else if(i == 50){
					slots[i] = new EndingSlot("Red");
				}
				else if(i == 11){
					slots[i] = new EndingSlot("Yellow");
				}
				else if(i == 24){
					slots[i] = new EndingSlot("Green");
				}
				else if(i == 37){
					slots[i] = new EndingSlot("Blue");
				}
				// Others are normal slot
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
		

	}
	

	
	public String toString(){
		String str = "Board consist of \n";
		for(int i = 0; i < houses.size(); i++){
			str += houses.get(i).toString() + "\n";
		}
		for(int i = 0; i < slots.length; i++){
			str += "index is "+ i + "\t"+ slots[i].toString() + "\n";
		}
		
		return str;
		
	}
	
	// Test code
	// Result : Working
	
	public static void main(String[] arg){
		Board board = new Board(1);
		System.out.println(board);
	}



	public ArrayList<House> getHouses() {
		return houses;
	}



	public Slot[] getSlots() {
		return slots;
	}



	public Slot[][] getEndingSlots() {
		return endingSlots;
	}



	public int getMapId() {
		return mapId;
	}



	public Die getDie() {
		return die;
	}
	
}




