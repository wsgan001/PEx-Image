/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 * 
 @inproceedings{paulovich2007pex,
 author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
 Minghim},
 title = {The Projection Explorer: A Flexible Tool for Projection-based 
 Multidimensional Visualization},
 booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
 Computer Graphics and Image Processing (SIBGRAPI 2007)},
 year = {2007},
 isbn = {0-7695-2996-8},
 pages = {27--34},
 doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
 publisher = {IEEE Computer Society},
 address = {Washington, DC, USA},
 }
 * 
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Roberto Pinho <robertopinho@yahoo.com.br>, 
 *                 Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */
package visualizer.view;

import br.com.explore.explorertree.util.Tooltip;
import br.com.explorer.explorertree.ExplorerTreeController;
import br.com.explorer.explorertree.ExplorerTreeNode;
import br.com.methods.overlap.rwordle.RWordleC;
import br.com.methods.utils.OverlapRect;
import br.com.test.ui.OverlapView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import net.sf.epsgraphics.ColorMode;
import net.sf.epsgraphics.EpsGraphics;
import visualizer.forcelayout.ForceData;
import visualizer.forcelayout.ForceDirectLayout;
import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Connectivity;
import visualizer.graph.Representative;
import visualizer.graph.XMLGraphWriter;
import visualizer.graph.Scalar;
import visualizer.graph.Vertex;
import visualizer.graph.coodination.Coordination;
import visualizer.graph.coodination.Mapping;
import visualizer.view.legend.Legend;
import visualizer.view.legend.LegendView;
import visualizer.graph.listeners.VertexSelectionFactory;
import visualizer.util.PExConstants;
import visualizer.util.OpenDialog;
import visualizer.util.SaveDialog;
import visualizer.util.filefilter.XMLFilter;
import visualizer.view.color.ColorScalePanel;
import visualizer.view.color.ColorTable;
import visualizer.topic.Topic;
import visualizer.util.Util;
import visualizer.util.filefilter.LEGFilter;

/**
 *
 * @author Fernando Vieira Paulovich, Roberto Pinho
 */
public class ProjectionViewer extends Viewer {

    private GroupAnalysisView gView = null;
    private ClusterAnalysisView cView = null;
    /**
     * Creates new form GraphViewer
     *
     * @param pexview
     */
    public ProjectionViewer(ProjectionExplorerView pexview) {
        super(pexview);

        this.view = new ViewPanel(this.pexview);
        this.coord = new Coordination(pexview.getCoordinator(), this);

        initComponents();
    }

    public ClusterAnalysisView getClusterAnalysisView() {
        return ClusterAnalysisView.getInstance();
    }
    
