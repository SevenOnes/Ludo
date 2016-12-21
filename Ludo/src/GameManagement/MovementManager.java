package GameManagement;

import java.util.ArrayList;

import GameEntities.Board;
import GameEntities.House;
import GameEntities.Slot;
import GameEntities.Token;

public class MovementManager {

	public boolean isAvailable(int playerID, Board b, int faceValue){
		if(b.getHouses().get(playerID).getTokens().size() == 4 && faceValue != 6)
			return false;
		else if(b.getHouses().get(playerID).getTokens().size() > 0 && faceValue == 6){	
			for(int i = 0; i < b.getHouses().get(playerID).getTokens().size(); i++)
				b.getHouses().get(playerID).getTokens().get(i).setAvailable(true);
			return true;
		}
		else{
			Slot[] slots = b.getSlots();
			boolean flag = false;			
			for(int i = 0; i < slots.length ; i++){
				if(slots[i].isOccupied()){
					if(playerID == 0){
						for(int k = 0;k < slots[i].getInsideToken().size();k++){
							if(slots[i].getInsideToken().get(k).getColor().equals("Red")){
								slots[i].getInsideToken().get(k).setAvailable(true);
								flag = true;
							}
						}
					}else if(playerID == 1){
						for(int k = 0;k < slots[i].getInsideToken().size();k++){
							if(slots[i].getInsideToken().get(k).getColor().equals("Yellow")){
								slots[i].getInsideToken().get(k).setAvailable(true);
								flag = true;
							}
						}
					}else if(playerID == 2){
						for(int k = 0;k < slots[i].getInsideToken().size();k++){
							if(slots[i].getInsideToken().get(k).getColor().equals("Green")){
								slots[i].getInsideToken().get(k).setAvailable(true);
								flag = true;
							}
						}
					}else if(playerID == 3){
						for(int k = 0;k < slots[i].getInsideToken().size();k++){
							if(slots[i].getInsideToken().get(k).getColor().equals("Blue")){
								slots[i].getInsideToken().get(k).setAvailable(true);
								flag = true;
							}
						}
					}
				}
			}

			slots = b.getEndingSlots()[playerID];			
			for(int i = 0; i < slots.length-1;i++){
				int maxCanBeRolled = 5 - i;
				if(slots[i].isOccupied()){
					if(faceValue <= maxCanBeRolled){
						for(int k = 0;k < slots[i].getInsideToken().size();k++){
							slots[i].getInsideToken().get(k).setAvailable(true);
							flag = true;
						}
					}
				}
			}
			return flag;
		}

	}

