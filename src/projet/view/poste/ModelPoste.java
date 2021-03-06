package projet.view.poste;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoPoste;
import projet.dao.DaoActionBenevole;
import projet.dao.DaoRaid;
import projet.data.Poste;
import projet.data.Raid;
import projet.view.raid.ModelRaid;

public class ModelPoste {

	private final ObservableList<Poste> liste = FXCollections.observableArrayList();

	private final Poste courant = new Poste();
	
	private final Property<Raid> raid = new SimpleObjectProperty<Raid>();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoPoste daoPoste;
	@Inject
	private DaoActionBenevole daoActionB;
	@Inject
	private ModelRaid modelRaid;
	@Inject
	private DaoRaid daoRaid;

	@PostConstruct
	public void initialize() {
		courant.id_raidProperty().addListener(c -> {
			raid.setValue(daoRaid.retrouver(courant.getId_raid()));
		});
	}
	// Getters

	public ObservableList<Poste> getListe() {
		return liste;
	}

	public Poste getCourant() {
		return courant;
	}
	public Property<Raid> raidProperty(){
		return raid;
	}
	
	public ObservableList<Raid> getRaid() {
		return modelRaid.getListe();
	}

	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoPoste.listerTout());
	}

	// Actions

	public void preparerAjouter() {
		mapper.update(courant, new Poste());
	}

	public void preparerModifier(Poste item) {
		mapper.update(courant, daoPoste.retrouver(item.getId_poste()));
	}

	public void validerMiseAJour() {
		courant.setId_raid(raid.getValue().getId());
		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (courant.getId_raid() == null || courant.getId_raid() == 0) {
			message.append("\nLe raid ne peut pas être vide.");
		} else if (daoRaid.retrouver(courant.getId_raid()) == null) {
			message.append("\nLe raid n'existe pas");
		}
		if (courant.getNbr_benev() == null || courant.getNbr_benev() <= 0) {
			message.append("\nLe nombre de benevole requis pour un poste ne peut pas valoir 0 ou être négatif.");
		}
		if (courant.getDescription() != null && courant.getDescription().length() > 250) {
			message.append("\nLa description ne peux comporter plus de 250 caractères");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (courant.getId_poste() == null) {
			// Insertion
			courant.setId_poste(daoPoste.inserer(courant));
		} else {
			// Modficiation
			daoPoste.modifier(courant);
		}
	}

	public void supprimer(Poste item) {

		if (daoActionB.retrouver(item.getId_poste()) != null) {
			daoActionB.supprimerPoste(item.getId_poste());
			daoPoste.supprimer(item.getId_poste());
			System.out.println(UtilFX.findNext(liste, item));
			mapper.update(courant, UtilFX.findNext(liste, item));
		}
		
		else
		{
			daoPoste.supprimer(item.getId_poste());
			System.out.println(UtilFX.findNext(liste, item));
			mapper.update(courant, UtilFX.findNext(liste, item));
		}

	}
}
