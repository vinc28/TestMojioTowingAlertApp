package com.testmojio;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class NotificationsActivityTest extends ActivityInstrumentationTestCase2 {
	
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
    
	public NotificationsActivityTest() {
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
		solo.waitForActivity("MainMenuActivity");
		solo.sleep(1500);
	}
	
	
	//Test to see whether Notifications page loads or not by looking for the word "Past"
	public void testNotificationsActivityLoad() {
		//Navigate to and make sure we are on the Notifications page 
		String keyWord = "Past";
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		assertTrue(solo.searchText(keyWord));
	}
	
	//Test to see if notifications page contains a date
	public void testNotificationsActivityContainsADate() {
		//Navigate to and make sure we are on the Notifications page 
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		//Check to see that it contains a month
		assertTrue(solo.searchText("January") || solo.searchText("February") || solo.searchText("March") 
			|| solo.searchText("April") || solo.searchText("May") || solo.searchText("June") 
			|| solo.searchText("July") || solo.searchText("August") || solo.searchText("September") 
			|| solo.searchText("October") || solo.searchText("November") || solo.searchText("December"));
		//Check to see that it contains a day
		assertTrue(solo.searchText("Monday") || solo.searchText("Tuesday") || solo.searchText("Wednesday")
			|| solo.searchText("Thursday") || solo.searchText("Friday") || solo.searchText("Saturday") 
			|| solo.searchText("Sunday"));
	}
	
	//Test to see if notifications page contains a specific date, Thursday, March 20, 2014
	public void testNotificationsActivityContainsASpecificDate() {
		//Navigate to and make sure we are on the Notifications page 
		String keyWord = "Thursday, March 20, 2014";
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		assertTrue(solo.searchText(keyWord));
	}
	
	
	//Test to see if clicking a view will bring you to the details page
	public void testNotificationsActivityDetails() {
		String keyWord = "Details";
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		solo.clickOnText("Device:");
		solo.waitForActivity("TowDetailsActivity");
		assertTrue(solo.searchText(keyWord));
		solo.goBack();
	}
	
	//Test to see if the details page has a date
	public void testNotificationsDetailsContainsADate() {
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		solo.clickOnText("Device:");
		solo.waitForActivity("TowDetailsActivity");
		//Check to see that it contains a month
		assertTrue(solo.searchText("January") || solo.searchText("February") || solo.searchText("March") 
				|| solo.searchText("April") || solo.searchText("May") || solo.searchText("June") 
				|| solo.searchText("July") || solo.searchText("August") || solo.searchText("September") 
				|| solo.searchText("October") || solo.searchText("November") || solo.searchText("December"));
		//Check to see that it contains a day
		assertTrue(solo.searchText("Monday") || solo.searchText("Tuesday") || solo.searchText("Wednesday")
				|| solo.searchText("Thursday") || solo.searchText("Friday") || solo.searchText("Saturday") 
				|| solo.searchText("Sunday"));		
		solo.goBack();
	}
		
	//Test to see if notifications page contains a specific date, Thursday, March 20, 2014
	public void testSpecificDetailsPage() {
		//Navigate to and make sure we are on the Notifications page 
		String keyWord = "Thursday, March 20, 2014";
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		solo.clickOnText(keyWord);
		solo.waitForActivity("TowDetailsActivity");
		assertTrue(solo.searchText(keyWord));
		solo.goBack();
	}
	
	//Test to see if the popup for filter shows
	public void testDisplayFilter(){
		String keyWord = "Select";
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		solo.waitForText("Device:");
		solo.clickOnButton("Filter");
		solo.waitForText(keyWord);
		assertTrue(solo.searchText(keyWord));
		solo.goBack();
	}
	
	//Test to see if filtering works
	public void testFiltering(){
		String keyWord = "vctest2";
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		solo.waitForText("Device:");
		solo.clickOnButton("Filter");
		solo.waitForText("Select Dongles");
		solo.clickOnButton("Name:vctest2");
		solo.goBack();
		solo.waitForText("Device:");
		assertTrue(!(solo.searchText(keyWord)));
		solo.clickOnButton("Filter");
		solo.waitForText("Select Dongles");
		solo.clickOnButton("Name:vctest2");
		solo.goBack();
		assertTrue((solo.searchText(keyWord)));
	}
	
	//After each test, go back to the main menu and click log out
	protected void tearDown() throws Exception {
		solo.goBack();
		solo.clickOnButton("Log Out");
		solo.finishOpenedActivities();
	}
	

}
