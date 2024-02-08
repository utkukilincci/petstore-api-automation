package controller.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

import static core.utils.RandomUtils.randomId;
import static core.utils.RandomUtils.randomName;

@Data
public class PetModel {
    @SerializedName("id")
    private Long id;

    @SerializedName("category")
    private PetCategory category;

    @SerializedName("name")
    private String name;

    @SerializedName("photoUrls")
    private List<String> photoUrls;

    @SerializedName("tags")
    private List<PetTags> tags;

    @SerializedName("status")
    private String status;

    public static PetModel createPet() {
        PetModel pet = new PetModel();

        pet.setId(randomId());
        pet.setCategory(new PetCategory());
        pet.setName(randomName());
        pet.setPhotoUrls(List.of(randomName()));
        pet.setTags(List.of(new PetTags()));
        pet.setStatus("available");

        return pet;
    }

    public Boolean compareModels(PetModel expectedModel) {
        return this.getId().equals(expectedModel.getId()) &&
                this.getName().equals(expectedModel.getName()) &&
                this.getStatus().equals(expectedModel.getStatus()) &&
                this.category.getId().equals(expectedModel.category.getId()) &&
                this.category.getName().equals(expectedModel.category.getName());
    }
}

@Data
class PetCategory implements Serializable {
    @SerializedName("id")
    private Long id = randomId();

    @SerializedName("name")
    private String name = randomName();
}

@Data
class PetTags implements Serializable {
    @SerializedName("id")
    private Long id = randomId();

    @SerializedName("name")
    private String name = randomName();
}
