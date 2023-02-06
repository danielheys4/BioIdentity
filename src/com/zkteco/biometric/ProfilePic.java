package com.zkteco.biometric;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.AbstractBorder;
public class ProfilePic extends JLabel {
    private AbstractBorder circleBorder = new CircleBorder();
    private int lineBorder = 1;
    private Color lineColor = Color.BLACK;

    public ProfilePic() {
    	
    	//ProfilePic circleProfile = new ProfilePic();
    	
    /*	JFrame fr=new JFrame();
    	fr.setSize(500,500);
    	fr.setLocationRelativeTo(null);
    	fr.setVisible(true);
    	fr.setTitle("frame");*/
    	
    	/*JPanel panPic=new JPanel();
    	panPic.setBounds(0,0,200,200);*/
    	//fr.setLocation(null);
    	//panPic.setVisible(true);
    	//fr.setTitle("frame");
    	
        Dimension d = new Dimension(50, 50);
        setSize(d);
        setPreferredSize(d);
        //setText("CLabel");
        setOpaque(true);
        //setHorizontalAlignment(CENTER);
        setVisible(true);
        setBorder(circleBorder);
        setBackground(null);
        //System.out.println("profile PIC");
       // panPic.add(circleProfile);
        
       // fr.add(panPic);
    }
    //Warna Border
    public Color getLineColor() {
        return lineColor;
    }
    public void setLineColor(Color color) {
        circleBorder = new CircleBorder(color, lineBorder);
        lineColor = color;
        setBorder(circleBorder);
    }
    //Ukuran Border
    public int getLineBorder() {
        return lineBorder;
    }
    public void setLineBorder(int lineBorder) {
        circleBorder = new CircleBorder(lineColor, lineBorder);
        this.lineBorder = lineBorder;
        setBorder(circleBorder);
    }
}





