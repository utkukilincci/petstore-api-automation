package core.serviceBase;

import lombok.Data;

import static java.lang.String.format;

@Data
public class QueryParam {

    private String key;
    private Object value;

    public QueryParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return format("Key: %s, Value: %s", key, value);
    }
}