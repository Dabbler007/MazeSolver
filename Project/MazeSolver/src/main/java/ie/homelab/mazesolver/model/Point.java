/*
 * Copyright (C) 2026 derek
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

package ie.homelab.mazesolver.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Point record.
 *
 * <p>Values x and y are in range MIN_SIZE - MAX_SIZE inclusive.
 *
 * @param x int value representing a point on the x axis.
 * @param y int value representing a point on the y axis.
 * 
 * @author derek
 */
public record Point(int x, int y) implements Serializable {

    /**
     * Point record constraints.
     */
    public Point {
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);
    }

    /**
     * Getter for x value.
     * 
     * @return x value.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y value.
     * 
     * @return y value.
     */
    public int getY() {
        return y;
    }

    @Override 
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.x;
        hash = 23 * hash + this.y;
        return hash;
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

        final Point other = (Point) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }
    
    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
