package com.testmojio;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

@SuppressWarnings("rawtypes")
public class LoginTest extends ActivityInstrumentationTestCase2 {
	
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

	public LoginTest() {
		super(launcherActivityClass);
		// TODO Auto-generated constructor stub
	}
	
	private Solo solo;
	
	
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	//Test logging in with an invalid username and invalid password
	public void testLoginInvalidUsernameAndPassword() {
		String keyWord = "combination";
		solo.enterText(0, "a");
		solo.enterText(1, "a");
		solo.clickOnButton("Log In");
		assertTrue(solo.searchText(keyWord));
		solo.goBack();
	}
	
	//Test logging in with a valid username and invalid password combination
	public void testLoginValidUsernameInvalidPassword() {
		String keyWord = "combination";
		solo.enterText(0, "vchiu86");
		solo.enterText(1, "a");
		solo.clickOnButton("Log In");
		assertTrue(solo.searchText(keyWord));
		solo.goBack();
	}
	
	//Test logging in with a valid username and valid password
	public void testLoginValidUsernameAndPassword() {
		String keyWord = "Welcome";
		solo.enterText(0, "vchiu86");
		solo.enterText(1, "capstone");
		solo.clickOnButton("Log In");
		solo.waitForActivity("MainMenuActivity");
		assertTrue(solo.searchText(keyWord));
		//Wait 2 seconds before pressing Log Out
		solo.sleep(2000);
		solo.clickOnButton("Log Out");
		solo.goBack();
	}
	
	//Wait 3 seconds between each test case
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
		solo.sleep(7000);
	}

}
