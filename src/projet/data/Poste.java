package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Poste {
	private final Property<Integer> id_poste = new SimpleObjectProperty<>();
	private final Property<Integer> id_raid = new SimpleObjectProperty<>();
	private final Property<Integer> nbr_benev = new SimpleObjectProperty<>();
	
	//constructer
	public Poste() {};
	public Poste( int id_poste, int id_raid, int nbr_benev) {
		setId_poste(id_poste);
		setId_raid(id_raid);
		setNbr_benev(nbr_benev);
		
	}
	
	
	//getter & setter
	
	public final Property<Integer> id_posteProperty() {
		return this.id_poste;
	}
	
	public final Integer getId_poste() {
		return this.id_posteProperty().getValue();
	}
	
	public final void setId_poste(final Integer id_poste) {
		this.id_posteProperty().setValue(id_poste);
	}
	
	public final Property<Integer> id_raidProperty() {
		return this.id_raid;
	}
	
	public final Integer getId_raid() {
		return this.id_raidProperty().getValue();
	}
	
	public final void setId_raid(final Integer id_raid) {
		this.id_raidProperty().setValue(id_raid);
	}
	
	public final Property<Integer> nbr_benevProperty() {
		return this.nbr_benev;
	}
	
	public final Integer getNbr_benev() {
		return this.nbr_benevProperty().getValue();
	}
	
	public final void setNbr_benev(final Integer nbr_benev) {
		this.nbr_benevProperty().setValue(nbr_benev);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "poste :"+this.getId_poste();
	}

	
	
	

}
