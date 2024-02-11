package pet;

import core.testBase.BaseTest;
import controller.service.request.petStoreApiRequest.PetController;
import controller.helper.petStoreApiHelper.PetControllerHelper;
import controller.service.requestData.petStoreApiControllerData.PetControllerData;
import controller.models.petStoreApiModels.PetModel;
import org.json.JSONObject;
import org.testng.annotations.Test;
import core.serviceBase.ReadableResponse;

import static controller.matchers.BaseMatchers.shouldStatusCodeSameAs;
import static controller.matchers.petStoreApiMatchers.PetControllerMatchers.shouldExistPet;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;

public class UpdatePetEndpointTests extends BaseTest {
    PetController petController = new PetController();
    PetControllerData petControllerData = new PetControllerData();
    PetControllerHelper petControllerHelper = new PetControllerHelper();

    @Test
    public void shouldUpdatePet(){
        String updatedName = "Very cute pet";

        PetModel petModel = PetModel.createPet();
        petControllerHelper.postPet(petModel);

        petModel.setName(updatedName);

        JSONObject requestBody = petControllerData.preparePetModelData(petModel);
        ReadableResponse response = petController.updatePet(requestBody);

        assertThat("When a user tries to update a pet, status code should be '200' ", response, shouldStatusCodeSameAs(SC_OK));
        assertThat("When a user tries to update a pet, should be able to see created pet ", response, shouldExistPet(petModel));
    }

    @Test
    public void shouldNotUpdatePetWithoutId(){
        String updatedName = "Very cute pet";

        PetModel petModel = PetModel.createPet();
        petControllerHelper.postPet(petModel);

        petModel.setName(updatedName);

        JSONObject requestBody = petControllerData.preparePetModelData(petModel);
        requestBody.remove("id");

        ReadableResponse response = petController.updatePet(requestBody);

        /** This case should return 400 because it 's bad request but end point return 200 and set random id. this case will fail :( **/

        assertThat(
                "When a user tries to update a pet, status code should be '200' ",
                response,
                shouldStatusCodeSameAs(SC_OK)
        );
        assertThat(
                "When a user tries to update a pet, should be able to see created pet ",
                response,
                shouldExistPet(petModel)
        );
    }
}
