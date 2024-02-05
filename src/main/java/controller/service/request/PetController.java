package controller.service.request;

import org.json.JSONObject;
import core.serviceBase.GeneralApiController;
import core.serviceBase.ReadableResponse;

import static core.testBase.Config.getPetStoreApiUrl;

public class PetController extends GeneralApiController {
    public PetController() {
        super(getPetStoreApiUrl());
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
