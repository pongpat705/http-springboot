package th.co.priorsolution.springboot.novice.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import th.co.priorsolution.springboot.novice.annotations.Hash;
import th.co.priorsolution.springboot.novice.component.hash.HashComponent;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class HashJsonSerialization extends StdSerializer<Object> implements ContextualSerializer {

    private static final long serialVersionUID = 1205739833414582495L;
    private transient HashComponent hashComponent;

    public HashJsonSerialization() {
        super(Object.class);
    }

    protected HashJsonSerialization(Class<Object> obj) {
        super(obj);
    }

    protected HashJsonSerialization(HashComponent hashComponent) {
        super(Object.class);
        this.hashComponent = hashComponent;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        HashJsonSerialization hash;
        Optional<Hash> annotationHash = Optional.ofNullable(property).map(prop -> prop.getAnnotation(Hash.class));
        if (annotationHash.isPresent() && annotationHash.get().enableHash()) {
            hash = new HashJsonSerialization(new HashComponent());
        } else {
            hash = new HashJsonSerialization();
        }
        return hash;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        try {
            gen.writeString(hashComponent.hash(value));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
