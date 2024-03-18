import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Cuvant {
    private String cuvant;

    // Constructor cu un singur parametru
    public Cuvant(String cuvant) {
        this.cuvant = cuvant;
    }

    // Metoda pentru afișarea cuvântului
    public void afisareCuvant() {
        System.out.println("Cuvant: " + cuvant);
    }

    // Supraîncărcarea metodei pentru afișarea cuvântului în litere mari
    public void afisareCuvant(boolean uppercase) {
        if (uppercase) {
            System.out.println("Cuvant (uppercase): " + cuvant.toUpperCase());
        } else {
            afisareCuvant();
        }
    }

    // supraincarcare a metodei pentru afișarea unui mesaj
    public void afisareCuvant(String mesaj) {
        System.out.println(mesaj + ": " + cuvant);
    }

    // Metoda de căutare a subșirului în cuvânt cu expresii regulate
    public boolean cautaSubsir(String subsir) {
        // Adăugăm \\b la început și la sfârșit pentru a indica limita cuvântului
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(subsir) + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cuvant);
        return matcher.find();
    }



}

class Pagina extends Cuvant {
    private Cuvant[] cuvinte;

    // Constructor cu un singur parametru
    public Pagina(Cuvant[] cuvinte) { //declaram clasa pagina
        super(""); // Apelam constr clasei de baza (Cuvant)
        this.cuvinte = cuvinte;
    }

    // Suprascrierea metodei pentru afișarea cuvintelor de pe pagina
    @Override
    public void afisareCuvant() { //Prin suprascriere, se înlocuiește comportamentul metodei din clasa Cuvant
        System.out.println("Cuvinte de pe pagina:");
        for (Cuvant cuvant : cuvinte) {
            cuvant.afisareCuvant();
        }
    }

    // Suprascrierea metodei de căutare a subșirului pe pagina folosind expresii regulate
    @Override
    public boolean cautaSubsir(String subsir) {
        System.out.println("Rezultatele căutării pentru subșirul \"" + subsir + "\":");
        boolean gasit = false;
        for (Cuvant cuvant : cuvinte) {
            if (cuvant.cautaSubsir(subsir)) { //Inițiază o buclă for care iterează prin array-ul de cuvinte din clasa Pagina
                cuvant.afisareCuvant("Cuvant gasit");
                gasit = true;
            }
        }
        return gasit;
    }

    @Override //afiseaza mesaj
    public void afisareCuvant(String mesaj) {
        System.out.println(mesaj);
        for (Cuvant cuvant : cuvinte) {
            cuvant.afisareCuvant();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Crearea unui obiect de tip Cuvant
        Cuvant cuvant1 = new Cuvant(",Java");
        Cuvant cuvant2 = new Cuvant("Programare");
        Cuvant cuvant3 = new Cuvant("Supraincarcare");

        // Crearea unui obiect de tip Pagina folosind clasa Cuvant
        Pagina pagina = new Pagina(new Cuvant[]{cuvant1, cuvant2, cuvant3});

        // Apelarea metodelor din clasa Pagina
        pagina.afisareCuvant();
        pagina.cautaSubsir("JAva");
        pagina.afisareCuvant("Rezultatul a fost cautat pe Pagina :");   //+mesaj
    }
}
