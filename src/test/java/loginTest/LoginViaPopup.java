package loginTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)

public class LoginViaPopup extends ParentTest {


    String login, password;

    public LoginViaPopup(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters(name = "login ={0} pass = {1}")
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {"test1@uemail99.com", "123456"},
        });
    }

    @Test
    public void validLogin() {
        mainPage.openSite();
        mainPage.openAuthorizationPopup();
        mainPage.loginUserOnMainPage(login, password);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), true);
    }

    /*
    // It's failed test - only for test!!!
     */
    @Test
    public void failedValidLoginForTest() {
        mainPage.openSite();
        mainPage.openAuthorizationPopup();
        mainPage.loginUserOnMainPage(login, password);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }


    @Test
    public void loginWithInvalidCredentials() {
        mainPage.openSite();
        mainPage.openAuthorizationPopup();
        mainPage.loginUserOnMainPage("kozak@test.com", password);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
        checkAC("Authorization popup is displayed", mainPage.authorizationPopupIsDisplayed(), true);
        checkAC("The tooltip about entered invalid credentials is displayed", mainPage.tooltipAboutEnteredInvalidCredentialsIsDisplayed(), true);
    }

    @Test
    public void loginWithoutCredentials() {
        mainPage.openSite();
        mainPage.openAuthorizationPopup();
        mainPage.loginUserOnMainPage("", "");
        checkAC("Authorization popup is displayed", mainPage.authorizationPopupIsDisplayed(), true);
        checkAC("The tooltip about not entered login is displayed", mainPage.tooltipAboutNotEnteredLoginInputIsDisplayed(), true);
        checkAC("The tooltips about not entered  password  is displayed", mainPage.tooltipAboutNotEnteredPasswordInputIsDisplayed(), true);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }

    @Test
    public void loginWithoutLogin() {
        mainPage.openSite();
        mainPage.openAuthorizationPopup();
        mainPage.loginUserOnMainPage("", password);
        checkAC("Authorization popup is displayed", mainPage.authorizationPopupIsDisplayed(), true);
        checkAC("The tooltip about not entered login is displayed", mainPage.tooltipAboutNotEnteredLoginInputIsDisplayed(), true);
        checkAC("The tooltips about not entered  password  is displayed", mainPage.tooltipAboutNotEnteredPasswordInputIsDisplayed(), false);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }

    @Test
    public void loginWithoutPassword() {
        mainPage.openSite();
        mainPage.openAuthorizationPopup();
        mainPage.loginUserOnMainPage(login, "");
        checkAC("Authorization popup is displayed", mainPage.authorizationPopupIsDisplayed(), true);
        checkAC("The tooltip about not entered login is displayed", mainPage.tooltipAboutNotEnteredLoginInputIsDisplayed(), false);
        checkAC("The tooltips about not entered  password  is displayed", mainPage.tooltipAboutNotEnteredPasswordInputIsDisplayed(), true);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }

}
