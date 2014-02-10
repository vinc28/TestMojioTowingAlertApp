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
	}
	
	
	//Test to see whether Notifications page loads or not by looking for the word "Date"
	public void testNotificationsActivityLoad() {
		//Navigate to and make sure we are on the Notifications page 
		String keyWord = "Past";
		solo.waitForActivity("MainMenuActivity");
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		assertTrue(solo.searchText(keyWord));
	}
	
	//Test to see if clicking Refresh on the notifications page will make a toast pop up
	public void testNotificationsActivityRefresh() {
		String keyWord = "Refreshed";
		solo.waitForActivity("MainMenuActivity");
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		solo.clickOnButton("Refresh");
		assertTrue(solo.searchText(keyWord));
	}
	
	//Test to see if notifications page contains a date
	public void testNotificationsActivityContainsADate() {
		//Navigate to and make sure we are on the Notifications page 
		solo.waitForActivity("MainMenuActivity");
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
	
	
	//Test to see if clicking a view will bring you to the details page
	public void testNotificationsActivityDetails() {
		String keyWord = "Details";
		solo.waitForActivity("MainMenuActivity");
		solo.clickOnButton("Notifications");
		solo.waitForActivity("NotificationsActivity");
		solo.clickOnText("Event ID");
		solo.waitForActivity("TowDetailsActivity");
		assertTrue(solo.searchText(keyWord));
		solo.goBack();
	}
	
	//Test to see if the details page has a date
		public void testNotificationsDetailsContainsADate() {
			solo.waitForActivity("MainMenuActivity");
			solo.clickOnButton("Notifications");
			solo.waitForActivity("NotificationsActivity");
			solo.clickOnText("Event ID");
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
	
	//After each test, go back to the main menu and click log out
	protected void tearDown() throws Exception {
		solo.goBack();
		solo.clickOnButton("Log Out");
		solo.finishOpenedActivities();
	}
	

}
