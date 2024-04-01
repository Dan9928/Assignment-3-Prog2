package assignment3.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.ToyManager;
import model.Animals;
import model.Boardgames;
import model.Figures;
import model.Puzzles;
import model.Toy;

public class ToyManagerTest {

    private ToyManager toyManager;
    private final String TEST_FILE_PATH = "res/test_toys.txt";

    @Before
    public void setUp() throws Exception {
        // Create a new ToyManager instance for each test
        toyManager = new ToyManager();
     
    }

    @After
    public void tearDown() throws IOException {
        // Delete the test file after each test
        File testFile = new File(TEST_FILE_PATH);
        testFile.delete();
    }

    @Test
    public void testAddToy() throws Exception {
        // Create a new toy
        Toy toy = new Figures("123", "Action Figure", "Brand X", 15.99, 10, 8, "Superhero");

        // Add the toy to the inventory
        toyManager.addToy(toy);

        // Check if the toy is in the inventory
        ArrayList<Toy> toysList = toyManager.getToysList();
        assertTrue(toysList.contains(toy));
    }

    @Test
    public void testRemoveToy() throws Exception {
        // Create a toy to remove
        Toy toy = new Animals("456", "Stuffed Bear", "Brand Y", 12.99, 5, 3, "Cotton", "Large");

        // Add the toy to the inventory
        toyManager.addToy(toy);

        // Remove the toy from the inventory
        toyManager.RemoveToy(toy);

        // Check if the toy is removed from the inventory
        ArrayList<Toy> toysList = toyManager.getToysList();
        assertFalse(toysList.contains(toy));
    }

    @Test
    public void testBuyToy() throws Exception {
        // Create a toy to buy
        Toy toy = new Puzzles("789", "Jigsaw Puzzle", "Brand Z", 19.99, 3, 10, "500 pieces");

        // Add the toy to the inventory
        toyManager.addToy(toy);

        // Buy the toy
        toyManager.buyToy(toy, 2);

        // Check if the count of the toy is updated
        assertEquals(1, toy.getCount());
    }
    

}


