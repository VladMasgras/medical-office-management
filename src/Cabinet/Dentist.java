package Cabinet;

public class Dentist extends Doctor {
    String numarTelefon;

    Dentist(String nume, int varsta, String numarTel) {
        super(nume,varsta);
        numarTelefon = numarTel;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    @Override
    public String toString(){
        return super.toString() + " Numar telefon: " + numarTelefon;
    }
}
