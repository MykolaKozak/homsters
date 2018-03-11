package loginTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;
import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)


public class LoginViaLoginPage extends ParentTest {

    String login, password;

    public LoginViaLoginPage(String login, String password) {
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
    public void loginViaValidCredentials() {
        mainPage.openSite();
        mainPage.clickOnIconForGeneralMenu();
        mainPage.clickOnSubMenuVoity();
        loginPage.loginUser(login, password);
        checkAC("The 'Login form'  is displayed", loginPage.loginFormIsDisplayed(), false);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), true);
    }

    @Test
    public void loginWithInvalidShortPassword() {
        mainPage.openSite();
        mainPage.clickOnIconForGeneralMenu();
        mainPage.clickOnSubMenuVoity();
        loginPage.loginUser(login, "123");
        checkAC("The tooltip about the short password is displayed", loginPage.tooltipAboutShortPasswordIsDisplayed(), true);
        checkAC("The 'Login form'  is displayed", loginPage.loginFormIsDisplayed(), true);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }

    @Test
    public void loginWithInvalidPassword() {
        mainPage.openSite();
        mainPage.clickOnIconForGeneralMenu();
        mainPage.clickOnSubMenuVoity();
        loginPage.loginUser(login, "Invalid password123");
        checkAC("The tooltip about the invalid login or password is displayed", loginPage.tooltipAboutInvalidCredentialsIsDisplayed(), true);
        checkAC("The 'Login form'  is displayed", loginPage.loginFormIsDisplayed(), true);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }

    @Test
    public void loginWithInvalidEmail() {
        mainPage.openSite();
        mainPage.clickOnIconForGeneralMenu();
        mainPage.clickOnSubMenuVoity();
        loginPage.loginUser("invalid@email.com", password);
        checkAC("The tooltip about the invalid login or password is displayed", loginPage.tooltipAboutInvalidCredentialsIsDisplayed(), true);
        checkAC("The 'Login form'  is displayed", loginPage.loginFormIsDisplayed(), true);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }

    @Test
    public void loginWithoutEmail() {
        mainPage.openSite();
        mainPage.clickOnIconForGeneralMenu();
        mainPage.clickOnSubMenuVoity();
        loginPage.loginUser("", password);
        checkAC("The tooltip about not entered login is displayed", loginPage.tooltipAboutNotEnteredLoginInputIsDisplayed(), true);
        checkAC("The 'Login form'  is displayed", loginPage.loginFormIsDisplayed(), true);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }

    @Test
    public void loginWithoutPassword() {
        mainPage.openSite();
        mainPage.clickOnIconForGeneralMenu();
        mainPage.clickOnSubMenuVoity();
        loginPage.loginUser(login, "");
        checkAC("The tooltip about not entered password is displayed", loginPage.tooltipAboutNotEnteredPasswordInputIsDisplayed(), true);
        checkAC("The 'Login form'  is displayed", loginPage.loginFormIsDisplayed(), true);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }

    @Test
    public void loginWithoutCredentials() {
        mainPage.openSite();
        mainPage.clickOnIconForGeneralMenu();
        mainPage.clickOnSubMenuVoity();
        loginPage.loginUser("", "");
        checkAC("The tooltip about not entered login is displayed", loginPage.tooltipAboutNotEnteredLoginInputIsDisplayed(), true);
        checkAC("The tooltips about not entered  password  is displayed", loginPage.tooltipAboutNotEnteredPasswordInputIsDisplayed(), true);
        checkAC("The 'Login form'  is displayed", loginPage.loginFormIsDisplayed(), true);
        checkAC("User should be logIn ", mainPage.profileMenuIsDisplayed(), false);
    }

}
