package kafka.avro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anamika.gupta created_on : 21-Feb-2017
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class B extends BaseClass {

	private String s;

	private Integer b;
}
