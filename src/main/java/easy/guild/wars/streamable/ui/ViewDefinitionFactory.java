package easy.guild.wars.streamable.ui;

import java.util.Arrays;

import easy.guild.wars.streamable.util.StreamableMap;

public class ViewDefinitionFactory extends StreamableMap<AbstractViewDefinition> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final GridViewDefinition GUILD_WARS_GRID = new GridViewDefinitionImpl("guildWarsGrid", ViewType.GRID,
			Arrays.asList(GridColumnDefinitions.NAME.getName(), GridColumnDefinitions.MIGHT.getName(),
					GridColumnDefinitions.POINTS.getName(), GridColumnDefinitions.TARGET_POINTS.getName(),
					GridColumnDefinitions.PERCENT.getName(), GridColumnDefinitions.AVG_ATTACKED.getName()));

	private ViewDefinitionFactory() {
		super();
	}

	public static ViewDefinitionFactory getInstance() {
		ViewDefinitionFactory map = new ViewDefinitionFactory();
		map.put(GUILD_WARS_GRID.getName(), GUILD_WARS_GRID);
		return map;
	}
}
