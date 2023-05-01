package cs.eng1.piazzapanic.food;

public class LongBoiBank {
	private static int balance = 0;

	public void setBalance(int balance) {
		LongBoiBank.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void earn(String dish) {
		switch(dish) {
			case "humborge":
				balance += 40;
				break;
			case "salad":
				balance += 30;
				break;
			case "pizza":
				balance += 25;
				break;
			case "jacket_potato":
				balance += 25;
				break;
			default:
				break;
		}
	}

	private boolean checkEnoughLongBoiCoins(int cost) {
		return (balance - cost >= 0);
	}

	public void spend(String difficulty) {
		switch(difficulty) {
			case "normal":
				if (checkEnoughLongBoiCoins(20)) {
					balance -= 20;
				}
				break;
			case "insane":
			case "eternity":
				// gonna implement this bad boi later ¯\_(ツ)_/¯
				// making it the same as insane for now
				if (checkEnoughLongBoiCoins(30)) {
					balance -= 30;
				}
				break;
			case "lunatic":
				if (checkEnoughLongBoiCoins(50)) {
					balance -= 50;
				}
				break;
		}
	}
}