package kafka.avro.entity;

import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anamika.gupta created_on : 20-Feb-2017
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperBase {

	private String superFieldString;

	@Nullable
	private TypeEnumTest superEnumType;

	private BaseClass superAbstract;

}
