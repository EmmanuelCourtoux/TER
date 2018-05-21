
public class PlanDeRoute {
	Intervalle collecte;
	Intervalle livraison;
	Intervalle aPourvoir;
	private double tempsDeTrajetCollecteLivraison;
	
	public PlanDeRoute(Intervalle c, Intervalle l, Intervalle mid) {
		this.collecte = c;
		this.livraison = l;
		this.aPourvoir = mid;
		this.tempsDeTrajetCollecteLivraison = new Dijkstra(Data.G, collecte.Fin.idNoeud, livraison.Départ.idNoeud).compute(); //Djikstra entre collecte.Fin et livraison.Départ;
	}
	
	
}
