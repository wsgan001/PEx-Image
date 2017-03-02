/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import visualizer.datamining.clustering.BKmeans;
import visualizer.datamining.clustering.Jdbscan2D;
import visualizer.datamining.clustering.SilhouetteCoefficient;
import visualizer.datamining.dataanalysis.KruskalStress;
import visualizer.datamining.dataanalysis.SammonStress;
import visualizer.datamining.dataanalysis.Stress;
import visualizer.graph.Representative;
import visualizer.graph.Vertex;
import visualizer.matrix.DenseMatrix;
import visualizer.matrix.DenseVector;
import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
import visualizer.projection.distance.Dissimilarity;
import visualizer.projection.distance.DissimilarityFactory;
import visualizer.projection.distance.DissimilarityType;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.distance.Euclidean;
import visualizer.projection.representative.AnalysisType;
import visualizer.projection.representative.BoxplotDataGenerator;
import visualizer.projection.representative.BoxplotRepresentative;
import visualizer.projection.representative.NeighborhoodHitClusterData;
import visualizer.projection.representative.NeighborhoodPreservationClusterData;
import visualizer.projection.representative.SilhouetteCoefficientClusterData;
import visualizer.projection.representative.StressClusterData;
import visualizer.util.OpenDialog;
import visualizer.util.PExConstants;
import visualizer.util.Util;
import visualizer.util.filefilter.DATAFilter;

/**
 *
 * @author wilson
 */
public class ClusterAnalysisView extends javax.swing.JFrame {
    private Viewer[] gv;
    private String arquivo;
    private File file = null;
    
    private static final String CLASS_NAME;
    static {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        CLASS_NAME = stackTrace[0].getClassName();
    }
    /**
     * Creates new form ClusterAnalysisView
     */
    protected ClusterAnalysisView() {
        initComponents();
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(bkmeansRadioButton);
        bg.add(dbscanRadioButton);
        
        DissimilarityType.getTypes().stream().filter(
                (disstype)->(disstype != DissimilarityType.KOLMOGOROV && disstype != DissimilarityType.NONE)
        ).forEach((d)->distanceTypeComboBox.addItem(d));
        
        arquivo = "";
        setResizable(false);
        
        saveDataCheckBox1ActionPerformed(null);
        
        nHitCheckBox.setSelected(true);
        nPreservationCheckBox.setSelected(true);
        sCoefficientCheckBox.setSelected(true);
        stressCheckBox.setSelected(true);
    }
    
    public static ClusterAnalysisView getInstance() {
        return (ClusterAnalysisView) SingletonRegistry.getInstance(CLASS_NAME);
    }
    
