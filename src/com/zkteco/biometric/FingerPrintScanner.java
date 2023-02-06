package com.zkteco.biometric;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FingerPrintScanner extends JFrame implements ActionListener{
	
	JButton enroll;
	JButton verify;
	
	
	JButton btnOpen= null;
	JButton btnEnroll = null;
	JButton btnVerify = null;
	JButton btnIdentify = null;
	JButton btnRegImg = null;
	JButton btnIdentImg = null;
	JButton btnClose = null;
	JButton LeftFingerbtnImgArea = null, RightFingerbtnImgArea=null;
	
	//new l-btns

	//end L-btns
	
	private JTextArea textArea;
	private JTextArea FPDataArea;
	private JTextArea LeftFingChosenText, RightFingChosenText;
	
	//the width of fingerprint image
	int fpWidth = 0;
	//the height of fingerprint image
	int fpHeight = 0;
	//for verify test
	private byte[] lastRegTemp = new byte[2048];
	//the length of lastRegTemp
	private int cbRegTemp = 0;
	//pre-register template
	private byte[][] regtemparray = new byte[3][2048];
	//Register
	private boolean bRegister = false;
	//Identify
	private boolean bIdentify = true;
	//finger id
	private int iFid = 1;
	
	private int nFakeFunOn = 1;
	//must be 3
	static int enroll_cnt = 3;
	
	//the index of pre-register function
	private int enroll_idx = 0;
	
	private byte[] imgbuf = null;
	private byte[] template = new byte[2048];
	private int[] templateLen = new int[1];
	
	
	private boolean mbStop = true;
	private long mhDevice = 0;
	private long mhDB = 0;
	private WorkThread workThread = null;
	
	Color FingerPanelBG = new Color(255,255,255,255);
	static JLabel LFingerChosen, RFingerChosen; static JLabel LeftFingChosenImage, RightFingChosenImage;
	public static Icon ic_leftThumbNotOk, ic_leftThumbOk;
	public static Icon ic_leftIndexNotOk, ic_leftIndexOk;
	public static Icon ic_leftMiddleNotOk, ic_leftMiddleOk;
	public static Icon ic_leftRingNotOk, ic_leftRingOk;
	public static Icon ic_leftSmallNotOk, ic_leftSmallOk;
	public static Icon ic_rightSmallOk, ic_rightSmallNotOk;
	public static Icon ic_rightRingOk,ic_rightRingNotOk;
	public static Icon ic_rightMiddleOk,ic_rightMiddleNotOk;
	public static Icon ic_rightIndexOk,ic_rightIndexNotOk;
	public static Icon ic_rightThumbOk,ic_rightThumbNotOk;
	
	public static String StudentName = "Hagabimana Daniel";
	public static String StudentNumber = "213006072";
	public static String StudentRegNo = "13/U/5288/PS";
	public static String StudentColledge = "COCIS";
	public static String StudentUniversity = "MUK";
	public static String StudentCountry = "Uganda";
	
	
	public static final String leftFingerChosen =null, rightFingerChosen =  null;
	public static String LeftFPImgName =  null, RightFPImgName =  null;
	public static String LeftFPImgpath =  null, RightFPImgpath =  null;
	public static String FPImgExt =  null;
	public static String LeftFingerChosenString=null, RightFingerChosenString=null;
	public static String  LeftFingToSave = null, RightFingToSave = null;
	
	
	
    JRadioButtonMenuItem LeftThumb, RightThumb;
    JRadioButtonMenuItem Leftindex, Rightindex;
    JRadioButtonMenuItem LeftMiddle, RightMiddle;
    JRadioButtonMenuItem LeftRing, RightRing;
    JRadioButtonMenuItem LeftSmall, RightSmall;
    
    Boolean RightHandEn = false;
    
    private final static JFrame FPframe = new JFrame();
	
	public void launch(){
		int ActBtnWid =30;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int FBiowindowWidth= (int)(screenSize.width/1.2);
		int FBiowindowHeight= (int)(screenSize.height/1.2);
		int FBiowindowHeightExtd= (int)(screenSize.height/1.2)+ActBtnWid;
		
		int FPWinSep=20;
		int LeftFingPanelWindowWidth = FBiowindowWidth/3-FPWinSep, LeftFingPanelWindowHeight = (int)(FBiowindowHeight/1.1);  // left panel size
		int LeftFingUpperPanelWindowWidth = FBiowindowWidth/3-FPWinSep, LeftFingUpperPanelWindowHeight = FBiowindowHeight/5; // Left upper pane
		int LeftFingBottomPanelWindowWidth = FBiowindowWidth/3-FPWinSep, LeftFingBottomPanelWindowHeight = (int)(FBiowindowHeight/1.47); // Left Downer pane
		
		
		int FPCentalBGPanelWindowWidth = FBiowindowWidth/3-FPWinSep, FPCentalBGPanelWindowHeight = (int)(FBiowindowHeight/1.1); // central panel 
		int FPControlPanelWindowWidth = FBiowindowWidth/3-FPWinSep, FPControlPanelWindowHeight = FBiowindowHeight/5; // central buttons size
		int FPCenterDataPanelWindowWidth = FBiowindowWidth/3-FPWinSep, FPCenterDataPanelWindowHeight = (int)(FBiowindowHeight/1.45); // central data Display
		
		int RightFingPanelWindowWidth = FBiowindowWidth/3-FPWinSep, RightFingPanelWindowHeight = (int)(FBiowindowHeight/1.1);  // right panel size
		int RightFingUpperPanelWindowWidth = FBiowindowWidth/3-FPWinSep, RightFingUpperPanelWindowHeight = FBiowindowHeight/5; // Left upper pane
		int RightFingBottomPanelWindowWidth = FBiowindowWidth/3-FPWinSep, RightFingBottomPanelWindowHeight = (int)(FBiowindowHeight/1.47); // Left Downer pane
		
		//load icons
		int FingerIconWidth = (int)(151/2.5), FingerIconHeight=(int)(271/2.5);
		
		  ic_leftSmallOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftSmallOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		  ic_leftSmallNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftSmallNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		  ic_leftRingOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftRingOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		  ic_leftRingNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftRingNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		  ic_leftMiddleOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftMiddleOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		  ic_leftMiddleNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftMiddleNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		 ic_leftIndexOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftIndexOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 ic_leftIndexNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftIndexNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		 ic_leftThumbOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftThumbOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 ic_leftThumbNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_leftThumbNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		 
		 ic_rightSmallOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightSmallOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 ic_rightSmallNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightSmallNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		 ic_rightRingOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightRingOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 ic_rightRingNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightRingNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		 ic_rightMiddleOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightMiddleOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		  ic_rightMiddleNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightMiddleNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		  ic_rightIndexOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightIndexOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		  ic_rightIndexNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightIndexNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		  ic_rightThumbOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightThumbOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		  ic_rightThumbNotOk= new ImageIcon(new ImageIcon("data/images/icons/fingers/ic_rightThumbNotOk.png").getImage().getScaledInstance(FingerIconWidth, FingerIconHeight, Image.SCALE_DEFAULT)); 
		 
		 
		 
		// left fingerprint panel
		String BioWindowTitleSpace="";
		JPanel LeftFingBGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));
		LeftFingBGPanel.setPreferredSize(new Dimension(LeftFingPanelWindowWidth,LeftFingPanelWindowHeight));
		LeftFingBGPanel.setBackground(FingerPanelBG );
		//LeftFingBGPanel.setBorder(BorderFactory.createLineBorder((new Color(255,255,255,255)),8));
		TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(new Color(0,0,255,100),1), BioWindowTitleSpace+"LEFT HAND");
	    Font font = new Font("Serif", Font.ITALIC, 12);
	    thatBorder2.setTitleColor(Color.black);
	    thatBorder2.setTitleFont(font);
	    LeftFingBGPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.RIGHT,TitledBorder.ABOVE_TOP, font, Color.black)); 
		
	    
	    JPanel LeftFingUpperBGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));
	    LeftFingUpperBGPanel.setPreferredSize(new Dimension(LeftFingUpperPanelWindowWidth-20,LeftFingUpperPanelWindowHeight));
	    LeftFingUpperBGPanel.setBackground(FingerPanelBG );
		LeftFingUpperBGPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),1));
		
		JPanel LeftFingUpper_Left_BGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));
		LeftFingUpper_Left_BGPanel.setPreferredSize(new Dimension(LeftFingUpperPanelWindowWidth/3,LeftFingUpperPanelWindowHeight-10));
		LeftFingUpper_Left_BGPanel.setBackground(FingerPanelBG );
		LeftFingUpper_Left_BGPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),1));
		
		JPanel LeftFingUpper_Right_BGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));
		LeftFingUpper_Right_BGPanel.setPreferredSize(new Dimension((int)(LeftFingUpperPanelWindowWidth/1.8),LeftFingUpperPanelWindowHeight-10));
		LeftFingUpper_Right_BGPanel.setBackground(FingerPanelBG );
		LeftFingUpper_Right_BGPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),1));
		
	    LeftFingChosenImage =new JLabel();
		//LeftFingChosenImage.setPreferredSize(new Dimension((int)(LeftFingUpperPanelWindowWidth/1.8),LeftFingUpperPanelWindowHeight-10));
		LeftFingChosenImage.setBackground(Color.red );
		LeftFingChosenImage.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),0));
		
		
		LeftFingChosenText = new JTextArea(); 
		LeftFingChosenText.setPreferredSize(new Dimension((int)(LeftFingUpperPanelWindowWidth/4),LeftFingUpperPanelWindowHeight-10));
		LeftFingChosenText.setBackground(Color.white);
		LeftFingChosenText.setForeground(Color.black);
		LeftFingChosenText.setLineWrap(true);
		LeftFingChosenText.setEditable ( false );
		LeftFingChosenText.setWrapStyleWord(true);
		LeftFingChosenText.setText("Please press open button to enable fingerprint scanner");
		
		
		LeftFingUpper_Right_BGPanel.add(LeftFingChosenImage);
		LeftFingUpper_Right_BGPanel.add(LeftFingChosenText);
		LeftFingUpperBGPanel.add(LeftFingUpper_Left_BGPanel);
		LeftFingUpperBGPanel.add(LeftFingUpper_Right_BGPanel);
		LeftFingBGPanel.add(LeftFingUpperBGPanel);
		
		JPanel LeftFingBottomBGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));
		LeftFingBottomBGPanel.setPreferredSize(new Dimension(LeftFingBottomPanelWindowWidth-20,LeftFingBottomPanelWindowHeight-20));
		LeftFingBottomBGPanel.setBackground(FingerPanelBG );
		LeftFingBottomBGPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),1));
		
		LeftFingBGPanel.add(LeftFingBottomBGPanel);
		
		JMenuBar leftHandMB = new JMenuBar();
	    JMenu leftHandFSelMenu = new JMenu("Finger");
	   // leftHandFSelMenu.setMnemonic(KeyEvent.VK_O);

	    ButtonGroup LeftFingerGroup = new ButtonGroup();

	    LeftThumb = new JRadioButtonMenuItem("Thumb");
	    LeftFingerGroup.add(LeftThumb);
	    leftHandFSelMenu.add(LeftThumb);
	    LeftThumb.setActionCommand("LeftThumb");
	    
	     Leftindex = new JRadioButtonMenuItem("Index");
	    LeftFingerGroup.add(Leftindex);
	    leftHandFSelMenu.add(Leftindex);
	    Leftindex.setSelected(true);
	    Leftindex.setActionCommand("LeftIndex");
	    
	    LeftMiddle = new JRadioButtonMenuItem("Middle");
	    LeftFingerGroup.add(LeftMiddle);
	    leftHandFSelMenu.add(LeftMiddle);
	    LeftMiddle.setActionCommand("LeftMiddle");
	    
	     LeftRing = new JRadioButtonMenuItem("Ring");
	    LeftFingerGroup.add(LeftRing);
	    leftHandFSelMenu.add(LeftRing);
	    LeftRing.setActionCommand("LeftRing");
	    
	     LeftSmall = new JRadioButtonMenuItem("Small");
	    LeftFingerGroup.add(LeftSmall);
	    leftHandFSelMenu.add(LeftSmall);
	    LeftSmall.setActionCommand("LeftSmall");

	    LFingerChosen = new  JLabel();
	    LFingerChosen.setText("");
	    LFingerChosen.setBackground(Color.white);


	    RadioListener myListener = new RadioListener();  //listen thumb
	    LeftThumb.addActionListener(myListener); LeftThumb.addChangeListener(myListener); LeftThumb.addItemListener(myListener);
	    Leftindex.addActionListener(myListener); Leftindex.addChangeListener(myListener);Leftindex.addItemListener(myListener);
	    LeftMiddle.addActionListener(myListener); LeftMiddle.addChangeListener(myListener);LeftMiddle.addItemListener(myListener);
	    LeftRing.addActionListener(myListener); LeftRing.addChangeListener(myListener);LeftRing.addItemListener(myListener);
	    LeftSmall.addActionListener(myListener); LeftSmall.addChangeListener(myListener);LeftSmall.addItemListener(myListener);
	    
	    if (LeftThumb.isSelected()){LFingerChosen.setText("Thumb");LeftFingChosenImage.setIcon(ic_leftThumbNotOk); LeftFingerChosenString="Thumb";}
	    if (Leftindex.isSelected()){LFingerChosen.setText("Index");LeftFingChosenImage.setIcon(ic_leftIndexNotOk);LeftFingerChosenString="Index";}
	    if (LeftMiddle.isSelected()){LFingerChosen.setText("Middle");LeftFingChosenImage.setIcon(ic_leftMiddleNotOk);LeftFingerChosenString="Middle";}
	    if (LeftRing.isSelected()){LFingerChosen.setText("Ring");LeftFingChosenImage.setIcon(ic_leftRingNotOk);LeftFingerChosenString="ring";}
	    if (LeftSmall.isSelected()){LFingerChosen.setText("Small");LeftFingChosenImage.setIcon(ic_leftSmallNotOk);LeftFingerChosenString="Small";}
	        
	 
	   
	    leftHandMB.add(leftHandFSelMenu);
	    leftHandMB.add(LFingerChosen);
	    LeftFingUpper_Left_BGPanel.add(leftHandMB);
	    LeftFingUpper_Left_BGPanel.add(LFingerChosen);
	    
	    LeftFingerbtnImgArea = new JButton();
		LeftFingerbtnImgArea.setBounds(0, 0, 256, 300);
		LeftFingerbtnImgArea.setDefaultCapable(false);
		LeftFingerbtnImgArea.setPreferredSize(new Dimension(LeftFingBottomPanelWindowWidth-30,LeftFingBottomPanelWindowHeight-30));
		LeftFingBottomBGPanel.add(LeftFingerbtnImgArea); 
		
		
		
		
