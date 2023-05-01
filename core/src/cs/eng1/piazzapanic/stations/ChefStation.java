package cs.eng1.piazzapanic.stations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import cs.eng1.piazzapanic.food.LongBoiBank;
import cs.eng1.piazzapanic.ui.StationActionUI;
import cs.eng1.piazzapanic.ui.StationUIController;

import java.util.LinkedList;
import java.util.List;

public class ChefStation extends Station{
    public static LongBoiBank bank = new LongBoiBank();

    public ChefStation(int id, TextureRegion image, StationUIController uiController,
                          StationActionUI.ActionAlignment alignment) {
        super(id, image, uiController, alignment);
    }
    public List<StationAction.ActionType> getActionTypes() {
        LinkedList<StationAction.ActionType> actionTypes = new LinkedList<>();
        if (nearbyChef == null) {
            return actionTypes;
        }
        if (bank.getBalance() >= 30) {
            actionTypes.add(StationAction.ActionType.GET_CHEF);
        }
        return actionTypes;
    }
    public void doStationAction(StationAction.ActionType action) {
        if (action == StationAction.ActionType.GET_POWERUP) {
            if (this.nearbyChef != null && bank.getBalance() >= 30) {
                // TODO: Add chef to stage
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
