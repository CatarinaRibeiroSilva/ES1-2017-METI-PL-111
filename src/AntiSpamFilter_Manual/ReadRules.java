package AntiSpamFilter_Manual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

public class ReadRules {
	private HashMap<String, String> rulesMap;
	public ArrayList<String> rulesList = new ArrayList<String>();
	public ArrayList<String> weightList = new ArrayList<String>();
	private static String rulesPath;

	/**
	 * Depois de ter recebido o nome do arquivo de regras, adicionado como regras e os pesos.
	 * Retorna um hashMap com as regras como valores e valores como peso.
	 * 
	 */
	public HashMap<String, String> read(String path) throws FileNotFoundException {
		rulesPath = path;
		String line;
		FileReader reader = new FileReader(path);
		rulesMap = new HashMap<String, String>();
		BufferedReader in = new BufferedReader(reader);
		try {
			while ((line = in.readLine()) != null) {
				String[] tokens = line.split("	");
				String rule = tokens[0];
				String weight = tokens[1];

				rulesMap.put(rule, weight);
			}

			in.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '");

		}
		return rulesMap;
	}

	/**
	 * This method is used to save as rules after they have been changed
	 */
	public void WriterRules(JTextArea area, JTextArea weight, String rulesPath) {
		List<String> rules;
		List<String> values;
		HashMap<String, Integer> rulesMap = new HashMap<String, Integer>();
		rules = Arrays.asList(area.getText().split("\n"));
		values = Arrays.asList(weight.getText().split("\n"));

		for (int i = 0; i < rules.size(); i++) {
			int value = Integer.parseInt(values.get(i));
			String rule = rules.get(i);
			rulesMap.put(rule, value);
		}

		try (BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(rulesPath), "utf-8"))) {
			for (Map.Entry<String, Integer> map : rulesMap.entrySet()) {
				String chave = map.getKey();
				int valor = map.getValue();
				writer.write(chave + "	" + valor);
				writer.newLine();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * return rulesPath
	 * 
	 */
	public String getRulesPath() {
		return rulesPath;
	}

}