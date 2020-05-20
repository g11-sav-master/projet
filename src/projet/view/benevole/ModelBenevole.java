package projet.view.benevole;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.dao.DaoUtilisateur;
import projet.data.Benevole;
import projet.data.Utilisateur;

public class ModelBenevole {

	private final ObservableList<Benevole> liste = FXCollections.observableArrayList();

	private final Benevole courant = new Benevole();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoBenevole daoBenevole;
	@Inject
	private DaoUtilisateur daoUtilisateur;

	// Getters

	public ObservableList<Benevole> getListe() {
		return liste;
	}

	public Benevole getCourant() {
		return courant;
	}

	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoBenevole.listerTout());
	}

	// Actions

	public void preparerAjouter() {
		mapper.update(courant, new Benevole());
	}

	public void preparerModifier(Benevole item) {
		mapper.update(courant, daoBenevole.retrouver(item.getIdUtilisateur()));
	}

	public void validerMiseAJour() {

		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (courant.getNom() == null || courant.getNom().isEmpty()) {
			message.append("\nLe nom ne doit pas être vide.");
		} else if (courant.getNom().length() > 50) {
			message.append("\nLe nom est trop long : 50 maxi.");
		}
		if (courant.getPrenom() == null || courant.getPrenom().isEmpty()) {
			message.append("\nLe prenom ne doit pas être vide.");
		} else if (courant.getPrenom().length() > 50) {
			message.append("\nLe prenom est trop long : 50 maxi.");
		}
		if (courant.getE_Mail() == null || courant.getE_Mail().isEmpty()) {
			message.append("\nL'e-mail ne doit pas être vide.");
		} else if (courant.getE_Mail().length() > 225) {
			message.append("\nL'e-mail est trop long : 225 maxi.");
		} else if (!courant.getE_Mail().contains("@")) {
			message.append("\nL'e-mail ne possède pas le caractère @.");
		}
		if (courant.getNumTel() == null || courant.getNumTel().isEmpty()) {
			message.append("\nLe numéro de téléphone ne doit pas être vide.");
		} else if (courant.getNumTel().length() != 10) {
			message.append("\nLe numéro de téléphone n'a pas le bon nombre de numéro : 10.");
		} else {
			for (char c : courant.getNumTel().toCharArray()) {
				if (c > '9' || c < '0') {
					message.append("\nLe numéro de téléphone n'a pas le bon format, seul des chiffres sont acceptés.");
					break;
				}
			}
		}
		if (courant.getDateNaissance() == null) {
			message.append("\nLa date de naissance ne doit pas être vide.");
		} else if (courant.getDateNaissance().compareTo(LocalDate.now().minusYears(10)) >= 0) {
			message.append("\nLe bénévole doit au moins avoir 10 ans.");
		}
		if (courant.getLogin() == null || courant.getLogin().isEmpty()) {
			message.append("\nLe login ne doit pas être vide.");
		} else if (courant.getLogin().length() > 225) {
			message.append("\nLe login est trop long : 50 maxi.");
		} else {
			if (courant.getIdUtilisateur() != null) {
				actualiserListe();
				for (Utilisateur utilisateur : liste) {
					if (utilisateur.getIdUtilisateur() != courant.getIdUtilisateur()) {
						if (utilisateur.getLogin() != null && utilisateur.getLogin().equals(courant.getLogin())) {
							message.append(
									"\nLe login est déjà utilisé pour un autre utilisateur. Merci d'en choisir un autre");
							break;
						}
					}
				}
			}
		}
		if (courant.getId_role() == null) {
			message.append("\nLe rôle ne peut être vide.");
		} else {
			System.out.println(
					"Fichier ModelBenevole fonction valider mise à jour à completer en rajoutant DaoRoleBenevole");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (courant.getIdUtilisateur() == null) {
			// Insertion
			courant.setIdUtilisateur(daoBenevole.inserer(courant));
		} else {
			// modficiation
			daoBenevole.modifier(courant);
		}
	}

	public void supprimer(Benevole item) {

		daoBenevole.supprimer(item.getIdUtilisateur());
		System.out.println(UtilFX.findNext(liste, item));
		mapper.update(courant, UtilFX.findNext(liste, item));
	}
}
