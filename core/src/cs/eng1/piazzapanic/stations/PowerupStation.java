package cs.eng1.piazzapanic.stations;

import com.badlogic.gdx.graphics.Texture;
import cs.eng1.piazzapanic.chef.Chef;
import cs.eng1.piazzapanic.food.LongBoiBank;
import cs.eng1.piazzapanic.stations.StationAction.ActionType;
import cs.eng1.piazzapanic.ui.StationActionUI;
import cs.eng1.piazzapanic.ui.StationUIController;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import cs.eng1.piazzapanic.ui.UIOverlay;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The PowerupStation class is a station representing the place in the kitchen where you collect powerups
* to be used in the game.
*/
public class PowerupStation extends Station {
    public static LongBoiBank bank = new LongBoiBank();
    private boolean isBeingUsed = false;
    private int randomPowerupInt;

    public PowerupStation(int id, TextureRegion image, StationUIController uiController,
                        StationActionUI.ActionAlignment alignment) {
        super(id, image, uiController, alignment);
    }
    public List<ActionType> getActionTypes() {
        LinkedList<ActionType> actionTypes = new LinkedList<>();
        if (nearbyChef == null) {
            return actionTypes;
        }
        if (bank.getBalance() >= 30 && !isBeingUsed) {
            actionTypes.add(ActionType.GET_POWERUP);
        }
        return actionTypes;
    }
    public void doStationAction(StationAction.ActionType action) {
        if (action == ActionType.GET_POWERUP) {
            if (this.nearbyChef != null && bank.getBalance() >= 30 && !isBeingUsed) {
                // TODO: Add random powerup to chefs
                bank.setBalance(bank.getBalance() - 30);
                randomPowerupInt = ThreadLocalRandom.current().nextInt(0, 5);
                isBeingUsed = true;
                switch (randomPowerupInt) {
                    case 0:
                        Chef.speed = 5f;
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    public void run() {
                                        Chef.speed = 3f;
                                        isBeingUsed = false;
                                    }
                                },
                                15000
                        );
                        break;
                    case 1:
                        UIOverlay.timer.stop();
                        UIOverlay.repTimer.stop();
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    public void run() {
                                        UIOverlay.timer.start();
                                        UIOverlay.repTimer.start();
                                        isBeingUsed = false;
                                    }
                                },
                                5000
                        );
                        break;
                    case 2:
                        CookingStation.totalTimeToCook /= 2;
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    public void run() {
                                        CookingStation.totalTimeToCook *= 2;
                                        isBeingUsed = false;
                                    }
                                },
                                30000
                        );
                        break;
                    case 3:
                        CookingStation.totalTimeToBurn *= 1000;
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    public void run() {
                                        CookingStation.totalTimeToBurn /= 1000;
                                        isBeingUsed = false;
                                    }
                                },
                                30000
                        );
                        break;
                    case 4:
                        ChoppingStation.totalTimeToChop /= 50;
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    public void run() {
                                        ChoppingStation.totalTimeToChop *= 50;
                                        isBeingUsed = false;
                                    }
                                },
                                5000
                        );
                        break;
                }
                uiController.showActions(this, getActionTypes());
            }
        }
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (bank.getBalance() >= 30 && !isBeingUsed) {
            drawFoodTexture(batch, new Texture("food/original/coin.png"));
        }
        else if (isBeingUsed){
            switch (randomPowerupInt) {
                case 0:
                    drawFoodTexture(batch, new Texture("food/original/speed.png"));
                    break;
                case 1:
                    drawFoodTexture(batch, new Texture("food/original/time.png"));
                    break;
                case 2:
                    drawFoodTexture(batch, new Texture("food/original/cook.png"));
                    break;
                case 3:
                    drawFoodTexture(batch, new Texture("food/original/nofire.png"));
                    break;
                case 4:
                    drawFoodTexture(batch, new Texture("food/original/chop.png"));
                    break;
            }
        }
    }
}
