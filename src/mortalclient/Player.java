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
    ImageIcon stoppedD; //Parada Direita
    ImageIcon stoppedE;//Parada Esquerda

    public void setup() {
        setText("12");
        stoppedD = new ImageIcon(new ImageIcon(getClass().getResource("p_d.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        stoppedE = new ImageIcon(new ImageIcon(getClass().getResource("p_e.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        walkR = new ImageIcon(new ImageIcon(getClass().getResource("c_d.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        walkL = new ImageIcon(new ImageIcon(getClass().getResource("c_e.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));

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
    public void setIconStoppedD() {
        setIcon(stoppedD);
    }

    public void setIconStoppedE() {
        setIcon(stoppedE);
    }
}
