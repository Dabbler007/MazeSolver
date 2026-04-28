package ie.homelab.mazesolver;

import ie.homelab.mazesolver.model.Maze;
import ie.homelab.mazesolver.view.MazePanel;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

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
/**
 * Maze solver frame for graphical Maze Solver output.
 *
 * @author derek
 */
public class MazeSolverFrame extends javax.swing.JFrame {

    private static final Logger LOGGER = Logger.getLogger(MazeSolverFrame.class.getName());
    private static final long serialVersionUID = 1L;

    private String licenseText;

    /**
     * Creates new form MazeSolverFrame.
     */
    public MazeSolverFrame() {
        super();
        licenseText = loadLicenseText();
        initComponents();

        final SpinnerModel model =
                        new SpinnerNumberModel(Maze.DEFAULT_SIZE, Maze.MIN_SIZE, Maze.MAX_SIZE, 1);
        mazeSizeSpinner.setModel(model);

        sizeDialog.setModal(true);
        sizeDialog.setVisible(true);
        int selectedSize = (Integer) mazeSizeSpinner.getModel().getValue();       
        initMaze(selectedSize);

        MazeGenerator mazeGenerator = new MazeGenerator();
        mazeGenerator.generateMaze();
        Maze instance = Maze.getInstance();
        MazeResolver resolver = new MazeResolver();
        resolver.resolveMaze();
        
        if (LOGGER.isLoggable(Level.FINE)) {
            final StringBuilder sb = new StringBuilder();
            sb.append(instance.toString());
            sb.append("\n\n");
            sb.append(instance.toString());
            sb.append("Path: ").append(resolver.getPointDistance(Maze.getStart()));
            sb.append("\n\n");

            sb.append("-- Ending Maze Solver --\n");

            LOGGER.log(Level.FINE, sb.toString());
        }
        
        MazePanel mp = new MazePanel(true);
        displayPanel.add(mp, BorderLayout.CENTER);
        displayPanel.repaint();
    }

