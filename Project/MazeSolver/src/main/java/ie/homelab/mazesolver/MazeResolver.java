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

/**
 * MazeResolver. Used to resolve the shortest path from start point to exit
 * point.
 *
 * @author derek
 */
public class MazeResolver {

    /**
     * Maze data object.
     */
    private static Maze maze;

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
        maze.toString();
        return output;
    }
}
