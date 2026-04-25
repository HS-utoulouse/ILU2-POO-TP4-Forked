package scenarioTest;

import personnages.Gaulois;
import produit.Poisson;
import produit.Produit;
import produit.Sanglier;
import villagegaulois.Etal;
import villagegaulois.IEtal;
import villagegaulois.IVillage;

public class Scenario {

	public static void main(String[] args) {

		IVillage village = new IVillage() {
			private Etal[] marche = new Etal[3];
			private int nbEtal = 0;

			@Override
			public <P extends Produit> boolean installerVendeur(Etal<P> etal, Gaulois vendeur, P[] produit, int prix) {
				if (nbEtal < marche.length) {
					etal.installerVendeur(vendeur, produit, prix);
					marche[nbEtal] = etal;
					nbEtal++;
					return true;
				}
				return false;
			}

			@Override
			public void acheterProduit(String produit, int quantiteSouhaitee) {
				int restant = quantiteSouhaitee;
				for (int i = 0; i < nbEtal && restant > 0; i++) {
					int quantiteDisponible = marche[i].contientProduit(produit, restant);
					if (quantiteDisponible > 0) { // on rentre si le produit et present
						int prixPaye = marche[i].acheterProduit(quantiteDisponible);
						System.out.println("À l'étal n° " + (i + 1) + ", j'achète " + quantiteDisponible + " " + produit
								+ " et je paye " + prixPaye + " sous.");
						restant -= quantiteDisponible;
					}
				}
				System.out.println("Je voulais " + quantiteSouhaitee + " " + produit + ", j'en ai acheté "
						+ (quantiteSouhaitee - restant) + ".");
			}
		};

		Gaulois ordralfabetix = new Gaulois("Ordralfabétix", 9);
		Gaulois obelix = new Gaulois("Obélix", 20);
		Gaulois asterix = new Gaulois("Astérix", 6);

		Etal<Sanglier> etalSanglierObelix = new Etal<>();
		Etal<Sanglier> etalSanglierAsterix = new Etal<>();
		Etal<Poisson> etalPoisson = new Etal<>();

		Sanglier sanglier1 = new Sanglier(200, obelix);
		Sanglier sanglier2 = new Sanglier(150, obelix);
		Sanglier sanglier3 = new Sanglier(100, asterix);
		Sanglier sanglier4 = new Sanglier(50, asterix);

		Sanglier[] sangliersObelix = { sanglier1, sanglier2 };
		Sanglier[] sangliersAsterix = { sanglier3, sanglier4 };

		Poisson poisson1 = new Poisson("lundi");
		Poisson[] poissons = { poisson1 };
		System.out.println("test");

		IEtal[] marche = new IEtal[3];

		marche[0] = etalSanglierAsterix;
		marche[1] = etalSanglierObelix;
		marche[2] = etalPoisson;

		etalSanglierAsterix.installerVendeur(asterix, sangliersAsterix, 10);
		etalSanglierObelix.installerVendeur(obelix, sangliersObelix, 8);
		etalPoisson.installerVendeur(ordralfabetix, poissons, 7);

		System.out.println("--- État du Marché ---");
		for (IEtal etal : marche) {
			if (etal != null) {
				System.out.println(etal.etatEtal());
			}
		}

		System.out.println("2 sanglier pour " + marche[0].acheterProduit(2) + " sous");
		System.out.println("1 sanglier pour " + marche[1].acheterProduit(1) + " sous");
		System.out.println("1 sanglier pour " + marche[2].acheterProduit(1) + " sous");

		System.out.println("--- État du Marché ---");
		for (IEtal etal : marche) {
			if (etal != null) {
				System.out.println(etal.etatEtal());
			}
		}

		village.installerVendeur(etalSanglierAsterix, asterix, sangliersAsterix, 10);
		village.installerVendeur(etalSanglierObelix, obelix, sangliersObelix, 8);
		village.installerVendeur(etalPoisson, ordralfabetix, poissons, 5);

		System.out.println(village);

		village.acheterProduit("sanglier", 3);

		System.out.println(village);
	}

}
