package kafka.avro.converter.util;

import org.apache.avro.generic.GenericData.EnumSymbol;
import org.apache.avro.generic.GenericRecord;

public class ReflectionUtil {

	public static Object fetchValue(Object t, String fieldName) {
		try {
			return t.getClass().getMethod(getMethodName(fieldName), null).invoke(t, null);
		} catch (Exception e) {
			throw new RuntimeException("Exception while fetching value for field:" + fieldName + " from class:"
					+ t.getClass() + " msg:" + e.getMessage());
		}
	}

	public static Object setValue(Object t, String fieldName, Object value) {
		try {
			return t.getClass().getMethod(setMethodName(fieldName), value.getClass()).invoke(t, value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception while setting value for field:" + fieldName + " from class:"
					+ t.getClass() + " msg:" + e.getMessage());

		}
	}

	public static Object setValue(Object t, String fieldName, Object value, Class<?> valueClass) {
		try {
			return t.getClass().getMethod(setMethodName(fieldName), valueClass).invoke(t, value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception while setting value for field:" + fieldName + " from class:"
					+ t.getClass() + " msg:" + e.getMessage());

		}
	}

	public static String getMethodName(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	public static String setMethodName(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	public static Object getNewInstance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Error while creating new Instance for class:" + clazz.getName() + " msg:"
					+ e.getMessage());
		}
	}

	public static Class<?> getClassTypeFromRecord(Object value) {
		try {
			if (value instanceof GenericRecord)
				return Class.forName(((GenericRecord) value).getSchema().getFullName());
			else if (value instanceof EnumSymbol)
				return Class.forName(((EnumSymbol) value).getSchema().getFullName());
			else
				throw new RuntimeException("Other value type has not been handled yet");
		} catch (Exception e) {
			throw new RuntimeException("Error while fetching classType from GenericRecord :" + value + " msg:"
					+ e.getMessage());
		}
	}

	public static Class<?> getFieldClassType(Class<?> clazz, String name) {
		try {
			Class<?> type = null;
			try {
				type = clazz.getDeclaredField(name).getType();
			} catch (NoSuchFieldException e) {

			}
			if (type == null && clazz.getSuperclass() != null)
				type = getFieldClassType(clazz.getSuperclass(), name);

			if (type == null)
				throw new NoSuchFieldException();
			return type;
		} catch (Exception e) {
			throw new RuntimeException("Error while fetching field class type for objectClass :" + clazz
					+ " and fieldName:" + name + " msg:" + e.getMessage(), e);
		}
	}
}
