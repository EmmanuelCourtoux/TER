import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Methodes {

	public final int nbrCommandesMax = 5;
	public final int nbrMaxNoeudsCommande = 6;
	public final int nbrMaxNoeudsCourse = nbrCommandesMax * nbrMaxNoeudsCommande;
	public final double intervalleLivraison = 15.0;
	public final double tempsMinLivraison = 60.0;
	public final double tempsMaxEntreCollectes = 3.0; //300m -> min
	public final double tempsMaxCollectesLivraison = 30.0; //3km -> min

	//Retourne la prochain etape en utilisant adjacentNodesCollecte
	public Integer prochainEtape(Noeud A) {		
		//Tri les voisins par distance *******************A TESTER*********************
		TreeMap<ArrayList<Integer>, Integer> list = new TreeMap<ArrayList<Integer>, Integer>(A.sortByValues());
		//Retourne le voisin le plus proche
		Entry<ArrayList<Integer>, Integer> ent = list.firstEntry(); //Premier chemin
		Integer res = ent.getKey().get(1); //Dernier noeud premier chemin
		return res;
	}

	//***************A TESTER****************
	//Djikstra qui retourne la liste des ID des noeuds du chemin  
	public ArrayList<Integer> cheminEntreNoeud(Noeud n1, Noeud n2){
		ArrayList<Integer> chemin = new ArrayList<Integer>();		
		chemin = new Dijkstra(Data.G, n1.idNoeud, n2.idNoeud).givePath();
		return chemin;
	}

	//retourne la distance entre le point de collecte le plus Ã©loignÃ© du point de livraison et ce dernier => pour vérifier contrainte 3km
	public double distancePointLivraisonMax(ArrayList<Noeud> l, Noeud liv){
		//permet de définir l'intervalle aPourvoir
		double max = 0;
		for(Noeud n1 : l) {
			int Dist = new Dijkstra(Data.G, n1.idNoeud, liv.idNoeud).compute(); //Calcul dist max entre les pts de collecte et le point de livraison
			if(Dist > max) {
				max = Dist;
			}
		} 
		return max;
	}

	//retourne la distance entre le point de collecte le plus proche du point de livraison et ce dernier => pour connaitre dernier point intervalle collecte
	public double distancePointLivraisonMin(ArrayList<Noeud> l, Noeud liv){
		//permet de définir l'intervalle aPourvoir
		double min = Integer.MAX_VALUE;
		for(Noeud n1 : l) {
			int Dist = new Dijkstra(Data.G, n1.idNoeud, liv.idNoeud).compute(); //Calcul dist max entre les pts de collecte et le point de livraison
			if(Dist < min) {
				min = Dist;
			}
		} 
		return min;
	}

	//Applique un djikstra a tous les noeuds de la collecte deux à deux et retourne le temps max entre deux noeuds => verif contrainte 300m pour collecte ou pts livraison
	//Remplit la liste des distances aux autres noeuds de collectes pour chaque noeud
	public double rayonMaxCollecte(ArrayList<Noeud> l) {
		double max = 0;
		for(Noeud n1 : l) {
			for(Noeud n2 : l) {
				int Dist = new Dijkstra(Data.G, n1.idNoeud, n2.idNoeud).compute(); //Distance entre les deux noeuds
				ArrayList<Integer> chemin = cheminEntreNoeud(n1, n2);
				if(Dist > max) {
					max = Dist;
				}
				n1.addNodeCollecte(chemin, Dist);	//***************** PROBLEME on a que l'id des noeuds ***********************		
			}
		}
		return max;
	} 
	
	public boolean verifMutualisation(PlanDeRoute A, PlanDeRoute B) {
		//Djikstra entre dernier pt de collecte A et premier pt de coll B
		int DistCollAB = new Dijkstra(Data.G, A.collecte.Fin.idNoeud, B.collecte.Départ.idNoeud).compute();
		//Djikstra pt de livraison A à B
		int DistLivAB = new Dijkstra(Data.G, A.livraison.Départ.idNoeud, B.livraison.Départ.idNoeud).compute();
		//Djikstra dernière coll => plus proche liv
		int DistCollLiv = new Dijkstra(Data.G, A.collecte.Fin.idNoeud, B.livraison.Départ.idNoeud).compute();
		double TCollA = A.collecte.tempsExe;//temps intervalle collecte A
		double TMidA = A.aPourvoir.tempsExe;//temps aPourvoir A
		double TMidB = B.aPourvoir.tempsExe;//temps aPourvoir B
		if(DistCollAB + DistLivAB + TCollA + TMidA + DistCollLiv >= TMidB + Data.epsilon)//verif contraintes : DistCollAB + DistLivAB + tCollA + TMidA +distCollLiv < TMidB + epsilon;
			return false;
		//verif Heure de livraison suffisamment proche 
		//MaJ Commande + verif contrainte pts de coll max etc...
		return true;
	}


	public boolean verifierContraintes(Commande c) {
		boolean result = true;
		if(c.HeurelivraisonDemandee - c.HeureCommande < 60.0)//Contrainte heure de commande
			result = false;
		if(rayonMaxCollecte(c.getPtsCol()) > tempsMaxEntreCollectes) { //Contrainte de 300m
			result = result && false;
		}
		if(distancePointLivraisonMax(c.getPtsCol(), c.PtLiv) > tempsMaxEntreCollectes) { //Contrainte de 3km
			result = result && false;
		}
		return result;
	}


}
