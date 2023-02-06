package com.zkteco.biometric;
import com.zkteco.biometric.HuionSign;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import processing.core.PApplet;
import processing.core.PImage;


public class BiometricSystem extends JFrame implements ActionListener{
	
	public static String Surname;
	public static String OtherNames;
	public static String Address;
	
	private JTextArea textArea;
	
	final static JLabel SignatureScanImageButtonLabel = new JLabel();
	final static String CandidateUniversity="MAKERERE UNIVERSITY";
	@SuppressWarnings("deprecation")
	public void launchFrame(){
		this.setLayout (null);
		
	/*	//top  most X- axis buttons
		enroll = new JButton("Enroll");
		verify = new JButton("Verify");
		
		JPanel buttons =  new JPanel(new GridLayout(1, 0)); // single row
		//buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
		//buttons.add(enroll);
		//buttons.add(verify);
		int uRsize = 20;
		this.add(buttons);
		buttons.setBounds(30, 10 + uRsize, 100, 30);
		buttons.setBorder(BorderFactory.createTitledBorder("Horizontal Alignment"));
		*/
		String spaceKComb= "                                  ";
		String spaceKCombsmall= "                 ";

		// menubar
	     JMenuBar MenuBar;
	    // JMenu
	     JMenu FileMenu, Data, AdditionalData, Querry, Settings, Machines, Access, Search, Help;
	     
	     
//**********************************************************************************************************File and its menu
	    // Menu items
	     JMenuItem New_m,
	     				new_m_bioIdForm,
	     				new_m_idDesignSurface,
	     				new_m_project,
	     			
	     openfile, openproject,
	     		   recent_m, 
	     				 recent0, 
	     				 recent2,
	     undo, redo, closewin, closeAllWin, save,saveAs,SaveWrkSpce, cut, copy, paste, move, rename, refresh , reload,
	     print, burn_m,
	               burn_m_Card,burn_m_Other,
	     importproj, export, restart, exit;
	     
        // create a menubar
        MenuBar = new JMenuBar();
        MenuBar.setBackground(new Color(255,255,255,255));
        // create a menu
        FileMenu = new JMenu("File"); 
        FileMenu.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        recent_m = new JMenu("Recent");
      //FileMenu.setBorder(BorderFactory.createLineBorder(Color.black));
       // FileMenu.setBorder(BorderFactory.createLineBorder(Color.decode("#7e3c1a"), 1, false));
       // FileMenu.setOpaque(true);
       // FileMenu.setFont(Font.decode("Arial-BOLD-14"));
       // FileMenu.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(0,0,0,99).brighter());
       // FileMenu.setMnemonic(KeyEvent.VK_F);
       // FileMenu.getAccessibleContext().setAccessibleDescription("File Menu");
        UIManager.put("PopupMenu.border", new LineBorder(new Color(90,90,90,50)));
        // create New-menuitems
        New_m = new JMenu("New");
     		    new_m_bioIdForm= new JMenuItem("Enrollment Form"+spaceKComb,KeyEvent.VK_T);
     	        new_m_bioIdForm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
     	        new_m_bioIdForm.getAccessibleContext().setAccessibleDescription("New items");
   
        		
     	        new_m_idDesignSurface= new JMenuItem("Card Design Surface", new ImageIcon(new ImageIcon("data/images/icons/icon-fingerprint.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
     	        new_m_idDesignSurface.setMnemonic(KeyEvent.VK_B);

        		new_m_project= new JMenuItem("project");
        		
        	       New_m.add(new_m_bioIdForm);
        	       New_m.add(new_m_idDesignSurface);
        	       New_m.add(new_m_project);
        	       
        	       FileMenu.add(New_m);
                           	        
        
        openfile = new JMenuItem("Open File..."+spaceKComb);
        openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK, true));
        openfile.getAccessibleContext().setAccessibleDescription("open file");
        
        
        openproject = new JMenuItem("Open Project...");
        openproject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK, true));
        openproject.getAccessibleContext().setAccessibleDescription("open file");
        
        
        
        recent0 = new JMenuItem("Recent File 0");
        recent2 = new JMenuItem("Recent File 2");
        // add menu items to menu
        
        FileMenu.add(openfile);
        FileMenu.add(openproject);
        
        recent_m.add(recent0);
        recent_m.add(recent2);
        FileMenu.add(recent_m);
        
//**********************************************************************separator
        FileMenu.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50).brighter());
//**********************************************************************end separator
      //**********************************************************************Undo
        undo = new JMenuItem("Undo...");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        FileMenu.add(undo);
        
        redo = new JMenuItem("Redo...");
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        FileMenu.add(redo);
        
//**********************************************************************separator
        FileMenu.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50).brighter());
//**********************************************************************end separator
        
        closewin = new JMenuItem("Close Window");
        closewin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        FileMenu.add(closewin);
        
        closeAllWin = new JMenuItem("Close All Windows");
        closeAllWin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
        FileMenu.add(closeAllWin);
        
//**********************************************************************separator
        FileMenu.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50));
//**********************************************************************end separator
        //*******************save
        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        FileMenu.add(save);
        //**********************************save as
        saveAs = new JMenuItem("Save As...");
        FileMenu.add(saveAs);
        
      //*********************************save workspace
        SaveWrkSpce = new JMenuItem("Save Workspace");
        SaveWrkSpce.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        FileMenu.add(SaveWrkSpce);
//**********************************************************************separator
        FileMenu.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50));
//**********************************************************************end separator
        //cut, copy, paste
      //*********************************cut
        cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        FileMenu.add(cut);
      //**********************************Copy
        copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        FileMenu.add(copy);
        
      //**********************************Paste
        paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        FileMenu.add(paste);
        
//**********************************************************************separator
        FileMenu.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50));
//**********************************************************************end separator
   
      //**********************************move
        move = new JMenuItem("Move...");
        FileMenu.add(move);
      //**********************************rename
        rename = new JMenuItem("Rename...");
        FileMenu.add(rename);
      //**********************************rename
        refresh = new JMenuItem("Refresh");
        FileMenu.add(refresh);
        
        //**********************************Reload
        reload = new JMenuItem("Reload");
        reload.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        FileMenu.add(reload);

//**********************************************************************separator
        FileMenu.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50));
//**********************************************************************end separator
        
      //**********************************print
        print = new JMenuItem("Print...");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        FileMenu.add(print);
      //**********************************Burn
        burn_m_Card = new JMenuItem("Burn to Card"+spaceKComb);  burn_m_Card.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        burn_m_Other = new JMenuItem("Burn to Other...");
        burn_m = new JMenu("Burn...");
       
        burn_m.add(burn_m_Card);burn_m.add(burn_m_Other);
        FileMenu.add(burn_m);
//**********************************************************************separator
        FileMenu.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50));
//**********************************************************************end separator
      //**********************************import
        importproj = new JMenuItem("Import...");
        importproj.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        FileMenu.add(importproj);
      //**********************************export
        export = new JMenuItem("Export...");
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        FileMenu.add(export);
        
      //**********************************************************************separator
        FileMenu.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50));
//**********************************************************************end separator
      //**********************************restart
        restart = new JMenuItem("Restart");
        FileMenu.add(restart);
      //**********************************Exit
        exit = new JMenuItem("Exit");
        FileMenu.add(exit);
        
//********************************************************************************************************** End File and its menu        
//********************************************************************************************************** Data menu        
        JMenuItem init_m_Data,select_m_Database,
                                     select_m_Database_0,
                                     select_m_Database_2,               
                   clr_m_DtDated, bckUp_m_Data, dwnload_m_Data, restart_m_Data, lock_m_Database,quit_m_Data;      
       
        Data = new JMenu("Data");  // Data main menu bar item
        //**********************************Init data
        init_m_Data = new JMenuItem("Initialize All Data");
        //init_m_Data.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        Data.add(init_m_Data);

        //**********************************select database
        select_m_Database_0 = new JMenuItem("Database 0");
        select_m_Database_2 = new JMenuItem("Database 2");
        select_m_Database = new JMenu("Select Database...");
        select_m_Database.add(select_m_Database_0); select_m_Database.add(select_m_Database_2);
        Data.add(select_m_Database);
        
//**********************************************************************separator
        Data.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50).brighter());
//**********************************************************************end separator
      //**********************************clear out dated data
        clr_m_DtDated = new JMenuItem("Clear Outdated Data");
        Data.add(clr_m_DtDated);
        
      //**********************************backup data
        bckUp_m_Data = new JMenuItem("Backup Database");
        Data.add(bckUp_m_Data);
    
//**********************************************************************separator
        Data.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50).brighter());
//**********************************************************************end separator 

        //**********************************download data to local machine
          dwnload_m_Data = new JMenuItem("Download Database...");
          Data.add(dwnload_m_Data);
          
      //**********************************lock system
        lock_m_Database = new JMenuItem("Lock Database");
        Data.add(lock_m_Database);
        
//**********************************************************************separator
        Data.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50).brighter());
//**********************************************************************end separator 
      //**********************************Restart system
        restart_m_Data = new JMenuItem("Restart System");
        Data.add(restart_m_Data);
        
      //**********************************Quit system
        quit_m_Data = new JMenuItem("Quit System"+spaceKComb);
        quit_m_Data.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        Data.add(quit_m_Data);
        
        
//**********************************************************************************************************Additional Data Entry 
        AdditionalData = new JMenu("Additional Data Entry");
        JMenuItem browe_loc, manual_entry;
      //**********************************Browse to location
        browe_loc = new JMenuItem("Browse To Location");
        AdditionalData.add(browe_loc);
      //**********************************Browse to location
        manual_entry = new JMenuItem("Manual Data Entry");
        AdditionalData.add(manual_entry);
        
//**********************************************************************************************************End Data menu Additional data entry         
 
//**********************************************************************************************************Querry 
        Querry = new JMenu("Query");
        JMenuItem querry_record;
      //**********************************Query Records
        querry_record = new JMenuItem("Query Records");
        Querry.add(querry_record);
      
//**********************************************************************************************************End Querry
    
//**********************************************************************************************************Settings 
        Settings = new JMenu("Config/Setting");
        JMenuItem Access_set, 
                            access_set_admin,access_set_Personnel,
                  Databse_set, Time_set, Auto_DatEntry;
      //**********************************Access settings
        Access_set = new JMenu("Access Settings"+spaceKComb);
        access_set_admin = new JMenuItem("Administrators");
        access_set_Personnel = new JMenuItem("Personnel/Students");
        Access_set.add(access_set_admin);  Access_set.add(access_set_Personnel);
        Settings.add(Access_set);
      //**********************************Time settings  
        Time_set = new JMenuItem("Time and TimeZone");
        Settings.add(Time_set);
      //**********************************Database settings  
//**********************************************************************separator
        Settings.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50).brighter());
//**********************************************************************end separator
        
        Databse_set = new JMenuItem("Database Settings");
        Settings.add(Databse_set);
      //**********************************Automatic Data entry
        Auto_DatEntry = new JMenuItem("Automatic Form Data Entry");
        Settings.add(Auto_DatEntry);
//**********************************************************************************************************End settings
//**********************************************************************************************************Machine Management
        Machines = new JMenu("Machine Management");
        JMenuItem Conn_USB, 
                          mt_USB,unMt_USB,
                  conn_Utility,
                          con_Device,
                          disCon_Device,
                  upload_data_Machine, download_data_machine,Machine_permissions,
                  sync_machine, 
                           sync_this,
                           sync_other;
      //**********************************Connect?disconnect USB Devices
        Conn_USB = new JMenu("Connected USB Devices" + spaceKCombsmall);
                mt_USB = new JMenuItem("Connect USB Device");
                unMt_USB = new JMenuItem("Disconnect USB Device");
        Conn_USB.add(mt_USB);  Conn_USB.add(unMt_USB);
        
    //**********************************Connect?disconnect remote Utility Devices
        conn_Utility = new JMenu("Connected Remote Machines");
        con_Device = new JMenuItem("Connect Remote Device");
        disCon_Device = new JMenuItem("Disconnect Remote Device");
        conn_Utility.add(con_Device);  conn_Utility.add(disCon_Device); 
        
        Machines.add(Conn_USB); Machines.add(conn_Utility);
       
//**********************************************************************separator
        Machines.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50).brighter());
//**********************************************************************end separator
        
    //**********************************Upload Data to machine
        upload_data_Machine = new JMenuItem("Upload Data to Machine");
        Machines.add(upload_data_Machine);
    //**********************************Download Data from machine
        download_data_machine = new JMenuItem("Download Data From Machine");
        Machines.add(download_data_machine);
        
//**********************************************************************separator
        Machines.add(new JSeparator(SwingConstants.HORIZONTAL)).setForeground(new Color(60,60,60,50).brighter());
//**********************************************************************end separator
        
    //**********************************Machine Permissions
        Machine_permissions = new JMenuItem("Machine Permissions");
        Machines.add(Machine_permissions);
    //**********************************Machine Sync
        sync_machine = new JMenu("Sync machine Data with PC");
                  sync_this= new JMenuItem("With This Computer");
                  sync_other= new JMenuItem("With Other Computer");
                  sync_machine.add(sync_this);sync_machine.add(sync_other);
        Machines.add(sync_machine);
      
//**********************************************************************************************************End Machine management
//**********************************************************************************************************Access Management
        Access = new JMenu("AccessManage");
        JMenuItem Gen_Mnge,ProMnge,
                                  Pro_Bsc_Mng,Pro_Rmt_mng,Pro_User_prev,
                           StrdMnge,
                                  Strd_Bsc_Mng,Strd_Rmt_mng,Strd_User_prev;
      //**********************************General management
        Gen_Mnge = new JMenuItem("General Access Manage");
        Access.add(Gen_Mnge);
      //**********************************Pro management
        ProMnge = new JMenu("Pro Access Manage"+spaceKComb);
        
        Pro_Bsc_Mng = new JMenuItem("Basic Configuration");
        ProMnge.add(Pro_Bsc_Mng);
        
        Pro_Rmt_mng = new JMenuItem("Machine Access setting");
        ProMnge.add(Pro_Rmt_mng);
        
        Pro_User_prev = new JMenuItem("User Previlege Setting");
        ProMnge.add(Pro_User_prev);
        Access.add(ProMnge);
        
      //**********************************Standard management
        StrdMnge = new JMenu("Standard Access Control Manage");
        
        Strd_Bsc_Mng = new JMenuItem("Basic Configuration");
        StrdMnge.add(Strd_Bsc_Mng);
        
        Strd_Rmt_mng = new JMenuItem("Standard Machine Access");
        StrdMnge.add(Strd_Rmt_mng);
        
        Strd_User_prev = new JMenuItem("standard User Previlege");
        StrdMnge.add(Strd_User_prev);
        Access.add(StrdMnge);
              
//**********************************************************************************************************End Access Management 

//**********************************************************************************************************Search
        Search = new JMenu("Search");
        JMenuItem Gen_Serch,advSerch;
        Gen_Serch = new JMenuItem("General Search");
        advSerch  = new JMenuItem("Advanced Search");
        Search.add(Gen_Serch); Search.add(advSerch);
//**********************************************************************************************************End Search
        
//**********************************************************************************************************help
        Help = new JMenu("Help");
        JMenuItem Soft_help,Online_help;
        Soft_help = new JMenuItem("About Software");
        Online_help  = new JMenuItem("Online Resources");
        Help.add(Soft_help); Help.add(Online_help);
//**********************************************************************************************************End help
        
        
//################################################add menus to menu bar
        MenuBar.add(FileMenu); MenuBar.add(Data); MenuBar.add(AdditionalData); MenuBar.add(Querry); MenuBar.add(Settings);
        MenuBar.add(Machines); MenuBar.add(Access); MenuBar.add(Search); MenuBar.add(Help);
       
//################################################### add menubar to frame
        this.setJMenuBar(MenuBar);
        pack();
        
        
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Action Listeners
        New_m.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
			     
	            // set the label to the menuItem that is selected
				textArea.setText(s + " selected");
			}
		});
		

        
        
        
        
        
        
//********************************************************************************************Upper JPanel Buttons 
       JPanel UpperBtnsPanel=new JPanel((new FlowLayout(FlowLayout.LEFT)));  
       UpperBtnsPanel.setBounds(0,-5,Toolkit.getDefaultToolkit().getScreenSize().width,60);    
       UpperBtnsPanel.setBackground(new Color(255,255,255,255));  
    
       //************************************************************ button Personnel  
       final JButton btn_Personnel = new JButton("Personnel", new ImageIcon(new ImageIcon("data/images/icons/icon_Personel.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
       btn_Personnel.setBorderPainted(false);
       btn_Personnel.setFocusPainted(false);
       btn_Personnel.setContentAreaFilled(false);
       btn_Personnel.setOpaque(false);
       btn_Personnel.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btn_Personnel.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	 // btn_Personnel.setBackground(Color.ORANGE);
        	  btn_Personnel.setContentAreaFilled(true);
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 // btn_Personnel.setBackground(null);
        	  btn_Personnel.setContentAreaFilled(false);
          }
       });
       btn_Personnel.setVerticalTextPosition(SwingConstants.BOTTOM);
       btn_Personnel.setHorizontalTextPosition(SwingConstants.CENTER);
       btn_Personnel.setBounds(0,0,0,0);
       
       //****************************************************************************************** button IDS
       
       final JButton btn_Identities = new JButton("ID Cards", new ImageIcon(new ImageIcon("data/images/icons/icon_id.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
       btn_Identities.setBorderPainted(false);
       btn_Identities.setFocusPainted(false);
       btn_Identities.setContentAreaFilled(false);
       btn_Identities.setOpaque(false);
       btn_Identities.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btn_Identities.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	 // btn_Identities.setBackground(Color.ORANGE);
        	  btn_Identities.setContentAreaFilled(true);
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 // btn_Identities.setBackground(null);
        	  btn_Identities.setContentAreaFilled(false);
          }
       });
       btn_Identities.setVerticalTextPosition(SwingConstants.BOTTOM);
       btn_Identities.setHorizontalTextPosition(SwingConstants.CENTER);
       btn_Identities.setBounds(0,0,0,0);
       
       // add button to panel
       UpperBtnsPanel.add(btn_Personnel); UpperBtnsPanel.add(btn_Identities); 
       // add panel to body
       this.add(UpperBtnsPanel);  

      //********************************************************************************************End button ids
       
//****************************************************************************************** button stats
       
       final JButton btn_stats = new JButton("Statistics", new ImageIcon(new ImageIcon("data/images/icons/icon_stats.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
       btn_stats.setBorderPainted(false);
       btn_stats.setFocusPainted(false);
       btn_stats.setContentAreaFilled(false);
       btn_stats.setOpaque(false);
       btn_stats.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btn_stats.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	 // btn_stats.setBackground(Color.ORANGE);
        	  btn_stats.setContentAreaFilled(true);
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 // btn_stats.setBackground(null);
        	  btn_stats.setContentAreaFilled(false);
          }
       });
       btn_stats.setVerticalTextPosition(SwingConstants.BOTTOM);
       btn_stats.setHorizontalTextPosition(SwingConstants.CENTER);
       btn_stats.setBounds(0,0,0,0);

       //********************************************************************************************End Statistics button      

       // add button to panel
       UpperBtnsPanel.add(btn_Personnel); UpperBtnsPanel.add(btn_Identities); UpperBtnsPanel.add(btn_stats); 
//**********************************************************************separator
       JSeparator JpanSep0 = new JSeparator(SwingConstants.VERTICAL);
        JpanSep0.setPreferredSize(new Dimension(3,60));
        JpanSep0.setForeground(new Color(60,60,60,50));
       UpperBtnsPanel.add(JpanSep0);
//**********************************************************************end separator
       
//****************************************************************************************** button connected devices
       
       final JButton btn_conDevice = new JButton("Machines", new ImageIcon(new ImageIcon("data/images/icons/icon_conDevice.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
       btn_conDevice.setBorderPainted(false);
       btn_conDevice.setFocusPainted(false);
       btn_conDevice.setContentAreaFilled(false);
       btn_conDevice.setOpaque(false);
       btn_conDevice.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btn_conDevice.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	 // btn_conDevice.setBackground(Color.ORANGE);
        	  btn_conDevice.setContentAreaFilled(true);
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 // btn_conDevice.setBackground(null);
        	  btn_conDevice.setContentAreaFilled(false);
          }
       });
       btn_conDevice.setVerticalTextPosition(SwingConstants.BOTTOM);
       btn_conDevice.setHorizontalTextPosition(SwingConstants.CENTER);
       btn_conDevice.setBounds(0,0,0,0);

       //********************************************************************************************End connected devices button
   
