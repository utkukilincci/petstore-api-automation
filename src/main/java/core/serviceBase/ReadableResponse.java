package core.serviceBase;

import io.restassured.response.Response;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ReadableResponse {
    @Getter
    private final int statusCode;
    private final Response response;

    public ReadableResponse(Response response) {
        this.response = response;
        this.statusCode = response.getStatusCode();
    }

    public String getBodyMessage(String path) {
        return response.path(path).toString()
                .replace("[", "")
                .replace("]", "");
    }

    public String getFullBodyMessage() {
        return response.print();
    }
}
