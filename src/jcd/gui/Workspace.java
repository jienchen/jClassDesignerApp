package jcd.gui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;
import saf.ui.AppGUI;
import static jcd.PropertyType.*;
import jcd.controller.UMLEditController;
import jcd.data.Connector;
import saf.ui.AppMessageDialogSingleton;

/**
 * @author Ji En Chen
 */
public class Workspace extends AppWorkspaceComponent {

    AppTemplate app;

    AppGUI gui;

    FlowPane editToolbar;
    FlowPane zoomToolbar;
    Button selectButton;
    Button resizeButton;
    Button addClassButton;
    Button addInterfaceButton;
    Button removeButton;
    Button undoButton;
    Button redoButton;
    Button zoomInButton;
    Button zoomOutButton;
    VBox Checkboxes;
    CheckBox gridCheckBox;
    CheckBox snapCheckBox;
    ScrollPane canvas;
    Pane canvasPane;
    VBox properties;
    Text nameDisplay;
    TextField nameTF;
    Text packageDisplay;
    TextField packageTF;
    Text parentDisplay;
    Text variablesDisplay;
    Text methodsDisplay;
    ComboBox parentCB;
    HBox nameBox;
    HBox packageBox;
    HBox parentBox;
    HBox variablesBox;
    HBox methodsBox;
    GridPane variablesPane;
    GridPane methodsPane;
    Image plus;
    Image minus;
    ImageView plusVariable;
    ImageView minusVariable;
    ImageView plusMethod;
    ImageView minusMethod;
    ArrayList<VBox> boxes;
    ArrayList<String> names;
    ArrayList<String> packages;
    ArrayList<ArrayList> variables;
    ArrayList<ArrayList> methods;
    ArrayList<String> types;
    ArrayList<String> parents;
    ArrayList<Connector> connectors;
    int X = 50;
    int Y = 50;
    double initX;
    double initY;
    double clickX;
    double clickY;
    VBox newClass;
    int selectedIndex = -1;
    Effect highlightedEffect;

    UMLEditController umlEditController;

    static int FONT_SIZE = 24;

    public Workspace(AppTemplate initApp) throws IOException {
        // KEEP THIS FOR LATER
        app = initApp;

        // KEEP THE GUI FOR LATER
        gui = app.getGUI();
        
        variables = new ArrayList<>();
        methods = new ArrayList<>();

        layoutGUI();
        setupHandlers();
    }

    public Workspace() {
        setBoxes(new ArrayList<>());
        setNames(new ArrayList<>());
        setPackages(new ArrayList<>());
        setVariables(new ArrayList<>());
        setMethods(new ArrayList<>());
        setTypes(new ArrayList<>());
        setParents(new ArrayList<>());
        setConnectors(new ArrayList<>());

        canvasPane = new Pane();
        properties = new VBox();
    }

