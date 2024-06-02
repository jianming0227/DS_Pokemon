/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pokemon;

import java.awt.FontFormatException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class ShowMapPage extends javax.swing.JFrame {

    static int saveNumber;
    /**
     * Creates new form ShowMapPage
     */
    public ShowMapPage(int saveNumber) {
        this.saveNumber = saveNumber;
        initComponents();
        
        String currentLocation = ConnectDatabase.getLocation(saveNumber);
        Location.setText(currentLocation);
        String palletTownPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Pallet Town.png";
        String saffronCityPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Saffron City.png";
        String fuchsiaCityPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Fuchsia City.png";
        String lavenderTownPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Lavender Town.png";
        String pewterCityPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Pewter City.png";
        String cinnabarIslandPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Cinnabar Island.png";
        String celadonCityPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Celadon City.png";
        String ceruleanCityPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Cerulean City.png";
        String viridianCityPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Viridian City.png";
        String vermilionCityPath = "C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMap\\Vermilion City.png";
        ImageIcon palletTownImage = new ImageIcon(palletTownPath);
        ImageIcon saffronCityImage = new ImageIcon(saffronCityPath);
        ImageIcon fuchsiaCityImage = new ImageIcon(fuchsiaCityPath);
        ImageIcon lavenderTownImage = new ImageIcon(lavenderTownPath);
        ImageIcon pewterCityImage = new ImageIcon(pewterCityPath);
        ImageIcon cinnabarIslandImage = new ImageIcon(cinnabarIslandPath);
        ImageIcon celadonCityImage = new ImageIcon(celadonCityPath);
        ImageIcon ceruleanCityImage = new ImageIcon(ceruleanCityPath);
        ImageIcon viridianCityImage = new ImageIcon(viridianCityPath);
        ImageIcon vermilionCityImage = new ImageIcon(vermilionCityPath);
        switch (Location.getText()) {
            case "PalletTown":
                currentLocationImg.setIcon(palletTownImage);
                break;
            case "ViridianCity":
                currentLocationImg.setIcon(viridianCityImage);
                break;
            case "PewterCity":
                currentLocationImg.setIcon(pewterCityImage);
                break;
            case "CeruleanCity":
                currentLocationImg.setIcon(ceruleanCityImage);
                break;
            case "CeladonCity":
                currentLocationImg.setIcon(celadonCityImage);
                break;
            case "SaffronCity":
                currentLocationImg.setIcon(saffronCityImage);
                break;
            case "LavenderTown":
                currentLocationImg.setIcon(lavenderTownImage);
                break;
            case "VermilionCity":
                currentLocationImg.setIcon(vermilionCityImage);
                break;
            case "FuschiaCity": 
                currentLocationImg.setIcon(fuchsiaCityImage);
                break;
            case "CinnabarIsland":
                currentLocationImg.setIcon(cinnabarIslandImage);
                break;
            default:
                break;
        }
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
        jLabel1 = new javax.swing.JLabel();
        currentLocationImg = new javax.swing.JLabel();
        Location = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 610, 160, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\KantoRegionHeader.png")); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setPreferredSize(new java.awt.Dimension(1080, 100));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        getContentPane().add(currentLocationImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1080, 620));

        Location.setText("jLabel2");
        getContentPane().add(Location, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, -1, -1));

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
            java.util.logging.Logger.getLogger(ShowMapPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowMapPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowMapPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowMapPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowMapPage(saveNumber).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Location;
    private javax.swing.JLabel currentLocationImg;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
