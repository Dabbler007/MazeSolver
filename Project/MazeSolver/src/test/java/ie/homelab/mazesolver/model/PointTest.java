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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author derek
 */
public class PointTest {

    public PointTest() {
        // Default constructor
    }

    @BeforeClass
    public static void setUpClass() {
        // Set up resources before any tests are run
    }

    @AfterClass
    public static void tearDownClass() {
        // Tear down resources after all tests are run
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getX method, of class Point.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Point instance = new Point(0, 0);
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);

        instance = new Point(100, 0);
        expResult = 100;
        result = instance.getX();
        assertEquals(expResult, result);

    }

    /**
     * Test of getY method, of class Point.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Point instance = new Point(0, 0);
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);

        instance = new Point(0, 100);
        expResult = 100;
        result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Point.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Point instance = new Point(0, 0);
        int expResult = 2645;
        int result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new Point(100, 0);
        expResult = 4945;
        result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new Point(0, 100);
        expResult = 2745;
        result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Point.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Point(0, 0);
        Point instance = new Point(0, 0);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Point(100, 0);
        instance = new Point(100, 0);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Point(0, 100);
        instance = new Point(0, 100);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Point(0, 0);
        instance = new Point(10, 10);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new Point(10, 0);
        instance = new Point(10, 10);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);        

        obj = new Point(27, 43);
        instance = (Point) obj;
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Point(88, 21);
        instance = new Point(14, 11);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = null;
        instance = new Point(14, 11);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = "";
        instance = new Point(14, 11);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);


    }

    /**
     * Test of toString method, of class Point.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Point instance = new Point(0, 0);
        String expResult = "[0,0]";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of x method, of class Point.
     */
    @Test
    public void testX() {
        System.out.println("x");
        Point instance = new Point(75, 35);
        int expResult = 75;
        int result = instance.x();
        assertEquals(expResult, result);
    }

    /**
     * Test of y method, of class Point.
     */
    @Test
    public void testY() {
        System.out.println("y");
        Point instance = new Point(75, 35);
        int expResult = 35;
        int result = instance.y();
        assertEquals(expResult, result);
    }

}