    private void layoutGUI() {
        editToolbar = new FlowPane();
        selectButton = gui.initChildButton(editToolbar, SELECT_ICON.toString(), SELECT_TOOLTIP.toString(), false);
        resizeButton = gui.initChildButton(editToolbar, RESIZE_ICON.toString(), RESIZE_TOOLTIP.toString(), false);
        addClassButton = gui.initChildButton(editToolbar, ADD_CLASS_ICON.toString(), ADD_CLASS_TOOLTIP.toString(), false);
        addInterfaceButton = gui.initChildButton(editToolbar, ADD_INTERFACE_ICON.toString(), ADD_INTERFACE_TOOLTIP.toString(), false);
        removeButton = gui.initChildButton(editToolbar, REMOVE_ICON.toString(), REMOVE_TOOLTIP.toString(), false);
        undoButton = gui.initChildButton(editToolbar, UNDO_ICON.toString(), UNDO_TOOLTIP.toString(), false);
        redoButton = gui.initChildButton(editToolbar, REDO_ICON.toString(), REDO_TOOLTIP.toString(), false);
        editToolbar.setPrefWidth(370);
        editToolbar.setHgap(2);

        zoomToolbar = new FlowPane();
        zoomInButton = gui.initChildButton(zoomToolbar, ZOOM_IN_ICON.toString(), ZOOM_IN_TOOLTIP.toString(), false);
        zoomOutButton = gui.initChildButton(zoomToolbar, ZOOM_OUT_ICON.toString(), ZOOM_OUT_TOOLTIP.toString(), false);
        gridCheckBox = new CheckBox("Grid");
        snapCheckBox = new CheckBox("Snap");
        gridCheckBox.setDisable(true);
        snapCheckBox.setDisable(true);
        Checkboxes = new VBox(5);
        Checkboxes.getChildren().add(gridCheckBox);
        Checkboxes.getChildren().add(snapCheckBox);
        Checkboxes.setPadding(new Insets(0, 0, 0, 10));
        zoomToolbar.getChildren().add(Checkboxes);
        zoomToolbar.setPrefWidth(180);
        zoomToolbar.setHgap(2);

        canvas = new ScrollPane();
        canvasPane = new Pane();
        canvasPane.setPrefSize(1500, 1500);
        canvas.setContent(canvasPane);
        workspace = new BorderPane();
        canvas.prefViewportHeightProperty().bind(workspace.prefHeightProperty());
        canvas.setPrefViewportWidth(1000);

        nameTF = new TextField();
        packageTF = new TextField();
        parentCB = new ComboBox();
        nameBox = new HBox();
        nameDisplay = new Text();
        nameDisplay.setText("Class: ");
        nameDisplay.setFont(Font.font("System Regular", 30));
        nameBox.getChildren().addAll(nameDisplay, nameTF);
        nameBox.setSpacing(100);
        nameBox.setAlignment(Pos.CENTER_LEFT);
        packageBox = new HBox();
        packageDisplay = new Text();
        packageDisplay.setText("Package: ");
        packageDisplay.setFont(Font.font("System Regular", FONT_SIZE));
        packageBox.getChildren().addAll(packageDisplay, packageTF);
        packageBox.setSpacing(82);
        packageBox.setAlignment(Pos.CENTER_LEFT);
        parentBox = new HBox();
        parentDisplay = new Text();
        parentDisplay.setText("Parent: ");
        parentDisplay.setFont(Font.font("System Regular", FONT_SIZE));
        parentCB.setPrefWidth(150);
        parentBox.getChildren().addAll(parentDisplay, parentCB);
        parentBox.setSpacing(100);
        parentBox.setAlignment(Pos.CENTER_LEFT);
        variablesBox = new HBox();
        methodsBox = new HBox();
        variablesPane = new GridPane();
        methodsPane = new GridPane();
        plus = new Image("file:/../images/Plus.png");
        minus = new Image("file:/../images/Minus.png");
        plusVariable = new ImageView(plus);
        minusVariable = new ImageView(minus);
        plusMethod = new ImageView(plus);
        minusMethod = new ImageView(minus);
        variablesDisplay = new Text();
        variablesDisplay.setText("Variables:");
        variablesDisplay.setFont(Font.font("System Regular", FONT_SIZE));
        variablesBox.getChildren().addAll(variablesDisplay, plusVariable, minusVariable);
        variablesBox.setSpacing(15);
        methodsDisplay = new Text();
        methodsDisplay.setText("Methods:");
        methodsDisplay.setFont(Font.font("System Regular", FONT_SIZE));
        methodsBox.getChildren().addAll(methodsDisplay, plusMethod, minusMethod);
        methodsBox.setSpacing(15);
        properties = new VBox();
        properties.prefHeightProperty().bind(workspace.prefHeightProperty());
        properties.setPrefWidth(200);

        ((BorderPane) workspace).setLeft(canvas);
        ((BorderPane) workspace).setCenter(properties);
        setBoxes(new ArrayList<>());
        setNames(new ArrayList<>());
        setPackages(new ArrayList<>());
        setTypes(new ArrayList<>());
        setParents(new ArrayList<>());
        setConnectors(new ArrayList<>());
        DropShadow dropShadowEffect = new DropShadow();
        dropShadowEffect.setOffsetX(0.0f);
        dropShadowEffect.setOffsetY(0.0f);
        dropShadowEffect.setSpread(1.0);
        dropShadowEffect.setColor(Color.YELLOW);
        dropShadowEffect.setBlurType(BlurType.GAUSSIAN);
        dropShadowEffect.setRadius(7);
        highlightedEffect = dropShadowEffect;
    }

