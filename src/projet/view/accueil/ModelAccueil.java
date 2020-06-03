package projet.view.accueil;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import projet.dao.DaoActionBenevole;
import projet.dao.DaoFormeDuo;
import projet.dao.DaoParticipantDuo;
import projet.dao.DaoParticipeOrganisation;
import projet.dao.DaoPoste;
import projet.dao.DaoRaid;
import projet.dao.DaoParticipant;
import projet.dao.DaoValidationMedicale;

import projet.data.FormeDuo;
import projet.data.Participant;
import projet.data.ParticipantDuo;
import projet.data.ParticipeOrganisation;
import projet.data.Poste;
import projet.data.Raid;
import projet.data.ValidationMedicale;

public class ModelAccueil {
	
	private ObservableList<String> liste = FXCollections.observableArrayList();
	
	private final Participant courant = new Participant();
	
	// Autres champs
	
	@Inject
	private DaoParticipant daoParticipant;
	@Inject
	private DaoFormeDuo daoFormeDuo;
	@Inject
	private DaoValidationMedicale daoValidationMedicale;
	@Inject
	private DaoParticipantDuo daoParticipantDuo;
	@Inject
	private DaoParticipeOrganisation daoParticipeOrganisation;
	@Inject
	private DaoPoste daoPoste;
	@Inject
	private DaoActionBenevole daoActionBenevole;
	@Inject
	private DaoRaid 		daoRaid;
	
	// Getters
	
	public ObservableList<String> getListe() {
		return liste;
	}

	public Participant getCourant() {
		return courant;
	}
	
	// Actualisations

	public void actualiserListe() {
		liste.setAll(this.listerDossierAttentes());
	}
	
	// 
	
	public ObservableList<String> listerDossierAttentes()
	{
		ArrayList<Participant> participants = new ArrayList<Participant>();
		ObservableList<String> dossierEnAttente = FXCollections.observableArrayList();
		participants.addAll(daoParticipant.listerTout());
		for(Participant participant : participants)
		{
			ValidationMedicale validationMedicale = daoValidationMedicale.retrouverUtilisateur(participant.getIdUtilisateur());
			FormeDuo formeDuo = daoFormeDuo.retrouverUtilisateur(participant.getIdUtilisateur());
			ParticipantDuo participantDuo = daoParticipantDuo.retrouver(formeDuo.getIdPartDuo());
			
			if((LocalDate.now().isAfter(validationMedicale.getDate_expiration())) && !participantDuo.getPaiement_valide())
				dossierEnAttente.add(participant.getNom()+" "+participant.getPrenom()+" : dossier médicale et paiement en attente");
			else if((LocalDate.now().isAfter(validationMedicale.getDate_expiration())) && participantDuo.getPaiement_valide()) 
				dossierEnAttente.add(participant.getNom()+" "+participant.getPrenom()+" : dossier médicale en attente");
			else if((LocalDate.now().isBefore(validationMedicale.getDate_expiration())) && !participantDuo.getPaiement_valide())
				dossierEnAttente.add(participant.getNom()+" "+participant.getPrenom()+" : paiement en attente");
		}
		
		return dossierEnAttente;
	}
	
	public ObservableList<String> listerNbrRepas()
	{
		int nbRepasBenevole=0, nbRepasParticipant=0;
		ArrayList<ParticipeOrganisation> listParticipeOrganisation = new ArrayList<ParticipeOrganisation>();
		listParticipeOrganisation.addAll(daoParticipeOrganisation.listerTout());
		for(ParticipeOrganisation participeOrganisation : listParticipeOrganisation)
		{
			nbRepasBenevole+=participeOrganisation.getNbreRepas();
		}
		
		ArrayList<ParticipantDuo> listParticipantDuos = new ArrayList<ParticipantDuo>();
		listParticipantDuos.addAll(daoParticipantDuo.listerTout());
		
		for(ParticipantDuo participantDuo : listParticipantDuos)
		{
			nbRepasParticipant+=participantDuo.getNbr_repas();
		}
		
		ObservableList<String> listes = FXCollections.observableArrayList();
		listes.add("Bénévoles : "+nbRepasBenevole+" repas commandé(s)");
		listes.add("Participants : "+nbRepasParticipant+" repas commandé(s)");
		return listes;
	}
	
	public ObservableList<String> listerPosteNonRempli()
	{
		ObservableList<String> listes = FXCollections.observableArrayList();
		ArrayList<Raid> raids = new ArrayList<Raid>();
		raids.addAll(daoRaid.listerTout());
		ArrayList<Poste> postes = new ArrayList<Poste>();
		for(Raid raid : raids)
		{
			postes.addAll(daoPoste.listerPosteRaid(raid.getId()));
			for(Poste poste : postes)
			{
				if(daoActionBenevole.nombreBenevoleAttribue(poste.getId_poste(),raid.getId()) != poste.getNbr_benev())
					listes.add(Character.toString('\u261b') +raid.getNom_raid() + " " + raid.getAnnee().getYear()+" - id poste : "+poste.getId_poste()+" - "+daoActionBenevole.nombreBenevoleAttribue(poste.getId_poste(),raid.getId())+"/"+poste.getNbr_benev()+Character.toString('\u261a'));
				else
					listes.add(raid.getNom_raid()+" - id poste : "+poste.getId_poste()+" - "+daoActionBenevole.nombreBenevoleAttribue(poste.getId_poste(),raid.getId())+"/"+poste.getNbr_benev());
			}
			postes.clear();
		}
		return listes;
	}
	
}
