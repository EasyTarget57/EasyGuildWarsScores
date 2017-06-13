package easy.guild.wars.streamable.ui;

import easy.guild.wars.streamable.Streamable;

/**
 * Superclass for other View Definitions. Should not have direct
 * implementations.
 */
public interface AbstractViewDefinition extends Streamable {
	String getName();

	ViewType getType();
}