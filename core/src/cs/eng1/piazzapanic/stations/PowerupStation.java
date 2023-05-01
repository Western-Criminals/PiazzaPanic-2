 package cs.eng1.piazzapanic.stations;

 import com.badlogic.gdx.graphics.Texture;
 import cs.eng1.piazzapanic.food.LongBoiBank;
 import cs.eng1.piazzapanic.stations.StationAction.ActionType;
 import cs.eng1.piazzapanic.ui.StationActionUI;
 import cs.eng1.piazzapanic.ui.StationUIController;
 import com.badlogic.gdx.graphics.g2d.TextureRegion;
 import com.badlogic.gdx.graphics.g2d.Batch;

 import java.util.LinkedList;
 import java.util.List;
 import java.util.concurrent.ThreadLocalRandom;

 /**
  * The PowerupStation class is a station representing the place in the kitchen where you collect powerups
  * to be used in the game.
  */
 public class PowerupStation extends Station {
     public static LongBoiBank bank = new LongBoiBank();

     public PowerupStation(int id, TextureRegion image, StationUIController uiController,
                           StationActionUI.ActionAlignment alignment) {
         super(id, image, uiController, alignment);
     }
     public List<ActionType> getActionTypes() {
         LinkedList<ActionType> actionTypes = new LinkedList<>();
         if (nearbyChef == null) {
             return actionTypes;
         }
         if (bank.getBalance() >= 30) {
             actionTypes.add(ActionType.GET_POWERUP);
         }
         return actionTypes;
     }
     public void doStationAction(StationAction.ActionType action) {
         if (action == ActionType.GET_POWERUP) {
             if (this.nearbyChef != null && bank.getBalance() >= 30) {
                 // TODO: Add random powerup to chefs
                 int randomPowerupInt = ThreadLocalRandom.current().nextInt(0, 5);
                 switch (randomPowerupInt) {
                     case 0:
                         //powerup 1
                         break;
                     case 1:
                         //powerup 2
                         break;
                     case 2:
                         //powerup 3
                         break;
                     case 3:
                         //powerup 4
                         break;
                     case 4:
                         //powerup 5
                         break;
                 }
                 bank.setBalance(bank.getBalance() - 30);
             }
         }
     }
     @Override
     public void draw(Batch batch, float parentAlpha) {
         super.draw(batch, parentAlpha);
         if (bank.getBalance() >= 30) {
             drawFoodTexture(batch, new Texture("food/original/coin.png"));
         }
     }
 }
