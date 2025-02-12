package cartes;

public abstract class Carte {

	//Attaque
    public static Carte FEU_ROUGE = new Attaque(Type.FEU);
    public static Carte LIMITE = new DebutLimite();
    public static Carte PANNE_ESSENCE = new Attaque(Type.ESSENCE);
    public static Carte CREVAISON = new Attaque(Type.CREVAISON);
    public static Carte ACCIDENT = new Attaque(Type.ACCIDENT);
    
    //Parade
    public static Carte FEU_VERT = new Parade(Type.FEU);
    public static Carte FIN_LIMITE = new FinLimite();
    public static Carte ESSENCE = new Parade(Type.ESSENCE);
    public static Carte ROUE_DE_SECOURS = new Parade(Type.CREVAISON);
    public static Carte REPARATION = new Parade(Type.ACCIDENT);
    
    // bottes
    public static Carte PRIORITAIRE = new Botte(Type.FEU);
    public static Carte CITERNE = new Botte(Type.ESSENCE);
    public static Carte INCREVABLE = new Botte(Type.CREVAISON);
    public static Carte  AS_DU_VOLANT = new Botte(Type.ACCIDENT);
    
    //Bornes
    public static Carte BORNE25 = new Borne(25);
    public static Carte BORNE50 = new Borne(50);
    public static Carte BORNE75 = new Borne(75);
    public static Carte BORNE100 = new Borne(100);
    public static Carte BORNE200 = new Borne(200);

}