//****************************************************************************************** button Disconnect Machine
       
       final JButton btn_diconMac = new JButton("Disconnect", new ImageIcon(new ImageIcon("data/images/icons/icon_diconMac.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
       btn_diconMac.setBorderPainted(false);
       btn_diconMac.setFocusPainted(false);
       btn_diconMac.setContentAreaFilled(false);
       btn_diconMac.setOpaque(false);
       btn_diconMac.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btn_diconMac.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	 // btn_diconMac.setBackground(Color.ORANGE);
        	  btn_diconMac.setContentAreaFilled(true);
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 // btn_diconMac.setBackground(null);
        	  btn_diconMac.setContentAreaFilled(false);
          }
       });
       btn_diconMac.setVerticalTextPosition(SwingConstants.BOTTOM);
       btn_diconMac.setHorizontalTextPosition(SwingConstants.CENTER);
       btn_diconMac.setBounds(0,0,0,0);

 //********************************************************************************************End button Disconnect machine
//****************************************************************************************** button Connect Machine
       
       final JButton btn_conMac = new JButton("Connect", new ImageIcon(new ImageIcon("data/images/icons/icon_conMac.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
       btn_conMac.setBorderPainted(false);
       btn_conMac.setFocusPainted(false);
       btn_conMac.setContentAreaFilled(false);
       btn_conMac.setOpaque(false);
       btn_conMac.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btn_conMac.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	 // btn_conMac.setBackground(Color.ORANGE);
        	  btn_conMac.setContentAreaFilled(true);
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 // btn_conMac.setBackground(null);
        	  btn_conMac.setContentAreaFilled(false);
          }
       });
       btn_conMac.setVerticalTextPosition(SwingConstants.BOTTOM);
       btn_conMac.setHorizontalTextPosition(SwingConstants.CENTER);
       btn_conMac.setBounds(0,0,0,0);

 //********************************************************************************************End button connect machine
       
       // add button to panel
       UpperBtnsPanel.add(btn_conDevice); UpperBtnsPanel.add(btn_diconMac); UpperBtnsPanel.add(btn_conMac);
       
//**********************************************************************separator
       JSeparator JpanSep2 = new JSeparator(SwingConstants.VERTICAL);
       JpanSep2.setPreferredSize(new Dimension(3,60));
       JpanSep2.setForeground(new Color(60,60,60,50));
       UpperBtnsPanel.add(JpanSep2);
//**********************************************************************end separator
       
//****************************************************************************************** button Connect Machine
       
       final JButton btn_quit = new JButton("Quit System", new ImageIcon(new ImageIcon("data/images/icons/icon_quit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
       btn_quit.setBorderPainted(false);
       btn_quit.setFocusPainted(false);
       btn_quit.setContentAreaFilled(false);
       btn_quit.setOpaque(false);
       btn_quit.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btn_quit.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	 // btn_quit.setBackground(Color.ORANGE);
        	  btn_quit.setContentAreaFilled(true);
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 // btn_quit.setBackground(null);
        	  btn_quit.setContentAreaFilled(false);
          }
       });
       btn_quit.setVerticalTextPosition(SwingConstants.BOTTOM);
       btn_quit.setHorizontalTextPosition(SwingConstants.CENTER);
       btn_quit.setBounds(0,0,0,0);

 //********************************************************************************************End button connect machine
       // add button to panel
       UpperBtnsPanel.add(btn_quit);
       
       
// add profile pic to right side of the bar
      String ProfileName = "Proscovia"; 
      System.out.println("");
      System.out.println("Variabeles passed to Designing Page");
      System.out.println("\n");
      System.out.println("Surname:" +Surname);
      System.out.println("Other Names:" +OtherNames);
      System.out.println("Address:" +Address);
      
      System.out.println("Setting Profile....");
     
      String ProfPicSpace = "                                                   ";
      JLabel ProfilePicSpacingLabel=new JLabel(ProfPicSpace+ProfPicSpace +ProfPicSpace +ProfPicSpace);  
      ProfilePicSpacingLabel.setVisible(true);
     
      UpperBtnsPanel.add(ProfilePicSpacingLabel, BorderLayout.LINE_END);
      
      Dimension profilePicSize = new Dimension(50,50);
      int profileImageSize = 50;
      JLabel ProfilePic = new ProfilePic();
      ProfilePic.setPreferredSize(profilePicSize);
      ProfilePic.setCursor(new Cursor(Cursor.HAND_CURSOR));
      ProfilePic.setIcon(new ImageIcon(new ImageIcon("data/images/ProfilePics/profile1.png").getImage().getScaledInstance(profileImageSize, profileImageSize, Image.SCALE_DEFAULT)));
      ProfilePic.setToolTipText("My Profile   [ "+Surname + " " + OtherNames+" ]  ");
     
      
      UpperBtnsPanel.add(ProfilePic);
     
 //######################################################## add Upper panel to body
       this.add(UpperBtnsPanel);  
       
       
       
       
 //########*************#######**********########*************##########**********Left Most JPanel Items
    // create empty border
       Border emptyBorder = BorderFactory.createEmptyBorder();
       
       JPanel BodyBGPanel=new JPanel((new FlowLayout(FlowLayout.LEFT)));  //277
       //BodyBGPanel.setBounds(0,UpperBtnsPanel.getSize().height-3,Toolkit.getDefaultToolkit().getScreenSize().width/5,Toolkit.getDefaultToolkit().getScreenSize().height-(UpperBtnsPanel.getSize().height-3));       
       BodyBGPanel.setBounds(0,UpperBtnsPanel.getSize().height-5,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-(UpperBtnsPanel.getSize().height-3));       
       BodyBGPanel.setBackground(new Color(0,0,255,30));
       //BodyBGPanel.setBorder(emptyBorder);
      // BodyBGPanel.setBorder(BorderFactory.createLineBorder(Color.red,3));


     //############################################################################Panel for Personnel Button    
       String spaceLP = "      ";  int LPanelButWidth = 30; int LdropPanBHeight = 28; int LButtdropWidth = 25;
       int LPanPersonelHeight = 200, LPanSettingsHeight=200, LPanSheduleTasksHeight=200, LPanData_manageHeight=200, LPanRemote_machineHeight=200,
           LPanNetwork_manageHeight=200;
    
       // create a panel
       JPanel p1 = new JPanel(); 
       JPanel p = new JPanel();
       JPanel p2 = new JPanel();
       JPanel p3 = new JPanel();
       
       final JPanel p1ScrollMat = new JPanel();  // scrollable JPanel On top of P1
 
       // remove gaps between Left and right split panels
       FlowLayout layout = (FlowLayout)p.getLayout();
       layout.setVgap(-2);layout.setHgap(-2);
       p.setBackground(null);
       // create text areas
       JTextArea t1 = new JTextArea(20, 20); //56
       JTextArea t2 = new JTextArea(10, 10);
       JTextArea t3 = new JTextArea(20,20);
       JTextArea t4 = new JTextArea(20,20);
 
       // set texts
       t1.setText("this is first text area");
       t2.setText("this is second text area");
       t3.setText("this is third text area");
       t4.setText("this is fourth text area");
       // add text area to panel
      
       int LButtdropXsize = (int) ((int)BodyBGPanel.getSize().width/4.2);  // global variable to adjust P1 and split panels
       int LButtHidropGHideOffset = 50;
       
       p1ScrollMat.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       p1ScrollMat.setBorder(emptyBorder); //
       p1ScrollMat.setBorder(BorderFactory.createLineBorder(Color.ORANGE,0));
       //p1ScrollMat.setBackground(Color.red);
       //p1ScrollMat.setPreferredSize(new Dimension(LPanelButWidth, 500));
       p1ScrollMat.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanPersonelHeight));
     
       
       
        p1.setLayout(new BorderLayout());
        p1.add(BorderLayout.CENTER, new JScrollPane(p1ScrollMat));
        p1.setBorder(BorderFactory.createLineBorder(Color.blue,0));
       // p1.setBackground(Color.blue);
        
    /*   p1.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       p1.setBorder(emptyBorder); // p1.setBorder(BorderFactory.createLineBorder(Color.red,2));
       p1.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset+15, (int)(BodyBGPanel.getSize().height/1.08)));
     */ // p1.add(t1);
      //  p1.add(p1ScrollMat);
      // p.add(t2);
       p2.setLayout((new FlowLayout(FlowLayout.LEFT,0,3)));
       p2.setBorder(emptyBorder);//p2.setBorder(BorderFactory.createLineBorder(Color.red,2));
       p2.setPreferredSize(new Dimension((int)(BodyBGPanel.getSize().width/1), (int)(BodyBGPanel.getSize().height/5)));  //1.27 /2.5//2.1
       //p2.add(t3);
       p3.setLayout((new FlowLayout(FlowLayout.LEFT,0,1)));
       p3.setPreferredSize(new Dimension((int)(BodyBGPanel.getSize().width/1), (int)(BodyBGPanel.getSize().height/1.4))); // 1.17//2.3 //1.4
       p3.setBorder(emptyBorder);//p3.setBorder(BorderFactory.createLineBorder(Color.red,0));
      // p3.add(t4); 
 
       // create a splitpane
       
       JSplitPane sl = new JSplitPane(SwingConstants.VERTICAL, p1, p);//p/p3
       JSplitPane s2 = new JSplitPane(SwingConstants.VERTICAL, p2, p3);
       
       
       p.setBorder(BorderFactory.createLineBorder(Color.red,0));
       p.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       p.add(s2);
       // set Orientation for slider
       sl.setOrientation(SwingConstants.VERTICAL);
       s2.setOrientation(SwingConstants.HORIZONTAL);
 
       sl.setOneTouchExpandable(true);
       s2.setOneTouchExpandable(true);
 
       // set divider location
       //sl.setDividerLocation(70);
       
       // borders
       
       
       
       // set Layout for frame
       BodyBGPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       BodyBGPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
 
       // add panel
       BodyBGPanel.add(sl);
      // BodyBGPanel.add(s2);
       
       boolean Personel_menu_dropped = true;
  
       
//############################################################################Panel for Personnel Button    
     // int LButtdropXsize = BodyBGPanel.getSize().width/5;
       
      JPanel Peronel_menu_heading_panel = new JPanel();
      Peronel_menu_heading_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,3)));
      Peronel_menu_heading_panel.setBorder(emptyBorder); 
      Peronel_menu_heading_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
      Peronel_menu_heading_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LdropPanBHeight));
      Peronel_menu_heading_panel.setBackground(new Color(0,0,0,50));
      p1ScrollMat.add(Peronel_menu_heading_panel);
      
      final JButton Peronel_menu_heading=new JButton("Personnel");
      Peronel_menu_heading.setFont(Font.decode("Arial-BOLD-14"));
      Peronel_menu_heading.setForeground(new Color(0,0,0,255));
      Peronel_menu_heading.setContentAreaFilled(true);
      Peronel_menu_heading.setBorderPainted(true);
      Peronel_menu_heading.setFocusPainted(false);
      Peronel_menu_heading.setToolTipText("Personnel");
      Peronel_menu_heading.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LButtdropWidth));
      Peronel_menu_heading.setVerticalTextPosition(SwingConstants.CENTER);
      Peronel_menu_heading.setHorizontalTextPosition(SwingConstants.CENTER);
      Peronel_menu_heading.setCursor(new Cursor(Cursor.HAND_CURSOR));
      Peronel_menu_heading.setBorder(BorderFactory.createLineBorder(Color.blue,0));
      Peronel_menu_heading.setLayout((new FlowLayout(FlowLayout.RIGHT,2,8)));
      Peronel_menu_heading.addMouseListener(new MouseAdapter() 
      {
         public void mouseEntered(MouseEvent evt) 
         {
       	 // Peronel_menu_heading.setBackground(Color.ORANGE);
       	  //Peronel_menu_heading.setContentAreaFilled(true);
         }
         public void mouseExited(MouseEvent evt) 
         {
       	 // Peronel_menu_heading.setBackground(null);
       	 // cap_biometri.setContentAreaFilled(false);
         }
      });
      
      final ImageIcon menu_dropped_icon =  new ImageIcon(new ImageIcon("data/images/icons/icon_menuShowed.png").getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT));  
      final ImageIcon menu_close_icon = new ImageIcon(new ImageIcon("data/images/icons/icon_menuClosed.png").getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT));  
      
      final JLabel Peronel_menu_heading_icon = new JLabel();
      
     // Peronel_menu_heading_icon.setIcon(menu_dropped_icon);
      
      Peronel_menu_heading.add(Peronel_menu_heading_icon);
      
      Peronel_menu_heading_panel.add(Peronel_menu_heading);
      
 //########################################################################################## End Hide and show Personnel Menu button
 
       