    private void setupHandlers() {
        umlEditController = new UMLEditController(app);

        canvasPane.setOnMousePressed((MouseEvent e) -> {
            int i;
            double x = e.getX();
            double y = e.getY();
            initX = e.getX();
            initY = e.getY();
            outerLoop:
            for (i = (getBoxes().size() - 1); i >= -1; i--) {
                if (i == -1) {
                    selectedIndex = -1;
                    nameTF.clear();
                    packageTF.clear();
                    break;
                }
                VBox test = getBoxes().get(i);
                if (x > test.getLayoutX() && x < test.getLayoutX() + test.getWidth()
                        && y > test.getLayoutY() && y < test.getLayoutY() + test.getHeight()) {
                    selectedIndex = i;
                    nameTF.setText(getNames().get(selectedIndex));
                    packageTF.setText(getPackages().get(selectedIndex));
                    break outerLoop;
                }
            }
            if (selectedIndex != -1) {
                clickX = getBoxes().get(selectedIndex).getLayoutX();
                clickY = getBoxes().get(selectedIndex).getLayoutY();
            }
            gui.updateToolbarControls(false);
            reloadWorkspace();
        });

        addClassButton.setOnAction((ActionEvent e) -> {
            for (int i = 0; i < getBoxes().size(); i++) {
                String test = getNames().get(i);
                if (nameTF.getText().equals(test) && selectedIndex == -1 && !nameTF.getText().equals("")) {
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show("Duplicate Class", "This class already exists.");
                    return;
                }
            }
            VBox box;
            newClass = new VBox();
            newClass.setStyle("-fx-background-color: #FFFFFF;"
                    + "-fx-padding: 10;"
                    + "-fx-border-width: 1px;"
                    + "-fx-border-color: #000000;");
            Text nameText = new Text();
            nameText.setText("");
            newClass.getChildren().add(nameText);
            newClass.setAlignment(Pos.TOP_LEFT);
            newClass.setMinSize(150, 100);
            newClass.setLayoutX(X);
            newClass.setLayoutY(Y);
            getPackages().add("default");
            getNames().add("");
            box = newClass;
            canvasPane.getChildren().add(box);
            getBoxes().add(box);
            selectedIndex = getBoxes().size()-1;
            X += 50;
            Y += 50;
            reloadWorkspace();
            nameTF.setText("");
            packageTF.setText("default");
        });

        canvasPane.setOnMouseDragged((MouseEvent e) -> {
            double width = e.getX() - initX;
            double height = e.getY() - initY;
            if (selectedIndex != -1) {
                if (getBoxes().get(selectedIndex) != null) {
                    VBox box = getBoxes().get(selectedIndex);
                    box.setLayoutX(clickX + width);
                    box.setLayoutY(clickY + height);
                }
                gui.updateToolbarControls(false);
                reloadWorkspace();
            }
        });

        nameTF.textProperty().addListener((Observable e) -> {
            if (selectedIndex != -1) {
                if (getBoxes().get(selectedIndex) != null) {
                    VBox box = getBoxes().get(selectedIndex);
                    box.getChildren().remove(0);
                    Text nameText = new Text();
                    nameText.setText(nameTF.getText());
                    box.getChildren().add(0, nameText);
                    if(!names.isEmpty()){getNames().remove(selectedIndex);}
                    getNames().add(selectedIndex, nameTF.getText());
                }
                gui.updateToolbarControls(false);
                reloadWorkspace();
            }
        });

        packageTF.textProperty().addListener((Observable e) -> {
            if (selectedIndex != -1) {
                if (getBoxes().get(selectedIndex) != null) {
                    VBox box = getBoxes().get(selectedIndex);
                    getPackages().remove(selectedIndex);
                    getPackages().add(selectedIndex, packageTF.getText());
                }
                gui.updateToolbarControls(false);
                reloadWorkspace();
            }
        });
    }

