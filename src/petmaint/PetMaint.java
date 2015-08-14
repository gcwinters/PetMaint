/*****************************************************************************/
/*                                                                           */
/* GUI lesson                                                                */
/* PetMaint.java                                                             */
/*                                                                           */
/*****************************************************************************/

/*
 * PetMaint.java
 */

package petmaint;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
// import java.io.FileInputStream;
// import java.io.File;

public class PetMaint extends JFrame {
	static final long serialVersionUID = 0;

	ConnectMenagerie petcon;
	Boolean testFlag = false;
	
	JPanel panelForFields  = null;
	JPanel panelForButtons = null;
	JPanel panelForPicture = null;
	
	FlowLayout layoutFrame  = null;
	GridLayout layoutField  = null;
        FlowLayout layoutButton = null;
		
	JLabel labelPetName    = null;
	JLabel labelPetOwner   = null;
	JLabel labelPetSpecies = null;
	JLabel labelPetSex     = null;
	JLabel labelPetBirth   = null;
	JLabel labelPetDeath   = null;
	JLabel labelPicLabel   = null;
	JLabel labelPicture    = null;
	
	JTextField textPetName    = null;
	JTextField textPetOwner   = null;
	JTextField textPetSpecies = null;
	JTextField textPetSex     = null;
	JTextField textPetBirth   = null;
	JTextField textPetDeath   = null;
	
	JButton buttonConnect    = null;
	JButton buttonQuery      = null;
	JButton buttonNext       = null;
	JButton buttonPrev       = null;
	JButton buttonUpdate     = null;
	JButton buttonDisconnect = null;
	JButton buttonClear      = null;
	
	ResultSet rs             = null;
	ImageIcon image          = null;
	
