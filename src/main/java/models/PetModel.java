package models;

import lombok.Data;

import java.util.List;

import static helpers.RandomUtils.randomId;
import static helpers.RandomUtils.randomName;

@Data
public class PetModel {
    private Long id;
    private PetCategory category;
    private String name;
    private List<String> photoUrls;
    private List<PetTags> tags;
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
class PetCategory {
    private Long id = randomId();
    private String name = randomName();
}

@Data
class PetTags {
    private Long id = randomId();
    private String name = randomName();
}
