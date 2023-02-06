package com.zkteco.biometric;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import com.topaz.sigplus.*;
import gnu.io.*;
import java.io.*;
import javax.swing.*;

public class AcmisTopazSignature extends Frame
   {
   SigPlus              sigObj = null;
   Thread               eventThread;

   /*public static void main( String Args[] )
	  {
	  AcmisTopazSignature signature = new AcmisTopazSignature();
	  signature.setSize(800,300);
	  signature.setVisible(true);
      signature.setBackground(Color.lightGray);
	  }*/
   
   /*public void launchTopazFrame(){
	      AcmisTopazSignature signature = new AcmisTopazSignature();
		  signature.setSize(800,300);
		  signature.setVisible(true);
	      signature.setBackground(Color.lightGray);
   }*/
   
   private static final int TopazSigFrameWidth = 600;
   private static final int TopazSigFrameHeight = 300;

   

	public AcmisTopazSignature()
		{
		setSize(TopazSigFrameWidth,TopazSigFrameHeight);
		setVisible(true);
		setBackground(Color.lightGray);
		setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-TopazSigFrameWidth/2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-TopazSigFrameHeight/2));
		setIconImage(Toolkit.getDefaultToolkit().getImage("data/images/icons/ic_title1.png"));
		
        GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		setLayout(gbl);
		Panel controlPanel = new Panel();
		setConstraints(controlPanel, gbl, gc, 0, 0,
		GridBagConstraints.REMAINDER, 1, 0, 0,
		GridBagConstraints.CENTER,
		GridBagConstraints.NONE,0, 0, 0, 0);
		add(controlPanel, gc);



		controlPanel.add(connectionChoice);
		controlPanel.add(connectionTablet);
		
		Button startButton = new Button("START");
		controlPanel.add(startButton);

		Button stopButton = new Button("STOP");
		controlPanel.add(stopButton);
		
		Button clearButton = new Button("CLEAR");
		controlPanel.add(clearButton);

		Button saveSigButton = new Button("SAVE SIG");
		controlPanel.add(saveSigButton);

		Button loadSigButton = new Button("LOAD SIG");
		controlPanel.add(loadSigButton);

                controlPanel.add(txtPath);

		Button okButton = new Button("QUIT");
		controlPanel.add(okButton);

		initConnection();

		try
			{
			ClassLoader cl = (com.topaz.sigplus.SigPlus.class).getClassLoader();
	  		sigObj = (SigPlus)Beans.instantiate( cl, "com.topaz.sigplus.SigPlus" );




		setConstraints(sigObj, gbl, gc, 0, 1,
		GridBagConstraints.REMAINDER, 1, 1, 1,
		GridBagConstraints.CENTER,
		GridBagConstraints.BOTH, 5, 0, 5, 0);
		add(sigObj, gc);
		sigObj.setSize(100,100);
                sigObj.clearTablet();
		setTitle( "ACMIS SIGNATURE CAPTURE" );
		
		 
	   okButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			    sigObj.setTabletState(0);
    		            System.exit(0);
		   }
	  });

	   startButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			    sigObj.setTabletState(0);
			    sigObj.setTabletState(1);
		   }
	  });

	  stopButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			    sigObj.setTabletState(0);
		   }
	  });

	  clearButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			    sigObj.clearTablet();
		   }
	  });

	  saveSigButton.addActionListener(new ActionListener(){
	     public void actionPerformed(ActionEvent e){
	        boolean blnExport=false;
	        String path=txtPath.getText();
                int pathlength=path.length();
                if(pathlength!=0)
                {
		   sigObj.autoKeyStart();
		   sigObj.setAutoKeyData("Sample Encryption Data");
		   sigObj.autoKeyFinish();
                   sigObj.setEncryptionMode(1);
	           blnExport = sigObj.exportSigFile(txtPath.getText());
                   if (blnExport==false)
                     {
                        System.out.println("Error writing SIG file");
                     }
                 }
                 else
                 {
                    System.out.println("Please type in full path information to save file");
		 }
              }
	  });


	  loadSigButton.addActionListener(new ActionListener(){
	     public void actionPerformed(ActionEvent e){
	        boolean blnImport=false;
	        String path=txtPath.getText();
                int pathlength=path.length();
                if(pathlength!=0)
                {
 	           sigObj.autoKeyStart();
		   sigObj.setAutoKeyData("Sample Encryption Data");
		   sigObj.autoKeyFinish();
                   sigObj.setEncryptionMode(1);
		   blnImport = sigObj.importSigFile(txtPath.getText());
                if (blnImport==false)
                     {
                        System.out.println("Error reading SIG file");
                     }
                 }
                 else
                 {
                    System.out.println("Please type in full path information to load file");
		 }
	      }
	  });


          //txtPath.addTextListener(new TextListener(){
		  //public void textValueChanged(TextEvent e){
                            //System.out.println(txtPath.getText());
		  //}
	  //});



	 connectionTablet.addItemListener(new ItemListener(){
		  public void itemStateChanged(ItemEvent e){
			    
                        if(connectionTablet.getSelectedItem() != "SignatureGemLCD4X3"){
                           sigObj.setTabletModel(connectionTablet.getSelectedItem());
                        }
                        else{
                           sigObj.setTabletModel("SignatureGemLCD4X3New"); //properly set up LCD4X3
                        }
                     
		  }
	  });


	 connectionChoice.addItemListener(new ItemListener(){
		  public void itemStateChanged(ItemEvent e){
			    
                        if(connectionChoice.getSelectedItem() != "HSB"){
  	                   sigObj.setTabletComPort(connectionChoice.getSelectedItem());
                        }
                        else{
                           sigObj.setTabletComPort("HID1"); //properly set up HSB tablet
                        }
                            
		  }
	  });

			addWindowListener( new WindowAdapter()
				{
				public void windowClosing( WindowEvent we )
					{
					sigObj.setTabletState( 0 );
					System.exit( 0 );
					}

				public void windowClosed( WindowEvent we )
					{
					System.exit( 0 );
					}
				} );

			sigObj.addSigPlusListener( new SigPlusListener()
				{
				public void handleTabletTimerEvent( SigPlusEvent0 evt )
					{
					}

				public void handleNewTabletData( SigPlusEvent0 evt )
					{
					}

				public void handleKeyPadData( SigPlusEvent0 evt )
					{
					}
				} );

                        setVisible(true);
                        
                        sigObj.setTabletModel("SignatureGemLCD4X3New");
                        sigObj.setTabletComPort("HID1");
                        
                        sigObj.setTabletState(1); //enable tablet at the start

 

			}
		catch ( Exception e )
			{
			return;
			}
			
		}


                TextField txtPath = new TextField("data/images/Signature/signature.sig", 30); //C:\\test.sig
      
                Choice connectionChoice = new Choice();   protected String[] connections = 
	        {
	       "HSB",
	       "USB",
		   "COM1", 
		   "COM2", 
		   "COM3", 
		   "COM4",
                    
		     
	        };


                Choice connectionTablet = new Choice();   protected String[] tablets = 
	        {
	        	"SignatureGemLCD4X3",
                   "SignatureGem1X5",
                   "SignatureGem4X5",
      		   "SignatureGemLCD1X5",
       		   
      		   "ClipGem",
      		   "ClipGemLGL", 
	        };


                private void initConnection()
	        {
		   for(int i = 0; i < connections.length; i++)
		   {
			connectionChoice.add(connections[i]);
		   }

		   for(int i = 0; i < tablets.length; i++)
		   {
			connectionTablet.add(tablets[i]);
		   }

	        }

                //Convenience method for GridBagLayout
	        private void setConstraints(
		Component comp,
		GridBagLayout gbl,
	    	GridBagConstraints gc,
	    	int gridx,
	    	int gridy,
	    	int gridwidth,
	    	int gridheight,
	    	int weightx,
	    	int weighty,
	    	int anchor,
	    	int fill,
	    	int top,
	    	int left,
	    	int bottom,
	    	int right)
	    	{
			gc.gridx = gridx;
			gc.gridy = gridy;
			gc.gridwidth = gridwidth;
			gc.gridheight = gridheight;
			gc.weightx = weightx;
			gc.weighty = weighty;
			gc.anchor = anchor;
			gc.fill = fill;
			gc.insets = new Insets(top, left, bottom, right);
			gbl.setConstraints(comp, gc);
	    	}
            }


