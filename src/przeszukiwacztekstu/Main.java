package przeszukiwacztekstu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{

    public Main(){
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Przeszukiwacz Tekstu");
        this.setBounds(300,300,400,200);


        panelFind.add(find);
        panelFind.add(findText);
        panelFind.add(label1);
        panelFind.add(update);
        panelFind.add(label2);
        panelFind.add(newText);
       // areaT.selectAll();
        //areaT.select(0,2);
        //areaT.replaceSelection("lala");
        //areaT.replaceRange("lala",0,15);
        //areaT.insert("jakis tam ",18 );
        find.addActionListener(new znajdowanieHandler());
        update.addActionListener(new zamienianieHandler());
        this.getContentPane().add(areaScroll, BorderLayout.NORTH);
        this.getContentPane().add(panelFind, BorderLayout.CENTER);

        this.setDefaultCloseOperation(3);
    }

    private class znajdowanieHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            poczatekSzukanego= areaT.getText().indexOf(findText.getText(),poczatekSzukanego+ findText.getText().length());

            System.out.println(poczatekSzukanego);
            if (poczatekSzukanego==-1)
                poczatekSzukanego= areaT.getText().indexOf(findText.getText());

            if(poczatekSzukanego>=0&& findText.getText().length()>0){
                areaT.requestFocus();
                areaT.select(poczatekSzukanego,poczatekSzukanego+ findText.getText().length());
            }

        }
        private int poczatekSzukanego=0;
    }

    private class zamienianieHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (areaT.getSelectionStart() != areaT.getSelectionEnd())
            {
                zamienTekst();            }
            else {
                find.doClick();
                if(areaT.getSelectionStart() != areaT.getSelectionEnd())
                zamienTekst();
            }

        }
        private void zamienTekst()
        {
            areaT.requestFocus();
            int opcja = JOptionPane.showConfirmDialog(rootPane,"Czy chcesz zamienic  \'"+ findText.getText()+"\' na \'"+ newText.getText()+"\'" , "Uwaga nastąpi zmiana !",JOptionPane.YES_NO_OPTION);
            if(opcja==0)
            areaT.replaceRange(newText.getText(), areaT.getSelectionStart(), areaT.getSelectionEnd());
        }
    }

    private JTextArea areaT =new JTextArea("To jest testowy tekst o tekstowym charakterze",7,10);
    private JScrollPane areaScroll =new JScrollPane(areaT);

    private JPanel panelFind =new JPanel();
    private JButton find =new JButton("Znajdz");
    private JLabel label1 =new JLabel("i");
    private JButton update =new JButton("Zamień");
    private JLabel label2 =new JLabel("na");

    private JTextField findText =new JTextField(4);
    private JTextField newText =new JTextField(4);


    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
