package httpclient.access;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.Style;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * The host GUI for the HTTPAccess features
 * @author rNatarajan
 *
 */
@SuppressWarnings("serial")
public class HttpClientGUI extends JPanel implements ActionListener {
	
	/**
	 * The host frame of the signup GUI
	 */
	protected JFrame frame;
	
	/**
	 * The format of the GUI
	 */
	protected Style style;
	
	/**
	 * The button pressed to register users
	 */
	protected Button submit;
	
	protected JPasswordField pass;
	protected JTextField user;
	protected JTextField email;
	
	protected JLabel userLabel;
	protected JLabel passLabel;
	protected JLabel emailLabel;
	protected JLabel complete;
	
	/**
	 * Grants access to the database features
	 */
	protected HttpClientAccess hCL;
	
	/**
	 * Creates a list of the usernames and passwords
	 */
	protected static String[] input = new String[2];
	
	/**
	 * Constructs the buttons and the constraints of the frame
	 * in a grid format
	 */
	public HttpClientGUI(){
		super(new GridBagLayout());

		submit = new Button("Submit");
		submit.setFont(new java.awt.Font("Calibri",Font.BOLD, 35));
		submit.addActionListener(this);
		submit.setActionCommand("Enter");
		
		
		
        
        pass = new JPasswordField(25);
        pass.setFont(new java.awt.Font("Calibri",Font.PLAIN, 25));
        
        user = new JTextField(25);
        user.setFont(new java.awt.Font("Calibri",Font.PLAIN, 25));
        
        email = new JTextField(25);
        email.setFont(new java.awt.Font("Calibri",Font.PLAIN, 25));
        
      
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        
        setLabels();
                
        c.fill = GridBagConstraints.HORIZONTAL;

        add(userLabel, c);
        add(user, c);
        add(passLabel, c);
        add(pass, c);
        add(emailLabel, c);
        add(email, c);

        add(complete, c);
        add(submit, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
	}
	/**
	 * Creates the labels for the frame
	 */
	protected void setLabels()
	{        

        passLabel = new JLabel("Password:");
		userLabel = new JLabel("Username:");
		emailLabel = new JLabel("Email:");

		passLabel.setForeground(Color.BLACK);
		userLabel.setForeground(Color.BLACK);
		emailLabel.setForeground(Color.BLACK);

		
		complete = new JLabel("Press 'Submit' when finished.");
		complete.setForeground(Color.BLACK);
		
	}
	/**
	 * Resets the values of the labels on the frame
	 */
	protected void resetLabels()
	{        

        passLabel.setText("Password:");
		userLabel.setText("Username:");
		emailLabel.setText("Email:");
 
		passLabel.setForeground(Color.BLACK);
		userLabel.setForeground(Color.BLACK);

		
		complete.setText("Your response has been recorded.");
		complete.setForeground(Color.GREEN);
		
	}
	
	/**
	 * gets the type of the input for future reference
	 * @param type the value (username = 0, password = 1, email = 2)
	 * @return the value of the input at that type
	 */
	public String inValue(int type)
	{
		return input[type];
	}
	
	/**
	 * loads the GUI
	 * @param program the frame name
	 */
	protected void load(String program)
	{
		for( int f=0; f<2; f++ )
		{
			input[f] = "";
		}
        frame = new JFrame(program);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new HttpClientGUI());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(700,500);
        frame.getContentPane().setBackground(Color.WHITE);
    }
	
	
	/**
	 * Adds button functionality
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		String action = event.getActionCommand();
		if( action.equals("Enter") )
		{

			char[] getPass;
			getPass = pass.getPassword();
			String getPassString = "";
			for( int f=0; f<getPass.length; f++ )
			{
				getPassString += getPass[f];
			}
			String getUser = user.getText();
			String getEmail = email.getText();

	    
			hCL = new HttpClientAccess();
			hCL.passCheck(getPassString);
			hCL.signup(getUser, getPassString, getEmail);
				
			input[0] = getEmail;
			email.setText("");
			input[1] = getPassString;
			pass.setText("");

			remove(submit);
			resetLabels();
				
		
			
				
				
				
		}
		else if( action.equals("Terminate") )
		{
			frame.dispose();
		}
	}
	
	/**
	 * Runs the GUI
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClientGUI hTG = new HttpClientGUI();
		hTG.load("Test");
	}
	
}
