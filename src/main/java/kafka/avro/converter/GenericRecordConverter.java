package kafka.avro.converter;

import org.apache.avro.generic.GenericData.EnumSymbol;
import org.apache.avro.generic.GenericRecord;

public interface GenericRecordConverter {

	public GenericRecord toGenericRecord(Object object);

	public Object toObject(GenericRecord object);

	public Object toObject(EnumSymbol object);
}
