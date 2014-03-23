package com.testmojio;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class SettingsActivityTest extends ActivityInstrumentationTestCase2 {
	

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
    
    public SettingsActivityTest() {
		super(launcherActivityClass);
		// TODO Auto-generated constructor stub
	}
    
    private Solo solo;
    
    protected void setUp() {
		solo = new Solo(getInstrumentation(), getActivity());
		//Waiting for LoginActivity slows the tests down, it waits at the LoginActivity page for a while before entering credentials
		//It seems to work fine without waiting for LoginActivity and instead enters the credentials right away
		//solo.waitForActivity("LoginActivity");
		solo.enterText(0, "vchiu86");
		solo.enterText(1, "capstone");
		solo.clickOnButton("Log In");	
	}
    
    //Test to see whether the Settings page loads or not and to make sure the buttons/checkboxes can be pressed and a toast appears
  	public void testSettingsActivityToggleButtons() {
  		boolean boxStatus;
  		
  		//Navigate to and make sure we are on the Subscriptions page 
  		solo.waitForActivity("MainMenuActivity");
  		solo.clickOnButton(2);
  		solo.waitForActivity("SettingsActivity");
  		assertTrue(solo.searchText("Subscriptions"));
  	
  		//Test to see if the status of the notifications button is different after pressing
  		boxStatus = solo.isToggleButtonChecked(0);
  		solo.clickOnButton(0);
  		assertTrue(solo.searchText("Saved"));
  		assertTrue(solo.isToggleButtonChecked(0) != boxStatus);
  		boxStatus = solo.isToggleButtonChecked(0);
  		solo.clickOnButton(0);
  		assertTrue(solo.isToggleButtonChecked(0) != boxStatus);
  		
  		//Test to see if the sound setting checkbox is different after pressing
  		boxStatus = solo.isCheckBoxChecked(0);
  		solo.clickOnButton(1);
  		assertTrue(solo.searchText("Saved"));
  		assertTrue(solo.isCheckBoxChecked(0) != boxStatus);
  		boxStatus = solo.isCheckBoxChecked(0);
  		solo.clickOnButton(1);
  		assertTrue(solo.isCheckBoxChecked(0) != boxStatus);
  		
  		//Test to see if the vibration setting checkbox is different after pressing
  		boxStatus = solo.isCheckBoxChecked(1);
  		solo.clickOnButton(2);
  		assertTrue(solo.searchText("Saved"));
  		assertTrue(solo.isCheckBoxChecked(1) != boxStatus);
  		boxStatus = solo.isCheckBoxChecked(1);
  		solo.clickOnButton(2);
  		assertTrue(solo.isCheckBoxChecked(1) != boxStatus);
  		
  		//Check to see if the status of the subscription status of the first dongle is different after pressing
  		boxStatus = solo.isToggleButtonChecked(1);
  		solo.clickOnButton(3);
  		assertTrue(solo.searchText("Saved"));
  		assertTrue(solo.isToggleButtonChecked(1) != boxStatus);
  		boxStatus = solo.isToggleButtonChecked(1);
  		solo.clickOnButton(3);
  		assertTrue(solo.isToggleButtonChecked(1) != boxStatus);
  	}
  	
  	//Test to see whether the Settings page saves after going back to the main menu
  	public void testSettingsActivitySaved() {
  		boolean boxStatus0, boxStatus1, boxStatus2, boxStatus3;
  		//Navigate to and make sure we are on the Subscriptions page 
  		solo.waitForActivity("MainMenuActivity");
  		solo.clickOnButton(2);
  		solo.waitForActivity("SettingsActivity");
  		assertTrue(solo.searchText("Subscriptions"));
  		solo.clickOnButton(0);
  		boxStatus0 = solo.isToggleButtonChecked(0);
  		solo.clickOnButton(1);
  		boxStatus1 = solo.isCheckBoxChecked(0);
  		solo.clickOnButton(2);
  		boxStatus2 = solo.isCheckBoxChecked(1);
  		solo.clickOnButton(3);
  		boxStatus3 = solo.isToggleButtonChecked(1);
  		solo.goBack();
  		solo.waitForActivity("MainMenuActivity");
  		solo.clickOnButton(2);
  		solo.waitForActivity("SettingsActivity");
  		assertTrue(solo.searchText("Subscriptions"));
  		assertTrue(boxStatus0 == solo.isToggleButtonChecked(0));
  		assertTrue(boxStatus1 == solo.isCheckBoxChecked(0));
  		assertTrue(boxStatus2 == solo.isCheckBoxChecked(1));
  		assertTrue(boxStatus3 == solo.isToggleButtonChecked(1));
  	}
    
  	
  
  	//After each test, go back to the main menu and click log out
  	protected void tearDown() throws Exception {
  		solo.goBack();
  		solo.clickOnButton("Log Out");
  		solo.finishOpenedActivities();
  	}
    
    

}
