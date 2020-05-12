package projet.data;

import java.time.LocalDate;
import java.util.Objects;


import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Benevole extends Utilisateur {


	// Données observables
	private final Property<Integer>		id_role	= new SimpleObjectProperty<>();
	private final Property<Boolean>		possedePermis	= new SimpleObjectProperty<>();
	
	
	// Constructeurs

	public Benevole() {
	}
	
	public Benevole( int idUtilisateur, String nom, String prenom, String e_mail, String numTel, LocalDate dateNaissance, boolean possedePermis) {
		super(idUtilisateur, nom, prenom, e_mail, numTel, dateNaissance);
		setPossedePermis(possedePermis);
	}
	
	public Benevole( Utilisateur utilisateur) {
		super(utilisateur.getIdUtilisateur(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getE_Mail(), utilisateur.getNumTel(), utilisateur.getDateNaissance());
		setPossedePermis(false); // choix par défaut
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
	
	public final Property<Integer> id_roleProperty() {
		return this.id_role;
	}
	
	public final Integer getId_role() {
		return this.id_roleProperty().getValue();
	}
	
	public final void setId_role(final Integer id_role) {
		this.id_roleProperty().setValue(id_role);
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
