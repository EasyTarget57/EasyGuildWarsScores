package easy.guild.wars.streamable.ui;

import java.util.Optional;

import easy.guild.wars.streamable.util.StreamableMap;

/**
 * A Map with the predefined Columns.
 *
 * @author dean
 *
 */
public class GridColumnDefinitions extends StreamableMap<GridColumnDefinition> {

	public static final GridColumnDefinition AVG_ATTACKED = new GridColumnDefinitionImpl("Avg might attacked",
			Optional.empty());
	public static final GridColumnDefinition GAINED_LOST = new GridColumnDefinitionImpl("Percentage", Optional.empty());
	public static final GridColumnDefinition PERCENT = new GridColumnDefinitionImpl("Percentage", Optional.of(2));
	public static final GridColumnDefinition TARGET_POINTS = new GridColumnDefinitionImpl("Target points",
			Optional.empty());
	public static final GridColumnDefinition POINTS = new GridColumnDefinitionImpl("Points", Optional.empty());
	public static final GridColumnDefinition MIGHT = new GridColumnDefinitionImpl("Might", Optional.empty());
	public static final GridColumnDefinition NAME = new GridColumnDefinitionImpl("Name", Optional.empty());

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private GridColumnDefinitions() {
		super();
	}

	public static final GridColumnDefinitions instance() {
		GridColumnDefinitions map = new GridColumnDefinitions();
		map.put(AVG_ATTACKED.getName(), AVG_ATTACKED);
		map.put(GAINED_LOST.getName(), GAINED_LOST);
		map.put(PERCENT.getName(), PERCENT);
		map.put(TARGET_POINTS.getName(), TARGET_POINTS);
		map.put(POINTS.getName(), POINTS);
		map.put(MIGHT.getName(), MIGHT);
		map.put(NAME.getName(), NAME);
		return map;
	}
}
