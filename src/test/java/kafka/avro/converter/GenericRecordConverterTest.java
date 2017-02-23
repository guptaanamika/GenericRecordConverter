package kafka.avro.converter;

import kafka.avro.converter.util.SchemaGenerator;
import kafka.avro.entity.A;
import kafka.avro.entity.SuperBase;
import kafka.avro.entity.TypeEnumTest;

import org.apache.avro.generic.GenericRecord;

public class GenericRecordConverterTest {

	public static void main(String[] args) throws ClassNotFoundException {

		Reflection_GenericRecordConverter conv = Reflection_GenericRecordConverter.INSTANCE;

		float s = 10;
		A a = new A();
		a.setBaseString("abc");
		a.setA(10);
		a.setBaseEnumType(TypeEnumTest.X);
		SuperBase t = new SuperBase("aaa", TypeEnumTest.Z, a);
		System.out.println(t);
		System.out.println(SchemaGenerator.getSchema(t));
		GenericRecord r = conv.toGenericRecord(t);
		System.out.println(r);
		System.out
				.println("==========================================================================================");
		System.out.println(conv.toObject(r));
	}
}
