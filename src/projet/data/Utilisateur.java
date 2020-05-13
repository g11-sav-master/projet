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
	protected final StringProperty		e_mail			= new SimpleStringProperty();
	protected final StringProperty		numTel			= new SimpleStringProperty();
	protected final Property<LocalDate>	dateNaissance	= new SimpleObjectProperty<>();
	protected final StringProperty		login			= new SimpleStringProperty();
	
	
	
	// Constructeurs
	
	public Utilisateur() {
	}

	public Utilisateur( int id, String nom, String prenom, String e_mail, String numTel, LocalDate dateNaissance, String login) {
		setIdUtilisateur(id);
		setNom(nom);
		setPrenom(prenom);
		setE_Mail(e_mail);
		setNumTel(numTel);
		setDateNaissance(dateNaissance);
		setLogin(login);
	}
	
	
	// Getters & setters

	
	
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
	
	public final StringProperty e_mailProperty() {
		return this.e_mail;
	}
	
	public final String getE_Mail() {
		return this.e_mailProperty().get();
	}
	
	public final void setE_Mail(final String e_mail) {
		this.e_mailProperty().set(e_mail);
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
	
	public final Property<Integer> idUtilisateurProperty() {
		return this.idUtilisateur;
	}
	
	public final Integer getIdUtilisateur() {
		return this.idUtilisateurProperty().getValue();
	}
	
	public final void setIdUtilisateur(final Integer idUtilisateur) {
		this.idUtilisateurProperty().setValue(idUtilisateur);
	}
	
	public final StringProperty loginProperty() {
		return this.login;
	}
	
	public final String getLogin() {
		return this.loginProperty().get();
	}

	public final void setLogin(final String login) {
		this.loginProperty().set(login);
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
