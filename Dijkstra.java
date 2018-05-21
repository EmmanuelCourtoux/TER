import java.awt.Color;
import java.util.ArrayList;
import java.util.PriorityQueue;

//Algorithme de Dijkstra
public class Dijkstra {
	final Graph g; // le graphe de travail
	final int n; // nombre de sommets de g
	final int source; // source du plus court chemin recherche
	final int dest; // destination du plus court chemin recherche
	//Fenetre f; // fenetre pour la visualisation
	int[] dist; // distances a la source
	int[] pred; // predecesseur de chaque sommet dans le parcours
	boolean[] settled; // sommets traites par l'algorithme
	PriorityQueue<Node> unsettled; // sommets a traiter
	int steps; // compteur du nombre d'etapes

	// constructeur
	Dijkstra(Graph g, int source, int   dest) {
		this.g = g;
		n = g.n;
		this.source = source;
		this.dest = dest;
		dist = new int[n];
		pred = new int[n];
		settled = new boolean[n];
		for (int i = 0; i < n; i++) {
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
			settled[i] = false;
		}
		dist[source] = 0;
		pred[source] = source;
		unsettled = new PriorityQueue<Node>();
		unsettled.add(new Node(source, 0));
		steps = 0;
	}

	// mise a jour de la distance, de la priorite, et du predecesseur d'un sommet
	void update(int y, int x) {
		int newDist = dist[x] + g.weight(x,y);
		if (dist[y] > newDist) {
			pred[y] = x;
			dist[y] = newDist;
			unsettled.add(new Node(y, newDist));
			//g.drawUnsettledPoint(f, y);
		}
	}

	// trouve le prochain sommet de unvisited non traite
	int nextNode() {
		while (!unsettled.isEmpty()) {
			int x = unsettled.poll().id;
			if (!settled[x]) return x;
		}
		return -1;
	}

	// une etape de l'algorithme Dijkstra
	int oneStep() {
		//slow();
		steps++;
		int x = nextNode();
		if (x == -1) return -1;
		settled[x] = true;
		//g.drawSettledPoint(f, x);
		for (int y : g.successors(x)) 
			update(y, x);
		return x;
	}

	// algorithme de Dijkstra complet
	int compute() {
		int x = source;
		while (x != dest && x != -1)
			x = oneStep();
		return (x == -1 ? -1 : dist[dest]);
	}

	//*****************A TESTER********************
	// retourne l'id des noeuds composant le plus court chemin
	public ArrayList<Integer> givePath() { 	
		ArrayList<Integer> res = new ArrayList<Integer>();
		int i = this.dest; //sommet i = destination
		while (this.pred[i] != -1 && this.pred[i] != i) { //parcours les prédecesseurs à partir du sommet
			res.add(this.pred[i]);
			i = this.pred[i];
		}
		return res;
	}	
}

// ralentisseur visualisation
/*void slow(){
	    if(f == null) return;
	    try {
	        Thread.sleep(5);
	    } catch (InterruptedException e) {}
	}*/

