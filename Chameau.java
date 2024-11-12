import java.io.*;
import java.util.*;

class Chameau {
Scanner in=new Scanner(System.in);
Random random = new Random();
    
    int KM_VOYAGE = 300; // Distance a parcourir
    int KM_NORM_MIN = 10;  // vitenne min en normal
    int KM_NORM_MAX = 15;  // vitesse max en normal 
    int KM_RAP_MIN = 20;  // vitesse min en rapide 
    int KM_RAP_MAX = 30;  // vitesse max en rapide
    int AVANTAGE_VOYAGEUR = 20;  // distance d'avantage face au natif de depart
    int GOURDE_PLEINE = 12;  // taille gourde
    int MORT_SOIF = 5;  // limite pour la mort de soif
    int MORT_FATIGUE = 5;  // limite pour la mort de fatigue
    int DIF_AIDE = 3;  // difficulté pour l'aide
    int AVANCE_NATIFS = 2;  // vitesse natifs (plus c'est grand plus ils sont lent)
    int kmVoyageur = 0; // Distance totale parcourue
    int kmNatifs = kmVoyageur - AVANTAGE_VOYAGEUR; // distance parcourue des natifs
    int gourde = GOURDE_PLEINE / 2; // gorgée au depart
    int soif = 0; // soif du joueur
    int fatigue = 0; // fatigue du chamaux
    int distanceparcourue;  // variable pour calcul du parcours
    int distanatif;   // variable distance natif
    int special = 20; //vaiable pour événements spécials (%)
    int event; //variable pour événements spéciale
    int jourstempete; // variable contenant les jours restant de tempete
    int difficulté=1; // niveau de difficulté
    boolean tempete=false; // booleen pour tempete ou non
    boolean oasis=false; // booleen lorsque le joueur trouve une oasis
    int rep; // reponse au menu et options 

    void parambase(){
        KM_VOYAGE = 300;
        KM_NORM_MIN = 10;
        KM_NORM_MAX = 15;
        KM_RAP_MIN = 20;
        KM_RAP_MAX = 30;
        DIF_AIDE = 3;
        kmVoyageur = 0;
        kmNatifs = kmVoyageur - AVANTAGE_VOYAGEUR;
        GOURDE_PLEINE = 12;
        gourde = GOURDE_PLEINE / 2;
        soif = 0;
        fatigue = 0;
        special = 20;
        jourstempete =0;
        AVANCE_NATIFS=2;
        difficulté=1;
        tempete=false;
        oasis=false;
    }

    void difficulté(){
        do {
            System.out.println("\n============== [ DIFFICULTE ] ============="); 
            System.out.println("1. Facile                [ 300 km ]"); 
            System.out.println("2. Moyen                 [ 750 km ]"); 
            System.out.println("3. Jesus (Difficile)     [ 1 000 km ]"); 
            System.out.println("4. Moise (Impossible)    [ 5 000 km ]"); 
            System.out.print("\nChoix : ");
            difficulté=in.nextInt();
            switch (difficulté){
                case 1:
                    KM_VOYAGE = 300;
                    break;
                case 2:
                    KM_VOYAGE = 750;
                    break;
                case 3:
                    KM_VOYAGE = 1000;
                    AVANCE_NATIFS=2;
                    break;
                case 4:
                    KM_VOYAGE = 5000;
                    DIF_AIDE = 4;
                    GOURDE_PLEINE = 10;
                    special = 25;
                    AVANCE_NATIFS=2;
                    break; 
            }
            System.out.println("===========================================\n"); 
        }
        while (difficulté>4);

    }
    void depart() {
        System.out.println("\n============================ [ LE JEU DU CHAMEAU ] ============================\n"); 
        System.out.println("vous avez volé un chameau pour traverser le desert.");
        System.out.println("les natif veulent le récuperer.");
        System.out.println("Votre objectif est de survivre à la traversée sans etre attrapé(e).");
        System.out.println("\n===============================================================================\n");
    }
        
    void options() {
        System.out.println("\n================= [ OPTIONS ] ================"); 
        System.out.println("1. Boire                    [ "+gourde+" gorgée(s) / "+(MORT_SOIF-soif-1)+" eau ]"); 
        System.out.println("2. Avancer normalement      [ "+KM_NORM_MIN+"/"+KM_NORM_MAX+" km 1e ]"); 
        System.out.println("3. Avancer vite             [ "+KM_RAP_MIN+"/"+KM_RAP_MAX+" 2e ]"); 
        System.out.println("4. Repos                    [ "+(MORT_FATIGUE-fatigue-1)+" energie ]"); 
        System.out.println("5. Esperer de l'aide"); 
        System.out.println("0. Quitter et aller au menu (w/o save)");
        System.out.print("\nChoix : ");
        rep = in.nextInt();
        System.out.println("==============================================\n"); 
    }

