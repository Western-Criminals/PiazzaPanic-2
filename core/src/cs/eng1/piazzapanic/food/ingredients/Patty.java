package cs.eng1.piazzapanic.food.ingredients;

import com.badlogic.gdx.graphics.Texture;
import cs.eng1.piazzapanic.food.FoodTextureManager;

public class Patty extends Ingredient {

  protected boolean halfCooked = false;
  protected boolean flipped = false;

  public Patty(FoodTextureManager textureManager) {
    super("patty", textureManager);
  }

  public void setHalfCooked() {
    halfCooked = true;
  }

  public boolean getIsHalfCooked() {
    return halfCooked;
  }
  public void setFlipped() {
    flipped = true;
  }

  /**
   * Get the texture based on whether the patty has been cooked.
   *
   * @return the texture to display.
   */
  @Override
  public Texture getTexture() {
    String name = getType() + "_";
    if (isBurnt) {
      name += "burnt";
    } else if (isCooked || (!flipped && halfCooked)) {
      name += "cooked";
    } else {
      name += "raw";
    }
    return textureManager.getTexture(name);
  }
}
