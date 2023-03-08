import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Kodu2b {

	// Kolm massiivi, mis täidetakse failist loetud andmetega
	static String[] kuupäev;
	static String[] kellaaeg;
	static double[] temperatuur;

	 public static void loeAndmed(String failitee) {
	 	//loeb andmed failist nimega "failitee"
		try {
			// Loeme failist kõik read, eeldame et faili kodeering on UTF-8
			List<String> read = Files.readAllLines(Path.of(failitee), StandardCharsets.UTF_8);

			// Määrame massiivide pikkuse
			kuupäev = new String[read.size()];
			kellaaeg = new String[read.size()];
			temperatuur = new double[read.size()];
			for (int i = 0; i < read.size(); i++) {
				// Hakime read tühikute põhjal
				String[] jupid = read.get(i).split(" ");
				// Määrame hakitud jupid vastavatesse massiividesse
				kuupäev[i] = jupid[0];
				kellaaeg[i] = jupid[1];
				temperatuur[i] = Double.parseDouble(jupid[2]);
			}
		} catch (IOException e) {
			// Faili ei leitud või lugemisel esines viga - viskame erindi ja lõpetame töö
			throw new RuntimeException("Faili " + failitee + " lugemisel tekkis viga", e);
		}
	}//loeAndmed

	/**
	 * Näidismeetod (mitte kasutamiseks): leiab antud ajal mõõdetud temperatuuri.
	 *
	 * @param päev Kuupäev kujul aaaa-kk-pp.
	 * @param kell Kellaaeg kujul tt:mm:ss.
	 * @return Temperatuur kuupäeval <b>päev</b> ajal <b>kell</b>, NaN kui andmetest puudu.
	 */
	static double temperatuurValitudPäevalJaKellal(String päev, String kell) {
		for (int i = 0; i < temperatuur.length; i++) {
			if (kuupäev[i].equals(päev) && kellaaeg[i].equals(kell))
				return temperatuur[i];
		}
		return Double.NaN;
	}

	public static void main(String[] args) {
		// Näidis
		// Failitee jooksvas kaustas. IntelliJ puhul otsitakse faili eelkõige PROJEKTI kaustast, mitte SRC kaustast
	    // Kui fail asetsetb src kaustas siis peaks failitee olema "src/ilmAegTemp_2022.txt"
		loeAndmed("ilmAegTemp_2022.txt");//loeb andmed etteantud failist
		System.out.println("Kodutöö nr 2b");
		System.out.println();

		System.out.println("Kontrolliks, massiivide algused:");
		System.out.printf("%s\t%s\t %s%n", "kuupäev[]", "kellaaeg[]", "temperatuur[]");
		for (int i = 0; i < 10; i++)
			System.out.printf("%s\t%s\t%.11f%n", kuupäev[i], kellaaeg[i], temperatuur[i]);

		System.out.println("   ...   \t".repeat(3) + "\n");

		String sünnipäev = "2022-08-29";
		String kell = "12:00:00";
		double temp = temperatuurValitudPäevalJaKellal(sünnipäev, kell);

		System.out.printf("Minu sünnipäeval aastal 2022 (%s) oli keskpäevane temperatuur: ", sünnipäev);
		if (Double.isNaN(temp))
			System.out.printf("- %nAntud aega: %s %s ei ole andmestikus!%n%n", sünnipäev, kell);
		else
			System.out.printf("%.1f kraadi.%n%n", temp);
	}

	// Õpilase poolt teostatavad meetodid:

	public static String vaheMinMaxVähimPäevas() {
		// Tagastada selle päeva kuupäev, kus vähima ja suurima temperatuuri vahe oli väikseim.
		// Tagastuse näide: "2022-08-02"
		return null;
	}

	public static String vaheMinMaxSuurimPäevas() {
		// Tagastada selle päeva kuupäev, kus vähima ja suurima temperatuuri vahe oli suurim.
		// Tagastuse näide: "2022-08-08"
		return null;
	}

	public static int vaheMinMaxVähimKuus() {
		// Tagastada selle kuu number (jaanuar: 1, veebruar: 2, ...),
		// kus vähima ja suurima temperatuuri vahe oli väikseim.
		// Tagastuse näide: 12
		return 0;
	}

	public static int vaheMinMaxSuurimKuus() {
		// Tagastada selle kuu number (jaanuar: 1, veebruar: 2, ...),
		// kus vähima ja suurima temperatuuri vahe oli suurim.
		// Tagastuse näide: 1
		return 0;
	}

	public static String suurimMiinimum() {
		// Tagastada selle päeva kuupäev, millel oli kõrgeim miinimumtemperatuur
		// võrreldes teiste päevade miinimumtemperatuuridega.
		// Tagastuse näide: "2022-07-07"
		return null;
	}
}


