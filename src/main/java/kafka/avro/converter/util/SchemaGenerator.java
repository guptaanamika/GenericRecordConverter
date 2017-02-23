package kafka.avro.converter.util;

import org.apache.avro.reflect.ReflectData;

public class SchemaGenerator {

	public static org.apache.avro.Schema getSchema(Object t) {
		return ReflectData.get().getSchema(t.getClass());
	}
}