    public void display(Viewer[] gv) {
        this.gv = gv;
        pack();
        setLocationRelativeTo(getParent());
        for( Viewer g: gv )
            g.cleanSelection(rootPaneCheckingEnabled);
        setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nNeighborsTextField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nHitCheckBox = new javax.swing.JCheckBox();
        nPreservationCheckBox = new javax.swing.JCheckBox();
        sCoefficientCheckBox = new javax.swing.JCheckBox();
        stressCheckBox = new javax.swing.JCheckBox();
        stressTypeComboBox = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        distanceTypeComboBox = new javax.swing.JComboBox();
        analysisTypeComboBox = new javax.swing.JComboBox();
        bkmeansRadioButton = new javax.swing.JRadioButton();
        dbscanRadioButton = new javax.swing.JRadioButton();
        compareCheckBox = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        pointsTextField = new javax.swing.JTextField();
        searchJButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        fileDataTextField1 = new javax.swing.JTextField();
        fileDataButton1 = new javax.swing.JButton();
        saveDataCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cluster Analysis"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Number of Neighbors"));

        jLabel1.setText("Nearest Neighbors:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nNeighborsTextField)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nNeighborsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Metrics"));

        jLabel2.setText("Metrics:");

        nHitCheckBox.setText("Neighborhood Hit");
        nHitCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nHitCheckBoxActionPerformed(evt);
            }
        });

        nPreservationCheckBox.setText("Neighborhood Preservation");

        sCoefficientCheckBox.setText("Silhouette Coefficient");

        stressCheckBox.setText("Stress");

        stressTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kruskal's Stress", "Sammon's Stress" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(stressCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stressTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nPreservationCheckBox)
                            .addComponent(nHitCheckBox)
                            .addComponent(sCoefficientCheckBox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nHitCheckBox)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nPreservationCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sCoefficientCheckBox)
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stressCheckBox)
                    .addComponent(stressTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose the Distance Type and Cluster Algorithm"));

        analysisTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Global Analysis", "Local Analysis", "Both" }));

        bkmeansRadioButton.setSelected(true);
        bkmeansRadioButton.setText("Bisecting K-means");

        dbscanRadioButton.setText("DBSCAN");

        compareCheckBox.setText("Compare with Projection");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(distanceTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bkmeansRadioButton)
                    .addComponent(dbscanRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(analysisTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compareCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(bkmeansRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dbscanRadioButton))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(distanceTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(analysisTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(compareCheckBox)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Source File"));

        searchJButton.setText("Search...");
        searchJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(pointsTextField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchJButton))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchJButton)
                    .addComponent(pointsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Save Data"));

        fileDataButton1.setText("Search...");
        fileDataButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileDataButton1ActionPerformed(evt);
            }
        });

        saveDataCheckBox1.setText("Do you want save data?");
        saveDataCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDataCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(fileDataTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fileDataButton1))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(saveDataCheckBox1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileDataTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileDataButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(saveDataCheckBox1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(generateButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nHitCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nHitCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nHitCheckBoxActionPerformed

    private void searchJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJButtonActionPerformed
        int result = OpenDialog.showOpenDialog(new DATAFilter(), this);
        if( result == JFileChooser.APPROVE_OPTION ) {
            String filename = OpenDialog.getFilename();
            arquivo = filename;
            pointsTextField.setText(filename);
        }
    }//GEN-LAST:event_searchJButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed

        try {
            DissimilarityType mtype = (DissimilarityType) distanceTypeComboBox.getSelectedItem();
            Dissimilarity diss = DissimilarityFactory.getInstance(mtype);

            int analysisType = analysisTypeComboBox.getSelectedIndex();
            Stress stress = chooseStressType();
            int nneighbors = Integer.parseInt(nNeighborsTextField.getText());            
            
            Matrix matrixProjection = MatrixFactory.getInstance(arquivo);
            DistanceMatrix dmatProjection = new DistanceMatrix(matrixProjection, diss);

            ArrayList<ArrayList<Integer>> clusters = null;
            String scalarName = null;
            
            String inputValue = null;
            if( bkmeansRadioButton.isSelected() ) {
                inputValue = (String) JOptionPane.showInputDialog(null,
                        "Choose the number of clusters:", "Defining the Number of Clusters",
                        JOptionPane.QUESTION_MESSAGE, null, null,
                        (Object) Integer.toString((int) Math.sqrt(this.gv[0].getGraph().getVertex().size())));

                if( inputValue == null ) return;
            }
            
            Matrix matrix = new DenseMatrix();        
            prepareMatrix(matrix, gv[0]);

            if( bkmeansRadioButton.isSelected() ) {
               int nclusters = Integer.parseInt(inputValue);
               
                BKmeans km = new BKmeans(nclusters);
                clusters = km.execute(new Euclidean(), matrix);
                scalarName = PExConstants.KMEANS + clusters.size();

            } else {
                Jdbscan2D dbscan = new Jdbscan2D();
               
                clusters = dbscan.execute(new Euclidean(), matrix);
                scalarName = PExConstants.DBSCAN + clusters.size();
            }
            
            
            
            ArrayList<WrapperRepresentative> wReps = new ArrayList<>();
            for( int i = 0; i < gv.length; ++i ) {
                
                for( int cluster = 0; cluster < clusters.size(); ++cluster ) {
                    ArrayList<Vertex> vertex = new ArrayList<>();
                    for( int v = 0; v < clusters.get(cluster).size(); ++v ) 
                        vertex.add(gv[i].getGraph().getVertex().get(clusters.get(cluster).get(v)));

                    
                    Matrix mCluster = Util.exportProjection(vertex, gv[i].getCurrentScalar());

                    List<String> ids = new ArrayList<>();
                    vertex.forEach((v)->ids.add(v.getUrl()));

                    Matrix matrixCluster = MatrixFactory.getInstance(arquivo, ids);
                    DistanceMatrix dmat = new DistanceMatrix(matrixCluster, diss);                    

                    List<BoxplotDataGenerator> boxplotList = new ArrayList<>();
                    
                    int nClusters = getNumberClusters(mCluster);   
                    addSelectedMetrics(boxplotList, nneighbors, dmat, dmatProjection, analysisType, diss, stress, nClusters);               

                    BoxplotRepresentative boxplot = createBoxplotRepresentative(boxplotList);
                    
                                        
                    Matrix projection = Util.exportProjection(gv[i].getGraph(), gv[i].getCurrentScalar());
                    JPanel panelRepresentative = boxplot.generateRepresentative(mCluster, projection);
                    Image img = boxplot.representativeAsImage();
                    Representative r = new Representative(vertex, img, 
                            vertex.get(0).getColor(), panelRepresentative);
                    gv[i].getGraph().addAutoRepresentative(r);
                    
                    wReps.add(new WrapperRepresentative(r, boxplot));                    
                }
                
                setClustersId(wReps);
                
                wReps.stream().forEach((wp) -> {
                    wantsSave(wp.bRep, wp.rep.getClusterId());
                });
                
                gv[i].updateImage();            
            }
            
            setVisible(false);
        } catch( IOException e ) {
            JOptionPane.showMessageDialog(this, e);
            throw new RuntimeException(e);
        }

    }//GEN-LAST:event_generateButtonActionPerformed

    private int getNumberClusters(Matrix mCluster) {
        List<Float> scClusters = SilhouetteCoefficient.getClusters(mCluster.getClassData());
        int nClusters = scClusters.size();
        return nClusters;
    }

    private void generateScalars(ArrayList<ArrayList<Integer>> clusters, HashMap<String, Integer> cindex, Matrix matrix, Viewer g) {
        for( int i = 0; i < clusters.size(); ++i )
            for( int j = 0; j < clusters.get(i).size(); ++j )
                cindex.put(matrix.getRow(clusters.get(i).get(j)).getId(), i);
        
//        g.getGraph().getVertex().forEach((v)->{
//            if( cindex.containsKey(v.getUrl()) ) {
//                v.setScalar(scalar, cindex.get(v.getUrl()));
//            } else {
//                v.setScalar(scalar, 0);
//                Logger.getLogger(this.getClass().getName()).log(Level.INFO,
//                        "Probably the points file is not the one used to create the projection!");
//            }
//        });
      //  g.updateScalars(scalar);
    }

    private void prepareMatrix(Matrix matrix, Viewer g) {
        for( int i = 0; i < g.getGraph().getVertex().size(); ++i ) {
            float[] point = new float[2];
            point[0] = g.getGraph().getVertex().get(i).getX();
            point[1] = g.getGraph().getVertex().get(i).getY();
            float cdata = g.getGraph().getVertex().get(i).getScalar(g.getCurrentScalar());
            matrix.addRow(new DenseVector(point, g.getGraph().getVertex().get(i).getUrl(), cdata));
        }
    }

    private Stress chooseStressType() {
        return (stressTypeComboBox.getSelectedIndex() == 0) ? new KruskalStress() : new SammonStress();
    }

    private void addSelectedMetrics(List<BoxplotDataGenerator> boxplotList, int nneighbors, DistanceMatrix dmat, DistanceMatrix dmatProjection, int analysisType, Dissimilarity diss, Stress stress, int nClusters) {
        if( nPreservationCheckBox.isSelected() )
            boxplotList.add(new NeighborhoodPreservationClusterData(nneighbors, dmat, dmatProjection, AnalysisType.which(analysisType)));
        
        if( nHitCheckBox.isSelected() )
            boxplotList.add(new NeighborhoodHitClusterData(nneighbors, AnalysisType.which(analysisType)));
        
        if( sCoefficientCheckBox.isSelected()  ) {
            if( analysisType == AnalysisType.LOCAL.getValue() && nClusters > 1  ) { // local
                boxplotList.add(new SilhouetteCoefficientClusterData(diss, AnalysisType.LOCAL));
            } else if( analysisType == AnalysisType.BOTH.getValue() ) {
                if( nClusters > 1 )
                    boxplotList.add(new SilhouetteCoefficientClusterData(diss, AnalysisType.BOTH));
                else
                    boxplotList.add(new SilhouetteCoefficientClusterData(diss, AnalysisType.GLOBAL));
            } else {
                boxplotList.add(new SilhouetteCoefficientClusterData(diss, AnalysisType.GLOBAL));
            }
        }
        
        if( stressCheckBox.isSelected() )
            boxplotList.add(new StressClusterData(dmatProjection, dmat, stress, AnalysisType.which(analysisType)));
    }

    private BoxplotRepresentative createBoxplotRepresentative(List<BoxplotDataGenerator> boxplotList) {
        return new BoxplotRepresentative(boxplotList, compareCheckBox.isSelected());
    }
    
    private void wantsSave(BoxplotRepresentative boxplotRep, String title) {
        if( !fileDataTextField1.getText().isEmpty() ) {
            
            try {
                String filename = fileDataTextField1.getText().split("\\.")[0]+"_"+title+".data";
                
                file = new File(filename);
                if( !file.exists() )
                    file.createNewFile();
                
                boxplotRep.save(file);
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Problems with the file", JOptionPane.ERROR_MESSAGE);                
            }  
            
        }               
    }
    

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void fileDataButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileDataButton1ActionPerformed

        JFileChooser saveDialog = new JFileChooser();
        int result = saveDialog.showSaveDialog(this);

        if( result == JFileChooser.APPROVE_OPTION ) {
            String filename = saveDialog.getSelectedFile().getAbsolutePath();
            fileDataTextField1.setText(filename);
        }
    }//GEN-LAST:event_fileDataButton1ActionPerformed

    private void saveDataCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDataCheckBox1ActionPerformed
        boolean state = saveDataCheckBox1.isSelected();
        fileDataTextField1.setEditable(state);
        fileDataButton1.setEnabled(state);
    }//GEN-LAST:event_saveDataCheckBox1ActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox analysisTypeComboBox;
    private javax.swing.JRadioButton bkmeansRadioButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JCheckBox compareCheckBox;
    private javax.swing.JRadioButton dbscanRadioButton;
    private javax.swing.JComboBox distanceTypeComboBox;
    private javax.swing.JButton fileDataButton1;
    private javax.swing.JTextField fileDataTextField1;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JCheckBox nHitCheckBox;
    private javax.swing.JTextField nNeighborsTextField;
    private javax.swing.JCheckBox nPreservationCheckBox;
    private javax.swing.JTextField pointsTextField;
    private javax.swing.JCheckBox sCoefficientCheckBox;
    private javax.swing.JCheckBox saveDataCheckBox1;
    private javax.swing.JButton searchJButton;
    private javax.swing.JCheckBox stressCheckBox;
    private javax.swing.JComboBox stressTypeComboBox;
    // End of variables declaration//GEN-END:variables

    private void setClustersId(ArrayList<WrapperRepresentative> autoRep) {
        sortClusters(autoRep);
        for( int id = 0; id < autoRep.size(); ++id )
            autoRep.get(id).rep.setClusterId(String.valueOf(id+1));
        
    }

    private void sortClusters(ArrayList<WrapperRepresentative> autoRep) {
        Collections.sort(autoRep, (WrapperRepresentative o1, WrapperRepresentative o2) -> {
            int x1 = o1.rep.getRectangle().x;
            int x2 = o2.rep.getRectangle().x;
            
            if (x1 < x2) return -1;
            if (x1 > x2) return 1;
            return 0;
        });
        
        Collections.sort(autoRep, (WrapperRepresentative o1, WrapperRepresentative o2) -> {
            int y1 = o1.rep.getRectangle().y;
            int y2 = o2.rep.getRectangle().y;
            
            if (y1 < y2) return -1;
            if (y1 > y2) return 1;
            return 0;
        });
    }
    
    private class WrapperRepresentative {
        public Representative rep;
        public BoxplotRepresentative bRep;
        
        public WrapperRepresentative(Representative rep, BoxplotRepresentative bRep) {
            this.rep = rep;
            this.bRep = bRep;
        }
    }
    
    
}