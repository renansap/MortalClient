/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mortalclient;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ksa
 */
public class MortalClient extends javax.swing.JFrame implements Runnable {

    Boolean keyRight = false, keyLeft = false, keyUp = false, keyDown = false;
    public static final int SPEED = 8;

    /**
     * @param args the command line arguments
     */
    private javax.swing.JLabel lblScore;

    public MortalClient() {
        initComponents();
    }
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }

    private void initComponents() {

        lblScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        lblScore.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(262, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(531, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        connect();
        repaint();//ler sobre
        gameFlowThread = new Thread(this);
        gameFlowThread.start();
    }//GEN-LAST:event_formWindowOpened 

    public void connect() {
        try {
            s = new Socket("localhost", 8880);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameFlow() {
        String command;
        boolean lEsq = false;
        boolean lDir = true;
        try {
            while (true) {

                command = in.readLine();
                String data[] = command.split("\\_");

                String id = data[0];
                String x = data[1];
                String y = data[2];
                String lado = data[5];
                String pontos = data[6];

                if (dados.containsKey(id)) {

                    Player jogador = dados.get(id);
                    jogador.x = Integer.parseInt(data[1]);
                    jogador.y = Integer.parseInt(data[2]);

                    //Calcula e mostra placar
                    String key = dados.keySet().toArray()[0].toString();

                    System.out.println(key);

                    if (key.equals(id)) {
                        j1 = Integer.parseInt(pontos);
                    } else {
                        j2 = Integer.parseInt(pontos);
                    }

                    lblScore.setText("<html><h3>Jogador 1 : " + j1 + "<br>Jogador 2 : " + j2 + "</html>");

//                    if ("R".equals(lado)) {
//                        jogador.setIconRight();
//                    } else {
//                        jogador.setIconLeft();
//                    }
                    if (keyRight) {//move direita
                        jogador.setIconRight();
                        jogador.x += SPEED;
                        lDir = true;
                        lEsq = false;
                    }

                    if (keyLeft) {//move esquerda
                        jogador.setIconLeft();
                        jogador.x -= SPEED;
                        lEsq = true;
                        lDir = false;
                    }

                    if (keyUp) {
                        jogador.y -= SPEED;
                    }

                    if (keyDown) {
                        jogador.y += SPEED;
                    }

                    if (!(keyDown || keyUp || keyLeft || keyRight)) {
                        if (lDir) {
                            jogador.setIconStoppedD();
                        }
                        if (lEsq) {
                            jogador.setIconStoppedE();
                        }
                    }
                    jogador.move();

                } else {
                    Player jogador = new Player();
                    jogador.setup();
                    getContentPane().add(jogador);
                    dados.put(id, jogador);

                    jogador.x = Integer.parseInt(data[1]);
                    jogador.y = Integer.parseInt(data[2]);
//                    if ("R".equals(lado)) {
//                        jogador.setIconRight();
//                    } else {
//                        jogador.setIconLeft();
//                    }
                    jogador.move();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
//         private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
//        // TODO add your handling code here:
//        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
//            out.println("PR_R");
//            
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
//            out.println("PR_L");
//            
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_UP) {
//            out.println("PR_U");
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            out.println("PR_D");
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
//            out.println("PUNCH");
//        }
//        
//    }//GEN-LAST:event_formKeyPressed

    //aplicando
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                keyRight = true;
                break;
            case KeyEvent.VK_LEFT:
                keyLeft = true;
                break;
            case KeyEvent.VK_UP:
                keyUp = true;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = true;
                break;
        }

    }//GEN-LAST:event_formKeyPressed

//    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
//        // TODO add your handling code here:
//        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
//            key_r = false;
//            out.println("RE_R");
//
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
//            key_r = false;
//            out.println("RE_L");
//
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_UP) {
//            key_r = false;
//            out.println("RE_U");
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            key_r = false;
//            out.println("RE_D");
//        }
//    }//GEN-LAST:event_formKeyReleased
// aplicando
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here elias
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                keyRight = false;
                break;
            case KeyEvent.VK_LEFT:
                keyLeft = false;
                break;
            case KeyEvent.VK_UP:
                keyUp = false;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = false;
                break;
        }
    }//GEN-LAST:event_formKeyReleased

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        gameFlow();
    }

    /**
     *
     * @param args
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
            java.util.logging.Logger.getLogger(MortalClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MortalClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MortalClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MortalClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MortalClient().setVisible(true);
            }
        });
    }

    Thread gameFlowThread;
    HashMap<String, Player> dados = new HashMap<String, Player>();
    Socket s;
    BufferedReader in;
    PrintWriter out;
    boolean key_r = false;
    int j1 = 0, j2 = 0;

}
