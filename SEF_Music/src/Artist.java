
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Artist {
	private String ID;
	private String Name;
	private String Address;
	private String Birthdate;
	private String Bio;
	private ArrayList<String> Occupations; // like singer, songwriter, composer 
	private ArrayList<String> Genres; // like rock, jazz, blues, pop, classical
	private ArrayList<String> Awards;
	
	//Regular Expression to check artist ID
	String regex = "^[0-9]{3}[A-Z]{5}[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{2}$";
	Pattern pattern = Pattern.compile(regex);
    Matcher matcher ; 
    
    
    
    
	
	public Artist(String id, String name, String address, String birthdate, String bio, ArrayList<String> occupations, ArrayList<String> genres, ArrayList<String> awards){

		this.ID=id;
		this.Name=name;
		this.Address=address;
		this.Birthdate=birthdate;
		this.Bio=bio;
		this.Occupations=occupations;
		this.Genres=genres;
		this.Awards = awards;
	}
			
	public boolean addArtist()
	{
		/* TODO: Add the information of a new artist to a TXT file */
		// If the artist information meets the defined conditions
		// the information should be added to the TXT file and the function should return true.
		// If the artist information does not meet the conditions,
		// the information should not be added to the TXT file and the function should return false.
		// Check Artist ID : 
		matcher = pattern.matcher(ID);
		//if(ID.length()!=10 ) {
			if(!matcher.matches() ) {
			// if length of artist ID != 10 and does not match the given pattern
			System.out.println("Wrong Artist ID");
			return false;
		}else
			if(!checkBirthdate(Birthdate)){
				// to check if the Birthdate is in the correct format DD-MM-YYYY
				return false;
				
		}else 
			if(countWords(Bio)<10 || countWords(Bio)>30) {
				//The bio NOT between 10 to 30 words. 
				 System.out.println("Bio should be between 10 to 30 words");
		          return false;
		}else
			if(Occupations.size()<1 || Occupations.size()>5){
				//An artist should have at least one occupation or a maximum of five occupations.
				System.out.println("least one occupation or a maximum of five occupations");
	            return false;
		}else
			if(Awards.size()<0 || Awards.size()>3 ){
			//An artist can have zero to a maximum of three awards. 
		}else
			if(!checkValidAwards(Awards)) {
				return false;
	            
			}else
				if(Genres.size()<2 || Genres.size()>5){
					//An artist should have at least two music genres and a maximum of five genres
					System.out.println("at least two music genres and a maximum of five genres");
		            return false;
			}else {
				return true;
			}
		return false;
	
	}

//	public boolean updateArtist()
//	{
//	/* TODO: Update the information of a given artist in a TXT file */
//	// If the artist's new information meets the defined conditions,
//	// the artist information should be updated in the TXT file and the function should return true.
//	// If the artist's new information does not meet the following conditions,
//	// the artist information should not be updated in the TXT file and the function should return false. return true;
//	return true;
//	}
	public int countWords (String bio)
	{
	StringTokenizer stringTokenizer1 = new StringTokenizer(bio);
	return stringTokenizer1.countTokens();
	}
	
	public boolean checkBirthdate(String birthdate) {
		//to check Date Format
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    
		// to check if the Birthdate is in the correct format DD-MM-YYYY
		try {
            Date date = dateFormat.parse(Birthdate);
            System.out.println("Valid Date Format: " + dateFormat.format(date));
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid Date Format");
            return false;
        }
	}
	
	public boolean checkValidAwards(ArrayList<String> awards) {
		
		//tocheck year format
	    String yearPattern = "\\d{4}";
	    String[] parts = null ;
	    
	    try {
		for (String award : awards) {
			// Split the award into Year and Title
			parts = award.split(", ", 2); }

			if (parts.length != 2) {
            // Invalid format if not split into two parts
            return false;}
        
		String year = parts[0];
        String title = parts[1];
        
        //validate year and title
        if (!year.matches(yearPattern) || countWords(title)<4 || countWords(title)>10) {
            return false;
        }
	    }catch(Exception e) {
	    	return false;
	    }
        return true;
	}
}