package projet.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class ValidationMedicale {


	// Données observables
	private final Property<Integer>		id_validation	= new SimpleObjectProperty<>();
	private final Property<Participant> participant		= new SimpleObjectProperty<>();
	private final Property<LocalDate>	date_expiration = new SimpleObjectProperty<>();
	private final Property<Boolean>		valide			= new SimpleObjectProperty<>();
	
	
	// Constructeurs

	public ValidationMedicale() {}
	
	public ValidationMedicale(int id_validation,Participant participant, LocalDate date) {
		super();
		setId_validation(id_validation);
		setParticipant(participant);
		setDate_expiration(date);
		setValide();
	}
	
	// Getters & setters

	public final Property<Integer> id_validationProperty() {
		return this.id_validation;
	}
	

	public final Integer getId_validation() {
		return this.id_validationProperty().getValue();
	}
	

	public final void setId_validation(final Integer id_validation) {
		this.id_validationProperty().setValue(id_validation);
	}
	

	public final Property<Participant> participantProperty() {
		return this.participant;
	}
	

	public final Participant getParticipant() {
		return this.participantProperty().getValue();
	}
	

	public final void setParticipant(final Participant participant) {
		this.participantProperty().setValue(participant);
	}
	

	public final Property<LocalDate> date_expirationProperty() {
		return this.date_expiration;
	}
	

	public final LocalDate getDate_expiration() {
		return this.date_expirationProperty().getValue();
	}
	

	public final void setDate_expiration(final LocalDate date_expiration) {
		this.date_expirationProperty().setValue(date_expiration);
	}

	//Hashcode & Equals & toString
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(date_expiration,  id_validation, valide);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidationMedicale other = (ValidationMedicale) obj;
		return Objects.equals(date_expiration, other.date_expiration) 
				&& Objects.equals(id_validation, other.id_validation);
	}
	
	@Override
	public String toString() {
		Boolean valide = LocalDate.now().isBefore(getDate_expiration()); 
		return participant.getValue().getPrenom()+" "+participant.getValue().getNom()+ " "+ valide;
	}

	public final Property<Boolean> valideProperty() {
		return this.valide;
	}
	

	public final Boolean getValide() {
		return this.valideProperty().getValue();
	}
	

	public final void setValide() {
		this.valideProperty().setValue(this.getDate_expiration().isAfter(LocalDate.now()));
	}
	

	
	
	
}
