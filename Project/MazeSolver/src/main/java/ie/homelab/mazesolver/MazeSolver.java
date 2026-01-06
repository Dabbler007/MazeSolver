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
import java.io.Console;

/**
 * Maze Solver implemented in Java (JDK 21)
 *
 * @author derek
 */
public class MazeSolver {

    private static Maze maze;

    /**
     * Java main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("-- Iniitialising Mail Solver --");

        Console con = System.console();
        if (con != null) {
            System.out.println("\n\n");

            int mazeSize = 20;
            boolean invalidSize = true;
            String rawSize;

            // Check for program arguments
            if (args.length == 0) {
                while (invalidSize) {
                    System.out.print("Enter a number (20-100) for initial maze size: ");
                    rawSize = con.readLine();
                    invalidSize = validateMazeSize(rawSize, mazeSize);
                    if (!invalidSize) {
                        mazeSize = Integer.parseInt(rawSize);
                    }
                }
            } else {
                // Try to validate program argument
                rawSize = args[0];
                invalidSize = validateMazeSize(rawSize, mazeSize);
                if (invalidSize) {
                    mazeSize = 20;
                    System.out.println("Invalid parameter in command line - Defaulting to 20");
                } else {
                    System.out.println("-- Launching from argument --");
                }
            }
            initMaze(mazeSize);
            MazeGenerator mazeGenerator = new MazeGenerator(maze);
            mazeGenerator.generateMaze();
            System.out.println(maze);
            System.out.println("\n\n");
        } else {
            System.out.println("\n\n-- OS does not support a console --\n\n");
            System.out.println("\n\n-- Try running program using java instead of javaw --\n\n");
        }

        System.out.println("-- Ending Maze Solver --");
    }

    private static boolean validateMazeSize(String rawSize, int mazeSize) {
        boolean output = false;

        try {
            mazeSize = Integer.parseInt(rawSize);
            if (mazeSize >= 20 && mazeSize <= 100) {
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
     * initialise maze.
     *
     * @param mazeSize int value in the range 20 - 100
     */
    private static void initMaze(int mazeSize) {

        maze = new Maze(mazeSize);
        System.out.println(maze.toString());
    }

}
