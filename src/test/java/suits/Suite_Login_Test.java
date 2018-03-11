package suits;

import loginTest.LoginViaLoginPage;
import loginTest.LoginViaPopup;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                LoginViaPopup.class,
                LoginViaLoginPage.class
        }
)
public class Suite_Login_Test {
}
