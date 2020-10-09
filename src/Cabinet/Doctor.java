package Cabinet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Doctor {
    private String nume;
    private int varsta;
    //private List<Pacient> pacienti;

    Doctor(){

    }

    Doctor(String nume, int varsta){
        this.nume = nume;
        this.varsta = varsta;
        //pacienti = new ArrayList<Pacient>();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void adaugaPacient(Pacient p){
        //pacienti.add(p);
    }

    public void stergePacient(Pacient p)
    {
        //pacienti.remove(p);
    }

    @Override
    public String toString(){
        return "numeDoctor " + nume
                + " ,varsta " + varsta;
                //+ " pacienti: " + pacienti;
    }
}
