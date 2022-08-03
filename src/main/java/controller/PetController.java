package controller;

import org.json.JSONObject;
import serviceBase.GeneralApiController;
import serviceBase.ReadableResponse;

import static base.Keywords.BASE_URL;

public class PetController extends GeneralApiController {
    public PetController() {
        super(BASE_URL);
    }

    public ReadableResponse postPet(JSONObject body) {
        return postRequest(body,"/pet");
    }

    public ReadableResponse updatePet(JSONObject body) {
        return putRequest(body,"/pet");
    }

    public ReadableResponse getPet(Long petId) {
        return getRequest("/pet/" + petId.toString());
    }

    public ReadableResponse deletePet(Long petId) {
        return deleteRequest("/pet/" + petId.toString());
    }
}
