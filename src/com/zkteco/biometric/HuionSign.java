package com.zkteco.biometric;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
    import java.util.List;
	 
	 
	public class HuionSign 
	{
	    /*public static void main(String[] args) 
	        {*/
	               /* JFrame projectFrame = new JFrame();
	                projectFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	                JPanel projectPanel = new JPanel();
	                JPanel btnPanel = new JPanel();
	                JButton encode = new JButton("ENCODE");
	                JButton decode = new JButton("DECODE");
	 
	                encode.addActionListener(new ButtonListener());
	                decode.addActionListener(new ButtonListener());
	 
	                projectFrame.setSize(SigFrameWidth,SigFrameHeight);
	                projectFrame.setTitle("ONLINE SIGNATURE STEGANOGRAPHY");  
	                projectFrame.setResizable(true);
	                projectPanel.setLayout(new FlowLayout());
	 
	                JLabel stego = new JLabel("ONLINE SIGNATURE STEGANOGRAPHY SYSTEM");
	                stego.setFont(new Font("Tahoma", Font.BOLD, 20));
	 
	                projectFrame.add(projectPanel);
	                projectFrame.add(btnPanel, BorderLayout.PAGE_END);
	                projectPanel.add(stego);
	                btnPanel.add(encode);
	                btnPanel.add(decode);
	                projectFrame.setVisible(true);*/
	            //    new encodePane();
	                
	        //}
	 
	        static class ButtonListener implements ActionListener   
	 {         
	  public void actionPerformed(ActionEvent ae) 
	  {
	   if(ae.getActionCommand().equals("ENCODE"))
	   {
	    new encodePane();
	   }
	   if(ae.getActionCommand().equals("DECODE"))
	   {
	    new decodePane();
	   }
	 
	  }
	 }
	}
	 
	 
	        class encodePane
	        {
	 
	           private static final int SigFrameWidth = 600;
			private static final int SigFrameHeight = 300;

			public encodePane() 
	            {
	        	   
	        	   String SignatureAssignant="HAGABIMANA DANIEL";	
	        	   
	  final JFrame encodeFrame = new JFrame();
	  			
	                Container content = encodeFrame.getContentPane();
	                content.setLayout(new BorderLayout());
	                JPanel encodePanel = new JPanel();
	                JPanel buttonPanel = new JPanel();
	 
	                final PadDraw drawPad = new PadDraw();
	                content.add(drawPad, BorderLayout.CENTER);
	 
	                JButton cancelBtn = new JButton("CANCEL");
	                cancelBtn.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                encodeFrame.dispose();
	                }
	                });
	 
	                JButton proceedBtn = new JButton("PROCEED");
	                proceedBtn.addActionListener(new ActionListener() { 
	                public void actionPerformed(ActionEvent e){
	                   //  new encode1Pane();
	                    // encodeFrame.dispose();
	                     System.out.println("Proceed");
	                     PadDraw.saveSignature();
	                     }   
	                });  
	 
	                JButton clearButton = new JButton("CLEAR");
	                //creates the clear button and sets the text as "Clear"
	                clearButton.addActionListener(new ActionListener(){
	                        public void actionPerformed(ActionEvent e){
	                                drawPad.clear();
	                        }
	                });
	 
	                JLabel Sign = new JLabel("Signature For: " + SignatureAssignant
		        	   , SwingConstants.CENTER);
	                Sign.setVerticalAlignment(SwingConstants.TOP);
	              
	               // proceedBtn.addActionListener(new Button1Listener());
	 
	                encodeFrame.setTitle("ACMIS SIGNATURE");
	                encodeFrame.setLocationRelativeTo(null);   
	                encodeFrame.setSize(SigFrameWidth,SigFrameHeight); 
	                encodeFrame.setResizable(true);
	                encodeFrame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-SigFrameWidth/2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-SigFrameHeight/2));
	                encodeFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("data/images/icons/ic_title1.png"));
	                
	                encodePanel.setLayout(new FlowLayout());
	                encodePanel.add(Sign);
	 
	                encodeFrame.add(encodePanel, BorderLayout.NORTH);
	                encodeFrame.add(buttonPanel, BorderLayout.SOUTH);
	 
	                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
	                buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	                buttonPanel.add(Box.createHorizontalGlue());
	                buttonPanel.add(clearButton);
	                buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
	                buttonPanel.add(cancelBtn);
	                buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
	                buttonPanel.add(proceedBtn);
	 
	                Sign.setFont(new Font("Calibri", Font.BOLD, 16));
	                encodeFrame.setVisible(true);
	 }
	 
	 
	        /*static class Button1Listener implements ActionListener 
	  {   
	   public void actionPerformed(ActionEvent ae)  
	   {
	    if(ae.getActionCommand().equals("PROCEED"))
	    {
	                                    new encode1Pane();
	                                }
	 
	                        }
	  }*/
	       }
	 
	       final class PadDraw extends JComponent{
	            static int signatureWidth = 600;
	            static int signatureHeight = 500;
	    	   
	            Image image;
	            static BufferedImage bufferImage = new BufferedImage(signatureWidth,signatureHeight, BufferedImage.TYPE_INT_ARGB);
	            
	            Graphics2D graphics2D;
	            int currentX, currentY, oldX, oldY;
	            float fontPixels=1.5f;
	            
	            private List<Point> points = new ArrayList<>();
	 
	            public PadDraw(){
	                setDoubleBuffered(false);
	                
	                
	                addMouseListener(new MouseAdapter(){
	                    public void mousePressed(MouseEvent e){
	                        oldX = e.getX();
	                        oldY = e.getY();
	                        
	                        points.add(new Point(e.getX(), e.getY()));
	                        graphics2D.setColor(Color.black);
	                        //graphics2D.setComposite(AlphaComposite.Src);
	                 
	                        graphics2D.setStroke(new BasicStroke(fontPixels,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	                       // graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                        for (Point p : points){
	     	            	   graphics2D.fillOval(p.x, p.y, (int)(fontPixels), (int) fontPixels);
	     	            	   repaint();
	     	               }
	                    }
	                   /* public void mouseClicked(MouseEvent e){
	                        int mousex = e.getX();
	                        int mouseY = e.getY();
	                        graphics2D.fillOval(mousex, mouseY, 3, 3);
	                    }*/
	                });
	            addMouseMotionListener(new MouseMotionAdapter(){
	                public void mouseDragged(MouseEvent e){
	                    currentX = e.getX();
	                    currentY = e.getY();
	                    if(graphics2D!=null)
	                        graphics2D.drawLine(oldX, oldY, currentX, currentY);
	                    repaint();
	                    oldX = currentX;
	                    oldY = currentY; 
	                }
	                
	            });
	            
	          
	            }
	       public void paintComponent(Graphics g){
	           if(image==null){
	               image = createImage(getSize().width, getSize().height);
	               graphics2D = (Graphics2D)image.getGraphics();
	               graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	               
	               
	               clear();
	 
	               }
	           g.drawImage(image,0,0,null);
	         
	           
	           //**************
	           if (image instanceof BufferedImage){/*return (BufferedImage) img;*/}
	            // Draw the image on to the buffered image
	           Graphics2D bufferGr = bufferImage.createGraphics();
	           bufferGr.drawImage(image, 0, 0, null);
	           bufferGr.dispose();
	           
	           // g.drawImage(bufferImage,0,0,null);
	           //************
	           
	          

	       }
	 
	       public void clear(){
	           graphics2D.setPaint(new Color(255,255,255,255));
	    	   //graphics2D.setComposite(AlphaComposite.Clear);
	           graphics2D.fillRect(0,0,getSize().width, getSize().height);
	           graphics2D.setPaint(Color.black);
	           points.clear();
	           repaint();
	 
	 
	       }
	        //***************************************
	       static void saveSignature() {
	    	String Signatory = "HAGABIMANA";
	    	String SignatureID = "2137288";
	    	System.out.println("Signature saved");
	    	   
	    	 //RenderedImage rendImage = bufferImage;
	    	 
	    	 
	    	 //*******************************************remove background from image
	    	 
	    	    
	    	    
	    	    BufferedImage RealSignature = makeTransparent(bufferImage);
	    	 
	    	    RenderedImage SignRend = RealSignature;
	    	 

	    	    String ImageDirectory= "E:\\Java-workspace\\Bio Identity\\data\\images\\Signature\\";
	    	    File currentSignature = new File(ImageDirectory+"Sign_"+Signatory+"_"+SignatureID+".png");
	    	    
	    	    /* try {
					ImageIO.write(rendImage, "png", currentSignature);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	    	   
	       
	          final ImageIcon saveIconOk =  new ImageIcon(new ImageIcon("data/images/icons/ic_Sign_savSucc.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));  
	          final ImageIcon saveIconNotOk =  new ImageIcon(new ImageIcon("data/images/icons/ic_Sign_savFail.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));  
	           
	          //String saveName = JOptionPane.showInputDialog("Please enter a name for the S1Mian graphical output file: ");
	            //every run is unique so do not allow the user to overwrite previously saved files...
	           
	          if(!currentSignature.exists())
	            {
	                try{

	                	
	                    ImageIO.write(SignRend, "png", currentSignature);
	                    JOptionPane.showMessageDialog(null, "Signature for "+Signatory+" has been created successfully", "signature Saved", JOptionPane.INFORMATION_MESSAGE, saveIconOk);
	                    // set signature window the current captured signature
	                    ImageIcon SignatureScanImage= new ImageIcon(new ImageIcon("data/images/Signature/Sign_HAGABIMANA_2137288.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)); 
	                    //ImageIcon SignatureScanImage= new ImageIcon("data/images/Signature/Sign_HAGABIMANA_2137288.png");
	                    BiometricSystem.SignatureScanImageButtonLabel.setIcon(SignatureScanImage);
	          	    	// fetch the signature and place it in window and card
	                }
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }


	            }
	            else{

	                JOptionPane.showMessageDialog(null, "Signature for "+Signatory+" already exists!", "signature exists", JOptionPane.INFORMATION_MESSAGE, saveIconNotOk);
	            }

	          
	    	   
	       }
	       //*****************************
	       
	       private static BufferedImage makeTransparent(BufferedImage img) {
	    	   
	    	    final Color backColor = new Color(255,255,255);
	    	    final int THRESHOLD = 35;
	    	    final int TRANSPARENT = 0;  // 0x00000000
	    	    int width = img.getWidth(null),
	    	              height = img.getHeight(null);

	    	            /*BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    	            Graphics g = image.getGraphics();
	    	            g.drawImage(img, 0, 0, null);*/
	    	    
	    	    System.out.println("before: " + img.getRGB(0, 0));
	            for (int y = 0; y < height; y++) {
	                for (int x = 0; x < width; x++) {
	                    int pixel = img.getRGB(x, y);
	                    Color color = new Color(pixel);

	                    int dr = Math.abs(color.getRed()   - backColor.getRed()),
	                    dg = Math.abs(color.getGreen() - backColor.getGreen()),
	                    db = Math.abs(color.getBlue()  - backColor.getBlue());

	                    if (dr < THRESHOLD && dg < THRESHOLD && db < THRESHOLD) {
	                    	img.setRGB(x, y, TRANSPARENT);
	                    }
	                }
	            }
	            System.out.println("   after: " + img.getRGB(0, 0));
	            
	            BufferedImage trimmed= trim(img);

	          /* File file1 = new File("E:\\image-transparency\\Image Filter\\data\\images\\signatures\\new.png");
	            try {
					ImageIO.write(trimmed, "png", file1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				//return img;
				return trimmed;
	       }
	       
	       
	       private static BufferedImage trim(BufferedImage img) {
	           int width = img.getWidth();
	           int height = img.getHeight();

	           int top = height / 2;
	           int bottom = top;

	           int left = width / 2 ;
	           int right = left;

	           for (int x = 0; x < width; x++) {
	               for (int y = 0; y < height; y++) {
	                   if (isFg(img.getRGB(x, y))){

	                       top    = Math.min(top, y);
	                       bottom = Math.max(bottom, y);

	                       left   = Math.min(left, x);
	                       right  = Math.max(right, x);

	                   }
	               }
	           }

	           return img.getSubimage(left, top, right - left, bottom - top);
	       }

	       private static boolean isFg(int v) {
	           Color c = new Color(v);
	           return(isColor((c.getRed() + c.getGreen() + c.getBlue())/2));
	       }

	       private static boolean isColor(int c) {
	           return c > 0 && c < 255;
	       }
	 
	}
	 
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       class encode1Pane
	       {
	           private static final int SigFrameWidth = 600;
			private static final int SigFrameHeight = 500;

			public encode1Pane()
	           {
	              final JFrame encode1Frame = new JFrame();
	              JPanel Enc = new JPanel();
	              JPanel enc = new JPanel();
	              JPanel Btn = new JPanel();
	              JButton prcd = new JButton ("ENCODE");
	              JLabel sec = new JLabel ("Your message");
	              final JTextField secText = new JTextField ("");
	              secText.setPreferredSize(new Dimension (500, 100));
	              JLabel name = new JLabel ("Set the file name");
	              final JTextField name2 = new JTextField ();
	              name2.setPreferredSize(new Dimension (200, 20));
	              JButton cncl = new JButton ("CANCEL");
	              cncl.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                new encodePane();
	                encode1Frame.dispose();
	                }
	                });
	 
	 
	              encode1Frame.setTitle("ENCODE SECTION");
	              encode1Frame.setLocationRelativeTo(null);   
	              encode1Frame.setSize(SigFrameWidth,SigFrameHeight);
	              encode1Frame.setResizable(true);
	 
	              Enc.add(sec);
	              Enc.add(secText);
	              secText.setBounds(30, 50, 200, 25);
	              enc.add(name);
	              enc.add(name2);
	              name2.setBounds(30, 50, 200, 25);
	              encode1Frame.add(Btn, BorderLayout.SOUTH);
	              encode1Frame.add(Enc, BorderLayout.NORTH);
	              encode1Frame.add(enc, BorderLayout.CENTER);
	              encode1Frame.setVisible(true);   
	 
	              Btn.setLayout(new BoxLayout(Btn, BoxLayout.LINE_AXIS));
	              Btn.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	              Btn.add(Box.createHorizontalGlue());
	              Btn.add(cncl);
	              Btn.add(Box.createRigidArea(new Dimension(10, 0)));
	              Btn.add(prcd);
	 
	 
	              sec.setFont(new Font("Serif", Font.ITALIC, 14));
	              name.setFont(new Font("Serif", Font.ITALIC, 14));
	              
	              //cncl.addActionListener(new Button2Listener());
	              prcd.addActionListener(new Button2Listener());
	 
	       }
	 
	           static class Button2Listener implements ActionListener 
	  {   
	   public void actionPerformed(ActionEvent ae)  
	   {
	    if(ae.getActionCommand().equals("Proceed"))
	    {
	      //new encodeOutput();
	    	//System.out.println("proceed");
	    	//new PadDraw().saveSignature();
	    	
//**************************************************************************
	    	 
	    	
//*************************************************************************
	    	
	    }    
	     }
	      }
	       }
	 
	 
	        class decodePane 
	            {
	               public decodePane() 
	                {
	                    JFrame decodeFrame = new JFrame();
	                  //  decodeFrame.add(new UploadImage());
	 
	                }}
