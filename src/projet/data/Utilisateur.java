package projet.data;

import java.time.LocalDate;
import java.util.Objects;


import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilisateur {


	// Données observables
	
	protected final Property<Integer>	idUtilisateur	= new SimpleObjectProperty<>();
	protected final StringProperty		nom	 			= new SimpleStringProperty();
	protected final StringProperty		prenom			= new SimpleStringProperty();
	protected final StringProperty		mail			= new SimpleStringProperty();
	protected final StringProperty		numTel			= new SimpleStringProperty();
	protected final Property<LocalDate>	dateNaissance	= new SimpleObjectProperty<>();
	
	
	
	// Constructeurs
	
	

	public Utilisateur( int id, String nom, String prenom, String mail, String numTel, LocalDate dateNaissance) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setMail(mail);
		setNumTel(numTel);
		setDateNaissance(dateNaissance);
	}
	
	
	// Getters & setters

	public final Property<Integer> idProperty() {
		return this.idUtilisateur;
	}

	public final Integer getId() {
		return this.idProperty().getValue();
	}

	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final java.lang.String getNom() {
		return this.nomProperty().getValue();
	}
	
	public final void setNom(final java.lang.String nom) {
		this.nomProperty().setValue(nom);
	}
	
	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	
	public final java.lang.String getPrenom() {
		return this.prenomProperty().getValue();
	}
	
	public final void setPrenom(final java.lang.String prenom) {
		this.prenomProperty().setValue(prenom);
	}
	
	public final StringProperty mailProperty() {
		return this.mail;
	}
	
	public final String getMail() {
		return this.mailProperty().get();
	}
	
	public final void setMail(final String mail) {
		this.mailProperty().set(mail);
	}

	public final StringProperty numTelProperty() {
		return this.numTel;
	}
	
	public final String getNumTel() {
		return this.numTelProperty().get();
	}
	
	public final void setNumTel(final String numTel) {
		this.numTelProperty().set(numTel);
	}
	
	public final Property<LocalDate> dateNaissanceProperty() {
		return this.dateNaissance;
	}
	
	public final LocalDate getDateNaissance() {
		return this.dateNaissanceProperty().getValue();
	}
	
	public final void setDateNaissance(final LocalDate dateNaissance) {
		this.dateNaissanceProperty().setValue(dateNaissance);
	}
	
	// toString()
	@Override
	public String toString() {
		return getNom() + " " + getPrenom();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(idUtilisateur.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(idUtilisateur.getValue(), other.idUtilisateur.getValue() );
	}
}