    @Override
    public void reloadWorkspace() {
        for (VBox vbox : getBoxes()) {
            vbox.setEffect(null);
        }
        if (selectedIndex != -1) {
            getBoxes().get(selectedIndex).setEffect(highlightedEffect);
            loadClass();
        } else {
            properties.getChildren().clear();
        }
    }
    
    public void afterLoad(){
        for(int i = 0; i < boxes.size();i++){
            Text nameText = new Text();
            nameText.setText(names.get(i));
            boxes.get(i).getChildren().add(nameText);
        }
    }

    public void loadClass() {
        if(properties.getChildren().isEmpty())properties.getChildren().addAll(nameBox, packageBox, parentBox, variablesBox, variablesPane,
                methodsBox, methodsPane);
    }

    @Override
    public void initToolbars() {
        gui.addToTopPane(editToolbar);
        gui.addToTopPane(zoomToolbar);
    }
    


    public void reset() {
        setBoxes(new ArrayList<>());
        setNames(new ArrayList<>());
        setPackages(new ArrayList<>());
        selectedIndex = -1;
        canvasPane.getChildren().clear();
        reloadWorkspace();
    }

    @Override
    public void initStyle() {
        editToolbar.getStyleClass().add(CLASS_BORDERED_PANE);
        zoomToolbar.getStyleClass().add(CLASS_BORDERED_PANE);
        canvasPane.getStyleClass().add(CLASS_CANVAS);
        properties.getStyleClass().add(CLASS_PROPERTIES);
    }

    public Pane getCanvas() {
        return canvasPane;
    }

    /**
     * @return the boxes
     */
    public ArrayList<VBox> getBoxes() {
        return boxes;
    }

    /**
     * @return the names
     */
    public ArrayList<String> getNames() {
        return names;
    }

    /**
     * @return the packages
     */
    public ArrayList<String> getPackages() {
        return packages;
    }

    /**
     * @return the variables
     */
    public ArrayList<ArrayList> getVariables() {
        return variables;
    }

    /**
     * @return the methods
     */
    public ArrayList<ArrayList> getMethods() {
        return methods;
    }

    /**
     * @return the types
     */
    public ArrayList<String> getTypes() {
        return types;
    }

    /**
     * @param boxes the boxes to set
     */
    public void setBoxes(ArrayList<VBox> boxes) {
        this.boxes = boxes;
    }

    /**
     * @param names the names to set
     */
    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    /**
     * @param packages the packages to set
     */
    public void setPackages(ArrayList<String> packages) {
        this.packages = packages;
    }

    /**
     * @param variables the variables to set
     */
    public void setVariables(ArrayList<ArrayList> variables) {
        this.variables = variables;
    }

    /**
     * @param methods the methods to set
     */
    public void setMethods(ArrayList<ArrayList> methods) {
        this.methods = methods;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    /**
     * @return the parents
     */
    public ArrayList<String> getParents() {
        return parents;
    }

    /**
     * @param parents the parents to set
     */
    public void setParents(ArrayList<String> parents) {
        this.parents = parents;
    }

    
    public ArrayList<Connector> getConnectors() {
        return connectors;
    }

    public void setConnectors(ArrayList<Connector> connectors) {
        this.connectors = connectors;
    }
}
