package cs.eng1.piazzapanic.ui;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import cs.eng1.piazzapanic.PiazzaPanicGame;
import cs.eng1.piazzapanic.ui.ButtonManager.ButtonColour;

import static cs.eng1.piazzapanic.screens.GameScreen.uiOverlay;
import static cs.eng1.piazzapanic.ui.PlayOverlay.save;

public class DifficultyOverlay {

  private final Table table;
  PiazzaPanicGame game;


  public DifficultyOverlay(final PiazzaPanicGame game) {
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

    Label play = new Label("Play",
            new Label.LabelStyle(game.getFontManager().getTitleFont(), null));

    TextButton normalButton = game.getButtonManager()
            .createTextButton("Normal", ButtonColour.BLUE);
    normalButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hide();
        game.loadGameScreen(false);
        uiOverlay.updatePatience(0);
        save.clear();
        save.setDifficulty("normal");
        save.write("save.json");
      }
    });

    TextButton insaneButton = game.getButtonManager()
            .createTextButton("Insane", ButtonColour.RED);
    insaneButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hide();
        game.loadGameScreen(false);
        uiOverlay.updatePatience(1);
        save.clear();
        save.setDifficulty("insane");
        save.write("save.json");
      }
    });

    TextButton lunaticButton = game.getButtonManager()
            .createTextButton("Lunatic", ButtonColour.VIOLET);
    lunaticButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hide();
        game.loadGameScreen(false);
        uiOverlay.updatePatience(2);
        save.clear();
        save.setDifficulty("lunatic");
        save.write("save.json");
      }
    });

    TextButton endlessButton = game.getButtonManager()
            .createTextButton("Eternity", ButtonColour.YELLOW);
    endlessButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hide();
        game.loadGameScreen(true);
        uiOverlay.updatePatience(0);
        save.clear();
        save.setDifficulty("eternity");
        save.write("save.json");
      }
    });

    table.add();
    table.add(play).padBottom(20f);
    table.row();
    table.add(normalButton);
    table.add(insaneButton);
    table.add(lunaticButton);
    table.row();
    table.add();
    table.add(endlessButton).padTop(30f);
    table.row();
    table.add();
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
