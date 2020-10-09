package Cabinet;

import java.util.*;

public class Cabinet {
    private List<Pacient> pacienti;
    private List<Doctor> doctori;
    private Map<Doctor, Pacient> asignare;

    Cabinet(){
        pacienti = new ArrayList<Pacient>();
        doctori = new ArrayList<Doctor>();
        asignare = new HashMap<Doctor, Pacient>();
    }

    public Map<Doctor, Pacient> getAsignare(){
        return asignare;
    }

    public void adaugaPacient(Pacient p){
        pacienti.add(p);
        Collections.sort(pacienti, compareByName);
    }

    public void stergePacient(Pacient p){
        pacienti.remove(p);
    }

    public void adaugaDoctor(Doctor d){
        doctori.add(d);
    }

    public void stergeDoctor(Doctor d){
        doctori.remove(d);
    }

    public Pacient getPacient(String nume){
        for (Pacient pac:pacienti){
            if (pac.getNume().equals(nume))
                return pac;
        }
        return null;
    }

    private Comparator<Pacient> compareByName = new Comparator<Pacient>() {
        @Override
        public int compare(Pacient p1, Pacient p2) {
            return (p1.getNume()).compareTo(p2.getNume());
        }
    };


    public Doctor getDoctor(String nume){
        for (Doctor doc:doctori){
            if (doc.getNume().equals(nume))
                return doc;
        }
        return null;
    }

    public void afisarePacienti(){
        System.out.println(pacienti);
    }

    public List<Pacient> getPacienti(){

        return pacienti;
    }

    public List<Doctor> getDoctori(){

        return doctori;
    }

    /*public void afisareCopii(){
        for (Pacient pac:pacienti)
            if (pac instanceof Copil)
                System.out.println();
    }*/

    public void afisareDoctori(){
        System.out.println(doctori);
    }
}
