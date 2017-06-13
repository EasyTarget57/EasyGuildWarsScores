package easy.guild.wars.streamable.ui;

abstract class ViewDefinitionBaseImpl implements AbstractViewDefinition {
	private final String name;
	private final ViewType type;

	ViewDefinitionBaseImpl(String name, ViewType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ViewType getType() {
		return type;
	}
}