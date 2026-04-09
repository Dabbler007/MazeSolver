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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author derek
 */
public class MazeGeneratorTest {

    Maze maze = null;
    
    public MazeGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        maze = new Maze();
    }

    @After
    public void tearDown() {
        maze = null;
    }

    /**
     * Test of generateMaze method, of class MazeGenerator.
     */
    @Test
    public void testGenerateMaze() {
        System.out.println("generateDefaultMaze");
        MazeGenerator instance = new MazeGenerator(maze);
        instance.generateMaze();

        assertTrue(instance != null);
        assertTrue(maze.getGrid().length == (Maze.DEFAULT_SIZE+1));
    }

        /**
     * Test of generateMaze method, of class MazeGenerator.
     */
    @Test
    public void testDefaultGenerateMaze() {
        System.out.println("generateMaze");
        maze = new Maze(100);
        MazeGenerator instance = new MazeGenerator(maze);
        instance.generateMaze();

        assertTrue(instance != null);
        assertTrue(maze.getGrid().length == 101);
    }
}