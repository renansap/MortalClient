///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mortalclient;
//
//import java.awt.Image;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//
///**
// *
// * @author Ksa
// */
//public class Player extends JLabel {
//
//    public int x = 0, y = 0, id, pontos;
//    public char lado = 'R';
//    ImageIcon walkL;
//    ImageIcon walkR;
//    ImageIcon stoppedD; //Parada Direita
//    ImageIcon stoppedE;//Parada Esquerda
//
//       public void setup() {
//        setText("12");
//
//       stoppedD = new ImageIcon(new ImageIcon(getClass().getResource("p_d.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
//        stoppedE = new ImageIcon(new ImageIcon(getClass().getResource("p_e.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
//        walkR = new ImageIcon(new ImageIcon(getClass().getResource("c_d.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
//        walkL = new ImageIcon(new ImageIcon(getClass().getResource("c_e.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
//
//        setBounds(x, y, 90, 127);
//        setIcon(walkR);
//    }
//
//    public void move() {
//
////
////        System.out.println("******************************************");
////        System.out.println(x);
////        System.out.println(y);
//        setBounds(x, y, 90, 127);
//    }
////
////    public void setIconRight() {;
////        setIcon(walkR);
////    }
////
////    public void setIconLeft() {
////        setIcon(walkL);
////    }
//
//
//    //implementando
//     public void setIconRight() {
//        setIcon(walkR);
//    }
//
//    public void setIconLeft() {
//        setIcon(walkL);
//    }
//
//    public void setIconStoppedD() {
//        setIcon(stoppedD);
//    }
//
//    public void setIconStoppedE() {
//        setIcon(stoppedE);
//    }
//
//}
package mortalclient;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {

    public int x = 50, y = 50;

    ImageIcon walkL;
    ImageIcon walkR;
    ImageIcon walkU;
    ImageIcon walkD;
//    ImageIcon stoppedD; //Parada Direita
//    ImageIcon stoppedE;//Parada Esquerda

    public void setup() {
        setText("12");
        walkR = new ImageIcon(new ImageIcon(getClass().getResource("c_d.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        walkL = new ImageIcon(new ImageIcon(getClass().getResource("c_e.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
//        stoppedD = new ImageIcon(new ImageIcon(getClass().getResource("p_d.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
//        stoppedE = new ImageIcon(new ImageIcon(getClass().getResource("p_e.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
//        walkR = new ImageIcon(new ImageIcon("./walk_r.gif").getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
//        walkL = new ImageIcon(new ImageIcon("./walk_l.gif").getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        setBounds(x, y, 90, 127);
        setIcon(walkR);
    }

    public void move() {
        setBounds(x, y, 90, 127);
    }

    public void setIconRight() {
        setIcon(walkR);
    }

    public void setIconLeft() {
        setIcon(walkL);
    }
}
