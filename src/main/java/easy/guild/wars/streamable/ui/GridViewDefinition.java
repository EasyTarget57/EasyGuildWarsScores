package easy.guild.wars.streamable.ui;

import java.util.List;

/**
 * Definition of a grid.
 *
 * @author dean
 */
public interface GridViewDefinition extends AbstractViewDefinition {
	List<String> getColumnNames();
}