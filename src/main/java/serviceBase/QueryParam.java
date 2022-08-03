package serviceBase;

import static java.lang.String.format;

public class QueryParam {

    private String key;
    private Object value;

    public QueryParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public QueryParam(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return format("Key: %s, Value: %s", key, value);
    }
}