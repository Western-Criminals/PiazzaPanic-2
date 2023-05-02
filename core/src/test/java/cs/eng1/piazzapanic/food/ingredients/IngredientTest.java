package cs.eng1.piazzapanic.food.ingredients;

import cs.eng1.piazzapanic.food.FoodTextureManager;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(GdxTestRunner.class)
public class IngredientTest extends TestCase {

    @Test
    public void testTestToString() {
        FoodTextureManager testManager = new FoodTextureManager();
        Ingredient testIngredient = new Ingredient("lettuce", testManager);
        assertTrue(testIngredient.toString() == "lettuce_raw");

    }



    @Test
    public void testGetType() {
        FoodTextureManager testManager = new FoodTextureManager();
        Ingredient testIngredient = new Ingredient("lettuce", testManager);
        assertTrue(testIngredient.getType() == "lettuce");
    }


    @Test
    public void testSetIsCooked() {
        FoodTextureManager testManager = new FoodTextureManager();
        Ingredient testIngredient = new Ingredient("lettuce", testManager);
        testIngredient.setIsCooked(true);
        assertTrue(testIngredient.isCooked);
    }


    @Test
    public void testSetIsBurnt() {
        FoodTextureManager testManager = new FoodTextureManager();
        Ingredient testIngredient = new Ingredient("lettuce", testManager);
        testIngredient.setIsBurnt(true);
        assertTrue(testIngredient.isBurnt);
    }



    @Test
    public void testSetIsChopped() {
        FoodTextureManager testManager = new FoodTextureManager();
        Ingredient testIngredient = new Ingredient("lettuce", testManager);
        testIngredient.setIsChopped(true);
        assertTrue(testIngredient.isChopped);
    }



}