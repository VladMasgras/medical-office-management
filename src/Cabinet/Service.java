package Cabinet;

import javax.print.Doc;
import java.util.*;
import java.time.LocalDate;


public class Service {

    public static void start() throws Exception {

        Cabinet cabinet = new Cabinet();
        List<Adult> listaAdulti = Database.getDatabase().read("adulti.csv", Adult.class);
        List<Batran> listaBatrani = Database.getDatabase().read("batrani.csv", Batran.class);
        List<Copil> listaCopil = Database.getDatabase().read("copii.csv", Copil.class);

        List<Pacient> pacientList = cabinet.getPacienti();
        List<Doctor> doctorList = cabinet.getDoctori();
        doctorList.addAll(Database.getDatabase().read("doctor.csv", Doctor.class));


        pacientList.addAll(listaAdulti);
        pacientList.addAll(listaBatrani);
        pacientList.addAll(listaCopil);

        System.out.println("1.Adauga pacient");
        System.out.println("2.Sterge adult");
        System.out.println("3.Sterge copil");
        System.out.println("4.Sterge batran");
        System.out.println("5.Adauga doctor");
        System.out.println("6.Sterge doctor");
        System.out.println("7.Afiseaza pacientii");
        System.out.println("8.Afiseaza pacientii copii");
        System.out.println("9.Afiseaza pacientii adulti");
        System.out.println("10.Afiseaza pacientii batrani");
        System.out.println("11.Afiseaza pacientii cu risc cardiovascular");
        System.out.println("12.Afiseaza doctorii");
        System.out.println("13.Adauga o reteta unui pacient");
        System.out.println("14.Asigneaza un pacient unui doctor/dentist");
        System.out.println("15.Adauga dentist");
        System.out.println("16.Update copil");
        System.out.println("17.Update adult");
        System.out.println("18.Update batran");
        System.out.println("19.Update doctor");
        System.out.println("0.Exit");


        loop:
        while (true){
            System.out.println("Selectati una din optiuni");
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 0:
                    Database.getDatabase().write(listaCopil,"copii.csv");
                    Database.getDatabase().write(listaAdulti, "adulti.csv");
                    Database.getDatabase().write(listaBatrani, "batrani.csv");
                    Database.getDatabase().write(doctorList,"doctor.csv");
                    break loop;
                case 1:
                    System.out.println("Introduceti numele pacientului");
                    String numePacient = scanner.next();
                    System.out.println("Introduceti varsta");
                    int varsta = scanner.nextInt();
                    System.out.println("Selectati categoria pacientului");
                    System.out.println("1.Copil");
                    System.out.println("2.Adult");
                    System.out.println("3.Batran");
                    switch (scanner.nextInt()) {
                        case 1:
                            System.out.println("Introduceti nivelul proteinei C");
                            float proteinaC = scanner.nextFloat();
                            Copil copil = new Copil(numePacient, varsta, proteinaC);
                            //cabinet.adaugaPacient(copil);
                            //listaCopil.add((Copil)copil);
                            SQLPersistance.getDatabase().addCopil(copil);
                            Audit.write("Copil adaugat", Thread.currentThread().getName());
                            break;
                        case 2:
                            System.out.println("Fumator/Nefumator? (true/false)");
                            boolean fumator = scanner.nextBoolean();
                            System.out.println("Sedentarism: scazut/moderat/ridicat");
                            String sedentarism = scanner.next();
                            Adult adult = new Adult(numePacient, varsta, fumator, sedentarism);
                            //cabinet.adaugaPacient(adult);
                            //listaAdulti.add((Adult)adult);
                            SQLPersistance.getDatabase().addAdult(adult);
                            Audit.write("Adult adaugat", Thread.currentThread().getName());
                            break;
                        case 3:
                            System.out.println("Introduceti valoarea tensiunii");
                            int valTensiune = scanner.nextInt();
                            Batran batran = new Batran(numePacient, varsta, valTensiune);
                            //cabinet.adaugaPacient(batran);
                            //listaBatrani.add((Batran)batran);
                            SQLPersistance.getDatabase().addBatran(batran);
                            Audit.write("Batran adaugat", Thread.currentThread().getName());
                            break;
                        default:
                            System.out.println("Optiune incorecta!");
                    }
                    break;
                case 2:
                    System.out.println("Introduceti numele pacientului");
                    String numeAdult = scanner.next();
                    int nAdulti = SQLPersistance.getDatabase().stergeAdult(numeAdult);
                    if (nAdulti == 0) {
                        System.out.println("Pacientul nu e in baza de date");
                        break;
                    }
                    //cabinet.stergePacient(cabinet.getPacient(nume));
                    //listaCopil.remove((Copil)cabinet.getPacient(nume));
                    //System.out.println(listaCopil.get(0));
                    Audit.write("Adult sters", Thread.currentThread().getName());
                    break;
                case 3:
                    System.out.println("Introduceti numele pacientului");
                    String numeCopil = scanner.next();
                    int nCopii = SQLPersistance.getDatabase().stergeCopil(numeCopil);
                    if (nCopii == 0) {
                        System.out.println("Pacientul nu e in baza de date");
                        break;
                    }
                    //cabinet.stergePacient(cabinet.getPacient(nume));
                    //listaCopil.remove((Copil)cabinet.getPacient(nume));
                    //System.out.println(listaCopil.get(0));
                    Audit.write("Adult sters", Thread.currentThread().getName());
                    break;
                case 4:
                    System.out.println("Introduceti numele pacientului");
                    String numeBatran = scanner.next();
                    int nBatrani = SQLPersistance.getDatabase().stergeBatran(numeBatran);
                    if (nBatrani == 0) {
                        System.out.println("Pacientul nu e in baza de date");
                        break;
                    }
                    //cabinet.stergePacient(cabinet.getPacient(nume));
                    //listaCopil.remove((Copil)cabinet.getPacient(nume));
                    //System.out.println(listaCopil.get(0));
                    Audit.write("Adult sters", Thread.currentThread().getName());
                    break;
                case 5:
                    System.out.println("Introduceti numele doctorului");
                    String numeDoctor = scanner.next();
                    System.out.println("Varsta");
                    int varstaDoc = scanner.nextInt();
                    Doctor doctor = new Doctor(numeDoctor,varstaDoc);
                    cabinet.adaugaDoctor(doctor);
                    Audit.write("Doctor adaugat", Thread.currentThread().getName());
                    break;
                case 6:
                    System.out.println("Introduceti numele doctorului");
                    String numeDoc = scanner.next();
                    if (cabinet.getDoctor(numeDoc) == null){
                        System.out.println("Doctorul nu e in baza de date");
                        break;
                    }
                    //System.out.println(cabinet.getDoctor(numeDoc));
                    cabinet.stergeDoctor(cabinet.getDoctor(numeDoc));
                    Audit.write("Doctor sters", Thread.currentThread().getName());
                    break;
                case 7:
                    cabinet.afisarePacienti();
                    Audit.write("Afisare pacienti", Thread.currentThread().getName());
                    break;
                case 8:
                    SQLPersistance.getDatabase().listeazaCopii();
                    Audit.write("Afisare copii", Thread.currentThread().getName());
                    break;
                case 9:
                    SQLPersistance.getDatabase().listeazaAdulti();
                    Audit.write("Afisare adulti", Thread.currentThread().getName());
                    break;
                case 10:
                    SQLPersistance.getDatabase().listeazaBatrani();
                    Audit.write("Afisare batrani", Thread.currentThread().getName());
                    break;
                case 11:
                    for (Pacient pac:pacientList){
                        if (pac.risc())
                            System.out.println(pac);
                    }
                    Audit.write("Afisare pacienti cu risc", Thread.currentThread().getName());
                    break;
                case 12:
                    cabinet.afisareDoctori();
                    Audit.write("Afisare doctori", Thread.currentThread().getName());
                    break;
                case 13:
                    System.out.println("Dati numele pacientului");
                    String numepac = scanner.next();
                    if (cabinet.getPacient(numepac) == null){
                        System.out.println("Pacientul nu e in baza de date");
                        break;
                    }
                    Reteta r = new Reteta();
                    System.out.println("Numarul de medicamente prescrise");
                    int nr = scanner.nextInt();
                    for (int i = 0; i < nr; i++) {
                        System.out.println("Numele medicamentului");
                        String numeMed = scanner.next();
                        System.out.println("Cantitatea");
                        int cant = scanner.nextInt();
                        r.adaugaMedicament(numeMed, cant);
                    }
                    r.setPacient(cabinet.getPacient(numepac));
                    //cabinet.getPacient(numepac).adaugaReteta(r);
                    Audit.write("Reteta adaugata", Thread.currentThread().getName());
                    break;
                case 14:
                    System.out.println("Introduceti numele doctorului");
                    String numeD = scanner.next();
                    /*if (cabinet.getDoctor(numeD) == null){
                        System.out.println("Doctorul nu e in baza de date");
                        break;
                    }*/
                        //throw new Error("Doctorul nu e in baza de date");
                    System.out.println("Introduceti numele pacientului");
                    String numeP = scanner.next();
                    /*if (cabinet.getPacient(numeP) == null){
                        System.out.println("Pacientul nu e in baza de date");
                        break;
                    }*/
                        //throw new Error("Pacientul nu e in baza de date");
                    //cabinet.getDoctor(numeD).adaugaPacient(cabinet.getPacient("numeP"));
                    /*Map<Doctor, Pacient> asignare = cabinet.getAsignare();
                    asignare.put(cabinet.getDoctor(numeD),cabinet.getPacient(numeP));*/
                    SQLPersistance.getDatabase().asigneazaDoctorCopil(numeP, numeD);
                    Audit.write("Pacient asignat", Thread.currentThread().getName());
                    break;
                case 15:
                    System.out.println("Introduceti numele dentistului");
                    String numeDent = scanner.next();
                    System.out.println("Introduceti varsta");
                    int varstaDent = scanner.nextInt();
                    System.out.println("Introduceti numarul de telefon");
                    String numar = scanner.next();
                    Doctor dentist = new Dentist(numeDent,varstaDent,numar);
                    cabinet.adaugaDoctor(dentist);
                    Audit.write("Dentist adaugat", Thread.currentThread().getName());
                    break;
                case 16:
                    System.out.println("Introduceti numele pacientului");
                    String numeC = scanner.next();
                    System.out.println("Introduceti Proteina C");
                    float proteinaC = scanner.nextFloat();
                    SQLPersistance.getDatabase().updateCopil(numeC, proteinaC);
                    break;
                case 17:
                    System.out.println("Introduceti numele pacientului");
                    String numePac = scanner.next();
                    System.out.println("Fumator(true/false)");
                    boolean fumator = scanner.nextBoolean();
                    System.out.println("Sedentarism");
                    String sed = scanner.next();
                    SQLPersistance.getDatabase().updateAdult(numePac, fumator, sed);
                    break;
                case 18:
                    System.out.println("Introduceti numele");
                    String numeB = scanner.next();
                    System.out.println("Tensiune");
                    int tens = scanner.nextInt();
                    SQLPersistance.getDatabase().updateBatran(numeB, tens);
                    break;
                default:
                    System.out.println("Optiune invalida");
            }
        }
    }
}
