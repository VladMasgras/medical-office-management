package Cabinet;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

public class Reteta {
    private LocalDate data;
    private Map<String, Integer> medicamente; //nume - cantitate
    private Pacient pacient;

    public Reteta()
    {
        this.data = LocalDate.now();
        medicamente = new HashMap<String, Integer>();
        this.pacient = null;
    }

    public void adaugaMedicament(String nume, int cantitate){
        medicamente.put(nume, cantitate);
    }

    public void setPacient(Pacient pacient){
        this.pacient = pacient;
    }

    public void stergeMedicament(String nume){
        medicamente.remove(nume);
    }

    @Override
    public String toString(){
        return "data " + data + '\t' + medicamente;
    }
}
