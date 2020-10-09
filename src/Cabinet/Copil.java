package Cabinet;

public class Copil extends Pacient {
    private float proteinaC;

    Copil(){
    }

    Copil(String nume, int varsta, float proteinaC){
        super(nume,varsta);
        this.proteinaC = proteinaC;
    }

    public float getProteinaC() {
        return proteinaC;
    }

    public void setProteinaC(int proteinaC) {
        this.proteinaC = proteinaC;
    }

    public boolean risc(){
        if (this.getProteinaC() > 0.60)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return super.toString() + " proteinaC " + proteinaC;
    }
}
