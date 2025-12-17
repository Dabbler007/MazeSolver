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

public class Maze {

    /**
     * Data structure holding grid data.<br>char '#' for wall <br>char '.' for space<br>char 'E' for exit
     */
    private int[][] grid = null;

    /**
     * Maze size, range 20 - 100.
     */
    private int mazeSize;

    /**
     * A Point record
     */
    private record Point(int x, int y) {

        public Point {
            Objects.requireNonNull(x);
            Objects.requireNonNull(y);
        }
    }

    private Point start;
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
        this.mazeSize = mazeSizeValue;
        initGrid();
    }

    /**
     * Getter for maze size
     *
     * @return maze size in range 20 - 100 inclusive
     */
    public int getMazeSize() {
        return mazeSize;
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
        Maze other = (Maze) obj;
        if (!Arrays.deepEquals(grid, other.grid)) {
            return false;
        }
        if (mazeSize != other.mazeSize) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder();
        output.append("Maze [ mazeSize=").append(mazeSize).append("]\n");
        for (int[] grid1 : grid) {
            for (int j = 0; j < grid.length; j++) {
                output.append((char) grid1[j]);
            }
            output.append("\n");
        }
        output.append("Player [").append(start.x).append(",").append(start.y).append("] )");
        output.append("Exit [").append(exit.x).append(",").append(exit.y).append("]\n");
        return output.toString();
    }

    /**
     * Initialise grid to be full of 'Walls'.
     */
    private void initGrid() {
        // Fill grid with 'Walls'
        grid = new int[mazeSize][mazeSize];
        for (int[] grid1 : grid) {
            for (int j = 0; j < grid.length; j++) {
                grid1[j] = '#';
            }
        }

        // Set start point
        double value = Math.random() * mazeSize;
        String val = "" + value;
        val = val.substring(0, val.indexOf('.'));
        int x = Integer.parseInt("" + val);
        System.out.println(value + "  " + x + "  " + val);
        value = Math.random() * mazeSize;
        val = "" + value;
        val = val.substring(0, val.indexOf('.'));
        int y = Integer.parseInt("" + val);
        start = new Point(x, y);
        grid[start.x][start.y] = '.';

        // Set exit point
        // Exit point must be on an edge.
        // Choose x or y axis
        if (Math.random() > 0.5) {
            // We choose 'x' as primary
            value = Math.random() * mazeSize;
            val = "" + value;
            val = val.substring(0, val.indexOf('.'));
            x = Integer.parseInt("" + val);

            // y must be 0 or mazeSize -1
            if (Math.random() >= 0.5) {
                y = 0;
            } else {
                y = mazeSize - 1;
            }
        } else {
            // We choose 'y' as primary
            value = Math.random() * mazeSize;
            val = "" + value;
            val = val.substring(0, val.indexOf('.'));
            y = Integer.parseInt("" + val);
            // x must be 0 or mazeSize -1
            if (Math.random() >= 0.5) {
                x = 0;
            } else {
                x = mazeSize - 1;
            }
        }

        exit = new Point(x, y);
        grid[exit.x][exit.y] = 'X';
    }

}
