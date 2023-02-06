package com.zkteco.biometric;

import javax. swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

class LoginPage extends JFrame{
	
	private static  String OldAcmisLoginEndPoint;
	private static  String OldAcmisRequestDataEndPoint;
	int windowWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
	int windowHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	 //initialize button, panel, label, and text field  
    JButton buttonSubmit;  
    JPanel newPanel, ErrorPannel, headingPannel, settingsPanel;  
    JLabel usernameLabel, passLabel, ErrorLabel=new JLabel(), HeadingLabel, settingslabel;  
    JTextField  userNameField, passField;  
   // String token;
   Object token, userProfile;
   Object address,surname,other_names;
    int errorcodeInt;
    
    String SubjectEntity = "makerere";
    
    String loginEndPoint, requestDataEndPoint;
    
    //https://staff.staging.acmis.ac.ug/api/v1/users/auth/login
     //https://staff.staging.acmis.ac.ug/api/v1/users/auth/profile
    
    String AcmisLoginEndPoint;
    String AcmisRequestDataEndPoint;
    
    String MakerereLoginEndPoint;
    String MakerereRequestDataEndPoint;
    
    String KyambogoLoginEndPoint;
    String KyambogoRequestDataEndPoint;
    
    String GuluLoginEndPoint;
    String GuluRequestDataEndPoint;
    
     String urlForName;
     String UrlFileLoc = "data/PrivateLibrary/";
     String UrlFileExt = ".txt";
    
    
      
    final JFrame frame = new JFrame("ACMIS LOGIN     [ "+SubjectEntity.toUpperCase()+" ]");
              
    
	LoginPage(){
		
		 frame.setIconImage(Toolkit.getDefaultToolkit().getImage("data/images/icons/ic_title1.png"));
    /*    setTitle("ACMIS LOGIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        //setting the bounds for the JFrame
        setBounds(0,0,windowWidth,windowHeight);
        Container c=getContentPane();
    */
        
	    //final ImageIcon icon = new ImageIcon("E:\\Java-workspace\\Bio Identity\\data\\images\\backgrounds\\bg1.jpg");
	    final ImageIcon icon =new ImageIcon(new ImageIcon("data/images/backgrounds/bg1.jpg").getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_DEFAULT));
	    JTextArea bgImage = new JTextArea() 
	    {
	      Image img = icon.getImage();
	      // instance initializer
	      {setOpaque(false);}
	      public void paintComponent(Graphics graphics) 
	      {
	        graphics.drawImage(img, 0, 0, this);
	        super.paintComponent(graphics);
	      }
	    };
	    
	    int PanelWidth = 500;
	    int PanelHeight =100;
	    
	    int ErrorPanelWidth = 300, HeadingPanelWidth=500;
	    int ErrorPanelHeight =50, HeadingPanelHeight = 50;
	    int gap=50;
	   
	    
	// check which entity this softaere is meant for
	    switch(SubjectEntity){
        case "acmis":urlForName = "acmisURL";
        break;
        case "makerere":urlForName = "makerereURL";
        break;
        case "kyambogo":urlForName = "kyambogoURL";
        break;
        case "gulu":urlForName = "guluURL";
        break;
        default:
        	System.out.println("Unknown host"); 
        	//System.exit(-1);
	    }
       
        
      //create label for username   
        usernameLabel = new JLabel();  
        usernameLabel.setText("Username");      //set label value for textField1  
        userNameField = new JTextField(15);    //set length of the text  
        //create label for password  
        passLabel = new JLabel();  
        passLabel.setText("Password");      //set label value for textField2  
        passField = new JPasswordField(15);    //set length for the password  
        //create submit button  
        buttonSubmit = new JButton("SUBMIT"); //set label to button  
        
          
        //create panel to put form elements  
        newPanel = new JPanel(new GridLayout(3, 1));  
        newPanel.setBackground(Color.white);
        newPanel.setBounds(windowWidth/2-PanelWidth/2,windowHeight/2-PanelHeight/2,PanelWidth,PanelHeight); 
        newPanel.add(usernameLabel);    //set username label to panel  
        newPanel.add(userNameField);   //set text field to panel  
        newPanel.add(passLabel);    //set password label to panel  
        newPanel.add(passField);   //set text field to panel  
        newPanel.add(buttonSubmit);           //set button to panel  
          
        //set border to panel   
        //add(newPanel, BorderLayout.CENTER);  
        ErrorPannel = new JPanel();  
        ErrorPannel.setBackground(Color.white);
        ErrorPannel.setBounds(windowWidth/2-ErrorPanelWidth/2,windowHeight/2+PanelHeight-20,ErrorPanelWidth,ErrorPanelHeight); 
        ErrorPannel.setVisible(false);
        
        
        
