package Cabinet;

import java.util.ArrayList;
import java.util.List;

public abstract class Pacient {
    private String nume;
    private int varsta;

    Pacient(){

    }

    public Pacient(String nume, int varsta){
        this.nume = nume;
        this.varsta = varsta;
        //this.retete = new ArrayList<Reteta>();
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

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    /*public void adaugaReteta(Reteta r){
        retete.add(r);
    }*/

    abstract public boolean risc();

    @Override
    public String toString(){
        return "numePacient = " + nume  +
                ", varstaPacient = " + varsta;
                //retete;
    }
}
