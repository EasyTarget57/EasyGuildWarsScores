package easy.guild.wars.streamable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProvider {
	public static final Gson GSON = new GsonBuilder().setDateFormat("MM-dd yyyy").setLenient().create();

	public Gson getGson() {
		return GSON;
	}
}
