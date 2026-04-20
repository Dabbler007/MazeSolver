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
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Generate maze content.<br>
 * Generator ensures a path from start to finish.
 *
 * @author derek
 */
public class MazeGenerator {

    /**
     * Random instance for maze generation.
     */
    private final Random random = new Random();

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
            if (Maze.isInBounds(nx, ny) && grid[nx][ny] == WALL) {
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
                Point nextPoint = neighbours.get(random.nextInt(neighbours.size()));
                wallX = (current.getX() + nextPoint.getX()) / 2;
                wallY = (current.getY() + nextPoint.getY()) / 2;
                if (grid[wallX][wallY] != EXIT) {
                    grid[wallX][wallY] = PATH; // Mark the wall between current and next as path.
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
        if (random.nextBoolean()) {
            z = 0;
        } else {
            z = Maze.getMazeSize() - 1;
        }
        return z;
    }

    /**
     * Return a set of corners for the maze.
     * 
     * @return Set of maze corners that can not be exits.
     */
    private Set<Point> getCorners() {
        Set<Point> output = new HashSet<>();
        int mazeSize = Maze.getMazeSize();
        output.add(new Point(0, 0));
        output.add(new Point(0, mazeSize - 1));
        output.add(new Point(mazeSize - 1, 0));
        output.add(new Point(0, mazeSize - 1));
        return output;
    }

    /**
     * Set Start and Exit points for maze.
     */
    private void setPoints() {
        int mazeSize = Maze.getMazeSize();
        // Set start point
        // Use actual maze size rather than grid size
        int x = 0;
        int y = 0;
        // Make sure start is not on an edge
        do {
            x = random.nextInt(mazeSize);
            y = random.nextInt(mazeSize);
        } while (x > 0 && y > 0 && x < mazeSize && y < mazeSize);

        Maze.setStart(new Point(x, y));

        int[][] grid = Maze.getGrid();
        grid[Maze.getStart().getX()][Maze.getStart().getY()] = PATH;

        // Set of maze corner points
        Set<Point> cornerSet = getCorners();
        // A corner
        Point exitPoint = new Point(0, 0);
        // Set an exit point
        // Exit point must be on an edge.
        // Exit point must not be a corner
        while (cornerSet.contains(exitPoint)) {
            // Choose x or y axis
            if (random.nextBoolean()) {
                // We choose 'x' as primary
                // Use actual maze size rather than grid size
                x = random.nextInt(mazeSize);
                // y must be on maze edge
                y = edgePosition();
            } else {
                // We choose 'y' as primary
                // Use actual maze size rather than grid size
                y = random.nextInt(mazeSize);
                // x must be on maze edge
                x = edgePosition();
            }
            exitPoint = new Point(x, y);
        }
        Maze.setExit(exitPoint);
        grid[Maze.getExit().getX()][Maze.getExit().getY()] = EXIT;
        Maze.setGrid(grid);
    }
}
