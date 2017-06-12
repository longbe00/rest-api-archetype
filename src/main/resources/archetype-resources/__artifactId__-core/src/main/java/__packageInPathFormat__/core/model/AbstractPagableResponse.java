/**
 * 
 */
package ${package}.core.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mike
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractPagableResponse<M> {

	private List<M> data;
	private PageInfo pageInfo;

}
