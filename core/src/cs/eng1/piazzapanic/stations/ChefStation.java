package cs.eng1.piazzapanic.stations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import cs.eng1.piazzapanic.chef.ChefManager;
import cs.eng1.piazzapanic.food.LongBoiBank;
import cs.eng1.piazzapanic.ui.StationActionUI;
import cs.eng1.piazzapanic.ui.StationUIController;

import java.util.LinkedList;
import java.util.List;

public class ChefStation extends Station{
    public static LongBoiBank bank = new LongBoiBank();
    private final Stage stage;
    private final ChefManager chefManager;
    private boolean boughtChef = false;

    public ChefStation(int id, TextureRegion image, StationUIController uiController,
                          StationActionUI.ActionAlignment alignment, Stage stage, ChefManager chefManager) {
        super(id, image, uiController, alignment);
        this.stage = stage;
        this.chefManager = chefManager;
    }
    public List<StationAction.ActionType> getActionTypes() {
        LinkedList<StationAction.ActionType> actionTypes = new LinkedList<>();
        if (nearbyChef == null) {
            return actionTypes;
        }
        if (bank.getBalance() >= 30 && !boughtChef) {
            actionTypes.add(StationAction.ActionType.GET_CHEF);
        }
        return actionTypes;
    }
    public void doStationAction(StationAction.ActionType action) {
        if (action == StationAction.ActionType.GET_CHEF) {
            if (this.nearbyChef != null && bank.getBalance() >= 30 && !boughtChef) {
                chefManager.addLastChefToStage(stage);
                bank.setBalance(bank.getBalance() - 30);
                boughtChef = true;
                uiController.hideActions(this);
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
