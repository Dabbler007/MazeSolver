/*
 * Copyright (C) 2026 derek
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package ie.homelab.mazesolver.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author derek
 */
public class MazeTest {

    @BeforeClass
    public static void setUpClass() {
        // Set up resources before any tests are run
    }

    @AfterClass
    public static void tearDownClass() {
        // Tear down resources after all tests are run
    }

    public MazeTest() {
        // Default constructor
    }

    @Before
    public void setUp() {
        // Set up before each test
        Maze.setMazeSize(20);
    }

    @After
    public void tearDown() {
        // Tear down after each test
    }

    /**
     * Test of getMazeSize method, of class Maze.
     */
    @Test
    public void testGetMazeSize() {
        System.out.println("getMazeSize");
        int expResult = Maze.DEFAULT_SIZE;
        int result = Maze.getMazeSize();
        assertEquals(expResult, result);

        Maze.setMazeSize(25);
        expResult = 25;
        result = Maze.getMazeSize();
        assertEquals(expResult, result);

        Maze.setMazeSize(0);
        expResult = 0;
        result = Maze.getMazeSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of isInBounds method, of class Maze.
     */
    @Test
    public void testIsInBounds() {
        System.out.println("isInBounds");
        int x = 0;
        int y = 0;
        boolean expResult = true;
        boolean result = Maze.isInBounds(x, y);
        assertEquals(expResult, result);

        x = 19;
        y = 19;
        result = Maze.isInBounds(x, y);
        assertEquals(expResult, result);

        expResult = false;
        x = 20;
        y = 20;
        result = Maze.isInBounds(x, y);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Maze.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = Maze.getInstance();
        Maze instance = Maze.getInstance();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        // Because Maze is static all instances are equal
        Maze.setMazeSize(25);
        result = instance.equals(obj);
        assertEquals(expResult, result);

        assertNotNull(instance);

        result = instance.equals(new Object());
        assertEquals(false, result);
        
        Maze.setMazeSize(20);
        obj = null;
        instance = Maze.getInstance();
        expResult = false;
        result = instance.equals(obj); 
        assertEquals(expResult,result);
        
        Maze.setMazeSize(27);
        obj = "";
        instance = Maze.getInstance();
        expResult = false;
        result = instance.equals(obj); 
        assertEquals(expResult,result);
    }

    /**
     * Test of getExit method, of class Maze.
     */
    @Test
    public void testGetExit() {
        System.out.println("getExit");
        Maze.setExit(new Point(10, 10));
        Point expResult = new Point(10, 10);
        Point result = Maze.getExit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGrid method, of class Maze.
     */
    @Test
    public void testGetGrid() {
        System.out.println("getGrid");

        int gridSize = Maze.DEFAULT_SIZE;
        int resultSize = Maze.getGrid().length;
        assertEquals(gridSize, resultSize);
        int[][] gridData = {{1, 0, 1}, {1, 1, 1}, {0, 1, 1}};
        Maze.setGrid(gridData);
        int expResult = 3;
        int[][] result = Maze.getGrid();
        assertEquals(expResult, result.length);
        assertArrayEquals(gridData, result);
    }

    /**
     * Test of getStart method, of class Maze.
     */
    @Test
    public void testGetStart() {
        System.out.println("getStart");
        Maze.setStart(new Point(0, 0));
        boolean expResult = true;
        boolean result = (Maze.getStart() != null);
        assertEquals(expResult, result);
        assertEquals(new Point(0, 0), Maze.getStart());
    }

    /**
     * Test of hashCode method, of class Maze.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Maze instance = Maze.getInstance();
        int expResult = 989084980;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGrid method, of class Maze.
     */
    @Test
    public void testSetGrid() {
        System.out.println("setGrid");
        int[][] gridData = {{1, 0, 1}, {1, 1, 1}, {0, 1, 1}};
        Maze.setGrid(gridData);
        int expResult = 3;
        int result = Maze.getGrid().length;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Maze.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        StringBuilder expResult = new StringBuilder();
        expResult.append("Maze [mazeSize=20]\n+--------------------+\n");
        expResult.append("|####################|\n|####################|\n");
        expResult.append(
                        "|####################|\n|####################|\n|####################|\n");
        expResult.append(
                        "|####################|\n|####################|\n|####################|\n");
        expResult.append(
                        "|####################|\n|####################|\n|####################|\n");
        expResult.append(
                        "|####################|\n|####################|\n|####################|\n");
        expResult.append(
                        "|####################|\n|####################|\n|####################|\n");
        expResult.append(
                        "|####################|\n|####################|\n|####################|\n");
        expResult.append("+--------------------+\nPlayer [0,0] Exit [19,19]\n");
        Maze.setStart(new Point(0, 0));
        Maze.setExit(new Point(19, 19));
        String result = Maze.getInstance().toString();
        assertEquals(expResult.toString(), result);
        
        // Parent logger of Maze logger.
        Logger log = Logger.getLogger("ie.homelab.mazesolver.model");
        log.setLevel(Level.FINE);
        result = Maze.getInstance().toString();
        assertEquals(expResult.toString(), result);
    }
}
