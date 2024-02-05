package pet;

import core.testBase.BaseTest;
import controller.service.request.PetController;
import controller.service.helper.PetControllerHelper;
import controller.models.PetModel;
import org.testng.annotations.Test;
import core.serviceBase.ReadableResponse;

import static core.testBase.Keywords.PET_NOT_FOUND_ERROR;
import static core.helpers.RandomUtils.randomId;
import static controller.matchers.BaseMatchers.shouldResponseEqualsMessageAs;
import static controller.matchers.BaseMatchers.shouldStatusCodeSameAs;
import static controller.matchers.PetControllerMatchers.shouldExistPet;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPetEndpointTests extends BaseTest {
    PetController petController = new PetController();
    PetControllerHelper petControllerHelper = new PetControllerHelper();

    @Test
    public void shouldGetPet(){
        PetModel petModel = PetModel.createPet();
        petControllerHelper.postPet(petModel);

        ReadableResponse response = petController.getPet(petModel.getId());

        assertThat("When a user tries to get a pet, status code should be '200' ", response, shouldStatusCodeSameAs(SC_OK));
        assertThat("When a user tries to get a pet, should be able to see created pet ", response, shouldExistPet(petModel));
    }

    @Test
    public void shouldNotGetPetWithInvalidId(){
        ReadableResponse response = petController.getPet(randomId());

        assertThat(
                "When a user tries to get a pet with invalid id, status code should be '404' ",
                response,
                shouldStatusCodeSameAs(SC_NOT_FOUND)
        );
        assertThat(
                "When a user tries to get a pet with invalid id, should be able to see this error message ",
                response,
                shouldResponseEqualsMessageAs("message", PET_NOT_FOUND_ERROR)
        );
    }
}
