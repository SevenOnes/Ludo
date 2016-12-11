/**
 *	Author  : Ahmet Taha Albayrak
 * 	Version : v1.0	
 * 	Date	: 12 / 11 / 2016
 */
package GameEntities;

public class Die {
	
	//Properties
	
	private int faceValue;
	
	// Constructor
	public Die(){
		faceValue = 0;
	}
	
	public void roll(){
		faceValue = (int) (Math.random()*6 + 1) ;
	}
	
	public int getfaceValue(){
		return faceValue;
	}
	
	// Test code 
	// Result  : Working
	/*
	public static void main(String[] args){
		Die die = new Die();
		die.roll();
		System.out.println(die.getfaceValue());
		die.roll();
		System.out.println(die.getfaceValue());
	}
	*/
}


