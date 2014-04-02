package com.testmojio;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

@SuppressWarnings("rawtypes")
public class MainMenuTest extends ActivityInstrumentationTestCase2 {
	
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

	public MainMenuTest() {
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
		
		//Click on the button to select device, make sure the text Select Device comes up, press back to get rid of the popup
		public void testSelectDeviceButton() {
			String keyWord = "Select Device or Event";
			solo.waitForActivity("MainMenuActivity");
			solo.sleep(1500);
			solo.clickOnButton(0);
			solo.waitForText(keyWord);
			assertTrue(solo.searchText(keyWord));
			solo.goBack();
		}
		
		//Click on the map auto refresh toggle button, see if the toast comes up
		public void testMapRefreshButton() {
			String keyWord = "Map Updated";
			solo.waitForActivity("MainMenuActivity");
			solo.sleep(5000);
			solo.clickOnButton(1);
			solo.waitForText(keyWord);
			assertTrue(solo.searchText(keyWord));
		}
		
		
		//After each test, click logout
		protected void tearDown() throws Exception {
			solo.getText("Log Out").isEnabled();
			solo.clickOnButton("Log Out");
			solo.finishOpenedActivities();
		}
	

}
