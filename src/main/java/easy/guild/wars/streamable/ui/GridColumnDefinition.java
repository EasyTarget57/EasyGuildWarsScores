package easy.guild.wars.streamable.ui;

import java.util.Optional;

import easy.guild.wars.streamable.Streamable;

public interface GridColumnDefinition extends Streamable {
	String getName();

	Optional<Integer> getMaxDigitsAfterComma();

	default String getCssClass() {
		return "grid-column-" + getName();
	}
}