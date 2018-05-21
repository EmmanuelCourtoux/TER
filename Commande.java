import java.util.ArrayList;

public class Commande {

	private ArrayList<Noeud> PtsCol;
	public Noeud PtLiv;
	public double HeureCommande;
	public double HeurelivraisonDemandee;
	public int volume;
	public boolean priseEnCharge;
	private double attractivite;
	
	public Commande(ArrayList<Noeud> c, Noeud l, double Hc, double Hl, int vol, double att) {
		this.PtsCol = c;
		this.PtLiv = l;
		this.HeureCommande = Hc;
		this.HeurelivraisonDemandee = Hl;
		this.volume = vol;
		this.priseEnCharge = false;
		this.attractivite = att;	
	}

	public ArrayList<Noeud> getPtsCol() {
		return PtsCol;
	}

	public void setPtsCol(ArrayList<Noeud> ptsCol) {
		PtsCol = ptsCol;
	}

	public double getAttractivite() {
		return attractivite;
	}

	public void setAttractivite(double attractivite) {
		this.attractivite = attractivite;
	}

}
