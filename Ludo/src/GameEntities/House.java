/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.0	
 * 	Date	: 12 / 11 / 2016
 */

package GameEntities;

import java.util.ArrayList;

public class House {

	// Properties
	private ArrayList<Token> tokens;
	
	final int MAXTOKEN = 4;
	
	// Constuructor
	public House(String color) {
		// Creating new ArrayList 
		tokens = new ArrayList<Token>();
		//Creating 4(MAXTOKEN) token putting in arraylist
		for(int i = 0; i < MAXTOKEN; i++){
			Token newToken = new Token(color);
			tokens.add(newToken);
		}
	}
	
	
	public Token retriveToken(){
		
		if(!tokens.isEmpty()){
			int last = tokens.size() -1 ;			// geting last index
			Token lastToken = tokens.get(last);		// geting last index
			tokens.remove(last);					// removing from list
			return lastToken;						// returning removed item;
		}		
		return null;
	}
	
	public boolean isEmpty(){
		return !(tokens.isEmpty());
	}
	
	// This method add a newtoken to list
	public void addToken(Token newToken){
		tokens.add(newToken);
	}
	
	public ArrayList<Token> getTokens(){
		return tokens;
	}
	
	public String toString() {
		return "Number of Token in House is " + tokens.size() + " Color = " + tokens.get(0).getColor();
	}
	
	// Test code 
	// Result  : Working
	/*
	public static void main(String[] args){
		House house = new House("Green");
		System.out.println(house);
		Token tmp = house.retriveToken();
		System.out.println(tmp);
		System.out.println(house);
		house.addToken(tmp);
		System.out.println(house.getTokens().get(3));
	}
	*/
}
