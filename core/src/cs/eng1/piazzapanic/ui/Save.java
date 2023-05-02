package cs.eng1.piazzapanic.ui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cs.eng1.piazzapanic.chef.FixedStack;
import cs.eng1.piazzapanic.food.ingredients.Ingredient;
import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONArray;

public class Save {
    private JSONObject jsonObject = new JSONObject();
    private JSONObject inventoryObject = new JSONObject();
    private String difficulty;
    private int balance;
    private FixedStack<String> inventory = new FixedStack<>(5);
    private ArrayList<FixedStack<String>> inventories = new ArrayList<>();
    private List<String> upgrades = new LinkedList<>();
    private Integer timer;
    private int reputation;
    private int patience;

    public Save(String difficulty, int balance, ArrayList<FixedStack<String>> inventories, List<String> upgrades, Integer timer, int reputation, int patience) {
        this.difficulty = difficulty;
        this.balance = balance;
        this.inventories = inventories;
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
            inventoryObject = jsonObject.getJSONObject("inventories");
            difficulty = jsonObject.getString("difficulty");
            balance = jsonObject.getInt("balance");
            for (int i = 0; i < inventoryObject.length(); i ++) {
                JSONArray i_lst = inventoryObject.getJSONArray(String.format("c%s", i));
                for (Object item : i_lst) {
                    inventory.push((String) item);
                }
                inventories.add(inventory);
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

    public ArrayList<FixedStack<String>> getInventories() {
        return inventories;
    }
    public void setInventories(int index, FixedStack<Ingredient> inventory) {
        for (Ingredient ingredient : inventory) {
            this.inventory.push(ingredient.toString());
        }
        this.inventories.set(index, this.inventory);
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

    public boolean isEndless() {
        return difficulty.equals("eternity");
    }

    private void toJson() {
        jsonObject.put("difficulty", difficulty);
        jsonObject.put("balance", balance);
        JSONArray i_lst = new JSONArray();
        for (int i = 0; i < inventories.size(); i ++) {
            for (String inv : inventory) {
                i_lst.put(inv);
            }
            inventoryObject.put(String.format("c%s", i), i_lst);
        }
        jsonObject.put("inventories", inventoryObject);
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
        inventories.replaceAll(ignored -> inventory);
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
