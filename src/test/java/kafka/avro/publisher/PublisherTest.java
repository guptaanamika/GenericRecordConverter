package kafka.avro.publisher;

import kafka.avro.entity.A;
import kafka.avro.entity.C;
import kafka.avro.entity.SuperBase;
import kafka.avro.entity.TypeEnumTest;
import kafka.avro.producer.Publisher;

import org.apache.avro.reflect.ReflectData;

/**
 * @author anamika.gupta created_on : 17-Feb-2017
 **/
public class PublisherTest {

	public static void main(String[] args) {
		Publisher publisher = new Publisher();

		A a = new A();
		a.setBaseString("abc");
		a.setA(10);
		a.setS("asdadasdsfdwfenjssdjnskjcfbsdicsdbcsjdcbdsvujgdusfgwufgwufbgwucbwdecnxbswidchciosndicdbnciuwdbwiuyr83r7y639847329hdwkjbxsicisducdsifi8edueceucveuvcuejfvdjcveducedc");
		SuperBase paramVelocity = new SuperBase("aaa", TypeEnumTest.X, a);

		System.out.println(ReflectData.get().getSchema(SuperBase.class));
		System.out.println(paramVelocity.toString());
		publisher.publish("23Feb_Test", paramVelocity);

		C c = new C();
		c.setBool(true);
		c.setB(10);
		c.setBaseString("baseString");
		c.setS("asdadasdsfdwfenjssdjnskjcfbsdicsdbcsjdcbdsvujgdusfgwufgwufbgwucbwdecnxbswidchciosndicdbnciuwdbwiuyr83r7y639847329hdwkjbxsicisducdsifi8edueceucveuvcuejfvdjcveducedc");
		publisher.publish("23Feb_Test", new SuperBase("aaa", TypeEnumTest.X, c));
	}
}
