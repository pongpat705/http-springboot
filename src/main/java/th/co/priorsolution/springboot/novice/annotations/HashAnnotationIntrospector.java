package th.co.priorsolution.springboot.novice.annotations;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import lombok.extern.slf4j.Slf4j;
import th.co.priorsolution.springboot.novice.serialization.HashJsonSerialization;

@Slf4j
public class HashAnnotationIntrospector extends NopAnnotationIntrospector {
    private static final long serialVersionUID = 1L;

    @Override
    public Object findSerializer(Annotated am) {
        Hash hashAnnotation = am.getAnnotation(Hash.class);
        if (hashAnnotation != null) {
            return HashJsonSerialization.class;
        }
        return null;
    }

}
