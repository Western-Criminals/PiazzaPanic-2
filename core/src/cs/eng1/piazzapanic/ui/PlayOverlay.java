package cs.eng1.piazzapanic.ui;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import cs.eng1.piazzapanic.PiazzaPanicGame;
import cs.eng1.piazzapanic.screens.HomeScreen;
import cs.eng1.piazzapanic.ui.ButtonManager.ButtonColour;

public class PlayOverlay {

  static Save save;

  static {
    try {
      save = new Save("save.json");
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  private final Table table;
  PiazzaPanicGame game;


  public PlayOverlay(final PiazzaPanicGame game) {
    this.game = game;
    table = new Table();
    table.setFillParent(true);
    table.setVisible(false);
    table.center();
    Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
    bgPixmap.setColor(Color.DARK_GRAY);
    bgPixmap.fill();
    TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(
        new Texture(bgPixmap));
    table.setBackground(textureRegionDrawableBg);

    TextButton backButton = game.getButtonManager()
        .createTextButton("Back", ButtonColour.GREY);
    backButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hide();
      }
    });

    TextButton newButton = game.getButtonManager()
            .createTextButton("New Game", ButtonColour.GREEN);
    newButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hide();
        HomeScreen.showDifficultyMenu();
      }
    });

    TextButton loadButton = game.getButtonManager()
            .createTextButton("Load Game", ButtonColour.BLUE);
    loadButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        //add load game functionality
      }
    });
    HorizontalGroup buttons = new HorizontalGroup();
    buttons.addActor(loadButton);
    buttons.addActor(newButton);
    table.add(buttons);
    table.row();
    table.add(backButton).padTop(60f);
  }


  public void addToStage(Stage uiStage) {
    uiStage.addActor(table);
  }

  public void show() {
    table.setVisible(true);
  }

  public void hide() {
    table.setVisible(false);
  }
}
