/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 1
 * Teema: Kodutöö 1a
 *
 * Autor: Markkus Koddala
 *
 **********************************/
public class Kodu1a {
    public static void main(String[] args) {
        System.out.println(algarvuRingideArv(999999));
        algarvuRingid5Suurimat(999999);
    }

    /**
     * Järjendi summa leidmine rekursiivselt.
     *
     * @param arv Antud arv, millest alapoole hakkab programm liikuma.
     * @ Järjendi a algusosa elementide summa a[0]+a[1]+...+a[n-1]
     */
    public static void ekraanileAlgarvuRingideArv(int arv) {
        System.out.println("Antud lähtekoht: " + arv);
        System.out.println("Väiksemaid erinevaid algarvuringe: " + algarvuRingideArv(arv));
    }

    /**
     * A
     * Väljastab 5 suurimat algarvuringi, mis on väiksemad kui arv ise.
     *
     * @param arv Antud arv, millest 5 väiksemat algarvuringi kuvab ekraanile.
     */
    public static void algarvuRingid5Suurimat(int arv) {
        System.out.println("Antud lähtekoht: " + arv);
        System.out.println("Leitud algarvuringide suurimad: ");
        int prinditudRinge = 0;
        for (arv = arv % 2 == 0 ? arv - 1 : arv; 9 < arv && prinditudRinge < 5; arv -= 2) { //algusdirektiiv kontrollib, kas tegemist on paarisarvuga, kui ei ole, siis muudab selle, sest kõik paarisarvud on kordarvud
            if (kasVastavOnAlgarv(arv) && kasOnAlgarvuringB(arv)) { //kontrollib kahte tingimust - kas arv on algarv ja kas on algarvuring
                prinditudRinge++; //kui on, siis lisab muutujale prinditudRinge +1
                System.out.println(arv); //kuvab arvu ekraanile, mis vastab tingimustele
            }
        }
    }

    /**
     * Leiab kõik algarvuringid arvust "arv" väiksemad ja loendab need kokku.
     *
     * @param arv Antud arv, millest väiksemaid algarvuringe hakkab lugema.
     * @return tagastab arvu, kui palju on algarvuringe
     */
    public static int algarvuRingideArv(int arv) {
        int ringeKokku = 0;
        for (arv = arv % 2 == 0 ? arv - 1 : arv; 9 < arv; arv -= 2) { // sarnaselt eelmisele meetodile, kontrollib ka arvu omadust(paaris või paaritu)
            if (kasVastavOnAlgarv(arv) && kasOnAlgarvuRing(arv)) ringeKokku++;
        }
        return ringeKokku;
    }

    /**
     * Meetod kontrollib, kas arv on erinev algarvuring. Selleks ta hakkab numbreid edasi liigutama kuni ring on täis (991 -> 199 -> 919).
     * Igal juhul kontrollib, kas arv on jätkuvalt algarv ja ega tõstetud arv pole suurem kui meetodile sisendina antud arv.
     * (Viimane on selleks, et arvesse laheks ainult suurim algarvuring)
     *
     * @param arv Antud arv, mida meetod hakkab kontrollima.
     * @return tõeväärtuse, kas on erinev algarvuring või ei.
     */

    public static boolean kasOnAlgarvuRing(int arv) {
        int jarkudeArv = (int) (Math.log10(arv)); // arvutab, mitu arvu on numbris
        int umberTostetud = kumnendnumbriTostmine(arv); //kumnendnumbriTostmine meetodis tõstetakse arv ühe koha võrra paremale ja umberTostetud omistab selle väärtuse
        for (int i = 0; i < jarkudeArv; i++) {
            if (kasVastavOnAlgarv(umberTostetud) && arv >= umberTostetud)
                umberTostetud = kumnendnumbriTostmine(umberTostetud); //tõstetakse uuesti arvu
            else
                return false; //kui arv pole algarv või "liigutatud" arv on suurem, kui sisendina antud arv, siis tagastab false tõeväärtuse
        }
        return true;
    }

    public static boolean kasOnAlgarvuringB(int n){
        String s = Integer.toString(n);
        if (!kasVastavOnAlgarv(n)) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            String rotated = s.substring(i) + s.substring(0, i);
            int rotatedInt = Integer.parseInt(rotated);
            if (!kasVastavOnAlgarv(rotatedInt)) {
                return false;
            } else if (!(n>=rotatedInt)) return false;
        }
        return true;
    }

    /**
     * Meetod kontrollib, kas arv on algarvuringile vastavate omadustega ehk koosneb ainult arvudest 1, 3, 7, 9 (https://mathworld.wolfram.com/CircularPrime.html) ja kas sellisel juhul on ikkagi algarv.
     *
     * @param arv Antud arv, mida meetod hakkab kontrollima.
     * @return tõeväärtuse, kas on algarvuringi omadustele vastav algarv või ei.
     */
    public static boolean kasVastavOnAlgarv(int arv) {
        String arvStringina = Integer.toString(arv); // muudab täisarvu sõneks
        if (arvStringina.contains("0") || arvStringina.contains("2") || arvStringina.contains("4") || arvStringina.contains("6") || arvStringina.contains("8") || arvStringina.contains("5"))
            return false;
        for (int i = 3; i < Math.sqrt(arv) + 1; i += 2) { // kontrollib, kas arv on algarv, selleks vaatab üle kõik võimalikud jagajad kuni arvu ruutjuureni.
            if (arv % i == 0)
                return false; //juhul, kui jagatava ja jagaja jagatis on 0, siis arv jagub ja tegemist pole algarvuga
        }
        return true;
    }

    /**
     * Siin meetodis toimub arvu numbrite tõstmine ühe võrra paremale.
     *
     * @param arv Arv, mida meetod hakkab tõstma.
     * @return tagastab ühe koha võrra paremale tõstetub numbri
     */
    public static int kumnendnumbriTostmine(int arv) {
        return arv % 10 * (int) Math.pow(10, (int) Math.log10(arv)) + arv / 10;
    }
}