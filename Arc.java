
// arc d'un graphe
public class Arc {
	int origin; // origine de l'arc
	int destination; // destination de l'arc
	
	// constructeur
	public Arc(int o, int d) {
		this.origin = o;
		this.destination = d;
	}

	// redefinition de la fonction equals
	public boolean equals(Object o) {
		Arc a=(Arc)o;
		return this.origin == a.origin && this.destination == a.destination;
	}
	
	// redefinition du hashCode
	public int hashCode() {
		return Graph.c*this.origin + this.destination;
	}
	
}
