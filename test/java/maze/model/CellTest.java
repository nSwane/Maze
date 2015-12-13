/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.model;

import maze.model.Cell;
import maze.model.Item;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class CellTest {
    
    public CellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getItem method, of class Cell.
     */
    @Test
    public void testGetItem() {
        System.out.println("getItem");
        Cell instance = new Cell(Item.WALL, 5, 3);
        Item expResult = Item.WALL;
        Item result = instance.getItem();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriority method, of class Cell.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        Cell instance = new Cell(Item.WALL, 5, 3);
        int expResult = Integer.MAX_VALUE;
        int result = instance.getPriority();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPriority method, of class Cell.
     */
    @Test
    public void testSetPriority() {
        System.out.println("setPriority");
        int priority = 0;
        Cell instance = new Cell(Item.WALL, 5, 3);
        instance.setPriority(priority);
        assertEquals(priority, instance.getPriority());
    }

    /**
     * Test of setItem method, of class Cell.
     */
    @Test
    public void testSetItem() {
        System.out.println("setItem");
        Item item = Item.WALL;
        Cell instance = new Cell(Item.WALL, 5, 3);
        instance.setItem(item);
        assertEquals(item, instance.getItem());
    }

    /**
     * Test of getRow method, of class Cell.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Cell instance = new Cell(Item.WALL, 5, 3);
        int expResult = 5;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumn method, of class Cell.
     */
    @Test
    public void testGetColumn() {
        System.out.println("getColumn");
        Cell instance = new Cell(Item.WALL, 5, 3);
        int expResult = 3;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Cell.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Cell o = new Cell(Item.WALL, 5, 3);
        o.setPriority(0);
        Cell instance = new Cell(Item.START_POINT, 0, 0);
        instance.setPriority(2);
        int expResult = 1; // 'instance' > 'o'
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }
    
}
