package com.testmojio;


import com.robotium.solo.Solo;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.test.ActivityInstrumentationTestCase2;


@SuppressWarnings("rawtypes")
public class TestMojio extends ActivityInstrumentationTestCase2 {
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "eecegroup32.mojiotowingalert.android.MainActivity";
	
	private static Class<?> launcherActivityClass;
    static{
            try {
                    launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
            } catch (ClassNotFoundException e) {
            	System.out.println("Class not found");
                    throw new RuntimeException(e);
            }
    }
	
	public TestMojio() {
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
	
	//Test to see whether Notifications page loads or not by looking for the word "Date"
	public void testNotificationsActivity() {
		//Navigate to and make sure we are on the Notifications page 
		String keyWord = "Date";
		solo.waitForActivity("MainMenuActivity");
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		assertTrue(solo.searchText(keyWord));
	}
	
	//Test to see whether the Map page loads or not by looking for the word "Location"
	public void testMapActivity() {
		//Navigate to and make sure we are on the Maps page 
		String keyWord = "Location";
		solo.waitForActivity("MainMenuActivity");
		solo.clickOnButton("Map");
		solo.waitForActivity("MapsActivity");
		assertTrue(solo.searchText(keyWord));
	}
	
	//Test to see whether the Settings page loads or not and to make sure the buttons/checkboxes can be pressed
	public void testSettingsActivity() {
		boolean boxStatus;
		
		//Navigate to and make sure we are on the Subscriptions page 
		solo.waitForActivity("MainMenuActivity");
		solo.clickOnButton("Settings");
		solo.waitForActivity("SettingsActivity");
		assertTrue(solo.searchText("Subscriptions"));
	
		//Test to see if the status of the notifications button is different after pressing
		boxStatus = solo.isToggleButtonChecked(0);
		solo.clickOnButton(0);
		assertTrue(solo.isToggleButtonChecked(0) != boxStatus);
		boxStatus = solo.isToggleButtonChecked(0);
		solo.clickOnButton(0);
		assertTrue(solo.isToggleButtonChecked(0) != boxStatus);
		
		//Test to see if the sound setting checkbox is different after pressing
		boxStatus = solo.isCheckBoxChecked(0);
		solo.clickOnButton(1);
		assertTrue(solo.isCheckBoxChecked(0) != boxStatus);
		boxStatus = solo.isCheckBoxChecked(0);
		solo.clickOnButton(1);
		assertTrue(solo.isCheckBoxChecked(0) != boxStatus);
		
		//Test to see if the vibration setting checkbox is different after pressing
		boxStatus = solo.isCheckBoxChecked(1);
		solo.clickOnButton(2);
		assertTrue(solo.isCheckBoxChecked(1) != boxStatus);
		boxStatus = solo.isCheckBoxChecked(1);
		solo.clickOnButton(2);
		assertTrue(solo.isCheckBoxChecked(1) != boxStatus);
		
		//Check to see if the status of the subscription status of the first dongle is different after pressing
		boxStatus = solo.isToggleButtonChecked(1);
		solo.clickOnButton(3);
		assertTrue(solo.isToggleButtonChecked(1) != boxStatus);
		boxStatus = solo.isToggleButtonChecked(1);
		solo.clickOnButton(3);
		assertTrue(solo.isToggleButtonChecked(1) != boxStatus);
	}
		
	//After each test, go back to the main menu and click log out
	protected void tearDown() throws Exception {
		solo.goBack();
		solo.clickOnButton("Log Out");
		solo.finishOpenedActivities();
	}

}
