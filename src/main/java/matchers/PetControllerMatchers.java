package matchers;

import com.google.gson.Gson;
import models.PetModel;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import serviceBase.ReadableResponse;

import static java.lang.String.format;

public class PetControllerMatchers {

    public static Matcher<ReadableResponse> shouldExistPet(PetModel expectedPetModel) {
        return new BaseMatcher<ReadableResponse>() {
            PetModel createdPet;
            final Gson gson = new Gson();

            @Override
            public boolean matches(final Object item) {
                ReadableResponse response = (ReadableResponse) item;

                createdPet = gson.fromJson(response.getFullBodyMessage(), PetModel.class);
                return createdPet.compareModels(expectedPetModel);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("Response should have correct created pet info")
                        .appendValue(
                                format("Response : Pet name: %s. pet id: %s. pet status: %s.",
                                        createdPet.getName(), createdPet.getId().toString(), createdPet.getStatus())
                        )
                        .appendValue(
                                format("Expected : Pet name: %s. pet id: %s. pet status: %s",
                                expectedPetModel.getName(), expectedPetModel.getId().toString(), expectedPetModel.getStatus())
                        );
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                description.appendText("not found!");
            }
        };
    }
}
