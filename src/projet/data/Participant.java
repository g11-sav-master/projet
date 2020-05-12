package projet.data;

import java.time.LocalDate;
import java.util.Objects;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Participant extends Utilisateur {


	// Données observables
	private final StringProperty	club			= new SimpleStringProperty();
	
	
	// Constructeurs

	public Participant() {
	}
	
	public Participant( int id, String nom, String prenom, String e_mail, String numTel, LocalDate dateNaissance, int idParticipant, String club) {
		super(id, nom, prenom, e_mail, numTel, dateNaissance);
		setClub(club);
	}
	
	// Getters & setters
	
	
	
	public final StringProperty clubProperty() {
		return this.club;
	}
	
	public final String getClub() {
		return this.clubProperty().get();
	}
	
	public final void setClub(final String club) {
		this.clubProperty().set(club);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		return Objects.equals(idUtilisateur.getValue(), other.idUtilisateur.getValue() );
	}
}
