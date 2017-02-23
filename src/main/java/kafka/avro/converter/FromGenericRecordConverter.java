package kafka.avro.converter;

import kafka.avro.converter.util.ReflectionUtil;
import kafka.avro.converter.util.TypeMap;

import org.apache.avro.generic.GenericData.EnumSymbol;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;

public class FromGenericRecordConverter {

	public static final FromGenericRecordConverter INSTANCE = new FromGenericRecordConverter();

	private FromGenericRecordConverter() {

	}

	public Object toObject(EnumSymbol genericRecord) {
		Class<Enum> enumClazz = (Class<Enum>) ReflectionUtil.getClassTypeFromRecord(genericRecord);
		return Enum.valueOf(enumClazz, genericRecord.toString());
	}

	public Object toObject(GenericRecord genericRecord) {
		Object object = ReflectionUtil.getNewInstance(ReflectionUtil.getClassTypeFromRecord(genericRecord));// ReflectionUtil.getNewInstance(clazz);
		for (org.apache.avro.Schema.Field field : genericRecord.getSchema().getFields()) {
			Object value = genericRecord.get(field.name());
			String name = field.name();
			if (value == null)
				continue;
			if (TypeMap.isPrimitiveType(value))
				ReflectionUtil.setValue(object, name, value, ReflectionUtil.getFieldClassType(object.getClass(), name));
			else if (value instanceof Utf8) {
				value = new String(((Utf8) value).getBytes());
				ReflectionUtil.setValue(object, name, value, ReflectionUtil.getFieldClassType(object.getClass(), name));
			} else if (value instanceof GenericRecord) {
				value = toObject((GenericRecord) value);
				ReflectionUtil.setValue(object, name, value, ReflectionUtil.getFieldClassType(object.getClass(), name));
			} else if (value instanceof EnumSymbol) {
				ReflectionUtil.setValue(object, name, toObject((EnumSymbol) value));
			} else
				throw new RuntimeException("Value type is neither primitive nor GenericRecord , value class :"
						+ value.getClass());
		}
		return object;
	}
}