	public boolean move (int playerID,Board b, int initialPos, int faceValue,int indexOfElement){
		System.out.println(playerID + " " + initialPos + " " + faceValue + " " + indexOfElement);
		if(initialPos == -1){
			if(!b.getHouses().get(playerID).isEmpty()){
				Token token = b.getHouses().get(playerID).getTokens().get(0);
				if(token.isAvailable()){
					token = b.getHouses().get(playerID).retriveToken();
					b.getSlots()[playerID*13].addInsideToken(token);
					return true;
				}
			}
		}
		else if(initialPos > 51){
			Token token = b.getEndingSlots()[playerID][initialPos-52].getInsideToken().get(0);
			if(token.isAvailable()){
				b.getEndingSlots()[playerID][initialPos-52].retriveToken(0);
				b.getEndingSlots()[playerID][initialPos-52+faceValue].addInsideToken(token);
				return true;
			}
		}
		else{
			Token token = b.getSlots()[initialPos].getInsideToken().get(indexOfElement);
			if(token.isAvailable()){
				token = b.getSlots()[initialPos].retriveToken(indexOfElement);
				if(playerID == 0){
					if(faceValue + initialPos >50){
						b.getEndingSlots()[playerID][faceValue + initialPos-51].addInsideToken(token);
					}
					else{
						if(initialPos+faceValue == 0 || initialPos+faceValue == 13 
								|| initialPos+faceValue == 26 || initialPos+faceValue == 39){
							b.getSlots()[initialPos+faceValue].addInsideToken(token);
						}else if(b.getSlots()[initialPos+faceValue].isOccupied()){
							if(b.getSlots()[initialPos+faceValue].getInsideToken().get(0).getColor().equals(token.getColor())){
								b.getSlots()[initialPos+faceValue].addInsideToken(token);
							}else{
								for(int i = 0; i < b.getSlots()[initialPos+faceValue].getInsideToken().size();){
									Token eatenToken = b.getSlots()[initialPos+faceValue].retriveToken(0);
									if(eatenToken.getColor().equals("Yellow")){
										b.getHouses().get(1).addToken(eatenToken);
									}else if(eatenToken.getColor().equals("Green")){
										b.getHouses().get(2).addToken(eatenToken);
									}else if(eatenToken.getColor().equals("Blue")){
										b.getHouses().get(3).addToken(eatenToken);
									}
								}
								b.getSlots()[initialPos+faceValue].addInsideToken(token);
							}
						}
						else{
							b.getSlots()[initialPos+faceValue].addInsideToken(token);
						}
					}
				}else if(playerID == 1){
					if(initialPos <=11 &&faceValue + initialPos > 11){
						b.getEndingSlots()[playerID][faceValue + initialPos-12].addInsideToken(token);
					}else{
						if((initialPos+faceValue)%52 == 0 || initialPos+faceValue == 13 
								|| initialPos+faceValue == 26 || initialPos+faceValue == 39){
							b.getSlots()[initialPos+faceValue].addInsideToken(token);
						}else if(b.getSlots()[(initialPos+faceValue)%52].isOccupied()){
							if(b.getSlots()[(initialPos+faceValue)%52].getInsideToken().get(0).getColor().equals(token.getColor())){
								b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
							}else{
								for(int i = 0; i < b.getSlots()[(initialPos+faceValue)%52].getInsideToken().size();){
									Token eatenToken = b.getSlots()[(initialPos+faceValue)%52].retriveToken(0);
									if(eatenToken.getColor().equals("Red")){
										b.getHouses().get(0).addToken(eatenToken);
									}else if(eatenToken.getColor().equals("Green")){
										b.getHouses().get(2).addToken(eatenToken);
									}else if(eatenToken.getColor().equals("Blue")){
										b.getHouses().get(3).addToken(eatenToken);
									}
								}
								b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
							}
						}
						else{
							b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
						}
					}
				}else if(playerID == 2){
					if(initialPos <= 24 &&faceValue + initialPos > 24){
						b.getEndingSlots()[playerID][faceValue + initialPos-25].addInsideToken(token);
					}else{
						if((initialPos+faceValue)%52 == 0 || initialPos+faceValue == 13 
								|| initialPos+faceValue == 26 || initialPos+faceValue == 39){
							b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
						}else if(b.getSlots()[(initialPos+faceValue)%52].isOccupied()){
							if(b.getSlots()[(initialPos+faceValue)%52].getInsideToken().get(0).getColor().equals(token.getColor())){
								b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
							}else{
								for(int i = 0; i < b.getSlots()[(initialPos+faceValue)%52].getInsideToken().size();){
									Token eatenToken = b.getSlots()[(initialPos+faceValue)%52].retriveToken(0);
									if(eatenToken.getColor().equals("Yellow")){
										b.getHouses().get(1).addToken(eatenToken);
									}else if(eatenToken.getColor().equals("Red")){
										b.getHouses().get(0).addToken(eatenToken);
									}else if(eatenToken.getColor().equals("Blue")){
										b.getHouses().get(3).addToken(eatenToken);
									}
								}
								b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
							}
						}
						else{
							b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
						}
					}
				}else if(playerID == 3){

					System.out.println(initialPos +" , " + faceValue);
					if(initialPos <= 37 &&faceValue + initialPos > 37){
						b.getEndingSlots()[playerID][faceValue + initialPos-37].addInsideToken(token);
					}else{
						if((initialPos+faceValue)%52 == 0 || initialPos+faceValue == 13 
								|| initialPos+faceValue == 26 || initialPos+faceValue == 39){
							b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
						}else if(b.getSlots()[(initialPos+faceValue)%52].isOccupied()){
							if(b.getSlots()[(initialPos+faceValue)%52].getInsideToken().get(0).getColor().equals(token.getColor())){
								b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
							}else{
								for(int i = 0; i < b.getSlots()[(initialPos+faceValue)%52].getInsideToken().size();){
									Token eatenToken = b.getSlots()[(initialPos+faceValue)%52].retriveToken(0);
									if(eatenToken.getColor().equals("Yellow")){
										b.getHouses().get(1).addToken(eatenToken);
									}else if(eatenToken.getColor().equals("Green")){
										b.getHouses().get(2).addToken(eatenToken);
									}else if(eatenToken.getColor().equals("Red")){
										b.getHouses().get(0).addToken(eatenToken);
									}
								}
								b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
							}
						}
						else{
							b.getSlots()[(initialPos+faceValue)%52].addInsideToken(token);
						}
					}
				}
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	public void makeUnAvaliable(Board b){
		for(int i = 0 ; i < b.getHouses().size(); i++){
			for(int k =0 ; k <  b.getHouses().get(i).getTokens().size() ; k++){
				b.getHouses().get(i).getTokens().get(k).setAvailable(false);
			}
		}
		for(int i = 0 ; i < b.getSlots().length; i++){
			for(int k =0 ; k < b.getSlots()[i].getInsideToken().size() ; k++){
				 b.getSlots()[i].getInsideToken().get(k).setAvailable(false);
			}
		}
		for(int i = 0 ; i < b.getEndingSlots().length; i++){
			for(int l = 0 ; l < b.getEndingSlots()[i].length;l++){
				for(int k =0 ; k < b.getEndingSlots()[i][l].getInsideToken().size() ; k++){
					b.getEndingSlots()[i][l].getInsideToken().get(k).setAvailable(false);
				}
			}
		}
		
	}
}
