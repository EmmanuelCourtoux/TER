import java.util.ArrayList;

public class Livreur {

	public int idLivreur;
	private PlanDeRoute pdr;
	public boolean actif;
	public Noeud prochaineEtape;
	public int volMax;
	public Noeud positionActuelle;
	private double fatigue;
	private double probaAccept;
	private ArrayList<Commande> CommandeAServir;

	public PlanDeRoute getPdr() {
		return pdr;
	}

	public void setPdr(PlanDeRoute pdr) {
		this.pdr = pdr;
	}

	public double getFatigue() {
		return fatigue;
	}

	public void setFatigue(double fatigue) {
		this.fatigue = fatigue;
	}

	public double getProbaAccept() {
		return probaAccept;
	}

	public void setProbaAccept(double probaAccept) {
		this.probaAccept = probaAccept;
	}

}
