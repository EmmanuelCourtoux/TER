import java.util.ArrayList;

public class Data {
	static Graph G; //Graphe d'application
	static double epsilon; // +/- 15min
	private ArrayList<Commande> ListeCommande; //Avant v�rifContraintes
	private ArrayList<Commande> ListeCommandeAcceptee; //Apr�s v�rifContraintes
	private ArrayList<PlanDeRoute> PlanDeRouteValide; //D�duit d'au dessus
	private ArrayList<PlanDeRoute> ListeCommandeVisible; //Commande visible 30 minutes avant l'heure de la premi�re collecte
	private ArrayList<Livreur> LivreursSyst�me; //Liste des livreurs du syst�me
	
	
	public Livreur randomLivreur(ArrayList<Livreur> l) {
		return null;
		//return un livreur au hasard
	}
	
	public Commande visibiliteCommande(Commande c) {
		//check si une commande doit devenir visible toute les min
		return null;
	}
	
	public boolean checkCommandeOutdated(PlanDeRoute A) {
		boolean res = true;
		//verif Heure actuelle + A.IntervalleCollecte.getTemps + temps de trajet derni�re collecte premi�re livraison > HeureMaxLivraison => FALSE
		return res;
	}
	
}