//***************************************************************################# Personnel Items menu
   //    String spaceLP = "      ";  int LPanelButWidth = 30;  int LPanPersonel = 200;
       final JPanel LPanPItem1 = new JPanel();
       LPanPItem1.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       LPanPItem1.setBorder(emptyBorder); 
       LPanPItem1.setBorder(BorderFactory.createLineBorder(Color.blue,0));
       LPanPItem1.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanPersonelHeight));
       LPanPItem1.setBackground(new Color(255,255,255,255));
       LPanPItem1.setVisible(true);
       if(LPanPItem1.isVisible()) {
		   Peronel_menu_heading_icon.setIcon(menu_dropped_icon);
		   }
	   else {
		   Peronel_menu_heading_icon.setIcon(menu_close_icon);
	   }
       p1ScrollMat.add(LPanPItem1);
       
     
  //*********************************************************************************************Biometric Capture button
      final JMenuItem cap_biometri = new JMenuItem(spaceLP+"Capture Biometrics", new ImageIcon(new ImageIcon("data/images/icons/icon_capBio.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
      cap_biometri.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
      cap_biometri.setBorderPainted(false);
      cap_biometri.setFocusPainted(true);
      cap_biometri.setContentAreaFilled(false);
      cap_biometri.setOpaque(true);
      cap_biometri.setBackground(null);
      cap_biometri.setCursor(new Cursor(Cursor.HAND_CURSOR));
      cap_biometri.addMouseListener(new MouseAdapter() 
      {
         public void mouseEntered(MouseEvent evt) 
         {
       	  cap_biometri.setBackground(Color.ORANGE);
       	  //cap_biometri.setContentAreaFilled(true);
         }
         public void mouseExited(MouseEvent evt) 
         {
       	  cap_biometri.setBackground(null);
       	 // cap_biometri.setContentAreaFilled(false);
         }
      });

      
 //*********************************************************************************************Check if registred 
     final JMenuItem Check_Registred = new JMenuItem(spaceLP+"Check Registration", new ImageIcon(new ImageIcon("data/images/icons/icon_checkReg.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
     Check_Registred.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
     Check_Registred.setBorderPainted(false);
     Check_Registred.setFocusPainted(true);
     Check_Registred.setContentAreaFilled(false);
     Check_Registred.setOpaque(true);
     Check_Registred.setBackground(null);
     Check_Registred.setCursor(new Cursor(Cursor.HAND_CURSOR));
     Check_Registred.addMouseListener(new MouseAdapter() 
     {
        public void mouseEntered(MouseEvent evt) 
        {
      	  Check_Registred.setBackground(Color.ORANGE);
      	  //Check_Registred.setContentAreaFilled(true);
        }
        public void mouseExited(MouseEvent evt) 
        {
      	  Check_Registred.setBackground(null);
      	 // Check_Registred.setContentAreaFilled(false);
        }
     });
//****************************************************************************************** End check if registered
     
//*********************************************************************************************Download Record
    final JMenuItem Dowl_Rec = new JMenuItem(spaceLP+"Download Record/Info", new ImageIcon(new ImageIcon("data/images/icons/icon_dwnRec.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
     Dowl_Rec.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
     Dowl_Rec.setBorderPainted(false);
     Dowl_Rec.setFocusPainted(true);
     Dowl_Rec.setContentAreaFilled(false);
     Dowl_Rec.setOpaque(true);
     Dowl_Rec.setBackground(null);
     Dowl_Rec.setCursor(new Cursor(Cursor.HAND_CURSOR));
     Dowl_Rec.addMouseListener(new MouseAdapter() 
     {
        public void mouseEntered(MouseEvent evt) 
        {
      	  Dowl_Rec.setBackground(Color.ORANGE);
      	  //Dowl_Rec.setContentAreaFilled(true);
        }
        public void mouseExited(MouseEvent evt) 
        {
      	  Dowl_Rec.setBackground(null);
      	 // Dowl_Rec.setContentAreaFilled(false);
        }
     });
//******************************************************************************************End Download Record
 //*********************************************************************************************Upload Record
     final JMenuItem Upl_Rec = new JMenuItem(spaceLP+"Upload Record/Info", new ImageIcon(new ImageIcon("data/images/icons/icon_uplRec.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
      Upl_Rec.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
      Upl_Rec.setBorderPainted(false);
      Upl_Rec.setFocusPainted(true);
      Upl_Rec.setContentAreaFilled(false);
      Upl_Rec.setOpaque(true);
      Upl_Rec.setBackground(null);
      Upl_Rec.setCursor(new Cursor(Cursor.HAND_CURSOR));
      Upl_Rec.addMouseListener(new MouseAdapter() 
      {
         public void mouseEntered(MouseEvent evt) 
         {
       	  Upl_Rec.setBackground(Color.ORANGE);
       	  //Upl_Rec.setContentAreaFilled(true);
         }
         public void mouseExited(MouseEvent evt) 
         {
       	  Upl_Rec.setBackground(null);
       	 // Upl_Rec.setContentAreaFilled(false);
         }
      });
 //******************************************************************************************End Upload Record
//*********************************************************************************************Access and Permissions
      final JMenuItem Acc_Perm = new JMenuItem(spaceLP+"Access and Permissions", new ImageIcon(new ImageIcon("data/images/icons/icon_accPermi.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
       Acc_Perm.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
       Acc_Perm.setBorderPainted(false);
       Acc_Perm.setFocusPainted(true);
       Acc_Perm.setContentAreaFilled(false);
       Acc_Perm.setOpaque(true);
       Acc_Perm.setBackground(null);
       Acc_Perm.setCursor(new Cursor(Cursor.HAND_CURSOR));
       Acc_Perm.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	  Acc_Perm.setBackground(Color.ORANGE);
        	  //Acc_Perm.setContentAreaFilled(true);
          }
          public void mouseExited(MouseEvent evt) 
          {
        	  Acc_Perm.setBackground(null);
        	 // Acc_Perm.setContentAreaFilled(false);
          }
       });
 //******************************************************************************************End Access and permissions
//*********************************************************************************************Other Information
       final JMenuItem Other_detail = new JMenuItem(spaceLP+"Other Information", new ImageIcon(new ImageIcon("data/images/icons/icon_extraInf.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        Other_detail.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
        Other_detail.setBorderPainted(false);
        Other_detail.setFocusPainted(true);
        Other_detail.setContentAreaFilled(false);
        Other_detail.setOpaque(true);
        Other_detail.setBackground(null);
        Other_detail.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Other_detail.addMouseListener(new MouseAdapter() 
        {
           public void mouseEntered(MouseEvent evt) 
           {
         	  Other_detail.setBackground(Color.ORANGE);
         	  //Other_detail.setContentAreaFilled(true);
           }
           public void mouseExited(MouseEvent evt) 
           {
         	  Other_detail.setBackground(null);
         	 // Other_detail.setContentAreaFilled(false);
           }
        });
  //******************************************************************************************other Information
      
      
      LPanPItem1.add(cap_biometri);LPanPItem1.add(Check_Registred); LPanPItem1.add(Dowl_Rec); LPanPItem1.add(Upl_Rec);
      LPanPItem1.add(Acc_Perm);LPanPItem1.add(Other_detail);
       
      
      
 //############################################################################ Panel for Settings Button       
      JPanel Settings_menu_heading_panel = new JPanel();
      Settings_menu_heading_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,3)));
      Settings_menu_heading_panel.setBorder(emptyBorder); 
      Settings_menu_heading_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
      Settings_menu_heading_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LdropPanBHeight));
      Settings_menu_heading_panel.setBackground(new Color(0,0,0,50));
      p1ScrollMat.add(Settings_menu_heading_panel);
      
      final JButton Settings_menu_heading=new JButton("Settings");
      Settings_menu_heading.setFont(Font.decode("Arial-BOLD-14"));
      Settings_menu_heading.setForeground(new Color(0,0,0,255));
      Settings_menu_heading.setContentAreaFilled(true);
      Settings_menu_heading.setBorderPainted(true);
      Settings_menu_heading.setFocusPainted(false);
      Settings_menu_heading.setToolTipText("Settings");
      Settings_menu_heading.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LButtdropWidth));
      Settings_menu_heading.setVerticalTextPosition(SwingConstants.CENTER);
      Settings_menu_heading.setHorizontalTextPosition(SwingConstants.CENTER);
      Settings_menu_heading.setCursor(new Cursor(Cursor.HAND_CURSOR));
      Settings_menu_heading.setBorder(BorderFactory.createLineBorder(Color.blue,0));
      Settings_menu_heading.setLayout((new FlowLayout(FlowLayout.RIGHT,2,8)));
      Settings_menu_heading.addMouseListener(new MouseAdapter() 
      {
         public void mouseEntered(MouseEvent evt) 
         {
       	 // Settings_menu_heading.setBackground(Color.ORANGE);
       	  //Settings_menu_heading.setContentAreaFilled(true);
         }
         public void mouseExited(MouseEvent evt) 
         {
       	 // Settings_menu_heading.setBackground(null);
       	 // Settings_menu_heading.setContentAreaFilled(false);
         }
      });
     
      final JLabel Settings_menu_heading_icon = new JLabel();
      
      //Settings_menu_heading_icon.setIcon(menu_dropped_icon);
      
      Settings_menu_heading.add(Settings_menu_heading_icon);
      
      Settings_menu_heading_panel.add( Settings_menu_heading);
      
 //########################################################################################## End Hide and show Settings Menu button
//***************************************************************################# Settings Items Panel 
          final JPanel Settings_menu_panel = new JPanel();
          Settings_menu_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
          Settings_menu_panel.setBorder(emptyBorder); 
          Settings_menu_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
          Settings_menu_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanSettingsHeight));
          Settings_menu_panel.setBackground(new Color(255,255,255,255));
          Settings_menu_panel.setVisible(false);
          if(Settings_menu_panel.isVisible()) {
        	  Settings_menu_heading_icon.setIcon(menu_dropped_icon);
   		   }
   	   else {
   		Settings_menu_heading_icon.setIcon(menu_close_icon);
   	   }
          p1ScrollMat.add(Settings_menu_panel);
          
        
  //*********************************************************************************************Set admin btn menu 
         final JMenuItem Set_admin = new JMenuItem(spaceLP+"Admin Settings", new ImageIcon(new ImageIcon("data/images/icons/icon_capBio.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
         Set_admin.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
         Set_admin.setBorderPainted(false);
         Set_admin.setFocusPainted(true);
         Set_admin.setContentAreaFilled(false);
         Set_admin.setOpaque(true);
         Set_admin.setBackground(null);
         Set_admin.setCursor(new Cursor(Cursor.HAND_CURSOR));
         Set_admin.addMouseListener(new MouseAdapter() 
         {
            public void mouseEntered(MouseEvent evt) 
            {
          	  Set_admin.setBackground(Color.ORANGE);
          	  //Set_admin.setContentAreaFilled(true);
            }
            public void mouseExited(MouseEvent evt) 
            {
          	  Set_admin.setBackground(null);
          	 // Set_admin.setContentAreaFilled(false);
            }
         });
    
//*********************************************************************************************End Set admin btn menu        
        
         Settings_menu_panel.add(Set_admin);
         
         
         
         
//############################################################################ Panel for schedule tasks Button       
         JPanel SheduleTasks_menu_heading_panel = new JPanel();
         SheduleTasks_menu_heading_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,3)));
         SheduleTasks_menu_heading_panel.setBorder(emptyBorder); 
         SheduleTasks_menu_heading_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
         SheduleTasks_menu_heading_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LdropPanBHeight));
         SheduleTasks_menu_heading_panel.setBackground(new Color(0,0,0,50));
         p1ScrollMat.add(SheduleTasks_menu_heading_panel);
         
         final JButton SheduleTasks_menu_heading=new JButton("Task Scheduling");
         SheduleTasks_menu_heading.setFont(Font.decode("Arial-BOLD-14"));
         SheduleTasks_menu_heading.setForeground(new Color(0,0,0,255));
         SheduleTasks_menu_heading.setContentAreaFilled(true);
         SheduleTasks_menu_heading.setBorderPainted(true);
         SheduleTasks_menu_heading.setFocusPainted(false);
         SheduleTasks_menu_heading.setToolTipText("SheduleTasks");
         SheduleTasks_menu_heading.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LButtdropWidth));
         SheduleTasks_menu_heading.setVerticalTextPosition(SwingConstants.CENTER);
         SheduleTasks_menu_heading.setHorizontalTextPosition(SwingConstants.CENTER);
         SheduleTasks_menu_heading.setCursor(new Cursor(Cursor.HAND_CURSOR));
         SheduleTasks_menu_heading.setBorder(BorderFactory.createLineBorder(Color.blue,0));
         SheduleTasks_menu_heading.setLayout((new FlowLayout(FlowLayout.RIGHT,2,8)));
         SheduleTasks_menu_heading.addMouseListener(new MouseAdapter() 
         {
            public void mouseEntered(MouseEvent evt) 
            {
          	 // SheduleTasks_menu_heading.setBackground(Color.ORANGE);
          	  //SheduleTasks_menu_heading.setContentAreaFilled(true);
            }
            public void mouseExited(MouseEvent evt) 
            {
          	 // SheduleTasks_menu_heading.setBackground(null);
          	 // SheduleTasks.setContentAreaFilled(false);
            }
         });
        
         final JLabel SheduleTasks_menu_heading_icon = new JLabel();
         
         //SheduleTasks_menu_heading_icon.setIcon(menu_dropped_icon);
         
         SheduleTasks_menu_heading.add(SheduleTasks_menu_heading_icon);
         
         SheduleTasks_menu_heading_panel.add( SheduleTasks_menu_heading);
         
    //########################################################################################## End Hide and show shedule tasks Menu button
   //***************************************************************################# SheduleTasks Items Panel 
             final JPanel SheduleTasks_menu_panel = new JPanel();
             SheduleTasks_menu_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
             SheduleTasks_menu_panel.setBorder(emptyBorder); 
             SheduleTasks_menu_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
             SheduleTasks_menu_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanSheduleTasksHeight));
             SheduleTasks_menu_panel.setBackground(new Color(255,255,255,255));
             SheduleTasks_menu_panel.setVisible(false);
             if(SheduleTasks_menu_panel.isVisible()) {
            	 SheduleTasks_menu_heading_icon.setIcon(menu_dropped_icon);
         		   }
         	   else {
         		  SheduleTasks_menu_heading_icon.setIcon(menu_close_icon);
         	   }
             p1ScrollMat.add(SheduleTasks_menu_panel);
             
           
     //*********************************************************************************************Schedule task1 btn menu 
            final JMenuItem Schedul_task1 = new JMenuItem(spaceLP+"Shedule Task 1", new ImageIcon(new ImageIcon("data/images/icons/icon_capBio.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
            Schedul_task1.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
            Schedul_task1.setBorderPainted(false);
            Schedul_task1.setFocusPainted(true);
            Schedul_task1.setContentAreaFilled(false);
            Schedul_task1.setOpaque(true);
            Schedul_task1.setBackground(null);
            Schedul_task1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            Schedul_task1.addMouseListener(new MouseAdapter() 
            {
               public void mouseEntered(MouseEvent evt) 
               {
             	  Schedul_task1.setBackground(Color.ORANGE);
             	  //Schedul_task1.setContentAreaFilled(true);
               }
               public void mouseExited(MouseEvent evt) 
               {
             	  Schedul_task1.setBackground(null);
             	 // Schedul_task1.setContentAreaFilled(false);
               }
            });
       
   //*********************************************************************************************End Set shedule task1 btn menu        
           
            SheduleTasks_menu_panel.add(Schedul_task1);
                    
         
//############################################################################ Panel for Data management Button       
            JPanel Data_manage_menu_heading_panel = new JPanel();
            Data_manage_menu_heading_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,3)));
            Data_manage_menu_heading_panel.setBorder(emptyBorder); 
            Data_manage_menu_heading_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
            Data_manage_menu_heading_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LdropPanBHeight));
            Data_manage_menu_heading_panel.setBackground(new Color(0,0,0,50));
            p1ScrollMat.add(Data_manage_menu_heading_panel);
            
            final JButton Data_manage_menu_heading=new JButton("Data management");
            Data_manage_menu_heading.setFont(Font.decode("Arial-BOLD-14"));
            Data_manage_menu_heading.setForeground(new Color(0,0,0,255));
            Data_manage_menu_heading.setContentAreaFilled(true);
            Data_manage_menu_heading.setBorderPainted(true);
            Data_manage_menu_heading.setFocusPainted(false);
            Data_manage_menu_heading.setToolTipText("Data management");
            Data_manage_menu_heading.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LButtdropWidth));
            Data_manage_menu_heading.setVerticalTextPosition(SwingConstants.CENTER);
            Data_manage_menu_heading.setHorizontalTextPosition(SwingConstants.CENTER);
            Data_manage_menu_heading.setCursor(new Cursor(Cursor.HAND_CURSOR));
            Data_manage_menu_heading.setBorder(BorderFactory.createLineBorder(Color.blue,0));
            Data_manage_menu_heading.setLayout((new FlowLayout(FlowLayout.RIGHT,2,8)));
            Data_manage_menu_heading.addMouseListener(new MouseAdapter() 
            {
               public void mouseEntered(MouseEvent evt) 
               {
             	 // Data_manage_menu_heading.setBackground(Color.ORANGE);
             	  //Data_manage_menu_heading.setContentAreaFilled(true);
               }
               public void mouseExited(MouseEvent evt) 
               {
             	 // Data_manage_menu_heading.setBackground(null);
             	 // Data_manage.setContentAreaFilled(false);
               }
            });
           
            final JLabel Data_manage_menu_heading_icon = new JLabel();
            
            //Data_manage_menu_heading_icon.setIcon(menu_dropped_icon);
            
            Data_manage_menu_heading.add(Data_manage_menu_heading_icon);
            
            Data_manage_menu_heading_panel.add( Data_manage_menu_heading);
            
       //########################################################################################## End Hide and show Data manage Menu button
      //***************************************************************################# Data_manage Items Panel 
                final JPanel Data_manage_menu_panel = new JPanel();
                Data_manage_menu_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
                Data_manage_menu_panel.setBorder(emptyBorder); 
                Data_manage_menu_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
                Data_manage_menu_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanData_manageHeight));
                Data_manage_menu_panel.setBackground(new Color(255,255,255,255));
                Data_manage_menu_panel.setVisible(false);
                if(Data_manage_menu_panel.isVisible()) {
                	Data_manage_menu_heading_icon.setIcon(menu_dropped_icon);
            		   }
            	   else {
            		   Data_manage_menu_heading_icon.setIcon(menu_close_icon);
            	   }
                p1ScrollMat.add(Data_manage_menu_panel);
                
              
        //*********************************************************************************************Data Manage btn menu 
               final JMenuItem Data_manage1 = new JMenuItem(spaceLP+"Data Management 1", new ImageIcon(new ImageIcon("data/images/icons/icon_capBio.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
               Data_manage1.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
               Data_manage1.setBorderPainted(false);
               Data_manage1.setFocusPainted(true);
               Data_manage1.setContentAreaFilled(false);
               Data_manage1.setOpaque(true);
               Data_manage1.setBackground(null);
               Data_manage1.setCursor(new Cursor(Cursor.HAND_CURSOR));
               Data_manage1.addMouseListener(new MouseAdapter() 
               {
                  public void mouseEntered(MouseEvent evt) 
                  {
                	  Data_manage1.setBackground(Color.ORANGE);
                	  //Data_manage1.setContentAreaFilled(true);
                  }
                  public void mouseExited(MouseEvent evt) 
                  {
                	  Data_manage1.setBackground(null);
                	 // Data_manage1.setContentAreaFilled(false);
                  }
               });
          
      //*********************************************************************************************End Data Manage btn menu        
              
               Data_manage_menu_panel.add(Data_manage1);   
 

 //############################################################################ Panel for Remote_machine Button       
               JPanel Remote_machine_menu_heading_panel = new JPanel();
               Remote_machine_menu_heading_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,3)));
               Remote_machine_menu_heading_panel.setBorder(emptyBorder); 
               Remote_machine_menu_heading_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
               Remote_machine_menu_heading_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LdropPanBHeight));
               Remote_machine_menu_heading_panel.setBackground(new Color(0,0,0,50));
               p1ScrollMat.add(Remote_machine_menu_heading_panel);
               
               final JButton Remote_machine_menu_heading=new JButton("Remote Machines");
               Remote_machine_menu_heading.setFont(Font.decode("Arial-BOLD-14"));
               Remote_machine_menu_heading.setForeground(new Color(0,0,0,255));
               Remote_machine_menu_heading.setContentAreaFilled(true);
               Remote_machine_menu_heading.setBorderPainted(true);
               Remote_machine_menu_heading.setFocusPainted(false);
               Remote_machine_menu_heading.setToolTipText("Remote machine access management");
               Remote_machine_menu_heading.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LButtdropWidth));
               Remote_machine_menu_heading.setVerticalTextPosition(SwingConstants.CENTER);
               Remote_machine_menu_heading.setHorizontalTextPosition(SwingConstants.CENTER);
               Remote_machine_menu_heading.setCursor(new Cursor(Cursor.HAND_CURSOR));
               Remote_machine_menu_heading.setBorder(BorderFactory.createLineBorder(Color.blue,0));
               Remote_machine_menu_heading.setLayout((new FlowLayout(FlowLayout.RIGHT,2,8)));
               Remote_machine_menu_heading.addMouseListener(new MouseAdapter() 
               {
                  public void mouseEntered(MouseEvent evt) 
                  {
                	 // Remote_machine_menu_heading.setBackground(Color.ORANGE);
                	  //Remote_machine_menu_heading.setContentAreaFilled(true);
                  }
                  public void mouseExited(MouseEvent evt) 
                  {
                	 // Remote_machine_menu_heading.setBackground(null);
                	 // Remote_machine.setContentAreaFilled(false);
                  }
               });
              
               final JLabel Remote_machine_menu_heading_icon = new JLabel();
               
               //Remote_machine_menu_heading_icon.setIcon(menu_dropped_icon);
               
               Remote_machine_menu_heading.add(Remote_machine_menu_heading_icon);
               
               Remote_machine_menu_heading_panel.add( Remote_machine_menu_heading);
               
          //########################################################################################## End Hide and show Remote_machine Menu button
         //***************************************************************################# Remote_machine Items Panel 
                   final JPanel Remote_machine_menu_panel = new JPanel();
                   Remote_machine_menu_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
                   Remote_machine_menu_panel.setBorder(emptyBorder); 
                   Remote_machine_menu_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
                   Remote_machine_menu_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanRemote_machineHeight));
                   Remote_machine_menu_panel.setBackground(new Color(255,255,255,255));
                   Remote_machine_menu_panel.setVisible(false);
                   if(Remote_machine_menu_panel.isVisible()) {
                	   Remote_machine_menu_heading_icon.setIcon(menu_dropped_icon);
               		   }
               	   else {
               		Remote_machine_menu_heading_icon.setIcon(menu_close_icon);
               	   }
                   p1ScrollMat.add(Remote_machine_menu_panel);
                   
                 
           //*********************************************************************************************Remote_machine btn menu 
                  final JMenuItem Remote_machine1 = new JMenuItem(spaceLP+"Remote machine 1", new ImageIcon(new ImageIcon("data/images/icons/icon_capBio.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
                  Remote_machine1.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
                  Remote_machine1.setBorderPainted(false);
                  Remote_machine1.setFocusPainted(true);
                  Remote_machine1.setContentAreaFilled(false);
                  Remote_machine1.setOpaque(true);
                  Remote_machine1.setBackground(null);
                  Remote_machine1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                  Remote_machine1.addMouseListener(new MouseAdapter() 
                  {
                     public void mouseEntered(MouseEvent evt) 
                     {
                   	  Remote_machine1.setBackground(Color.ORANGE);
                   	  //Remote_machine1.setContentAreaFilled(true);
                     }
                     public void mouseExited(MouseEvent evt) 
                     {
                   	  Remote_machine1.setBackground(null);
                   	 // Remote_machine1.setContentAreaFilled(false);
                     }
                  });
             
         //*********************************************************************************************End Remote_machine btn menu        
                 
                  Remote_machine_menu_panel.add(Remote_machine1);   
                  
  
                  
                  
//############################################################################ Panel for Network_manage Button       
                  JPanel Network_manage_menu_heading_panel = new JPanel();
                  Network_manage_menu_heading_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,3)));
                  Network_manage_menu_heading_panel.setBorder(emptyBorder); 
                  Network_manage_menu_heading_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
                  Network_manage_menu_heading_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LdropPanBHeight));
                  Network_manage_menu_heading_panel.setBackground(new Color(0,0,0,50));
                  p1ScrollMat.add(Network_manage_menu_heading_panel);
                  
                  final JButton Network_manage_menu_heading=new JButton("Network Management");
                  Network_manage_menu_heading.setFont(Font.decode("Arial-BOLD-14"));
                  Network_manage_menu_heading.setForeground(new Color(0,0,0,255));
                  Network_manage_menu_heading.setContentAreaFilled(true);
                  Network_manage_menu_heading.setBorderPainted(true);
                  Network_manage_menu_heading.setFocusPainted(false);
                  Network_manage_menu_heading.setToolTipText("Manage Networks");
                  Network_manage_menu_heading.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LButtdropWidth));
                  Network_manage_menu_heading.setVerticalTextPosition(SwingConstants.CENTER);
                  Network_manage_menu_heading.setHorizontalTextPosition(SwingConstants.CENTER);
                  Network_manage_menu_heading.setCursor(new Cursor(Cursor.HAND_CURSOR));
                  Network_manage_menu_heading.setBorder(BorderFactory.createLineBorder(Color.blue,0));
                  Network_manage_menu_heading.setLayout((new FlowLayout(FlowLayout.RIGHT,2,8)));
                  Network_manage_menu_heading.addMouseListener(new MouseAdapter() 
                  {
                     public void mouseEntered(MouseEvent evt) 
                     {
                   	 // Network_manage_menu_heading.setBackground(Color.ORANGE);
                   	  //Network_manage_menu_heading.setContentAreaFilled(true);
                     }
                     public void mouseExited(MouseEvent evt) 
                     {
                   	 // Network_manage_menu_heading.setBackground(null);
                   	 // Network_manage.setContentAreaFilled(false);
                     }
                  });
                 
                  final JLabel Network_manage_menu_heading_icon = new JLabel();
                  
                  //Network_manage_menu_heading_icon.setIcon(menu_dropped_icon);
                  
                  Network_manage_menu_heading.add(Network_manage_menu_heading_icon);
                  
                  Network_manage_menu_heading_panel.add( Network_manage_menu_heading);
                  
             //########################################################################################## End Hide and show Network_manage Menu button
            //***************************************************************################# Network_manage Items Panel 
                      final JPanel Network_manage_menu_panel = new JPanel();
                      Network_manage_menu_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
                      Network_manage_menu_panel.setBorder(emptyBorder); 
                      Network_manage_menu_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
                      Network_manage_menu_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanNetwork_manageHeight));
                      Network_manage_menu_panel.setBackground(new Color(255,255,255,255));
                      Network_manage_menu_panel.setVisible(false);
                      if(Network_manage_menu_panel.isVisible()) {
                    	  Network_manage_menu_heading_icon.setIcon(menu_dropped_icon);
                  		   }
                  	   else {
                  		 Network_manage_menu_heading_icon.setIcon(menu_close_icon);
                  	   }
                      p1ScrollMat.add(Network_manage_menu_panel);
                      
                    
              //*********************************************************************************************Network_manage btn menu 
                     final JMenuItem Network_manage1 = new JMenuItem(spaceLP+"Network Manage 1", new ImageIcon(new ImageIcon("data/images/icons/icon_capBio.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
                     Network_manage1.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LPanelButWidth));
                     Network_manage1.setBorderPainted(false);
                     Network_manage1.setFocusPainted(true);
                     Network_manage1.setContentAreaFilled(false);
                     Network_manage1.setOpaque(true);
                     Network_manage1.setBackground(null);
                     Network_manage1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                     Network_manage1.addMouseListener(new MouseAdapter() 
                     {
                        public void mouseEntered(MouseEvent evt) 
                        {
                      	  Network_manage1.setBackground(Color.ORANGE);
                      	  //Network_manage1.setContentAreaFilled(true);
                        }
                        public void mouseExited(MouseEvent evt) 
                        {
                      	  Network_manage1.setBackground(null);
                      	 // Network_manage1.setContentAreaFilled(false);
                        }
                     });
                
                     
                     Network_manage_menu_panel.add(Network_manage1);
            //*********************************************************************************************End Network_manage btn menu        
                    
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  Add the mat to left panel         
    p1.add(p1ScrollMat);     
 
    
    
    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Scroll Bar              


//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% End Scroll Bar    
              
         
         
 //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& Add mouse click listeners for the accordion     
       Peronel_menu_heading.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
        	   if(LPanPItem1.isVisible()) {
        		   LPanPItem1.setVisible(false);
        		   Peronel_menu_heading_icon.setIcon(menu_close_icon);
        		   }
        	   else {
        		   LPanPItem1.setVisible(true);
        		   Peronel_menu_heading_icon.setIcon(menu_dropped_icon);
        	   }
           }
       });
       
       Settings_menu_heading.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
        	   if(Settings_menu_panel.isVisible()) {
        		   Settings_menu_panel.setVisible(false);
        		   Settings_menu_heading_icon.setIcon(menu_close_icon);
        		   }
        	   else {
        		   Settings_menu_panel.setVisible(true);
        		   Settings_menu_heading_icon.setIcon(menu_dropped_icon);
        	   }
           }
       });
       
       SheduleTasks_menu_heading.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
        	   if(SheduleTasks_menu_panel.isVisible()) {
        		   SheduleTasks_menu_panel.setVisible(false);
        		   SheduleTasks_menu_heading_icon.setIcon(menu_close_icon);
        		   }
        	   else {
        		   SheduleTasks_menu_panel.setVisible(true);
        		   SheduleTasks_menu_heading_icon.setIcon(menu_dropped_icon);
        	   }
           }
       });
       
       Data_manage_menu_heading.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
        	   if(Data_manage_menu_panel.isVisible()) {
        		   Data_manage_menu_panel.setVisible(false);
        		   Data_manage_menu_heading_icon.setIcon(menu_close_icon);
        		   }
        	   else {
        		   Data_manage_menu_panel.setVisible(true);
        		   Data_manage_menu_heading_icon.setIcon(menu_dropped_icon);
        	   }
           }
       });
       
       Remote_machine_menu_heading.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
        	   if(Remote_machine_menu_panel.isVisible()) {
        		   Remote_machine_menu_panel.setVisible(false);
        		   Remote_machine_menu_heading_icon.setIcon(menu_close_icon);
        		   }
        	   else {
        		   Remote_machine_menu_panel.setVisible(true);
        		   Remote_machine_menu_heading_icon.setIcon(menu_dropped_icon);
        	   }
           }
       });
       Network_manage_menu_heading.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
        	   if(Network_manage_menu_panel.isVisible()) {
        		   Network_manage_menu_panel.setVisible(false);
        		   Network_manage_menu_heading_icon.setIcon(menu_close_icon);
        		   }
        	   else {
        		   Network_manage_menu_panel.setVisible(true);
        		   Network_manage_menu_heading_icon.setIcon(menu_dropped_icon);
        	   }
           }
       });
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& End mouse click listeners for the accordion 
       
       
       
        
//######################################################## add background panel to body
       this.add(BodyBGPanel);        
        
       
       
       
       
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& Right upper pannel where biometrics go
     final int BioWindowsWidth = 82;
	final int BioWindowsHeight=82;
	int BioWExtY=20; Color baseColor=new Color(200,0,0,50);
     String BioWindowTitleSpace="           ";
     
       JPanel RealBioImagesBgPanel = new JPanel();
       RealBioImagesBgPanel.setLayout((new FlowLayout(FlowLayout.LEFT,3,0)));
       RealBioImagesBgPanel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
       //RealBioImagesBgPanel.setBackground(baseColor);
       RealBioImagesBgPanel.setPreferredSize(new Dimension((int)(BodyBGPanel.getSize().width/1.27), (int)(BodyBGPanel.getSize().height/2)));      
       p2.add(RealBioImagesBgPanel);
       
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Real Face 
       final JPanel RealFaceImageBoundPanel = new JPanel();
       RealFaceImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       RealFaceImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
     /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"Face Image");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       RealFaceImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //RealFaceImageBoundPanel.setBackground(new Color(255,255,0,255));
       RealFaceImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       RealFaceImageBoundPanel.setVisible(true);
       
       final JButton RealFaceImageBoundPanelButton=new JButton("face image");
       RealFaceImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       RealFaceImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       RealFaceImageBoundPanelButton.setContentAreaFilled(true);
       RealFaceImageBoundPanelButton.setBorderPainted(true);
       RealFaceImageBoundPanelButton.setFocusPainted(false);
       RealFaceImageBoundPanelButton.setToolTipText("Face Image");
       RealFaceImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       RealFaceImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       RealFaceImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       RealFaceImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       RealFaceImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       RealFaceImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       RealFaceImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	  
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel RealFaceImageButtonLabel = new JLabel();
       RealFaceImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon faceImage= new ImageIcon(new ImageIcon("data/images/faces/FaceScan.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       RealFaceImageButtonLabel.setIcon(faceImage);
       RealFaceImageButtonLabel.setBackground(new Color(255,255,0,255));
       RealFaceImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       RealFaceImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       RealFaceImageButtonLabel.setToolTipText("Face Image");
       
       RealFaceImageBoundPanelButton.add(RealFaceImageButtonLabel); //add label to button
       RealFaceImageBoundPanel.add(RealFaceImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(RealFaceImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel BioWExtYPanel = new JPanel();
       BioWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       BioWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //BioWExtYPanel.setBackground(new Color(255,255,0,255));
       BioWExtYPanel.setOpaque(false);
       BioWExtYPanel.setVisible(false);
       BioWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       RealFaceImageBoundPanel.add(BioWExtYPanel);
       
       RealFaceImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {BioWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {BioWExtYPanel.setVisible(false);}});
       RealFaceImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {BioWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {BioWExtYPanel.setVisible(false);}});
       // tiny icons
       int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel SmallOptionsLabel = new JLabel();
       SmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       SmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SmallOptionsLabel.setIcon(SmallOptions);
       SmallOptionsLabel.setBackground(new Color(255,255,0,255));
       BioWExtYPanel.add(SmallOptionsLabel);
       BioWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SmallFolderLabel = new JLabel();
       SmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SmallFolderLabel.setBackground(new Color(255,255,0,255));
       SmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SmallFolderLabel.setIcon(SmallFolder);
       BioWExtYPanel.add(SmallFolderLabel);
       BioWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SmallExpandLabel = new JLabel();
       SmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SmallExpandLabel.setBackground(new Color(255,255,0,255));
       SmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SmallExpandLabel.setIcon(SmallExpand);
       BioWExtYPanel.add(SmallExpandLabel);
       BioWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SmallReloadLabel = new JLabel();
       SmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SmallReloadLabel.setBackground(new Color(255,255,0,255));
       SmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SmallReloadLabel.setIcon(SmallReload);
       BioWExtYPanel.add(SmallReloadLabel);
       BioWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SmallDiscardLabel = new JLabel();
       SmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SmallDiscardLabel.setBackground(new Color(255,255,0,255));
       SmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SmallDiscardLabel.setIcon(SmallDiscard);
       BioWExtYPanel.add(SmallDiscardLabel);
       BioWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SmallCropLabel = new JLabel();
       SmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SmallCropLabel.setBackground(new Color(255,255,0,255));
       SmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SmallCropLabel.setIcon(SmallCrop);
       BioWExtYPanel.add(SmallCropLabel);
       BioWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SmallGrayScaleLabel = new JLabel();
       SmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       SmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SmallGrayScaleLabel.setIcon(SmallGrayScale);
       BioWExtYPanel.add(SmallGrayScaleLabel);
       BioWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Real Face 
      
     //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Bio Face 
       final JPanel BioFaceImageBoundPanel = new JPanel();
       BioFaceImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       BioFaceImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"Face Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       BioFaceImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //BioFaceImageBoundPanel.setBackground(new Color(255,255,0,255));
       BioFaceImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       BioFaceImageBoundPanel.setVisible(false);
       
       final JButton BioFaceImageBoundPanelButton=new JButton("Face Recognition");
       BioFaceImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       BioFaceImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       BioFaceImageBoundPanelButton.setContentAreaFilled(true);
       BioFaceImageBoundPanelButton.setBorderPainted(true);
       BioFaceImageBoundPanelButton.setFocusPainted(false);
       BioFaceImageBoundPanelButton.setToolTipText("Face Recognition");
       BioFaceImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       BioFaceImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       BioFaceImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       BioFaceImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       BioFaceImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       BioFaceImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       BioFaceImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel BioFaceImageButtonLabel = new JLabel();
       BioFaceImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon biofaceImage= new ImageIcon(new ImageIcon("data/images/bioFaces/Image_BioFace150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       BioFaceImageButtonLabel.setIcon(biofaceImage);
       BioFaceImageButtonLabel.setBackground(new Color(255,255,0,255));
       BioFaceImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       BioFaceImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       BioFaceImageButtonLabel.setToolTipText("Face Recognition");
       
       
       BioFaceImageBoundPanelButton.add(BioFaceImageButtonLabel); //add label to button
       BioFaceImageBoundPanel.add(BioFaceImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(BioFaceImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel BioFWExtYPanel = new JPanel();
       BioFWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       BioFWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //BioFWExtYPanel.setBackground(new Color(255,255,0,255));
       BioFWExtYPanel.setOpaque(false);
       BioFWExtYPanel.setVisible(false);
       BioFWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       BioFaceImageBoundPanel.add(BioFWExtYPanel);
       
       BioFaceImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {BioFWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {BioFWExtYPanel.setVisible(false);}});
       BioFaceImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {BioFWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {BioFWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel BioFaceSmallOptionsLabel = new JLabel();
       BioFaceSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       BioFaceSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       BioFaceSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon BioFaceSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       BioFaceSmallOptionsLabel.setIcon(BioFaceSmallOptions);
       BioFaceSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       BioFWExtYPanel.add(BioFaceSmallOptionsLabel);
       BioFWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel BioFaceSmallFolderLabel = new JLabel();
       BioFaceSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       BioFaceSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       BioFaceSmallFolderLabel.setBackground(new Color(255,255,0,255));
       BioFaceSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon BioFaceSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       BioFaceSmallFolderLabel.setIcon(BioFaceSmallFolder);
       BioFWExtYPanel.add(BioFaceSmallFolderLabel);
       BioFWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel BioFaceSmallExpandLabel = new JLabel();
       BioFaceSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       BioFaceSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       BioFaceSmallExpandLabel.setBackground(new Color(255,255,0,255));
       BioFaceSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon BioFaceSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       BioFaceSmallExpandLabel.setIcon(BioFaceSmallExpand);
       BioFWExtYPanel.add(BioFaceSmallExpandLabel);
       BioFWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel BioFaceSmallReloadLabel = new JLabel();
       BioFaceSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       BioFaceSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       BioFaceSmallReloadLabel.setBackground(new Color(255,255,0,255));
       BioFaceSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon BioFaceSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       BioFaceSmallReloadLabel.setIcon(BioFaceSmallReload);
       BioFWExtYPanel.add(BioFaceSmallReloadLabel);
       BioFWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel BioFaceSmallDiscardLabel = new JLabel();
       BioFaceSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       BioFaceSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       BioFaceSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       BioFaceSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon BioFaceSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       BioFaceSmallDiscardLabel.setIcon(BioFaceSmallDiscard);
       BioFWExtYPanel.add(BioFaceSmallDiscardLabel);
       BioFWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel BioFaceSmallCropLabel = new JLabel();
       BioFaceSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       BioFaceSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       BioFaceSmallCropLabel.setBackground(new Color(255,255,0,255));
       BioFaceSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon BioFaceSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       BioFaceSmallCropLabel.setIcon(BioFaceSmallCrop);
       BioFWExtYPanel.add(BioFaceSmallCropLabel);
       BioFWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel BioFaceSmallGrayScaleLabel = new JLabel();
       BioFaceSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       BioFaceSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       BioFaceSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       BioFaceSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon BioFaceSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       BioFaceSmallGrayScaleLabel.setIcon(BioFaceSmallGrayScale);
       BioFWExtYPanel.add(BioFaceSmallGrayScaleLabel);
       BioFWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End BIO Face 
       
       
     //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ IRIS SCAN  
       final JPanel IrisScanImageBoundPanel = new JPanel();
       IrisScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       IrisScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"Iris Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       IrisScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //IrisScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       IrisScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       IrisScanImageBoundPanel.setVisible(false);
       
       final JButton IrisScanImageBoundPanelButton=new JButton("Iris Scan");
       IrisScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       IrisScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       IrisScanImageBoundPanelButton.setContentAreaFilled(true);
       IrisScanImageBoundPanelButton.setBorderPainted(true);
       IrisScanImageBoundPanelButton.setFocusPainted(false);
       IrisScanImageBoundPanelButton.setToolTipText("Iris Scan");
       IrisScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       IrisScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       IrisScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       IrisScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       IrisScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       IrisScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       IrisScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel IrisScanImageButtonLabel = new JLabel();
       IrisScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon IrisScanImage= new ImageIcon(new ImageIcon("data/images/Irises/Eyescan.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       IrisScanImageButtonLabel.setIcon(IrisScanImage);
       IrisScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       IrisScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       IrisScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       IrisScanImageButtonLabel.setToolTipText("Iris Scan");
       
       
       IrisScanImageBoundPanelButton.add(IrisScanImageButtonLabel); //add label to button
       IrisScanImageBoundPanel.add(IrisScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(IrisScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel IrisSWExtYPanel = new JPanel();
       IrisSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       IrisSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //IrisSWExtYPanel.setBackground(new Color(255,255,0,255));
       IrisSWExtYPanel.setOpaque(false);
       IrisSWExtYPanel.setVisible(false);
       IrisSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       IrisScanImageBoundPanel.add(IrisSWExtYPanel);
       
       IrisScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {IrisSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {IrisSWExtYPanel.setVisible(false);}});
       IrisScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {IrisSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {IrisSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel IrisScanSmallOptionsLabel = new JLabel();
       IrisScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       IrisScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       IrisScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon IrisScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       IrisScanSmallOptionsLabel.setIcon(IrisScanSmallOptions);
       IrisScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       IrisSWExtYPanel.add(IrisScanSmallOptionsLabel);
       IrisSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel IrisScanSmallFolderLabel = new JLabel();
       IrisScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       IrisScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       IrisScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       IrisScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon IrisScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       IrisScanSmallFolderLabel.setIcon(IrisScanSmallFolder);
       IrisSWExtYPanel.add(IrisScanSmallFolderLabel);
       IrisSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel IrisScanSmallExpandLabel = new JLabel();
       IrisScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       IrisScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       IrisScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       IrisScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon IrisScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       IrisScanSmallExpandLabel.setIcon(IrisScanSmallExpand);
       IrisSWExtYPanel.add(IrisScanSmallExpandLabel);
       IrisSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel IrisScanSmallReloadLabel = new JLabel();
       IrisScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       IrisScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       IrisScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       IrisScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon IrisScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       IrisScanSmallReloadLabel.setIcon(IrisScanSmallReload);
       IrisSWExtYPanel.add(IrisScanSmallReloadLabel);
       IrisSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel IrisScanSmallDiscardLabel = new JLabel();
       IrisScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       IrisScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       IrisScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       IrisScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon IrisScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       IrisScanSmallDiscardLabel.setIcon(IrisScanSmallDiscard);
       IrisSWExtYPanel.add(IrisScanSmallDiscardLabel);
       IrisSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel IrisScanSmallCropLabel = new JLabel();
       IrisScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       IrisScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       IrisScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       IrisScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon IrisScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       IrisScanSmallCropLabel.setIcon(IrisScanSmallCrop);
       IrisSWExtYPanel.add(IrisScanSmallCropLabel);
       IrisSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel IrisScanSmallGrayScaleLabel = new JLabel();
       IrisScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       IrisScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       IrisScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       IrisScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon IrisScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       IrisScanSmallGrayScaleLabel.setIcon(IrisScanSmallGrayScale);
       IrisSWExtYPanel.add(IrisScanSmallGrayScaleLabel);
       IrisSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Iris Scan
       
     //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ RETNA SCAN  
       final JPanel RetinaScanImageBoundPanel = new JPanel();
       RetinaScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       RetinaScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"Retina Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       RetinaScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //RetinaScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       RetinaScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       RetinaScanImageBoundPanel.setVisible(false);
       
       final JButton RetinaScanImageBoundPanelButton=new JButton("Retina scan");
       RetinaScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       RetinaScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       RetinaScanImageBoundPanelButton.setContentAreaFilled(true);
       RetinaScanImageBoundPanelButton.setBorderPainted(true);
       RetinaScanImageBoundPanelButton.setFocusPainted(false);
       RetinaScanImageBoundPanelButton.setToolTipText("Retina scan");
       RetinaScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       RetinaScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       RetinaScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       RetinaScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       RetinaScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       RetinaScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       RetinaScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel RetinaScanImageButtonLabel = new JLabel();
       RetinaScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon RetinaScanImage= new ImageIcon(new ImageIcon("data/images/retinas/image_RetinaScan150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       RetinaScanImageButtonLabel.setIcon(RetinaScanImage);
       RetinaScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       RetinaScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       RetinaScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       RetinaScanImageButtonLabel.setToolTipText("Retina Scan");
       
       
       RetinaScanImageBoundPanelButton.add(RetinaScanImageButtonLabel); //add label to button
       RetinaScanImageBoundPanel.add(RetinaScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(RetinaScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel RetinaSWExtYPanel = new JPanel();
       RetinaSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       RetinaSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //RetinaSWExtYPanel.setBackground(new Color(255,255,0,255));
       RetinaSWExtYPanel.setOpaque(false);
       RetinaSWExtYPanel.setVisible(false);
       RetinaSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       RetinaScanImageBoundPanel.add(RetinaSWExtYPanel);
       
       RetinaScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {RetinaSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {RetinaSWExtYPanel.setVisible(false);}});
       RetinaScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {RetinaSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {RetinaSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel RetinaScanSmallOptionsLabel = new JLabel();
       RetinaScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       RetinaScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       RetinaScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon RetinaScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       RetinaScanSmallOptionsLabel.setIcon(RetinaScanSmallOptions);
       RetinaScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       RetinaSWExtYPanel.add(RetinaScanSmallOptionsLabel);
       RetinaSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel RetinaScanSmallFolderLabel = new JLabel();
       RetinaScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       RetinaScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       RetinaScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       RetinaScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon RetinaScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       RetinaScanSmallFolderLabel.setIcon(RetinaScanSmallFolder);
       RetinaSWExtYPanel.add(RetinaScanSmallFolderLabel);
       RetinaSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel RetinaScanSmallExpandLabel = new JLabel();
       RetinaScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       RetinaScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       RetinaScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       RetinaScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon RetinaScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       RetinaScanSmallExpandLabel.setIcon(RetinaScanSmallExpand);
       RetinaSWExtYPanel.add(RetinaScanSmallExpandLabel);
       RetinaSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel RetinaScanSmallReloadLabel = new JLabel();
       RetinaScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       RetinaScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       RetinaScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       RetinaScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon RetinaScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       RetinaScanSmallReloadLabel.setIcon(RetinaScanSmallReload);
       RetinaSWExtYPanel.add(RetinaScanSmallReloadLabel);
       RetinaSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel RetinaScanSmallDiscardLabel = new JLabel();
       RetinaScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       RetinaScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       RetinaScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       RetinaScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon RetinaScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       RetinaScanSmallDiscardLabel.setIcon(RetinaScanSmallDiscard);
       RetinaSWExtYPanel.add(RetinaScanSmallDiscardLabel);
       RetinaSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel RetinaScanSmallCropLabel = new JLabel();
       RetinaScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       RetinaScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       RetinaScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       RetinaScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon RetinaScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       RetinaScanSmallCropLabel.setIcon(RetinaScanSmallCrop);
       RetinaSWExtYPanel.add(RetinaScanSmallCropLabel);
       RetinaSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel RetinaScanSmallGrayScaleLabel = new JLabel();
       RetinaScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       RetinaScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       RetinaScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       RetinaScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon RetinaScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       RetinaScanSmallGrayScaleLabel.setIcon(RetinaScanSmallGrayScale);
       RetinaSWExtYPanel.add(RetinaScanSmallGrayScaleLabel);
       RetinaSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End RETINA SCAN
      
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ FingerPrint SCAN  
       final JPanel FingerPrintScanImageBoundPanel = new JPanel();
       FingerPrintScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       FingerPrintScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"FingerPrint Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       FingerPrintScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //FingerPrintScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       FingerPrintScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       FingerPrintScanImageBoundPanel.setVisible(true);
       
       final JButton FingerPrintScanImageBoundPanelButton=new JButton("FingerPrints");
       FingerPrintScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       FingerPrintScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       FingerPrintScanImageBoundPanelButton.setContentAreaFilled(true);
       FingerPrintScanImageBoundPanelButton.setBorderPainted(true);
       FingerPrintScanImageBoundPanelButton.setFocusPainted(false);
       FingerPrintScanImageBoundPanelButton.setToolTipText("FingerPrint Recognition");
       FingerPrintScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       FingerPrintScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       FingerPrintScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       FingerPrintScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       FingerPrintScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       FingerPrintScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       FingerPrintScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel FingerPrintScanImageButtonLabel = new JLabel();
       FingerPrintScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon FingerPrintScanImage= new ImageIcon(new ImageIcon("data/images/FingerPrints/FingerprintScan.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       FingerPrintScanImageButtonLabel.setIcon(FingerPrintScanImage);
       FingerPrintScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       FingerPrintScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       FingerPrintScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       FingerPrintScanImageButtonLabel.setToolTipText("FingerPrint Scan");
       
       FingerPrintScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {
          public void mouseClicked(MouseEvent evt) 
          {
        	  System.out.println("finger print pressed");
        	  new FingerPrintScanner().launch();
          }
       });
       
       FingerPrintScanImageBoundPanelButton.add(FingerPrintScanImageButtonLabel); //add label to button
       FingerPrintScanImageBoundPanel.add(FingerPrintScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(FingerPrintScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel FingerPrintSWExtYPanel = new JPanel();
       FingerPrintSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       FingerPrintSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //FingerPrintSWExtYPanel.setBackground(new Color(255,255,0,255));
       FingerPrintSWExtYPanel.setOpaque(false);
       FingerPrintSWExtYPanel.setVisible(false);
       FingerPrintSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       FingerPrintScanImageBoundPanel.add(FingerPrintSWExtYPanel);
       
       FingerPrintScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {FingerPrintSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {FingerPrintSWExtYPanel.setVisible(false);}});
       FingerPrintScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {FingerPrintSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {FingerPrintSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel FingerPrintScanSmallOptionsLabel = new JLabel();
       FingerPrintScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       FingerPrintScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerPrintScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerPrintScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerPrintScanSmallOptionsLabel.setIcon(FingerPrintScanSmallOptions);
       FingerPrintScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       FingerPrintSWExtYPanel.add(FingerPrintScanSmallOptionsLabel);
       FingerPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerPrintScanSmallFolderLabel = new JLabel();
       FingerPrintScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerPrintScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerPrintScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       FingerPrintScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerPrintScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerPrintScanSmallFolderLabel.setIcon(FingerPrintScanSmallFolder);
       FingerPrintSWExtYPanel.add(FingerPrintScanSmallFolderLabel);
       FingerPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerPrintScanSmallExpandLabel = new JLabel();
       FingerPrintScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerPrintScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerPrintScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       FingerPrintScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerPrintScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerPrintScanSmallExpandLabel.setIcon(FingerPrintScanSmallExpand);
       FingerPrintSWExtYPanel.add(FingerPrintScanSmallExpandLabel);
       FingerPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerPrintScanSmallReloadLabel = new JLabel();
       FingerPrintScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerPrintScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerPrintScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       FingerPrintScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerPrintScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerPrintScanSmallReloadLabel.setIcon(FingerPrintScanSmallReload);
       FingerPrintSWExtYPanel.add(FingerPrintScanSmallReloadLabel);
       FingerPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerPrintScanSmallDiscardLabel = new JLabel();
       FingerPrintScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerPrintScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerPrintScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       FingerPrintScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerPrintScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerPrintScanSmallDiscardLabel.setIcon(FingerPrintScanSmallDiscard);
       FingerPrintSWExtYPanel.add(FingerPrintScanSmallDiscardLabel);
       FingerPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerPrintScanSmallCropLabel = new JLabel();
       FingerPrintScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerPrintScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerPrintScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       FingerPrintScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerPrintScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerPrintScanSmallCropLabel.setIcon(FingerPrintScanSmallCrop);
       FingerPrintSWExtYPanel.add(FingerPrintScanSmallCropLabel);
       FingerPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerPrintScanSmallGrayScaleLabel = new JLabel();
       FingerPrintScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerPrintScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerPrintScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       FingerPrintScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerPrintScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerPrintScanSmallGrayScaleLabel.setIcon(FingerPrintScanSmallGrayScale);
       FingerPrintSWExtYPanel.add(FingerPrintScanSmallGrayScaleLabel);
       FingerPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End FingerPrint SCAN

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Finger Vein SCAN  
       final JPanel FingerVeinScanImageBoundPanel = new JPanel();
       FingerVeinScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       FingerVeinScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"FingerVein Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       FingerVeinScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //FingerVeinScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       FingerVeinScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       FingerVeinScanImageBoundPanel.setVisible(false);
       
       final JButton FingerVeinScanImageBoundPanelButton=new JButton("FingerVeins");
       FingerVeinScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       FingerVeinScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       FingerVeinScanImageBoundPanelButton.setContentAreaFilled(true);
       FingerVeinScanImageBoundPanelButton.setBorderPainted(true);
       FingerVeinScanImageBoundPanelButton.setFocusPainted(false);
       FingerVeinScanImageBoundPanelButton.setToolTipText("FingerVein Recognition");
       FingerVeinScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       FingerVeinScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       FingerVeinScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       FingerVeinScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       FingerVeinScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       FingerVeinScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       FingerVeinScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel FingerVeinScanImageButtonLabel = new JLabel();
       FingerVeinScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon FingerVeinScanImage= new ImageIcon(new ImageIcon("data/images/FingerVeins/image_FingerVeinScan150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       FingerVeinScanImageButtonLabel.setIcon(FingerVeinScanImage);
       FingerVeinScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       FingerVeinScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       FingerVeinScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       FingerVeinScanImageButtonLabel.setToolTipText("FingerVein Scan");
       
       
       FingerVeinScanImageBoundPanelButton.add(FingerVeinScanImageButtonLabel); //add label to button
       FingerVeinScanImageBoundPanel.add(FingerVeinScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(FingerVeinScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel FingerVeinSWExtYPanel = new JPanel();
       FingerVeinSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       FingerVeinSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //FingerVeinSWExtYPanel.setBackground(new Color(255,255,0,255));
       FingerVeinSWExtYPanel.setOpaque(false);
       FingerVeinSWExtYPanel.setVisible(false);
       FingerVeinSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       FingerVeinScanImageBoundPanel.add(FingerVeinSWExtYPanel);
       
       FingerVeinScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {FingerVeinSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {FingerVeinSWExtYPanel.setVisible(false);}});
       FingerVeinScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {FingerVeinSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {FingerVeinSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel FingerVeinScanSmallOptionsLabel = new JLabel();
       FingerVeinScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       FingerVeinScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerVeinScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerVeinScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerVeinScanSmallOptionsLabel.setIcon(FingerVeinScanSmallOptions);
       FingerVeinScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       FingerVeinSWExtYPanel.add(FingerVeinScanSmallOptionsLabel);
       FingerVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerVeinScanSmallFolderLabel = new JLabel();
       FingerVeinScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerVeinScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerVeinScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       FingerVeinScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerVeinScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerVeinScanSmallFolderLabel.setIcon(FingerVeinScanSmallFolder);
       FingerVeinSWExtYPanel.add(FingerVeinScanSmallFolderLabel);
       FingerVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerVeinScanSmallExpandLabel = new JLabel();
       FingerVeinScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerVeinScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerVeinScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       FingerVeinScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerVeinScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerVeinScanSmallExpandLabel.setIcon(FingerVeinScanSmallExpand);
       FingerVeinSWExtYPanel.add(FingerVeinScanSmallExpandLabel);
       FingerVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerVeinScanSmallReloadLabel = new JLabel();
       FingerVeinScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerVeinScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerVeinScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       FingerVeinScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerVeinScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerVeinScanSmallReloadLabel.setIcon(FingerVeinScanSmallReload);
       FingerVeinSWExtYPanel.add(FingerVeinScanSmallReloadLabel);
       FingerVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerVeinScanSmallDiscardLabel = new JLabel();
       FingerVeinScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerVeinScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerVeinScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       FingerVeinScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerVeinScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerVeinScanSmallDiscardLabel.setIcon(FingerVeinScanSmallDiscard);
       FingerVeinSWExtYPanel.add(FingerVeinScanSmallDiscardLabel);
       FingerVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerVeinScanSmallCropLabel = new JLabel();
       FingerVeinScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerVeinScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerVeinScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       FingerVeinScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerVeinScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerVeinScanSmallCropLabel.setIcon(FingerVeinScanSmallCrop);
       FingerVeinSWExtYPanel.add(FingerVeinScanSmallCropLabel);
       FingerVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel FingerVeinScanSmallGrayScaleLabel = new JLabel();
       FingerVeinScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       FingerVeinScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       FingerVeinScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       FingerVeinScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon FingerVeinScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       FingerVeinScanSmallGrayScaleLabel.setIcon(FingerVeinScanSmallGrayScale);
       FingerVeinSWExtYPanel.add(FingerVeinScanSmallGrayScaleLabel);
       FingerVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End FingerVein SCAN
      
       
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Palm Vein SCAN  
       final JPanel PalmVeinScanImageBoundPanel = new JPanel();
       PalmVeinScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       PalmVeinScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"PalmVein Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       PalmVeinScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //PalmVeinScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       PalmVeinScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       PalmVeinScanImageBoundPanel.setVisible(false);
       
       final JButton PalmVeinScanImageBoundPanelButton=new JButton("PalmVeins");
       PalmVeinScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       PalmVeinScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       PalmVeinScanImageBoundPanelButton.setContentAreaFilled(true);
       PalmVeinScanImageBoundPanelButton.setBorderPainted(true);
       PalmVeinScanImageBoundPanelButton.setFocusPainted(false);
       PalmVeinScanImageBoundPanelButton.setToolTipText("PalmVein Recognition");
       PalmVeinScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       PalmVeinScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       PalmVeinScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       PalmVeinScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       PalmVeinScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       PalmVeinScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       PalmVeinScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel PalmVeinScanImageButtonLabel = new JLabel();
       PalmVeinScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon PalmVeinScanImage= new ImageIcon(new ImageIcon("data/images/PalmVeins/image_PalmVeinScan150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       PalmVeinScanImageButtonLabel.setIcon(PalmVeinScanImage);
       PalmVeinScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       PalmVeinScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       PalmVeinScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       PalmVeinScanImageButtonLabel.setToolTipText("Palm vein Scan");
       
       
       PalmVeinScanImageBoundPanelButton.add(PalmVeinScanImageButtonLabel); //add label to button
       PalmVeinScanImageBoundPanel.add(PalmVeinScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(PalmVeinScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel PalmVeinSWExtYPanel = new JPanel();
       PalmVeinSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       PalmVeinSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //PalmVeinSWExtYPanel.setBackground(new Color(255,255,0,255));
       PalmVeinSWExtYPanel.setOpaque(false);
       PalmVeinSWExtYPanel.setVisible(false);
       PalmVeinSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       PalmVeinScanImageBoundPanel.add(PalmVeinSWExtYPanel);
       
       PalmVeinScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {PalmVeinSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {PalmVeinSWExtYPanel.setVisible(false);}});
       PalmVeinScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {PalmVeinSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {PalmVeinSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel PalmVeinScanSmallOptionsLabel = new JLabel();
       PalmVeinScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       PalmVeinScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmVeinScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmVeinScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmVeinScanSmallOptionsLabel.setIcon(PalmVeinScanSmallOptions);
       PalmVeinScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       PalmVeinSWExtYPanel.add(PalmVeinScanSmallOptionsLabel);
       PalmVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmVeinScanSmallFolderLabel = new JLabel();
       PalmVeinScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmVeinScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmVeinScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       PalmVeinScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmVeinScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmVeinScanSmallFolderLabel.setIcon(PalmVeinScanSmallFolder);
       PalmVeinSWExtYPanel.add(PalmVeinScanSmallFolderLabel);
       PalmVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmVeinScanSmallExpandLabel = new JLabel();
       PalmVeinScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmVeinScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmVeinScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       PalmVeinScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmVeinScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmVeinScanSmallExpandLabel.setIcon(PalmVeinScanSmallExpand);
       PalmVeinSWExtYPanel.add(PalmVeinScanSmallExpandLabel);
       PalmVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmVeinScanSmallReloadLabel = new JLabel();
       PalmVeinScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmVeinScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmVeinScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       PalmVeinScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmVeinScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmVeinScanSmallReloadLabel.setIcon(PalmVeinScanSmallReload);
       PalmVeinSWExtYPanel.add(PalmVeinScanSmallReloadLabel);
       PalmVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmVeinScanSmallDiscardLabel = new JLabel();
       PalmVeinScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmVeinScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmVeinScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       PalmVeinScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmVeinScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmVeinScanSmallDiscardLabel.setIcon(PalmVeinScanSmallDiscard);
       PalmVeinSWExtYPanel.add(PalmVeinScanSmallDiscardLabel);
       PalmVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmVeinScanSmallCropLabel = new JLabel();
       PalmVeinScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmVeinScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmVeinScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       PalmVeinScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmVeinScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmVeinScanSmallCropLabel.setIcon(PalmVeinScanSmallCrop);
       PalmVeinSWExtYPanel.add(PalmVeinScanSmallCropLabel);
       PalmVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmVeinScanSmallGrayScaleLabel = new JLabel();
       PalmVeinScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmVeinScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmVeinScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       PalmVeinScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmVeinScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmVeinScanSmallGrayScaleLabel.setIcon(PalmVeinScanSmallGrayScale);
       PalmVeinSWExtYPanel.add(PalmVeinScanSmallGrayScaleLabel);
       PalmVeinSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Palm Vein SCAN
      
       
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Palm Print  
       final JPanel PalmPrintScanImageBoundPanel = new JPanel();
       PalmPrintScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       PalmPrintScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"PalmPrint Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       PalmPrintScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //PalmPrintScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       PalmPrintScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       PalmPrintScanImageBoundPanel.setVisible(false);
       
       final JButton PalmPrintScanImageBoundPanelButton=new JButton("Palm Prints");
       PalmPrintScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       PalmPrintScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       PalmPrintScanImageBoundPanelButton.setContentAreaFilled(true);
       PalmPrintScanImageBoundPanelButton.setBorderPainted(true);
       PalmPrintScanImageBoundPanelButton.setFocusPainted(false);
       PalmPrintScanImageBoundPanelButton.setToolTipText("PalmPrint Recognition");
       PalmPrintScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       PalmPrintScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       PalmPrintScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       PalmPrintScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       PalmPrintScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       PalmPrintScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       PalmPrintScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel PalmPrintScanImageButtonLabel = new JLabel();
       PalmPrintScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon PalmPrintScanImage= new ImageIcon(new ImageIcon("data/images/PalmPrints/image_PalmPrintScan150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       PalmPrintScanImageButtonLabel.setIcon(PalmPrintScanImage);
       PalmPrintScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       PalmPrintScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       PalmPrintScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       PalmPrintScanImageButtonLabel.setToolTipText("Palm Print");
       
       
       PalmPrintScanImageBoundPanelButton.add(PalmPrintScanImageButtonLabel); //add label to button
       PalmPrintScanImageBoundPanel.add(PalmPrintScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(PalmPrintScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel PalmPrintSWExtYPanel = new JPanel();
       PalmPrintSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       PalmPrintSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //PalmPrintSWExtYPanel.setBackground(new Color(255,255,0,255));
       PalmPrintSWExtYPanel.setOpaque(false);
       PalmPrintSWExtYPanel.setVisible(false);
       PalmPrintSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       PalmPrintScanImageBoundPanel.add(PalmPrintSWExtYPanel);
       
       PalmPrintScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {PalmPrintSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {PalmPrintSWExtYPanel.setVisible(false);}});
       PalmPrintScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {PalmPrintSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {PalmPrintSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel PalmPrintScanSmallOptionsLabel = new JLabel();
       PalmPrintScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       PalmPrintScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmPrintScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmPrintScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmPrintScanSmallOptionsLabel.setIcon(PalmPrintScanSmallOptions);
       PalmPrintScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       PalmPrintSWExtYPanel.add(PalmPrintScanSmallOptionsLabel);
       PalmPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmPrintScanSmallFolderLabel = new JLabel();
       PalmPrintScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmPrintScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmPrintScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       PalmPrintScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmPrintScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmPrintScanSmallFolderLabel.setIcon(PalmPrintScanSmallFolder);
       PalmPrintSWExtYPanel.add(PalmPrintScanSmallFolderLabel);
       PalmPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmPrintScanSmallExpandLabel = new JLabel();
       PalmPrintScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmPrintScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmPrintScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       PalmPrintScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmPrintScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmPrintScanSmallExpandLabel.setIcon(PalmPrintScanSmallExpand);
       PalmPrintSWExtYPanel.add(PalmPrintScanSmallExpandLabel);
       PalmPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmPrintScanSmallReloadLabel = new JLabel();
       PalmPrintScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmPrintScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmPrintScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       PalmPrintScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmPrintScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmPrintScanSmallReloadLabel.setIcon(PalmPrintScanSmallReload);
       PalmPrintSWExtYPanel.add(PalmPrintScanSmallReloadLabel);
       PalmPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmPrintScanSmallDiscardLabel = new JLabel();
       PalmPrintScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmPrintScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmPrintScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       PalmPrintScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmPrintScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmPrintScanSmallDiscardLabel.setIcon(PalmPrintScanSmallDiscard);
       PalmPrintSWExtYPanel.add(PalmPrintScanSmallDiscardLabel);
       PalmPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmPrintScanSmallCropLabel = new JLabel();
       PalmPrintScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmPrintScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmPrintScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       PalmPrintScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmPrintScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmPrintScanSmallCropLabel.setIcon(PalmPrintScanSmallCrop);
       PalmPrintSWExtYPanel.add(PalmPrintScanSmallCropLabel);
       PalmPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel PalmPrintScanSmallGrayScaleLabel = new JLabel();
       PalmPrintScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       PalmPrintScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       PalmPrintScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       PalmPrintScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon PalmPrintScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       PalmPrintScanSmallGrayScaleLabel.setIcon(PalmPrintScanSmallGrayScale);
       PalmPrintSWExtYPanel.add(PalmPrintScanSmallGrayScaleLabel);
       PalmPrintSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Palm Print SCAN
      
       
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Hand Geomentry Print  
       final JPanel HandGeometryScanImageBoundPanel = new JPanel();
       HandGeometryScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       HandGeometryScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"HandGeometry Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       HandGeometryScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //HandGeometryScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       HandGeometryScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       HandGeometryScanImageBoundPanel.setVisible(false);
       
       final JButton HandGeometryScanImageBoundPanelButton=new JButton("Hand Geometry");
       HandGeometryScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       HandGeometryScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       HandGeometryScanImageBoundPanelButton.setContentAreaFilled(true);
       HandGeometryScanImageBoundPanelButton.setBorderPainted(true);
       HandGeometryScanImageBoundPanelButton.setFocusPainted(false);
       HandGeometryScanImageBoundPanelButton.setToolTipText("Hand Geometry");
       HandGeometryScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       HandGeometryScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       HandGeometryScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       HandGeometryScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       HandGeometryScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       HandGeometryScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       HandGeometryScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel HandGeometryScanImageButtonLabel = new JLabel();
       HandGeometryScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon HandGeometryScanImage= new ImageIcon(new ImageIcon("data/images/HandGeometry/image_HandGeometryScan150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       HandGeometryScanImageButtonLabel.setIcon(HandGeometryScanImage);
       HandGeometryScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       HandGeometryScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       HandGeometryScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       HandGeometryScanImageButtonLabel.setToolTipText("Hand Geometry");
       
       
       HandGeometryScanImageBoundPanelButton.add(HandGeometryScanImageButtonLabel); //add label to button
       HandGeometryScanImageBoundPanel.add(HandGeometryScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(HandGeometryScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel HandGeometrySWExtYPanel = new JPanel();
       HandGeometrySWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       HandGeometrySWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //HandGeometrySWExtYPanel.setBackground(new Color(255,255,0,255));
       HandGeometrySWExtYPanel.setOpaque(false);
       HandGeometrySWExtYPanel.setVisible(false);
       HandGeometrySWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       HandGeometryScanImageBoundPanel.add(HandGeometrySWExtYPanel);
       
       HandGeometryScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {HandGeometrySWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {HandGeometrySWExtYPanel.setVisible(false);}});
       HandGeometryScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {HandGeometrySWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {HandGeometrySWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel HandGeometryScanSmallOptionsLabel = new JLabel();
       HandGeometryScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       HandGeometryScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       HandGeometryScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon HandGeometryScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       HandGeometryScanSmallOptionsLabel.setIcon(HandGeometryScanSmallOptions);
       HandGeometryScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       HandGeometrySWExtYPanel.add(HandGeometryScanSmallOptionsLabel);
       HandGeometrySWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel HandGeometryScanSmallFolderLabel = new JLabel();
       HandGeometryScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       HandGeometryScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       HandGeometryScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       HandGeometryScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon HandGeometryScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       HandGeometryScanSmallFolderLabel.setIcon(HandGeometryScanSmallFolder);
       HandGeometrySWExtYPanel.add(HandGeometryScanSmallFolderLabel);
       HandGeometrySWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel HandGeometryScanSmallExpandLabel = new JLabel();
       HandGeometryScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       HandGeometryScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       HandGeometryScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       HandGeometryScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon HandGeometryScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       HandGeometryScanSmallExpandLabel.setIcon(HandGeometryScanSmallExpand);
       HandGeometrySWExtYPanel.add(HandGeometryScanSmallExpandLabel);
       HandGeometrySWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel HandGeometryScanSmallReloadLabel = new JLabel();
       HandGeometryScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       HandGeometryScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       HandGeometryScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       HandGeometryScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon HandGeometryScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       HandGeometryScanSmallReloadLabel.setIcon(HandGeometryScanSmallReload);
       HandGeometrySWExtYPanel.add(HandGeometryScanSmallReloadLabel);
       HandGeometrySWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel HandGeometryScanSmallDiscardLabel = new JLabel();
       HandGeometryScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       HandGeometryScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       HandGeometryScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       HandGeometryScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon HandGeometryScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       HandGeometryScanSmallDiscardLabel.setIcon(HandGeometryScanSmallDiscard);
       HandGeometrySWExtYPanel.add(HandGeometryScanSmallDiscardLabel);
       HandGeometrySWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel HandGeometryScanSmallCropLabel = new JLabel();
       HandGeometryScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       HandGeometryScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       HandGeometryScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       HandGeometryScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon HandGeometryScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       HandGeometryScanSmallCropLabel.setIcon(HandGeometryScanSmallCrop);
       HandGeometrySWExtYPanel.add(HandGeometryScanSmallCropLabel);
       HandGeometrySWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel HandGeometryScanSmallGrayScaleLabel = new JLabel();
       HandGeometryScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       HandGeometryScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       HandGeometryScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       HandGeometryScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon HandGeometryScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       HandGeometryScanSmallGrayScaleLabel.setIcon(HandGeometryScanSmallGrayScale);
       HandGeometrySWExtYPanel.add(HandGeometryScanSmallGrayScaleLabel);
       HandGeometrySWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Hand Geometry
      
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DNA 
       final JPanel DNAScanImageBoundPanel = new JPanel();
       DNAScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       DNAScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"DNA Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       DNAScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //DNAScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       DNAScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       DNAScanImageBoundPanel.setVisible(false);
       
       final JButton DNAScanImageBoundPanelButton=new JButton("DNA");
       DNAScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       DNAScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       DNAScanImageBoundPanelButton.setContentAreaFilled(true);
       DNAScanImageBoundPanelButton.setBorderPainted(true);
       DNAScanImageBoundPanelButton.setFocusPainted(false);
       DNAScanImageBoundPanelButton.setToolTipText("DNA");
       DNAScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       DNAScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       DNAScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       DNAScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       DNAScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       DNAScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       DNAScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel DNAScanImageButtonLabel = new JLabel();
       DNAScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon DNAScanImage= new ImageIcon(new ImageIcon("data/images/DNA/image_DNAScan150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       DNAScanImageButtonLabel.setIcon(DNAScanImage);
       DNAScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       DNAScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       DNAScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       DNAScanImageButtonLabel.setToolTipText("DNA");
       
       
       DNAScanImageBoundPanelButton.add(DNAScanImageButtonLabel); //add label to button
       DNAScanImageBoundPanel.add(DNAScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(DNAScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel DNASWExtYPanel = new JPanel();
       DNASWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       DNASWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //DNASWExtYPanel.setBackground(new Color(255,255,0,255));
       DNASWExtYPanel.setOpaque(false);
       DNASWExtYPanel.setVisible(false);
       DNASWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       DNAScanImageBoundPanel.add(DNASWExtYPanel);
       
       DNAScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {DNASWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {DNASWExtYPanel.setVisible(false);}});
       DNAScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {DNASWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {DNASWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel DNAScanSmallOptionsLabel = new JLabel();
       DNAScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       DNAScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       DNAScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon DNAScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       DNAScanSmallOptionsLabel.setIcon(DNAScanSmallOptions);
       DNAScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       DNASWExtYPanel.add(DNAScanSmallOptionsLabel);
       DNASWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel DNAScanSmallFolderLabel = new JLabel();
       DNAScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       DNAScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       DNAScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       DNAScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon DNAScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       DNAScanSmallFolderLabel.setIcon(DNAScanSmallFolder);
       DNASWExtYPanel.add(DNAScanSmallFolderLabel);
       DNASWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel DNAScanSmallExpandLabel = new JLabel();
       DNAScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       DNAScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       DNAScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       DNAScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon DNAScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       DNAScanSmallExpandLabel.setIcon(DNAScanSmallExpand);
       DNASWExtYPanel.add(DNAScanSmallExpandLabel);
       DNASWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel DNAScanSmallReloadLabel = new JLabel();
       DNAScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       DNAScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       DNAScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       DNAScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon DNAScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       DNAScanSmallReloadLabel.setIcon(DNAScanSmallReload);
       DNASWExtYPanel.add(DNAScanSmallReloadLabel);
       DNASWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel DNAScanSmallDiscardLabel = new JLabel();
       DNAScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       DNAScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       DNAScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       DNAScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon DNAScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       DNAScanSmallDiscardLabel.setIcon(DNAScanSmallDiscard);
       DNASWExtYPanel.add(DNAScanSmallDiscardLabel);
       DNASWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel DNAScanSmallCropLabel = new JLabel();
       DNAScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       DNAScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       DNAScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       DNAScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon DNAScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       DNAScanSmallCropLabel.setIcon(DNAScanSmallCrop);
       DNASWExtYPanel.add(DNAScanSmallCropLabel);
       DNASWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel DNAScanSmallGrayScaleLabel = new JLabel();
       DNAScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       DNAScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       DNAScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       DNAScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon DNAScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       DNAScanSmallGrayScaleLabel.setIcon(DNAScanSmallGrayScale);
       DNASWExtYPanel.add(DNAScanSmallGrayScaleLabel);
       DNASWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End DNA
       
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Drive_Passport 
       final JPanel Drive_PassportScanImageBoundPanel = new JPanel();
       Drive_PassportScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       Drive_PassportScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"Drive_Passport Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       Drive_PassportScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //Drive_PassportScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       Drive_PassportScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       Drive_PassportScanImageBoundPanel.setVisible(false);
       
       final JButton Drive_PassportScanImageBoundPanelButton=new JButton("Driving Licence/Passport");
       Drive_PassportScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       Drive_PassportScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       Drive_PassportScanImageBoundPanelButton.setContentAreaFilled(true);
       Drive_PassportScanImageBoundPanelButton.setBorderPainted(true);
       Drive_PassportScanImageBoundPanelButton.setFocusPainted(false);
       Drive_PassportScanImageBoundPanelButton.setToolTipText("Dring Licence/passport");
       Drive_PassportScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       Drive_PassportScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       Drive_PassportScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       Drive_PassportScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       Drive_PassportScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       Drive_PassportScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       Drive_PassportScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel Drive_PassportScanImageButtonLabel = new JLabel();
       Drive_PassportScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon Drive_PassportScanImage= new ImageIcon(new ImageIcon("data/images/Drive_Passport/image_Drive_PassportScan150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       Drive_PassportScanImageButtonLabel.setIcon(Drive_PassportScanImage);
       Drive_PassportScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       Drive_PassportScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       Drive_PassportScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       Drive_PassportScanImageButtonLabel.setToolTipText("Driving Licence/passport");
       
       
       Drive_PassportScanImageBoundPanelButton.add(Drive_PassportScanImageButtonLabel); //add label to button
       Drive_PassportScanImageBoundPanel.add(Drive_PassportScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(Drive_PassportScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel Drive_PassportSWExtYPanel = new JPanel();
       Drive_PassportSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       Drive_PassportSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //Drive_PassportSWExtYPanel.setBackground(new Color(255,255,0,255));
       Drive_PassportSWExtYPanel.setOpaque(false);
       Drive_PassportSWExtYPanel.setVisible(false);
       Drive_PassportSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       Drive_PassportScanImageBoundPanel.add(Drive_PassportSWExtYPanel);
       
       Drive_PassportScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {Drive_PassportSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {Drive_PassportSWExtYPanel.setVisible(false);}});
       Drive_PassportScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {Drive_PassportSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {Drive_PassportSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel Drive_PassportScanSmallOptionsLabel = new JLabel();
       Drive_PassportScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       Drive_PassportScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       Drive_PassportScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon Drive_PassportScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       Drive_PassportScanSmallOptionsLabel.setIcon(Drive_PassportScanSmallOptions);
       Drive_PassportScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       Drive_PassportSWExtYPanel.add(Drive_PassportScanSmallOptionsLabel);
       Drive_PassportSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel Drive_PassportScanSmallFolderLabel = new JLabel();
       Drive_PassportScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       Drive_PassportScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       Drive_PassportScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       Drive_PassportScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon Drive_PassportScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       Drive_PassportScanSmallFolderLabel.setIcon(Drive_PassportScanSmallFolder);
       Drive_PassportSWExtYPanel.add(Drive_PassportScanSmallFolderLabel);
       Drive_PassportSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel Drive_PassportScanSmallExpandLabel = new JLabel();
       Drive_PassportScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       Drive_PassportScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       Drive_PassportScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       Drive_PassportScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon Drive_PassportScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       Drive_PassportScanSmallExpandLabel.setIcon(Drive_PassportScanSmallExpand);
       Drive_PassportSWExtYPanel.add(Drive_PassportScanSmallExpandLabel);
       Drive_PassportSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel Drive_PassportScanSmallReloadLabel = new JLabel();
       Drive_PassportScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       Drive_PassportScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       Drive_PassportScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       Drive_PassportScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon Drive_PassportScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       Drive_PassportScanSmallReloadLabel.setIcon(Drive_PassportScanSmallReload);
       Drive_PassportSWExtYPanel.add(Drive_PassportScanSmallReloadLabel);
       Drive_PassportSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel Drive_PassportScanSmallDiscardLabel = new JLabel();
       Drive_PassportScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       Drive_PassportScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       Drive_PassportScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       Drive_PassportScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon Drive_PassportScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       Drive_PassportScanSmallDiscardLabel.setIcon(Drive_PassportScanSmallDiscard);
       Drive_PassportSWExtYPanel.add(Drive_PassportScanSmallDiscardLabel);
       Drive_PassportSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel Drive_PassportScanSmallCropLabel = new JLabel();
       Drive_PassportScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       Drive_PassportScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       Drive_PassportScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       Drive_PassportScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon Drive_PassportScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       Drive_PassportScanSmallCropLabel.setIcon(Drive_PassportScanSmallCrop);
       Drive_PassportSWExtYPanel.add(Drive_PassportScanSmallCropLabel);
       Drive_PassportSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel Drive_PassportScanSmallGrayScaleLabel = new JLabel();
       Drive_PassportScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       Drive_PassportScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       Drive_PassportScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       Drive_PassportScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon Drive_PassportScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       Drive_PassportScanSmallGrayScaleLabel.setIcon(Drive_PassportScanSmallGrayScale);
       Drive_PassportSWExtYPanel.add(Drive_PassportScanSmallGrayScaleLabel);
       Drive_PassportSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Drive_Passport
       
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Ordor 
       final JPanel OrdorScanImageBoundPanel = new JPanel();
       OrdorScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       OrdorScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"Ordor Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       OrdorScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //OrdorScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       OrdorScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       OrdorScanImageBoundPanel.setVisible(false);
       
       final JButton OrdorScanImageBoundPanelButton=new JButton("Ordor");
       OrdorScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       OrdorScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       OrdorScanImageBoundPanelButton.setContentAreaFilled(true);
       OrdorScanImageBoundPanelButton.setBorderPainted(true);
       OrdorScanImageBoundPanelButton.setFocusPainted(false);
       OrdorScanImageBoundPanelButton.setToolTipText("Ordor");
       OrdorScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       OrdorScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       OrdorScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       OrdorScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       OrdorScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       OrdorScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       OrdorScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel OrdorScanImageButtonLabel = new JLabel();
       OrdorScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon OrdorScanImage= new ImageIcon(new ImageIcon("data/images/Ordor/image_OrdorScan150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       OrdorScanImageButtonLabel.setIcon(OrdorScanImage);
       OrdorScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       OrdorScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       OrdorScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       OrdorScanImageButtonLabel.setToolTipText("Ordor");
       
       
       OrdorScanImageBoundPanelButton.add(OrdorScanImageButtonLabel); //add label to button
       OrdorScanImageBoundPanel.add(OrdorScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(OrdorScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel OrdorSWExtYPanel = new JPanel();
       OrdorSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       OrdorSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //OrdorSWExtYPanel.setBackground(new Color(255,255,0,255));
       OrdorSWExtYPanel.setOpaque(false);
       OrdorSWExtYPanel.setVisible(false);
       OrdorSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       OrdorScanImageBoundPanel.add(OrdorSWExtYPanel);
       
       OrdorScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {OrdorSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {OrdorSWExtYPanel.setVisible(false);}});
       OrdorScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {OrdorSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {OrdorSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel OrdorScanSmallOptionsLabel = new JLabel();
       OrdorScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       OrdorScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       OrdorScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon OrdorScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       OrdorScanSmallOptionsLabel.setIcon(OrdorScanSmallOptions);
       OrdorScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       OrdorSWExtYPanel.add(OrdorScanSmallOptionsLabel);
       OrdorSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel OrdorScanSmallFolderLabel = new JLabel();
       OrdorScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       OrdorScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       OrdorScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       OrdorScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon OrdorScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       OrdorScanSmallFolderLabel.setIcon(OrdorScanSmallFolder);
       OrdorSWExtYPanel.add(OrdorScanSmallFolderLabel);
       OrdorSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel OrdorScanSmallExpandLabel = new JLabel();
       OrdorScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       OrdorScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       OrdorScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       OrdorScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon OrdorScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       OrdorScanSmallExpandLabel.setIcon(OrdorScanSmallExpand);
       OrdorSWExtYPanel.add(OrdorScanSmallExpandLabel);
       OrdorSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel OrdorScanSmallReloadLabel = new JLabel();
       OrdorScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       OrdorScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       OrdorScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       OrdorScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon OrdorScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       OrdorScanSmallReloadLabel.setIcon(OrdorScanSmallReload);
       OrdorSWExtYPanel.add(OrdorScanSmallReloadLabel);
       OrdorSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel OrdorScanSmallDiscardLabel = new JLabel();
       OrdorScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       OrdorScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       OrdorScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       OrdorScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon OrdorScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       OrdorScanSmallDiscardLabel.setIcon(OrdorScanSmallDiscard);
       OrdorSWExtYPanel.add(OrdorScanSmallDiscardLabel);
       OrdorSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel OrdorScanSmallCropLabel = new JLabel();
       OrdorScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       OrdorScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       OrdorScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       OrdorScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon OrdorScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       OrdorScanSmallCropLabel.setIcon(OrdorScanSmallCrop);
       OrdorSWExtYPanel.add(OrdorScanSmallCropLabel);
       OrdorSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel OrdorScanSmallGrayScaleLabel = new JLabel();
       OrdorScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       OrdorScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       OrdorScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       OrdorScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon OrdorScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       OrdorScanSmallGrayScaleLabel.setIcon(OrdorScanSmallGrayScale);
       OrdorSWExtYPanel.add(OrdorScanSmallGrayScaleLabel);
       OrdorSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Ordor

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Voice 
       final JPanel VoiceScanImageBoundPanel = new JPanel();
       VoiceScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       VoiceScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"Voice Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       VoiceScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //VoiceScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       VoiceScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       VoiceScanImageBoundPanel.setVisible(false);
       
       final JButton VoiceScanImageBoundPanelButton=new JButton("Voice");
       VoiceScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       VoiceScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       VoiceScanImageBoundPanelButton.setContentAreaFilled(true);
       VoiceScanImageBoundPanelButton.setBorderPainted(true);
       VoiceScanImageBoundPanelButton.setFocusPainted(false);
       VoiceScanImageBoundPanelButton.setToolTipText("Voice");
       VoiceScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       VoiceScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       VoiceScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       VoiceScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       VoiceScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       VoiceScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       VoiceScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       final JLabel VoiceScanImageButtonLabel = new JLabel();
       VoiceScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon VoiceScanImage= new ImageIcon(new ImageIcon("data/images/Voice/image_VoiceScan150.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       VoiceScanImageButtonLabel.setIcon(VoiceScanImage);
       VoiceScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       VoiceScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       VoiceScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       VoiceScanImageButtonLabel.setToolTipText("Voice");
       
       
       VoiceScanImageBoundPanelButton.add(VoiceScanImageButtonLabel); //add label to button
       VoiceScanImageBoundPanel.add(VoiceScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(VoiceScanImageBoundPanel); //add biometric windows to P2 carpet 
       
       final JPanel VoiceSWExtYPanel = new JPanel();
       VoiceSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       VoiceSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //VoiceSWExtYPanel.setBackground(new Color(255,255,0,255));
       VoiceSWExtYPanel.setOpaque(false);
       VoiceSWExtYPanel.setVisible(false);
       VoiceSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       VoiceScanImageBoundPanel.add(VoiceSWExtYPanel);
       
       VoiceScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {VoiceSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {VoiceSWExtYPanel.setVisible(false);}});
       VoiceScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {VoiceSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {VoiceSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel VoiceScanSmallOptionsLabel = new JLabel();
       VoiceScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       VoiceScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       VoiceScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon VoiceScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       VoiceScanSmallOptionsLabel.setIcon(VoiceScanSmallOptions);
       VoiceScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       VoiceSWExtYPanel.add(VoiceScanSmallOptionsLabel);
       VoiceSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel VoiceScanSmallFolderLabel = new JLabel();
       VoiceScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       VoiceScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       VoiceScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       VoiceScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon VoiceScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       VoiceScanSmallFolderLabel.setIcon(VoiceScanSmallFolder);
       VoiceSWExtYPanel.add(VoiceScanSmallFolderLabel);
       VoiceSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel VoiceScanSmallExpandLabel = new JLabel();
       VoiceScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       VoiceScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       VoiceScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       VoiceScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon VoiceScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       VoiceScanSmallExpandLabel.setIcon(VoiceScanSmallExpand);
       VoiceSWExtYPanel.add(VoiceScanSmallExpandLabel);
       VoiceSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel VoiceScanSmallReloadLabel = new JLabel();
       VoiceScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       VoiceScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       VoiceScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       VoiceScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon VoiceScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       VoiceScanSmallReloadLabel.setIcon(VoiceScanSmallReload);
       VoiceSWExtYPanel.add(VoiceScanSmallReloadLabel);
       VoiceSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel VoiceScanSmallDiscardLabel = new JLabel();
       VoiceScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       VoiceScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       VoiceScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       VoiceScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon VoiceScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       VoiceScanSmallDiscardLabel.setIcon(VoiceScanSmallDiscard);
       VoiceSWExtYPanel.add(VoiceScanSmallDiscardLabel);
       VoiceSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel VoiceScanSmallCropLabel = new JLabel();
       VoiceScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       VoiceScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       VoiceScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       VoiceScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon VoiceScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       VoiceScanSmallCropLabel.setIcon(VoiceScanSmallCrop);
       VoiceSWExtYPanel.add(VoiceScanSmallCropLabel);
       VoiceSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel VoiceScanSmallGrayScaleLabel = new JLabel();
       VoiceScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       VoiceScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       VoiceScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       VoiceScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon VoiceScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       VoiceScanSmallGrayScaleLabel.setIcon(VoiceScanSmallGrayScale);
       VoiceSWExtYPanel.add(VoiceScanSmallGrayScaleLabel);
       VoiceSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Voice

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Signature 
       final JPanel SignatureScanImageBoundPanel = new JPanel();
       SignatureScanImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       SignatureScanImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       /*  TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,0), BioWindowTitleSpace+"Signature Biometric");
       Font font = new Font("Serif", Font.ITALIC, 12);
       thatBorder2.setTitleColor(Color.red);
       thatBorder2.setTitleFont(font);
       SignatureScanImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
      */
       //SignatureScanImageBoundPanel.setBackground(new Color(255,255,0,255));
       SignatureScanImageBoundPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth),BioWindowsHeight+BioWExtY));
       SignatureScanImageBoundPanel.setVisible(true);
       
       final JButton SignatureScanImageBoundPanelButton=new JButton("Signature");
       SignatureScanImageBoundPanelButton.setFont(Font.decode("Arial-9"));
       SignatureScanImageBoundPanelButton.setBackground(new Color(255,255,255,255));
       SignatureScanImageBoundPanelButton.setContentAreaFilled(true);
       SignatureScanImageBoundPanelButton.setBorderPainted(true);
       SignatureScanImageBoundPanelButton.setFocusPainted(false);
       SignatureScanImageBoundPanelButton.setToolTipText("Signature");
       SignatureScanImageBoundPanelButton.setPreferredSize(new Dimension(BioWindowsWidth-2,BioWindowsHeight-2));
       SignatureScanImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
       SignatureScanImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
       SignatureScanImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       SignatureScanImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,1));
       SignatureScanImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       SignatureScanImageBoundPanelButton.addMouseListener(new MouseAdapter() 
       {
          public void mouseEntered(MouseEvent evt) 
          {
        	
          }
          public void mouseExited(MouseEvent evt) 
          {
        	 
          }
       });
       
       
       
       // SignatureScanImageButtonLabel = new JLabel();
       SignatureScanImageButtonLabel.setPreferredSize(new Dimension(BioWindowsWidth-4,BioWindowsHeight-4));
       ImageIcon SignatureScanImage= new ImageIcon(new ImageIcon("data/images/Signature/DigitalSignature.png").getImage().getScaledInstance(BioWindowsWidth-4, BioWindowsHeight-4, Image.SCALE_DEFAULT)); 
       SignatureScanImageButtonLabel.setIcon(SignatureScanImage);
       SignatureScanImageButtonLabel.setBackground(new Color(255,255,0,255));
       SignatureScanImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       SignatureScanImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,1));
       SignatureScanImageButtonLabel.setToolTipText("Signature");
       
       
       SignatureScanImageBoundPanelButton.add(SignatureScanImageButtonLabel); //add label to button
       SignatureScanImageBoundPanel.add(SignatureScanImageBoundPanelButton);//add button to bounding panel
       RealBioImagesBgPanel.add(SignatureScanImageBoundPanel); //add biometric windows to P2 carpet 

       
       SignatureScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {
          public void mouseClicked(MouseEvent evt) 
          {
        	  System.out.println("signature pressed");
     	    //	new encodePane(); //for huion signature pad
        	  new AcmisTopazSignature();  //for topaz signature pad
          }
       });
       
       
       
       final JPanel SignatureSWExtYPanel = new JPanel();
       SignatureSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
       SignatureSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
       //SignatureSWExtYPanel.setBackground(new Color(255,255,0,255));
       SignatureSWExtYPanel.setOpaque(false);
       SignatureSWExtYPanel.setVisible(false);
       SignatureSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
       SignatureScanImageBoundPanel.add(SignatureSWExtYPanel);
       
       SignatureScanImageBoundPanel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {SignatureSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {SignatureSWExtYPanel.setVisible(false);}});
       SignatureScanImageButtonLabel.addMouseListener(new MouseAdapter() 
       {  public void mouseEntered(MouseEvent evt) 
          {SignatureSWExtYPanel.setVisible(true);}
          public void mouseExited(MouseEvent evt) 
          {SignatureSWExtYPanel.setVisible(false);}});
       // tiny icons
      // int SmallIconSize=10; String smallIconSpacing=" ";
       final JLabel SignatureScanSmallOptionsLabel = new JLabel();
       SignatureScanSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
       SignatureScanSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SignatureScanSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SignatureScanSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SignatureScanSmallOptionsLabel.setIcon(SignatureScanSmallOptions);
       SignatureScanSmallOptionsLabel.setBackground(new Color(255,255,0,255));
       SignatureSWExtYPanel.add(SignatureScanSmallOptionsLabel);
       SignatureSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SignatureScanSmallFolderLabel = new JLabel();
       SignatureScanSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SignatureScanSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SignatureScanSmallFolderLabel.setBackground(new Color(255,255,0,255));
       SignatureScanSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SignatureScanSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SignatureScanSmallFolderLabel.setIcon(SignatureScanSmallFolder);
       SignatureSWExtYPanel.add(SignatureScanSmallFolderLabel);
       SignatureSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SignatureScanSmallExpandLabel = new JLabel();
       SignatureScanSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SignatureScanSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SignatureScanSmallExpandLabel.setBackground(new Color(255,255,0,255));
       SignatureScanSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SignatureScanSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SignatureScanSmallExpandLabel.setIcon(SignatureScanSmallExpand);
       SignatureSWExtYPanel.add(SignatureScanSmallExpandLabel);
       SignatureSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SignatureScanSmallReloadLabel = new JLabel();
       SignatureScanSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SignatureScanSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SignatureScanSmallReloadLabel.setBackground(new Color(255,255,0,255));
       SignatureScanSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SignatureScanSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SignatureScanSmallReloadLabel.setIcon(SignatureScanSmallReload);
       SignatureSWExtYPanel.add(SignatureScanSmallReloadLabel);
       SignatureSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SignatureScanSmallDiscardLabel = new JLabel();
       SignatureScanSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SignatureScanSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SignatureScanSmallDiscardLabel.setBackground(new Color(255,255,0,255));
       SignatureScanSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SignatureScanSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SignatureScanSmallDiscardLabel.setIcon(SignatureScanSmallDiscard);
       SignatureSWExtYPanel.add(SignatureScanSmallDiscardLabel);
       SignatureSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SignatureScanSmallCropLabel = new JLabel();
       SignatureScanSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SignatureScanSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SignatureScanSmallCropLabel.setBackground(new Color(255,255,0,255));
       SignatureScanSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SignatureScanSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SignatureScanSmallCropLabel.setIcon(SignatureScanSmallCrop);
       SignatureSWExtYPanel.add(SignatureScanSmallCropLabel);
       SignatureSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
       final JLabel SignatureScanSmallGrayScaleLabel = new JLabel();
       SignatureScanSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
       SignatureScanSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
       SignatureScanSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
       SignatureScanSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
       ImageIcon SignatureScanSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
       SignatureScanSmallGrayScaleLabel.setIcon(SignatureScanSmallGrayScale);
       SignatureSWExtYPanel.add(SignatureScanSmallGrayScaleLabel);
       SignatureSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
       
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Signature

      
       
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& right downer pannel where card IDS go
      // int BioWindowsWidth = 150, BioWindowsHeight=150, BioWExtY=20; Color baseColor=new Color(200,0,0,50);
    //   String BioWindowTitleSpace="  
       ImageIcon IDCardUpperSideImage= new ImageIcon("data/images/cards/Muk_ID_Template_Side_A.png"); 
       ImageIcon IDCardBottomSideImage= new ImageIcon("data/images/cards/Muk_ID_Template_Side_B.png"); 
       //int cgetIconHeight()
       final int IDCardWidth =IDCardUpperSideImage.getIconWidth(); //(int) ((int)BodyBGPanel.getSize().width/2.5);
       final int IDCardHeight=IDCardUpperSideImage.getIconHeight();//(int) ((int)BodyBGPanel.getSize().height/2.5);";
       int leftCardPadding = 5, rightCardPadding=15, bottomCardPadding=15, topCardPadding=5;
       Color CardEditorColor = new Color(50,50,50,255);
       
       int IDcarddesigntoolsWidth = 500;
       
         JPanel IDCardsImagesBgPanel = new JPanel();
         IDCardsImagesBgPanel.setLayout((new FlowLayout(FlowLayout.LEFT,3,10)));
         IDCardsImagesBgPanel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
         IDCardsImagesBgPanel.setBackground(CardEditorColor);
         IDCardsImagesBgPanel.setPreferredSize(new Dimension((int)(BodyBGPanel.getSize().width/1.27), (int)(BodyBGPanel.getSize().height/1)+BioWExtY));       //2.3 //1.4
         p3.add(IDCardsImagesBgPanel);
//**************************************************************************************************************** ID card design surface  
         
         JPanel IDCardUpperSideImageBoundPanel = new JPanel();
         IDCardUpperSideImageBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
         IDCardUpperSideImageBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
        /* TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,1), BioWindowTitleSpace+"");
         Font font = new Font("Serif", Font.ITALIC, 12);
         thatBorder2.setTitleColor(Color.red);
         thatBorder2.setTitleFont(font);
         IDCardUpperSideImageBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
        */
         IDCardUpperSideImageBoundPanel.setBackground(null); //new Color(255,255,0,255)
         IDCardUpperSideImageBoundPanel.setPreferredSize(new Dimension((int)(IDCardWidth)+rightCardPadding+10,(int)(BodyBGPanel.getSize().height/1.45)+BioWExtY-5));
         
         final JButton IDCardUpperSideImageBoundPanelButton=new JButton("Identity Card");
         IDCardUpperSideImageBoundPanelButton.setFont(Font.decode("Arial-9"));
         IDCardUpperSideImageBoundPanelButton.setBackground(CardEditorColor);
         IDCardUpperSideImageBoundPanelButton.setContentAreaFilled(true);
         IDCardUpperSideImageBoundPanelButton.setBorderPainted(true);
         IDCardUpperSideImageBoundPanelButton.setFocusPainted(false);
         IDCardUpperSideImageBoundPanelButton.setToolTipText("Identity Card");
         IDCardUpperSideImageBoundPanelButton.setPreferredSize(new Dimension(IDCardWidth-2+rightCardPadding,IDCardHeight-2+bottomCardPadding));
         IDCardUpperSideImageBoundPanelButton.setVerticalTextPosition(SwingConstants.CENTER);
         IDCardUpperSideImageBoundPanelButton.setHorizontalTextPosition(SwingConstants.CENTER);
         IDCardUpperSideImageBoundPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
         IDCardUpperSideImageBoundPanelButton.setBorder(BorderFactory.createLineBorder(Color.blue,0));
         IDCardUpperSideImageBoundPanelButton.setLayout((new FlowLayout(FlowLayout.LEFT,leftCardPadding,topCardPadding)));
         IDCardUpperSideImageBoundPanelButton.addMouseListener(new MouseAdapter() 
         {
            public void mouseEntered(MouseEvent evt) 
            {
          	
            }
            public void mouseExited(MouseEvent evt) 
            {
          	 
            }
         });
         
         //******************** get buffered images
         BufferedImage IDCardSideA;
         BufferedImage IDCardSideB;
         BufferedImage IDCardCombined;
         
         
         try {
			IDCardSideA = ImageIO.read(new File("data/images/cards/IDtemplateSideA.png"));//ImageIO.read(getClass().getClassLoader().getResource("data/images/cards/IDtemplateSideA.png"));
			IDCardSideB = ImageIO.read(new File("data/images/cards/IDtemplateSideB.png"));//ImageIO.read(getClass().getClassLoader().getResource("data/images/cards/IDtemplateSideB.png"));
			IDCardCombined = new BufferedImage(710,446,BufferedImage.TYPE_INT_ARGB);
			Graphics2D gIDCard = IDCardCombined.createGraphics();
			gIDCard.drawImage(IDCardSideB,0,0,null);
			gIDCard.drawImage(IDCardSideA,0,0,null);
			gIDCard.dispose();
			/*
			JLabel IDCardUpperSideImageButtonLabel = new JLabel();
			IDCardUpperSideImageButtonLabel.setOpaque(false);
			//IDCardUpperSideImageButtonLabel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
			IDCardUpperSideImageButtonLabel.setPreferredSize(new Dimension(IDCardWidth,IDCardHeight));  //
			IDCardUpperSideImageButtonLabel.setIcon(new ImageIcon(IDCardCombined));
			IDCardUpperSideImageBoundPanelButton.add(IDCardUpperSideImageButtonLabel); //add label to button
			*/
			/*try{
				ImageIO.write(IDCardCombined, "PNG", new File("E:\\Java-workspace\\Bio Identity\\data\\images\\cards\\Card_Output\\IDCard.png"));
			}
			
			catch(IOException e){
				e.printStackTrace();
			}*/
			
           
         
         } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         
         
         final JLabel IDCardUpperSideImageButtonLabel = new JLabel();
      //   IDCardUpperSideImageButtonLabel.setPreferredSize(new Dimension(IDCardWidth-4,IDCardHeight-4));
         //ImageIcon IDCardUpperSideImage= new ImageIcon(new ImageIcon("data/images/cards/card_chip.png").getImage().getScaledInstance(IDCardWidth, IDCardHeight, Image.SCALE_DEFAULT)); 
         //getIconHeight()
         
         IDCardUpperSideImageButtonLabel.setIcon(IDCardUpperSideImage);
         IDCardUpperSideImageButtonLabel.setBackground(new Color(0,0,0,255));
         IDCardUpperSideImageButtonLabel.setForeground(null);
         IDCardUpperSideImageButtonLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
         IDCardUpperSideImageButtonLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
         IDCardUpperSideImageButtonLabel.setOpaque(false);
         IDCardUpperSideImageButtonLabel.setToolTipText("Id");
         IDCardUpperSideImageButtonLabel.setPreferredSize(new Dimension(IDCardWidth+5,IDCardHeight+5));
        /* System.out.println(wix);
         System.out.println(IDCardHeight);*/
         
         
         IDCardUpperSideImageBoundPanelButton.add(IDCardUpperSideImageButtonLabel); //add label to button
         IDCardUpperSideImageBoundPanel.add(IDCardUpperSideImageBoundPanelButton);//add button to bounding panel
         IDCardsImagesBgPanel.add(IDCardUpperSideImageBoundPanel); //add biometric windows to P2 carpet 
         
         final JPanel IDCardUpperSideSWExtYPanel = new JPanel();
         IDCardUpperSideSWExtYPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,2)));
         IDCardUpperSideSWExtYPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
         //IDCardUpperSideSWExtYPanel.setBackground(new Color(255,255,0,255));
         IDCardUpperSideSWExtYPanel.setOpaque(false); 
         IDCardUpperSideSWExtYPanel.setVisible(false);
         IDCardUpperSideSWExtYPanel.setPreferredSize(new Dimension((int)(BioWindowsWidth)-2,BioWExtY));
         IDCardUpperSideImageBoundPanel.add(IDCardUpperSideSWExtYPanel);
         
         IDCardUpperSideImageBoundPanel.addMouseListener(new MouseAdapter() 
         {  public void mouseEntered(MouseEvent evt) 
            {IDCardUpperSideSWExtYPanel.setVisible(true);}
            public void mouseExited(MouseEvent evt) 
            {IDCardUpperSideSWExtYPanel.setVisible(false);}});
         IDCardUpperSideImageButtonLabel.addMouseListener(new MouseAdapter() 
         {  public void mouseEntered(MouseEvent evt) 
            {IDCardUpperSideSWExtYPanel.setVisible(true);}
            public void mouseExited(MouseEvent evt) 
            {IDCardUpperSideSWExtYPanel.setVisible(false);}});
         // tiny icons
        // int SmallIconSize=10; String smallIconSpacing=" ";
         final JLabel IDCardUpperSideSmallOptionsLabel = new JLabel();
         IDCardUpperSideSmallOptionsLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
         IDCardUpperSideSmallOptionsLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
         IDCardUpperSideSmallOptionsLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
         ImageIcon IDCardUpperSideSmallOptions= new ImageIcon(new ImageIcon("data/images/icons/icon_smallOptions.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
         IDCardUpperSideSmallOptionsLabel.setIcon(IDCardUpperSideSmallOptions);
         IDCardUpperSideSmallOptionsLabel.setBackground(new Color(255,255,0,255));
         IDCardUpperSideSWExtYPanel.add(IDCardUpperSideSmallOptionsLabel);
         IDCardUpperSideSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
         
         final JLabel IDCardUpperSideSmallFolderLabel = new JLabel();
         IDCardUpperSideSmallFolderLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
         IDCardUpperSideSmallFolderLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
         IDCardUpperSideSmallFolderLabel.setBackground(new Color(255,255,0,255));
         IDCardUpperSideSmallFolderLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
         ImageIcon IDCardUpperSideSmallFolder= new ImageIcon(new ImageIcon("data/images/icons/icon_smallFolder.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
         IDCardUpperSideSmallFolderLabel.setIcon(IDCardUpperSideSmallFolder);
         IDCardUpperSideSWExtYPanel.add(IDCardUpperSideSmallFolderLabel);
         IDCardUpperSideSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
         
         final JLabel IDCardUpperSideSmallExpandLabel = new JLabel();
         IDCardUpperSideSmallExpandLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
         IDCardUpperSideSmallExpandLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
         IDCardUpperSideSmallExpandLabel.setBackground(new Color(255,255,0,255));
         IDCardUpperSideSmallExpandLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
         ImageIcon IDCardUpperSideSmallExpand= new ImageIcon(new ImageIcon("data/images/icons/icon_smallExpand.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
         IDCardUpperSideSmallExpandLabel.setIcon(IDCardUpperSideSmallExpand);
         IDCardUpperSideSWExtYPanel.add(IDCardUpperSideSmallExpandLabel);
         IDCardUpperSideSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
         
         final JLabel IDCardUpperSideSmallReloadLabel = new JLabel();
         IDCardUpperSideSmallReloadLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
         IDCardUpperSideSmallReloadLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
         IDCardUpperSideSmallReloadLabel.setBackground(new Color(255,255,0,255));
         IDCardUpperSideSmallReloadLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
         ImageIcon IDCardUpperSideSmallReload= new ImageIcon(new ImageIcon("data/images/icons/icon_smallReload.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
         IDCardUpperSideSmallReloadLabel.setIcon(IDCardUpperSideSmallReload);
         IDCardUpperSideSWExtYPanel.add(IDCardUpperSideSmallReloadLabel);
         IDCardUpperSideSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
         
         final JLabel IDCardUpperSideSmallDiscardLabel = new JLabel();
         IDCardUpperSideSmallDiscardLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
         IDCardUpperSideSmallDiscardLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
         IDCardUpperSideSmallDiscardLabel.setBackground(new Color(255,255,0,255));
         IDCardUpperSideSmallDiscardLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
         ImageIcon IDCardUpperSideSmallDiscard= new ImageIcon(new ImageIcon("data/images/icons/icon_smallDiscard.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
         IDCardUpperSideSmallDiscardLabel.setIcon(IDCardUpperSideSmallDiscard);
         IDCardUpperSideSWExtYPanel.add(IDCardUpperSideSmallDiscardLabel);
         IDCardUpperSideSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
         
         final JLabel IDCardUpperSideSmallCropLabel = new JLabel();
         IDCardUpperSideSmallCropLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
         IDCardUpperSideSmallCropLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
         IDCardUpperSideSmallCropLabel.setBackground(new Color(255,255,0,255));
         IDCardUpperSideSmallCropLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
         ImageIcon IDCardUpperSideSmallCrop= new ImageIcon(new ImageIcon("data/images/icons/icon_smallCrop.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
         IDCardUpperSideSmallCropLabel.setIcon(IDCardUpperSideSmallCrop);
         IDCardUpperSideSWExtYPanel.add(IDCardUpperSideSmallCropLabel);
         IDCardUpperSideSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
         
         final JLabel IDCardUpperSideSmallGrayScaleLabel = new JLabel();
         IDCardUpperSideSmallGrayScaleLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
         IDCardUpperSideSmallGrayScaleLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
         IDCardUpperSideSmallGrayScaleLabel.setBackground(new Color(255,255,0,255));
         IDCardUpperSideSmallGrayScaleLabel.setPreferredSize(new Dimension(SmallIconSize,SmallIconSize));
         ImageIcon IDCardUpperSideSmallGrayScale= new ImageIcon(new ImageIcon("data/images/icons/icon_smallGrayScale.png").getImage().getScaledInstance(SmallIconSize, SmallIconSize, Image.SCALE_DEFAULT)); 
         IDCardUpperSideSmallGrayScaleLabel.setIcon(IDCardUpperSideSmallGrayScale);
         IDCardUpperSideSWExtYPanel.add(IDCardUpperSideSmallGrayScaleLabel);
         IDCardUpperSideSWExtYPanel.add(new JLabel(smallIconSpacing));  // space
         
     /*    class Rect extends JPanel{
        	 super.Rect(g);
         Graphics2D g2 = (Graphics2D) g;
         RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
            qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
            g2.setRenderingHints( qualityHints );         


         g2.setPaint(Color.RED);

         double x = 50;
         double y = 50;
         double w = x + 250;
         double h = y + 100;
         g2.fill(new RoundRectangle2D.Double(x, y, w, h, 50, 50));

         g2.fill(new Rectangle2D.Double(x + 100,y,w,h));
         }*/
         
         class drawRect extends JPanel{
         public void paint(Graphics g) {
             Graphics2D graphics2 = (Graphics2D) g;
             
           RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
             qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
             graphics2.setRenderingHints( qualityHints ); 
  
             double x = 0;
             double y = 1;
             double w =710-1;
             double h = 446;
             //graphics2.setBackground(Color.yellow);
             graphics2.setPaint(new Color(20,20,20,255));
             graphics2.setStroke(new BasicStroke(3.0f));
             graphics2.draw(new RoundRectangle2D.Double(x, y, w, h, 50, 50));
             
             /*RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(100, 100, IDCardWidth, IDCardHeight, 50, 50); //IDCardWidth
             graphics2.setPaint(Color.RED);
             graphics2.setStroke(new BasicStroke(2.0f));
             graphics2.fill(roundedRectangle);*/
             
             
         }
         }
         drawRect bound = new drawRect();
         bound.setVisible(true);
         bound.setBackground(Color.yellow);
         bound.setOpaque(false);
         //bound.setBorder(BorderFactory.createLineBorder(Color.green,1));
         bound.setPreferredSize(new Dimension((int)(IDCardWidth)+5,(int)(IDCardHeight)+5));
         //bound.setLocation(0, 0);
         //bound.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
         
         IDCardUpperSideImageButtonLabel.add(bound);
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End ID design surface
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Card design tools
         IDdesign design = new IDdesign();
			design.invoke(); 
			
			String IDCardDesignToolsIconSpacing = "    ";

	         JPanel IDCardDesignToolsBoundPanel = new JPanel();
	         IDCardDesignToolsBoundPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
	         IDCardDesignToolsBoundPanel.setBorder(BorderFactory.createLineBorder(Color.red,0));
	        /* TitledBorder thatBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.red,1), BioWindowTitleSpace+"");
	         Font font = new Font("Serif", Font.ITALIC, 12);
	         thatBorder2.setTitleColor(Color.red);
	         thatBorder2.setTitleFont(font);
	         IDCardDesignToolsBoundPanel.setBorder(new TitledBorder(thatBorder2, "", TitledBorder.LEFT,TitledBorder.ABOVE_TOP, font, Color.RED)); 
	        */
	        IDCardDesignToolsBoundPanel.setBackground(CardEditorColor);
	         IDCardDesignToolsBoundPanel.setForeground(Color.red);
	         IDCardDesignToolsBoundPanel.setPreferredSize(new Dimension((int)(BodyBGPanel.getSize().width/4.5)+rightCardPadding+10,(int)(BodyBGPanel.getSize().height/1.45)+BioWExtY-5));
	         IDCardsImagesBgPanel.add(IDCardDesignToolsBoundPanel);	
	         IDCardsImagesBgPanel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
	        
		      JPanel CardBioInclude_menu_heading_panel = new JPanel();
		      CardBioInclude_menu_heading_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,3)));
		      CardBioInclude_menu_heading_panel.setBorder(emptyBorder); 
		      CardBioInclude_menu_heading_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
		      CardBioInclude_menu_heading_panel.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LdropPanBHeight));
		      //CardBioInclude_menu_heading_panel.setPreferredSize(new Dimension(25,25));
		      CardBioInclude_menu_heading_panel.setBackground(CardEditorColor);
		      IDCardDesignToolsBoundPanel.add(CardBioInclude_menu_heading_panel);
		      
		      
		      final JButton CardBioInclude_menu_heading=new JButton(new ImageIcon(new ImageIcon("data/images/icons/icon_bioInclude.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		      CardBioInclude_menu_heading.setFont(Font.decode("Arial-BOLD-14"));
		      CardBioInclude_menu_heading.setForeground(new Color(0,0,0,255));
		      CardBioInclude_menu_heading.setContentAreaFilled(false);
		      CardBioInclude_menu_heading.setBorderPainted(false);
		      CardBioInclude_menu_heading.setFocusPainted(false);
		      CardBioInclude_menu_heading.setToolTipText("Biometrics Enabled");
		      //CardBioInclude_menu_heading.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LButtdropWidth));
		      CardBioInclude_menu_heading.setVerticalTextPosition(SwingConstants.CENTER);
		      CardBioInclude_menu_heading.setHorizontalTextPosition(SwingConstants.CENTER);
		      CardBioInclude_menu_heading.setCursor(new Cursor(Cursor.HAND_CURSOR));
		      CardBioInclude_menu_heading.setBorder(BorderFactory.createLineBorder(Color.blue,0));
		      CardBioInclude_menu_heading.setLayout((new FlowLayout(FlowLayout.RIGHT,0,0)));
		      CardBioInclude_menu_heading.addMouseListener(new MouseAdapter() 
		      {
		         public void mouseEntered(MouseEvent evt) 
		         {
		       	 // CardBioInclude_menu_heading.setBackground(Color.ORANGE);
		       	  //CardBioInclude_menu_heading.setContentAreaFilled(true);
		         }
		         public void mouseExited(MouseEvent evt) 
		         {
		       	 // CardBioInclude_menu_heading.setBackground(null);
		       	 // CardBioInclude_menu_heading.setContentAreaFilled(false);
		         }
		      });
		     
		      final JLabel CardBioInclude_menu_heading_icon = new JLabel();
		      
		      //CardBioInclude_menu_heading_icon.setIcon(menu_dropped_icon);
		      
		      //CardBioInclude_menu_heading.add(CardBioInclude_menu_heading_icon);
		      
		      CardBioInclude_menu_heading_panel.add( CardBioInclude_menu_heading);
		      CardBioInclude_menu_heading_panel.add(new JLabel(IDCardDesignToolsIconSpacing));
		 //########################################################################################## End Hide and show CardBioInclude Menu button
		//***************************************************************################# CardBioInclude Items Panel 
		        
		      final JPanel CardBioInclude_menu_panel = new JPanel();
		          CardBioInclude_menu_panel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
		          CardBioInclude_menu_panel.setBorder(emptyBorder); 
		          CardBioInclude_menu_panel.setBorder(BorderFactory.createLineBorder(Color.blue,0));
		          CardBioInclude_menu_panel.setPreferredSize(new Dimension((int)(110),350));
		          CardBioInclude_menu_panel.setBackground(CardEditorColor);
		          CardBioInclude_menu_panel.setForeground(new Color(255,255,255,255));
		          CardBioInclude_menu_panel.setVisible(true);
		         // CardBioInclude_menu_panel.setVisible(false);
		        /*  if(CardBioInclude_menu_panel.isVisible()) {
		        	  CardBioInclude_menu_heading_icon.setIcon(menu_dropped_icon);
		   		   }
		   	   else {
		   		CardBioInclude_menu_heading_icon.setIcon(menu_close_icon);
		   	   }*/

		          IDCardDesignToolsBoundPanel.add(CardBioInclude_menu_panel);
		          
		        
		  //*********************************************************************************************Set admin btn menu             
		          Checkbox FaceImageCheckBox = new Checkbox("Face Image");    
		          FaceImageCheckBox.setBounds(100, 100,  50, 50);   
		          FaceImageCheckBox.setState(true);
		          FaceImageCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 RealFaceImageBoundPanel.setVisible(true);
		                 }else{
		                	 RealFaceImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });  
		          Checkbox FaceRecogCheckBox = new Checkbox("Face Scan");    
		          FaceRecogCheckBox.setBounds(100, 100,  50, 50);   
		          FaceRecogCheckBox.setState(false);
		          FaceRecogCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 BioFaceImageBoundPanel.setVisible(true);
		                 }else{
		                	 BioFaceImageBoundPanel.setVisible(false);
		                 }
		              }    
		           }); 
		          
		          Checkbox IrisScanCheckBox = new Checkbox("Iris Scan");    
		          IrisScanCheckBox.setBounds(100, 100,  50, 50);   
		          IrisScanCheckBox.setState(false);
		          IrisScanCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 IrisScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 IrisScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           }); 
		          
		          Checkbox retinaScanCheckBox = new Checkbox("Retina can");    
		          retinaScanCheckBox.setBounds(100, 100,  50, 50);   
		          retinaScanCheckBox.setState(false);
		          retinaScanCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 RetinaScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 RetinaScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           }); 
		          
		          Checkbox fingerPrintCheckBox  = new Checkbox("Finger Print");    
		          fingerPrintCheckBox.setBounds(100, 100,  50, 50);   
		          fingerPrintCheckBox.setState(true);
		          fingerPrintCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 FingerPrintScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 FingerPrintScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           }); 
		          
		          Checkbox fingerVeinCheckBox = new Checkbox("Finger veins");    
		          fingerVeinCheckBox.setBounds(100, 100,  50, 50);   
		          fingerVeinCheckBox.setState(false);
		          fingerVeinCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 FingerVeinScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 FingerVeinScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });
		          
		          Checkbox palmVeinCheckBox  = new Checkbox("Palm veins");    
		          palmVeinCheckBox.setBounds(100, 100,  50, 50);   
		          palmVeinCheckBox.setState(false);
		          palmVeinCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 PalmVeinScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 PalmVeinScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });
		          
		          Checkbox palmPrintCheckBox = new Checkbox("Palm Print");    
		          palmPrintCheckBox.setBounds(100, 100,  50, 50);   
		          palmPrintCheckBox.setState(false);
		          palmPrintCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 PalmPrintScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 PalmPrintScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });
		          
		          Checkbox handGeomCheckBox = new Checkbox("Hand Geometry");    
		          handGeomCheckBox.setBounds(100, 100,  50, 50);   
		          handGeomCheckBox.setState(false);
		          handGeomCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 HandGeometryScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 HandGeometryScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });
		          
		          Checkbox DNACheckBox = new Checkbox("DNA");    
		          DNACheckBox.setBounds(100, 100,  50, 50);   
		          DNACheckBox.setState(false);
		          DNACheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 DNAScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 DNAScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });
		           
		          Checkbox DrivePassCheckBox  = new Checkbox("Passport");    
		          DrivePassCheckBox.setBounds(100, 100,  50, 50);   
		          DrivePassCheckBox.setState(false);
		          DrivePassCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 Drive_PassportScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 Drive_PassportScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });
		          
		          Checkbox OrdorCheckBox  = new Checkbox("Ordor/Smell");    
		          OrdorCheckBox.setBounds(100, 100,  50, 50);   
		          OrdorCheckBox.setState(false);
		          OrdorCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 OrdorScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 OrdorScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });
		          
		          Checkbox voiceCheckBox  = new Checkbox("Voice Biometrics");    
		          voiceCheckBox.setBounds(100, 100,  50, 50);   
		          voiceCheckBox.setState(false);
		          voiceCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 VoiceScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 VoiceScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });
		          
		          Checkbox SignatureCheckBox = new Checkbox("signature");    
		          SignatureCheckBox.setBounds(100, 100,  50, 50);   
		          SignatureCheckBox.setState(true);
		          SignatureCheckBox.addItemListener(new ItemListener() {    
		              public void itemStateChanged(ItemEvent e) {                 
		                 if(e.getStateChange()==1) {
		                	 SignatureScanImageBoundPanel.setVisible(true);
		                 }else{
		                	 SignatureScanImageBoundPanel.setVisible(false);
		                 }
		              }    
		           });
		            
CardBioInclude_menu_panel.add(FaceImageCheckBox); CardBioInclude_menu_panel.add(FaceRecogCheckBox); CardBioInclude_menu_panel.add(IrisScanCheckBox);
CardBioInclude_menu_panel.add(retinaScanCheckBox);CardBioInclude_menu_panel.add(fingerPrintCheckBox);CardBioInclude_menu_panel.add(fingerVeinCheckBox);
CardBioInclude_menu_panel.add(palmVeinCheckBox);CardBioInclude_menu_panel.add(palmPrintCheckBox);CardBioInclude_menu_panel.add(handGeomCheckBox);
CardBioInclude_menu_panel.add(DNACheckBox);CardBioInclude_menu_panel.add(DrivePassCheckBox);CardBioInclude_menu_panel.add(OrdorCheckBox);
CardBioInclude_menu_panel.add(voiceCheckBox);CardBioInclude_menu_panel.add(SignatureCheckBox);

CardBioInclude_menu_heading.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
 	   if(CardBioInclude_menu_panel.isVisible()) {
 		  CardBioInclude_menu_panel.setVisible(false);
 		  
 		   }
 	   else {
 		  CardBioInclude_menu_panel.setVisible(true);
 	   }
    }
});


final JButton CardShowSides=new JButton(new ImageIcon(new ImageIcon("data/images/icons/icon_bioInclude.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
CardShowSides.setFont(Font.decode("Arial-BOLD-14"));
CardShowSides.setForeground(new Color(0,0,0,255));
CardShowSides.setContentAreaFilled(false);
CardShowSides.setBorderPainted(false);
CardShowSides.setFocusPainted(false);
CardShowSides.setToolTipText("Sides");
//CardShowSides.setPreferredSize(new Dimension((int)(LButtdropXsize)-LButtHidropGHideOffset,LButtdropWidth));
CardShowSides.setVerticalTextPosition(SwingConstants.CENTER);
CardShowSides.setHorizontalTextPosition(SwingConstants.CENTER);
CardShowSides.setCursor(new Cursor(Cursor.HAND_CURSOR));
CardShowSides.setBorder(BorderFactory.createLineBorder(Color.blue,0));
CardShowSides.setLayout((new FlowLayout(FlowLayout.LEFT,0,0)));
CardShowSides.addMouseListener(new MouseAdapter() 
{
   public void mouseEntered(MouseEvent evt) 
   {
 	 // CardShowSides.setBackground(Color.ORANGE);
 	  //CardShowSides.setContentAreaFilled(true);
   }
   public void mouseExited(MouseEvent evt) 
   {
 	 // CardShowSides.setBackground(null);
 	 // CardShowSides.setContentAreaFilled(false);
   }
});

final JLabel CardShowSides_icon = new JLabel();

//CardShowSides_icon.setIcon(menu_dropped_icon);

//CardShowSides.add(CardShowSides_icon);

CardBioInclude_menu_heading_panel.add( CardShowSides);
CardBioInclude_menu_heading_panel.add(new JLabel(IDCardDesignToolsIconSpacing));




final JLabel CrdSideIcLabel = new JLabel();
CrdSideIcLabel.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
CrdSideIcLabel.setBorder(BorderFactory.createLineBorder(Color.green,0));
CrdSideIcLabel.setBackground(new Color(255,255,0,255));
CrdSideIcLabel.setPreferredSize(new Dimension(20,20));
CrdSideIcLabel.setPreferredSize(new Dimension(25,25));
ImageIcon SmallFolder1= new ImageIcon(new ImageIcon("data/images/icons/icon_cardSide.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)); 
CrdSideIcLabel.setIcon(SmallFolder1);

/*
UIManager.put("PopupMenu.border", new LineBorder(new Color(255,0,0,200)));
UIManager.put("MenuBar.background", Color.RED);
UIManager.put("Menu.background", Color.GREEN);
UIManager.put("MenuItem.background", Color.MAGENTA);
*/
JMenuBar menuBar = new JMenuBar();
menuBar.setPreferredSize(new Dimension(30,25));
menuBar.setBackground(Color.RED);
menuBar.setOpaque(false);
menuBar.setBorder(BorderFactory.createLineBorder(Color.green,0));

//JLabel CrdSideIcLabel = new JLabel();
 JMenu menu=new JMenu();
menu.setPreferredSize(new Dimension(30,25));
menu.setLayout((new FlowLayout(FlowLayout.LEFT,0,0 )));
menu.setIcon(SmallFolder1);
//menu.setBackground(Color.RED);
menu.setOpaque(false);
menu.setBorder(BorderFactory.createLineBorder(Color.green,0));

ButtonGroup group = new ButtonGroup();
JRadioButtonMenuItem Cupperside = new JRadioButtonMenuItem("Upper Side");
Cupperside.setSelected(true);
//Cupperside.setMnemonic(KeyEvent.VK_R);
Cupperside.setActionCommand("Cupperside");
group.add(Cupperside);
menu.add(Cupperside);
Cupperside.addActionListener(new ActionListener() {
		@Override
public void actionPerformed(ActionEvent e) {String Cside = e.getActionCommand();
IDCardUpperSideImageButtonLabel.setIcon(IDCardUpperSideImage);		     
}});


JRadioButtonMenuItem CBottomside = new JRadioButtonMenuItem("Bottom Side");
//CBottomside.setMnemonic(KeyEvent.VK_O);
group.add(CBottomside);
menu.add(CBottomside);
CBottomside.setActionCommand("CBottomside");
CBottomside.addActionListener(new ActionListener() {
	@Override
public void actionPerformed(ActionEvent e) {String Cside = e.getActionCommand();
IDCardUpperSideImageButtonLabel.setIcon(IDCardBottomSideImage);		     
}});


		// TODO Auto-generated method stub

menuBar.add(menu);
CrdSideIcLabel.add(menuBar);
//menu.addSeparator();

CardBioInclude_menu_heading_panel.add(menuBar);
//**********************************************************************************************************View
//JMenu view= new JMenu(new ImageIcon(new ImageIcon("data/images/icons/icon-fingerprint.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
//JMenu view= new JMenu("bio");
//***********************************************************************************************************end view 
          
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ End Card design tools

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(true);
		//this.setTitle("ACMIS CLOUD BIOMETRICS MANAGEMENT SYSTEM");
		String heading="ACMIS ELECTRONIC BIOMETRIC IDENTITY CARD PRINTING SYSTEM   [" + CandidateUniversity+"]";
		this.setFont(new Font("System", Font.BOLD, 14));
		Font f = this.getFont();
		FontMetrics fm = this.getFontMetrics(f);
		int px = fm.stringWidth(heading);
		int py = fm.stringWidth(" ");
		int pz = this.getWidth()/2 - (px/2);
		int w = pz/py;
		String pad ="";
		//for (int i=0; i!=w; i++) pad +=" "; 
		pad = String.format("%"+w+"s", pad); 
		this.setTitle(pad + heading.toUpperCase());
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("data/images/icons/ic_title1.png"));
		
		//resize frame
	  this.addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentResized(ComponentEvent e) {
	            	//JFrame frame = new JFrame();
	                //titleAlign(frame);
	
	            }
	        });
	}
	  
	    // this.pack();
	  
	  
	  

		

		
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
			String act = e.getActionCommand();
		    if (act.equals("Cupperside")){
		        System.out.println("Cupperside");
		    }else if(act.equals("CBottomside")){
		    	System.out.println("Cupperside");
		    }
		}
}




///************************************************************************* processing sketches

 class Processing extends PApplet{
	 //window settings
	
	 BiometricSystem jav= new BiometricSystem();
	
	 Processing(){
		 System.out.print("the java value is " + jav.jav);
	 }
	 
//**************************************************************** images
PImage heys;
	 
	 
	 
	public void settings(){
		size(500, 500);
	}
	
	public void setup() {
//****************************************************system settings window
surface.setAlwaysOnTop(true);
surface.setTitle("       ACMIS DIGITAL BIOMETRIC ID BURNING WINDOW");
surface.setResizable(true);
surface.setLocation(100, 100);
				
				
				
//***************************************************data loads
//#####################################################images
heys = loadImage("images/faces/trash/heys200.png");	
	}
	
	
	public void draw(){
		//ellipse(mouseX, mouseY, 50, 50);
		//textFont("normal");
		textSize(20); fill(255,0,0);
		text("  processing value is " + var, 20, 20);
		//text("  java value is " + new BiometricSystem().jav, 20, 60);
		
		image(heys,70,70);
		
		
	//*************************************sounds
		 /* Toolkit.getDefaultToolkit().beep();
		  println("beep");
		  delay(1000);
		 
		  Toolkit.getDefaultToolkit().beep();
		  println("beep");
		  delay(1000);
		 
		  println("one draw loop");*/
//************************************************ end sounds
	}
	

	public void mousePressed(){
		background(64);
	}
	
	int var= 20;
	
 }
