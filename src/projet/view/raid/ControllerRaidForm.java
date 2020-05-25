package projet.view.raid;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Raid;
import projet.view.EnumView;


public class ControllerRaidForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private TextField		textFieldNom;
	@FXML
	private DatePicker 		datePickerAnnee;
	@FXML
	private TextField		textFieldPrixI;
	@FXML
	private TextField		textFieldPrixR;
	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelRaid modelraid;

	Pattern pattern = Pattern.compile("(^[0-9][0-9].[0-9][0-9]$)|(^[0-9].[0-9]$)|(^[0-9].[0-9][0-9]$)|(^[0-9][0-9].[0-9]$)|(^[0-9]$)|(^.[0-9]$)");
	
	UnaryOperator<TextFormatter.Change> filter = c -> {
		String heure = c.getControlNewText();
		if (pattern.matcher(heure).matches()) {
			return c;
		} else {
			return null;
		}
	};
	
	StringConverter<Double> converter = new StringConverter<Double>() {

	    @Override
	    public Double fromString(String s) {
	        if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
	            return 0.0 ;
	        } else {
	            return Double.valueOf(s);
	        }
	    }


	    @Override
	    public String toString(Double d) {
	        return d.toString();
	    }
	};

	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Raid courant = modelraid.getCourant();
		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new IntegerStringConverter()  );
		textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
		UtilFX.bindBidirectional( datePickerAnnee.getEditor(), courant.anneeProperty(), new ConverterStringLocalDate() );
		if ( courant.getPrix_insc()!=null)
		{
		textFieldPrixI.textProperty().bindBidirectional( courant.prix_inscProperty(), new DoubleStringConverter() );
		TextFormatter<Double> tfD = new TextFormatter<>(filter);
		textFieldPrixI.setTextFormatter(tfD);
		textFieldPrixR.textProperty().bindBidirectional( courant.prix_repasProperty(), new DoubleStringConverter() );
		TextFormatter<Double> tfD2 = new TextFormatter<>(filter);
		textFieldPrixR.setTextFormatter(tfD2);
		}else {
			textFieldPrixI.textProperty().bindBidirectional( courant.prix_inscProperty(), new DoubleStringConverter() );
			TextFormatter<Double> tfD = new TextFormatter<>(converter, 0.0,filter);
			textFieldPrixI.setTextFormatter(tfD);
			textFieldPrixR.textProperty().bindBidirectional( courant.prix_repasProperty(), new DoubleStringConverter() );
			TextFormatter<Double> tfD2 = new TextFormatter<>(converter, 0.0,filter );
			textFieldPrixR.setTextFormatter(tfD2);
			
		}
		
	
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.RaidView );
	}
	
	@FXML
	private void doValider() {
		modelraid.validerMiseAJour();
		managerGui.showView( EnumView.RaidView );
	}
	
}
