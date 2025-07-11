package net.framinfo.freetube.validators;

import jakarta.enterprise.context.ApplicationScoped;
import net.framinfo.freetube.providers.JSONProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Verifies that the object is not null
 */
@ApplicationScoped
public class NonNullValidator {


    /**
     * Verifies that an object is not null / empty / blank
     *
     * @param o object
     * @return true if object is not null nor empty nor blank, false otherwise
     */
    public boolean run(Object o) {
        if (o != null) {
            if (o instanceof List)
                return !((List) o).isEmpty();
            if (o instanceof String)
                return !((String) o).isEmpty() && !((String) o).isBlank() && !((String) o).equals("null");
            if (o instanceof JSONObject)
                return !((JSONObject) o).isEmpty();
            if (o instanceof JSONArray)
                return !((JSONArray) o).isEmpty();
            if (o instanceof JSONProvider)
                return !((JSONProvider) o).getBaseString().isEmpty() && !((JSONProvider) o).getBaseString().isBlank();
            return true;
        }
        return false;
    }

    /**
     * Verifies that a list of objects is not null / empty / blank
     *
     * @param o object list
     * @return true if object is not null nor empty nor blank, false otherwise
     */
    public boolean runAll(Object... o) {
        AtomicBoolean toReturn = new AtomicBoolean(true);
        Arrays.stream(o).forEach(obj -> {
            if (toReturn.get()) {
                if (obj == null) toReturn.set(false);
                else if (obj instanceof List && ((List) obj).isEmpty()) toReturn.set(false);
                else if (obj instanceof String && (((String) obj).isEmpty() || ((String) obj).isBlank() || ((String) obj).equals("null")))
                    toReturn.set(false);
                else if (obj instanceof JSONObject && ((JSONObject) obj).isEmpty()) toReturn.set(false);
                else if (obj instanceof JSONArray && ((JSONArray) obj).isEmpty()) toReturn.set(false);
                else if (obj instanceof JSONProvider && (((JSONProvider) obj).getBaseString().isEmpty() || ((JSONProvider) obj).getBaseString().isBlank()))
                    toReturn.set(false);
            }
        });
        return toReturn.get();
    }
}
