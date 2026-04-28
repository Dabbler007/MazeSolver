package ie.homelab.mazesolver.model;

/*
 * Copyright (C) 2025 Derek Fitzsimons
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
import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton data object for a maze.
 *
 * @author derek
 */
public final class Maze implements Serializable {
    /**
     * Wall value. ASCII code for '#'.
     */
    private static final int WALL = 35; // ASCII code for '#'

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Maze.class.getName());

    /**
     * Maze size, range 20 - 100.
     */
    private static int mazeSize;

    /**
     * Default maze size.
     */
    public static final int DEFAULT_SIZE = 20;
    /**
     * Maximum maze size.
     */
    public static final int MAX_SIZE = 100;
    /**
     * Minimum maze size.
     */
    public static final int MIN_SIZE = 20;

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exit point.
     */
    private static Point exit;

    /**
     * Data structure holding grid data.<br>
     * char '#' for wall <br>
     * char '.' for space<br>
     * char 'X' for exit
     */
    private static int[][] grid = null;

    /**
     * Start point.
     */
    private static Point start;

    /**
     * Singleton instance.
     */
    private static final Maze INSTANCE = new Maze();

    /**
     * Set maze size.
     * 
     * @param mazeSize int value.
     */
    public static void setMazeSize(int mazeSize) {
        Maze.mazeSize = mazeSize;
        // Reinitialise grid
        initGrid();
    }

    /**
     * Getter for maze size.
     *
     * @return maze size in range MIN_SIZE - MAX_SIZE inclusive.
     */
    public static int getMazeSize() {
        return mazeSize;
    }

    /**
     * Check given coordinate falls within bounds of maze.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return Is in bounds true/false.
     */
    public static boolean isInBounds(final int x, final int y) {
        boolean output = false;

        if (x >= 0 && x < mazeSize && y >= 0 && y < mazeSize) {
            // coordinate is in bounds
            output = true;
        }
        return output;
    }

    /**
     * Set exit point.
     *
     * @param newExit New value for exit point.
     */
    public static void setExit(final Point newExit) {
        Maze.exit = newExit;
    }

    /**
     * Set maze start Point.
     * 
     * @param newStart Maze start point value.
     */
    public static void setStart(final Point newStart) {
        Maze.start = newStart;
    }

    /**
     * Initialise grid to be full of 'Walls'.
     */
    public static void initGrid() {
        /*
         * Fill grid with 'Walls', represented by char '#'. This is the default state of the maze,
         * and will be modified
         */
        grid = new int[mazeSize][mazeSize];
        for (int[] grid1 : grid) {
            for (int j = 0; j < mazeSize; j++) {
                grid1[j] = Maze.WALL;
            }
        }
    }

    /**
     * Getter for exit Point.
     *
     * @return exit Point.
     */
    public static Point getExit() {
        return exit;
    }

    /**
     * Getter for grid data.
     *
     * @return Grid data.
     */
    public static int[][] getGrid() {
        return grid;
    }

    /**
     * Get start Point.
     *
     * @return start Point.
     */
    public static Point getStart() {
        return start;
    }

    /**
     * Set grid data.
     *
     * @param gridData Grid data.
     */
    public static void setGrid(final int[][] gridData) {
        Maze.grid = gridData;
    }

    /**
     * Default Maze constructor.
     */
    private Maze() {
        // Default maze size.
        setMazeSize(DEFAULT_SIZE);
        initGrid();
    }

    /**
     * Overrides equals.
     *
     * @param obj Object to test equality against.
     * @return True or false
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            // Class is the same by refrerence
            return true;
        }
        if (obj == null) {
            // Class is null
            return false;
        }
        if (getClass() != obj.getClass()) {
            // Not the same class type
            return false;
        } else {
            if (!Arrays.deepEquals(grid, Maze.getGrid())) {
                // Grids are different
                return false;
            }
            if (mazeSize != Maze.getMazeSize()) {
                // Grid sizes are different
                return false;
            }
        }
        // All tests passed, objects are equal
        return true;

    }

    /**
     * Overrides hashCode().
     *
     * @return Object hashcode.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(grid);
        result = prime * result + mazeSize;
        return result;
    }

    /**
     * Overrides Object toString().
     *
     * @return String Object.
     */
    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder();
        output.append("Maze [mazeSize=").append(mazeSize).append("]\n");
        // Top line
        output.append("+");
        for (int i = 0; i < mazeSize; i++) {
            output.append("-");
        }
        output.append("+\n");
        for (int i = 0; i < mazeSize; i++) {
            output.append("|"); // Left edge
            for (int j = 0; j < mazeSize; j++) {
                output.append((char) grid[j][i]);
            }
            output.append("|\n"); // Right edge
        }
        // Bottom line
        output.append("+"); // Bottom left corner
        for (int i = 0; i < mazeSize; i++) {
            output.append("-");
        }
        output.append("+\n"); // Bottom right corner

        output.append("Player [").append(start.x()).append(",");
        output.append(start.y()).append("] ");
        output.append("Exit [").append(exit.x()).append(",");
        output.append(exit.y()).append("]\n");
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, output.toString());
        }
        return output.toString();
    }

    /**
     * Getter for singleton instance.
     * 
     * @return Singleton instance of maze.
     */
    public static Maze getInstance() {
        return INSTANCE;
    }
}