    public GroupAnalysisView getGroupAnalysisView() {   
        return GroupAnalysisView.getInstance();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mapSplitPane = new javax.swing.JSplitPane();
        mapTabbedPane = new javax.swing.JTabbedPane();
        projectionPanel = new javax.swing.JPanel();
        scrollPaneGraph = new javax.swing.JScrollPane(this.view);
        titlePanel = new javax.swing.JPanel();
        titleTextField = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        titleComboBox = new JExtendedComboBox(this.titlesComboModel);
        legendButton = new javax.swing.JButton();
        reportScrollPane = new javax.swing.JScrollPane();
        reportPanel = new ReportView();
        comboPanel = new javax.swing.JPanel();
        scalarCombo = new JExtendedComboBox(this.scalarComboModel);
        scalarToggleButton = new javax.swing.JToggleButton();
        edgesCoordToggleButton = new javax.swing.JToggleButton();
        edgesCoordComboBox = new JExtendedComboBox(this.edgesComboModel);
        splitScalars = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        mapSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        mapSplitPane.setOneTouchExpandable(true);

        projectionPanel.setLayout(new java.awt.BorderLayout());
        projectionPanel.add(scrollPaneGraph, java.awt.BorderLayout.CENTER);

        titlePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        titlePanel.setLayout(new java.awt.BorderLayout());

        titleTextField.setEditable(false);
        titlePanel.add(titleTextField, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        titleComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titleComboBoxMouseClicked(evt);
            }
        });
        titleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleComboBoxActionPerformed(evt);
            }
        });
        buttonPanel.add(titleComboBox);

        legendButton.setText("Legend"); // NOI18N
        legendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(legendButton);

        titlePanel.add(buttonPanel, java.awt.BorderLayout.EAST);

        projectionPanel.add(titlePanel, java.awt.BorderLayout.PAGE_START);

        mapTabbedPane.addTab("Projection", projectionPanel);

        reportScrollPane.setViewportView(reportPanel);

        mapTabbedPane.addTab("Report", reportScrollPane);

        mapSplitPane.setRightComponent(mapTabbedPane);

        comboPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        comboPanel.setLayout(new java.awt.GridBagLayout());

        scalarCombo.setMaximumSize(new java.awt.Dimension(85, 27));
        scalarCombo.setMinimumSize(new java.awt.Dimension(85, 27));
        scalarCombo.setPreferredSize(new java.awt.Dimension(85, 27));
        scalarCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scalarComboActionPerformed(evt);
            }
        });
        scalarCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scalarComboMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 3, 3);
        comboPanel.add(scalarCombo, gridBagConstraints);

        scalarToggleButton.setText("Color"); // NOI18N
        scalarToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scalarToggleButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 1);
        comboPanel.add(scalarToggleButton, gridBagConstraints);

        edgesCoordToggleButton.setText("Edges"); // NOI18N
        edgesCoordToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edgesCoordToggleButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 1);
        comboPanel.add(edgesCoordToggleButton, gridBagConstraints);

        edgesCoordComboBox.setMaximumSize(new java.awt.Dimension(85, 27));
        edgesCoordComboBox.setMinimumSize(new java.awt.Dimension(85, 27));
        edgesCoordComboBox.setPreferredSize(new java.awt.Dimension(85, 27));
        edgesCoordComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edgesCoordComboBoxMouseClicked(evt);
            }
        });
        edgesCoordComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edgesCoordComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 3, 3);
        comboPanel.add(edgesCoordComboBox, gridBagConstraints);

        splitScalars.setText("Split"); // NOI18N
        splitScalars.setToolTipText("This may take some minutes"); // NOI18N
        splitScalars.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitScalarsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        comboPanel.add(splitScalars, gridBagConstraints);

        mapSplitPane.setLeftComponent(comboPanel);

        getContentPane().add(mapSplitPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        this.pexview.setFocusedJInternalFrame(this);
    }//GEN-LAST:event_formInternalFrameActivated

    private void edgesCoordComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edgesCoordComboBoxActionPerformed
        if (this.edgesCoordToggleButton.isSelected()) {
            Mapping mapping = (Mapping) this.edgesCoordComboBox.getSelectedItem();

            if (mapping != null && mapping.getName().equals("topic")) {
                if (OpenDialog.checkCorpus(this.graph, this)) {
                    this.coord.changeMapping(mapping);
                } else {
                    this.edgesCoordComboBox.setSelectedItem(Mapping.OFF);
                }
            } else {
                this.coord.changeMapping(mapping);
            }
        } else {
            this.updateImage();
        }
    }//GEN-LAST:event_edgesCoordComboBoxActionPerformed

    private void edgesCoordToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edgesCoordToggleButtonActionPerformed
        if (this.edgesCoordToggleButton.isSelected()) {
            this.prevConnectivity = (Connectivity) this.edgesCoordComboBox.getSelectedItem();
            this.edgesCoordComboBox.setModel(this.coordComboModel);
            this.edgesCoordToggleButton.setText("Coordination");
            this.edgesCoordComboBox.setSelectedItem(this.prevCoord);
        } else {
            this.prevCoord = (Mapping) this.edgesCoordComboBox.getSelectedItem();
            this.edgesCoordComboBox.setModel(this.edgesComboModel);
            this.edgesCoordToggleButton.setText("Edges");

            if (this.prevConnectivity != null) {
                this.edgesCoordComboBox.setSelectedItem(this.prevConnectivity);
            } else {
                this.edgesCoordComboBox.setSelectedIndex(0);
            }
        }

    }//GEN-LAST:event_edgesCoordToggleButtonActionPerformed

    private void scalarToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scalarToggleButtonActionPerformed
        if (this.scalarToggleButton.isSelected()) {
            this.prevColorScalar = (Scalar) this.scalarCombo.getSelectedItem();
            this.scalarToggleButton.setText("Size");

            if (this.prevSizeScalar != null) {
                this.scalarCombo.setSelectedItem(this.prevSizeScalar);
            } else {
                this.scalarCombo.setSelectedIndex(0);
            }
        } else {
            this.prevSizeScalar = (Scalar) this.scalarCombo.getSelectedItem();
            this.scalarToggleButton.setText("Color");

            if (this.prevColorScalar != null) {
                this.scalarCombo.setSelectedItem(this.prevColorScalar);
            } else {
                this.scalarCombo.setSelectedIndex(0);
            }
        }

        this.scalarComboActionPerformed(null);
    }//GEN-LAST:event_scalarToggleButtonActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        this.saveOnClosing();
        this.pexview.getCoordinator().removeCoordination(this);
        this.pexview.setFocusedJInternalFrame(null);
    }//GEN-LAST:event_formInternalFrameClosing

    private void scalarComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scalarComboActionPerformed
        Scalar scalar = (Scalar) this.scalarCombo.getSelectedItem();

        if (scalar != null) {
            if (this.scalarToggleButton.isSelected()) {
                this.view.resizeAs(scalar);
            } else {
                this.view.colorAs(scalar);
            }
        }
    }//GEN-LAST:event_scalarComboActionPerformed

    private void scalarComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scalarComboMouseClicked
        if (evt.getClickCount() == 2) {
            Scalar scalar = (Scalar) this.scalarCombo.getSelectedItem();

            if (!scalar.getName().equals(PExConstants.DOTS)) {
                this.scalarComboModel.removeElement(scalar);
                this.scalarCombo.setSelectedIndex(0);
                this.graph.removeScalar(scalar);
                this.setGraphChanged(true);
            }
        }
    }//GEN-LAST:event_scalarComboMouseClicked

    private void edgesCoordComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edgesCoordComboBoxMouseClicked
        if (evt.getClickCount() == 2 && !this.edgesCoordToggleButton.isSelected()) {
            Connectivity con = (Connectivity) this.edgesCoordComboBox.getSelectedItem();

            //keep at least one connectivity
            if (this.edgesCoordComboBox.getItemCount() > 1) {
                this.edgesComboModel.removeElement(con);
                this.edgesCoordComboBox.setSelectedIndex(0);
                this.graph.removeConnectivity(con);
                this.setGraphChanged(true);
            }
        }
    }//GEN-LAST:event_edgesCoordComboBoxMouseClicked

    private void titleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleComboBoxActionPerformed
        String title_aux = (String) ((JComboBox) evt.getSource()).getSelectedItem();

        if (title != null) {
            if (this.graph != null) {
                this.graph.changeTitle(title_aux);
            }

            this.pexview.refreshLists();
            this.updateImage();
        }
    }//GEN-LAST:event_titleComboBoxActionPerformed

    private void titleComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleComboBoxMouseClicked
        if (evt.getClickCount() == 2) {
            String title_aux = (String) this.titleComboBox.getSelectedItem();

            if (this.titleComboBox.getItemCount() > 1) {
                this.titlesComboModel.removeElement(title_aux);
                this.titleComboBox.setSelectedIndex(0);
                this.graph.removeTitle(title_aux);
                this.setGraphChanged(true);
            }
        }
    }//GEN-LAST:event_titleComboBoxMouseClicked

    private void legendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendButtonActionPerformed
        if (this.legendview == null) {
            int result = OpenDialog.showOpenDialog(new LEGFilter(), this);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String filename = OpenDialog.getFilename();

                    Legend legend = Util.readLegend(view.colorTable, filename);
                    this.legendview = new LegendView(pexview, legend);
                    this.legendview.setLocationRelativeTo(this);
                } catch (IOException ex) {
                    Logger.getLogger(ProjectionViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        this.legendview.setVisible(true);
    }//GEN-LAST:event_legendButtonActionPerformed

private void splitScalarsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitScalarsActionPerformed
    ArrayList<Float> SplittedScalars = new ArrayList<Float>();
    for (Vertex vertex : graph.getVertex()) {
        if (!SplittedScalars.contains(vertex.getScalar(graph.getScalarByName(this.scalarCombo.getSelectedItem().toString())))) {
            SplittedScalars.add(vertex.getScalar(graph.getScalarByName(this.scalarCombo.getSelectedItem().toString())));
        }
    }

    if (SplittedScalars.size() > 2) {
        for (int i = 0; i < SplittedScalars.size(); i++) {
            graph.addScalar(this.scalarCombo.getSelectedItem().toString() + "_" + i);
            for (Vertex vertex : graph.getVertex()) {
                if (vertex.getScalar(graph.getScalarByName(this.scalarCombo.getSelectedItem().toString())) == SplittedScalars.get(i)) {
                    vertex.setScalar(graph.getScalarByName(this.scalarCombo.getSelectedItem().toString() + "_" + i), 1);
                } else {
                    vertex.setScalar(graph.getScalarByName(this.scalarCombo.getSelectedItem().toString() + "_" + i), 0);
                }
            }
        }
    }
    this.updateScalars(null);
}//GEN-LAST:event_splitScalarsActionPerformed

    @Override
    public void markNeighbors(Vertex vertex) {
        this.view.markNeighbors(vertex);
    }

    @Override
    public int getNeighborhoodDepth() {
        return neighborhoodDepth;
    }

    @Override
    public void setNeighborhoodDepth(int depth) {
        this.neighborhoodDepth = depth;
    }

    @Override
    public Scalar getCurrentScalar() {
        return (Scalar) this.scalarCombo.getSelectedItem();
    }

    @Override
    public String getCurrentTitle() {
        return (String) this.titleComboBox.getSelectedItem();
    }

    @Override
    public Connectivity getCurrentConnectivity() {
        if (this.edgesCoordToggleButton.isSelected()) {
            return this.prevConnectivity;
        } else {
            return (Connectivity) this.edgesCoordComboBox.getSelectedItem();
        }
    }

    @Override
    public Graph getGraph() {
        return this.graph;
    }

    @Override
    public void cleanSelection(boolean cleanVertexLabels) {
        if (this.view != null) {
            this.view.cleanMarkedVertices(cleanVertexLabels);
        }
    }

    @Override
    public void selectVertices(ArrayList<Vertex> vertices) {
        if (this.view != null) {
            this.view.markVertices(vertices);
        }
    }

    @Override
    public void addTopic(Topic topic) {
        if (this.view != null) {
            this.view.getTopics().add(topic);
        }
    }

    @Override
    public ColorTable getColorTable() {
        if (this.view != null) {
            return this.view.getColorTable();
        }

        return null;
    }

    @Override
    public void updateImage() {
        if (this.view != null) {

            this.view.cleanImage();
            this.view.repaint();

        }
    }

    @Override
    public void colorAs(Scalar scalar) {
        if (this.view != null) {
            this.view.colorAs(scalar);
        }
    }

    @Override
    public Vertex getSelectedVertex() {
        if (this.view != null) {
            return this.view.getMarkedVertex();
        }

        return null;
    }

    @Override
    public Font getViewerFont() {
        if (this.view != null) {
            return this.view.getFont();
        }

        return null;
    }

    @Override
    public void setViewerFont(Font font) {
        if (this.view != null) {
            this.view.setFont(font);
        }
    }

    @Override
    public void setViewerBackground(Color bg) {
        if (this.view != null) {
            this.view.setBackground(bg);
        }
    }

    @Override
    public void deleteSelectedVertices() {
        if (this.view != null) {
            this.view.deleteSelectedVertex();
        }
    }

    @Override
    public void saveToPngImageFile(String filename) throws IOException {
        if (this.view != null) {
            this.view.saveToPngImageFile(filename);
        }
    }

    @Override
    public void saveToEpsImageFile(String filename) throws IOException {
        if (this.view != null) {
            this.view.saveToEpsImageFile(filename);
        }
    }

    @Override
    public ArrayList<Vertex> getSelectedVertex(Point localSource, Point localTarget) {
        if (this.view != null) {
            return this.view.getSelectedVertex(localSource, localTarget);
        }

        return null;
    }

    @Override
    public void zoomIn() {
        this.zoom(1.1f);
    }

    @Override
    public void zoomOut() {
        this.zoom(0.9091f);
    }

    @Override
    public void cleanTopics() {
        this.view.getTopics().clear();
        this.view.repaint();
    }

    @Override
    public void updateScalars(Scalar scalar) {
        this.scalarComboModel.removeAllElements();
        for (Scalar s : this.graph.getScalars()) {
            this.scalarComboModel.addElement(s);
        }

        if (scalar != null) {
            this.scalarCombo.setSelectedItem(scalar);
        }

        this.setGraphChanged(true);
    }

    @Override
    public void updateConnectivities(Connectivity connectivity) {
        this.edgesComboModel.removeAllElements();
        for (Connectivity con : this.graph.getConnectivities()) {
            this.edgesComboModel.addElement(con);
        }

        if (!this.edgesCoordToggleButton.isSelected()) {
            if (connectivity != null) {
                this.edgesCoordComboBox.setSelectedItem(connectivity);
                this.prevConnectivity = connectivity;
            }
        }

        this.setGraphChanged(true);
    }

    @Override
    public void updateCoordinations(Mapping mapping) {
        this.coordComboModel.removeAllElements();
        this.coordComboModel.addElement(Mapping.OFF);

        for (Mapping m : this.coord.getMappings()) {
            if (this.coordComboModel.getIndexOf(m) < 0) {
                this.coordComboModel.addElement(m);
            }
        }

        if (this.edgesCoordToggleButton.isSelected()) {
            if (mapping != null) {
                this.edgesCoordComboBox.setSelectedItem(mapping);
                this.prevCoord = mapping;
            }
        }
    }

    @Override
    public void updateTitles(String name) {
        this.titlesComboModel.removeAllElements();
        for (String t : this.graph.getTitles()) {
            this.titlesComboModel.addElement(t);
        }

        if (name != null) {
            this.titleComboBox.setSelectedItem(name);
        }

        this.setGraphChanged(true);
    }

    public void setGraph(Graph graph) {
        if (graph != null) {
            this.graph = graph;

            this.view.setGraph(graph);

            this.updateScalars(null);
            this.updateConnectivities(null);
            this.updateTitles(null);

            //set the labels names
            if (this.graph.getDescription().trim().length() > 0) {
                DescriptionView.getInstance(this.getProjectionExplorerView()).
                        display(this.graph.getDescription());
            }

            ((ReportView) this.reportPanel).reset(this.graph.getProjectionData());
        }
    }

    @Override
    public boolean runForce() {
        if (this.graph != null) {
            if (start) {
                this.cleanTopics();

                for (Vertex v : this.graph.getVertex()) {
                    if (v.fdata == null) {
                        v.fdata = new ForceData();
                    }

                }

                force = new ForceDirectLayout(this.graph, this);
                force.start(this.getCurrentConnectivity());
                this.start = false;
            } else {
                this.force.stop();
                this.start = true;
            }

        }

        this.setGraphChanged(true);

        return this.start;
    }

    @Override
    public void setTitle(String title) {
        if (!title.startsWith("[")) {
            title = "[" + this.id + "]: " + title;
        }

        super.setTitle(title);
    }

    @Override
    public int saveOnClosing() {
        if (this.graphChanged) {
            int result1 = javax.swing.JOptionPane.showConfirmDialog(this,
                    "Would like to save the current projection?", "Save",
                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);

            if (result1 == javax.swing.JOptionPane.YES_OPTION) {
                String filename = this.graph.getProjectionData().getSourceFile();

                int result2 = SaveDialog.showSaveDialog(new XMLFilter(), this, filename);

                if (result2 == JFileChooser.APPROVE_OPTION) {
                    filename = SaveDialog.getFilename();

                    try {
                        XMLGraphWriter.save(this.graph, this.graph.getDescription(), filename);
                        this.setTitle(filename);
                        this.setGraphChanged(false);
                        return javax.swing.JOptionPane.OK_OPTION;
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(),
                                "Problems with the file", JOptionPane.ERROR_MESSAGE);
                    }

                    this.dispose();
                }

            } else if (result1 == javax.swing.JOptionPane.NO_OPTION) {
                this.dispose();
                return javax.swing.JOptionPane.NO_OPTION;
            } else {
                return javax.swing.JOptionPane.CANCEL_OPTION;
            }

        } else {
            this.dispose();
        }

        return javax.swing.JOptionPane.NO_OPTION;
    }

    @Override
    public void setFont(java.awt.Font font) {
        this.font = font;
        if (this.view != null) {
            this.view.repaint();
        }
    }

    @Override
    public java.awt.Font getFont() {
        return this.font;
    }

    private void zoom(float rate) {
        if (this.graph != null) {

            ArrayList<Vertex> vertex = this.graph.getVertex();
            float maxX = vertex.get(0).getX();
            float minX = vertex.get(0).getX();
            float maxY = vertex.get(0).getY();
            float minY = vertex.get(0).getY();

            //Encontra o maior e menor valores para X e Y
            for (Vertex v : vertex) {
                if (maxX < v.getX()) {
                    maxX = v.getX();
                } else if (minX > v.getX()) {
                    minX = v.getX();
                }

                if (maxY < v.getY()) {
                    maxY = v.getY();
                } else if (minY > v.getY()) {
                    minY = v.getY();
                }

            }

            float endX = maxX * rate;
            float endY = maxY * rate;

            //Normalizo
            for (Vertex v : vertex) {
                if (maxX != minX) {
                    v.setX((((v.getX() - minX) / (maxX - minX)) * (endX - minX)) + minX);
                } else {
                    v.setX(minX);
                }

                if (maxY != minY) {
                    v.setY(((((v.getY() - minY) / (maxY - minY)) * (endY - minY)) + minY));
                } else {
                    v.setY(minY);
                }

            }

            //Change the size of the panel according to the graph
            this.view.setPreferredSize(new Dimension(this.graph.getSize().width * 2,
                    this.graph.getSize().height * 2));
            this.view.setSize(new Dimension(this.graph.getSize().width * 2,
                    this.graph.getSize().height * 2));

            for (Topic topic : this.view.getTopics()) {
                topic.getRectangle().x *= rate;
                topic.getRectangle().y *= rate;
                topic.getRectangle().height *= rate;
                topic.getRectangle().width *= rate;
            }

            this.updateImage();
        }
    }
    
    public void setController(ExplorerTreeController controller) {
        view.controller = controller;        
    }
    
    public void setPoints(Point2D.Double[] points) {
        view.points = points;
    }
    
    public class ViewPanel extends JPanel {
        
        /**
         ** 
         * Parameters for multilevel approach
         **/
        private boolean semaphore = false;
        private int parentMoving = -1;

        private Timer timer = null;
        private Timer timerTooltip = null;

        private int representativePolygon = -1;

        private Point2D.Double lastClicked = null;

        private ExplorerTreeController controller;
        private Point2D.Double[] points;

        private List<Integer> movingIndexes = new ArrayList<>();
        private List<Point2D.Double> toDraw = new ArrayList<>();
        private Tooltip tooltip = null;

        private List<List<Integer>> currentCluster = null;

        private Polygon[] diagrams = null;
        private Polygon[] intersects = null;

        private List<Integer> nearest = null;
        private boolean hideShowNumbers = false;
        private int[] selectedRepresentatives = null;
        
        /**
         **** **** **** **** **** **** **** **** **** **** 
         **** **** **** **** **** **** **** **** **** **** 
         */
        

        public ViewPanel(ProjectionExplorerView pexview) {
            this.controller = null;
            this.points = null;
            
            
            this.pexview = pexview;            
            this.colorTable = new ColorTable();
            this.csp = new ColorScalePanel(ProjectionViewer.this);
            this.csp.setColorTable(this.colorTable);
            this.csp.setPreferredSize(new Dimension(200, 12));
            // this.add(this.csp);

            this.setBackground(java.awt.Color.WHITE);
            this.addMouseMotionListener(new MouseMotionListener());
            this.addMouseListener(new MouseClickedListener());            
            this.addMouseWheelListener(new MouseAdapter() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    if( semaphore )
                        return;                        

                    int notches = e.getWheelRotation();                    
                    if( controller != null && controller.representative() != null && controller.nearest() != null ) {
                        int index = controller.indexRepresentative(e.getX(), e.getY());

                        if( index != -1 ) {  
                            ExplorerTreeNode node = controller.getNode(index);                            
                            if( notches > 0 && node.parent() != null )
                                agglomerateAnimation(index, node);
                            else if( notches < 0 && !node.children().isEmpty() )
                                expandAnimation(index, e);
                            else if( notches < 0 && node.children().isEmpty() ) {
                                semaphore = false;                                
                                //removeSubsetOverlap(controller.nearest().get(index), index);
                                cleanImage();
                                repaint();
                            }                                
                        } 
                    }
                }
            });
            

            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            setFocusable(true);
            requestFocusInWindow();
        }

        @Override
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

            if (graph != null && this.imageBuffer == null) {
                setGraph(graph);
             //adjustPanel();

                this.imageBuffer = new BufferedImage(graph.getSize().width + 300,
                        graph.getSize().height + 300, BufferedImage.TYPE_INT_RGB);
                java.awt.Graphics2D g2Buffer = this.imageBuffer.createGraphics();
                g2Buffer.setColor(this.getBackground());

                g2Buffer.fillRect(0, 0, graph.getSize().width + 300, graph.getSize().height + 300);

                if (highQualityRender) {
                    g2Buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                } else {
                    g2Buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                }
                
                if( controller == null ) {

                    graph.draw(getCurrentConnectivity(), g2Buffer);

                    if (board) {
                        g2Buffer.setColor(Color.GRAY);
                        boardPolygons.stream().forEach((p) -> {
                            g2Buffer.drawPolygon(p);
                        });
                    }
                
                } else { 
                    if( selectedRepresentatives != null || controller.representative() != null ) {

                        if( controller.nearest() != null ) {
                            
                            graph.draw(controller, representativePolygon, movingIndexes, parentMoving, g2Buffer);

                        } else {                            
                            
                            graph.draw(selectedRepresentatives, g2Buffer);
                            
                        }

                        if( !toDraw.isEmpty() ) {
                            int j = movingIndexes.size()-1;
                            for( int i = toDraw.size()-1; i >= (toDraw.size()-movingIndexes.size()); --i ) {
                                Point2D.Double p = toDraw.get(i);
                                g2Buffer.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
                                int index = movingIndexes.get(j--);
                                int size = controller.sizeRepresentative(controller.nearest().get(index).size());
                                g2Buffer.setColor(Color.RED);
                                g2Buffer.fillOval((int)p.x, (int)p.y, size, size);
                                g2Buffer.setColor(Color.BLACK);
                                g2Buffer.drawOval((int)p.x, (int)p.y, size, size);
                            }
                        }


                        drawScale(g2Buffer);

                        if( tooltip != null ) {
                            tooltip.draw(g2Buffer);
                        }
                    
                    }
                }
                
                g2Buffer.dispose();
            }

            if (this.imageBuffer != null) {
                g2.drawImage(this.imageBuffer, 0, 0, null);
            }

            this.topics.forEach((topic) -> {
                topic.drawTopic(g2, getFont(), false);
            });
            
          
            
            //Draw he rectangle to select the points
            if (this.source != null && this.target != null) {
                int x = this.source.x;
                int width = width = this.target.x - this.source.x;

                int y = this.source.y;
                int height = this.target.y - this.source.y;

                if (this.source.x > this.target.x) {
                    x = this.target.x;
                    width = this.source.x - this.target.x;
                }

                if (this.source.y > this.target.y) {
                    y = this.target.y;
                    height = this.source.y - this.target.y;
                }
                g2.setColor(this.color);
                g2.drawRect(x, y, width, height);

                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.45f));
                g2.setPaint(this.color);
                g2.fill(new java.awt.Rectangle(x, y, width, height));
            } else if (this.selectedTopic != null) {
                this.selectedTopic.drawTopic(g2, this.getFont(), true);
            } else {
                //Draw the vertex tool tip
                if (vertexLabelVisible && this.toolTipLabel != null && this.toolTipPosition != null) {
                    if (toolTipImage != null) {
                        g2.drawImage(this.toolTipImage, this.toolTipPosition.x, this.toolTipPosition.y, null);
                    } else {
                        //Getting the font information
                        g2.setFont(this.getFont());
                        java.awt.FontMetrics metrics = g2.getFontMetrics(g2.getFont());

                        //Getting the label size
                        int width = metrics.stringWidth(this.toolTipLabel);
                        int height = metrics.getAscent();

                        //Drawing a box to write de label
                        //g.setColor(java.awt.Color.DARK_GRAY);
                        //g.fillRect(this.toolTipPosition.x-2, this.toolTipPosition.y-height, width+4, height+4);
                        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.75f));
                        g2.setPaint(java.awt.Color.WHITE);
                        g2.fill(new java.awt.Rectangle(this.toolTipPosition.x - 2,
                                this.toolTipPosition.y - height, width + 4, height + 4));
                        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));

                        g2.setColor(java.awt.Color.DARK_GRAY);
                        g2.drawRect(this.toolTipPosition.x - 2, this.toolTipPosition.y - height, width + 4, height + 4);

                        //Drawing the label
                        g2.drawString(this.toolTipLabel, this.toolTipPosition.x, this.toolTipPosition.y);
                    }
                }
            }

            //drawn the selection polygon
            if (this.polygon != null) {
                g2.setColor(this.color);
                g2.drawPolygon(this.polygon);

                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.45f));
                g2.setPaint(this.color);
                g2.fillPolygon(this.polygon);
            }
        }

        public void saveToPngImageFile(String filename) throws IOException {
//         try {
//            this.paint(this.imageBuffer.getGraphics());
//            ImageIO.write(this.imageBuffer, "png", new File(filename));
//         } catch (IOException ex) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//         }
            try {
                BufferedImage image = new BufferedImage(graph.getSize().width + 1,
                        graph.getSize().height + 20, BufferedImage.TYPE_INT_RGB);
                this.paint(image.getGraphics());
                ImageIO.write(image, "png", new File(filename));
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void saveToEpsImageFile(String filename) throws IOException {
            // Save this document to example.eps
            FileOutputStream outputStream = new FileOutputStream(filename);

            EpsGraphics g = new EpsGraphics(filename, outputStream, 0, 0,
                    graph.getSize().width + 1, graph.getSize().height + 1, ColorMode.COLOR_RGB);

            //create the image
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

            if (highQualityRender) {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            } else {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            }

            g.setColor(this.getBackground());
            g.fillRect(0, 0, graph.getSize().width + 1, graph.getSize().height + 1);
            g.setFont(this.getFont());

            graph.draw(getCurrentConnectivity(), g);

            for (Topic topic : this.topics) {
                topic.drawTopic(g, this.getFont(), false);
            }

            // Flush and close the document (don't forget to do this!)
            g.flush();
            g.close();
        }

        public void setGraph(Graph graph) {

            if (graph != null) {
                this.setPreferredSize(new Dimension(graph.getSize().width * 2,
                        graph.getSize().height * 2));
                this.setSize(new Dimension(graph.getSize().width * 2,
                        graph.getSize().height * 2));

                this.cleanImage();
                this.repaint();
            }
        }

        public Vertex getMarkedVertex() {
            return markedVertex;
        }

        public void cleanImage() {
            this.imageBuffer = null;
        }

        public ColorTable getColorTable() {
            return colorTable;
        }

        public void colorAs(Scalar scalar) {
            ArrayList<Vertex> vertex = graph.getVertex();

            for (Vertex v : vertex) {
                v.setColor(scalar, this.colorTable);
            }

            this.cleanImage();
            this.repaint();
        }

        public void resizeAs(Scalar scalar) {
            ArrayList<Vertex> vertex = graph.getVertex();

            for (Vertex v : vertex) {
                v.setRayFactor(scalar);
            }

            this.cleanImage();
            this.repaint();
        }

        public void adjustPanel() {
            float iniX = graph.getVertex().get(0).getX();
            float iniY = graph.getVertex().get(0).getY();
            float max_x = iniX, max_y = iniX;
            float min_x = iniY, min_y = iniY;
            int zero = graph.getVertex().get(0).getRayBase() * 5 + 10;

            for (int i = 1; i < graph.getVertex().size(); i++) {
                float x = graph.getVertex().get(i).getX();
                if (max_x < x) {
                    max_x = x;
                } else if (min_x > x) {
                    min_x = x;
                }

                float y = graph.getVertex().get(i).getY();
                if (max_y < y) {
                    max_y = y;
                } else if (min_y > y) {
                    min_y = y;
                }
            }

            for (Vertex v : graph.getVertex()) {
                v.setX(v.getX() + zero - min_x);
                v.setY(v.getY() + zero - min_y);
            }

            Dimension d = this.getSize();
            d.width = (int) max_x + zero;
            d.height = (int) max_y + zero;
            this.setSize(d);
            this.setPreferredSize(d);
        }

        public void markNeighbors(Vertex vertex) {
            if (graph != null) {
                //clean the marked vertices
                this.cleanMarkedVertices(false);

                //mark the new vertices
                ArrayList<Vertex> neighborsVertex = new ArrayList<Vertex>();
                ArrayList<Edge> neighborsEdges = new ArrayList<Edge>();

                graph.getNeighbors(neighborsVertex, neighborsEdges,
                        getCurrentConnectivity(), vertex,
                        neighborhoodDepth);

                pexview.setNearestNeighborsPoints(neighborsVertex);
                pexview.setMarkedPointText(graph.getCorpus(), vertex);

                //selecting vertex
                vertex.setSelected(true);
                if (neighborsVertex != null) {
                    for (Vertex v : neighborsVertex) {
                        v.setSelected(true);
                    }
                }

                this.cleanImage();
                this.repaint();
            }
        }

        public void markVertices(ArrayList<Vertex> vertices) {
            if (vertices != null) {
                this.cleanMarkedVertices(false);

                //change the vertices' colors
                for (Vertex v : vertices) {
                    v.setSelected(true);
                }

                this.cleanImage();
                this.repaint();
            }
        }

        public void cleanMarkedVertices(boolean cleanVertex) {
            if (graph != null) {
                this.markedVertex = null;

                for (Vertex vertex : graph.getVertex()) {
                    vertex.setSelected(false);

                    if (cleanVertex) {
                        Vertex.setShowLabel(false);
                    }
                }
            }

            this.cleanImage();
            this.repaint();
        }

        public void deleteSelectedVertex() {
            if (this.selectedVertices != null && this.selectedVertices.size() > 0) {
                graph.removeVertex(this.selectedVertices);
                this.cleanMarkedVertices(true);
                this.selectedVertex = null;
                this.selectedVertices = null;

                this.cleanImage();
                this.repaint();

                pexview.recreatingLists(graph.getVertex());

                setGraphChanged(true);
            }
        }

        public ArrayList<Vertex> getSelectedVertex(java.awt.Polygon polygon) {
            ArrayList<Vertex> selected = new ArrayList<Vertex>();

            if (graph != null) {
                for (Vertex v : graph.getVertex()) {
                    if (polygon.contains(v.getX(), v.getY())) {
                        selected.add(v);
                    }
                }
            }

            return selected;
        }

        public ArrayList<Vertex> getSelectedVertex(java.awt.Point localSource,
                java.awt.Point localTarget) {
            ArrayList<Vertex> selVertex = new ArrayList<Vertex>();

            if (graph != null) {
                int x = localSource.x;
                int width = localTarget.x - localSource.x;

                int y = localSource.y;
                int height = localTarget.y - localSource.y;

                if (localSource.x > localTarget.x) {
                    x = localTarget.x;
                    width = localSource.x - localTarget.x;
                }

                if (localSource.y > localTarget.y) {
                    y = localTarget.y;
                    height = localSource.y - localTarget.y;
                }

                java.awt.Rectangle rect = new java.awt.Rectangle(x, y, width, height);

                for (Vertex v : graph.getVertex()) {
                    if (v.isInside(rect)) {
                        selVertex.add(v);
                    }
                }
            }

            return selVertex;
        }

        public Topic getTopicByPosition(java.awt.Point point) {
            float dist = Float.MAX_VALUE;
            Topic topic = null;

            for (Topic t : this.topics) {
                float aux = t.weightDistance(point);
                if (aux != -1 && dist > aux) {
                    dist = aux;
                    topic = t;
                }
            }
            return topic;
        }

        public Topic getTopicByBoxPosition(java.awt.Point point) {
            Topic topic = null;

            for (Topic t : this.topics) {
                if (t.isBoxInside(point)) {
                    return t;
                }
            }
            return topic;
        }

        @Override
        public void setFont(java.awt.Font font) {
            ProjectionViewer.this.setFont(font);
        }

        @Override
        public java.awt.Font getFont() {
            return ProjectionViewer.this.getFont();
        }

        public ArrayList<Topic> getTopics() {
            return topics;
        }

        @Override
        public void setBackground(Color bg) {
            super.setBackground(bg);
            if (this.csp != null) {
                this.csp.setBackground(bg);
            }
        }

        class MouseMotionListener extends MouseMotionAdapter {

            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                super.mouseMoved(evt);

                if (graph != null) {
                    if( controller == null ) {
                        Vertex vertex = null;
                        Representative rep = null;
                        ArrayList<Representative> autoRep = new ArrayList<>();

                        if (ProjectionViewer.highlightTopic || evt.isControlDown()) {
                            Topic topicByPosition = ViewPanel.this.getTopicByPosition(evt.getPoint());
                            repByPosition = graph.getRepresentativeCluster(evt.getX(), evt.getY());

                            if (ViewPanel.this.selectedTopic != null) {
                                ViewPanel.this.selectedTopic.setShowThisTopic(false);
                            }


                            if (topicByPosition != null) {
                                ViewPanel.this.selectedTopic = topicByPosition;
                                topicByPosition.setShowThisTopic(true);
                            } else {
                                ViewPanel.this.selectedTopic = null;
                            }

                            if( repByPosition != null ) 
                                repByPosition.setShowThisRepresentative(true);
                        } else if (evt.isShiftDown()) {
                            Topic topicByPosition = ViewPanel.this.getTopicByPosition(evt.getPoint());

                            if (topicByPosition != null) {
                                if (ViewPanel.this.selectedTopic != topicByPosition) {
                                    ViewPanel.this.selectedTopic = topicByPosition;
                                    topicByPosition.setShowThisTopic(!topicByPosition.isShowThisTopic());
                                }
                            } else {
                                ViewPanel.this.selectedTopic = null;
                            }

                        } else {
                            ViewPanel.this.selectedTopic = null;
                            vertex = graph.getVertexByPosition(evt.getX(), evt.getY());
                            rep = graph.getRepresentativeByPosition(evt.getX(), evt.getY());
                            autoRep = graph.getAutoRepresentativeByPosition(evt.getX(), evt.getY());                        
                        }

                        if (vertex != null) {
                            //Show the vertex label on the tool tip
                            if (Vertex.isShowImage()) {
                                try {
                                    Image im = graph.getImageCollection().getImage(vertex.getUrl());
                                    if (im != null) {
                                        ViewPanel.this.toolTipImage = im.getScaledInstance(100, 100, 0);
                                    } else {
                                        ViewPanel.this.toolTipImage = null;
                                    }
                                    ViewPanel.this.toolTipLabel = "";
                                    ViewPanel.this.toolTipPosition = evt.getPoint();
                                    ViewPanel.this.repaint();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            } else if (Vertex.isShowContent()) {
                                //Show the vertex label on the tool tip
                                try {
                                    ViewPanel.this.toolTipLabel = graph.getCorpus().getFullContent(vertex.getUrl());
                                } catch (IOException ex) {
                                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                                }
                                ViewPanel.this.toolTipImage = null;
                            } else if (Vertex.isShowTitle()) {
                                ViewPanel.this.toolTipLabel = vertex.toString();
                                ViewPanel.this.toolTipImage = null;
                            } else {
                                ViewPanel.this.toolTipLabel = null;
                                ViewPanel.this.toolTipImage = null;
                            }
                            if (ViewPanel.this.toolTipLabel != null && ViewPanel.this.toolTipLabel.trim().length() > 0) {
                                if (ViewPanel.this.toolTipLabel.length() > 100) {
                                    ViewPanel.this.toolTipLabel = ViewPanel.this.toolTipLabel.substring(0, 96) + "...";
                                }
                                ViewPanel.this.toolTipPosition = evt.getPoint();
                                ViewPanel.this.repaint();
                            }

                            titleTextField.setText(ViewPanel.this.toolTipLabel);
                            titleTextField.setCaretPosition(0);
                        } else {
                            //Clear the tool tip
                            titleTextField.setText("");
                            ViewPanel.this.toolTipLabel = null;
                            ViewPanel.this.toolTipPosition = null;
                            ViewPanel.this.toolTipImage = null;
                            ViewPanel.this.repaint();
                        }

                        if (rep != null) {
                            rep.setTransparent(true);
                            updateImage();
                        } else {
                            updateImage();
                        }

                        if( !autoRep.isEmpty() ) {
                            autoRep.forEach((r)->r.setTransparent(true));
                            updateImage();
                        } else 
                            updateImage();
                    
                    } else {
                        if( semaphore )
                            return;

                        if( controller.representative() != null && controller.nearest() != null ) {                        

                            Polygon polygon = controller.clickedPolygon(evt.getX(), evt.getY());                        
                            if( polygon != null ) {
                                double dist = Double.MAX_VALUE;
                                int indexDist = 0;

                                for( int i = 0; i < controller.representative().length; ++i ) {

                                    double d = br.com.methods.utils.Util.euclideanDistance(evt.getX(), evt.getY(), 
                                            points[controller.representative()[i]].x, points[controller.representative()[i]].y);

                                    if( d < dist ) {
                                        dist = d;
                                        indexDist = controller.representative()[i];
                                    }
                                }

                                representativePolygon = indexDist;
                            } else {
                                representativePolygon = -1;
                            }

                            cleanImage();
                            repaint();


                            int index = controller.indexRepresentative(evt.getX(), evt.getY());
                            if( index != -1 ) {

                                if( tooltip != null )
                                    return;

                                ExplorerTreeNode node = controller.getNode(index);                            
                                if( node.children().isEmpty() ) {  
                                    semaphore = true;
                                    List<OverlapRect> projection = removeOverlap(controller.nearest().get(index));
                                    
                                    List<Integer> indexElements = controller.nearest().get(index);
                                    ///List<OverlapRect> projection = new ArrayList<>();
                                    List<Color> projectionColors = new ArrayList<>();
                                    for( int i = 0; i < indexElements.size(); ++i ) {
                                        Vertex v = graph.getVertex().get(indexElements.get(i));
//                                        OverlapRect rect = new OverlapRect(v.getX(), v.getY(), v.getRay()*2, v.getRay()*2, (int) v.getId());
//                                        projection.add(rect);
                                        projectionColors.add(v.getColor());
                                    }
                                    
                                    tooltip = new Tooltip(new Point2D.Double(evt.getX(), evt.getY()), projection);
                                    tooltip.setColors(projectionColors);
                                    timerTooltip = new Timer(0, new ActionListener() {
                                        private float opacity = 0;

                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if( opacity > 1.0f )
                                                opacity = 1.0f;
                                            tooltip.setOpacity(opacity);
                                            opacity += 0.1f;
                                            if( opacity >= 1.0f ) {
                                                cleanImage();
                                                repaint();
                                                timerTooltip.stop();
                                                semaphore = false;
                                            }
                                            cleanImage();
                                            repaint();
                                        }                                    
                                    });

                                    timerTooltip.setDelay(5);
                                    timerTooltip.setRepeats(true);
                                    timerTooltip.start();
                                } else if( tooltip != null ) {
                                    tooltip = null;
                                    cleanImage();
                                    repaint();
                                }

                            } else if( tooltip != null ) {

                                semaphore = true;
                                timerTooltip = new Timer(0, new ActionListener() {
                                    private float opacity = tooltip.getOpacity();

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if( opacity < 0.0f )
                                            opacity = 0.0f;
                                        tooltip.setOpacity(opacity);
                                        opacity -= 0.1f;
                                        if( opacity <= 0.0f ) {
                                            cleanImage();
                                            repaint();
                                            timerTooltip.stop();
                                            semaphore = false;
                                            tooltip = null;
                                        }
                                        cleanImage();
                                        repaint();
                                    }                                    
                                });

                                timerTooltip.setDelay(5);
                                timerTooltip.setRepeats(true);
                                timerTooltip.start();
                            }

                        }
                    }
                    
                    
                    
                    
                }
            }

            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                if (ProjectionViewer.movePoints && ViewPanel.this.selectedVertex != null) {

                    if (ViewPanel.this.selectedVertices != null
                            && ViewPanel.this.selectedVertices.size() > 0
                            && ViewPanel.this.selectedVertices.contains(ViewPanel.this.selectedVertex)) {
                        float x = evt.getX() - ViewPanel.this.selectedVertex.getX();
                        float y = evt.getY() - ViewPanel.this.selectedVertex.getY();

                        for (Vertex v : ViewPanel.this.selectedVertices) {
                            v.setX(x + v.getX());
                            v.setY(y + v.getY());
                        }

                        setGraphChanged(true);
                        ViewPanel.this.adjustPanel();
                    } else {
                        ViewPanel.this.selectedVertex.setX(evt.getX());
                        ViewPanel.this.selectedVertex.setY(evt.getY());

                        setGraphChanged(true);
                        ViewPanel.this.adjustPanel();
                    }

                    ViewPanel.this.cleanImage();
                } else if (ViewPanel.this.source != null) {
                    ViewPanel.this.target = evt.getPoint();
                }

                if (ViewPanel.this.polygon != null) {
                    ViewPanel.this.polygon.addPoint(evt.getX(), evt.getY());
                }

                ViewPanel.this.repaint();
            }

            
        }

        class MouseClickedListener extends MouseAdapter {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                super.mouseClicked(evt);

                if (graph != null) {
                    if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                        if (!ProjectionViewer.highlightTopic
                                && !evt.isControlDown() && !evt.isShiftDown()) {
                            Vertex v = graph.getVertexByPosition(evt.getX(), evt.getY());

                            if (v != null) {
                                if (evt.getClickCount() == 1) {
                                    ViewPanel.this.markNeighbors(v);
                                    ViewPanel.this.markedVertex = v;
                                } else {
                                    if (v.getUrl().endsWith("txt")) {
                                        if (OpenDialog.checkCorpus(graph, pexview)) {
                                            MultipleFileView.getInstance(pexview).display(ProjectionViewer.this, v);
                                        }
                                    } else {
                                        if (OpenDialog.checkImages(graph, pexview)) {
                                            MultipleImageView.getInstance(pexview).display(ProjectionViewer.this, v);
                                        }
                                    }
                                }
                            }

                        }
                    } else if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                        graph.initRepresentative();
                        ViewPanel.this.cleanMarkedVertices(true);
                    }
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                super.mousePressed(evt);
                
                if( controller == null ) {
                    if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                        if (graph != null) {

                            if (ProjectionViewer.movePoints) {
                                ViewPanel.this.selectedVertex = graph.getVertexByPosition(evt.getX(), evt.getY());

                                if (ViewPanel.this.selectedVertex != null) {
                                    ViewPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                }
                            }

                            Scalar s = (Scalar) scalarCombo.getSelectedItem();
                            ViewPanel.this.source = evt.getPoint();
                            ViewPanel.this.color = VertexSelectionFactory.getInstance(ProjectionViewer.this,
                                    ProjectionViewer.type, s).getColor();
                        }
                    } else if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                        Scalar s = (Scalar) scalarCombo.getSelectedItem();
                        ViewPanel.this.polygon = new java.awt.Polygon();
                        ViewPanel.this.polygon.addPoint(evt.getX(), evt.getY());
                        ViewPanel.this.color = VertexSelectionFactory.getInstance(ProjectionViewer.this,
                                ProjectionViewer.type, s).getColor();
                    }

                    if( repByPosition != null ) {
                        repByPosition.setShowThisRepresentative(true);
                        repByPosition.setTransparent(true);
                        updateImage();
                    }
                    if( evt.isAltDown() )
                        graph.tellRepresentatives(evt.getX(), evt.getY());
                
                } else {
                    
                    if( semaphore )
                        return;
                    if(  controller.representative() != null && controller.nearest() != null ) {
                        int index = controller.indexRepresentative(evt.getX(), evt.getY());

                        lastClicked = new Point2D.Double(evt.getX(), evt.getY());
                        if( index != -1 ) {        

                            ExplorerTreeNode node = controller.getNode(index);                            
                            if( evt.isControlDown() && node.parent() != null )
                                agglomerateAnimation(index, node);                                      
                            else if( !node.children().isEmpty() )
                                expandAnimation(index, evt);
                            else if( node.children().isEmpty() ) {    
                                semaphore = false;
                                //removeSubsetOverlap(controller.nearest().get(index), index);
                                cleanImage();
                                repaint();
                            }

                        } 
                    }   
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                super.mouseReleased(evt);

                if (graph != null) {
                    
                    
                    if ((ViewPanel.this.source != null && ViewPanel.this.target != null)
                            || ViewPanel.this.polygon != null) {
                        ArrayList<Vertex> vertices = null;

                        if (ViewPanel.this.polygon != null) {
                            vertices = ViewPanel.this.getSelectedVertex(ViewPanel.this.polygon);
                        } else {
                            vertices = ViewPanel.this.getSelectedVertex(ViewPanel.this.source, ViewPanel.this.target);
                        }

                        if (vertices != null) {
                            ViewPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                            ViewPanel.this.cleanMarkedVertices(false);
                            Scalar s = (Scalar) scalarCombo.getSelectedItem();
                            ViewPanel.this.selectedVertices = VertexSelectionFactory.getInstance(ProjectionViewer.this,
                                    ProjectionViewer.type, s).vertexSelected(null, vertices);

                            ViewPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        }
                    }
                    
                    if( repByPosition != null ) {
                        repByPosition.setShowThisRepresentative(true);
                        repByPosition.setTransparent(false);
                        updateImage();
                    }
                }

                if (ViewPanel.this.selectedVertex != null) {
                    ViewPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }

                ViewPanel.this.polygon = null;
                ViewPanel.this.selectedVertex = null;
                ViewPanel.this.source = null;
                ViewPanel.this.target = null;
                ViewPanel.this.repaint();
            }
        }      

        private void agglomerateAnimation(int index, ExplorerTreeNode node) {

            semaphore = true;
            movingIndexes = controller.agglomerateNode(index);
            parentMoving = node.parent().routing();
            timer = new Timer(0, new ActionListener() {
                private double i = 0;

                @Override
                public void actionPerformed(ActionEvent e) {

                        for( int j = 0; j < movingIndexes.size(); ++j) {
                            int v = movingIndexes.get(j);
                            double x = (1.0-i)*controller.projection()[v].x +
                                    i*controller.projection()[parentMoving].x;
                            double y = (1.0-i)*controller.projection()[v].y +
                                    i*controller.projection()[parentMoving].y;

                            toDraw.add(new Point2D.Double(x, y));

                            i += 0.01;
                            if( i >= 1.0 ) {
                                parentMoving = -1;
                                movingIndexes.clear();
                                toDraw.clear();
                                cleanImage();
                                repaint();
                                timer.stop();
                                semaphore = false;
                            }
                        }

                        cleanImage();
                        repaint();
                }
            });

            timer.setDelay(10);
            timer.setRepeats(true);
            timer.start();
        }

        private void expandAnimation(int index, MouseEvent e) {
            semaphore = true;
            movingIndexes = controller.expandNode(index, e.getX(), e.getY(), getSize().width, getSize().height);
            timer = new Timer(0, new ActionListener() {
                private double i = 0;

                @Override
                public void actionPerformed(ActionEvent e) {

                        for( int j = 0; j < movingIndexes.size(); ++j) {
                            int v = movingIndexes.get(j);
                            double x = (1.0-i)*controller.projection()[index].x + i*controller.projection()[v].x;
                            double y = (1.0-i)*controller.projection()[index].y + i*controller.projection()[v].y;

                            toDraw.add(new Point2D.Double(x, y));

                            i += 0.01;
                            if( i >= 1.0 ) {
                                movingIndexes.clear();
                                toDraw.clear();
                                cleanImage();
                                repaint();
                                timer.stop();
                                semaphore = false;
                            }
                        }

                        cleanImage();
                        repaint();



                }
            });

            timer.setDelay(10);
            timer.setRepeats(true);
            timer.start();
        }
        
        
        private void drawScale(Graphics2D g2Buffer) {
            g2Buffer.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));            

            int width = 255;
            int height = 30;
            int x = 10;
            int y = 10;            
            int spaceDescription = 15;

            for( int i = 0; i < width; ++i ) {
                float value = (float)i/(float)width;
                int r = (int) ((255 * (value*100))/100);
                int g = 0;
                int b = (int) ((255 * (100 - value*100)))/100;

                g2Buffer.setColor(new Color(r, g, b));
                g2Buffer.drawLine(x+i, y, x+i, y+height);
            }

            g2Buffer.setColor(Color.BLACK);
            g2Buffer.drawRect(x, y, width, height);

            g2Buffer.drawString("min", x, y + height + spaceDescription);
            g2Buffer.drawString("max", x + width - g2Buffer.getFontMetrics().stringWidth("max"), y + height + spaceDescription);
        }

        
        private List<OverlapRect> removeOverlap(List<Integer> indexes) {
            int algo = 1;//Integer.parseInt(JOptionPane.showInputDialog("Deseja utilizar uma estrutura de matriz esparsa?\n0-N�o\n1-Sim"));
            boolean applySeamCarving = false;//Integer.parseInt(JOptionPane.showInputDialog("Apply SeamCarving?")) == 1;
            
            //List<OverlapRect> rects = br.com.methods.utils.Util.toRectangle(rectangles, indexes);
            
            List<OverlapRect> rects = new ArrayList<>();
            indexes.stream().forEach((e)-> {
                Vertex vertex = graph.getVertex().get(e);
                rects.add(new OverlapRect(vertex.getX(), vertex.getY(), vertex.getRay()*2, vertex.getRay()*2, e));
            });
            
            
            double[] center0 = br.com.methods.utils.Util.getCenter(rects);
          //  PRISM prism = new PRISM(algo);
            RWordleC rwordlec = new RWordleC();

          //  Map<OverlapRect, OverlapRect> projected = prism.applyAndShowTime(rects);
            Map<OverlapRect, OverlapRect> projected = rwordlec.applyAndShowTime(rects);
            List<OverlapRect> projectedValues = br.com.methods.utils.Util.getProjectedValues(projected);
            double[] center1 = br.com.methods.utils.Util.getCenter(projectedValues);
            double ammountX = center0[0]-center1[0];
            double ammountY = center0[1]-center1[1];
            br.com.methods.utils.Util.translate(projectedValues, ammountX, ammountY);        
            br.com.methods.utils.Util.normalize(projectedValues);

            if( applySeamCarving )
                projectedValues = OverlapView.addSeamCarvingResult(projectedValues);

            return projectedValues;
        }
