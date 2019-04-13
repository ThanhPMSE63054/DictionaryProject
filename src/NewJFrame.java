
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceDustLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;
import vn.me.SE1278ValidarorLib;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PRO
 */
public class NewJFrame extends javax.swing.JFrame {

    String filenameE_V = "E-V.txt";
    String filenameV_E = "V-E.txt";
    EBST treeEV = new EBST();
    EBST treeVE = new EBST();
    
    public NewJFrame() {

        initComponents();
        loadData();
        Thread autoSave = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(5000);
                        save();
                    }
                } catch (Exception e) {
                }
            }
        };
        autoSave.start();

    }

    //load
    public void loadEV() {
        try {
            FileReader f = new FileReader(filenameE_V);
            BufferedReader bf = new BufferedReader(f);
            String S;
            String s;
            String[] v = null;
            while ((S = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(S, ":");
                String E_word = stk.nextToken().trim();
                ArrayList arr = new ArrayList();
                v = stk.nextToken().trim().split(",");
                for (int i = 0; i < v.length; i++) {
                    arr.add(v[i]);
                }
                EBSTNode E = new EBSTNode(E_word, arr);
                treeEV.Insert(E);
            }
            bf.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadVE() {
        try {
            FileReader f = new FileReader(filenameV_E);
            BufferedReader bf = new BufferedReader(f);
            String S;
            String s;
            String[] v = null;
            while ((S = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(S, ":");
                String E_word = stk.nextToken().trim();
                ArrayList arr = new ArrayList();
                v = stk.nextToken().trim().split(",");
                for (int i = 0; i < v.length; i++) {
                    arr.add(v[i]);
                }
                EBSTNode E = new EBSTNode(E_word, arr);
                treeVE.Insert(E);
            }
            bf.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        loadEV();
        loadVE();
        rbtE.setSelected(true);
    }

    //load
    //save
    public String getV_word(ArrayList V_word) {
        String result = "";
        for (int i = 0; i < V_word.size(); i++) {
            result = result + V_word.get(i);
            if (i < (V_word.size() - 1)) {
                result = result + ",";
            }
        }
        return result;
    }

    public void PreorderToPrint(EBSTNode p, PrintWriter pw) {
        if (p != null) {
            pw.println(p.toString());
            PreorderToPrint(p.left, pw);
            PreorderToPrint(p.right, pw);
        }
    }

    public void saveEV() {
        try {
            PrintWriter pw = new PrintWriter(filenameE_V);
            PreorderToPrint(treeEV.root, pw);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveVE() {
        try {
            PrintWriter pw = new PrintWriter(filenameV_E);
            PreorderToPrint(treeVE.root, pw);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        saveEV();
        saveVE();
        Date d = new Date(System.currentTimeMillis());
        lblTime.setText("Data saved at: " + d.toString());
    }
    //save

    private boolean checkWord() {
        if (SE1278ValidarorLib.checkName(this.txtNewWord.getText()) && SE1278ValidarorLib.checkName(this.txtNewMeans.getText())) {
            return true;
        } else if (!SE1278ValidarorLib.checkName(this.txtNewWord.getText())) {
            JOptionPane.showMessageDialog(this, "the word not invaild");
            txtNewWord.requestFocus();
        }
        if (!SE1278ValidarorLib.checkName(this.txtNewMeans.getText())) {
            JOptionPane.showMessageDialog(this, "the mean not invaild");
            txtNewMeans.requestFocus();
            
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbtE = new javax.swing.JRadioButton();
        rbtV = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtWord = new javax.swing.JTextField();
        txtMeans = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNewWord = new javax.swing.JTextField();
        txtNewMeans = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dictionary ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        buttonGroup1.add(rbtE);
        rbtE.setText("E-V dictionary ");
        rbtE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtEActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtV);
        rbtV.setText("V-E dictionary ");

        jLabel1.setText("Word");

        jLabel2.setText(" Means");

        txtWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWordActionPerformed(evt);
            }
        });

        txtMeans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMeansMouseClicked(evt);
            }
        });
        txtMeans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMeansActionPerformed(evt);
            }
        });

        btnSearch.setText("Translate");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtE)
                        .addGap(18, 18, 18)
                        .addComponent(rbtV)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtWord, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(btnSearch)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMeans, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtE)
                    .addComponent(rbtV))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMeans, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(txtWord))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(btnSearch)
                        .addGap(108, 108, 108))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add new word", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel3.setText("New word");

        jLabel4.setText("Means");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblTime.setForeground(new java.awt.Color(102, 255, 102));
        lblTime.setText("auto save");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNewWord, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                            .addComponent(txtNewMeans))
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNewWord, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNewMeans, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblTime)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        if (rbtE.isSelected() == true) {
            EBSTNode find = treeEV.Search(txtWord.getText());
            if (find == null) {
                JOptionPane.showMessageDialog(this, "This word is not in the dictionary");
                txtMeans.setText("");
                txtWord.requestFocus();
            } else {
                txtMeans.setText(find.getV_word(find.V_word));
            }
        } else {
            EBSTNode find = treeVE.Search(txtWord.getText());
            if (find == null) {
                JOptionPane.showMessageDialog(this, "This word is not in the dictionary");
                txtMeans.setText("");
                txtWord.requestFocus();
            } else {
                txtMeans.setText(find.getV_word(find.V_word));
            }
        }
        txtNewMeans.setText("");
        txtNewWord.setText("");
    }//GEN-LAST:event_btnSearchActionPerformed

    public void addNewWord(String word, String mean, EBST tree) {
        
        if(!checkWord()){
            return;
        }
        int check = 0;
        EBSTNode nn = tree.Search(word);
        if (nn == null) {
            ArrayList arr = new ArrayList();
            arr.add(mean);
            tree.Insert(new EBSTNode(word, arr));
            JOptionPane.showMessageDialog(this, "Added new word!!!!");
        } else {
            for (int i = 0; i < nn.V_word.size(); i++) {
                if (mean.equals(nn.V_word.get(i))) {
                    check++;
                }
            }
            if (check != 0) {
                JOptionPane.showMessageDialog(this, "The dictionary had this word");
            } else {
                nn.V_word.add(mean);
                JOptionPane.showMessageDialog(this, "Added new means!!!!");
            }
        }
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        if (rbtE.isSelected() == true) {
            addNewWord(txtNewWord.getText(), txtNewMeans.getText(), treeEV);
        } else {
            addNewWord(txtNewWord.getText(), txtNewMeans.getText(), treeVE);
        }
    }//GEN-LAST:event_btnAddActionPerformed


    private void txtWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWordActionPerformed

    private void txtMeansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMeansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMeansActionPerformed

    private void rbtEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtEActionPerformed

    private void txtMeansMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMeansMouseClicked
       
    }//GEN-LAST:event_txtMeansMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        try{
              setDefaultLookAndFeelDecorated(true);
              UIManager.setLookAndFeel(new SubstanceDustLookAndFeel());
//              UIManager.setLookAndFeel(new SubstanceSaharaLookAndFeel());
              
              
          }catch(Exception e){
              
          }
        
        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTime;
    private javax.swing.JRadioButton rbtE;
    private javax.swing.JRadioButton rbtV;
    private javax.swing.JTextField txtMeans;
    private javax.swing.JTextField txtNewMeans;
    private javax.swing.JTextField txtNewWord;
    private javax.swing.JTextField txtWord;
    // End of variables declaration//GEN-END:variables
}
