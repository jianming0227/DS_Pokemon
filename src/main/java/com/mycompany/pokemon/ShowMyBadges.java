/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pokemon;

import static com.mycompany.pokemon.ShowMapPage.saveNumber;
import java.awt.FontFormatException;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class ShowMyBadges extends javax.swing.JFrame {
    static int saveNumber;

    /**
     * Creates new form ShowMyBadges
     */
    public ShowMyBadges(int saveNumber) {
        this.saveNumber = saveNumber;
        initComponents();
        String[] badges = ConnectDatabase.showMyBadgesGUI(saveNumber);
        
        ImageIcon boulderImage = null;
        ImageIcon boulderBImage = null;
        boulderImage = new ImageIcon("C:\\Users\\User\\Downloads\\Boulder.png");
        boulderBImage = new ImageIcon("C:\\Users\\User\\Downloads\\Boulder_Badge.png");        
        Image boulderResizedImage = boulderImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);
        Image boulderBResizedImage = boulderBImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);        
        ImageIcon boulderIcon = new ImageIcon(boulderResizedImage);
        ImageIcon boulderBIcon = new ImageIcon(boulderBResizedImage);
               
            if(badges[0] != "None"){
                Boulder.setIcon(boulderBIcon);
            } else
                Boulder.setIcon(boulderIcon);
            
        ImageIcon cascadeImage = null;
        ImageIcon cascadeBImage = null;
        cascadeImage = new ImageIcon("C:\\Users\\User\\Downloads\\Cascade.png");
        cascadeBImage = new ImageIcon("C:\\Users\\User\\Downloads\\Cascade_Badge.png");        
        Image cascadeResizedImage = cascadeImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);
        Image cascadeBResizedImage = cascadeBImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);        
        ImageIcon cascadeIcon = new ImageIcon(cascadeResizedImage);
        ImageIcon cascadeBIcon = new ImageIcon(cascadeBResizedImage);
               
            if(badges[1] != "None"){
                Cascade.setIcon(cascadeBIcon);
            } else
                Cascade.setIcon(cascadeIcon);    
        
            
            ImageIcon thunderImage = null;
        ImageIcon thunderBImage = null;
        thunderImage = new ImageIcon("C:\\Users\\User\\Downloads\\Thunder.png");
        thunderBImage = new ImageIcon("C:\\Users\\User\\Downloads\\Thunder_Badge.png");        
        Image thunderResizedImage = thunderImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);
        Image thunderBResizedImage = thunderBImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);        
        ImageIcon thunderIcon = new ImageIcon(thunderResizedImage);
        ImageIcon thunderBIcon = new ImageIcon(thunderBResizedImage);
               
            if(badges[2] != "None"){
                Thunder.setIcon(thunderBIcon);
            } else
                Thunder.setIcon(thunderIcon);
            
            ImageIcon rainbowImage = null;
        ImageIcon rainbowBImage = null;
        rainbowImage = new ImageIcon("C:\\Users\\User\\Downloads\\Rainbow.png");
        rainbowBImage = new ImageIcon("C:\\Users\\User\\Downloads\\Rainbow_Badge.png");        
        Image rainbowResizedImage = rainbowImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);
        Image rainbowBResizedImage = rainbowBImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);        
        ImageIcon rainbowIcon = new ImageIcon(rainbowResizedImage);
        ImageIcon rainbowBIcon = new ImageIcon(rainbowBResizedImage);
               
            if(badges[3] != "None"){
                Rainbow.setIcon(rainbowBIcon);
            } else
                Rainbow.setIcon(rainbowIcon);
            
            ImageIcon soulImage = null;
        ImageIcon soulBImage = null;
        soulImage = new ImageIcon("C:\\Users\\User\\Downloads\\Soul.png");
        soulBImage = new ImageIcon("C:\\Users\\User\\Downloads\\Soul_Badge.png");        
        Image soulResizedImage = soulImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);
        Image soulBResizedImage = soulBImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);        
        ImageIcon soulIcon = new ImageIcon(soulResizedImage);
        ImageIcon soulBIcon = new ImageIcon(soulBResizedImage);
               
            if(badges[4] != "None"){
                Soul.setIcon(soulBIcon);
            } else
                Soul.setIcon(soulIcon);
            
            
            ImageIcon marshImage = null;
        ImageIcon marshBImage = null;
        marshImage = new ImageIcon("C:\\Users\\User\\Downloads\\Marsh.png");
        marshBImage = new ImageIcon("C:\\Users\\User\\Downloads\\Marsh_Badge.png");        
        Image marshResizedImage = marshImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);
        Image marshBResizedImage = marshBImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);        
        ImageIcon marshIcon = new ImageIcon(marshResizedImage);
        ImageIcon marshBIcon = new ImageIcon(marshBResizedImage);
               
            if(badges[5] != "None"){
                Marsh.setIcon(marshBIcon);
            } else
                Marsh.setIcon(marshIcon);
            
            ImageIcon volcanoImage = null;
        ImageIcon volcanoBImage = null;
        volcanoImage = new ImageIcon("C:\\Users\\User\\Downloads\\Volcano.png");
        volcanoBImage = new ImageIcon("C:\\Users\\User\\Downloads\\Volcano_Badge.png");        
        Image volcanoResizedImage = volcanoImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);
        Image volcanoBResizedImage = volcanoBImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);        
        ImageIcon volcanoIcon = new ImageIcon(volcanoResizedImage);
        ImageIcon volcanoBIcon = new ImageIcon(volcanoBResizedImage);
               
            if(badges[6] != "None"){
                Volcano.setIcon(volcanoBIcon);
            } else
                Volcano.setIcon(volcanoIcon);
            
            ImageIcon earthImage = null;
        ImageIcon earthBImage = null;
        earthImage = new ImageIcon("C:\\Users\\User\\Downloads\\Earth.png");
        earthBImage = new ImageIcon("C:\\Users\\User\\Downloads\\Earth_Badge.png");        
        Image earthResizedImage = earthImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);
        Image earthBResizedImage = earthBImage.getImage().getScaledInstance(167, 167, Image.SCALE_SMOOTH);        
        ImageIcon earthIcon = new ImageIcon(earthResizedImage);
        ImageIcon earthBIcon = new ImageIcon(earthBResizedImage);
               
            if(badges[7] != "None"){
                Earth.setIcon(earthBIcon);
            } else
                Earth.setIcon(earthIcon);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        Cascade = new javax.swing.JLabel();
        Thunder = new javax.swing.JLabel();
        Rainbow = new javax.swing.JLabel();
        Soul = new javax.swing.JLabel();
        Marsh = new javax.swing.JLabel();
        Volcano = new javax.swing.JLabel();
        Earth = new javax.swing.JLabel();
        Boulder = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1080, 720));
        setMinimumSize(new java.awt.Dimension(1080, 720));
        setPreferredSize(new java.awt.Dimension(1080, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 680, 310, 40));

        Cascade.setText("jLabel2");
        Cascade.setMaximumSize(new java.awt.Dimension(167, 167));
        Cascade.setMinimumSize(new java.awt.Dimension(167, 167));
        Cascade.setPreferredSize(new java.awt.Dimension(167, 167));
        getContentPane().add(Cascade, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, -1, -1));

        Thunder.setText("jLabel2");
        Thunder.setMaximumSize(new java.awt.Dimension(167, 167));
        Thunder.setMinimumSize(new java.awt.Dimension(167, 167));
        Thunder.setPreferredSize(new java.awt.Dimension(167, 167));
        getContentPane().add(Thunder, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, -1, -1));

        Rainbow.setText("jLabel2");
        Rainbow.setMaximumSize(new java.awt.Dimension(167, 167));
        Rainbow.setMinimumSize(new java.awt.Dimension(167, 167));
        Rainbow.setPreferredSize(new java.awt.Dimension(167, 167));
        getContentPane().add(Rainbow, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, -1, -1));

        Soul.setText("jLabel2");
        Soul.setMaximumSize(new java.awt.Dimension(167, 167));
        Soul.setMinimumSize(new java.awt.Dimension(167, 167));
        Soul.setPreferredSize(new java.awt.Dimension(167, 167));
        getContentPane().add(Soul, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, -1, -1));

        Marsh.setText("jLabel2");
        Marsh.setMaximumSize(new java.awt.Dimension(167, 167));
        Marsh.setMinimumSize(new java.awt.Dimension(167, 167));
        Marsh.setPreferredSize(new java.awt.Dimension(167, 167));
        getContentPane().add(Marsh, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, -1, -1));

        Volcano.setText("jLabel2");
        Volcano.setMaximumSize(new java.awt.Dimension(167, 167));
        Volcano.setMinimumSize(new java.awt.Dimension(167, 167));
        Volcano.setPreferredSize(new java.awt.Dimension(167, 167));
        getContentPane().add(Volcano, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 440, -1, -1));

        Earth.setText("jLabel2");
        Earth.setMaximumSize(new java.awt.Dimension(167, 167));
        Earth.setMinimumSize(new java.awt.Dimension(167, 167));
        Earth.setPreferredSize(new java.awt.Dimension(167, 167));
        getContentPane().add(Earth, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 440, -1, -1));

        Boulder.setText("jLabel2");
        Boulder.setMaximumSize(new java.awt.Dimension(167, 167));
        Boulder.setMinimumSize(new java.awt.Dimension(167, 167));
        Boulder.setPreferredSize(new java.awt.Dimension(167, 167));
        getContentPane().add(Boulder, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\My Badges (1).png")); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
                MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                this.dispose();
                mainMenuPageFrame.setVisible(true);
                mainMenuPageFrame.pack();
                mainMenuPageFrame.setLocationRelativeTo(null);
            } catch (FontFormatException ex) {
                Logger.getLogger(LoadGamePage.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShowMyBadges.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowMyBadges.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowMyBadges.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowMyBadges.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowMyBadges(saveNumber).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Boulder;
    private javax.swing.JLabel Cascade;
    private javax.swing.JLabel Earth;
    private javax.swing.JLabel Marsh;
    private javax.swing.JLabel Rainbow;
    private javax.swing.JLabel Soul;
    private javax.swing.JLabel Thunder;
    private javax.swing.JLabel Volcano;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
