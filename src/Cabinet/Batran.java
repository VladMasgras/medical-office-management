package Cabinet;

public class Batran extends Pacient{
    int valTensiune;

    Batran(){

    }

    Batran(String nume, int varsta, int valTensiune){
        super(nume, varsta);
        this.valTensiune = valTensiune;
    }

    public int getValTensiune() {
        return valTensiune;
    }

    public void setValTensiune(int valTensiune) {
        this.valTensiune = valTensiune;
    }

    public boolean risc(){
        return (valTensiune < 130 || valTensiune > 139);
    }

    @Override
    public String toString(){
        return super.toString() + " valTensiune " + valTensiune;
    }
}
