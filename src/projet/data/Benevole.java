package projet.data;

import java.time.LocalDate;
import java.util.Objects;


import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Benevole extends Utilisateur {


	// Données observables
	
	private final Property<Boolean>		possedePermis	= new SimpleObjectProperty<>();
	
	
	// Constructeurs

	public Benevole( int idUtilisateur, String nom, String prenom, String mail, String numTel, LocalDate dateNaissance, boolean possedePermis) {
		super(idUtilisateur, nom, prenom, mail, numTel, dateNaissance);
		setPossedePermis(possedePermis);
	}
	
	// Getters & setters

	public final Property<Boolean> possedePermisProperty() {
		return this.possedePermis;
	}
	
	public final Boolean getPossedePermis() {
		return this.possedePermisProperty().getValue();
	}
	
	public final void setPossedePermis(final Boolean possedePermis) {
		this.possedePermisProperty().setValue(possedePermis);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benevole other = (Benevole) obj;
		return Objects.equals(idUtilisateur.getValue(), other.idUtilisateur.getValue() );
	}
	
}
