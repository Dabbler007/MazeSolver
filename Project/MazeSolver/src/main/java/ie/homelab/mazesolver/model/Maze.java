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
package ie.homelab.mazesolver.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Maze {

    /**
     * Data structure holding grid data.<br>
     * char '#' for wall <br>
     * char '.' for space<br>
     * char 'E' for exit
     */
    public int[][] grid = null;

    /**
     * Maze size, range 20 - 100.
     */
    private static int mazeSize;

    /**
     * A Point record
     */
    public record Point(int x, int y) {

        public Point {
            Objects.requireNonNull(x);
            Objects.requireNonNull(y);
        }
    }

    /**
     *
     */
    private Point start;

    /**
     *
     */
    private Point exit;

    /**
     * Default Maze constructor.
     */
    public Maze() {
        // Default maze size.
        mazeSize = 20;
        initGrid();
    }

    /**
     * Parameterised Maze constructor
     *
     * @param mazeSizeValue int value 20 - 100 inclusive
     */
    public Maze(int mazeSizeValue) {
        Maze.mazeSize = mazeSizeValue;
        initGrid();
    }

    /**
     *
     * @return
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     *
     * @param grid
     */
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    /**
     *
     * @return
     */
    public Point getStart() {
        return start;
    }

    /**
     * Getter for maze size
     *
     * @return maze size in range 20 - 100 inclusive
     */
    public static int getMazeSize() {
        return mazeSize;
    }

    /**
     *
     * @return
     */
    public Point getExit() {
        return exit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(grid);
        result = prime * result + mazeSize;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Maze other = (Maze) obj;
        if (!Arrays.deepEquals(grid, other.grid)) {
            return false;
        }
        if (mazeSize != Maze.getMazeSize()) {
            return false;
        }
        return true;
    }

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

        output.append("Player [").append(start.x).append(",").append(start.y).append("] )");
        output.append("Exit [").append(exit.x).append(",").append(exit.y).append("]\n");
        return output.toString();
    }

    /**
     * Initialise grid to be full of 'Walls'.
     */
    private void initGrid() {
        // Fill grid with 'Walls', Make grid 1 cell wider and taller to facilitate generator algorithm
        grid = new int[mazeSize + 1][mazeSize + 1];
        for (int[] grid1 : grid) {
            for (int j = 0; j < mazeSize + 1; j++) {
                grid1[j] = '#';
            }
        }

        // Set start point
        int x = new Random().nextInt(mazeSize); // Use actual maze size rather than grid size
        int y = new Random().nextInt(mazeSize);
        start = new Point(x, y);
        grid[start.x][start.y] = '.';

        // Set exit point
        // Exit point must be on an edge.
        // Choose x or y axis
        if (Math.random() > 0.5) {
            // We choose 'x' as primary
            x = new Random().nextInt(mazeSize); // Use actual maze size rather than grid size

            // y must be 0 or mazeSize
            if (Math.random() >= 0.5) {
                y = 0;
            } else {
                y = mazeSize - 1;
            }
        } else {
            // We choose 'y' as primary

            y = new Random().nextInt(mazeSize); // Use actual maze size rather than grid size
            // x must be 0 or mazeSize
            if (Math.random() >= 0.5) {
                x = 0;
            } else {
                x = mazeSize - 1;
            }
        }

        exit = new Point(x, y);
        grid[exit.x][exit.y] = 'X';
    }

    /**
     * Check given coordinate falls within bounds of maze.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return
     */
    public static boolean isInBounds(int x, int y) {
        boolean output = false;

        if (x >= 0 && x < (mazeSize) && y >= 0 && y < (mazeSize)) {
            // coordinate is in bounds
            output = true;
        }
        return output;
    }
}
