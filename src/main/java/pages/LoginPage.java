package pages;

import static libs.ActionsWithOurElement.*;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends ParentPage {


    String currentWindowHandle = webDriver.getWindowHandle();  // retur to this page
    static WebDriverWait webDriverWait10 = new WebDriverWait(webDriver, 10);

////////////////---Login Form---////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//*[text()='Вход']")
    private WebElement titleVhod;

    @FindBy(id = "loginForm")
    private WebElement loginForm;

    @FindBy(id = "UserName")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='E-mail или номер телефона']")
    private WebElement placeholderForUserNameInput;

    @FindBy(id = "Password")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@placeholder='Пароль']")
    private WebElement placeholderForPasswordInput;

    @FindBy(xpath = "//span[@class='field-validation-error text-danger']")
    private WebElement tooltipAboutInvalidCredintials;

    @FindBy(id = "UserName-error")
    private WebElement tooltipAboutEmptyLoginInput;

    @FindBy(xpath = "//span[contains(text(),'Поле пароль обязательное')]")
    private WebElement tooltipAboutEmptyPassfordInput;

    @FindBy(xpath = "//span[contains(text(),'Введите от 6 до 100 символов')]")
    private WebElement tooltipAboutShortPassword;

    @FindBy(id = "RememberMe")
    private WebElement checkBoxRememberMe;

    @FindBy(xpath = "//*[text()='Войти']")
    private WebElement buttonVoity;

    @FindBy(id = "LinkedIn")
    private WebElement buttonLoginViaLinkedIn;

    @FindBy(id = "Google")
    private WebElement buttonLoginViaGoogle;

    @FindBy(xpath = "//*[contains(text(),'Войти используя ваш Google аккаунт')]")
    private WebElement tyltipForLoginViaGoogle;

    @FindBy(id = "Facebook")
    private WebElement buttonLoginViaFacebook;

    @FindBy(xpath = "//*[@class='ForgotPassword']")
    private WebElement buttonForgotPassword;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public LoginPage(WebDriver webDriver) {
        super(webDriver, "/account/login");
    }


    @Step
    public void loginUser(String userName, String password) {
        checkCurrentUrl();
        enterTextInToInput(inputUserName, userName);
        enterTextInToInput(inputPassword, password);
        clickOnElement(buttonVoity);
    }


    /////////////////---Asserts---//////////////////////////////////////////////////////////////////////////////////////

    public boolean tooltipAboutShortPasswordIsDisplayed() {
        return isElementPresent(tooltipAboutShortPassword);
    }

    public boolean tooltipAboutInvalidCredentialsIsDisplayed() {
        return isElementPresent(tooltipAboutInvalidCredintials);
    }

    public boolean tooltipAboutNotEnteredLoginInputIsDisplayed() {
        return isElementPresent(tooltipAboutEmptyLoginInput);
    }

    public boolean tooltipAboutNotEnteredPasswordInputIsDisplayed() {
        return isElementPresent(tooltipAboutEmptyPassfordInput);
    }

    public boolean loginFormIsDisplayed() {
        return isElementPresent(loginForm);
    }


}
