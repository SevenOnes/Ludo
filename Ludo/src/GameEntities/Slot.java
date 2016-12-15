/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.0	
 * 	Date	: 12 / 11 / 2016
 */

package GameEntities;

import java.util.ArrayList;

public class Slot {
	
	//Properties
	private boolean occupied;
	private ArrayList<Token> insideToken;
	
	// Constructor
	public Slot(){
		occupied = false;
		insideToken = new ArrayList<Token>();
	}
	
	
	public Token retriveToken(){
		if(!insideToken.isEmpty()){
			Token returnToken = insideToken.get(0);
			if(insideToken.isEmpty()){
				setOccupied(false);
			}
			
			return returnToken;
		}
		else{
			return null;
		}
			
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public ArrayList<Token> getInsideToken() {
		return insideToken;
	}

	public void addInsideToken(Token insideToken) {
		this.insideToken.add(insideToken);
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
