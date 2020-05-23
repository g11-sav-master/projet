package projet.view.benevole;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoActionBenevole;
import projet.dao.DaoBenevole;
import projet.dao.DaoParticipeOrganisation;
import projet.dao.DaoRoleBenevole;
import projet.data.ActionBenevole;
import projet.data.Benevole;
import projet.data.ParticipeOrganisation;
import projet.data.Utilisateur;
import projet.data.RoleBenevole;

public class ModelBenevole {

	private final ObservableList<Benevole> liste = FXCollections.observableArrayList();
	
	private final ObservableList<RoleBenevole> listeRole = FXCollections.observableArrayList();

	private final Benevole courant = new Benevole();
	private final RoleBenevole roleSelected = new RoleBenevole();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoBenevole daoBenevole;
	@Inject
	private DaoRoleBenevole daoRoleBenevole;
	@Inject
	private DaoActionBenevole daoActionBenevole;
	@Inject
	private DaoParticipeOrganisation daoParticipeOrganisation;

	// Getters

	public ObservableList<Benevole> getListe() {
		return liste;
	}

	public Benevole getCourant() {
		return courant;
	}
	
	public RoleBenevole getRoleSelected() {
		return roleSelected;
	}
	
	public RoleBenevole getCourantRole() {
		actualiserListeRole();
		for (RoleBenevole role : listeRole) {
			if(role.getId_role() == courant.getId_role())
				return role;
		}
		return null;
	}
	
	public ObservableList<RoleBenevole> getRoleBenevoleListe() {
		return listeRole;
	}

	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoBenevole.listerTout());
	}
	
	public void actualiserListeRole() {
		listeRole.setAll(daoRoleBenevole.listerTout());
	}

	// Actions

	public void preparerAjouter() {
		mapper.update(courant, new Benevole());
	}

	public void preparerModifier(Benevole item) {
		mapper.update(courant, daoBenevole.retrouver(item.getIdUtilisateur()));
	}
	
	public void preparerAjouterRole() {
		mapper.update(roleSelected, new RoleBenevole());
	}

	public void preparerModifierRole(RoleBenevole item) {
		mapper.update(roleSelected, item);
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
			actualiserListeRole();
			ArrayList<Integer> lstIdRole = new ArrayList<Integer>();
			for (RoleBenevole role : listeRole) {
				lstIdRole.add(role.getId_role());
			}
			if(!lstIdRole.contains(courant.getId_role())) {
				message.append("\nLe rôle n'a pas un id valide.");
			}
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
	
	public void validerMiseAJourRole() {

		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (roleSelected.getNom_role() == null || roleSelected.getNom_role().isEmpty()) {
			message.append("\nLe nom ne doit pas être vide.");
		} else if (roleSelected.getNom_role().length() > 50) {
			message.append("\nLe nom est trop long : 50 maxi.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (roleSelected.getId_role() == null) {
			// Insertion
			roleSelected.setId_role(daoRoleBenevole.inserer(roleSelected));
		} else {
			// modficiation
			daoRoleBenevole.modifier(roleSelected);
			if(courant.getId_role() == roleSelected.getId_role()) {
				courant.setId_role(null);
				courant.setId_role(roleSelected.getId_role());
			}
		}
	}

	public void supprimer(Benevole item) {
		for (ActionBenevole action : daoActionBenevole.listerTout()) {
			if(action.getId_utilisateur() == item.getIdUtilisateur()) {
				StringBuilder message = new StringBuilder();
				message.append("\nImpossible de supprimer ce bénévole car des actions sont encore lié à ce dernier.");
				throw new ExceptionValidation(message.toString().substring(1));
			}
		}
		for (ParticipeOrganisation participeOrga : daoParticipeOrganisation.listerTout()) {
			if(participeOrga.getIdUtilisateur() == item.getIdUtilisateur()) {
				StringBuilder message = new StringBuilder();
				message.append("\nImpossible de supprimer ce bénévole car une participation à l'organisation d'un raid est encore lié à ce dernier.");
				throw new ExceptionValidation(message.toString().substring(1));
			}
		}
		
		daoBenevole.supprimer(item.getIdUtilisateur());
		mapper.update(courant, UtilFX.findNext(liste, item));
	}
	
	public void supprimerRole(RoleBenevole item) {
		for (Benevole benevole : liste) {
			if(benevole.getId_role() != null && benevole.getId_role() == item.getId_role()) {
				StringBuilder message = new StringBuilder();
				message.append("\nUn bénévole au moins possède ce rôle.");
				throw new ExceptionValidation(message.toString().substring(1));
			}
		}
		if(courant.getId_role() == item.getId_role()) {
			StringBuilder message = new StringBuilder();
			message.append("\nLe bénévole sélectionné possède ce rôle. Merci d'en changer avant de supprimer ce rôle");
			throw new ExceptionValidation(message.toString().substring(1));
		}
		daoRoleBenevole.supprimer(item.getId_role());
		mapper.update(roleSelected, UtilFX.findNext(listeRole, item));
	}
}