	public PetMaint() {
		super();
		
		// Instantiate instance objects:
		panelForFields  = new JPanel();
		panelForButtons = new JPanel();
		panelForPicture = new JPanel();
		
		layoutFrame  = new FlowLayout();
		layoutField  = new GridLayout(3,3); // was 3,3
		layoutButton = new FlowLayout();
		
		labelPetName    = new JLabel("Name: ",   JLabel.RIGHT);
		labelPetOwner   = new JLabel("Owner: ",  JLabel.RIGHT);
		labelPetSpecies = new JLabel("Species: ",JLabel.RIGHT);
		labelPetSex     = new JLabel("Sex: ",    JLabel.RIGHT);
		labelPetBirth   = new JLabel("Birth: ",  JLabel.RIGHT);
		labelPetDeath   = new JLabel("Death: ",  JLabel.RIGHT);
		labelPicLabel   = new JLabel("Photo:",   JLabel.RIGHT);
    
	//	image = new ImageIcon("/home/gwinters/Desktop/Images/cat.jpg");
	//	labelPicture = new JLabel("", image, JLabel.RIGHT);
		
		labelPicture = new JLabel();
		
		textPetName    = new JTextField(7);
		textPetOwner   = new JTextField(7);
		textPetSpecies = new JTextField(7);
		textPetSex     = new JTextField(7);
		textPetBirth   = new JTextField(7);
		textPetDeath   = new JTextField(7);
		
		// Provide some Tool Tips and Mnemonics.
		textPetName.setToolTipText("Pet's name.");
		textPetOwner.setToolTipText("Pet's owner's name.");
		textPetSpecies.setToolTipText("Pet's species.");
		textPetSex.setToolTipText("Pet's sex.");
		textPetBirth.setToolTipText("Pet's date of birth.");
		textPetDeath.setToolTipText("Pet's date of death.");
		
		buttonConnect = new JButton("Connect");
		buttonConnect.setToolTipText("Connect to MySQL database MENAGERIE.");
		buttonConnect.setMnemonic(KeyEvent.VK_T);
	        buttonConnect.setEnabled(true);  // Initialize to Active
		
		buttonQuery = new JButton("Find");
		buttonQuery.setToolTipText(
			"Perform a test SELECT from MySQL database MENAGERIE, table PET.");
		buttonQuery.setMnemonic(KeyEvent.VK_F);
		buttonQuery.setEnabled(false);  // Initialize to Greyed-out
		
		buttonPrev = new JButton("Previous");
		buttonPrev.setToolTipText(
			"Display previous record (row) from MySQL database MENAGERIE, table PET.");
		buttonPrev.setMnemonic(KeyEvent.VK_P);
		buttonPrev.setEnabled(false);  // Initialize to Greyed-out
		
		buttonNext = new JButton("Next");
		buttonNext.setToolTipText(
			"Display next record (row) from MySQL database MENAGERIE, table PET.");
		buttonNext.setMnemonic(KeyEvent.VK_N);
		// buttonNext.setMnemonic(KeyEvent.VK_PAGE_DOWN);
		buttonNext.setEnabled(false);  // Initialize to Greyed-out
		
		buttonUpdate = new JButton("Update");
		buttonUpdate.setToolTipText(
			"Perform an update to MySQL database MENAGERIE, table PET.");
		buttonUpdate.setMnemonic(KeyEvent.VK_U);
		// buttonUpdate.setMnemonic(KeyEvent.VK_F2);
		buttonUpdate.setEnabled(false);  // Initialize to Greyed-out
		
		buttonDisconnect = new JButton("Disconnect");
		buttonDisconnect.setToolTipText("Disconnect from MySQL database MENAGERIE.");
		buttonDisconnect.setMnemonic(KeyEvent.VK_D);
		buttonDisconnect.setEnabled(false);  // Initialize to Greyed-out
		
		buttonClear = new JButton("Clear");
		buttonClear.setToolTipText("Blank out fields on form.");
		buttonClear.setMnemonic(KeyEvent.VK_C);
		buttonClear.setEnabled(true);  // Initialize to Active
		
		// Set layout managers for each panel and for this frame.
		panelForFields.setLayout(layoutField);
		panelForButtons.setLayout(layoutButton);
		this.getContentPane().setLayout(layoutFrame);
		
		// Add components to panelField;
		panelForFields.add(labelPetName);
		panelForFields.add(textPetName);
		
		panelForFields.add(labelPetOwner);
		panelForFields.add(textPetOwner);
		
		panelForFields.add(labelPetSpecies);
		panelForFields.add(textPetSpecies);
		
		panelForFields.add(labelPetSex);
		panelForFields.add(textPetSex);
		
		panelForFields.add(labelPetBirth);
		panelForFields.add(textPetBirth);
		
		panelForFields.add(labelPetDeath);
		panelForFields.add(textPetDeath);
		
		// Key listeners:
		
		// Form
/*** not working, put on buttons instead, ok uncomment try again */
		
		this.addKeyListener(new KeyListener () {
			public void keyPressed(KeyEvent ke) {
				System.out.println("Pressed a key.");
				if(ke.getKeyCode() == KeyEvent.VK_F1) {
					buttonQueryAction();
				}
				
				if(ke.getKeyCode() == KeyEvent.VK_PAGE_UP) {
					buttonPrevAction();
				}
				
				if(ke.getKeyCode() == KeyEvent.VK_F2) {
					buttonUpdateAction();
				}
				
				if(ke.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
					buttonNextAction();
				}
			}
			
			public void keyTyped(KeyEvent ke) {
			}
			
			public void keyReleased(KeyEvent ke) {
				
			}
		});
		/***/
 	 
		// Pet Name field
		textPetName.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
					buttonQueryAction();
				}
			}
			
			public void keyTyped(KeyEvent ke) {
			}
			
			public void keyReleased(KeyEvent ke) {
			}
		});
		
		// Connect button
		buttonConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonConnectAction(e);
			}
		});
		panelForButtons.add(buttonConnect);
		
		// Find button
		buttonQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonQueryAction();
			}
		});
		/** comment out so will try this at form level
		buttonQuery.addKeyListener(new KeyListener () {
			public void keyPressed(KeyEvent ke) {
				int whichKey = ke.getKeyCode();
		      
				switch(whichKey) {
				    case KeyEvent.VK_F1:
				    	buttonQueryAction();
				    	break;
				    case KeyEvent.VK_PAGE_UP:
				    case KeyEvent.VK_F9:
				    	buttonPrevAction();
				    	break;
				    case KeyEvent.VK_PAGE_DOWN:
				    case KeyEvent.VK_F10:
				    	buttonNextAction();
				    	break;
				    case KeyEvent.VK_F2:
				    	buttonUpdateAction();
				    	break;
				}
			}
			
			public void keyTyped(KeyEvent ke) {
			}
			
			public void keyReleased(KeyEvent ke) {
				
			}
		});
		**/
		panelForButtons.add(buttonQuery);
		
		// Prev button
		buttonPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPrevAction();
			}
		});
		panelForButtons.add(buttonPrev);	
		
		// Next button
		buttonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonNextAction();
			}
		});
		panelForButtons.add(buttonNext);
		
        // Update button
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonUpdateAction();
			}
		});
		panelForButtons.add(buttonUpdate);
		
		// Disconnect button
		buttonDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDisconnectAction();
			}
		});
		panelForButtons.add(buttonDisconnect);
		
        // Clear button
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClearAction();
			}
		});
		panelForButtons.add(buttonClear);
		
		panelForPicture.add(labelPicLabel);
		panelForPicture.add(labelPicture); 
		
		// Add two panels to this frame
		this.getContentPane().add(panelForFields);
		this.getContentPane().add(panelForButtons);
		this.getContentPane().add(panelForPicture);
		
		// Set the size for this frame
		this.setSize(600,280);
	}
	
	public void buttonConnectAction(ActionEvent e) {
		try {
	        petcon = new ConnectMenagerie();
		}
		
		catch (Exception connex) {
			System.out.println("Connection to Menagerie database failed.");
			}
		
		buttonConnect.setEnabled(false);
		buttonQuery.setEnabled(true);
		buttonDisconnect.setEnabled(true);
	}
	
	public void buttonQueryAction() {
		 try {
	         Statement s = petcon.conn.createStatement();
	         String whichName = this.textPetName.getText();
	         s.executeQuery("SELECT * FROM pet");
	         rs = s.getResultSet();
	     
	         while(rs.next()) {
	             String petName  = rs.getString("name");
	             
	             if(petName.equals(whichName) != true) {
	            	 continue;
	             }
	            
	             FillFields(rs);
	             break;
	         }
	         
	        // rs.close();
	        // s.close();
	       
	        buttonNext.setEnabled(true);
	        buttonPrev.setEnabled(true);
	        buttonUpdate.setEnabled(true);
		 }
		 
		 catch (Exception y) {
			 System.out.println("SELECT Exception: "+y.toString());
		 }
		 
        try {
        	// Look ahead (forward)
         	rs.next();
        	String whichName = rs.getString("name");
         	System.out.println("next record, name is "+whichName);
         	buttonNext.setEnabled(true);
         	buttonPrev.setEnabled(true);
        }
        
        catch (Exception testex) {
        	System.out.println("error on QUERY rs.next test: "+testex.toString());
        	buttonNext.setEnabled(false);
        }
      
        // If made it to here, then we successufully moved forward to
        // see if there was a next record. Now that we know, go back
        // to the record we're really on.
      
        try {
        	rs.previous(); 
        }
        
        catch(Exception goBack) {
        	System.out.println("error on NEXT prev test: "+goBack.toString());
        }
	} // End buttonQueryAction
	
	public void buttonNextAction() {	
	    try {
	        if(rs.next()) {
	        	FillFields(rs);
                buttonPrev.setEnabled(true);
	        }
	    }
	    
	    catch(Exception nextEx) {
	    	System.out.println("Exception on NEXT: "+nextEx.toString());
	    }
	    
	    try {
        	rs.next();
        	String whichName = rs.getString("name");
        	System.out.println("next record, name is "+whichName);
        	buttonNext.setEnabled(true);
        }
        
        catch (Exception testex) {
         	System.out.println("error on NEXT rs.next test: "+testex.toString());
        	buttonNext.setEnabled(false);
        	return;
        }
        
        // If made it to here, then we successufully moved forward to
        // see if there was a next record. Now that we know, go back
        // to the record we're really on.
        
        try {
        	rs.previous(); 
        }
        
        catch(Exception goBack) {
        	System.out.println("error on NEXT prev test: "+goBack.toString());
        }
	} // End buttonNextAction
	
	public void buttonPrevAction() {	
        try {
		    if(rs.previous()) {
		    	FillFields(rs);
	            buttonNext.setEnabled(true);
		        }
		    }
		    
        catch(Exception prevEx) {
        	System.out.println("Exception on PREV: "+prevEx.toString());
        }

        try {
        	rs.previous();
        	String whichName = rs.getString("name");
        	System.out.println("previous record, name is "+whichName);
        	buttonPrev.setEnabled(true);
        }

        catch (Exception testex) {
        	System.out.println("error on PREV rs.previous test: "+testex.toString());
        	buttonPrev.setEnabled(false);
        	return;
        }

        // If made it to here, then we successufully moved backward to
        // see if there was a previous record. Now that we know, go back
        // to the record we're really on.

        try {
        	rs.next(); 
        }

        catch(Exception goBack) {
        	System.out.println("error on PREV next test: "+goBack.toString());
        }
	} // End buttonPrevAction
	
	public void buttonUpdateAction() {
	    System.out.println("UPDATE button pressed. Stub only, sorry.");
	    
	    /**  HACK TO TEST FUNTIONALITY, not ready for prime time.
	    try {
	    Statement ns = petcon.conn.createStatement();
        
        String newName = "'ed'";
        String whichOwner = "'julie'";
        
        ns.executeUpdate("UPDATE pet SET pet.owner="+newName+" WHERE pet.owner="+whichOwner);
	    }
	    
	    catch (Exception updEx){
	    	System.out.println("Update error: "+updEx.toString());
	    }
	    **/
	    
	    /*** HACK TO INITIALLY LOAD UP PHOTO'S INTO DATABASE
	    try {
	    	PreparedStatement s;
	    	FileInputStream fis = null;
	    	File graphicsFile = new File("/home/gwinters/Desktop/Images/gerbil.jpg");
	    	fis = new FileInputStream(graphicsFile);
	    	
	    	s = petcon.conn.prepareStatement(
	    			"UPDATE pet SET pet.photo=? WHERE pet.species=?");
	    	
	    	s.setBinaryStream(1, fis, (int) graphicsFile.length());
	    	s.setString(2,"gerbil");
	    	s.executeUpdate();
	    	
	    	System.out.println("Put a test image on the Greg record.");
	    }
	    
	    catch (Exception updEx) {
	    	System.out.println("Update error: "+updEx.toString());
	    }
	    **/
	}
		
	public void buttonDisconnectAction() {
		petcon.DisconnectMenagerie();
		
		buttonConnect.setEnabled(true);
		buttonQuery.setEnabled(false);
		buttonNext.setEnabled(false);
		buttonPrev.setEnabled(false);
		buttonUpdate.setEnabled(false);
		buttonDisconnect.setEnabled(false);
	}
	
	public void buttonClearAction() {
		textPetName.setText("");
        textPetOwner.setText("");
        textPetSpecies.setText("");
        textPetSex.setText("");
        textPetBirth.setText("");
        textPetDeath.setText("");
        labelPicture.setVisible(false);
        
        buttonNext.setEnabled(false);
        buttonPrev.setEnabled(false);
        buttonUpdate.setEnabled(false);
	}
	
	public void FillFields(ResultSet rsIn) {
		try {
		String petName    = rsIn.getString("name");
		String petOwner   = rsIn.getString("owner");
		String petSpecies = rsIn.getString("species");
		String petSex     = rsIn.getString("sex");
		String petBirth   = rsIn.getString("birth");
		String petDeath   = rsIn.getString("death");
		Blob   petPhoto   = rsIn.getBlob("photo");
	 
		this.textPetName.setText(petName);
		this.textPetOwner.setText(petOwner);
		this.textPetSpecies.setText(petSpecies);
		this.textPetSex.setText(petSex);
		this.textPetBirth.setText(petBirth);
		this.textPetDeath.setText(petDeath);
		
		this.image = new ImageIcon(petPhoto.getBytes(1, (int) petPhoto.length()));
		this.labelPicture.setIcon(this.image);
		this.labelPicture.setVisible(true);
		
		System.out.println("Displayed record for "+petName);
		}
		
		catch(Exception fillEx) {
			System.out.println("FillFields exception: "+fillEx.toString());
		}
	}
}