    void etat(){
        System.out.println("\n================== [ ETAT ] =================="); 
        System.out.println("vous avez parcouru "+kmVoyageur+" sur "+KM_VOYAGE+" km");
        System.out.println("Les natifs sont à "+(kmVoyageur-kmNatifs)+" km" );
        System.out.println("La fatigue de votre chameau est à "+fatigue+" sur "+MORT_FATIGUE);
        switch (fatigue){
            case 0:
                System.out.println("Le chameau est en forme");
                break;
            case 1:
                System.out.println("Le chameau est un peu fatigué");
                break;
            case 2:
                System.out.println("Le chameau est très fatigué");
                break;
            case 3:
                System.out.println("Le chameau est énormement fatigué!");
                break;
            case 4:
                System.out.println("Le chameau va mourir de fatigue ! Reposez vous !");
                break;              
                }
        System.out.println("Votre soif est à "+soif+" sur "+MORT_SOIF);
        switch (soif){
            case 0:
                System.out.println("Vous n'avez pas soif");
                break;
            case 1:
                System.out.println("Vous avez un peu soif");
                break;
            case 2:
                System.out.println("Vous avez très soif");
                break;
            case 3:
                System.out.println("Vous avez énormement soif !");
                break;  
            case 4:
                System.out.println("Vous allez mourir de soif ! Buvez !");
                break;                      
                }
        System.out.println("Il vous reste "+gourde+" gorgée(s) sur "+GOURDE_PLEINE);
        System.out.println("=============================================="); 
    }

