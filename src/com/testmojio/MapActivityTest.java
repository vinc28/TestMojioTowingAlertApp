package com.testmojio;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

@SuppressWarnings("rawtypes")
public class MapActivityTest extends ActivityInstrumentationTestCase2 {
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "eecegroup32.mojiotowingalert.android.LoginActivity";
	
	private static Class<?> launcherActivityClass;
    static{
            try {
                    launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
            } catch (ClassNotFoundException e) {
            	System.out.println("Class not found");
                    throw new RuntimeException(e);
            }
    }

	public MapActivityTest() {
		super(launcherActivityClass);
		// TODO Auto-generated constructor stub
	}
	
	private Solo solo;
	
	//Enter credentials and click log in before each test
		protected void setUp() throws Exception {
			solo = new Solo(getInstrumentation(), getActivity());
			//Waiting for LoginActivity slows the tests down, it waits at the LoginActivity page for a while before entering credentials
			//It seems to work fine without waiting for LoginActivity and instead enters the credentials right away
			//solo.waitForActivity("LoginActivity");
			solo.enterText(0, "vchiu86");
			solo.enterText(1, "capstone");
			solo.clickOnButton("Log In");
		}
		
		//Test to see whether the Map page loads or not by looking for the word "Location"
		public void testMapLoad() {
			//Navigate to and make sure we are on the Maps page 
			String keyWord = "Location";
			solo.waitForActivity("MainMenuActivity");
			solo.clickOnButton("Map");
			solo.waitForActivity("MapsActivity");
			assertTrue(solo.searchText(keyWord));
		}
		
		//After each test, go back to the main menu and click log out
		protected void tearDown() throws Exception {
			solo.goBack();
			solo.clickOnButton("Log Out");
			solo.finishOpenedActivities();
		}
	

}
