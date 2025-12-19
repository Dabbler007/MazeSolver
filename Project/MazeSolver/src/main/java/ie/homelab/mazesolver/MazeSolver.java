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

import java.io.Console;

import ie.homelab.mazesolver.model.Maze;

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
            while (invalidSize) {
                System.out.print("Enter a number (20-100) for initial maze size: ");
                String rawsize = con.readLine();
                try {
                    mazeSize = Integer.parseInt(rawsize);
                    if (mazeSize >= 20 && mazeSize <= 100) {
                        invalidSize = false;
                    }
                } catch (final NumberFormatException ex) {
                    invalidSize = true;
                    System.out.println("Invalid entry, try again");
                }
            }
            initMaze(mazeSize);
            System.out.println("\n\n");
        } else {
            System.out.println("\n\n-- OS does not support a console --\n\n");
        }
        System.out.println("-- Ending Maze Solver --");
    }

    private static void initMaze(int mazeSize) {

        maze = new Maze(mazeSize);
        System.out.println(maze.toString());
    }

}
