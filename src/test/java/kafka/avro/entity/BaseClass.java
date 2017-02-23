package kafka.avro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.avro.reflect.Nullable;
import org.apache.avro.reflect.Union;

/**
 * @author anamika.gupta created_on : 17-Feb-2017
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Union(value = { A.class, B.class, C.class })
public abstract class BaseClass {

	private String baseString;

	@Nullable
	private TypeEnumTest baseEnumType;
}
