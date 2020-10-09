package Cabinet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class Interface {

    JFrame fereastra;

    public Interface(){
        fereastra = new JFrame("Cabinet");
        fereastra.setSize(600, 400);
        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = (JPanel) fereastra.getContentPane();
        topPanel.setLayout(new FlowLayout());

        JLabel jLabel = new JLabel("Meniu");
        jLabel.setBounds(260,50,150,20);
        topPanel.add(jLabel);

        JButton listeazaCopii = new JButton("Listeaza copii");
        listeazaCopii.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListeazaCopiiFrame fereastraListezaCopii = new ListeazaCopiiFrame();
            }
        });

        topPanel.add(listeazaCopii);

        JButton listeazaAdulti = new JButton("Listeaza adulti");
        listeazaAdulti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListeazaAdultiFrame fereastraListeazaAdulti = new ListeazaAdultiFrame();
            }
        });

        topPanel.add(listeazaAdulti);

        JButton listeazaBatrani = new JButton("Listeaza batrani");
        listeazaBatrani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListeazaBatraniFrame fereastraListeazaBatrani = new ListeazaBatraniFrame();
            }
        });
        topPanel.add(listeazaBatrani);

        JButton listeazaDoctori = new JButton("Listeaza doctori");
        listeazaDoctori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListeazaDoctoriFrame fereastraDoctori = new ListeazaDoctoriFrame();
            }
        });
        topPanel.add(listeazaDoctori);

        JButton adaugaPacient = new JButton("Adauga Pacient");
        adaugaPacient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdaugaCopilFrame fereastra = new AdaugaCopilFrame();
            }
        });
        topPanel.add(adaugaPacient);

        JButton asigneazaDoctor = new JButton("Asigneaza doctor unui copil");
        asigneazaDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AsigneazaDoctorCopil fereastra = new AsigneazaDoctorCopil();
            }
        });
        topPanel.add(asigneazaDoctor);

        //fereastra.setLayout(null);
        fereastra.setVisible(true);
    }

}

class ListeazaCopiiFrame extends JFrame {

    JTable table;

    public ListeazaCopiiFrame(){
        super("Pacienti copii");
        this.setSize(600,400);
        JScrollPane panel = new JScrollPane();
        panel.setBounds(0, 0,580,300);
        panel.setBackground(Color.WHITE);

        ResultSet rs = SQLPersistance.getDatabase().getCopii();
        try{
            if (rs == null) throw new NullPointerException("Nu exista elemente");

            Vector<String> numeColoane = new Vector<>();
            int nrColoane = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= nrColoane; i++)
                numeColoane.add(rs.getMetaData().getColumnName(i));

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()){
                Vector<Object> coloane = new Vector<>();
                for (int i = 1; i <= nrColoane; i++)
                    coloane.add(rs.getObject(i));
                data.add(coloane);
            }

            table = new JTable(data, numeColoane);
            table.setBounds(0,0,580,300);
            panel.add(table);
            panel.setViewportView(table);
            add(panel);


        } catch (SQLException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        this.setLayout(null);
        this.setVisible(true);

        Audit.write("Copii listati", Thread.currentThread().getName());
    }
}

class ListeazaAdultiFrame extends JFrame{
    JTable table;

    public ListeazaAdultiFrame(){
        super("Pacienti adulti");
        this.setSize(600,400);
        JScrollPane panel = new JScrollPane();
        panel.setBounds(0, 0,580,300);
        panel.setBackground(Color.WHITE);

        ResultSet rs = SQLPersistance.getDatabase().getAdulti();
        try{
            if (rs == null) throw new NullPointerException("Nu exista elemente");

            Vector<String> numeColoane = new Vector<>();
            int nrColoane = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= nrColoane; i++)
                numeColoane.add(rs.getMetaData().getColumnName(i));

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()){
                Vector<Object> coloane = new Vector<>();
                for (int i = 1; i <= nrColoane; i++)
                    coloane.add(rs.getObject(i));
                data.add(coloane);
            }

            table = new JTable(data, numeColoane);
            table.setBounds(0,0,580,300);
            panel.add(table);
            panel.setViewportView(table);
            add(panel);


        } catch (SQLException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        this.setLayout(null);
        this.setVisible(true);

        Audit.write("Adulti listati", Thread.currentThread().getName());
    }
}

