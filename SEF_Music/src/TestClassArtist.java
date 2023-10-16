

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TestClassArtist {
	ArrayList<String> validAwards = new ArrayList<>();
	
	@Test
	void testValidAddArtist() {
		//Test Case 1: Check the function with valid inputs
		
		//Test Case 1_Test Data 1		
        validAwards.clear();
        validAwards.add("2023, Best Album of the year");
        
        Artist artist1 = new Artist("123ABCDE*&", "Taylor Swift", "Melbourne|Victoria|Australia", "13-12-1989", "This is a valid bio with more than 10 words. Adding three more words.",
                new ArrayList<>(Arrays.asList("Singer","Director", "Songwriter")), new ArrayList<>(Arrays.asList("Country","Pop")), validAwards);
       
        assertTrue(artist1.addArtist());
        
      //Test Case 1_Test Data 2
        validAwards.clear();
                
        Artist artist2 = new Artist("615TDBHJ#$", "Harry Styles", "Brisbane|Queensland|Australia", "05-10-1989", "This is a valid bio with more than 10 words. Adding four or more words.",
                new ArrayList<>(Arrays.asList("Singer","Director", "Songwriter","ScreenWriter")), new ArrayList<>(Arrays.asList("Country","Pop","Rock")), validAwards);
       
        assertTrue(artist2.addArtist());
	}
	
	@Test
    public void testInvalidArtistID() {
		validAwards.clear();
        validAwards.add("2023, Top Artist of the month");
        
      //Test Case 2_Test Data 1 - LESS THAN 10 CHARACTERS
        Artist artist = new Artist("123ASD", "Jane Smith", "Brisbane|Queensland|Australia", "10-20-1990", "This is a valid bio with more than 10 words.",
                new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(Arrays.asList("Pop","Rock")),validAwards);
        assertFalse(artist.addArtist());
        
      //Test Case 2_Test Data 2 - NO SPECIAL CHARACTERS
        Artist artist1 = new Artist("123ASDWQ45", "Jane Smith", "Brisbane|Queensland|Australia", "10-20-1990", "This is a valid bio with more than 10 words.",
                new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(Arrays.asList("Pop","Rock")),validAwards);
        assertFalse(artist1.addArtist());
    }
	
	@Test
    public void testInvalidBirthdate() {
		validAwards.clear();
        validAwards.add("2023, Top Artist of the month");
        
      //Test Case 3_Test Data 1 - DATE IN FORMAT dd/mm/yyyy
        Artist artist = new Artist("123ASDWQ+%", "Jane Smith", "Brisbane|Queensland|Australia", "10/20/1990", "This is a valid bio with more than 10 words.",
                new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(Arrays.asList("Pop","Rock")),validAwards);
        assertFalse(artist.addArtist());
        
      //Test Case 3_Test Data 2 - DATE IN FORMAT yyyy-mm-dd
        Artist artist1 = new Artist("123ASDWQ:?", "Jane Smith", "Brisbane|Queensland|Australia", "1990-12-11", "This is a valid bio with more than 10 words.",
                new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(Arrays.asList("Pop","Rock")),validAwards);
        assertFalse(artist1.addArtist());
    }
	
	@Test
    public void testInvalidBio() {
		System.out.println("test4");
		validAwards.clear();
        validAwards.add("2023, Top Artist of the month");
        
      //Test Case 4_Test Data 1 - VERY BIG BIO
        Artist artist = new Artist("569SSDDF*&", "Taylor Swift", "Melbourne|Victoria|Australia", "13-12-1989", "Taylor Alison Swift is an American singer-songwriter. Recognized for her songwriting, musical versatility, artistic reinventions, and influence on the music industry, she is a prominent cultural figure of the 21st century.",
                new ArrayList<>(Arrays.asList("Singer","Director", "Songwriter")), new ArrayList<>(Arrays.asList("Country","Pop")), validAwards);
        assertFalse(artist.addArtist());
        
      //Test Case 4_Test Data 2 - VERY SHORT BIO
        Artist artist1 = new Artist("123ASDWQ:?", "Jane Smith", "Brisbane|Queensland|Australia", "13-12-1989",  "Short Bio.",
                new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(Arrays.asList("Pop","Rock")),validAwards);
        assertFalse(artist1.addArtist());
    }
	
	@Test
    public void testInvalidOccupations() {
		System.out.println("test5");
		validAwards.clear();
        validAwards.add("2023, Top Artist of the month");
        
      //Test Case 5_Test Data 1 - NO OCCUPATION I.E ZERO OCCUPATION
        Artist artist = new Artist("569SSDDF*&", "Taylor Swift", "Melbourne|Victoria|Australia", "13-12-1989", "Taylor Alison Swift is an American singer-songwriter. Her cat is Olivia.",
                new ArrayList<>(), new ArrayList<>(Arrays.asList("Country","Pop")), validAwards);
        assertFalse(artist.addArtist());
        
      //Test Case 5_Test Data 2 - MORE THAN 5 OCCUPATIONS
        Artist artist1 = new Artist("123ASDWQ:?", "Jane Smith", "Brisbane|Queensland|Australia", "13-12-1989","This is a valid bio with more than 10 words.",
                new ArrayList<>(Arrays.asList("Singer","Director", "Songwriter","Playwriter","Scriptwriter","Producer")), new ArrayList<>(Arrays.asList("Pop","Rock")),validAwards);
        assertFalse(artist1.addArtist());
    }
	
	@Test
    public void testInvalidGenres() {
		System.out.println("test6");
		validAwards.clear();
        validAwards.add("2023, Top Artist of the month");
        
      //Test Case 6_Test Data 1 - MORE THAN 5 GENRES
        Artist artist = new Artist("569SSDDF*&", "Taylor Swift", "Melbourne|Victoria|Australia", "13-12-1989", "Taylor Alison Swift is an American singer-songwriter. Her cat is Olivia.",
                new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(Arrays.asList("Country","Pop","Rock","Melody","KPOP","Indian")), validAwards);
        assertFalse(artist.addArtist());
        
      //Test Case 6_Test Data 2 - LESS THAN 2 GENRES
        Artist artist1 = new Artist("123ASDWQ:?", "Jane Smith", "Brisbane|Queensland|Australia", "13-12-1989","This is a valid bio with more than 10 words.",
                new ArrayList<>(Arrays.asList("Singer","Director")), new ArrayList<>(Arrays.asList("Country")),validAwards);
        assertFalse(artist1.addArtist());
    }
	
	@Test
    public void testInvalidAwards() {
		System.out.println("test7");
		ArrayList<String> tooManyAwards = new ArrayList<>();
        tooManyAwards.add("2020, Best Album");
        tooManyAwards.add("2019, Best Song");
        tooManyAwards.add("2018, Best Artist");
        tooManyAwards.add("2017, Best Musician");
        
      //Test Case 7_Test Data 1 - MORE THAN THREE AWARDS
        Artist artist = new Artist("569SSDDF*&", "Taylor Swift", "Melbourne|Victoria|Australia", "13-12-1989", "Taylor Alison Swift is an American singer-songwriter. Her cat is Olivia.",
                new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(Arrays.asList("Country","Pop")), tooManyAwards);
        assertFalse(artist.addArtist());
        
      //Test Case 7_Test Data 2 - TITLE HAS LESS THAN 4 WORDS
        validAwards.add("2020, Best Album");
        Artist artist1 = new Artist("123ASDWQ:?", "Jane Smith", "Brisbane|Queensland|Australia", "13-12-1989","This is a valid bio with more than 10 words.",
                new ArrayList<>(Arrays.asList("Singer","Director")), new ArrayList<>(Arrays.asList("Country","Pop")),validAwards);
        assertFalse(artist1.addArtist());
    }
	
	@Test
    public void testInvalidAddress() {
			System.out.println("test8");
			validAwards.clear();
	        validAwards.add("2023, Top Artist of the month");
	        
	      //Test Case 8_Test Data 1 - IF ADDRESS HAS ONLY CITY ,STATE : Melbourne|Victoria
	        Artist artist = new Artist("569SSDDF*&", "Taylor Swift", "Melbourne|Victoria", "13-12-1989", "Taylor Alison Swift is an American singer-songwriter. Her cat is Olivia.",
	                new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(Arrays.asList("Country","Pop","Rock")), validAwards);
	        assertFalse(artist.addArtist());
	        
	      //Test Case 8_Test Data 2 - IF ADDRESS IN FORMAT : Brisbane,Queensland,Australia
	        Artist artist1 = new Artist("123ASDWQ:?", "Jane Smith", "Brisbane,Queensland,Australia", "13-12-1989","This is a valid bio with more than 10 words.",
	                new ArrayList<>(Arrays.asList("Singer","Director")), new ArrayList<>(Arrays.asList("Country","Pop")),validAwards);
	        assertFalse(artist1.addArtist());
	    }
	
	//UPDATE ARTIST TEST CASES
	@Test
    public void testUpdateArtistValid() {
		
		validAwards.clear();
        validAwards.add("2023, Top Artist of the month");
        
      //Test Case 10_Test Data 1 - BIRTHDATE AFTER 2000
        Artist artist = new Artist("123ABCDE*&", "Taylor Swift New", "Melbourne|Victoria|Australia", "13-12-2004", "This is a valid bio with more than 10 words. Adding three more words.",
                new ArrayList<>(Arrays.asList("Singer","Director", "Songwriter")), new ArrayList<>(Arrays.asList("Country","Pop")), validAwards);
        assertTrue(artist.updateArtist());
        
        
      //Test Case 10_Test Data 2 - AWARDS YEAR AFTER 2000
        Artist artist1 = new Artist("615TDBHJ#$", "Harry Styles New ", "Brisbane|Queensland|Australia", "05-10-2000", "This is a valid bio with more than 10 words. Adding four or more words.",
                new ArrayList<>(Arrays.asList("Singer","Director", "Songwriter","ScreenWriter")), new ArrayList<>(Arrays.asList("Country","Pop","Rock")), validAwards);
        assertTrue(artist1.updateArtist());
    }
	
	@Test
    public void testUpdateArtistInvalid() {
		
		validAwards.clear();
        validAwards.add("2023, Top Artist of the month");
        
      //Test Case 11_Test Data 1 - BIRTHDATE BEFORE 2000
        Artist artist = new Artist("569SSDDF*&", "Taylor Swift", "Melbourne|Victoria|Australia", "13-12-1989", "Taylor Alison Swift is an American singer-songwriter. Her cat is Olivia.",
                new ArrayList<>(Arrays.asList("Scriptwriter","Producer")), new ArrayList<>(Arrays.asList("Country","Pop")), validAwards);
        assertFalse(artist.updateArtist());
        
        
        validAwards.clear();
        validAwards.add("1989, Top Artist of the month");
      //Test Case 11_Test Data 2 - AWARDS YEAR BEFORE 2000
        Artist artist1 = new Artist("123ASDWQ:?", "Jane Smith", "Brisbane|Queensland|Australia", "13-12-2000","This is a valid bio with more than 10 words.",
                new ArrayList<>(Arrays.asList("Singer","Director", "Songwriter","Playwriter")), new ArrayList<>(Arrays.asList("Pop","Rock")),validAwards);
        assertFalse(artist1.updateArtist());
    }
	
	

}
