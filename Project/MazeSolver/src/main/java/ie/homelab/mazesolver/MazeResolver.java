package ie.homelab.mazesolver;

/*
 * Copyright (C) 2026 derek
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
import ie.homelab.mazesolver.model.Maze;
import ie.homelab.mazesolver.model.Maze.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * MazeResolver. Used to resolve the shortest path from start point to exit point.
 *
 * @author derek
 */
public class MazeResolver {

    /**
     * Search distance.
     */
    private static int DIST = 1;

    /**
     * Search directions
     */
    private static int[][] DIRECTIONS = {{0, -DIST}, {0, DIST}, {-DIST, 0}, {DIST, 0}};
    /**
     * Maze data object.
     */
    private static Maze maze;
    /**
     * Array of distances from exit.
     */
    private int[][] distance;

    /**
     * Default constructor.
     */
    public MazeResolver() {

    }

    /**
     * Parameterised constructor.
     *
     * @param input Incoming maze.
     */
    public MazeResolver(final Maze input) {
        maze = input;

    }

    /**
     * Resolve maze path.
     *
     * @return true / false.
     */
    public boolean resolveMaze() {
        boolean output = true;

        // Track distances travelled from each point
        distance = new int[maze.getGrid().length - 1][maze.getGrid().length - 1];

        distance = initialiseDistances(distance);

        // Exit point distance from exit is 0
        distance[maze.getExit().x()][maze.getExit().y()] = 0;
        Stack<Point> stack = new Stack<>();
        // Make exit start point for reversing the maze.
        stack.push(maze.getExit());

        Point current;
        int currentDistance;
        List<Point> neighbours;
        while (!stack.empty()) {
            current = stack.pop();
            currentDistance = distance[current.x()][current.y()];
            // Find neighbours for cell at top of stack
            neighbours = getUnvisitedNeighbours(current, distance);

            // Add neighbours to stack, record distance as current distance +1
            for (Point n : neighbours) {
                // if neighbour not visited
                if (distance[n.x()][n.y()] <= -1) {
                    stack.push(n);
                    distance[n.x()][n.y()] = currentDistance + 1;
                }
            }
        }

        System.out.println(renderDistances(distance));
        return output;
    }

    /**
     * Get list of unvisited neighbours to point p.
     *
     * @param p Point in the grid.
     * @param grid Grid data.
     * @return List of Points.
     */
    private List<Point> getUnvisitedNeighbours(final Point p, final int[][] distance) {
        List<Point> output = new ArrayList<>();

        // below, above,left, right.
        int nx;
        int ny;
        for (int[] dir : DIRECTIONS) {
            nx = p.x() + dir[0];
            ny = p.y() + dir[1];

            // Unvisited Neighbours are in bounds and contain an unvisited value.
            if (Maze.isInBounds(nx, ny) && distance[nx][ny] <= -1 && maze.getGrid()[nx][ny] != '#') {
                output.add(new Maze.Point(nx, ny));
            }
        }
        return output;
    }

    /**
     * Initialise distances to -1.
     *
     * @param distances
     */
    private int[][] initialiseDistances(final int[][] distances) {

        for (int y = 0; y < distances.length; y++) {
            for (int[] rowValues : distances) {
                rowValues[y] = -1;
            }
        }
        return distances;
    }

    /**
     *
     * @param distances
     * @return
     */
    private String renderDistances(final int[][] distances) {
        final StringBuilder output = new StringBuilder();

        //By row
        for (int x = 0; x < distances.length; x++) {
            for (int[] rowValues : distances) {
                if (rowValues[x] < 0) {
                    output.append("   ,");
                } else {
                    if (rowValues[x] < 10) {
                        output.append("  ");
                    } else if (rowValues[x] < 100) {
                        output.append(" ");
                    }
                    output.append(rowValues[x]).append(',');
                }
            }
            output.append("\n");
        }
        return output.toString();
    }

    /**
     * Get distance from resolved exit.
     *
     * @param p Point to measure distance from to exit.
     * @return Distance from point to exit. Returns -1 if maze not resolved.
     */
    public int getPointDistance(final Point p) {
        int output = -1;
        if (distance != null && distance.length > 0) {
            output = distance[p.x()][p.y()];
        }
        return output;
    }

}
