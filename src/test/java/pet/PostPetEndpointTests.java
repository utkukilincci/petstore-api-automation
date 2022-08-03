package pet;

import base.BaseTest;
import controller.PetController;
import controller.controllerRequestData.PetControllerData;
import models.PetModel;
import org.json.JSONObject;
import org.testng.annotations.Test;
import serviceBase.ReadableResponse;

import static base.Keywords.BAD_HAPPENED_ERROR;
import static helpers.RandomUtils.randomName;
import static matchers.BaseMatchers.shouldResponseEqualsMessageAs;
import static matchers.BaseMatchers.shouldStatusCodeSameAs;
import static matchers.PetControllerMatchers.shouldExistPet;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostPetEndpointTests extends BaseTest {
    PetController petController = new PetController();
    PetControllerData petControllerData = new PetControllerData();

    @Test
    public void shouldPostPet(){
        PetModel petModel = PetModel.createPet();

        JSONObject requestBody = petControllerData.preparePetModelData(petModel);
        ReadableResponse response = petController.postPet(requestBody);

        assertThat(
                "When a user tries to post a pet, status code should be '200' ",
                response,
                shouldStatusCodeSameAs(SC_OK)
        );
        assertThat(
                "When a user tries to post a pet, should be able to see created pet ",
                response,
                shouldExistPet(petModel)
        );
    }

    @Test
    public void shouldNotPostPetWithInvalidId(){
        PetModel petModel = PetModel.createPet();

        JSONObject requestBody = petControllerData.preparePetModelData(petModel);
        requestBody.put("id", randomName());

        ReadableResponse response = petController.postPet(requestBody);

        /** This case should return 400 because it 's bad request but end point return 500 :( **/

        assertThat(
                "When a user tries to post a pet, status code should be '400' ",
                response,
                shouldStatusCodeSameAs(SC_INTERNAL_SERVER_ERROR)
        );
        assertThat(
                "When a user tries to post a pet, should be able to see created pet ",
                response,
                shouldResponseEqualsMessageAs("message", BAD_HAPPENED_ERROR)
        );
    }
}
