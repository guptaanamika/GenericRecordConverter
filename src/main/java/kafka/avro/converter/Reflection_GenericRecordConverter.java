package kafka.avro.converter;

import org.apache.avro.generic.GenericData.EnumSymbol;
import org.apache.avro.generic.GenericRecord;

public class Reflection_GenericRecordConverter implements GenericRecordConverter {

	public static final Reflection_GenericRecordConverter INSTANCE = new Reflection_GenericRecordConverter();

	private Reflection_GenericRecordConverter() {

	}

	@Override
	public GenericRecord toGenericRecord(Object object) {
		return ToGenericRecordConverter.INSTANCE.toGenericRecord(object);
	}

	@Override
	public Object toObject(GenericRecord object) {
		return FromGenericRecordConverter.INSTANCE.toObject(object);
	}

	@Override
	public Object toObject(EnumSymbol object) {
		return FromGenericRecordConverter.INSTANCE.toObject(object);
	}

}
