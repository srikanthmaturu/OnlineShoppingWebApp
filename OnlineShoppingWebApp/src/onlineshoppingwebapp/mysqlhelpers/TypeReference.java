package onlineshoppingwebapp.mysqlhelpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * References a generic type.
 *
 * @author crazybob@google.com (Bob Lee)
 */

public abstract class TypeReference <T>{

    protected final Type type;
    protected volatile Constructor<?> constructor;

    protected TypeReference() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }

    /**
     * Instantiates a new instance of {@code T} using the default, no-arg
     * constructor.
     */

    public Object newInstance()
            throws NoSuchMethodException, IllegalAccessException,
                   InvocationTargetException, InstantiationException {
        if (constructor == null) {
            Class<?> rawType = type instanceof Class<?>
                ? (Class<?>) type
                : (Class<?>) ((ParameterizedType) type).getRawType();
            constructor = rawType.getConstructor();
        }
        return  constructor.newInstance();
    }

    /**
     * Gets the referenced type.
     */
    protected Type getType() {
        return this.type;
    }

}