class ListeazaBatraniFrame extends JFrame{
    JTable table;

    public ListeazaBatraniFrame(){
        super("Pacienti batrani");
        this.setSize(600,400);
        JScrollPane panel = new JScrollPane();
        panel.setBounds(0, 0,580,300);
        panel.setBackground(Color.WHITE);

        ResultSet rs = SQLPersistance.getDatabase().getBatrani();
        try{
            if (rs == null) throw new NullPointerException("Nu exista elemente");

            Vector<String> numeColoane = new Vector<>();
            int nrColoane = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= nrColoane; i++)
                numeColoane.add(rs.getMetaData().getColumnName(i));

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()){
                Vector<Object> coloane = new Vector<>();
                for (int i = 1; i <= nrColoane; i++)
                    coloane.add(rs.getObject(i));
                data.add(coloane);
            }

            table = new JTable(data, numeColoane);
            table.setBounds(0,0,580,300);
            panel.add(table);
            panel.setViewportView(table);
            add(panel);


        } catch (SQLException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        this.setLayout(null);
        this.setVisible(true);

        Audit.write("Batrani listati", Thread.currentThread().getName());
    }
}

class ListeazaDoctoriFrame extends JFrame{
    JTable table;

    public ListeazaDoctoriFrame(){
        super("Doctori");
        this.setSize(600,400);
        JScrollPane panel = new JScrollPane();
        panel.setBounds(0, 0,580,300);
        panel.setBackground(Color.WHITE);

        ResultSet rs = SQLPersistance.getDatabase().getDoctori();
        try{
            if (rs == null) throw new NullPointerException("Nu exista elemente");

            Vector<String> numeColoane = new Vector<>();
            int nrColoane = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= nrColoane; i++)
                numeColoane.add(rs.getMetaData().getColumnName(i));

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()){
                Vector<Object> coloane = new Vector<>();
                for (int i = 1; i <= nrColoane; i++)
                    coloane.add(rs.getObject(i));
                data.add(coloane);
            }

            table = new JTable(data, numeColoane);
            table.setBounds(0,0,580,300);
            panel.add(table);
            panel.setViewportView(table);
            add(panel);
            Audit.write("Doctori listati", Thread.currentThread().getName());


        } catch (SQLException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        this.setLayout(null);
        this.setVisible(true);
    }

}

class AdaugaCopilFrame extends JFrame {

    JLabel labelNume, labelVarsta, labelProteinaC, labelSedentarism, labelFumator, labelTensiune;
    JTextField nume, varsta, proteinaC, sedentarism, fumator, tensiune;
    String[] pacientiStrings = {"Copil", "Adult", "Batran"};
    JComboBox pacienti;

    public AdaugaCopilFrame(){

        super("Adauga copil");
        this.setSize(600, 400);

        JPanel panel = (JPanel) this.getContentPane();
        //panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());

        pacienti = new JComboBox(pacientiStrings);
        pacienti.setSelectedIndex(0);
        //panel.add(pacienti);
        pacienti.setBounds(5, 50,90,20);
        panel.add(pacienti);

        labelNume = new JLabel("Nume");
        nume = new JTextField();
        labelNume.setBounds(100,30,100,20);
        nume.setBounds(100,50,100,20);
        panel.add(labelNume);
        panel.add(nume);

        labelVarsta = new JLabel("Varsta");
        varsta = new JTextField();
        labelVarsta.setBounds(200,30,100,20);
        varsta.setBounds(200,50,100,20);
        panel.add(labelVarsta);
        panel.add(varsta);

        labelProteinaC = new JLabel("Proteina C");
        proteinaC = new JTextField();
        labelProteinaC.setBounds(300,30,100,20);
        proteinaC.setBounds(300,50,100,20);
        panel.add(labelProteinaC);
        panel.add(proteinaC);
        labelProteinaC.setVisible(false);
        proteinaC.setVisible(false);

        labelFumator = new JLabel("Fumator");
        fumator = new JTextField();
        labelFumator.setBounds(300,30,100,20);
        fumator.setBounds(300,50,100,20);
        panel.add(labelFumator);
        panel.add(fumator);
        labelFumator.setVisible(false);
        fumator.setVisible(false);

        labelTensiune = new JLabel("Tensiune");
        tensiune = new JTextField();
        labelTensiune.setBounds(300,30,100,20);
        tensiune.setBounds(300,50,100,20);
        panel.add(labelTensiune);
        panel.add(tensiune);
        labelTensiune.setVisible(false);
        tensiune.setVisible(false);

        labelSedentarism = new JLabel("Sedentarism");
        sedentarism = new JTextField();
        labelSedentarism.setBounds(400,30,100,20);
        sedentarism.setBounds(400,50,100,20);
        panel.add(labelSedentarism);
        panel.add(sedentarism);
        labelSedentarism.setVisible(false);
        sedentarism.setVisible(false);


        pacienti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pacienti.getSelectedIndex() == 0){
                    labelProteinaC.setVisible(true);
                    proteinaC.setVisible(true);

                    labelFumator.setVisible(false);
                    fumator.setVisible(false);

                    labelTensiune.setVisible(false);
                    tensiune.setVisible(false);

                    labelSedentarism.setVisible(false);
                    sedentarism.setVisible(false);
                }
                else if (pacienti.getSelectedIndex() == 1){
                    labelProteinaC.setVisible(false);
                    proteinaC.setVisible(false);

                    labelFumator.setVisible(true);
                    fumator.setVisible(true);

                    labelTensiune.setVisible(false);
                    tensiune.setVisible(false);

                    labelSedentarism.setVisible(true);
                    sedentarism.setVisible(true);
                }

