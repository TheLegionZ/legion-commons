package br.com.thelegion.legioncommons.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.experimental.Delegate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Supplier;

public class JsonProvider {

	private final Gson gson;

	public JsonProvider(Gson gson) {
		this.gson = gson;
	}

	public JsonProvider(Supplier<Gson> gsonSupplier) {
		this(gsonSupplier.get());
	}

	public JsonProvider(GsonBuilder builder) {
		this(builder.create());
	}

	public <T> T fromJson(ResultSet set, String column, Class<T> type) throws SQLException {
		return fromJson(set.getString(column), type);
	}

	public <T> T fromJson(ResultSet set, String column, TypeToken<T> type) throws SQLException {
		return fromJson(set.getString(column), type.getType());
	}

	@Delegate
	public Gson getGson() {
		return gson;
	}
}
