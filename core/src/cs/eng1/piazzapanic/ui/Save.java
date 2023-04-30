package cs.eng1.piazzapanic.ui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.json.*;

public class Save {
    private JSONObject jsonObject = new JSONObject();
    private String difficulty;
    private int balance;
    private Stack<String> inventory = new Stack<>();
    private List<String> upgrades = new LinkedList<>();
    private Integer timer;
    private int reputation;
    private int patience;

    public Save(String difficulty, int balance, Stack<String> inventory, List<String> upgrades, Integer timer, int reputation, int patience) {
        this.difficulty = difficulty;
        this.balance = balance;
        this.inventory = inventory;
        this.upgrades = upgrades;
        this.timer = timer;
        this.reputation = reputation;
        this.patience = patience;
        toJson();
    }

    public Save(String path) throws Throwable {
        try {
            JSONTokener jsonTokener = new JSONTokener(Files.newInputStream(Paths.get(path)));
            jsonObject = new JSONObject(jsonTokener);
            difficulty = jsonObject.getString("difficulty");
            balance = jsonObject.getInt("balance");
            JSONArray i_lst = jsonObject.getJSONArray("inventory");
            for (Object upgrade : i_lst) {
                inventory.push((String) upgrade);
            }
            JSONArray u_lst = jsonObject.getJSONArray("upgrade");
            for (Object upgrade : u_lst) {
                upgrades.add((String) upgrade);
            }
            timer = jsonObject.getInt("timer");
            reputation = jsonObject.getInt("reputation");
            patience = jsonObject.getInt("patience");
        } catch (IOException e) {
            throw e.getCause();
        }
    }

    public Save() {
        clear();
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Stack<String> getInventory() {
        return inventory;
    }
    public void setInventory(Stack<String> inventory) {
        this.inventory = inventory;
    }

    public List<String> getUpgrade() {
        return upgrades;
    }
    public void setUpgrade(List<String> upgrade) {
        this.upgrades = upgrade;
    }

    public Integer getTimer() {
        return timer;
    }
    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public int getReputation() {
        return reputation;
    }
    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public int getPatience() {
        return patience;
    }
    public void setPatience(int patience) {
        this.patience = patience;
    }

    private void toJson() {
        jsonObject.put("difficulty", difficulty);
        jsonObject.put("balance", balance);
        JSONArray i_lst = new JSONArray();
        for (String inv : inventory) {
            i_lst.put(inv);
        }
        jsonObject.put("inventory", i_lst);
        JSONArray u_lst = new JSONArray();
        for (String upgrade : upgrades) {
            u_lst.put(upgrade);
        }
        jsonObject.put("upgrades", u_lst);
        jsonObject.put("timer", timer);
        jsonObject.put("reputation", reputation);
        jsonObject.put("patience", patience);
    }

    public void clear() {
        difficulty = "normal";
        balance = 0;
        inventory.clear();
        upgrades.clear();
        timer = 0;
        reputation = 3;
        patience = 60;
        toJson();
    }
    public void write(String path) throws Throwable {
        toJson();
        try {
            Writer write = jsonObject.write(new FileWriter(path), 4, 0);
            write.close();
        } catch (IOException e) {
            throw e.getCause();
        }
    }
}
