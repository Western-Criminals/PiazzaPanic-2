package cs.eng1.piazzapanic.food.recipes;

import cs.eng1.piazzapanic.food.FoodTextureManager;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.LinkedList;

public class RecipeTest extends TestCase {


    @Test
    public void testGetTexture() {

    }

    @Test
    public void testGetType() {
    }

    @Test
    public void testGetRecipeIngredients() {

    }

    @Test
    public void testTestGetType() {
        FoodTextureManager testManager = new FoodTextureManager();
        Recipe testRecipe = new Recipe("pizza", testManager);
        assertTrue(testRecipe.getType()=="pizza");
    }

    @Test
    public void testTestGetRecipeIngredients() {
        LinkedList<String> ingredientTypes1 = new LinkedList<>();
        FoodTextureManager testManager = new FoodTextureManager();
        Recipe testRecipe = new Recipe("pizza", testManager);
        ingredientTypes1.add("cheese");
        ingredientTypes1.add("pizza_base_cooked");
        assertTrue(testRecipe.getRecipeIngredients() == ingredientTypes1);
    }
}