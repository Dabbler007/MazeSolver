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

public class Maze {

    /**
     * Data structure holding grid data.
     * char '#' for wall
     * char '.' for space
     * char 'E' for exit
     */
    private int[][] grid = null;

    /**
     * Maze size, range 20 - 100.
     */
    private int mazeSize;

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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Maze other = (Maze) obj;
        if (!Arrays.deepEquals(grid, other.grid))
            return false;
        if (mazeSize != other.mazeSize)
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder();
        output.append( "Maze [ mazeSize=" ).append( mazeSize ).append( "]\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                output.append((char)grid[i][j]);
            }
            output.append("\n");

        }
        return output.toString();
    }

    /**
     * Initialise grid to be full of 'Walls'.
     */
    private void initGrid() {
        grid = new int[mazeSize][mazeSize];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = '#';
            }
        }
    }
    
}
