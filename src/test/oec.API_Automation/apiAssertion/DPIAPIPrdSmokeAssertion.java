package apiAssertion;

import static org.assertj.core.api.Assertions.assertThat;

public class DPIAPIPrdSmokeAssertion {

    public static DPIAPIPrdSmokeAssertion assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode).as("YMM & VIN Navigation is proper " + expectedStatusCode + "as expected").isEqualTo(expectedStatusCode);
        return new DPIAPIPrdSmokeAssertion();
    }
}
