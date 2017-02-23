package kafka.avro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class C extends BaseClass {

	private String s;

	private Integer b;
	
	private Boolean bool;
}
