/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.0	
 * 	Date	: 12 / 11 / 2016
 */

package GameEntities;

import java.util.ArrayList;

public class Slot {
	
	//Properties
	private ArrayList<Token> insideToken;
	
	// Constructor
	public Slot(){
		insideToken = new ArrayList<Token>();
	}
	
	
	public Token retriveToken(int tokenNumber){
		// Checking if it empty arraylist or not
		if(!insideToken.isEmpty()){
			// getting token from array
			Token returnToken = insideToken.get(tokenNumber);
			// Removing from in arraylist
			insideToken.remove(tokenNumber);
			return returnToken;
		}
		else{
			return null;
		}
			
	}

	public boolean isOccupied() {
		// Basically return if there is anything in there
		return !(insideToken.isEmpty());
	}

	public ArrayList<Token> getInsideToken(){
		// This let get arraylist in array
		return insideToken;
	}

	public void addInsideToken(Token toInside) {
		// add the token in the arraylist 
		insideToken.add(toInside);
	}
	
	public String toString(){
		return "This is a standart slot with Occupied " + isOccupied() ;
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
