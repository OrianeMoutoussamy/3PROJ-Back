package net.framinfo.freetube.providers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Automatic JSON Parser
 */
@Getter
public class JSONProvider {

    private static final Logger logger = LoggerFactory.getLogger(JSONProvider.class);

    /**
     * The base string used to initialize the JSONProvider
     */
    private final String baseString;

    /**
     * The JSONObject obtained by parsing the baseString
     */
    private JSONObject jsonBody;

    /**
     * The HashMap obtained by parsing the jsonBody
     */
    private HashMap<String, Object> parsedJson;

    public JSONProvider(String baseString) {
        this.baseString = baseString;
        try {
            this.jsonBody = new JSONObject(this.baseString);
            this.parseBody();
        } catch (JSONException e) {
            logger.error("Unable to parse JSON: ", e);
        }
    }

    /**
     * Returns a value for the set key of type classType from a JSON string
     *
     * @param classType the class type to be returned
     * @param jsonKey   the key to get the value (should be json)
     * @return null if cast failed, the value cast to classType otherwise
     */
    public <T> T getFromJson(Class<T> classType, String jsonKey) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String str = (String) this.parsedJson.get(jsonKey);
            return mapper.readValue(str, classType);
        } catch (Exception e) {
            logger.error("Unable to get " + jsonKey + " with type " + classType.getSimpleName() + ": ", e);
            return null;
        }
    }

    /**
     * Returns a value for the set key of type classType
     *
     * @param classType the class type to be returned
     * @param key       the key to get the value
     * @return null if cast failed, the value cast to classType otherwise
     */
    public <T> T get(Class<T> classType, String key) {
        try {
            return (T) this.parsedJson.get(key);
        } catch (Exception e) {
            logger.error("Unable to get " + key + " with type " + classType.getSimpleName() + ": ", e);
            return null;
        }
    }

    /**
     * Automatically parses json body
     */
    private void parseBody() {
        try {
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            final MapType type = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
            this.parsedJson = mapper.readValue(this.baseString, type);
        } catch (Exception e) {
            logger.error("Unable to automatically parse JSON body: ", e);
        }
    }
}

