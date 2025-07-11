package net.framinfo.freetube.providers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.validators.NonNullValidator;
import org.eclipse.microprofile.config.ConfigProvider;

/**
 * Provides configuration data from application.properties
 */
@ApplicationScoped
public class CustomConfigProvider {

    @Inject
    NonNullValidator nonNullValidator;

    /**
     * Will return the value for set key, in classType type
     * @param classType the type to be returned
     * @param key the key to get the value
     * @return value associated to this key in properties
     */
    public <T> T getValue(Class<T> classType, String key) {
        return nonNullValidator.runAll(classType, key) ? ConfigProvider.getConfig().getValue(key, classType) : null;
    }
}

