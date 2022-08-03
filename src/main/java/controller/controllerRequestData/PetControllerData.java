package controller.controllerRequestData;

import com.google.gson.Gson;
import models.PetModel;
import org.json.JSONObject;

public class PetControllerData {
    Gson gson = new Gson();

    public JSONObject preparePetModelData(PetModel petModel) {
        return new JSONObject(gson.toJson(petModel));
    }
}
