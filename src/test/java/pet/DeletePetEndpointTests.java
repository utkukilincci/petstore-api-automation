package pet;

import base.BaseTest;
import controller.PetController;
import controller.controllerHelper.PetControllerHelper;
import models.PetModel;
import org.testng.annotations.Test;
import serviceBase.ReadableResponse;

import static helpers.RandomUtils.randomId;
import static matchers.BaseMatchers.shouldResponseEqualsMessageAs;
import static matchers.BaseMatchers.shouldStatusCodeSameAs;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletePetEndpointTests extends BaseTest {
    PetController petController = new PetController();
    PetControllerHelper petControllerHelper = new PetControllerHelper();

    @Test
    public void shouldDeletePet(){
        PetModel petModel = PetModel.createPet();
        petControllerHelper.postPet(petModel);

        ReadableResponse response = petController.deletePet(petModel.getId());

        assertThat(
                "When a user tries to delete a pet, status code should be '200' ",
                response,
                shouldStatusCodeSameAs(SC_OK)
        );
        assertThat(
                "When a user tries to delete a pet, should be able to see deleted pet id in message",
                response,
                shouldResponseEqualsMessageAs("message", petModel.getId().toString())
        );
    }

    @Test
    public void shouldNotDeletePetWithInvalidId(){
        ReadableResponse response = petController.deletePet(randomId());

        assertThat(
                "When a user tries to delete a pet with invalid id, status code should be '404' ",
                response,
                shouldStatusCodeSameAs(SC_NOT_FOUND)
        );
    }
}
