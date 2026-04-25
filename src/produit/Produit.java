package produit;

public abstract class Produit implements IProduit {
	protected String nom;
	protected Unite unite;

	protected Produit(String nom, Unite unite) {
		this.nom = nom;
		this.unite = unite;
	}

	public String getNom() {
		return nom;
	}
	
	@Override
	public int calculerPrix(int prix) {
		return prix;
	}

	public abstract String decrireProduit();

}
