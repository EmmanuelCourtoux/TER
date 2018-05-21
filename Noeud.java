import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Noeud {

	public int idNoeud;
	static Map<ArrayList<Integer>, Integer> adjacentNodesCollecte = new HashMap<>(); //Stocke les distances et les chemins associées aux autres noeuds de la collecte, le noeud visé est le dernier noeud du chemin
	public double tempsTraversee;
	public double tempsStationnement;

	// constructeur
	Noeud(int id, double trav, double Stat){
		idNoeud = id;
		tempsTraversee = trav;
		tempsStationnement = Stat;
	}

	public void addNodeCollecte(ArrayList<Integer> chemin, int distance) {
		adjacentNodesCollecte.put(chemin, distance);
	}

	static Map sortByValues() { 
		Map<ArrayList<Integer>, Integer> sortedMap = 
				adjacentNodesCollecte.entrySet().stream()
			    .sorted(Entry.comparingByValue())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		return sortedMap;
		}


	}
