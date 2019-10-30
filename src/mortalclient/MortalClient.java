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
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author Ksa
 */
public class MortalClient extends javax.swing.JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    Thread gameFlowThread;
    HashMap<String, Player> players = new HashMap<String, Player>();
    Socket socketPlayer;
    BufferedReader in;
    PrintWriter out;

    boolean key_r = false;

    public MortalClient() {
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        player = new Player();
//        player.setup();
//        getContentPane().add(player);

        connect();
        repaint();
        gameFlowThread = new Thread(this);
        gameFlowThread.start();
    }

    public void connect() { // conexão com servidro
        try {
            socketPlayer = new Socket("localhost", 8880);
            in = new BufferedReader(new InputStreamReader(socketPlayer.getInputStream()));
            out = new PrintWriter(socketPlayer.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameFlow() {
        String command;
        try {
            while (true) {
                command = in.readLine();
                //2_194_82_false_90127

                // System.out.println("ReadLine: " + command);
                String data[] = command.split("\\_");

                String id = data[0];
                String x = data[1];
                String y = data[2];
                String dir = data[3];

                if (!players.containsKey(id)) {
                    Player p = new Player();
                    p.x = Integer.parseInt(x);
                    p.y = Integer.parseInt(y);

                    if (dir.equals("true")) {
                        p.setIconRight();

                    }
                    if (key_r = true) {
                        p.setIconStoppedD();
                    } else {
                        p.setIconLeft();
                    }
                    p.setup();
                    p.move();

                    getContentPane().add(p);
                    players.put(id, p);

                } else {
                    Player p = players.get(id);
                    p.x = Integer.parseInt(x);
                    p.y = Integer.parseInt(y);

                    if (dir.equals("true")) {
                        if (key_r = true) {
                        p.setIconStoppedD();
                    }
                        p.setIconRight();
                    } else {
                        p.setIconLeft();
                    }
                    p.move();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed // Identificar a tecla precionada

        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            out.println("PR_R");
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            out.println("PR_L");
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            out.println("PR_U");
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            out.println("PR_D");
        }
    }

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            key_r = false;
            out.println("RE_R");

            System.out.println("formKeyPressed soltando botão");
        }

        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            key_r = false;
            out.println("RE_L");
        }

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            key_r = false;
            out.println("RE_U");
        }

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            key_r = false;
            out.println("RE_D");
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void run() {
        gameFlow();
    }

    public static void main(String args[]) {
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MortalClient().setVisible(true);
            }
        });
    }

}