    /**
     * Creates new form MazeSolverFrame of specified size.
     *
     * @param mazeSize int value in the range {@link ie.homelab.mazesolver.model.Maze#MIN_SIZE} -
     *                 {@link ie.homelab.mazesolver.model.Maze#MAX_SIZE}
     */
    public MazeSolverFrame(final int mazeSize) {
        super();
        licenseText = loadLicenseText();
        initComponents();
        initMaze(mazeSize);

        Maze instance = Maze.getInstance();
        MazeGenerator mazeGenerator = new MazeGenerator();
        mazeGenerator.generateMaze();
        MazeResolver resolver = new MazeResolver();
        resolver.resolveMaze();
        
        if (LOGGER.isLoggable(Level.FINE)) {
            final StringBuilder sb = new StringBuilder();
            sb.append(instance.toString());
            sb.append("\n\n");
            sb.append(instance.toString());
            sb.append("Path: ").append(resolver.getPointDistance(Maze.getStart()));
            sb.append("\n\n");
            sb.append("-- Ending Maze Solver --\n");
            LOGGER.log(Level.FINE, sb.toString());
        }
        MazePanel mp = new MazePanel(true);
        mp.setVisible(true);
        displayPanel.add(mp, BorderLayout.CENTER);
        displayPanel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aboutDialog = new JDialog();
        aboutTitle = new JLabel();
        closeButton = new JButton();
        copyLabel = new JLabel();
        aboutScrollPane = new JScrollPane();
        licensePanel = new JTextPane();
        sizeDialog = new JDialog();
        sizeTitle = new JLabel();
        mazeSizeSpinner = new JSpinner();
        sizeButton = new JButton();
        spinnerLabel = new JLabel();
        mainPanel = new JPanel();
        scrollPane = new JScrollPane();
        displayPanel = new JPanel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        exitMenuItem = new JMenuItem();
        helpMenu = new JMenu();
        aboutMenuItem = new JMenuItem();

        aboutDialog.setTitle("About Maze Solver");
        aboutDialog.setAlwaysOnTop(true);
        aboutDialog.setLocationByPlatform(true);
        aboutDialog.setMinimumSize(new Dimension(400, 300));
        aboutDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        aboutDialog.setName("aboutDialog"); // NOI18N
        aboutDialog.setUndecorated(true);
        aboutDialog.setResizable(false);
        aboutDialog.setSize(new Dimension(400, 300));

        aboutTitle.setFont(new Font("Liberation Sans", 0, 24)); // NOI18N
        aboutTitle.setHorizontalAlignment(SwingConstants.CENTER);
        aboutTitle.setText("Maze Solver");

        closeButton.setText("Close");
        closeButton.setToolTipText("Close about dialog");
        closeButton.addActionListener(this::closeButtonActionPerformed);

        copyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        copyLabel.setText("(C) 2025-2026 Homelab");
        copyLabel.setToolTipText("");

        licensePanel.setEditable(false);
        licensePanel.setFont(new Font("Liberation Sans", 0, 12)); // NOI18N
        licensePanel.setText(licenseText);
        aboutScrollPane.setViewportView(licensePanel);

        GroupLayout aboutDialogLayout = new GroupLayout(aboutDialog.getContentPane());
        aboutDialog.getContentPane().setLayout(aboutDialogLayout);
        aboutDialogLayout.setHorizontalGroup(aboutDialogLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(aboutDialogLayout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(closeButton)
                .addContainerGap(171, Short.MAX_VALUE))
            .addComponent(aboutTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
                 GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(aboutDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aboutDialogLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(aboutScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(copyLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        aboutDialogLayout.setVerticalGroup(aboutDialogLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(aboutDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aboutTitle)
                .addGap(18, 18, 18)
                .addComponent(copyLabel)
                .addGap(12, 12, 12)
                .addComponent(aboutScrollPane, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(closeButton)
                .addContainerGap())
        );

        sizeDialog.setAlwaysOnTop(true);
        sizeDialog.setLocationByPlatform(true);
        sizeDialog.setMinimumSize(new Dimension(400, 300));
        sizeDialog.setModal(true);
        sizeDialog.setResizable(false);
        sizeDialog.setSize(new Dimension(400, 300));

        sizeTitle.setText("Select a maze size");

        mazeSizeSpinner.setToolTipText("Select a maze size.");

        sizeButton.setText("Ok");
        sizeButton.addActionListener(this::sizeButtonActionPerformed);

        spinnerLabel.setFont(new Font("Liberation Sans", 0, 24)); // NOI18N
        spinnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        spinnerLabel.setLabelFor(mazeSizeSpinner);
        spinnerLabel.setText("Maze Solver");

        GroupLayout sizeDialogLayout = new GroupLayout(sizeDialog.getContentPane());
        sizeDialog.getContentPane().setLayout(sizeDialogLayout);
        sizeDialogLayout.setHorizontalGroup(sizeDialogLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(sizeDialogLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(sizeDialogLayout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(sizeButton)
                    .addGroup(sizeDialogLayout.createSequentialGroup()
                        .addComponent(sizeTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mazeSizeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                             GroupLayout.PREFERRED_SIZE))).addContainerGap(161, Short.MAX_VALUE))
                .addGroup(sizeDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spinnerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        sizeDialogLayout.setVerticalGroup(sizeDialogLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(sizeDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spinnerLabel)
                .addGap(61, 61, 61)
                .addGroup(sizeDialogLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(sizeTitle)
                    .addComponent(mazeSizeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                         GroupLayout.PREFERRED_SIZE)).addGap(42, 42, 42)
                .addComponent(sizeButton)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maze Solver");
        setMinimumSize(new Dimension(400, 400));
        setSize(new Dimension(400, 800));

        mainPanel.setMinimumSize(new Dimension(200, 200));
        mainPanel.setPreferredSize(new Dimension(520, 520));

        scrollPane.setViewportBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        scrollPane.setDoubleBuffered(true);
        scrollPane.setMinimumSize(new Dimension(200, 200));
        scrollPane.setPreferredSize(new Dimension(530, 518));
        scrollPane.setViewportView(displayPanel);

        displayPanel.setMinimumSize(new Dimension(200, 200));
        displayPanel.setName(""); // NOI18N
        displayPanel.setPreferredSize(new Dimension(512, 512));
        displayPanel.setLayout(new BorderLayout());
        scrollPane.setViewportView(displayPanel);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 553, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(this::exitMenuItemActionPerformed);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(this::aboutMenuItemActionPerformed);
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * Handler for about dialog close button click.
     *
     * @param evt Action event Object.
     */
    private void closeButtonActionPerformed(ActionEvent evt) { // GEN-FIRST:event_closeButtonActionPerformed
        if (evt.getActionCommand().equals("Close")) {
            aboutDialog.setVisible(false);
            aboutDialog.setModal(false);
        }
    } // GEN-LAST:event_closeButtonActionPerformed

    /**
     * Handler for about menu click.
     *
     * @param evt Action event Object.
     */
    private void aboutMenuItemActionPerformed(ActionEvent evt) { // GEN-FIRST:event_aboutMenuItemActionPerformed
        if (evt.getActionCommand().equals("About")) {
            aboutDialog.setModal(true);
            aboutDialog.setVisible(true);
        }
    } // GEN-LAST:event_aboutMenuItemActionPerformed

    /**
     * Handler for Ok button (Size).
     *
     * @param evt Action event Object.
     */
    private void sizeButtonActionPerformed(ActionEvent evt) { // GEN-FIRST:event_sizeButtonActionPerformed
        if (evt.getActionCommand().equals("Ok")) {
            sizeDialog.setVisible(false);
            sizeDialog.setModal(false);
        }
    } // GEN-LAST:event_sizeButtonActionPerformed

    private void exitMenuItemActionPerformed(ActionEvent evt) { // GEN-FIRST:event_exitMenuItemActionPerformed
        if (evt.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    } // GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * MazeSolver frame main method.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and
         * feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        // </editor-fold>

        if (args.length > 0) {
            // validate args input
            int tempSize;
            boolean invalidSize = validateMazeSize(args[0]);
            if (invalidSize) {
                
                // Just run with the default value
                EventQueue.invokeLater(
                                () -> new MazeSolverFrame(Maze.DEFAULT_SIZE).setVisible(true));
            } else {
                // Program argument supplied, set maze size
                try {
                    tempSize = Integer.parseInt(args[0]);

                } catch (final NumberFormatException ex) {

                    // Shouldn't happen as input already validated.
                    tempSize = Maze.DEFAULT_SIZE;
                }
                // Next method Will only accept final values :)
                final int standIn = tempSize;
                EventQueue.invokeLater(() -> new MazeSolverFrame(standIn).setVisible(true));
            }
        } else {
            // No arguments supplied

            // Create and display the form
            EventQueue.invokeLater(() -> new MazeSolverFrame().setVisible(true));
        }
    }

    /**
     * Initialise maze.
     *
     * @param mazeSize int value in the range MIN_SIZE - MAX_SIZE
     */
    protected static void initMaze(final int mazeSize) {
        Maze.setMazeSize(mazeSize);
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
                output = true;
            }
        } catch (NumberFormatException _) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, "Invalid number format for maze size: {0}", rawSize);
            }

        }
        return output;
    } 

    /**
     * Load license text from properties file.
     *
     * @return License text from properties file.
     */
    private String loadLicenseText() {
        String output = "Error loading License Text";
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propertiesPath = rootPath + "ie/homelab/mazesolver/license.properties";
        String fs = System.getProperty("file.separator");
        String os = System.getProperty("os.name");
        if (!fs.equals("/") && os.contains("Windows")) {
            // Snip off leading '/'
            propertiesPath = propertiesPath.substring(1, propertiesPath.length());
            propertiesPath = propertiesPath.replace("/", Matcher.quoteReplacement(fs));
        }
        Properties licenseProperties = new Properties();
        try (FileInputStream fstream = new FileInputStream(propertiesPath)) {   
            licenseProperties.load(fstream);
            licenseText = licenseProperties.getProperty("licenseText");
            output = licenseText;
        } catch (final IOException ex) {
            System.getLogger(MazeSolverFrame.class.getName()).log(System.Logger.Level.ERROR,
                            ex.getMessage(), ex);
        }
        return output;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JDialog aboutDialog;
    private JMenuItem aboutMenuItem;
    private JScrollPane aboutScrollPane;
    private JLabel aboutTitle;
    private JButton closeButton;
    private JLabel copyLabel;
    private JPanel displayPanel;
    private JMenuItem exitMenuItem;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JTextPane licensePanel;
    private JPanel mainPanel;
    private JSpinner mazeSizeSpinner;
    private JMenuBar menuBar;
    private JScrollPane scrollPane;
    private JButton sizeButton;
    private JDialog sizeDialog;
    private JLabel sizeTitle;
    private JLabel spinnerLabel;
    // End of variables declaration//GEN-END:variables
}
