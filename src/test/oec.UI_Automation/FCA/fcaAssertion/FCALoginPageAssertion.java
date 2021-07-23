package FCA.fcaAssertion;

import FCA.fcaObjectRepository.FCALoginPage;
import base.AbstractAssertion;

import static org.assertj.core.api.Assertions.assertThat;

public class FCALoginPageAssertion extends AbstractAssertion<FCALoginPage> {

    public FCALoginPageAssertion assertAtLoginPage() {
        String currentUrl = functionalLibrary.getPageUrl();
        String expectedUrl = functionalLibrary.getBaseURL();
        assertThat(functionalLibrary.isAt(expectedUrl)).as("verify if at 'Login Page'" +
                "\nExpected: " + expectedUrl + "\nActual: " + currentUrl)
                .isTrue();
        return this;
    }

    public FCALoginPageAssertion assertRequiredFieldsArePresent() {
        assertThat(functionalLibrary.isUserNameDisplayed()).as("Verify that UserName field is present.").isTrue();
        assertThat(functionalLibrary.isPasswordDisplayed()).as("Verify that Password field is present.").isTrue();
        assertThat(functionalLibrary.isLoginBtnDisplayed()).as("Verify that Login Button is present.").isTrue();
        assertThat(functionalLibrary.isLoginImageDisplayed()).as("Verify that Login Image is present.").isTrue();
        assertThat(functionalLibrary.isLoginBodyDisplayed()).as("Verify that Login Body is present.").isTrue();
        return this;
    }

}
