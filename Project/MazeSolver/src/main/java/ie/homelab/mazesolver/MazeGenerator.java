package ie.homelab.mazesolver;

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
import ie.homelab.mazesolver.model.Maze;
import ie.homelab.mazesolver.model.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

/**
 * Generate maze content.<br>
 * Generator ensures a path from start to finish.
 *
 * @author derek
 */
public class MazeGenerator {

    /**
     * Exit ASCII code 88 'X'.
     */
    private static final int EXIT = 88;

    /**
     * Path ASCII code 46 '.'.
     */
    private static final int PATH = 46;

    /**
     * Wall ASCII code 35 '#'.
     */
    private static final int WALL = 35;
    /**
     * Search distance.
     */
    private static final int DIST = 2;

    /**
     * Default constructor.
     * 
     */
    public MazeGenerator() {
        Maze.initGrid();
        generateMaze();
    }

    /**
     * Get list of unvisited neighbours to point p.
     *
     * @param p Point in the grid.
     * 
     * @return List of Points.
     */
    private List<Point> getUnvisitedNeighbours(final Point p) {
        final int[][] grid = Maze.getGrid();
        List<Point> output = new ArrayList<>();

        // below, above,left, right.
        int[][] directions = {{0, -DIST}, {0, DIST}, {-DIST, 0}, {DIST, 0}};
        int nx;
        int ny;
        for (int[] dir : directions) {
            nx = p.getX() + dir[0];
            ny = p.getY() + dir[1];

            // Unvisited Neighbours are in bounds and contain a wall or an exit.
            if (Maze.isInBounds(nx, ny) && ((grid[nx][ny] == WALL || grid[nx][ny] == EXIT))) {
                output.add(new Point(nx, ny));
            }
        }
        return output;
    }

    /**
     * Generate a maze path.
     */
    public final void generateMaze() {

        setPoints();

        // Randomized DFS Backtracking
        final Deque<Point> queue = new ArrayDeque<>();

        int[][] grid = Maze.getGrid();
        Point start = Maze.getStart();
        queue.push(start); // Push start position onto stack.
        grid[start.getX()][start.getY()] = PATH; // ensure start position holds a path value.
        Point current;
        List<Point> neighbours;
        int wallX;
        int wallY;
        while (!queue.isEmpty()) {
            current = queue.peek();
            neighbours = getUnvisitedNeighbours(current);
            if (!neighbours.isEmpty()) {
                // Select a random neighbour
                Point nextPoint = neighbours.get(new Random().nextInt(neighbours.size()));
                wallX = (current.getX() + nextPoint.getX()) / 2;
                wallY = (current.getY() + nextPoint.getY()) / 2;
                if (grid[wallX][wallY] != EXIT) {
                    grid[wallX][wallY] = '.'; // Mark the wall between current and next as path.
                }
                // Mark neighbour as path and move on.
                if (grid[nextPoint.getX()][nextPoint.getY()] != EXIT) {
                    grid[nextPoint.getX()][nextPoint.getY()] = PATH;
                }
                queue.push(nextPoint);
            } else {
                queue.pop(); // No neighbours to investigate.
            }
        }
        Maze.setGrid(grid);
    }

    /*
     * Edge Position.
     *
     * @return int value representing a near or far edge on the edge of the maze.<br>Chosen by
     * random value.
     */
    private int edgePosition() {
        int z;
        // z must be 0 or mazeSize - 1
        if (new Random().nextBoolean() == true) {
            z = 0;
        } else {
            z = Maze.getMazeSize() - 1;
        }
        return z;
    }

    /**
     * Set Start and Exit points for maze.
     */
    private void setPoints() {
        int mazeSize = Maze.getMazeSize();
        // Set start point
        // Use actual maze size rather than grid size
        int x = new Random(System.currentTimeMillis()).nextInt(mazeSize);
        int y = new Random(System.currentTimeMillis()).nextInt(mazeSize);
        Maze.setStart(new Point(x, y));

        int[][] grid = Maze.getGrid();
        grid[Maze.getStart().getX()][Maze.getStart().getY()] = PATH;

        // Set exit point
        // Exit point must be on an edge.
        // Choose x or y axis
        if (new Random().nextBoolean() == true) {
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

        Maze.setExit(new Point(x, y));
        grid[Maze.getExit().getX()][Maze.getExit().getY()] = 'X';
        Maze.setGrid(grid);
    }
}