//
//        private void removeSubsetOverlap(List<Integer> indexes, int representative) {
//            int algo = 1;//Integer.parseInt(JOptionPane.showInputDialog("Deseja utilizar uma estrutura de matriz esparsa?\n0-N�o\n1-Sim"));
//            boolean applySeamCarving = false;//Integer.parseInt(JOptionPane.showInputDialog("Apply SeamCarving?")) == 1;
//            List<OverlapRect> rects = br.com.methods.utils.Util.toRectangle(rectangles, indexes);
//
//            System.out.println("-------------------");
//            for( int i = 0; i < rects.size(); ++i ) {
//                System.out.println(rects.get(i).x+", "+rects.get(i).y);
//            }
//            System.out.println("-------------------");
//
//
//            double maxDistance = -1;
//            for( int i = 0; i < indexes.size(); ++i ) {
//                RectangleVis p1 = rectangles.get(representative);
//                RectangleVis p2 = rectangles.get(indexes.get(i));
//
//                double d = br.com.methods.utils.Util.euclideanDistance(p1.x, p1.y, p2.x, p2.y);            
//                if( d > maxDistance )
//                    maxDistance = d;
//
//            }
//            double[] center0 = br.com.methods.utils.Util.getCenter(rects);
//            PRISM prism = new PRISM(algo);
//            Map<OverlapRect, OverlapRect> projected = prism.applyAndShowTime(rects);
//            List<OverlapRect> projectedValues = br.com.methods.utils.Util.getProjectedValues(projected);
//            double[] center1 = br.com.methods.utils.Util.getCenter(projectedValues);
//
//            double ammountX = center0[0]-center1[0];
//            double ammountY = center0[1]-center1[1];
//            br.com.methods.utils.Util.translate(projectedValues, ammountX, ammountY);        
//            br.com.methods.utils.Util.normalize(projectedValues);
//
//            if( applySeamCarving )
//                projectedValues = OverlapView.addSeamCarvingResult(projectedValues);
//
//            ArrayList<RectangleVis> cluster = new ArrayList<>();
//            br.com.methods.utils.Util.toRectangleVis(cluster, projectedValues, indexes);
//    //        
//
//            int rep = -1;
//            List<OverlapRect> toforce = new ArrayList<>();
//            ArrayList<OverlapRect> overlaps = new ArrayList<>();
//            List<Map.Entry<OverlapRect, OverlapRect>> entryset = projected.entrySet().stream().collect(Collectors.toList());
//            for( int i = 0; i < entryset.size(); ++i ) {
//                double d = br.com.methods.utils.Util.euclideanDistance(entryset.get(i).getKey().x, entryset.get(i).getKey().y, 
//                        rectangles.get(representative).getUX(), rectangles.get(representative).getUY());
//                System.out.println(">> distance: "+d);
//                if( d == 0 ) {
//                    rep = i;
//                    System.out.println("INDEX REPRESENTATIVE: "+i+" ID: "+entryset.get(i).getValue().getId());
//
//                }
//                overlaps.add(entryset.get(i).getKey());
//                toforce.add(entryset.get(i).getValue());
//            }
//
//            JFrame frame = new JFrame();
//            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            OverlapView panel = new OverlapView(projected, cluster, afterSeamCarving);
//
//            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);        
//            slider.setPaintTicks(true);
//            slider.setPaintLabels(true);
//
//            slider.addChangeListener(panel);
//
//
//            frame.add(panel, BorderLayout.CENTER);
//            frame.add(slider, BorderLayout.SOUTH);
//            panel.setLocation((int)lastClicked.x, (int)lastClicked.y);
//    //        
//            panel.cleanImage();
//            panel.repaint();
//            panel.adjustPanel();  
//            frame.setSize(panel.getSize().width, panel.getSize().height+100);
//            frame.setLocationRelativeTo(this);
//            frame.setVisible(true);
//    //        
//
//
//            List<OverlapRect> after = new ForceLayout().repulsive(toforce, rep, 1, 5);
//
//            ArrayList<RectangleVis> rectanglesforce = new ArrayList<>();
//            for( OverlapRect o: after ) {
//                RectangleVis rec = new RectangleVis(o.getUX(), o.getUY(), o.width, o.height, Color.BLUE, o.getId());
//                rectanglesforce.add(rec);
//            }
//            JFrame frame2 = new JFrame();
//            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            OverlapView panel2 = new OverlapView(projected, rectanglesforce, afterSeamCarving);
//
//            JSlider slider2= new JSlider(JSlider.HORIZONTAL, 0, 100, 100);        
//            slider2.setPaintTicks(true);
//            slider2.setPaintLabels(true);
//
//            slider2.addChangeListener(panel2);
//
//
//            frame2.add(panel2, BorderLayout.CENTER);
//            frame2.add(slider2, BorderLayout.SOUTH);
//            panel2.setLocation((int)lastClicked.x, (int)lastClicked.y);
//
//    //        panel2.cleanImage();
//    //        panel2.repaint();
//    //        panel2.adjustPanel();  
//    //        frame2.setSize(panel2.getSize().width, panel2.getSize().height+100);
//    //        frame2.setLocationRelativeTo(this);
//    //        frame2.setVisible(true);
//
//
//            /**
//             * Testing NMap representation
//             */
//
//            List<Element> data = new ArrayList<>();
//
//            List<OverlapRect> proj1 = projected.entrySet().stream().map((v)->v.getKey()).collect(Collectors.toList());
//            List<OverlapRect> proj2 = projected.entrySet().stream().map((v)->v.getValue()).collect(Collectors.toList());
//            Random rand = new Random();
//
//            for( int i = 0; i < proj2.size(); ++i ) {
//
//
//                double distance =  br.com.methods.utils.Util.euclideanDistance(rectangles.get(representative).x, rectangles.get(representative).y, 
//                                                          proj1.get(i).x, proj1.get(i).y);
//
//                double weight = ExplorerTreeController.calculateWeight(10, 0.2*10, maxDistance, distance);
//                data.add(new Element(proj2.get(i).getId(), (float)proj2.get(i).x, (float)proj2.get(i).y, (float) weight, 1));
//
//                System.out.println("id: "+proj2.get(i).getId()+" x: "+proj2.get(i).x+" -- y: "+proj2.get(i).y);
//
//            }
//            int visualSpaceWidth = 800;
//            int visualSpaceHeight = 600;
//
//            NMap nmap = new NMap(visualSpaceWidth, visualSpaceHeight);
//
//            // We can use this when weights are different        
//            List<BoundingBox> ac = nmap.alternateCut(data);
//    //        Frame frameAlternateCut = new Frame(visualSpaceWidth, visualSpaceHeight, ac, "NMap Alternate Cut");
//    //        frameAlternateCut.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    //        frameAlternateCut.setVisible(true);
//    ////        
//    ////        List<BoundingBox> ew = nmap.equalWeight(data);
//    ////        Frame frameEqualWeight = new Frame(visualSpaceWidth, visualSpaceHeight, ew, "NMAP Equal Weight");
//    ////        frameEqualWeight.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    ////        frameEqualWeight.setVisible(true);       
//    ////        
//    //        List<OverlapRect> proj = projected.entrySet().stream().map((v)->v.getKey()).collect(Collectors.toList());
//    //        
//    //        List<Element> data2 = new ArrayList<>();
//    //        for( int i = 0; i < proj.size(); ++i )
//    //            data2.add(new Element(proj.get(i).getId(), (float)proj.get(i).x, (float)proj.get(i).y, 1.0f, 1.0f));
//    //        
//    //        List<BoundingBox> ew2 = nmap.equalWeight(data2);
//    //        Frame frameEqualWeight2 = new Frame(visualSpaceWidth, visualSpaceHeight, ew2, "NMAP Equal Weight 2");
//    //        frameEqualWeight2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    //        frameEqualWeight2.setVisible(true);
//    //        
//    //        
//            List<OverlapRect> after2 = OverlapView.removeOverlap(overlaps, rep);//new ForceNMAP(800, 600).repulsive(toforce, rep, 0.2*10, 10);
//    //        List<OverlapRect> after2 = new ArrayList<>();
//    //        for( BoundingBox bb: ac ) {
//    //            Element e = bb.getElement();
//    //            System.out.println("id: "+e.getId()+" x: "+e.x+" -- y: "+e.y);
//    //            after2.add(new OverlapRect(e.x, e.y, RECTSIZE, RECTSIZE, e.getId()));
//    //        }
//
//
//
//
//
//            ArrayList<RectangleVis> rectanglesforce2 = new ArrayList<>();
//            for( OverlapRect o: after2 ) {
//                RectangleVis rec = new RectangleVis(o.getUX(), o.getUY(), o.width, o.height, Color.BLUE, o.getId());
//                rectanglesforce2.add(rec);
//            }
//            JFrame frame3 = new JFrame();
//            frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            OverlapView panel3 = new OverlapView(projected, rectanglesforce2, afterSeamCarving);
//
//            JSlider slider3 = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);        
//            slider3.setPaintTicks(true);
//            slider3.setPaintLabels(true);
//
//            slider3.addChangeListener(panel3);
//
//
//            frame3.add(panel3, BorderLayout.CENTER);
//            frame3.add(slider3, BorderLayout.SOUTH);
//            panel3.setLocation((int)lastClicked.x, (int)lastClicked.y);
//
//            panel3.cleanImage();
//            panel3.repaint();
//            panel3.adjustPanel();  
//            frame3.setSize(panel3.getSize().width, panel3.getSize().height+100);
//            frame3.setLocationRelativeTo(this);
//            frame3.setVisible(true);
//        }
        
        


        //Used to select points with the polygon
        private java.awt.Polygon polygon;
        //Used to select points with the retangle
        private java.awt.Point source = null;
        private java.awt.Point target = null;
        private java.awt.Color color = java.awt.Color.RED;
        //Used to color based on a document
        private Vertex markedVertex;
        //Used to move the points
        private Vertex selectedVertex;
        private Image toolTipImage;
        //Used to show the vertex tool tip
        private String toolTipLabel;
        private java.awt.Point toolTipPosition;
        //used to show the topics
        private ArrayList<Topic> topics = new ArrayList<Topic>();

        //the topic been selected
        private Topic selectedTopic = null;
        //contain the selected vertices which will be moved
        private ArrayList<Vertex> selectedVertices;
        //The image which will be drawn as a graph
        private BufferedImage imageBuffer;
        //Font used to create the label        
        private ProjectionExplorerView pexview;
        private ColorScalePanel csp;
        private ColorTable colorTable;
        
        private Representative repByPosition = null;        
    }

    public boolean isBoard() {
        return board;
    }

    public void setBoard(boolean board) {
        this.board = board;
        boardPolygons = new ArrayList<>();
    }

    public void addPolygonBoard(Polygon poly) {
        boardPolygons.add(poly);
    }

    private java.awt.Font font = new java.awt.Font("Verdana", java.awt.Font.BOLD, 13);
    private LegendView legendview;
    private int neighborhoodDepth = 1;
    private ViewPanel view;
    private Graph graph;
    private ForceDirectLayout force;
    private boolean start = true;
    private Scalar prevColorScalar;
    private Scalar prevSizeScalar;
    private Mapping prevCoord = Mapping.OFF;
    private Connectivity prevConnectivity;
    private DefaultComboBoxModel scalarComboModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel coordComboModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel edgesComboModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel titlesComboModel = new DefaultComboBoxModel();

    private boolean board = false;
    private ArrayList<Polygon> boardPolygons;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel comboPanel;
    private javax.swing.JComboBox edgesCoordComboBox;
    private javax.swing.JToggleButton edgesCoordToggleButton;
    private javax.swing.JButton legendButton;
    private javax.swing.JSplitPane mapSplitPane;
    private javax.swing.JTabbedPane mapTabbedPane;
    private javax.swing.JPanel projectionPanel;
    private javax.swing.JPanel reportPanel;
    private javax.swing.JScrollPane reportScrollPane;
    private javax.swing.JComboBox scalarCombo;
    private javax.swing.JToggleButton scalarToggleButton;
    private javax.swing.JScrollPane scrollPaneGraph;
    private javax.swing.JButton splitScalars;
    private javax.swing.JComboBox titleComboBox;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField titleTextField;
    // End of variables declaration//GEN-END:variables
}
