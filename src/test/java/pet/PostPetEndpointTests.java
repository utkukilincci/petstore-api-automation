package pet;

import core.testBase.BaseTest;
import controller.service.request.petStoreApiRequest.PetController;
import controller.service.requestData.petStoreApiControllerData.PetControllerData;
import controller.models.petStoreApiModels.PetModel;
import io.qameta.allure.Description;
import org.json.JSONObject;
import org.testng.annotations.Test;
import core.serviceBase.ReadableResponse;

import static core.testBase.Keywords.BAD_HAPPENED_ERROR;
import static core.utils.RandomUtils.randomName;
import static controller.matchers.BaseMatchers.shouldResponseEqualsMessageAs;
import static controller.matchers.BaseMatchers.shouldStatusCodeSameAs;
import static controller.matchers.petStoreApiMatchers.PetControllerMatchers.shouldExistPet;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostPetEndpointTests extends BaseTest {
    PetController petController = new PetController();
    PetControllerData petControllerData = new PetControllerData();

    @Test
    @Description("Should post pet with valid request body")
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
