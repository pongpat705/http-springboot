package th.co.priorsolution.springboot.novice.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;

public class DateSerializer extends StdSerializer<LocalDate> {
    public DateSerializer() {
        this(null);
    }

    private static final long serialVersionUID = -4217554393942640626L;

    protected DateSerializer(Class<LocalDate> vc) {
        super(vc);
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.toString());
    }
}
