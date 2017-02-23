package kafka.avro.converter.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.util.Utf8;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

import kafka.avro.converter.entity.Type;

public class TypeMap {

	private static final Map<String, Type> typeMap = new HashMap<String, Type>();

	static {
		typeMap.put("int", Type.PRIMITIVE);
		typeMap.put("java.lang.Integer", Type.BOX_PRIMITIVE);
		typeMap.put("double", Type.PRIMITIVE);
		typeMap.put("java.lang.Double", Type.BOX_PRIMITIVE);
		typeMap.put("long", Type.BOX_PRIMITIVE);
		typeMap.put("java.lang.Long", Type.BOX_PRIMITIVE);
		typeMap.put("float", Type.BOX_PRIMITIVE);
		typeMap.put("java.lang.Float", Type.BOX_PRIMITIVE);
		typeMap.put("boolean", Type.BOX_PRIMITIVE);
		typeMap.put("java.lang.Boolean", Type.BOX_PRIMITIVE);
		typeMap.put("enum", Type.BOX_PRIMITIVE);
		typeMap.put("java.lang.String", Type.BOX_PRIMITIVE);
		typeMap.put("java.lang.Enum", Type.BOX_PRIMITIVE);
	}

	public static Boolean containsKey(String key) {
		return typeMap.containsKey(key);
	}

	public static Boolean isPrimitiveType(Object type) {
		if (type instanceof Enum) {
			return true;
		} 
		String typeString = type.getClass().getTypeName();
		return TypeMap.containsKey(typeString);
	}
}
