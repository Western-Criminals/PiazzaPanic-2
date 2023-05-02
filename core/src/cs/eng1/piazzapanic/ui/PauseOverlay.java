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
import cs.eng1.piazzapanic.screens.GameScreen;
import cs.eng1.piazzapanic.ui.ButtonManager.ButtonColour;

import static cs.eng1.piazzapanic.stations.RecipeStation.bank;
import static cs.eng1.piazzapanic.ui.PlayOverlay.save;
import static cs.eng1.piazzapanic.ui.UIOverlay.timer;
import static cs.eng1.piazzapanic.ui.UIOverlay.rep;

public class PauseOverlay {
  private final Table table;
  public PauseOverlay(final PiazzaPanicGame game) {
    table = new Table();
    table.setFillParent(true);
    table.setVisible(false);
    table.center();
    Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    bgPixmap.setColor(new Color(0,0,1,0.3f));
    bgPixmap.fill();
    TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(
        new Texture(bgPixmap));
    table.setBackground(textureRegionDrawableBg);

    Label paused = new Label("Paused",
            new Label.LabelStyle(game.getFontManager().getTitleFont(), null));

    TextButton resumeButton = game.getButtonManager()
        .createTextButton("Resume", ButtonColour.GREY);
    resumeButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hide();
      }
    });

    TextButton settingsButton = game.getButtonManager()
            .createTextButton("Settings", ButtonColour.BLUE);
    settingsButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        GameScreen.showSettingsMenu();
      }
    });

    TextButton homeButton = game.getButtonManager()
            .createTextButton("Save & Quit", ButtonColour.RED);
    homeButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hide();
        save.setBalance(bank.getBalance());
        save.setTimer((int) timer.getTime());
        save.setReputation(rep);
        try {
          save.write("save.json");
        } catch (Throwable e) {
          throw new RuntimeException(e);
        }
        game.loadHomeScreen();
      }
    });

    table.add(paused);
    table.row();
    table.add(resumeButton).padTop(20f);
    table.row();
    table.add(settingsButton).padTop(20f);
    table.row();
    table.add(homeButton).padTop(20f);
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
