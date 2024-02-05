package controller.service.requestData;

import com.google.gson.Gson;
import controller.models.PetModel;
import org.json.JSONObject;

public class PetControllerData {
    Gson gson = new Gson();

    public JSONObject preparePetModelData(PetModel petModel) {
        return new JSONObject(gson.toJson(petModel));
    }
}
