package main;

import java.util.Random;

public class parco {
	public static void main(String[] args) {
	// 
			int minutiApertura = 1040;
	        int costoAttrazione1 = 22, capacitaAttrazione1 = 4;
	        int costoAttrazione2 = 15, capacitaAttrazione2 = 6;
	        int costoAttrazione3 = 15, capacitaAttrazione3 = 5;
	        int girimax1 = 0, girimax2 = 0, girimax3 =0;
	        int visitatoriTotali = 0;
	        int visitatoriSoddisfatti1 = 0,visitatoriSoddisfatti2 = 0,visitatoriSoddisfatti3 =0;
	        int guadagnoTotale = 0;
	        int coda1 = 0, coda2 = 0, coda3 = 0;
	        int tempoRimanente1 = 0, tempoRimanente2 = 0, tempoRimanente3 = 0;
	        int visitatoriAttrazione1 = 0, visitatoriAttrazione2 = 0, visitatoriAttrazione3 = 0;
	        double tempoAttesaTotale1 = 0, tempoAttesaTotale2 = 0, tempoAttesaTotale3 = 0;
	        int t = 15;
	        Random random = new Random();

	        for (int minuto = 0; minuto < minutiApertura; minuto++) {
	            visitatoriTotali++;
	            int budget = 10 + random.nextInt(26);  // Budget casuale tra 10 e 35
	            int pazienza = 10 + random.nextInt(26);  // Pazienza casuale tra 10 e 35
	            if (girimax1==50) {costoAttrazione1 = 999;}
	            if (girimax2==55) {costoAttrazione2 = 999;}
	            if (girimax3==60) {costoAttrazione3 = 999;}
	            // Calcola i tempi di attesa per ogni attrazione
	            int tempoAttesa1 = tempoRimanente1 +  (coda1 / capacitaAttrazione1) * t;
	            int tempoAttesa2 = tempoRimanente2 +  (coda2 / capacitaAttrazione2) * t;
	            int tempoAttesa3 = tempoRimanente3 +  (coda3 / capacitaAttrazione3) * t;

	            // Scegli l'attrazione con meno persone in coda che soddisfa budget e pazienza, prioritizza sempre la coda più corta 
	            int sceltaAttrazione = -1;

	            if (budget >= costoAttrazione1 && tempoAttesa1 <= pazienza) {
	                sceltaAttrazione = 1;
	            }

	            if (budget >= costoAttrazione2 && tempoAttesa2 <= pazienza) {
	                if (sceltaAttrazione == -1) {
	                    sceltaAttrazione = 2;
	                } else {
	                    if (coda2 < coda1) {
	                        sceltaAttrazione = 2;
	                    }
	                }
	            }

	            if (budget >= costoAttrazione3 && tempoAttesa3 <= pazienza) { //qui si poteva fare in mille modi diversi ma ancora non avevamo fatto Math.min ecc
	                int codaMinima;
	                if (coda1 < coda2) {
	                    codaMinima = coda1;
	                } else {
	                    codaMinima = coda2;
	                }

	                if (sceltaAttrazione == -1) {
	                    sceltaAttrazione = 3;
	                } else {
	                    if (coda3 < codaMinima) {
	                        sceltaAttrazione = 3;
	                    }
	                }
	            }

	            // Incrementa la coda dell'attrazione scelta
	            if (sceltaAttrazione == 1) {
	                tempoAttesaTotale1 += tempoAttesa1;
	                coda1++;
	                visitatoriSoddisfatti1++;
	            } else if (sceltaAttrazione == 2) {
	                tempoAttesaTotale2 += tempoAttesa2;
	                coda2++;
	                visitatoriSoddisfatti2++;
	            } else if (sceltaAttrazione == 3) {
	                tempoAttesaTotale3 += tempoAttesa3;
	                coda3++;
	                visitatoriSoddisfatti3++;
	            }
	             //if (minuto % 10 == 0)  	qui una volta c'era un timer da dieci minuti per far partire la giostra, non era una bellissima idea
	            //{
	                if (tempoRimanente1 == 0 && coda1 >= capacitaAttrazione1) 
	                {
	                    int inGiostra1;        //questo è uno strumento per far salire le persone sulla giostra ogni volta che questa termina un giro
	                    if (coda1 <= capacitaAttrazione1)   // le persone rimarranno sullo giostra finchè non sarà piena, a quel punto partità
	                    {
	                        inGiostra1 = coda1;
	                    } else 
	                    {
	                        inGiostra1 = capacitaAttrazione1;
	                    }

	                    coda1 -= inGiostra1;
	                    visitatoriAttrazione1 += inGiostra1;
	                    guadagnoTotale += (capacitaAttrazione1 * costoAttrazione1);
	                    tempoRimanente1 = 10;
	                    girimax1++;
	                }
	                
	                if (tempoRimanente2 == 0 && coda2 >= capacitaAttrazione2) {
	                    int inGiostra2;
	                    if (coda2 < capacitaAttrazione2) {
	                        inGiostra2 = coda2;
	                    } else {
	                        inGiostra2 = capacitaAttrazione2;
	                    }

	                    coda2 -= inGiostra2;
	                    visitatoriAttrazione2 += inGiostra2;
	                    guadagnoTotale += (capacitaAttrazione2 * costoAttrazione2);
	                    tempoRimanente2 = 10;
	                    girimax2++;
	                }
	                if (tempoRimanente3 == 0 && coda3 >= capacitaAttrazione3) {
	                    int inGiostra3;
	                    if (coda3 < capacitaAttrazione3) {
	                        inGiostra3 = coda3;
	                    } else {
	                        inGiostra3 = capacitaAttrazione3;
	                    }

	                    coda3 -= inGiostra3;
	                    visitatoriAttrazione3 += inGiostra3;
	                    guadagnoTotale += (capacitaAttrazione3 * costoAttrazione3);
	                    tempoRimanente3 = 10;
	                    girimax3++;
	                }
	           // }

	            // Riduci il tempo rimanente delle attrazioni
	            if (tempoRimanente1 > 0) {
	                tempoRimanente1--;
	            }
	            if (tempoRimanente2 > 0) {
	                tempoRimanente2--;
	            }
	            if (tempoRimanente3 > 0) {
	                tempoRimanente3--;
	            }
	        }

	        // Calcola i risultati 
	        double percentualeSoddisfatti = (double) (visitatoriSoddisfatti1+visitatoriSoddisfatti2+visitatoriSoddisfatti3) / visitatoriTotali * 100;
	        double tempoAttesaMedio1;
	        if (visitatoriAttrazione1 > 0) { //questo serve a non dividere per zero nel caso in cui nessuno visitasse l'attrazione in questione
	            tempoAttesaMedio1 = tempoAttesaTotale1 / visitatoriAttrazione1;
	        } else {
	            tempoAttesaMedio1 = 0;
	        }

	        double tempoAttesaMedio2;
	        if (visitatoriAttrazione2 > 0) {
	            tempoAttesaMedio2 = tempoAttesaTotale2 / visitatoriAttrazione2;
	        } else {
	            tempoAttesaMedio2 = 0;
	        }

	        double tempoAttesaMedio3;
	        if (visitatoriAttrazione3 > 0) {
	            tempoAttesaMedio3 = tempoAttesaTotale3 / visitatoriAttrazione3;
	        } else {
	            tempoAttesaMedio3 = 0;
	        }

	        // Stampa i risultati
	        System.out.println("Guadagno netto totale: " + guadagnoTotale + " euro");
	        if (visitatoriSoddisfatti1>visitatoriSoddisfatti2 && visitatoriSoddisfatti1 >visitatoriSoddisfatti3) 
	        
	        {System.out.println("L'attrazione più visitata è la numero1");}
	        
	        if (visitatoriSoddisfatti2>visitatoriSoddisfatti1 && visitatoriSoddisfatti2 >visitatoriSoddisfatti3) 
	            
	        {System.out.println("L'attrazione più visitata è la numero2");}
	        
	        if (visitatoriSoddisfatti3>visitatoriSoddisfatti1 && visitatoriSoddisfatti3 >visitatoriSoddisfatti2) 
	            
	        {System.out.println("L'attrazione più visitata è la numero3");}
	        
	        System.out.println("Percentuale visitatori soddisfatti: " + percentualeSoddisfatti + "%");
	        System.out.println("Tempo medio di attesa per Attrazione 1: " + tempoAttesaMedio1 + " minuti");
	        System.out.println("Tempo medio di attesa per Attrazione 2: " + tempoAttesaMedio2 + " minuti");
	        System.out.println("Tempo medio di attesa per Attrazione 3: " + tempoAttesaMedio3 + " minuti");
	    

		}

	}

