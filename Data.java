import java.util.ArrayList;

public class Data {
	static Graph G; //Graphe d'application
	static double epsilon; // +/- 15min
	private ArrayList<Commande> ListeCommande; //Avant vérifContraintes
	private ArrayList<Commande> ListeCommandeAcceptee; //Après vérifContraintes
	private ArrayList<PlanDeRoute> PlanDeRouteValide; //Déduit d'au dessus
	private ArrayList<PlanDeRoute> ListeCommandeVisible; //Commande visible 30 minutes avant l'heure de la première collecte
	private ArrayList<Livreur> LivreursSystème; //Liste des livreurs du système
	
	
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
		//verif Heure actuelle + A.IntervalleCollecte.getTemps + temps de trajet dernière collecte première livraison > HeureMaxLivraison => FALSE
		return res;
	}
	
}
