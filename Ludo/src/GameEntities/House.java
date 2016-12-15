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
		tokens = new ArrayList<Token>();
		for(int i = 0; i < MAXTOKEN; i++){
			Token newToken = new Token(color);
			tokens.add(newToken);
		}
	}
	
	
	public Token retriveToken(){
		int last = tokens.size() -1 ;
		Token lastToken = tokens.get(last);
		tokens.remove(last);
		return lastToken;
	}
	
	public boolean isEmpty(){
		if(tokens.size() == 0){
			return true;
		}
		return false;
	}
	
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
