package jcd.controller;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import jcd.data.DataManager;
import jcd.gui.Workspace;
import saf.AppTemplate;
/**
 *
 * @author Ji En Chen
 */
public class UMLEditController {
    AppTemplate app;
    DataManager dataManager;
    VBox selected;
    
    public UMLEditController(AppTemplate initApp) {
	app = initApp;
	dataManager = (DataManager)app.getDataComponent();
        selected = null;
    }

    public void handleNaming(String name) {
        if (selected != null){
            selected.getChildren().clear();
            Text label = new Text();
            label.setText(name);
            selected.getChildren().add(label);
        }
    }
    
}
