package ie.homelab.mazesolver.view;

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
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Maze Cell. One segment of a maze panel.
 *
 * @author derek
 */
public class MazeCell extends JPanel {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Cell size.
     */
    protected static final int CELL_SIZE = 18;

    /**
     * Border Thickness.
     */
    protected static final int BRDR_SIZE = 1;
    /**
     * Cell background colour.
     */
    private Color bgColour = Color.WHITE;

    /**
     * Dimension of Maze cell.
     */
    private Dimension dim = new Dimension(CELL_SIZE, CELL_SIZE);

    /**
     * Default constructor.
     */
    public MazeCell() {
        super();
        setSize(dim);
        setPreferredSize(dim);
        setVisible(true);
        setBackground(bgColour);
        setBorder(new LineBorder(Color.LIGHT_GRAY, BRDR_SIZE));
    }

    /**
     * Parameterised constructor.
     *
     * @param value Cell content value. i.e. 35 corresponds to ASCII '#'.
     */
    public MazeCell(int value) {
        this();
        if (value == 35) {
            bgColour = Color.BLACK;
            
        } else {
            bgColour = Color.WHITE;
        }
        setBackground(bgColour);
    }

}
