package Cabinet;

public class Adult extends Pacient {
    private boolean fumator;
    private String sedentarism;

    Adult (){

    }

    Adult (String nume, int varsta, boolean fumator, String sed){
        super(nume,varsta);
        this.fumator = fumator;
        this.sedentarism = sed;
    }

    public boolean isFumator() {
        return fumator;
    }

    public void setFumator(boolean fumator) {
        this.fumator = fumator;
    }

    public String getSedentarism() {
        return sedentarism;
    }

    public void setSedentarism(String sedentarism) {
        this.sedentarism = sedentarism;
    }

    public boolean risc(){

        if (this.isFumator() && this.getSedentarism() == "Ridicat")
            return true;
        return false;
    }

    @Override
    public String toString(){
        return super.toString() + ((fumator) ? " fumator":" nefumator") + ", sedentarism " + sedentarism;
    }
}
