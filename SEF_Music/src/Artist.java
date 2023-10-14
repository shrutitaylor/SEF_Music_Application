
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    
    public Artist() {}   
    
	
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
		if(validityCheck()) {
			// add it to file if all the conditions are satisfied
			//if everything is valid we will add artist data to file
			addToFile(new Artist (this.getID(),this.getName(),this.getAddress(),this.getBirthdate(),this.getBio(),this.getOccupations(),this.getGenres(),this.getAwards()));
			return true;
		}else {
			return false;
		}
	
	}

	public boolean updateArtist()
	{
	  
		int valid = 0;
		if(validityCheck()) { valid+=1; }
			
		//check Birthdate
		if(checkAge(this.getBirthdate())) { valid+=1 ; }
		
		//check valid Awards year
		if(checkAwardsYear(Awards)) {	valid+=1; }
		
		if(valid == 3) {
			//if all three are valid
			updateFile(new Artist (this.getID(),this.getName(),this.getAddress(),this.getBirthdate(),this.getBio(),this.getOccupations(),this.getGenres(),this.getAwards()), this.getID());
			return true;
		}
		return false;
	}
	private boolean checkAwardsYear(ArrayList<String> awards) {
		
		int count =0;
		for(String a : awards) {
			int year = Integer.parseInt(a.split(",")[0]);
			
			if(year >= 2000) {
				count+=1;
			}
		}
		if(count == awards.size()) {
			//All awards can be changed
			return true;
		}
		return false;
	}


	public int countWords (String bio)
	{
	StringTokenizer stringTokenizer1 = new StringTokenizer(bio);
	return stringTokenizer1.countTokens();
	}
	
	public boolean checkBirthdate(String birthdate) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 try {
		        Date date = dateFormat.parse(birthdate);
		        String formattedDate = dateFormat.format(date);

		        // Check if the parsed and formatted date matches the input
		        if (!formattedDate.equals(birthdate)) {
		            System.out.println("Date does not match the specified format.");
		            return false;
		        }

		        System.out.println("Valid Date Format: " + formattedDate);
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
	    
	    if(awards.isEmpty()) {
	    	System.out.println("Valid award");
	    	// zero awards
	    	return true;
	    }
	    
	    try {
		for (String award : awards) {
			// Split the award into Year and Title
			parts = award.split(", ", 2); }

			if (parts.length != 2) {
            // Invalid format if not split into two parts
				System.out.println("Invalid award");
                return false;
            }
        
			String year = parts[0];
	        String title = parts[1];
        
        //validate year and title
        if (!year.matches(yearPattern) || countWords(title)<4 || countWords(title)>10) {
        	System.out.println("Invalid award");
            return false;
        }
	    }catch(Exception e) {
	    	System.out.println("Invalid award");
	    	return false;
	    }
	    System.out.println("VALID award");
        return true;
	}
	
	public boolean checkAddress(String address) {
		
	    // Split the address into its components using the '|' character as a delimiter
	    String[] addressComponents = address.split("\\|");

	    // Check if there are exactly three components (City, State, and Country)
	    if (addressComponents.length != 3) {
	        System.out.println("Invalid Address Format");
	        return false;
	    }else {

	    // Check if each component is not empty (contains at least one character)
	    for (String component : addressComponents) {
	        if (component.trim().isEmpty()) {
	            System.out.println("Invalid Address Format");
	            return false;
	        }
	    }

	    // If all checks pass, the address format is valid
	    System.out.println("Valid Address Format: " + address);
	    return true;
	    }
	}
	
	public boolean checkAge(String Birthdate) {
		
		//Assuming this is a valid birthdate
		String[] date = Birthdate.split("-");
		int year = Integer.parseInt(date[2]);
		
		if(year < 2000) {
			//Born before 2000
			System.out.println("Born before 2000, occupation cannot be changed");
			return false;
		}
		System.out.println("Born after 2000");
		return true;
		
	}

	public boolean addToFile(Artist artist) {
		String filePath = "artist_info.txt";

        // Write the artist information to the text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write( artist.getID()+", " + artist.getName()+", " + artist.getAddress()
            +", " + artist.getBirthdate()+", " + artist.getBio()+", " + artist.getOccupations().toString()
            +", " + artist.getGenres().toString()+", " + artist.getAwards().toString());
            writer.newLine();
           
            return true;
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
	}
	public boolean updateFile(Artist artist, String artistId) {
		// Define the file path
        String filePath = "artist_info.txt";

        // Read the existing artist information from the file
        ArrayList<String> artistInfoList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	if(!line.split(",")[0].equals(artistId)) {
            		 artistInfoList.add(line);
            		 System.out.println(line.split(",")[0]+"--"+line);
            	}
                               
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //add the updated list
        	artistInfoList.add(artist.getID()+", " + artist.getName()+", " + artist.getAddress()
            +", " + artist.getBirthdate()+", " + artist.getBio()+", " + artist.getOccupations().toString()
            +", " + artist.getGenres().toString()+", " + artist.getAwards().toString());
        
        	//Write it to the file
        	 try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        		 for (String line : artistInfoList) {
        			 //System.out.println(line);
                 writer.write( line);
                 writer.newLine();
        		 }
        		 
                 return true;
                 
             } catch (IOException e) {
                 e.printStackTrace();
                 return false;
             }
        
        	 
	}
	
	public boolean validityCheck(){
		//check all conditions via this method
		
		// Check Artist ID : 
		matcher = pattern.matcher(ID);
		//if(ID.length()!=10 ) {
			if(!matcher.matches() ) {
			// if length of artist ID != 10 and does not match the given pattern
			System.out.println("Wrong Artist ID");
			return false;
		}else
			if(!checkAddress(Address)){
				// to check if the Adress is in the format CITY|STATE|COUNTRY
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


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public String getBirthdate() {
		return Birthdate;
	}


	public void setBirthdate(String birthdate) {
		Birthdate = birthdate;
	}


	public String getBio() {
		return Bio;
	}


	public void setBio(String bio) {
		Bio = bio;
	}


	public ArrayList<String> getOccupations() {
		return Occupations;
	}


	public void setOccupations(ArrayList<String> occupations) {
		Occupations = occupations;
	}


	public ArrayList<String> getGenres() {
		return Genres;
	}


	public void setGenres(ArrayList<String> genres) {
		Genres = genres;
	}


	public ArrayList<String> getAwards() {
		return Awards;
	}


	public void setAwards(ArrayList<String> awards) {
		Awards = awards;
	}
}