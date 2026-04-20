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

package ie.homelab.mazesolver;

import ie.homelab.mazesolver.model.Maze;
import ie.homelab.mazesolver.model.Point;
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
public class MazeResolverTest {

    public MazeResolverTest() {
        // Default Constructor
    }

    @BeforeClass
    public static void setUpClass() {
        // Set up resources before any tests are run
        MazeGenerator generator = new MazeGenerator();
        generator.generateMaze();
    }

    @AfterClass
    public static void tearDownClass() {
        // Tear down resources after all tests are run
    }

    @Before
    public void setUp() {
        // Set up before each test
    }

    @After
    public void tearDown() {
        //Tear down after each test
    }

    /**
     * Test of resolveMaze method, of class MazeResolver.
     */
    @Test
    public void testResolveMaze() {
        System.out.println("resolveMaze");
        
        // Set logging level to expand test coverage
        Logger log = Logger.getLogger("ie.homelab.mazesolver");
        log.setLevel(Level.FINE);
        
        MazeResolver instance = new MazeResolver();
        boolean expResult = true;
        boolean result = instance.resolveMaze();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPointDistance method, of class MazeResolver.
     */
    @Test
    public void testGetPointDistance() {
        System.out.println("getPointDistance");
        Point p = new Point(0,0);
        MazeResolver instance = new MazeResolver();
        
        // Set logging level to expand test coverage
        Logger log = Logger.getLogger("ie.homelab.mazesolver");
        log.setLevel(Level.FINE);
        
        int expResult = -1;
        int result = instance.getPointDistance(p);
        assertEquals(expResult, result);
        
        instance.resolveMaze();
        // Maze distances work towards start from exit.
        p = Maze.getStart();
        //So start should be 1 or greater      
        assertTrue(instance.getPointDistance(p) > 0);

    }

}