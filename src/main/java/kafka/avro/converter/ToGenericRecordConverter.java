package kafka.avro.converter;

import java.lang.reflect.Field;

import kafka.avro.converter.util.ReflectionUtil;
import kafka.avro.converter.util.SchemaGenerator;
import kafka.avro.converter.util.TypeMap;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

public class ToGenericRecordConverter {

	public static final ToGenericRecordConverter INSTANCE = new ToGenericRecordConverter();

	private ToGenericRecordConverter() {

	}

	public GenericRecord toGenericRecord(Object object) {
		GenericRecord genericRecord = null;
		if (!(object instanceof Enum))
			genericRecord = new GenericData.Record(SchemaGenerator.getSchema(object));
		else
			genericRecord = (GenericRecord) new GenericData.EnumSymbol(SchemaGenerator.getSchema(object), object);
		populateForEachField(object.getClass(), object, genericRecord);
		populateForEachFieldForSuperClass(object.getClass(), object, genericRecord);
		return genericRecord;
	}

	private void populateForEachFieldForSuperClass(Class<?> clazz, Object object, GenericRecord genericRecord) {
		Class<?> superClazz = object.getClass().getSuperclass();
		while (superClazz != null) {
			populateForEachField(superClazz, object, genericRecord);
			superClazz = superClazz.getSuperclass();
		}
	}

	private void populateForEachField(Class<?> clazz, Object object, GenericRecord genericRecord) {
		for (Field field : clazz.getDeclaredFields()) {
			String name = field.getName();
			Object value = ReflectionUtil.fetchValue(object, name);
			if (value == null)
				continue;
			if (value instanceof Enum) {
				genericRecord.put(name, new GenericData.EnumSymbol(SchemaGenerator.getSchema(value), value));
			} else if (TypeMap.isPrimitiveType(value))
				genericRecord.put(name, value);
			else
				genericRecord.put(name, toGenericRecord(value));
		}
	}
}
