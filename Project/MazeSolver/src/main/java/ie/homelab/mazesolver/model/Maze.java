package ie.homelab.mazesolver.model;

/*
 * Copyright (C) 2025 Derek Fitzsimons
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Data object for a maze.
 *
 * @author derek
 */
public class Maze {

    /**
     * Half multiplier.
     */
    private static final double HALF = 0.5d;
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

        if (x >= 0 && x < (mazeSize) && y >= 0 && y < (mazeSize)) {
            // coordinate is in bounds
            output = true;
        }
        return output;
    }

    /*
     * Exit point.
     */
    private Point exit;

    /*
     * Data structure holding grid data.<br>
     * char '#' for wall <br>
     * char '.' for space<br>
     * char 'E' for exit
     */
    private int[][] grid = null;

    /*
     * Start point.
     */
    private Point start;

    /**
     * Default Maze constructor.
     */
    public Maze() {
        // Default maze size.
        mazeSize = DEFAULT_SIZE;
        initGrid();
    }

    /**
     * Parameterised Maze constructor.
     *
     * @param mazeSizeValue int value MIN_SIZE - MAX_SIZE inclusive.
     */
    public Maze(final int mazeSizeValue) {
        Maze.mazeSize = mazeSizeValue;
        initGrid();
    }

    /*
     * Edge Position.
     *
     * @return int value representing a near or far edge on the edge of the
        maze.<br>Chosen by random value.
     */
    private int edgePosition() {
        int z;
        // z must be 0 or mazeSize - 1
        if (Math.random() >= HALF) {
            z = 0;
        } else {
            z = mazeSize - 1;
        }
        return z;
    }

    /*
     * Initialise grid to be full of 'Walls'.
     */
    private void initGrid() {
        /* Fill grid with 'Walls', Make grid 1 cell wider and taller to
        facilitate generator algorithm */
        grid = new int[mazeSize + 1][mazeSize + 1];
        for (int[] grid1 : grid) {
            for (int j = 0; j < mazeSize + 1; j++) {
                grid1[j] = '#';
            }
        }

        // Set start point
        // Use actual maze size rather than grid size
        int x = new Random().nextInt(mazeSize);
        int y = new Random().nextInt(mazeSize);
        start = new Point(x, y);
        grid[start.x][start.y] = '.';

        // Set exit point
        // Exit point must be on an edge.
        // Choose x or y axis
        if (Math.random() > HALF) {
            // We choose 'x' as primary
            // Use actual maze size rather than grid size
            x = new Random().nextInt(mazeSize);
            // y must be on maze edge
            y = edgePosition();
        } else {
            // We choose 'y' as primary
            // Use actual maze size rather than grid size
            y = new Random().nextInt(mazeSize);
            // x must be on maze edge
            x = edgePosition();
        }

        exit = new Point(x, y);
        grid[exit.x][exit.y] = 'X';
    }

    /**
     * Overrides equals.
     *
     * @param obj Object to test equality against.
     * @return True or false
     */
    @Override
    public boolean equals(final Object obj) {
        boolean output = false;
        if (this == obj) {
            output = true;
        } else if (obj == null) {
            output = false;
        } else if (getClass() != obj.getClass()) {
            output = false;
        } else {
            final Maze other = (Maze) obj;
            if (!Arrays.deepEquals(grid, other.grid)) {
                output = false;
            } else if (mazeSize != Maze.getMazeSize()) {
                output = false;
            }
        }
        return output;
    }

    /**
     * Getter for exit Point.
     *
     * @return exit Point.
     */
    public Point getExit() {
        return exit;
    }

    /**
     * Getter for grid data.
     *
     * @return Grid data.
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * Get start Point.
     *
     * @return start Point.
     */
    public Point getStart() {
        return start;
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
     * Set grid data.
     *
     * @param gridData Grid data.
     */
    public void setGrid(final int[][] gridData) {
        this.grid = gridData;
    }

    /**
     * Overrides Object toString().
     *
     * @return String Object.
     */
    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder();
        output.append("Maze [ mazeSize=").append(mazeSize).append("]\n");
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

        output.append("Player [").append(start.x).append(",");
        output.append(start.y).append("] )");
        output.append("Exit [").append(exit.x).append(",");
        output.append(exit.y).append("]\n");
        return output.toString();
    }

    /**
     * A Point record.
     *
     * @param x int value representing a point on the x axis.
     * <br>In range MIN_SIZE - MAX_SIZE inclusive.
     * @param y int value representing a point on the y axis.
     * <br>In range MIN_SIZE - MAX_SIZE inclusive.
     */
    public record Point(int x, int y) {

        /**
         * Point record constraints.
         */
        public Point {
            Objects.requireNonNull(x);
            Objects.requireNonNull(y);
        }
    }
}
