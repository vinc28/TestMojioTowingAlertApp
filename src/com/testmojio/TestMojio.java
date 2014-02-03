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
	
	protected void setUp() throws Exception {
		//super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		solo.enterText(0, "vchiu86");
		solo.enterText(1, "capstone");
		solo.clickOnButton("Log In");
	}
	
	public void testNotificationsActivity() {
		solo.clickOnButton("Notifications");
		assertTrue(solo.searchText("Date"));
	}
	

	public void testMapActivity() {
		solo.clickOnButton("Map");
		assertTrue(solo.searchText("Location"));
	}
	
	public void testSettingsActivity() {
		solo.clickOnButton("Settings");
		assertTrue(solo.searchText("Subscriptions"));
	}
		

	protected void tearDown() throws Exception {
		//super.tearDown();
		solo.goBack();
		solo.clickOnButton("Log Out");
		solo.finishOpenedActivities();
	}

}
