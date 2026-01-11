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
package ie.homelab.mazesolver;

import ie.homelab.mazesolver.model.Maze;
import ie.homelab.mazesolver.model.Maze.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;


/**
 * Generate maze content.<br>Generator ensures a path from start to finish.
 *
 * @author derek
 */
public class MazeGenerator {

    /**
     * Search distance.
     */
    private static final int DIST = 2;
    /**
     * Maze data object.
     */
    private static Maze maze;

    /**
     * Parameterised MazeGenerator constructor.
     *
     * @param incomingMaze Maze data Object.
     */
    public MazeGenerator(final Maze incomingMaze) {
        MazeGenerator.maze = incomingMaze;
        generateMaze();
    }

    /**
     * Get list of unvisited neighbours to point p.
     *
     * @param p Point in the grid.
     * @param grid Grid data.
     * @return List of Points.
     */
    private List<Point> getUnvisitedNeighbours(final Point p, final int[][] grid) {
        List<Point> output = new ArrayList<>();
        // below, above,left, right
        int[][] directions = {{0, -DIST}, {0, DIST}, {-DIST, 0}, {DIST, 0}};
        int nx;
        int ny;
        for (int[] dir : directions) {
            nx = p.x() + dir[0];
            ny = p.y() + dir[1];

            // Unvisited Neighbours are in bounds and contain a wall or an exit
            if (Maze.isInBounds(nx, ny) && (grid[nx][ny] == '#' || grid[nx][ny] == 'X')) {
                output.add(new Maze.Point(nx, ny));
            }
        }
        return output;
    }

    /**
     * Generate a maze path.
     */
    public final void generateMaze() {
        // Randomized DFS Backtracking
        final Stack<Point> queue = new Stack<>();

        int[][] grid = maze.getGrid();
        Maze.Point start = maze.getStart();
        queue.push(start); // Push start position onto stack
        grid[start.x()][start.y()] = '.'; // ensure start position holds a path value
        Point current;
        List<Point> neighbours;
        int wallX;
        int wallY;
        while (!queue.isEmpty()) {
            current = queue.peek();
            neighbours = getUnvisitedNeighbours(current, grid);
            if (!neighbours.isEmpty()) {
                // Select a random neighbour
                Maze.Point next = neighbours.get(new Random().nextInt(neighbours.size()));
                wallX = (current.x() + next.x()) / 2;
                wallY = (current.y() + next.y()) / 2;
                if (grid[wallX][wallY] != 'X') {
                    grid[wallX][wallY] = '.'; // Mark the wall between current and next as path
                }
                // Mark neighbour as path and move on
                if (grid[next.x()][next.y()] != 'X') {
                    grid[next.x()][next.y()] = '.';
                }
                queue.push(next);
            } else {
                queue.pop(); // No neighbours to investigate
            }
        }
    }
}
