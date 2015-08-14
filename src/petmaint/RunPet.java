
package petmaint;

 public class RunPet {
    /**
     * @param args - none
     */
    public static void main(String[] args) {
    	
    	try {
    	PetMaint petmaint = new PetMaint();
    	petmaint.setTitle("Pets Info");
    	petmaint.setLocation(500,300);
        petmaint.setVisible(true);
        petmaint.setDefaultCloseOperation(PetMaint.DISPOSE_ON_CLOSE);
       
        // Connect up to our database.
        try {
	        petmaint.petcon = new ConnectMenagerie();
		}
		
		catch (Exception connex) {
			System.out.println("Initiation error - connection to Menagerie database failed.");
			}
		
		// Initialize form's buttons appropriately
		petmaint.buttonConnect.setEnabled(false);
		petmaint.buttonQuery.setEnabled(true);
		petmaint.buttonDisconnect.setEnabled(true);
    	}
        
        catch (Exception e) {
    		System.out.println("Exception starting application: "+e.toString());
    	}
        
        finally {
        	;
        }
    }
}
