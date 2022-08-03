package matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import serviceBase.ReadableResponse;


import static java.lang.String.format;

public class BaseMatchers {

    public static Matcher<ReadableResponse> shouldResponseEqualsMessageAs(String path, String message) {
        return new BaseMatcher<ReadableResponse>() {
            private ReadableResponse response;

            @Override
            public boolean matches(final Object item) {
                response = (ReadableResponse) item;

                return response.getBodyMessage(path).equals(message);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(format("Response should have %s as, %s", path, message));
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                description.appendText(" displayed as, ").appendValue(response.getBodyMessage("."));
            }
        };
    }

    public static Matcher<ReadableResponse> shouldStatusCodeSameAs(int statusCode) {
        return new BaseMatcher<ReadableResponse>() {
            private ReadableResponse response;

            @Override
            public boolean matches(final Object item) {
                response = (ReadableResponse) item;

                return response.getStatusCode() == statusCode;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(format("Status code should be %s", statusCode));
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                description.appendText(format(" displayed as: %s , error message: %s", response.getStatusCode(), response.getFullBodyMessage()));
            }
        };
    }
}
