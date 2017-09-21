package util;

import kr.co.shineware.util.common.model.Pair;
import org.apache.commons.configuration.*;

import java.util.List;



public class PoSTagger {
	// Config
	private static Configuration config;

	// Delimiters
	private static String posDelimiter;
	private static String itemDelimiter;


	public PoSTagger() {
		try {
			config = new PropertiesConfiguration("config.properties");
			posDelimiter = config.getString("delimiter.pos");
			itemDelimiter = config.getString("delimiter.item");
		}
		catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public PoSTagger(String posDelimiter, String itemDelimiter) {
		this();
		this.setDelimiter(posDelimiter, itemDelimiter);
	}


	public void setDelimiter(String posDelimiter, String itemDelimiter) {
		PoSTagger.posDelimiter = posDelimiter;
		PoSTagger.itemDelimiter = itemDelimiter;
	}

	public String tag(List<Pair<String, String>> inputList) {
		String result = "";
		String word;

		for (Pair item : inputList) {
			word = (config.getBoolean("remove.space")) ? String.valueOf(item.getFirst()).replace(" ", "") : String.valueOf(item.getFirst());
			result += word + posDelimiter + item.getSecond() + itemDelimiter;
		}

		return result;
	}

}
