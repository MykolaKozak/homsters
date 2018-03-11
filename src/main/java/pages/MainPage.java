package pages;

import static libs.ActionsWithOurElement.*;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends ParentPage {

    String currentWindowHandle = webDriver.getWindowHandle();  // retur to this page
  //  WebDriverWait wait = new WebDriverWait(webDriver, 20);


//////////---Main Page---///////////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//*[@class='b-complex-header__button b-complex-header__button--login js-get-popup-autification']")
    private WebElement iconForLoginPopup;

    @FindBy(xpath = "//*[@class='js-toogle-main-menu b-complex-header__button b-complex-header__button--burger']")
    private WebElement iconOfGeneralMenu;

    @FindBy(xpath = "//*[@class='b-complex-header__button b-complex-header__button--login-active js-toogle-main-menu']")
    private WebElement iconForProfileMenu;

    @FindBy(xpath = "//a[@href='https://homsters.kz/account/login']")
    private WebElement subMenuVoity;

    //////---Popup Authorization---/////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//*[@class='b-popup__wrapper']")
    private WebElement authorizationPopup;

    @FindBy(xpath = "//*[@class='b-popup__close js-popup__close']")
    private WebElement iconCloseOnLoginPopup;

    @FindBy(id = "mail_or_email_login")
    private WebElement inputUserName;

    @FindBy(id = "user_password_login")
    private WebElement inputPassword;

    @FindBy(xpath = "//*[@for='remember_me_login']")
    private WebElement checkBoxRememberMe;

    @FindBy(xpath = "//*[@class='b-button b-button--full b-button--confirm-secondary']")
    private WebElement buttonVoity;

    @FindBy(xpath = "//form/div[2]/div/div[@class='b-form__error js-error active']")
    private WebElement tooltipAboutNotEnteredLogin;

    @FindBy(xpath = "//form/div[3]/div/div[@class='b-form__error js-error active']")
    private WebElement tooltipAboutNotEnteredPassword;

    @FindBy(xpath = "//*[contains(text(),'Неверное имя пользователя или пароль')]")
    private WebElement tooltipAboutInvalidCredentials;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public MainPage(WebDriver webDriver) {
        super(webDriver, "/");
    }

    @Step
    public void openSite() {
        try {
            webDriver.get(configProperties.base_url());
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open url");
            Assert.fail("Can not open url ");
        }
    }

    @Step
    public void openAuthorizationPopup() {
        clickOnElement(iconForLoginPopup);
    }

    @Step
    public void loginUserOnMainPage(String userName, String password) {
        checkCurrentUrl();
        enterTextInToInput(inputUserName, userName);
        enterTextInToInput(inputPassword, password);
        setStateToCheckBox(checkBoxRememberMe, "Checked");
        clickOnElement(buttonVoity);
    }

    @Step
    public void clickOnIconForGeneralMenu() {
        clickOnElement(iconOfGeneralMenu);
    }

    @Step
    public void clickOnSubMenuVoity() {
        clickOnElement(subMenuVoity);
    }


/////////////////////---Asserts---//////////////////////////////////////////////////////////////////////////////////////

    public boolean profileMenuIsDisplayed() {
        return isElementPresent(iconForProfileMenu);
    }

    public boolean authorizationPopupIsDisplayed() {
        return isElementPresent(iconCloseOnLoginPopup);
    }

    public boolean tooltipAboutNotEnteredLoginInputIsDisplayed() {
        return isElementPresent(tooltipAboutNotEnteredLogin);
    }

    public boolean tooltipAboutNotEnteredPasswordInputIsDisplayed() {
        return isElementPresent(tooltipAboutNotEnteredPassword);
    }

    public boolean tooltipAboutEnteredInvalidCredentialsIsDisplayed() {
        return isElementPresent(tooltipAboutInvalidCredentials);
    }

}