    void run() {

        depart();
        difficulté();
        System.out.println("\n\n\n\n\n\n");
        

        etat();

        do {
            if (!tempete){
                event =random.nextInt(0,special+1);
                
                switch (event){
                    case 0:
                        System.out.println("\n================== [ EVENT ] =================");
                        System.out.println("Vous etes tombé sur un oasis !");
                        System.out.println("Cela vous est d'un grand repos et vous remplissez en totalité votre gourde ");
                        gourde=GOURDE_PLEINE;
                        fatigue=0;
                        System.out.println("Vous avez donc "+gourde+" gorgée(s)");
                        System.out.println("Vous vous etes reposé");
                        System.out.println("Désormais, la fatigue de votre chameau est à "+fatigue+"\n");
                        System.out.println("==============================================");
                        break;
                    case 1:
                        System.out.println("\n================== [ EVENT ] =================");
                        event=random.nextInt(1,3);
                        System.out.println("Vous etes tombé sur une tempete de "+event+" jours !");
                        System.out.println("Les natifs vont surement s'arreter camper, vous décidez d'avancer malgrès la tempete et votre vitesse réduite");
                        tempete = true;
                        jourstempete=event;
                        KM_NORM_MIN = 5; 
                        KM_NORM_MAX = 8; 
                        KM_RAP_MIN = 10;
                        KM_RAP_MAX = 15; 
                        System.out.println("\n==============================================");
                        break;
                    case 2:
                        System.out.println("\n================== [ EVENT ] =================");
                        event=random.nextInt(1,3);
                        System.out.println("Vous etes tombé sur une tempete de "+event+" jours !");
                        System.out.print("Les natifs vont surement s'arreter camper, vous décidez d'avancer malgrès la tempete et votre vitesse réduite");
                        tempete = true;
                        jourstempete=event;
                        KM_NORM_MIN = 5; 
                        KM_NORM_MAX = 8; 
                        KM_RAP_MIN = 10;
                        KM_RAP_MAX = 15; 
                        System.out.println("\n==============================================");
                        break;    
                    }
            }  

            if (tempete){
                if (jourstempete==0){
                    tempete=false; 
                    KM_NORM_MIN = 10; 
                    KM_NORM_MAX = 15; 
                    KM_RAP_MIN = 20;
                    KM_RAP_MAX = 30; 
                    System.out.println("\n================== [ EVENT ] =================");
                    System.out.println("La tempete s'estompe, vous devriez pouvoir avancer à vitesse normale");
                    System.out.println("==============================================");
                }
                jourstempete--;
            }

            if(!oasis && !tempete){
                distanatif = random.nextInt(0,AVANCE_NATIFS+1);
                if (distanatif==0){
                    distanceparcourue = random.nextInt(KM_RAP_MIN,KM_RAP_MAX+1);
                    kmNatifs+=distanceparcourue;
                }
                else if (distanatif==1){
                    distanceparcourue = random.nextInt(KM_NORM_MIN,KM_NORM_MAX+1);
                    kmNatifs+=distanceparcourue;
                }
            }
            
            options();
            
            System.out.println("\n\n\n\n\n\n");

            soif++;

            System.out.println("\n================== [ ACTION ] ================");

            switch (rep){
                case 1:
                    System.out.println(" "); 
                    if (gourde==0){
                        System.out.println("La gourde est vide");
                    }
                    else {
                        soif = 0;
                        gourde=gourde-1;
                        System.out.println("Vous avez bu une gorgée");
                        System.out.println("Votre soif est à 0");
                        System.out.println("Il vous reste "+gourde+" gorgée(s)\n");
                    }
                    break;
                case 2:
                    System.out.println(" "); 
                    fatigue=fatigue+1;
                    distanceparcourue = random.nextInt(KM_NORM_MIN,KM_NORM_MAX+1);
                    kmVoyageur+=distanceparcourue;
                    System.out.println("La distance parcourue est de "+distanceparcourue+", vous avez parcouru "+kmVoyageur+"\n");
                    break;
                case 3:
                    System.out.println(" "); 
                    fatigue=fatigue+2;
                    distanceparcourue = random.nextInt(KM_RAP_MIN,KM_RAP_MAX+1);
                    kmVoyageur+=distanceparcourue;
                    System.out.println("La distance parcourue est de "+distanceparcourue+", vous avez parcouru "+kmVoyageur+"\n");
                    break;
                case 4:
                    System.out.println(" "); 
                    System.out.println("La fatigue de votre chameau est à "+fatigue);
                    fatigue=0;
                    System.out.println("Vous vous etes reposé");
                    System.out.println("Désormais, la fatigue de votre chameau est à "+fatigue+"\n");
                    break;
                case 5:
                System.out.println(" "); 
                    if (random.nextInt(4)==0){
                        System.out.println("Vous avez trouvé de l'aide");
                        if (gourde>=GOURDE_PLEINE){
                            System.out.println("La gourde est déja pleine !");
                        }
                        else if((gourde+3)<=GOURDE_PLEINE){
                            gourde+=3;
                            System.out.println("Quelques gorgées ont été ajoutées, vous avez "+gourde+" gorgées\n");
                        }   
                        else {
                            gourde+=(3-((gourde+3)-GOURDE_PLEINE));
                            System.out.println("Quelques gorgées ont été ajoutées, vous avez "+gourde+" gorgées\n");
                        }
                    }
                    else {
                        System.out.println("Vous n'avez pas trouvé d'aide\n");
                    }
                    break;
                default:
                    System.out.println("\nOptions invalide !\n");
            }
            System.out.println("==============================================");

            etat();
        }
        while (rep!=0 && kmVoyageur<KM_VOYAGE && kmNatifs<kmVoyageur && soif<MORT_SOIF && fatigue<MORT_FATIGUE);

        System.out.println("\n=============== [ RESULTATS ] ================\n"); 
        if (kmVoyageur>=KM_VOYAGE){
            System.out.println("Bravo, vous avez gagné !\n");
        }
        if (kmNatifs>=kmVoyageur){
                System.out.println("Vous avez perdu ! Les natifs vous ont attrapé !\n");
        }
        if (soif>=MORT_SOIF){
            System.out.println("Vous avez perdu ! Vous etes mort de soif !\n");
        }
        if (fatigue>=MORT_FATIGUE){
            System.out.println("Vous avez perdu ! Le chameau est mort de fatigue !\n");
        }

        System.out.println("La Distance parcourue est de "+kmVoyageur+"\n");
        System.out.println("==============================================");
        
        menu();
    }
        
    void menu(){
        do {
            System.out.println("\n================== [ MENU ] =================="); 
            System.out.println("1. Nouvelle partie"); 
            System.out.println("0. Quitter le jeu");
            System.out.print("\nChoix : ");
            rep = in.nextInt();
            System.out.println("=============================================="); 
            System.out.println("\n\n\n\n\n\n");
            
            if (rep==1){
            parambase();
            run();
            }
            else  {
                System.out.println("\n============ [ FERMETURE DE JEU ] ============\n"); 
            }
        }
        while (rep>1);
    }

    public static void main(String[] args) {
        new Chameau().menu();
    }
}