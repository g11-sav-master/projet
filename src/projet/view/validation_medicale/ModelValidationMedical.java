package projet.view.validation_medicale;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoValidationMedicale;
import projet.data.Participant;
import projet.data.ValidationMedicale;
import projet.view.participant.ModelParticipant;

public class ModelValidationMedical {

	private final ObservableList<ValidationMedicale> liste = FXCollections.observableArrayList();

	private final ValidationMedicale courant = new ValidationMedicale();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoValidationMedicale daoValidationMedicale;
	@Inject
	private ModelParticipant modelParticipant;

	// Getters

	public ObservableList<ValidationMedicale> getListe() {
		return liste;
	}
	public ObservableList<Participant> getParticipants(){
		return modelParticipant.getListe();
	}

	public ValidationMedicale getCourant() {
		return courant;
	}

	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoValidationMedicale.listerTout());
	}

	// Actions

	public void preparerAjouter() {
		modelParticipant.actualiserListe();
		mapper.update(courant, new ValidationMedicale());
	}

	public void preparerModifier(ValidationMedicale item) {
		modelParticipant.actualiserListe();
		mapper.update(courant, daoValidationMedicale.retrouver(item.getId_validation()));
	}

	public void validerMiseAJour() {

		// Vérifie la validité des données

		/*if (courant.getId_validation() == null ) {
			message.append("\nLe numero de validation ne peut pas être nul.");
		} else if (daoValidationMedicale.retrouver(courant.getId_validation()) == null) {
			message.append("\nLa validation n'existe pas");
		}
		if (courant.getParticipant() == null ) {
			message.append("\nVous ne pouvez pa");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}*/

		// Effectue la mise à jour

		if (courant.getId_validation() == null) {
			// Insertion
			courant.setId_validation(daoValidationMedicale.inserer(courant));
		} else {
			// Modficiation
			daoValidationMedicale.modifier(courant);
		}
	}

	public void supprimer(ValidationMedicale item) {

		if (daoValidationMedicale.retrouver(item.getId_validation()) != null) {
			daoValidationMedicale.supprimer(item.getId_validation());
			System.out.println(UtilFX.findNext(liste, item));
			mapper.update(courant, UtilFX.findNext(liste, item));
		}

	}
}
