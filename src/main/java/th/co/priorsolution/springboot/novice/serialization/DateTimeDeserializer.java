package th.co.priorsolution.springboot.novice.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	public DateTimeDeserializer() {
		this(null);
	}

	protected DateTimeDeserializer(Class<?> vc) {
		super(vc);
	}

	private static final long serialVersionUID = 804777737972906663L;

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String date = p.getText();
		DateTimeFormatter dft = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(date, dft);
		return dateTime;
	}

}