        frame.getContentPane().add(newPanel);
        frame.getContentPane().add(ErrorPannel);
        
        
        headingPannel = new JPanel();  
        headingPannel.setBackground(Color.white);
        headingPannel.setBounds(windowWidth/2-HeadingPanelWidth/2,0,HeadingPanelWidth,HeadingPanelHeight); 
        headingPannel.setVisible(true);
        headingPannel.setOpaque(false);
        HeadingLabel = new JLabel();
        HeadingLabel.setFont(new Font("System", Font.BOLD, 20));
        HeadingLabel.setText("ACMIS ELECTRONIC IDENTITY CARDS PRINTING");      //set label value for textField1  
        HeadingLabel.setLayout(new GridBagLayout());
        HeadingLabel.setVisible(true);
       
        headingPannel.add(HeadingLabel);
        frame.getContentPane().add(headingPannel);
        
        
        
        settingsPanel = new JPanel();  
        settingsPanel.setBackground(Color.white);
        settingsPanel.setBounds(20,20,100,100); 
        settingsPanel.setVisible(true);
        settingsPanel.setOpaque(false);
        settingslabel = new JLabel();
        settingslabel.setFont(new Font("System", Font.BOLD, 20));
        settingslabel.setIcon(new ImageIcon(new ImageIcon("data/images/icons/ic_login_settings1.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        settingslabel.setToolTipText("Settings");
        settingslabel.setLayout(new GridBagLayout());
        settingslabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingslabel.setVisible(true);
        
        settingsPanel.add(settingslabel);
        frame.getContentPane().add(settingsPanel);
        
 
        
        
        
     
           
        try {
            OldAcmisLoginEndPoint = Files.readAllLines(Paths.get(UrlFileLoc+urlForName+UrlFileExt)).get(0).trim();
            OldAcmisRequestDataEndPoint = Files.readAllLines(Paths.get(UrlFileLoc+urlForName+UrlFileExt)).get(1).trim();
           } catch (IOException e) { e.printStackTrace(); System.out.println (SubjectEntity+" URL files not found or corrupt!");}
            
         
        
        JLabel newEndPointReqURLLabel = new JLabel();  
        newEndPointReqURLLabel.setText("Token Request URL");     
        final JTextField newEndPointReqURLField = new JTextField(OldAcmisLoginEndPoint);   
        newEndPointReqURLField.setBackground(Color.orange);
        newEndPointReqURLField.setForeground(Color.black);
        //create label for password  
        JLabel newEndPointReceiveURLLabel = new JLabel();  
        newEndPointReceiveURLLabel.setText("Token Verification URL");      //set label value for textField2  
        final JTextField  newEndPointReceiveURLField= new JTextField(OldAcmisRequestDataEndPoint);    // end point verification url
        newEndPointReceiveURLField.setBackground(Color.orange);
        newEndPointReceiveURLField.setForeground(Color.black);
        //create submit button  
        JButton newURLSubmitBtn = new JButton("SAVE URLS"); //set label to button  
        
          
        //create panel to put new url elements  
        final JPanel newUrlPanel = new JPanel(new GridLayout(3, 1));  
        newUrlPanel.setBackground(Color.white);
        newUrlPanel.setForeground(Color.black);
        //newUrlPanel.setBounds(windowWidth/2-PanelWidth/2,windowHeight/2-PanelHeight/2,PanelWidth,PanelHeight); 
        newUrlPanel.setBounds(windowWidth/2-PanelWidth/2,100,PanelWidth,PanelHeight); 
        newUrlPanel.add(newEndPointReqURLLabel);    //set username label to panel  
        newUrlPanel.add(newEndPointReqURLField);   //set text field to panel  
        newUrlPanel.add(newEndPointReceiveURLLabel);    //set password label to panel  
        newUrlPanel.add(newEndPointReceiveURLField);   //set text field to panel  
        newUrlPanel.add(newURLSubmitBtn);           //set button to panel  
        newUrlPanel.setVisible(false);
          
        frame.getContentPane().add(newUrlPanel);
        
        
        settingslabel.addMouseListener(new MouseAdapter() 
        {
            public void mouseEntered(MouseEvent evt) {} 
            public void mouseExited(MouseEvent evt) {} 
            public void mouseClicked(MouseEvent evt) {
            	   if(newUrlPanel.isVisible()) {
            		   newUrlPanel.setVisible(false);
            		   }
            	   else {
            		   newUrlPanel.setVisible(true);
            	   }
           } 
            
         });
        
      
       
	    
	    JScrollPane pane = new JScrollPane(bgImage);
	    Container content = frame.getContentPane();
	    content.add(pane, BorderLayout.CENTER);
	    frame.setDefaultCloseOperation(3);
        frame.setSize(windowWidth, windowHeight);
        //frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setVisible(true);
        
        newURLSubmitBtn.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
            {  
              String newURLTokenRequest = newEndPointReqURLField.getText();        //get user entered username from the textField1  
              String newURLTokenVerify =  newEndPointReceiveURLField.getText();        //get user entered pasword from the textField2 
                 
              try {
                       FileWriter writer = new FileWriter(UrlFileLoc+urlForName+UrlFileExt, false);
                       writer.write(newURLTokenRequest);writer.write("\r\n"); writer.write(newURLTokenVerify);
                       writer.close();
                   } catch (IOException e) { e.printStackTrace();} 
              }
        });   
   
        
        
      //define abstract method actionPerformed() which will be called on button click 
       buttonSubmit.addActionListener(new ActionListener(){ 
       public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
       {  
         String userValue = userNameField.getText();        //get user entered username from the textField1  
         String passValue = passField.getText();        //get user entered pasword from the textField2 
         //support@acmis.ac.ug//guKomm!t22
        
               try {
                   AcmisLoginEndPoint = Files.readAllLines(Paths.get(UrlFileLoc+urlForName+UrlFileExt)).get(0).trim();
                   AcmisRequestDataEndPoint = Files.readAllLines(Paths.get(UrlFileLoc+urlForName+UrlFileExt)).get(1).trim();
               } catch (IOException e) { e.printStackTrace(); System.out.println ("corrupted URL files!");}
                  loginEndPoint=AcmisLoginEndPoint;
        	      requestDataEndPoint=AcmisRequestDataEndPoint;
         
         
         
          
         URL loginUrl ;
           
		try {	
			
			loginUrl = new URL (loginEndPoint);
			HttpURLConnection httpURLConnection = (HttpURLConnection)loginUrl.openConnection();
			
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			
			httpURLConnection.setRequestProperty("Accept", "application/json");
			
			httpURLConnection.setDoOutput(true);
			
			
			
			JSONObject userObj = new JSONObject();
			
			
			
			userObj.put("username", userValue);
			userObj.put("password", passValue);
			
	
			//System.out.println(userObj.toString());
			
			 try (OutputStream outputStream = httpURLConnection.getOutputStream()) { 
				   outputStream.write(userObj.toString().getBytes());
				   outputStream.flush();
				   //httpURLConnection.disconnect();
				  }
			 
				  if ( httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
				      String line;
				      StringBuilder response = new StringBuilder();
				      while ((line = bufferedReader.readLine()) != null) {
				    	  response.append(line.trim());
				    	//  System.out.println(response.toString());
				    	  
				    	  
				    	  //get candidate info from message
				    	  
				    	  String WholeResponse = response.toString();
				    	  
				    	  JSONObject jsonRes = new JSONObject(WholeResponse);
				    	   token = jsonRes.getJSONObject("access_token").getString("token");
				    	  
				    	//  System.out.print(token);
				    	  

				    	  // end get candidate Message
				    	  
				    	
				      }
				    }
				    
				 // Get user
			     URL urlGet = new URL(AcmisRequestDataEndPoint);
			     HttpURLConnection conne = (HttpURLConnection)urlGet.openConnection();
			     conne.setRequestProperty("Accept", "application/json");
			     conne.setRequestMethod("GET");
			     conne.setRequestProperty("Authorization", "Bearer "+token);
				    try (BufferedReader DataReader = new BufferedReader(new InputStreamReader(conne.getInputStream()))) {
					      String profile;
					      StringBuilder responseData = new StringBuilder();
					      while ((profile = DataReader.readLine()) != null) {
					    	  responseData.append(profile.trim());
					    	  System.out.println(responseData.toString());
					    	  
					    	  
					    	  //get candidate info from message
					    	  
					    	  String WholeDataResponse = responseData.toString();
					    	  
					    	  JSONObject jsonResData = new JSONObject(WholeDataResponse);
					    	  
//					    	  Object user = jsonResData.getJSONObject("user");
					    	   address = jsonResData.getJSONObject("user").get("address"); 
					    	   surname = jsonResData.getJSONObject("user").get("surname"); 
					    	   other_names = jsonResData.getJSONObject("user").get("other_names");
					    	 // Object role_code = jsonResData.getJSONObject("roles").get("role_code");
					    	//  Object role_title = jsonResData.getJSONObject("roles").get("role_title");
					    	  
					    	  System.out.print(address + " " + surname + " " + other_names); 
					    	  
					      }
					    }
          	
			    	  conne.disconnect();
			    	 // end get candidate
			    	  
			    	  JumptoWorkSpace();
				  } 
				  else {
					  System.out.println(httpURLConnection.getResponseCode()+" : "+httpURLConnection.getResponseMessage());
					   errorcodeInt=httpURLConnection.getResponseCode();
					   
					   if(errorcodeInt <0) {
					  switch(errorcodeInt){
					  case 400: System.out.println("Invalid username or Password");
					  break;
					  default:System.out.println("Login failed. Please contact admin");
					  }
					  
					   }
					  
					  Reader ErrorStream = new InputStreamReader(httpURLConnection.getErrorStream(), StandardCharsets.UTF_8);
					  if(ErrorStream.toString() != null) {
					 // System.out.println(ErrorStream.toString());
					  }
					  else {
						//  System.out.println("ErrorStream is null");
					  }
					  
					  httpURLConnection.disconnect();
					  JumptoFailedLogin();
					  
				  }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			String erroMessage = e.getMessage();
			//System.out.println(erroMessage);
			System.out.println("Invalid Grid URL");

		} 
		
		
		catch (IOException e) {
			
			String erroMessage = e.getMessage();
			//System.out.println(erroMessage);
			// TODO Auto-generated catch block
			jumpToNoInternet();
			
			e.printStackTrace();
			
		}
           
		
		
		
           //String jsonInputString = "{"name": "Upendra", "job": "Programmer"}";
           
           
           //check whether the credentials are authentic or not  
           if (userValue.equals("admin") && passValue.equals("admin")) {  //if authentic, navigate user to a new page  
             
        	  // do nothing
           } 
           
           
           
           
       }

	private String getLoginToken() {
		// TODO Auto-generated method stub
		
		
		
		
		
		return null;
	}
       
       
        });
        
        
        
    }
	
	private void JumptoWorkSpace() {
		// ErrorLabel = new JLabel(); 
         ErrorLabel.setIcon (new ImageIcon(new ImageIcon("data\\images\\icons\\ic_Sign_savSucc.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
         ErrorLabel.setText("Login successful");      //set label value for textField1  
         ErrorPannel.setLayout(new GridBagLayout());
         ErrorPannel.add(ErrorLabel);
         ErrorPannel.setVisible(true);
         
        // address,surname, other_names
         BiometricSystem.Surname=surname.toString();
         BiometricSystem.OtherNames=other_names.toString();
         BiometricSystem.Address=address.toString();
         
         //create a welcome label and set it to the new page  
        /* JLabel wel_label = new JLabel("Welcome: "+userValue);  
         page.getContentPane().add(wel_label); */
         
          new BiometricSystem().launchFrame();
       /* String[] processingArgs = {"Processing"};
			 Processing graphicsEditor = new Processing();
			 PApplet.runSketch(processingArgs, graphicsEditor);*/
         
        
         frame.setVisible(false); //close login window
  	   
     }  
	
	private void JumptoFailedLogin() {

         //show error message  
       //  System.out.println("Please enter valid username and password");  
         
       //create label for username   
        // ErrorLabel = new JLabel(); 
         ErrorLabel.setIcon (new ImageIcon(new ImageIcon("data\\images\\icons\\ic_login_error.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
         ErrorLabel.setText("Invalid User Name Or Password");      //set label value for textField1  
         ErrorPannel.setLayout(new GridBagLayout());
         ErrorPannel.setVisible(true);
         ErrorPannel.add(ErrorLabel);
    }
	
	 private void jumpToNoInternet() {
		 System.out.println("Internet Not Connected"); 
		// ErrorLabel = new JLabel(); 
         ErrorLabel.setIcon (new ImageIcon(new ImageIcon("data\\images\\icons\\ic_network_error.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
         ErrorLabel.setForeground(Color.red);
         ErrorLabel.setText("No internet connection !");      
         ErrorPannel.setLayout(new GridBagLayout());
         ErrorPannel.setVisible(true);
         ErrorPannel.add(ErrorLabel);
	 }
      
	
	
    
	
	
	
    public static void main(String[] args)throws WriterException, IOException, NotFoundException {
       // new LoginPage();
        //LoginPage form = new LoginPage();  
         //form.setVisible(true);
        
        new BiometricSystem().launchFrame();
    	//new ProfilePic();
    	//new Codec().writeQRCode();
    	//new FingerPrintScanner().launch();
    	//new AcmisTopazSignature();

    }
}











