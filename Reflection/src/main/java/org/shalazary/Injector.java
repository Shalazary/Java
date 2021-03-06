package org.shalazary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    private static Properties injectorRules;

    public Injector(String injectorRulesFilename) throws IOException {
        InputStream injectorRulesInputStream = ClassLoader.getSystemResourceAsStream(injectorRulesFilename);
        if(injectorRulesInputStream == null)
            throw new FileNotFoundException("File " + injectorRulesFilename + " not found in resources");
        injectorRules = new Properties();
        injectorRules.load(injectorRulesInputStream);
    }

    public Injector() throws IOException {
        this("defaultInjectorRules.properties");
    }

    /**
     * Injects into passed object implementations from injectorRules file
     * @param injectableObject object that will be modified
     * @param <T> type of passes object
     * @throws InjectException
     */
    public <T> void inject(T injectableObject) throws InjectException {
        Class<?> injectableObjectClass = injectableObject.getClass();
        Field[] injectableObjectFields = injectableObjectClass.getDeclaredFields();

        for(Field field : injectableObjectFields) {
            if(field.getAnnotation(AutoInjectable.class) != null) {
                Class<?> fieldClass = field.getType();

                String implementationClassName = injectorRules.getProperty(fieldClass.getName());
                if(implementationClassName == null)
                    throw new InjectException("Not such interface for inject: " + fieldClass.getName());

                Class<?> implementationClass;
                try {
                    implementationClass = Class.forName(implementationClassName);
                } catch (Exception e) {
                    throw new InjectException("Not such implementation for inject: " + implementationClassName);
                }

                Constructor<?> implementationClassConstructors;
                try {
                    implementationClassConstructors = implementationClass.getConstructor();
                } catch (Exception e) {
                    throw new InjectException("Implementation " + implementationClassName + "dose not contains empty constructor");
                }

                Object implementation;
                try {
                    implementation = implementationClassConstructors.newInstance();
                } catch (Exception e) {
                    throw new InjectException(e.getMessage());
                }

                try {
                    field.setAccessible(true);
                    field.set(injectableObject, implementation);
                } catch (Exception e) {
                    throw new InjectException(e.getMessage());
                }
            }
        }
    }
}