                else if (pacienti.getSelectedIndex() == 2){
                    labelProteinaC.setVisible(false);
                    proteinaC.setVisible(false);

                    labelFumator.setVisible(false);
                    fumator.setVisible(false);

                    labelTensiune.setVisible(true);
                    tensiune.setVisible(true);

                    labelSedentarism.setVisible(false);
                    sedentarism.setVisible(false);
                }
            }
        });

        JButton buton = new JButton("Submit");
        buton.setBounds(5,100,150,20);
        panel.add(buton);

        buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pacienti.getSelectedIndex() == 0){
                    Copil copil = new Copil(nume.getText(), Integer.parseInt(varsta.getText()), Float.parseFloat(proteinaC.getText()));
                    System.out.println(copil);
                    SQLPersistance.getDatabase().addCopil(copil);
                    Audit.write("Copil adaugat in baza de date", Thread.currentThread().getName());
                }
                else if (pacienti.getSelectedIndex() == 1){
                    Adult adult = new Adult(nume.getText(), Integer.parseInt(varsta.getText()), Boolean.parseBoolean(fumator.getText()), sedentarism.getText());
                    System.out.println(adult);
                    SQLPersistance.getDatabase().addAdult(adult);
                    Audit.write("Adult adaugat in baza de date", Thread.currentThread().getName());
                }
                else if (pacienti.getSelectedIndex() == 2){
                    Batran batran = new Batran(nume.getText(), Integer.parseInt(varsta.getText()), Integer.parseInt(tensiune.getText()));
                    System.out.println(batran);
                    SQLPersistance.getDatabase().addBatran(batran);
                    Audit.write("Batran adaugat in baza de date", Thread.currentThread().getName());
                }
            }
        });

        panel.setOpaque(true);
        //this.setContentPane(panel);

        this.setLayout(null);
        this.setVisible(true);


    }

}

class AsigneazaDoctorCopil extends JFrame {

    JLabel labelDoctor, labelCopil;
    JTextField doctor, copil;

    public AsigneazaDoctorCopil()
    {
        super("Asigneaza doctor unui copil");
        this.setSize(600, 400);

        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new FlowLayout());

        labelCopil = new JLabel("Nume copil ");
        labelCopil.setBounds(5,50,90,20);
        copil = new JTextField();
        copil.setBounds(5,100,90,20);

        labelDoctor = new JLabel("Nume doctor");
        labelDoctor.setBounds(105,50,90,20);
        doctor = new JTextField();
        doctor.setBounds(105,100,90,20);

        Button buton = new Button("Submit");
        buton.setBounds(5,200,150,20);

        panel.add(labelCopil);
        panel.add(labelDoctor);
        panel.add(copil);
        panel.add(doctor);
        panel.add(buton);

        buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLPersistance.getDatabase().asigneazaDoctorCopil(copil.getText(), doctor.getText());
                Audit.write("Copil asignat unui doctor", Thread.currentThread().getName());
            }
        });


        panel.setOpaque(true);
        this.setLayout(null);
        this.setVisible(true);
    }


}