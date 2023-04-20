package cs.eng1.piazzapanic.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.text.DecimalFormat;

public class LongBoiBankUI extends Label {
	private int balance = 0;
	private final DecimalFormat df = new DecimalFormat("#");

	public LongBoiBankUI(Label.LabelStyle labelStyle) {
		super("Balance: 0", labelStyle);
	}

	public void setBalance(int balance) {
		this.balance = balance;
		updateLabel();
	}
	public int getBalance() {
		return balance;
	}

	public void updateLabel() {
		this.setText("Balance: " + df.format(balance));
	}
}
