package controller.helper.petStoreApiHelper;

import com.google.gson.Gson;
import controller.service.request.petStoreApiRequest.PetController;
import controller.service.requestData.petStoreApiControllerData.PetControllerData;
import controller.models.petStoreApiModels.PetModel;
import org.json.JSONObject;
import core.serviceBase.ReadableResponse;

import static controller.matchers.BaseMatchers.shouldStatusCodeSameAs;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;

public class PetControllerHelper {
    PetController petController = new PetController();
    PetControllerData petControllerData = new PetControllerData();

    public PetModel postPet(PetModel petModel) {
        JSONObject mainData = petControllerData.preparePetModelData(petModel);
        ReadableResponse response = petController.postPet(mainData);

        assertThat("When a user tries to post pet, ", response, shouldStatusCodeSameAs(SC_OK));

        return new Gson().fromJson(response.getFullBodyMessage(), PetModel.class);
    }
}
