package cs.eng1.piazzapanic.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import cs.eng1.piazzapanic.PiazzaPanicGame;
import cs.eng1.piazzapanic.stations.Station;
import cs.eng1.piazzapanic.stations.StationAction;
import cs.eng1.piazzapanic.ui.StationActionUI.ActionAlignment;
import cs.eng1.piazzapanic.ui.UIOverlay;

import java.util.List;


public class BurningUI extends Table {

  private ActionAlignment actionAlignment = ActionAlignment.BOTTOM;


  private final Station station;
  private final PiazzaPanicGame game;
  private final ProgressBar progress;


  public BurningUI(final Station station, final PiazzaPanicGame game) {
    this.station = station;
    this.game = game;
    setVisible(false);
    center();
    bottom();

    ProgressBarStyle progressBarStyle = new ProgressBarStyle(new TextureRegionDrawable(new Texture(
        Gdx.files.internal(
            "food/original/clear.png"))), null);
    progressBarStyle.knobBefore = new TextureRegionDrawable(new Texture(Gdx.files.internal(
      "food/original/fire.png")));
    progress = new ProgressBar(0, 100, 0.1f, true, progressBarStyle);
  }

  /**
   * Initialise and show the progress bar with 0 progress.
   */
  public void showProgressBar() {
    progress.setValue(0);
    add(progress).height(UIOverlay.scale).width(UIOverlay.scale);
    setVisible(true);
  }

  /**
   * @param percentage A value between 0 and 100 representing the percentage completed
   */
  public void updateProgress(float percentage) {
    progress.setValue(percentage);
  }

  public void hideProgressBar() {
    clear();
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    calculatePositionFromAlignment();
    super.draw(batch, parentAlpha);
  }

  /**
   * Take the set alignment of buttons and position them and this class on the given side of the
   * station by transforming the station's world position to screen position.
   */
  private void calculatePositionFromAlignment() {
    Vector3 worldPosition = new Vector3();
    switch (actionAlignment) {
      case LEFT:
        // Get left center of station in screen coordinates
        worldPosition.x = station.getX();
        worldPosition.y = station.getY() + station.getHeight() / 2f;
        break;
      case TOP:
        // Get upper middle of station in screen coordinates
        worldPosition.x = station.getX() + station.getWidth() / 2f;
        worldPosition.y = station.getY() + station.getHeight();
        break;
      case RIGHT:
        // Get right center of station in screen coordinates
        worldPosition.x = station.getX() + station.getWidth();
        worldPosition.y = station.getY() + station.getHeight() / 2f;
        break;
      case BOTTOM:
        // Get lower middle of station in screen coordinates
        worldPosition.x = station.getX() + station.getWidth() / 2f;
        worldPosition.y = station.getY();
        break;
    }
    Vector3 screenPosition = station.getStage().getCamera().project(worldPosition);
    setPosition(screenPosition.x, screenPosition.y);
  }
}
