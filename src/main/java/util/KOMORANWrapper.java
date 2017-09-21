package util;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.util.common.model.Pair;
import org.apache.commons.configuration.*;

import java.util.List;



public class KOMORANWrapper {
	// Config
	private static Configuration config;

	// KOMORAN Instance
	private static Komoran komoran;


	public KOMORANWrapper() {
		try {
			config = new PropertiesConfiguration("config.properties");

			System.out.print("Loading model... ");
			this.loadModel(config.getString("model.type"));
			System.out.println("DONE");
		}
		catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public KOMORANWrapper(String modelType) {
		this();
		this.loadModel(modelType);
	}


	public void loadModel(String modelType) {
		komoran = ("full".equals(modelType.toLowerCase())) ? new Komoran(DEFAULT_MODEL.FULL) : new Komoran(DEFAULT_MODEL.LIGHT);
	}

	public List<Pair<String, String>> analyze(String queryString) {
		return komoran.analyze(queryString.trim()).getList();

	}

}