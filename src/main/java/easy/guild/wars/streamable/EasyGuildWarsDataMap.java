package easy.guild.wars.streamable;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import easy.guild.wars.streamable.results.EnhancedResultContainer;
import easy.guild.wars.streamable.ui.GridColumnDefinitions;
import easy.guild.wars.streamable.ui.ViewDefinitionFactory;

public class EasyGuildWarsDataMap implements Streamable {

	private final GridColumnDefinitions columns;

	private final ViewDefinitionFactory views;

	private final EnhancedResultContainer results;

	public EasyGuildWarsDataMap(GridColumnDefinitions columns, ViewDefinitionFactory views,
			EnhancedResultContainer results) {
		this.columns = columns;
		this.views = views;
		this.results = results;
	}

	@Override
	public Object toJson() {
		Map<String, Object> map = ImmutableMap.of("columns", columns.toJson(), "views", views.toJson(), "results",
				results.toJson());
		return map;
	}

}
