package ie.homelab.mazesolver;

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
import ie.homelab.mazesolver.model.Maze;
import java.io.Console;

/**
 * Maze Solver implemented in Java (JDK 21).
 *
 * @author derek
 */
public class MazeSolver {

    /**
     * Maze data object.
     */
    private static Maze maze;

    /**
     * Default Maze constructor.
     */
    public MazeSolver() {
        // Default maze constructor.
    }

    /*
     * Initialise maze.
     *
     * @param mazeSize int value in the range MIN_SIZE - MAX_SIZE
     */
    private static void initMaze(final int mazeSize) {

        maze = new Maze(mazeSize);
    }

    /**
     * Validate input for maze size.
     *
     * @param rawSize input value.
     * @return valid true/false.
     */
    private static boolean validateMazeSize(final String rawSize) {
        boolean output = false;
        int tempSize;
        try {
            tempSize = Integer.parseInt(rawSize);
            if (tempSize >= Maze.MIN_SIZE && tempSize <= Maze.MAX_SIZE) {
                output = false;
            }
        }
        catch (final NumberFormatException ex) {
            output = true;
            System.out.println("Invalid entry, try again");
        }

        return output;
    }

    /**
     * Java main method.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        System.out.println("-- Iniitialising Mail Solver --");

        Console con = System.console();
        if (con != null) {
            System.out.println("\n\n");

            int mazeSize = Maze.DEFAULT_SIZE;
            boolean invalidSize = true;
            String rawSize;

            // Check for program arguments
            if (args.length == 0) {
                StringBuilder sb = new StringBuilder();

                while (invalidSize) {
                    sb.setLength(0);
                    sb.append("Enter a number (");
                    sb.append(Maze.MIN_SIZE).append("-");
                    sb.append(Maze.MAX_SIZE);
                    sb.append(") for initial maze size: ");
                    System.out.print(sb.toString());
                    rawSize = con.readLine();
                    invalidSize = validateMazeSize(rawSize);
                    if (!invalidSize) {
                        mazeSize = Integer.parseInt(rawSize);
                    }
                }
            } else {
                // Try to validate program argument
                rawSize = args[0];
                invalidSize = validateMazeSize(rawSize);
                if (invalidSize) {
                    mazeSize = Maze.DEFAULT_SIZE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid parameter in command line ");
                    sb.append("- Defaulting to ");
                    sb.append(Maze.DEFAULT_SIZE);
                    System.out.println(sb.toString());
                } else {
                    System.out.println("-- Launching from argument --");
                }
            }
            
            // Initialise
            initMaze(mazeSize);
            System.out.println(maze.toString());
            System.out.println("\n");
            // Generate
            MazeGenerator mazeGenerator = new MazeGenerator(maze);
            mazeGenerator.generateMaze();
            System.out.println(maze);
            System.out.println("\n");
            // Resolve
            MazeResolver resolver = new MazeResolver(maze);
            resolver.resolveMaze();
            System.out.println(maze);
            System.out.print("Path: ");
            System.out.println(""+resolver.getPointDistance(maze.getStart()));
            System.out.println("\n");
        } else {
            System.out.println("\n\n-- OS does not support a console --\n\n");
            StringBuilder sb = new StringBuilder();
            sb.append("\n\n-- Try running program using java instead of javaw --\n\n");
            System.out.println(sb.toString());
        }

        System.out.println("-- Ending Maze Solver --");
    }

}
