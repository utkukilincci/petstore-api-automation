package controller.controllerHelper;

import com.google.gson.Gson;
import controller.PetController;
import controller.controllerRequestData.PetControllerData;
import models.PetModel;
import org.json.JSONObject;
import serviceBase.ReadableResponse;

import static matchers.BaseMatchers.shouldStatusCodeSameAs;
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
