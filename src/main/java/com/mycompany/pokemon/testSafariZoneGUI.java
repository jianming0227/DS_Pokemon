/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pokemon;

/**
 *
 * @author User
 */
import java.awt.Font;
import java.awt.FontFormatException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
public class testSafariZoneGUI extends javax.swing.JFrame {

    static int saveNumber;
    /**
     * Creates new form testSafariZoneGUI
     */
    public testSafariZoneGUI(int saveNumber) {
        this.saveNumber = saveNumber;
        initComponents();
        initComponents2();
    }
    
    private void initComponents2() {

    JPanel upperPanel = new javax.swing.JPanel();
    JLabel jLabel2 = new javax.swing.JLabel();
    JPanel LowerPanel = new javax.swing.JPanel();
    JButton Eevee = new javax.swing.JButton();
    JButton Bulbasaur = new javax.swing.JButton();
    JButton Snorlax = new javax.swing.JButton();
    JButton Machop = new javax.swing.JButton();
    JButton Pikachu = new javax.swing.JButton();
    JButton Jigglypuff = new javax.swing.JButton();
    JButton Charmander = new javax.swing.JButton();
    JButton Check = new javax.swing.JButton();
    JButton Back = new javax.swing.JButton(); // New Back button
    JLabel jLabel1 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new java.awt.Dimension(1167, 780));
    setPreferredSize(new java.awt.Dimension(1172, 780));
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    upperPanel.setBackground(new java.awt.Color(252, 227, 75));

    jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\SafariZone_pics\\SafariZone_pics\\10.png")); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(upperPanel);
    upperPanel.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addComponent(jLabel2)
            .addContainerGap(824, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap(15, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    getContentPane().add(upperPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1217, -1));

    LowerPanel.setBackground(new java.awt.Color(255, 255, 255));
    LowerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    Eevee.setBackground(new java.awt.Color(255, 255, 204));
    Eevee.setFont(new java.awt.Font("SimHei", 1, 14)); // NOI18N
    Eevee.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\SafariZone_pics\\SafariZone_pics\\eevee.png")); // NOI18N
    Eevee.setText("EEVEE");
    Eevee.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            EeveeActionPerformed(evt);
        }
    });
    LowerPanel.add(Eevee, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 256));

    Bulbasaur.setBackground(new java.awt.Color(255, 255, 204));
    Bulbasaur.setFont(new java.awt.Font("SimHei", 1, 14)); // NOI18N
    Bulbasaur.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\SafariZone_pics\\SafariZone_pics\\bulbasaur.png")); // NOI18N
    Bulbasaur.setText("BULBASAUR");
    Bulbasaur.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BulbasaurActionPerformed(evt);
        }
    });
    LowerPanel.add(Bulbasaur, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 290, 256));

    Snorlax.setBackground(new java.awt.Color(255, 255, 204));
    Snorlax.setFont(new java.awt.Font("SimHei", 1, 14)); // NOI18N
    Snorlax.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\SafariZone_pics\\SafariZone_pics\\snorlax.png")); // NOI18N
    Snorlax.setText("SNORLAX");
    Snorlax.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            SnorlaxActionPerformed(evt);
        }
    });
    LowerPanel.add(Snorlax, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 280, 256));

    Machop.setBackground(new java.awt.Color(255, 255, 204));
    Machop.setFont(new java.awt.Font("SimHei", 1, 14)); // NOI18N
    Machop.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\SafariZone_pics\\SafariZone_pics\\machop.png")); // NOI18N
    Machop.setText("MACHOP");
    Machop.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            MachopActionPerformed(evt);
        }
    });
    LowerPanel.add(Machop, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 270, 256));

    Pikachu.setBackground(new java.awt.Color(255, 255, 204));
    Pikachu.setFont(new java.awt.Font("SimHei", 1, 14)); // NOI18N
    Pikachu.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\SafariZone_pics\\SafariZone_pics\\pikachu.png")); // NOI18N
    Pikachu.setText("PIKACHU");
    Pikachu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            PikachuActionPerformed(evt);
        }
    });
    LowerPanel.add(Pikachu, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 280, 256));

    Jigglypuff.setBackground(new java.awt.Color(255, 255, 204));
    Jigglypuff.setFont(new java.awt.Font("SimHei", 1, 14)); // NOI18N
    Jigglypuff.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\SafariZone_pics\\SafariZone_pics\\jigglypuff.png")); // NOI18N
    Jigglypuff.setText("JIGGLYPUFF");
    Jigglypuff.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            JigglypuffActionPerformed(evt);
        }
    });
    LowerPanel.add(Jigglypuff, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, 290, 256));

    Charmander.setBackground(new java.awt.Color(255, 255, 204));
    Charmander.setFont(new java.awt.Font("SimHei", 1, 14)); // NOI18N
    Charmander.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\SafariZone_pics\\SafariZone_pics\\charmander.png")); // NOI18N
    Charmander.setText("CHARMANDER");
    Charmander.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            CharmanderActionPerformed(evt);
        }
    });
    LowerPanel.add(Charmander, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 280, 290, 256));

    Check.setBackground(new java.awt.Color(252, 227, 75));
    Check.setFont(new java.awt.Font("SimHei", 1, 24)); // NOI18N
    Check.setText("Check");
    Check.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            CheckActionPerformed(evt);
        }
    });
    LowerPanel.add(Check, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 550, 232, 53));

    // Configure the Back button
    Back.setBackground(new java.awt.Color(252, 227, 75));
    Back.setFont(new java.awt.Font("SimHei", 1, 24)); // NOI18N
    Back.setText("Back");
    Back.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BackActionPerformed(evt);
        }
    });
    LowerPanel.add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 550, 130, 53));

    jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\SafariZone_pics\\SafariZone_pics\\11.png")); // NOI18N
    jLabel1.setText("jLabel1");
    LowerPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, 1160, 620));

    getContentPane().add(LowerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 128, 1160, 620));

    setLocationRelativeTo(null);
    pack();
}                    
    
    private void BackActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
        this.dispose();
        mainMenuPageFrame.setVisible(true);
        mainMenuPageFrame.pack();
        mainMenuPageFrame.setLocationRelativeTo(null);
    } catch (FontFormatException ex) {
        Logger.getLogger(LoadGamePage.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void PikachuActionPerformed(java.awt.event.ActionEvent evt) {                                        
        pokemons.add("Pikachu");
    }                                       

    private void EeveeActionPerformed(java.awt.event.ActionEvent evt) {                                      
        pokemons.add("Eevee");
    }                                     

    private void BulbasaurActionPerformed(java.awt.event.ActionEvent evt) {                                          
        pokemons.add("Bulbasaur");
    }                                         

    private void SnorlaxActionPerformed(java.awt.event.ActionEvent evt) {                                        
        pokemons.add("Snorlax");
    }                                       

    private void MachopActionPerformed(java.awt.event.ActionEvent evt) {                                       
        pokemons.add("Machop");
    }                                      

    private void JigglypuffActionPerformed(java.awt.event.ActionEvent evt) {                                           
        pokemons.add("Jigglypuff");
    }                                          

    private void CharmanderActionPerformed(java.awt.event.ActionEvent evt) {                                           
        pokemons.add("Charmander");
    }                                          

    private void CheckActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Check for invalid combinations
            boolean isInvalid = false;
            if (checkCondition1(pokemons)) {
                System.out.println("Condition 1 failed");
                isInvalid = true;
            } else if (checkCondition2(pokemons)) {
                System.out.println("Condition 2 failed");
                isInvalid = true;
            } else if (checkCondition3(pokemons)) {
                System.out.println("Condition 3 failed");
                isInvalid = true;
            } else if (checkCondition4(pokemons)) {
                System.out.println("Condition 4 failed");
                isInvalid = true;
            } else if (checkCondition5(pokemons)) {
                System.out.println("Condition 5 failed");
                isInvalid = true;
            } else if (!checkCondition6(pokemons)) {
                System.out.println("Condition 6 failed");
                isInvalid = true;
            } else if (pokemons.size()<2) isInvalid = true;

            if (isInvalid) {
                JOptionPane.showMessageDialog(null, "Invalid Combination!!\nTry Again.");
                pokemons.clear();
                return; // Exit the method
            }

            // Clear pokemonList before adding elements
            pokemonList.clear();

            // Add selected Pokémon to pokemonList
            for (int i = 0; i < pokemons.size(); i++) {
                pokemonList.add(pokemons.get(i));
            }

            // Display selected Pokémon
            JOptionPane.showMessageDialog(null, "You've chosen:\n" + displayList(pokemonList));

            // Sort Pokémon and display log
            JTextPane textPane = new JTextPane();
            textPane.setContentType("text/html");
            textPane.setText(sortPokemon(pokemonList)); 
            textPane.setCaretPosition(0);
            textPane.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textPane);
            JOptionPane.showMessageDialog(null, scrollPane, "Your POKEMONs are now sorted!", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
    }                               

    ArrayList<String> pokemons = new ArrayList<>();
    LinkedList<String> pokemonList = new LinkedList<>();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(testSafariZoneGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testSafariZoneGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testSafariZoneGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testSafariZoneGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testSafariZoneGUI(saveNumber).setVisible(true);
            }
        });
    }
    
    public static String displayList(LinkedList<String> list) {
        StringBuilder Plist = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Plist.append(list.get(i));
            if (i != list.size() - 1) {
                Plist.append(", ");
            }
        }
        return Plist.toString();
    }
    
    public static String sortPokemon(LinkedList<String> list) {
        StringBuilder log = new StringBuilder();

        log.append("<html><body style='font-size: 14px; font-family: Arial;'>");

        // Eevee insists on being positioned either at the beginning of the lineup to showcase its adaptability
        moveToFirst(list, "Eevee");
        log.append("<i>Step 1: Eevee insists on being positioned either at the beginning of the lineup to showcase its adaptability.</i>")
           .append("<br>Sorted List: ").append(displayList(list)).append("<br>");

        // Pikachu demands to be placed at the center of the arrangement
        moveToCenter(list, "Pikachu");
        log.append("<br><i>Step 2: Pikachu demands to be placed at the center of the arrangement.</i>")
           .append("<br>Partial Sort: ").append(displayList(list)).append("<br>");

        // Snorlax insists on being positioned at the end of the lineup to ensure maximum relaxation
        moveToLast(list, "Snorlax");
        log.append("<br><i>Step 3: Snorlax insists on being positioned at the end of the lineup to ensure maximum relaxation.</i>")
           .append("<br>Partial Sort: ").append(displayList(list)).append("<br>");

        // Jigglypuff prefers to be surrounded by other "cute" Pokémon for morale purposes
        moveToPikachu(list, "Jigglypuff");
        log.append("<br><i>Step 4: Jigglypuff prefers to be surrounded by other \"cute\" Pokémon for morale purposes.</i>")
           .append("<br>Partial Sort: ").append(displayList(list)).append("<br>");

        // Bulbasaur refuses to be placed next to Charmander
        avoidCharmander(list, "Bulbasaur", "Charmander");
        log.append("<br><i>Step 5: Bulbasaur refuses to be placed next to Charmander.</i>")
           .append("<br>Partial Sort: ").append(displayList(list)).append("<br>");

        // Machop demands to be placed next to the heaviest Pokemon in the lineup
        moveToSnorlax(list, "Machop");
        log.append("<br><i>Step 6: Machop demands to be placed next to the heaviest Pokemon in the lineup.</i>")
           .append("<br><b>Final Sorted List: ").append(displayList(list)).append("</b><br>");

        return log.toString();
    }

    private static void moveToFirst(LinkedList<String> list, String Eevee) {
        int index = list.indexOf(Eevee);
        if (index != -1) {
            list.remove(index);
            list.addFirst(Eevee);
        }
    }

    private static void moveToCenter(LinkedList<String> list, String Pikachu) {
        int index = list.indexOf(Pikachu);
        if (index != -1) {
            list.remove(index);
            list.add((list.size() + 1) / 2, Pikachu);
        }
    }

    private static void moveToLast(LinkedList<String> list, String Snorlax) {
        int index = list.indexOf(Snorlax);
        int indexOfPikachu = list.indexOf("Pikachu");
        String lastElement = list.getLast();
        if(lastElement.equals("Snorlax")) return;
        if (index != -1) {
            if(indexOfPikachu > index) {
                list.set(index, lastElement);
                list.set(list.size() - 1, Snorlax);
            } else {
                list.remove(index);
                list.addLast(Snorlax);
            }
        }
    }

    private static void moveToPikachu(LinkedList<String> list, String Jigglypuff) {
        int index = list.indexOf(Jigglypuff);
        int indexOfPikachu = list.indexOf("Pikachu");
        if(index == -1) return;
        if(indexOfPikachu == -1) {
            list.remove(index);
            list.add((list.size() + 1) / 2, Jigglypuff);
            return;
        }
        String elementBeforePika = list.get(indexOfPikachu - 1);
        int indexOfElementBeforePika = list.indexOf(elementBeforePika);
        if (index != -1) {
            if((indexOfPikachu - index) == 1) return;
            else {
                list.set(index, elementBeforePika);
                list.set(indexOfElementBeforePika, Jigglypuff);
            }
        }moveToCenter(list, "Pikachu");
    }

    private static void avoidCharmander(LinkedList<String> list, String Bulbasaur, String Charmander) {
        int index1 = list.indexOf(Bulbasaur);
        int index2 = list.indexOf(Charmander);
        if(index1 == -1 || index2 == -1) return;
        int indexOfMachop = list.indexOf("Machop");
        if (Math.abs(index1 - index2) == 1) {
            // Swap the positions of the Pokemon with Machop
            if(index1 == (list.size() - 2)) { 
                list.set(index1, "Machop");
                list.set(indexOfMachop, Bulbasaur);
            } else {
                list.set(index2, "Machop");
                list.set(indexOfMachop, Charmander);
            }
        }moveToCenter(list, "Pikachu");
    }

    private static void moveToSnorlax(LinkedList<String> list, String Machop) {
        int index = list.indexOf(Machop);
        int indexOfSnorlax = list.indexOf("Snorlax");
        if(indexOfSnorlax == -1) return;
        if((indexOfSnorlax - index) != 1) {
            if (index != -1) {
                list.remove(index);
                list.add(indexOfSnorlax-1, Machop);
            }
        }moveToCenter(list, "Pikachu");
    }

    // Methods to handle invalid combination
    public static boolean checkCondition1(ArrayList<String> arr) {
        boolean hasBulbasaur = arr.contains("Bulbasaur");
        boolean hasCharmander = arr.contains("Charmander");
        return arr.size() == 2 && hasBulbasaur && hasCharmander;
    }

    public static boolean checkCondition2(ArrayList<String> arr) {
        boolean hasBulbasaur = arr.contains("Bulbasaur");
        boolean hasCharmander = arr.contains("Charmander");
        boolean hasEeveeOrSnorlax = arr.contains("Eevee") || arr.contains("Snorlax");
        return arr.size() == 3 && hasBulbasaur && hasCharmander && hasEeveeOrSnorlax;
    }

    public static boolean checkCondition3(ArrayList<String> arr) {
        boolean hasBulbasaur = arr.contains("Bulbasaur");
        boolean hasCharmander = arr.contains("Charmander");
        boolean hasEevee = arr.contains("Eevee");
        boolean hasSnorlax = arr.contains("Snorlax");
        return arr.size() == 4 && hasBulbasaur && hasCharmander && hasEevee && hasSnorlax;
    }

    public static boolean checkCondition4(ArrayList<String> arr) {
        boolean hasBulbasaur = arr.contains("Bulbasaur");
        boolean hasCharmander = arr.contains("Charmander");
        boolean hasEevee = arr.contains("Eevee");
        boolean hasMachop = arr.contains("Machop");
        boolean hasSnorlax = arr.contains("Snorlax");
        return arr.size() == 5 && hasBulbasaur && hasCharmander && hasEevee && hasMachop && hasSnorlax;
    }

    public static boolean checkCondition5(ArrayList<String> arr) {
        boolean hasBulbasaur = arr.contains("Bulbasaur");
        boolean hasCharmander = arr.contains("Charmander");
        boolean hasMachop = arr.contains("Machop");
        boolean hasSnorlax = arr.contains("Snorlax");
        return arr.size() == 4 && hasBulbasaur && hasCharmander && hasMachop && hasSnorlax;
    }

    public static boolean checkCondition6(ArrayList<String> arr) {
        Set<String> uniqueSet = new HashSet<>(arr);
        return uniqueSet.size() == arr.size();
    }              
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

