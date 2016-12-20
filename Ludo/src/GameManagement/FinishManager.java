package GameManagement;

import GameEntities.Slot;

public class FinishManager {
	
	public boolean endPlayer(Slot[] slots){
		if (slots[5].getInsideToken().size() == 4)
			return true;	
		return false;
	}
	
	public boolean endGame(Slot[][] slots){
		for(int i = 0; i < slots.length; i++){
			if(!endPlayer(slots[i]))
				return false;
		}
		return true;
	}
}
