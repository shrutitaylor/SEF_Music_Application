

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TestClassArtist {
	ArrayList<String> validAwards = new ArrayList<>();
	
	@Test
	void testAddArtist_testCase1() {
		//Test Case 1: Check the function with valid inputs
		//Test Case 1_Test Data 1
		
        validAwards.add("2022, Best Song Written For Visual Media");
        
        Artist artist = new Artist("123ABCDE_%", "John Doe", "123 Main St", "05-11-1980", "This is a valid bio with more than 10 words.",
                new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(Arrays.asList("Rock","Pop")), validAwards);
       
        assertTrue(artist.addArtist());
        
      //Test Case 1_Test Data 2
        validAwards.clear();
        validAwards.add("2023, Best Album of the year");
        
        Artist artist1 = new Artist("569SSDDF*&", "Taylor Swift", "Melbourne|Victoria|Australia", "13-12-1989", "This is a valid bio with more than 10 words. Adding three more words.",
                new ArrayList<>(Arrays.asList("Singer","Director", "Songwriter")), new ArrayList<>(Arrays.asList("Country","Pop")), validAwards);
       
        assertTrue(artist1.addArtist());
	}

}
