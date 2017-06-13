package easy.guild.wars.streamable;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;

public interface Streamable {
	public static final TypeToken<Map<String, Object>> MAP_STRING_OBJECT_TYPETOKEN = new TypeToken<Map<String, Object>>() {
	};
	public static final TypeToken<Map<String, String>> MAP_STRING_STRING_TYPETOKEN = new TypeToken<Map<String, String>>() {
	};
	public static final TypeToken<List<String>> LIST_STRING_TYPETOKEN = new TypeToken<List<String>>() {
	};
	public static final Type MAP_STRING_STRING_TYPE = MAP_STRING_STRING_TYPETOKEN.getType();
	public static final Type MAP_STRING_OBJECT_TYPE = MAP_STRING_OBJECT_TYPETOKEN.getType();
	public static final Type LIST_STRING_TYPE = LIST_STRING_TYPETOKEN.getType();

	public Object toJson();
}
