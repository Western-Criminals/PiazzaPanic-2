package cs.eng1.piazzapanic.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.text.DecimalFormat;

import cs.eng1.piazzapanic.stations.RecipeStation;

public class LongBoiBankUI extends Label {
	private int balance = 0;
	private final DecimalFormat df = new DecimalFormat("#");

	public LongBoiBankUI(Label.LabelStyle labelStyle) {
		super("Balance: 0", labelStyle);
	}

	public void reset() {
		balance = 0;
		RecipeStation.bank.setBalance(balance);
		setText("Balance: 0");
	}

	public int getBalance() {
		return balance;
	}

	@Override
	public void act(float x) {
		balance = RecipeStation.bank.getBalance();
		updateLabel();
	}

	public void updateLabel() {
		setText("Balance: " + df.format(balance));
	}
}
