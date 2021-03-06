package AntiSpamFilter_Manual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JTextField;

public class ReadMessages {

	private List<String> message = null;
	public List<List<String>> allMessageSPAM;
	public List<List<String>> allMessageHam;
	private String rulesPath = " ";
	private int FP;
	private int FN;

	
	
	/**
	 * This method after entering the filename, in which case it is the Spam file.
	 *It reads each line of the file and adds the contents of each line to an array, 
	 *and through all those array will be added another array designated by allMessageHam. 
	*/
	public void lerSpam(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		allMessageSPAM = new ArrayList<List<String>>();
		try {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tokens = line.split("	");
				message = new ArrayList<String>();
				for (int n = 0; n < tokens.length; n++) {
					message.add(tokens[n]);
				}
				allMessageSPAM.add(message);
			}
		} finally {
			scanner.close();

		}

	}
	
	
	
	/**
	 * This method after entering the filename, in which case it is the Ham file.
	 *It reads each line of the file and adds the contents of each line to an array, 
	 *and through all those array will be added another array designated by allMessageSpam. 
	*/
	
	public void lerHam(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		allMessageHam = new ArrayList<List<String>>();
		try {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tokens = line.split("	");
				message = new ArrayList<String>();
				for (int n = 0; n < tokens.length; n++) {
					message.add(tokens[n]);
				}
				allMessageHam.add(message);
			}
		} finally {
			scanner.close();

		}

	}

	/**
	 * In this method it calculates the FN
	 *
	 */
	public void calcularFN(JTextField tx) throws FileNotFoundException {

		FN = 0;
		int soma;

		for (int n = 0; n < allMessageSPAM.size(); n++) {
			soma = 0;
			for (int i = 1; i < allMessageSPAM.get(n).size(); i++) {
				soma = getValueOfMap(readRulesAndValues(), allMessageSPAM.get(n).get(i)) + soma;
			}

			if (soma < 5) {
				FN++;
			}
			String fn = FN + " ";
			tx.setText(fn);
		}
		System.out.println("Falsos Negativos-Spam : " + FN);
	}

	/**
	 * In this method it calculates the FP for the Automatic part
	 * 
	 */
	public int calcularFNAuto(double[] x) throws FileNotFoundException {

		FN = 0;
		int soma=0;;
		int a=0;
		Map<String, Integer> map= readRulesAndValues();
		Map<String, Integer> new_map = new HashMap<String, Integer>();
		for(String rule: map.keySet()) {
			new_map.put(rule, (int)x[a]);
			a++;
		}
		for (int n = 0; n < allMessageSPAM.size(); n++) {
			soma = 0;
			for (int i = 1; i < allMessageSPAM.get(n).size(); i++) {
				soma = getValueOfMap(new_map, allMessageSPAM.get(n).get(i)) + soma;
			}

			if (soma < 5) {
				FN++;
			}
		}
		return soma;
	}

	
	/**
	 * In this method it calculates the FP
	 */
	public void calcularFP(JTextField tx) throws FileNotFoundException {

		FP = 0;
		System.out.println("maasassas: " + allMessageHam.size());
		for (int n = 0; n < allMessageHam.size(); n++) {
			int soma = 0;
			for (int i = 1; i < allMessageHam.get(n).size(); i++) {
				soma = getValueOfMap(readRulesAndValues(), allMessageHam.get(n).get(i)) + soma;
			}
			if (soma > 5) {
				FP++;
			}
			String fp = FP + "";
			tx.setText(fp);

		}
		System.out.println("Falsos Positivos-Ham : " + FP);
	}
	/**
	 * return FP;
	 */

	public int getFP() {
		return FP;
	}

	/**
	 * return FN;
	 */
	public int getFN() {
		return FN;
	}

	
	/**
	 * 
	 *In this method it calculates the FN for the Automatic part
	 */
	public int calcularFPAuto(double[] x) throws FileNotFoundException {

		FN = 0;
		int soma=0;;
		int a=0;
		Map<String, Integer> map= readRulesAndValues();
		Map<String, Integer> new_map = new HashMap<String, Integer>();
		for(String rule: map.keySet()) {
			new_map.put(rule, (int)x[a]);
			a++;
		}

		for (int n = 0; n < allMessageHam.size(); n++) {
			soma = 0;
			for (int i = 1; i < allMessageHam.get(n).size(); i++) {
				soma = getValueOfMap(new_map, allMessageHam.get(n).get(i)) + soma;
			}

			if (soma < 5) {
				FN++;
			}
		}
		return soma;
	}
/**
 * With this method you get the values ​​of the existing weight in the file Rules
 *
 */
	public int getValueOfMap(Map<String, Integer> rulesMap, String key) {
		for (Map.Entry<String, Integer> map : rulesMap.entrySet()) {
			if (map.getKey().equals(key)) {
				return map.getValue();
			}
		}
		return 0;
	}

	
	/**
	 * This method is used to read the Rules file
	 */
	public HashMap<String, Integer> readRulesAndValues() throws FileNotFoundException {
		HashMap<String, Integer> map = new HashMap<>();
		ReadRules rRules = new ReadRules();

		Scanner scanner = new Scanner(new File(rRules.getRulesPath()));
		try {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tokens = line.split("	");
				map.put(tokens[0], Integer.parseInt(tokens[1]));
			}
		} finally {
			scanner.close();
		}
		return map;
	}
/**
 * return rulesPath
 */
	public String getRulesPath() {
		return rulesPath;
	}
	/**
	 *edit rulesPath
	 */
	public void setRulesPath(String rulesPath) {
		this.rulesPath = rulesPath;
	}

}
