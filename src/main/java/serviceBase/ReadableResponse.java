package serviceBase;

import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ReadableResponse {
    private int statusCode;
    private String statusLine;
    private Response response;

    public ReadableResponse(Response response) {
        this.response = response;
        this.statusCode = response.getStatusCode();
        this.statusLine = response.getStatusLine();
    }

    public String getBodyMessage(String path) {
        return response.path(path).toString()
                .replace("[", "")
                .replace("]", "");
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getFullBodyMessage() {
        return response.print();
    }
}
