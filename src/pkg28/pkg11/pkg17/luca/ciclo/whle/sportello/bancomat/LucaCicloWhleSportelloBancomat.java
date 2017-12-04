/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg28.pkg11.pkg17.luca.ciclo.whle.sportello.bancomat;

import javax.swing.JOptionPane;

/**
 *
 * @author tss
 */
public class LucaCicloWhleSportelloBancomat {
 static double saldo = 0.000;
    static String codici[];
    static double prezzi[];
    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        //prima cosa da fare è capire cosa vuole fare l'utente attraverso un ciclo  do while
        // versamento (V) o prelievo (P) o uscire (U)
        String scelta;
        //caricare codici e prezzi nella macchina... abbiamo fatto così perchè non ce ne frega una minchia e dobbiamo fare veloci
        codici = new String[4];
        codici[0] = "A1";
        codici[1] = "A2";
        codici[2] = "A3";
        codici[3] = "A4";
        prezzi = new double[4];
        prezzi[0] = 0.35;
        prezzi[1] = 0.5;
        prezzi[2] = 5.0;
        prezzi[3] = 1.35;

        do {
            scelta = JOptionPane.showInputDialog("quale operazione vuoi fare?\n\nM = macchinetta\nP = prelevare\nV = versare\nU = uscire\n \nil tuo saldo disponibile è " + saldo + " $").toUpperCase();
            // se M fai previliavo dalla macchinetta
            if (scelta.equals("M")) {
                //richiamo il metodo prelevare();
                prebibite();
            }
            // se P fai un prelievo
            if (scelta.equals("P")) {
                //richiamo il metodo prelevare();
                prelevare();
            }
            //se V fai un versamento
            if (scelta.equals("V")) {
                //richiamo il versamento
                versare();
            }
        } while (!scelta.equals("U"));
    }

    static void versare() {
        double soldi_versati;
        do {

            soldi_versati = inputvaluta("inserisci banconote o moneta\nil tuo saldo attuale è " + saldo + " $" + "\ninserisci 0 per effettuare un altra operazione");
            saldo += soldi_versati;
            soldi_versati = decimali(saldo, 2);
            
            

        } while (soldi_versati == 0);
    }

    static void versareold() {
        String versato = "";
        do {
            versato = JOptionPane.showInputDialog("inserisci banconote o moneta?\nil tuo saldo attuale è " + saldo + " $" + "\ndigita U per terminare il versamento").toUpperCase();
            if (!versato.equals("U")) {
                double soldi_versati = Double.parseDouble(versato);
                saldo += soldi_versati;
                saldo = decimali(saldo, 2);
                JOptionPane.showMessageDialog(null, "hai ancora " + saldo + " $");
            }

        } while (!versato.equals("U"));
    }

    static void prelevare() {
        //String prelevato = JOptionPane.showInputDialog("quanto vuoi prelevare ?");
        double soldi_prelevati = inputvaluta("quanto vuoi prelvare?");
        if (saldo >= soldi_prelevati) {
            saldo -= soldi_prelevati;
            saldo = decimali(saldo, 2);
            JOptionPane.showMessageDialog(null, "prelevare pure i soldi\nhai ancora " + saldo + " $");
        } else //mancano i soldi
        {
            JOptionPane.showMessageDialog(null, "cazzo fai sei povero");
        }
    }

    static void prebibite() {
        String codice = JOptionPane.showInputDialog("quanle bibita vuoi acquistare ?" + "\nA1 prezzo 0.35$" + "\nA2 prezzo 0.5$" + "\nA3 prezzo 5.0$" + "\nA4 prezzo 1.35$");
        codice = codice.toUpperCase();
        int posizioneok = -1;
        //cerco nei codici la posizione di codice se esiste

        for (int i = 0; i < codici.length; i++) {
            // se in posizione i trovo codice in codici[i] ok trovato e mi segno la posizione
            if (codice.equals(codici[i])) {
                posizioneok = i;
            }
        }
        double costo = 0;
        if (posizioneok >= 0) {
            costo = prezzi[posizioneok];

            if (saldo >= costo) {
                saldo -= costo;
                saldo = decimali(saldo, 2);
                JOptionPane.showMessageDialog(null, "prelevare pure la bibita\nhai ancora " + saldo + " $");
            } else //mancano i soldi
            {
                JOptionPane.showMessageDialog(null, "cazzo fai sei povero");
            }
        }
        else {
        JOptionPane.showMessageDialog(null, " weee ciccio   codice errato");
        }
    }

    static double arrotonda(double valore) {
        double moltiplicatore = 100;
        valore = valore * moltiplicatore;
        valore = Math.round(valore) / moltiplicatore;
        return valore;
    }

    static double decimali(double valore, int cifre) {
        double moltiplicatore = 1;
        for (int i = 0; i < cifre; i++) {
            moltiplicatore = moltiplicatore * 10;
        }
        valore = valore * moltiplicatore;
        valore = Math.round(valore) / moltiplicatore;
        return valore;
    }

    // esempio ; double valore = inputvaluta ("dimmi quanti soldi vuoi")
    static double inputvaluta(String domanda) {

        double ris = -1;

        boolean ko = true;
        while (ko == true) {

            try {
                ko = false;
                String val = JOptionPane.showInputDialog(domanda);
                //parte pericolosa perchè se nell'input non si è inserito un valore numeri non si riesce a convertirlo in double
                ris = Double.parseDouble(val);
            } catch (Exception errore) {
                //cosa fare se errore
                System.out.println(errore.getMessage());
                ko = true;
                JOptionPane.showMessageDialog(null, "we ciccio inserisci un importo corretto");

            }
        }
        return ris;

    }
}