//######################################################################################################################### central part
		// central buttons pannel
		JPanel FPControlButtonsBGPanel =new JPanel((new FlowLayout(FlowLayout.CENTER)));   
		FPControlButtonsBGPanel.setPreferredSize(new Dimension(FPControlPanelWindowWidth,FPControlPanelWindowHeight));
		FPControlButtonsBGPanel.setBackground(new Color(255,255,255,255));
		//FPControlButtonsBGPanel.setForeground(new Color(255,0,0,255));
		FPControlButtonsBGPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,100),1));
		
		JPanel FPControlLeftButtonsBGPanel =new JPanel((new FlowLayout(FlowLayout.CENTER)));   
		FPControlLeftButtonsBGPanel.setPreferredSize(new Dimension((int)(FPControlPanelWindowWidth/2.1),(int)(FPControlPanelWindowHeight/1.6)));
		FPControlLeftButtonsBGPanel.setBackground(new Color(255,0,0,0));
		//FPControlLeftButtonsBGPanel.setForeground(new Color(255,0,0,255));
		FPControlLeftButtonsBGPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,50),1));
		
		JPanel FPControlRightButtonsBGPanel =new JPanel((new FlowLayout(FlowLayout.CENTER)));   
		FPControlRightButtonsBGPanel.setPreferredSize(new Dimension((int)(FPControlPanelWindowWidth/2.1),(int)(FPControlPanelWindowHeight/1.6)));
		FPControlRightButtonsBGPanel.setBackground(new Color(255,0,0,0));
		//FPControlRightButtonsBGPanel.setForeground(new Color(255,0,0,255));
		FPControlRightButtonsBGPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,50),1));
		
		JPanel FPControlDownButtonsBGPanel =new JPanel((new FlowLayout(FlowLayout.CENTER)));   
		FPControlDownButtonsBGPanel.setPreferredSize(new Dimension((int)(FPControlPanelWindowWidth/1),(int)(FPControlPanelWindowHeight/4)));
		FPControlDownButtonsBGPanel.setBackground(new Color(255,0,0,0));
		//FPControlDownButtonsBGPanel.setForeground(new Color(255,0,0,255));
		FPControlDownButtonsBGPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,5),1));
		         
		
		FPControlButtonsBGPanel.add(FPControlLeftButtonsBGPanel);
		FPControlButtonsBGPanel.add(FPControlRightButtonsBGPanel);
		FPControlButtonsBGPanel.add(FPControlDownButtonsBGPanel);
		// central Data pannel
		
		
		
		
		
				JPanel FPDataBGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));   
				FPDataBGPanel.setPreferredSize(new Dimension(FPCenterDataPanelWindowWidth,FPCenterDataPanelWindowHeight));
				FPDataBGPanel.setBackground(new Color(255,255,255,100));
				//FPDataBGPanel.setForeground(new Color(255,0,0,255));
				FPDataBGPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,100),1));
				
				textArea = new JTextArea(); 
				textArea.setPreferredSize(new Dimension((int)(FPCenterDataPanelWindowWidth/1.07),FPCenterDataPanelWindowHeight/10));
				textArea.setBackground(Color.white);
				textArea.setForeground(Color.black);
				
				textArea.setLineWrap(true);
				textArea.setEditable ( true );
				textArea.setWrapStyleWord(true);
				FPDataBGPanel.add(textArea);  
				
				
		 
				FPDataArea = new JTextArea((int)(FPCenterDataPanelWindowHeight/18),(int)(FPCenterDataPanelWindowWidth/11.6)); 
				FPDataArea.setPreferredSize(new Dimension((int)(FPCenterDataPanelWindowWidth/1.07),FPCenterDataPanelWindowHeight-25));
				FPDataArea.setBackground(Color.white);
				FPDataArea.setForeground(Color.black);
				FPDataArea.setLineWrap(true);
				FPDataArea.setEditable ( true );
				FPDataArea.setWrapStyleWord(true);
				JScrollPane scroll = new JScrollPane(FPDataArea);
			    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			   // scroll.getViewport ().setView ( textArea );
				FPDataBGPanel.add(scroll);  
				
				
		// central t pannel
				JPanel FPCentralPanel =new JPanel((new FlowLayout(FlowLayout.CENTER)));   
				FPCentralPanel.setPreferredSize(new Dimension(FPCentalBGPanelWindowWidth,FPCentalBGPanelWindowHeight));
				//FPCentralPanel.setBackground(new Color(0,255,0,255));
				FPCentralPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,255,255)),0));
				//FPCentralPanel.setBorder(BorderFactory.createTitledBorder("Horizontal Alignment"));
		
				FPCentralPanel.add(FPControlButtonsBGPanel);
				FPCentralPanel.add(FPDataBGPanel);
		
 //######################################################################################################################## End central part 
				
				
				
		// Right fingerprint panel
		JPanel RightFingBGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));   
		RightFingBGPanel.setPreferredSize(new Dimension(RightFingPanelWindowWidth,RightFingPanelWindowHeight));
		RightFingBGPanel.setBackground(new Color(255,255,255,255));
		RightFingBGPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,200,255)),1));
		
		//LeftFingBGPanel.setBorder(BorderFactory.createLineBorder((new Color(255,255,255,255)),8));
				TitledBorder rightPanBorder = new TitledBorder(BorderFactory.createLineBorder(new Color(0,0,255,100),1), BioWindowTitleSpace+"RIGHT HAND");
			    Font rightPanBorderfont = new Font("Serif", Font.ITALIC, 12);
			    rightPanBorder.setTitleColor(Color.black);
			    rightPanBorder.setTitleFont(rightPanBorderfont);
			    RightFingBGPanel.setBorder(new TitledBorder(rightPanBorder, "", TitledBorder.RIGHT,TitledBorder.ABOVE_TOP, font, Color.black)); 
				
			    
			    JPanel RightFingUpperBGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));
			    RightFingUpperBGPanel.setPreferredSize(new Dimension(RightFingUpperPanelWindowWidth-20,RightFingUpperPanelWindowHeight));
			    RightFingUpperBGPanel.setBackground(FingerPanelBG );
				RightFingUpperBGPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),1));
				
				JPanel RightFingUpper_Left_BGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));
				RightFingUpper_Left_BGPanel.setPreferredSize(new Dimension(RightFingUpperPanelWindowWidth/3,RightFingUpperPanelWindowHeight-10));
				RightFingUpper_Left_BGPanel.setBackground(FingerPanelBG );
				RightFingUpper_Left_BGPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),1));
				
				JPanel RightFingUpper_Right_BGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));
				RightFingUpper_Right_BGPanel.setPreferredSize(new Dimension((int)(RightFingUpperPanelWindowWidth/1.8),RightFingUpperPanelWindowHeight-10));
				RightFingUpper_Right_BGPanel.setBackground(FingerPanelBG );
				RightFingUpper_Right_BGPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),1));
				
			    RightFingChosenImage =new JLabel();
				//RightFingChosenImage.setPreferredSize(new Dimension((int)(RightFingUpperPanelWindowWidth/1.8),RightFingUpperPanelWindowHeight-10));
				RightFingChosenImage.setBackground(Color.red );
				RightFingChosenImage.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),0));
				
				
				RightFingChosenText = new JTextArea(); 
				RightFingChosenText.setPreferredSize(new Dimension((int)(RightFingUpperPanelWindowWidth/4),RightFingUpperPanelWindowHeight-10));
				RightFingChosenText.setBackground(Color.white);
				RightFingChosenText.setForeground(Color.black);
				RightFingChosenText.setLineWrap(true);
				RightFingChosenText.setEditable ( false );
				RightFingChosenText.setWrapStyleWord(true);
				RightFingChosenText.setText("Please press open button to enable fingerprint scanner");
				
				
				RightFingUpper_Right_BGPanel.add(RightFingChosenImage);
				RightFingUpper_Right_BGPanel.add(RightFingChosenText);
				RightFingUpperBGPanel.add(RightFingUpper_Left_BGPanel);
				RightFingUpperBGPanel.add(RightFingUpper_Right_BGPanel);
				RightFingBGPanel.add(RightFingUpperBGPanel);
				
				JPanel RightFingBottomBGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));
				RightFingBottomBGPanel.setPreferredSize(new Dimension(RightFingBottomPanelWindowWidth-20,RightFingBottomPanelWindowHeight-20));
				RightFingBottomBGPanel.setBackground(FingerPanelBG );
				RightFingBottomBGPanel.setBorder(BorderFactory.createLineBorder((new Color(0,0,0,30)),1));
				
				RightFingBGPanel.add(RightFingBottomBGPanel);
				
				JMenuBar rightHandMB = new JMenuBar();
			    JMenu rightHandFSelMenu = new JMenu("Finger");
			   // rightHandFSelMenu.setMnemonic(KeyEvent.VK_O);

			    ButtonGroup RightFingerGroup = new ButtonGroup();

			    RightThumb = new JRadioButtonMenuItem("Thumb");
			    RightFingerGroup.add(RightThumb);
			    rightHandFSelMenu.add(RightThumb);
			    RightThumb.setActionCommand("RightThumb");
			    
			     Rightindex = new JRadioButtonMenuItem("Index");
			    RightFingerGroup.add(Rightindex);
			    rightHandFSelMenu.add(Rightindex);
			    Rightindex.setSelected(true);
			    Rightindex.setActionCommand("RightIndex");
			    
			    RightMiddle = new JRadioButtonMenuItem("Middle");
			    RightFingerGroup.add(RightMiddle);
			    rightHandFSelMenu.add(RightMiddle);
			    RightMiddle.setActionCommand("RightMiddle");
			    
			     RightRing = new JRadioButtonMenuItem("Ring");
			    RightFingerGroup.add(RightRing);
			    rightHandFSelMenu.add(RightRing);
			    RightRing.setActionCommand("RightRing");
			    
			     RightSmall = new JRadioButtonMenuItem("Small");
			    RightFingerGroup.add(RightSmall);
			    rightHandFSelMenu.add(RightSmall);
			    RightSmall.setActionCommand("RightSmall");

			    RFingerChosen = new  JLabel();
			    RFingerChosen.setText("");
			    RFingerChosen.setBackground(Color.white);


			    //RadioListener myListener = new RadioListener();  //listen fingers
			    RightThumb.addActionListener(myListener); RightThumb.addChangeListener(myListener); RightThumb.addItemListener(myListener);
			    Rightindex.addActionListener(myListener); Rightindex.addChangeListener(myListener);Rightindex.addItemListener(myListener);
			    RightMiddle.addActionListener(myListener); RightMiddle.addChangeListener(myListener);RightMiddle.addItemListener(myListener);
			    RightRing.addActionListener(myListener); RightRing.addChangeListener(myListener);RightRing.addItemListener(myListener);
			    RightSmall.addActionListener(myListener); RightSmall.addChangeListener(myListener);RightSmall.addItemListener(myListener);
			    
			    if (RightThumb.isSelected()){RFingerChosen.setText("Thumb");RightFingChosenImage.setIcon(ic_rightThumbNotOk);}
			    if (Rightindex.isSelected()){RFingerChosen.setText("Index");RightFingChosenImage.setIcon(ic_rightIndexNotOk);}
			    if (RightMiddle.isSelected()){RFingerChosen.setText("Middle");RightFingChosenImage.setIcon(ic_rightMiddleNotOk);}
			    if (RightRing.isSelected()){RFingerChosen.setText("Ring");RightFingChosenImage.setIcon(ic_rightRingNotOk);}
			    if (RightSmall.isSelected()){RFingerChosen.setText("Small");RightFingChosenImage.setIcon(ic_rightSmallNotOk);}
			        
			 
			   
			    rightHandMB.add(rightHandFSelMenu);
			    rightHandMB.add(RFingerChosen);
			    RightFingUpper_Left_BGPanel.add(rightHandMB);
			    RightFingUpper_Left_BGPanel.add(RFingerChosen);
			    
			    RightFingerbtnImgArea = new JButton();
				RightFingerbtnImgArea.setBounds(0, 0, 256, 300);
				RightFingerbtnImgArea.setDefaultCapable(false);
				RightFingerbtnImgArea.setPreferredSize(new Dimension(RightFingBottomPanelWindowWidth-30,RightFingBottomPanelWindowHeight-30));
				RightFingBottomBGPanel.add(RightFingerbtnImgArea); 
				
				
				
				
    	

		
		
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& Overall Panel		
		
		JPanel FingerBiometricBGPanel =new JPanel((new FlowLayout(FlowLayout.LEFT)));   
		   FingerBiometricBGPanel.setBounds(0,0,FBiowindowWidth,FBiowindowHeight);
		   FingerBiometricBGPanel.setBackground(new Color(255,255,255,255));
		   //FingerBiometricBGPanel.setForeground(new Color(255,0,0,255));
		   //FingerBiometricBGPanel.setBorder(BorderFactory.createLineBorder(Color.red,3));
		   //FingerBiometricBGPanel.setBorder(BorderFactory.createTitledBorder("Horizontal Alignment"));
		   
		   JPanel btnPanel = new JPanel();
		   JButton cancel = new JButton("Cancel");
		   cancel.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
               FPframe.dispose();
               }
               });
		   JButton restart = new JButton("Restart");
           JButton proceed = new JButton("Proceed");
          
         
           cancel.addActionListener(new ButtonListener()); 
           restart.addActionListener(new ButtonListener()); 
           proceed.addActionListener(new ButtonListener());
           btnPanel.add(cancel);
           btnPanel.add(restart);
           btnPanel.add(proceed);
           
           String CandidateNameIndex=("                  ");
           String smallSpacing = "          ";
           
           
           JPanel CandidatePanel1 = new JPanel();
           JLabel CandidateLabe1l = new JLabel();
           CandidateLabe1l.setText(CandidateNameIndex+CandidateNameIndex);
           CandidatePanel1.add(CandidateLabe1l);
           
           JPanel CandidatePanel = new JPanel();
           JLabel CandidateLabel = new JLabel();
           CandidateLabel.setText("Enrolling:   "+StudentName+ smallSpacing + StudentNumber+ smallSpacing + StudentRegNo + smallSpacing + StudentColledge+ smallSpacing + StudentUniversity+ smallSpacing+ StudentCountry);
           CandidatePanel.add(CandidateLabel);
    	   
		   FingerBiometricBGPanel.add(LeftFingBGPanel); // add left Fingerprint panel
		   FingerBiometricBGPanel.add(FPCentralPanel); // add centra bts pannel
		   FingerBiometricBGPanel.add(RightFingBGPanel); // add right bts pannel
           FingerBiometricBGPanel.add(btnPanel, BorderLayout.PAGE_END);
           FingerBiometricBGPanel.add(CandidatePanel1, BorderLayout.PAGE_END);
           FingerBiometricBGPanel.add(CandidatePanel, BorderLayout.PAGE_END);
          

        //JFrame FPframe = new JFrame();
        //FPframe.setLayout(null);
        FPframe.setSize(FBiowindowWidth,FBiowindowHeightExtd);
        FPframe.add(FingerBiometricBGPanel, BorderLayout.CENTER);
        FPframe.setLocationRelativeTo(null);
        FPframe.setVisible(true);
        FPframe.setResizable(false);
		FPframe.setBackground(FingerPanelBG);
		String heading="FINGERPRINT CAPTURE";
		FPframe.setFont(new Font("System", Font.BOLD, 14));
		Font f = FPframe.getFont();
		FontMetrics fm = FPframe.getFontMetrics(f);
		int px = fm.stringWidth(heading);
		int py = fm.stringWidth(" ");
		int pz = FPframe.getWidth()/2 - (px/2);
		int w = (int)(pz*1.2)/py;
		String pad ="";
		//for (int i=0; i!=w; i++) pad +=" "; 
		pad = String.format("%"+w+"s", pad); 
		FPframe.setTitle(pad + heading.toUpperCase());
		
		FPframe.setIconImage(Toolkit.getDefaultToolkit().getImage("data/images/icons/ic_title1.png"));
		//resize frame
				FPframe.addComponentListener(new ComponentAdapter() {
			            @Override
			            public void componentResized(ComponentEvent e) {
			            	//JFrame frame = new JFrame();
			                //titleAlign(frame);
			
			            }
			        });
		
				
		
				
				
				
        
	    LeftFPImgpath = "data/images/fingerPrints/TempFPrintData/";
	    RightFPImgpath = "data/images/fingerPrints/TempFPrintData/";
	    FPImgExt = ".bmp";
		
	  //  System.out.println("Finger chosen: "+LeftFingToSave);
	    
				int nRsize = 10; int Xpos = 200;
				int buttonSizeMediumWidth =  100, buttonSizeMediumHeight=30;
				
		btnOpen = new JButton("Open");  
		btnOpen.setToolTipText("Open USB fingerPrint Scanner");
		//btnOpen.setPreferredSize(new Dimension(buttonSizeMediumWidth,buttonSizeMediumHeight));
		btnOpen.setBounds(0, 0 , buttonSizeMediumWidth, buttonSizeMediumHeight);
		FPControlLeftButtonsBGPanel.add(btnOpen);  
		
		
		btnEnroll = new JButton("Enroll");  
		btnEnroll.setToolTipText("Enroll Person");
		btnEnroll.setBounds(0, buttonSizeMediumHeight+5 , buttonSizeMediumWidth, buttonSizeMediumHeight);
		btnEnroll.setVisible(true);
		FPControlLeftButtonsBGPanel.add(btnEnroll);
		
		
	    btnVerify = new JButton("Verify");  
		btnVerify.setToolTipText("Verify");
		btnVerify.setBounds(0, buttonSizeMediumHeight*2+10 , buttonSizeMediumWidth, buttonSizeMediumHeight);
		btnVerify.setVisible(true);
		FPControlLeftButtonsBGPanel.add(btnVerify);
		
		btnIdentify = new JButton("Identify");  
		btnIdentify.setToolTipText("Identify ");
		btnIdentify.setBounds(0, 0 , buttonSizeMediumWidth, buttonSizeMediumHeight);
		btnIdentify.setVisible(true);
		FPControlLeftButtonsBGPanel.add(btnIdentify);
		
		
		btnRegImg = new JButton("Register By Image");  
		btnRegImg.setToolTipText("Register by fingerprint image ");
		btnRegImg.setBounds(0, 0 , buttonSizeMediumWidth, buttonSizeMediumHeight);
		btnRegImg.setVisible(true);
		FPControlRightButtonsBGPanel.add(btnRegImg);
		
		btnIdentImg = new JButton("Verify By Image");  
		btnIdentImg.setToolTipText("Vefify by fingerprint image ");
		btnIdentImg.setBounds(0, buttonSizeMediumHeight+5 , buttonSizeMediumWidth, buttonSizeMediumHeight);
		btnIdentImg.setVisible(true);
		FPControlRightButtonsBGPanel.add(btnIdentImg);
		
		
		btnClose = new JButton("Close");  
		btnClose.setToolTipText("Close");
		btnClose.setBounds(FPControlPanelWindowWidth/2-buttonSizeMediumWidth/2, 0 , buttonSizeMediumWidth, buttonSizeMediumHeight);
		btnClose.setVisible(true);
		FPControlDownButtonsBGPanel.add(btnClose);
		
		
		
        //**************************************************FingerPrint Drawn here
	/*	LeftFingerbtnImgArea = new JButton();
		LeftFingerbtnImgArea.setBounds(150+Xpos, 5+ nRsize, 256, 300);
		LeftFingerbtnImgArea.setDefaultCapable(false);
		//FingerBiometricBGPanel.add(LeftFingerbtnImgArea); */
		
		/*textArea = new JTextArea();
	    FingerBiometricBGPanel.add(textArea);  
		textArea.setBounds(10+Xpos, 340+ + nRsize, 480, 100);
		textArea.setText("well done");
		textArea.setBackground(Color.red);
		textArea.setForeground(Color.white);
		FPDataBGPanel.add(textArea); */ 
		

		
		
		btnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (0 != mhDevice)
				{
					//already inited
					Font font = new Font("Serif", Font.BOLD, 12);
	            	textArea.setForeground(Color.black);
	            	textArea.setFont(font);
					textArea.setText("Please close fingerprint Scanner first");
					
					LeftFingChosenText.setForeground(Color.black);
					LeftFingChosenText.setFont(font);
					LeftFingChosenText.setText("Disconnect fingerprint Scanner");
					
					return;
				}
				int ret = FingerprintSensorErrorCode.ZKFP_ERR_OK;
				//Initialize
				cbRegTemp = 0;
				bRegister = false;
				bIdentify = false;
				iFid = 1;
				enroll_idx = 0;
				if (FingerprintSensorErrorCode.ZKFP_ERR_OK != FingerprintSensorEx.Init())
				{
					Font fontError = new Font("Serif", Font.BOLD, 12);
	            	textArea.setForeground(Color.red);
	            	textArea.setFont(fontError);
					textArea.setText("Failed to start fingerprint device !");
					
					LeftFingChosenText.setForeground(Color.red);
					LeftFingChosenText.setFont(fontError);
					LeftFingChosenText.setText("No USB fingerprint scanner detected!");
					
					return;
				}
				ret = FingerprintSensorEx.GetDeviceCount();
				if (ret < 0)
				{
					
					Font font = new Font("Serif", Font.BOLD, 12);
	            	textArea.setForeground(Color.black);
	            	textArea.setFont(font);
					textArea.setText("No fingerprint Scanner connected !");
					
					LeftFingChosenText.setForeground(Color.black);
					LeftFingChosenText.setFont(font);
					LeftFingChosenText.setText("No fingerprint Scanner found !");
					
					FreeSensor();
					return;
				}
				if (0 == (mhDevice = FingerprintSensorEx.OpenDevice(0)))
				{
					
					Font fontError = new Font("Serif", Font.BOLD, 12);
	            	textArea.setForeground(Color.red);
	            	textArea.setFont(fontError);
					textArea.setText("Failed to Open Fingerprint Scanner !");
					
					LeftFingChosenText.setForeground(Color.red);
					LeftFingChosenText.setFont(fontError);
					LeftFingChosenText.setText("Please Check if no other program is using the fingerprint Scanner.");
					
					FreeSensor();
					return;
				}
				if (0 == (mhDB = FingerprintSensorEx.DBInit()))
				{
					Font fontError = new Font("Serif", Font.BOLD, 12);
	            	textArea.setForeground(Color.red);
	            	textArea.setFont(fontError);
					textArea.setText("Failed to access Data Base");
					
					LeftFingChosenText.setForeground(Color.black);
					LeftFingChosenText.setFont(font);
					LeftFingChosenText.setText("Database failure !");
					
					FreeSensor();
					return;
				}
				
				
				//set fakefun off
				
				//FingerprintSensorEx.SetParameter(mhDevice, 2002, changeByte(nFakeFunOn), 4);
							
				byte[] paramValue = new byte[4];
				int[] size = new int[1];
				//GetFakeOn
				//size[0] = 4;
				//FingerprintSensorEx.GetParameters(mhDevice, 2002, paramValue, size);
				//nFakeFunOn = byteArrayToInt(paramValue);
				
				size[0] = 4;
				FingerprintSensorEx.GetParameters(mhDevice, 1, paramValue, size);
				fpWidth = byteArrayToInt(paramValue);
				size[0] = 4;
				FingerprintSensorEx.GetParameters(mhDevice, 2, paramValue, size);
				fpHeight = byteArrayToInt(paramValue);
				//width = fingerprintSensor.getImageWidth();
				//height = fingerprintSensor.getImageHeight();
				imgbuf = new byte[fpWidth*fpHeight];
				LeftFingerbtnImgArea.resize(fpWidth, fpHeight);
				mbStop = false;
				workThread = new WorkThread();
			    workThread.start();// 绾跨▼鍚姩
	            //textArea.setText("Open succ!");
			    Font fontBlack = new Font("Calibri", Font.BOLD, 12);
                textArea.setForeground(Color.black);
                textArea.setFont(fontBlack);
			    textArea.setText("Fingerprint scanner connected successfully");
			    LeftFingChosenText.setForeground(Color.black);
			    LeftFingChosenText.setFont(fontBlack);
			    LeftFingChosenText.setText("please press enroll button to register new fingerprints");
			}
		});
		
		
		
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FreeSensor();
				
				Font font = new Font("Serif", Font.BOLD, 12);
            	textArea.setForeground(Color.black);
            	textArea.setFont(font);
				textArea.setText("Fingerprint Scanner successfully disconnected");
				
				LeftFingChosenText.setForeground(Color.black);
				LeftFingChosenText.setFont(font);
				LeftFingChosenText.setText("You have discionnected fingerprint Scanner");
				
			}
		});
		
		btnEnroll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                register();
                
			}
			});
		
		btnVerify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(0 == mhDevice)
				{
					Font font = new Font("Serif", Font.BOLD, 12);
                	textArea.setForeground(Color.red);
                	textArea.setFont(font);
					textArea.setText("Please open fingerprint scanner first");
					
					LeftFingChosenText.setForeground(Color.black);
					LeftFingChosenText.setFont(font);
					LeftFingChosenText.setText("Press 'Open' to enable fingerprint scanner ");
					
					return;
				}
				if(bRegister)
				{
					enroll_idx = 0;
					bRegister = false;
				}
				if(bIdentify)
				{
					bIdentify = false;
				}
			}
			});
		
		btnIdentify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(0 == mhDevice)
				{
					Font font = new Font("Serif", Font.BOLD, 12);
                	textArea.setForeground(Color.red);
                	textArea.setFont(font);
					textArea.setText("Please open fingerprint scanner first");
					
					LeftFingChosenText.setForeground(Color.black);
					LeftFingChosenText.setFont(font);
					LeftFingChosenText.setText("Press 'Open' to enable fingerprint scanner ");
					
					return;
				}
				if(bRegister)
				{
					enroll_idx = 0;
					bRegister = false;
				}
				if(!bIdentify)
				{
					bIdentify = true;
				}
			}
			});
		
		
		btnRegImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(0 == mhDB)
				{
					Font font = new Font("Serif", Font.BOLD, 12);
                	textArea.setForeground(Color.red);
                	textArea.setFont(font);
					textArea.setText("Please open fingerprint scanner first");
					
					LeftFingChosenText.setForeground(Color.black);
					LeftFingChosenText.setFont(font);
					LeftFingChosenText.setText("Press 'Open' to enable fingerprint scanner ");
					
				}
				String path = "data/images/fingerPrints/TempFPrintData/fRIndex_213007052.bmp";
				byte[] fpTemplate = new byte[2048];
				int[] sizeFPTemp = new int[1];
				sizeFPTemp[0] = 2048;
				int ret = FingerprintSensorEx.ExtractFromImage( mhDB, path, 500, fpTemplate, sizeFPTemp);
				if (0 == ret)
				{
					ret = FingerprintSensorEx.DBAdd( mhDB, iFid, fpTemplate);
					if (0 == ret)
					{
						//String base64 = fingerprintSensor.BlobToBase64(fpTemplate, sizeFPTemp[0]);		
						iFid++;
                    	cbRegTemp = sizeFPTemp[0];
                        System.arraycopy(fpTemplate, 0, lastRegTemp, 0, cbRegTemp);
                        //Base64 Template
                        //String strBase64 = Base64.encodeToString(regTemp, 0, ret, Base64.NO_WRAP);
                        Font fontBlue = new Font("Calibri", Font.BOLD, 12);
                        textArea.setForeground(Color.blue);
                        textArea.setFont(fontBlue);
                        textArea.setText("Left hand finger successfully Enrolled.");
                        
                        LeftFingChosenText.setForeground(Color.blue);
                        LeftFingChosenText.setFont(fontBlue);
                        LeftFingChosenText.setText("left hand fingerprint for "+StudentName +" has been successfully captured!");
					}
					else
					{
						Font fontError = new Font("Serif", Font.BOLD, 12);
	                	textArea.setForeground(Color.red);
	                	textArea.setFont(fontError);
						textArea.setText("Database Add failed, ret=" + ret);
						
						LeftFingChosenText.setForeground(Color.red);
						LeftFingChosenText.setFont(fontError);
						LeftFingChosenText.setText("Failed to add fingerprint to Database ");
					}
				}
				else
				{
					
					Font fontError = new Font("Serif", Font.BOLD, 12);
                	textArea.setForeground(Color.red);
                	textArea.setFont(fontError);
                	textArea.setText("ExtractFromImage fail, ret=" + ret);
					
					LeftFingChosenText.setForeground(Color.red);
					LeftFingChosenText.setFont(fontError);
					LeftFingChosenText.setText("Failed to extract from provided image !");
					
				}
			}
			});
		
		
		btnIdentImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(0 ==  mhDB)
				{
					Font font = new Font("Serif", Font.BOLD, 12);
                	textArea.setForeground(Color.red);
                	textArea.setFont(font);
					textArea.setText("Please open fingerprint scanner first");
					
					LeftFingChosenText.setForeground(Color.black);
					LeftFingChosenText.setFont(font);
					LeftFingChosenText.setText("Press 'Open' to enable fingerprint scanner ");
				}
				String path = "data/images/fingerPrints/TempFPrintData/fRIndex_213007052.bmp";
				byte[] fpTemplate = new byte[2048];
				int[] sizeFPTemp = new int[1];
				sizeFPTemp[0] = 2048;
				int ret = FingerprintSensorEx.ExtractFromImage(mhDB, path, 500, fpTemplate, sizeFPTemp);
				if (0 == ret)
				{
					if (bIdentify)
					{
						int[] fid = new int[1];
						int[] score = new int [1];
						ret = FingerprintSensorEx.DBIdentify(mhDB, fpTemplate, fid, score);
                        if (ret == 0)
                        {
                        	Font font = new Font("Serif", Font.BOLD, 12);
		                	textArea.setForeground(Color.blue);
		                	textArea.setFont(font);
		                	textArea.setText("Identify succ, fid=" + fid[0] + ",score=" + score[0]);
                        	
                        	LeftFingChosenText.setForeground(Color.blue);
							LeftFingChosenText.setFont(font);
							LeftFingChosenText.setText("Fingerprint match found successfully");
			                
                        	
                        }
                        else
                        {
                        	Font Errorfont = new Font("Serif", Font.BOLD, 12);
		                	textArea.setForeground(Color.red);
		                	textArea.setFont(Errorfont);
                        	textArea.setText("Failed to identify, errcode=" + ret);
                        	
                        	LeftFingChosenText.setForeground(Color.red);
							LeftFingChosenText.setFont(Errorfont);
							LeftFingChosenText.setText("Fingerprint is not exixting in database");
			                
							if(RightHandEn !=true){resetLeftFingers(); }
							else {resetRightFingers(); }
                        	
                        }
                            
					}
					else
					{
						if(cbRegTemp <= 0)
						{ 
							Font font = new Font("Serif", Font.BOLD, 12);
		                	textArea.setForeground(Color.black);
		                	textArea.setFont(font);
							textArea.setText("Please register first!");
							
							LeftFingChosenText.setForeground(Color.black);
							LeftFingChosenText.setFont(font);
							LeftFingChosenText.setText("Press the enroll button to register");
						}
						else
						{
							ret = FingerprintSensorEx.DBMatch(mhDB, lastRegTemp, fpTemplate);
							if(ret > 0)
							{
								
								Font font = new Font("Serif", Font.BOLD, 12);
			                	textArea.setForeground(Color.blue);
			                	textArea.setFont(font);
			                	textArea.setText("Verified successfully, score=" + ret);
								
								
								LeftFingChosenText.setForeground(Color.black);
								LeftFingChosenText.setFont(font);
								LeftFingChosenText.setText("Match found");
				                
				              //  resetLeftFingers(); 
							}
							else
							{
								Font Errorfont = new Font("Serif", Font.BOLD, 12);
			                	textArea.setForeground(Color.red);
			                	textArea.setFont(Errorfont);
								textArea.setText("Verify fail, ret=" + ret);
								
								
								LeftFingChosenText.setForeground(Color.red);
								LeftFingChosenText.setFont(Errorfont);
								LeftFingChosenText.setText("Verification Failure. No fingerprint match found !");
				                
				                resetLeftFingers(); 
							}
						}
					}
				}
				else
				{
					Font Errorfont = new Font("Serif", Font.BOLD, 12);
                	textArea.setForeground(Color.red);
                	textArea.setFont(Errorfont);
					textArea.setText("ExtractFromImage fail, ret=" + ret);
					
					LeftFingChosenText.setText("Corrupt or missing fingerprint Image !");
				}
			}
		});
	
		
		FPframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		FPframe.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
            	FreeSensor();
            }
		});
		
	}
	
	
	
	protected void register() {
		// TODO Auto-generated method stub
		if(0 == mhDevice)
		{
			Font font = new Font("Serif", Font.BOLD, 12);
        	textArea.setForeground(Color.red);
        	textArea.setFont(font);
			textArea.setText("Please open fingerprint scanner first");
			
			LeftFingChosenText.setForeground(Color.black);
			LeftFingChosenText.setFont(font);
			LeftFingChosenText.setText("Press 'Open' to enable fingerprint scanner ");
			
			return;
		}
		if(!bRegister)
		{
			enroll_idx = 0;
			bRegister = true;
			
			
			
			Font fontBlack = new Font("Calibri", Font.BOLD, 12);
            textArea.setForeground(Color.black);
            textArea.setFont(fontBlack);
            if(RightHandEn !=true){textArea.setText("Place your left finger "+enroll_cnt+" times!"); resetLeftFingers();}
            else {textArea.setText("Place your right finger "+enroll_cnt+" times!"); resetRightFingers();}
			
			LeftFingChosenText.setForeground(Color.black);
            LeftFingChosenText.setFont(fontBlack);
            RightFingChosenText.setForeground(Color.black);
            RightFingChosenText.setFont(fontBlack);
            if(RightHandEn !=true){LeftFingChosenText.setText(StudentName +", Place your "+" finger as indicated by Image "+(enroll_cnt - enroll_idx)+" times to get enrolled");}
            else{RightFingChosenText.setText(StudentName +", Place your right"+" finger as indicated by Image "+(enroll_cnt - enroll_idx)+" times to get enrolled");}
		}
	
	}



	protected void resetLeftFingers() {
		// TODO Auto-generated method stub
		if (LeftThumb.isSelected()){LeftFingChosenImage.setIcon(ic_leftThumbNotOk);}
	    if (Leftindex.isSelected()){LeftFingChosenImage.setIcon(ic_leftIndexNotOk);}
	    if (LeftMiddle.isSelected()){LeftFingChosenImage.setIcon(ic_leftMiddleNotOk);}
	    if (LeftRing.isSelected()){LeftFingChosenImage.setIcon(ic_leftRingNotOk);}
	    if (LeftSmall.isSelected()){LeftFingChosenImage.setIcon(ic_leftSmallNotOk);}
	}
	
	private void VerifyLeftFinger() {
		// TODO Auto-generated method stub
		 if (LeftThumb.isSelected()){LeftFingChosenImage.setIcon(ic_leftThumbOk);}
	    if (Leftindex.isSelected()){LeftFingChosenImage.setIcon(ic_leftIndexOk);}
	    if (LeftMiddle.isSelected()){LeftFingChosenImage.setIcon(ic_leftMiddleOk);}
	    if (LeftRing.isSelected()){LeftFingChosenImage.setIcon(ic_leftRingOk);}
	    if (LeftSmall.isSelected()){LeftFingChosenImage.setIcon(ic_leftSmallOk);}
	}
	
	protected void resetRightFingers() {
		// TODO Auto-generated method stub
		if (RightThumb.isSelected()){RightFingChosenImage.setIcon(ic_rightThumbNotOk);}
	    if (Rightindex.isSelected()){RightFingChosenImage.setIcon(ic_rightIndexNotOk);}
	    if (RightMiddle.isSelected()){RightFingChosenImage.setIcon(ic_rightMiddleNotOk);}
	    if (RightRing.isSelected()){RightFingChosenImage.setIcon(ic_rightRingNotOk);}
	    if (RightSmall.isSelected()){RightFingChosenImage.setIcon(ic_rightSmallNotOk);}
	}
	
	private void VerifyRightFinger() {
		// TODO Auto-generated method stub
		 if (RightThumb.isSelected()){RightFingChosenImage.setIcon(ic_rightThumbOk);}
	    if (Rightindex.isSelected()){RightFingChosenImage.setIcon(ic_rightIndexOk);}
	    if (RightMiddle.isSelected()){RightFingChosenImage.setIcon(ic_rightMiddleOk);}
	    if (RightRing.isSelected()){RightFingChosenImage.setIcon(ic_rightRingOk);}
	    if (RightSmall.isSelected()){RightFingChosenImage.setIcon(ic_rightSmallOk);}
	}




	private void FreeSensor()
	{
		mbStop = true;
		try {		//wait for thread stopping
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (0 != mhDB)
		{
			FingerprintSensorEx.DBFree(mhDB);
			mhDB = 0;
		}
		if (0 != mhDevice)
		{
			FingerprintSensorEx.CloseDevice(mhDevice);
			mhDevice = 0;
		}
		FingerprintSensorEx.Terminate();
	}
	
	public static void writeBitmap(byte[] imageBuf, int nWidth, int nHeight,
			String path) throws IOException {
		java.io.FileOutputStream fos = new java.io.FileOutputStream(path);
		java.io.DataOutputStream dos = new java.io.DataOutputStream(fos);

		int w = (((nWidth+3)/4)*4);
		int bfType = 0x424d; // 浣嶅浘鏂囦欢绫诲瀷锛�0鈥�1瀛楄妭锛�
		int bfSize = 54 + 1024 + w * nHeight;// bmp鏂囦欢鐨勫ぇ灏忥紙2鈥�5瀛楄妭锛�
		int bfReserved1 = 0;// 浣嶅浘鏂囦欢淇濈暀瀛楋紝蹇呴』涓�0锛�6-7瀛楄妭锛�
		int bfReserved2 = 0;// 浣嶅浘鏂囦欢淇濈暀瀛楋紝蹇呴』涓�0锛�8-9瀛楄妭锛�
		int bfOffBits = 54 + 1024;// 鏂囦欢澶村紑濮嬪埌浣嶅浘瀹為檯鏁版嵁涔嬮棿鐨勫瓧鑺傜殑鍋忕Щ閲忥紙10-13瀛楄妭锛�

		dos.writeShort(bfType); // 杈撳叆浣嶅浘鏂囦欢绫诲瀷'BM'
		dos.write(changeByte(bfSize), 0, 4); // 杈撳叆浣嶅浘鏂囦欢澶у皬
		dos.write(changeByte(bfReserved1), 0, 2);// 杈撳叆浣嶅浘鏂囦欢淇濈暀瀛�
		dos.write(changeByte(bfReserved2), 0, 2);// 杈撳叆浣嶅浘鏂囦欢淇濈暀瀛�
		dos.write(changeByte(bfOffBits), 0, 4);// 杈撳叆浣嶅浘鏂囦欢鍋忕Щ閲�

		int biSize = 40;// 淇℃伅澶存墍闇�鐨勫瓧鑺傛暟锛�14-17瀛楄妭锛�
		int biWidth = nWidth;// 浣嶅浘鐨勫锛�18-21瀛楄妭锛�
		int biHeight = nHeight;// 浣嶅浘鐨勯珮锛�22-25瀛楄妭锛�
		int biPlanes = 1; // 鐩爣璁惧鐨勭骇鍒紝蹇呴』鏄�1锛�26-27瀛楄妭锛�
		int biBitcount = 8;// 姣忎釜鍍忕礌鎵�闇�鐨勪綅鏁帮紙28-29瀛楄妭锛夛紝蹇呴』鏄�1浣嶏紙鍙岃壊锛夈��4浣嶏紙16鑹诧級銆�8浣嶏紙256鑹诧級鎴栬��24浣嶏紙鐪熷僵鑹诧級涔嬩竴銆�
		int biCompression = 0;// 浣嶅浘鍘嬬缉绫诲瀷锛屽繀椤绘槸0锛堜笉鍘嬬缉锛夛紙30-33瀛楄妭锛夈��1锛圔I_RLEB鍘嬬缉绫诲瀷锛夋垨2锛圔I_RLE4鍘嬬缉绫诲瀷锛変箣涓�銆�
		int biSizeImage = w * nHeight;// 瀹為檯浣嶅浘鍥惧儚鐨勫ぇ灏忥紝鍗虫暣涓疄闄呯粯鍒剁殑鍥惧儚澶у皬锛�34-37瀛楄妭锛�
		int biXPelsPerMeter = 0;// 浣嶅浘姘村钩鍒嗚鲸鐜囷紝姣忕背鍍忕礌鏁帮紙38-41瀛楄妭锛夎繖涓暟鏄郴缁熼粯璁ゅ��
		int biYPelsPerMeter = 0;// 浣嶅浘鍨傜洿鍒嗚鲸鐜囷紝姣忕背鍍忕礌鏁帮紙42-45瀛楄妭锛夎繖涓暟鏄郴缁熼粯璁ゅ��
		int biClrUsed = 0;// 浣嶅浘瀹為檯浣跨敤鐨勯鑹茶〃涓殑棰滆壊鏁帮紙46-49瀛楄妭锛夛紝濡傛灉涓�0鐨勮瘽锛岃鏄庡叏閮ㄤ娇鐢ㄤ簡
		int biClrImportant = 0;// 浣嶅浘鏄剧ず杩囩▼涓噸瑕佺殑棰滆壊鏁�(50-53瀛楄妭)锛屽鏋滀负0鐨勮瘽锛岃鏄庡叏閮ㄩ噸瑕�

		dos.write(changeByte(biSize), 0, 4);// 杈撳叆淇℃伅澶存暟鎹殑鎬诲瓧鑺傛暟
		dos.write(changeByte(biWidth), 0, 4);// 杈撳叆浣嶅浘鐨勫
		dos.write(changeByte(biHeight), 0, 4);// 杈撳叆浣嶅浘鐨勯珮
		dos.write(changeByte(biPlanes), 0, 2);// 杈撳叆浣嶅浘鐨勭洰鏍囪澶囩骇鍒�
		dos.write(changeByte(biBitcount), 0, 2);// 杈撳叆姣忎釜鍍忕礌鍗犳嵁鐨勫瓧鑺傛暟
		dos.write(changeByte(biCompression), 0, 4);// 杈撳叆浣嶅浘鐨勫帇缂╃被鍨�
		dos.write(changeByte(biSizeImage), 0, 4);// 杈撳叆浣嶅浘鐨勫疄闄呭ぇ灏�
		dos.write(changeByte(biXPelsPerMeter), 0, 4);// 杈撳叆浣嶅浘鐨勬按骞冲垎杈ㄧ巼
		dos.write(changeByte(biYPelsPerMeter), 0, 4);// 杈撳叆浣嶅浘鐨勫瀭鐩村垎杈ㄧ巼
		dos.write(changeByte(biClrUsed), 0, 4);// 杈撳叆浣嶅浘浣跨敤鐨勬�婚鑹叉暟
		dos.write(changeByte(biClrImportant), 0, 4);// 杈撳叆浣嶅浘浣跨敤杩囩▼涓噸瑕佺殑棰滆壊鏁�

		for (int i = 0; i < 256; i++) {
			dos.writeByte(i);
			dos.writeByte(i);
			dos.writeByte(i);
			dos.writeByte(0);
		}

		byte[] filter = null;
		if (w > nWidth)
		{
			filter = new byte[w-nWidth];
		}
		
		for(int i=0;i<nHeight;i++)
		{
			dos.write(imageBuf, (nHeight-1-i)*nWidth, nWidth);
			if (w > nWidth)
				dos.write(filter, 0, w-nWidth);
		}
		dos.flush();
		dos.close();
		fos.close();
	}

	public static byte[] changeByte(int data) {
		return intToByteArray(data);
	}
	
	public static byte[] intToByteArray (final int number) {
		byte[] abyte = new byte[4];  
	    // "&" 涓庯紙AND锛夛紝瀵逛袱涓暣鍨嬫搷浣滄暟涓搴斾綅鎵ц甯冨皵浠ｆ暟锛屼袱涓綅閮戒负1鏃惰緭鍑�1锛屽惁鍒�0銆�  
	    abyte[0] = (byte) (0xff & number);  
	    // ">>"鍙崇Щ浣嶏紝鑻ヤ负姝ｆ暟鍒欓珮浣嶈ˉ0锛岃嫢涓鸿礋鏁板垯楂樹綅琛�1  
	    abyte[1] = (byte) ((0xff00 & number) >> 8);  
	    abyte[2] = (byte) ((0xff0000 & number) >> 16);  
	    abyte[3] = (byte) ((0xff000000 & number) >> 24);  
	    return abyte; 
	}	 
		 
	public static int byteArrayToInt(byte[] bytes) {
			int number = bytes[0] & 0xFF;  
		    // "|="鎸変綅鎴栬祴鍊笺��  
		    number |= ((bytes[1] << 8) & 0xFF00);  
		    number |= ((bytes[2] << 16) & 0xFF0000);  
		    number |= ((bytes[3] << 24) & 0xFF000000);  
		    return number;  
		 }
	
	private class WorkThread extends Thread {
	        @Override
	        public void run() {
	            super.run();
	            int ret = 0;
	            while (!mbStop) {
	            	templateLen[0] = 2048;
	            	if (0 == (ret = FingerprintSensorEx.AcquireFingerprint(mhDevice, imgbuf, template, templateLen)))
	            	{
	            		if (nFakeFunOn == 1)
                    	{
                    		byte[] paramValue = new byte[4];
            				int[] size = new int[1];
            				size[0] = 4;
            				int nFakeStatus = 0;
            				//GetFakeStatus
            				ret = FingerprintSensorEx.GetParameters(mhDevice, 2004, paramValue, size);
            				nFakeStatus = byteArrayToInt(paramValue);
            				System.out.println("ret = "+ ret +",nFakeStatus=" + nFakeStatus);
            				if (0 == ret && (byte)(nFakeStatus & 31) != 31)
            				{
            					textArea.setText("Is a fake-finer?");
            					return;
            				}
                    	}
                    	OnCatpureOK(imgbuf);
                    	OnExtractOK(template, templateLen[0]);
                    	String strBase64 = FingerprintSensorEx.BlobToBase64(template, templateLen[0]);
                    	System.out.println("strBase64=" + strBase64);
                    	FPDataArea.setText(strBase64);
	            	}
	                try {
	                    Thread.sleep(500);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }

	            }
	        }

			private void runOnUiThread(Runnable runnable) {
				// TODO Auto-generated method stub
				
			}
	    }
	
		
	private void OnCatpureOK(byte[] imgBuf)
		{
			try {
				/*LeftFPImgName= "left"+"_"+leftFingerChosen+"_"+StudentName+"_"+StudentNumber+"_"+StudentUniversity;
				LeftFPImgpath = "data/images/fingerPrints/TempFPrintData/";
				FPImgExt = ".bmp";*/
				if(RightHandEn != true) {
				if(LFingerChosen.getText()==null) {
					LeftFPImgName= "left"+"_"+LeftFingerChosenString+"_"+StudentName+"_"+StudentNumber+"_"+StudentUniversity;
				}else {
					LeftFPImgName= "left"+"_"+LFingerChosen.getText()+"_"+StudentName+"_"+StudentNumber+"_"+StudentUniversity;
				}
				writeBitmap(imgBuf, fpWidth, fpHeight, LeftFPImgpath+LeftFPImgName +FPImgExt);
				LeftFingerbtnImgArea.setIcon(new ImageIcon(ImageIO.read(new File(LeftFPImgpath+LeftFPImgName +FPImgExt))));
				//System.out.println(LeftFPImgpath+LeftFPImgName +FPImgExt);
				}
				else {
					if(RFingerChosen.getText()==null) {
						RightFPImgName= "right"+"_"+RightFingerChosenString+"_"+StudentName+"_"+StudentNumber+"_"+StudentUniversity;
					}else {
						RightFPImgName= "right"+"_"+RFingerChosen.getText()+"_"+StudentName+"_"+StudentNumber+"_"+StudentUniversity;
					}
					writeBitmap(imgBuf, fpWidth, fpHeight, RightFPImgpath+RightFPImgName +FPImgExt);
					RightFingerbtnImgArea.setIcon(new ImageIcon(ImageIO.read(new File(RightFPImgpath+RightFPImgName +FPImgExt))));
					//System.out.println(RightFPImgpath+RightFPImgName +FPImgExt);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void OnExtractOK(byte[] template, int len)
		{
			if(bRegister)
			{
				int[] fid = new int[1];
				int[] score = new int [1];
                int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
                if (ret == 0)
                {
                	Font Errorfont = new Font("Serif", Font.BOLD, 12);
                	textArea.setForeground(Color.red);
                	textArea.setFont(Errorfont);
                    textArea.setText("Warning! "+ fid[0] +" Similar fingerprint (s) already exists !");
                    
                    Font ErrorSmallfont = new Font("Calibri", Font.BOLD, 11);
                    LeftFingChosenText.setForeground(Color.red);
                    LeftFingChosenText.setFont(ErrorSmallfont);
                    
                    
                    if(RightHandEn != true) {VerifyLeftFinger(); LeftFingChosenText.setText("Fingerprint already exists. If you think its an error, place the right hand finger to confirm");
    				}
					else {VerifyRightFinger();RightFingChosenText.setText("Fingerprint already exists. If you think its an error, place the left hand finger to confirm");
    				}
  
                    
                    bRegister = false;
                    enroll_idx = 0;
                    return;
                }
                if (enroll_idx > 0 && FingerprintSensorEx.DBMatch(mhDB, regtemparray[enroll_idx-1], template) <= 0)
                {
                	textArea.setText("please press the same finger " +(enroll_cnt - enroll_idx)+" times for the enrollment");
                    return;
                }
                System.arraycopy(template, 0, regtemparray[enroll_idx], 0, 2048);
                enroll_idx++;
                if (enroll_idx == enroll_cnt) {
                	int[] _retLen = new int[1];
                    _retLen[0] = 2048;
                    byte[] regTemp = new byte[_retLen[0]];
                    
                    if (0 == (ret = FingerprintSensorEx.DBMerge(mhDB, regtemparray[0], regtemparray[1], regtemparray[2], regTemp, _retLen)) &&
                    		0 == (ret = FingerprintSensorEx.DBAdd(mhDB, iFid, regTemp))) {
                    	iFid++;
                    	cbRegTemp = _retLen[0];
                        System.arraycopy(regTemp, 0, lastRegTemp, 0, cbRegTemp);
                        String strBase64 = FingerprintSensorEx.BlobToBase64(regTemp, cbRegTemp);
                        //Base64 Template
                        Font fontBlue = new Font("Calibri", Font.BOLD, 20);
                        textArea.setForeground(Color.black);
                        textArea.setFont(fontBlue);
                        if(RightHandEn != true) {textArea.setText("Now Enroll One Right Finger" );}
                        else {textArea.setText(StudentName +"'s right hand fingerprint has been stored");}
                        
                        Font fontBlue1 = new Font("Calibri", Font.BOLD, 12);
                        LeftFingChosenText.setForeground(Color.blue);
                        LeftFingChosenText.setFont(fontBlue1);
                        if(RightHandEn != true) {LeftFingChosenText.setText(StudentName +"'s left hand fingerprint has been stored"); VerifyLeftFinger();}
                        else {RightFingChosenText.setText("Right hand finger Enrolled successfully.");}
                         
                        
                        RightHandEn = true;
                        enroll_idx = 0;
                        enroll_cnt=3;
                        bRegister = true;
                        register();
                        
                        
                        
                    } else {
                    	
                    	
                    	Font Errorfont = new Font("Serif", Font.BOLD, 12);
	                	textArea.setForeground(Color.red);
	                	textArea.setFont(Errorfont);
						textArea.setText("Verify fail, ret=" + ret);
						textArea.setText("Failed to enroll !, error code=" + ret);
						
						LeftFingChosenText.setForeground(Color.red);
						LeftFingChosenText.setFont(Errorfont);
						LeftFingChosenText.setText("enrolment failure !");
		                
						if(RightHandEn !=true){resetLeftFingers(); }
						else {resetRightFingers(); }
                    	
                    }
                    bRegister = false;
                } else {
                	textArea.setText("You need to press " + (enroll_cnt - enroll_idx) + " more times to get consistency");
                }
			}
			else
			{
				if (bIdentify)
				{
					int[] fid = new int[1];
					int[] score = new int [1];
					int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
                    if (ret == 0)
                    {
                    	
                    	Font font = new Font("Serif", Font.BOLD, 12);
	                	textArea.setForeground(Color.blue);
	                	textArea.setFont(font);
	                	textArea.setText("Identity Found, fid=" + fid[0] + ",score=" + score[0]);
						
						
						LeftFingChosenText.setForeground(Color.blue);
						LeftFingChosenText.setFont(font);
						
		                
						if(RightHandEn != true) {VerifyLeftFinger(); LeftFingChosenText.setText("Left fingerprint is registered.");}
						else {VerifyRightFinger(); RightFingChosenText.setText("Right fingerprint is registered.");}
                    	
                    }
                    else
                    {
                    	//textArea.setText("Identify fail, errcode=" + ret);
                    	
                    	Font Errorfont = new Font("Serif", Font.BOLD, 12);
	                	textArea.setForeground(Color.red);
	                	textArea.setFont(Errorfont);
						textArea.setText("Verify fail, ret=" + ret);
						textArea.setText("Failed to idenfify, errcode=" + ret);
						
						LeftFingChosenText.setForeground(Color.red);
						LeftFingChosenText.setFont(Errorfont);
						LeftFingChosenText.setText("Identification failed !");
		                
						if(RightHandEn !=true){resetLeftFingers(); }
						else {resetRightFingers(); }
						
                    }
                        
				}
				else
				{
					if(cbRegTemp <= 0)
					{
						Font font = new Font("Serif", Font.BOLD, 12);
	                	textArea.setForeground(Color.black);
	                	textArea.setFont(font);
						textArea.setText("Please enroll (register) first");
						
						LeftFingChosenText.setForeground(Color.black);
						LeftFingChosenText.setFont(font);
						LeftFingChosenText.setText("Press 'enroll' button to start registering");
		                
					}
					else
					{
						int ret = FingerprintSensorEx.DBMatch(mhDB, lastRegTemp, template);
						if(ret > 0)
						{
							
							Font font = new Font("Serif", Font.BOLD, 12);
		                	textArea.setForeground(Color.blue);
		                	textArea.setFont(font);
		                	textArea.setText("Fingerprint Match Found , Score=" + ret);
							
							
							LeftFingChosenText.setForeground(Color.blue);
							LeftFingChosenText.setFont(font);
							
			                
							if(RightHandEn != true) {VerifyLeftFinger(); LeftFingChosenText.setText("Fingerprint Verified Successfully.");}
							else {VerifyRightFinger();RightFingChosenText.setText("Fingerprint Verified Successfully.");}
							
						}
						else
						{
							//textArea.setText("Verify fail, ret=" + ret);
							Font Errorfont = new Font("Serif", Font.BOLD, 12);
		                	textArea.setForeground(Color.red);
		                	textArea.setFont(Errorfont);
							textArea.setText("Verify fail, ret=" + ret);
							
							
							LeftFingChosenText.setForeground(Color.red);
							LeftFingChosenText.setFont(Errorfont);
							
			                
							if(RightHandEn !=true){resetLeftFingers(); LeftFingChosenText.setText("Verification Failure. No fingerprint match found !");}
							else {resetRightFingers(); RightFingChosenText.setText("Verification Failure. No fingerprint match found !");}
							
							
						}
					}
				}
			}
		}
		
	

		
		



		/*public static void main(String[] args) {
			new BiometricSystem().launchFrame();*/
			
		/*	String[] processingArgs = {"Processing"};
			Processing mySketch = new Processing();
			PApplet.runSketch(processingArgs, mySketch);
			
			Processing var= new Processing();
			System.out.print("the value is " + var.var);*/
		/*}*/
		
		int jav = 60;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		 public final static JFrame getMainFrame(){
		        return FPframe;
		    }
}



class RadioListener implements ActionListener, //only one event type needed
   ChangeListener, //for curiosity only
   ItemListener {  //for curiosity only
	
	static String LeftFingerChosenString=FingerPrintScanner.LeftFingerChosenString;
public void actionPerformed(ActionEvent e) {
String factoryName = null;

System.out.print("ActionEvent received: ");
if (e.getActionCommand() == "LeftThumb") {
System.out.println("LeftThumb" + " pressed.");
FingerPrintScanner.LFingerChosen.setText("Thumb");
FingerPrintScanner.LeftFingChosenImage.setIcon(FingerPrintScanner.ic_leftThumbNotOk);
FingerPrintScanner.LeftFingerChosenString="Thumb";
LeftFingerChosenString="Thumb";
System.out.println("Finger chosen: "+LeftFingerChosenString);

} 

if (e.getActionCommand() == "LeftIndex") {
System.out.println("LeftIndex" + " pressed.");
FingerPrintScanner.LFingerChosen.setText("Index");
FingerPrintScanner.LeftFingChosenImage.setIcon(FingerPrintScanner.ic_leftIndexNotOk);
FingerPrintScanner.LeftFingerChosenString="Index";
LeftFingerChosenString="Index";
System.out.println("Finger chosen: "+LeftFingerChosenString);
} 

if (e.getActionCommand() == "LeftMiddle") {
System.out.println("LeftMiddle" + " pressed.");
FingerPrintScanner.LFingerChosen.setText("Middle");
FingerPrintScanner.LeftFingChosenImage.setIcon(FingerPrintScanner.ic_leftMiddleNotOk);
FingerPrintScanner.LeftFingerChosenString="Middle";
LeftFingerChosenString="Middle";
System.out.println("Finger chosen: "+LeftFingerChosenString);
} 

if (e.getActionCommand() == "LeftRing") {
System.out.println("LeftRing" + " pressed.");
FingerPrintScanner.LFingerChosen.setText("Ring");
FingerPrintScanner.LeftFingChosenImage.setIcon(FingerPrintScanner.ic_leftRingNotOk);
FingerPrintScanner.LeftFingerChosenString="Ring";
LeftFingerChosenString="Ring";
System.out.println("Finger chosen: "+LeftFingerChosenString);
} 

if (e.getActionCommand() == "LeftSmall") {
System.out.println("LeftSmall" + " pressed.");
FingerPrintScanner.LFingerChosen.setText("Small");
FingerPrintScanner.LeftFingChosenImage.setIcon(FingerPrintScanner.ic_leftSmallNotOk);
FingerPrintScanner.LeftFingerChosenString="Small";
LeftFingerChosenString="Small";
System.out.println("Finger chosen: "+LeftFingerChosenString);
} 

//**********************************88right
if (e.getActionCommand() == "RightThumb") {
System.out.println("RightThumb" + " pressed.");
FingerPrintScanner.RFingerChosen.setText("Thumb");
FingerPrintScanner.RightFingChosenImage.setIcon(FingerPrintScanner.ic_rightThumbNotOk);
} 

if (e.getActionCommand() == "RightIndex") {
System.out.println("RightIndex" + " pressed.");
FingerPrintScanner.RFingerChosen.setText("Index");
FingerPrintScanner.RightFingChosenImage.setIcon(FingerPrintScanner.ic_rightIndexNotOk);
} 

if (e.getActionCommand() == "RightMiddle") {
System.out.println("RightMiddle" + " pressed.");
FingerPrintScanner.RFingerChosen.setText("Middle");
FingerPrintScanner.RightFingChosenImage.setIcon(FingerPrintScanner.ic_rightMiddleNotOk);
} 

if (e.getActionCommand() == "RightRing") {
System.out.println("RightRing" + " pressed.");
FingerPrintScanner.RFingerChosen.setText("Ring");
FingerPrintScanner.RightFingChosenImage.setIcon(FingerPrintScanner.ic_rightRingNotOk);
} 

if (e.getActionCommand() == "RightSmall") {
System.out.println("RightSmall" + " pressed.");
FingerPrintScanner.RFingerChosen.setText("Small");
FingerPrintScanner.RightFingChosenImage.setIcon(FingerPrintScanner.ic_rightSmallNotOk);
} 


}

public void itemStateChanged(ItemEvent e) {
/*System.out.println("ItemEvent received: " 
       + e.getItem()
       + " is now "
       + ((e.getStateChange() == ItemEvent.SELECTED)?
      "selected.":"unselected"));*/
}

public void stateChanged(ChangeEvent e) {
/*System.out.println("ChangeEvent received from: "
       + e.getSource());*/
}


}

class ButtonListener implements ActionListener   
{         
public void actionPerformed(ActionEvent ae) 
{
if(ae.getActionCommand().equals("Cancel"))
{
//	FingerPrintScanner.getMainFrame().dispose();
}
if(ae.getActionCommand().equals("Restart"))
{
//Restart();
}

if(ae.getActionCommand().equals("Proceed"))
{
//Proceed();
}



}
}




