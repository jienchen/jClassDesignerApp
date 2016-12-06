package jcd.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import jcd.data.Connector;
import jcd.data.DataManager;
import jcd.gui.Workspace;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;

/**
 *
 * @author Ji En Chen
 */
public class FileManager implements AppFileComponent {

    static final String JSON_NAME = "name";
    static final String JSON_PACKAGE = "package";
    static final String JSON_TYPE = "type";
    static final String JSON_PARENT = "parent";
    static final String JSON_X = "x";
    static final String JSON_Y = "y";
    static final String JSON_WIDTH = "width";
    static final String JSON_HEIGHT = "height";
    static final String JSON_VARIABLE = "variable";
    static final String JSON_METHOD = "method";
    static final String JSON_BEGIN = "begin";
    static final String JSON_END = "end";
    static final String JSON_COORD = "coordinates";

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        DataManager dataManager = (DataManager) data;
        // NOW BUILD THE JSON OBJCTS TO SAVE
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        ArrayList<VBox> boxes = dataManager.getWorkspace().getBoxes();
        ArrayList<String> names = dataManager.getWorkspace().getNames();
        ArrayList<String> packages = dataManager.getWorkspace().getPackages();
        ArrayList<ArrayList> variables = dataManager.getWorkspace().getVariables();
        ArrayList<ArrayList> methods = dataManager.getWorkspace().getMethods();
        ArrayList<String> types = dataManager.getWorkspace().getTypes();
        ArrayList<String> parents = dataManager.getWorkspace().getParents();
        ArrayList<Connector> connectors = dataManager.getWorkspace().getConnectors();
        for (int i = 0; i < boxes.size(); i++) {
            String name = names.get(i);
            String pack = packages.get(i);
            String parent = parents.get(i);
            String type = types.get(i);
            double x = boxes.get(i).getLayoutX();
            double y = boxes.get(i).getLayoutY();
            double width = boxes.get(i).getWidth();
            double height = boxes.get(i).getHeight();
            JsonArrayBuilder variableBuilder = Json.createArrayBuilder();
            JsonArrayBuilder methodBuilder = Json.createArrayBuilder();
            ArrayList variable = variables.get(i);
            ArrayList method = methods.get(i);
            for (int k = 0; k < variable.size(); k++) {
                variableBuilder.add((String) variable.get(k));
            }
            for (int j = 0; j < method.size(); j++) {
                methodBuilder.add((String) method.get(j));
            }

            JsonObject boxJson = Json.createObjectBuilder()
                    .add(JSON_TYPE, type)
                    .add(JSON_NAME, name)
                    .add(JSON_PACKAGE, pack)
                    .add(JSON_PARENT, parent)
                    .add(JSON_X, x)
                    .add(JSON_Y, y)
                    .add(JSON_WIDTH, width)
                    .add(JSON_HEIGHT, height)
                    .add(JSON_VARIABLE, variableBuilder.build())
                    .add(JSON_METHOD, methodBuilder.build())
                    .build();
            arrayBuilder.add(boxJson);
        }
        JsonArray boxesArray = arrayBuilder.build();

        JsonArrayBuilder connectorsBuilder = Json.createArrayBuilder();
        for (int l = 0; l < connectors.size(); l++) {
            Connector connector = connectors.get(l);
            ArrayList<ArrayList<Double>> coordinates = connector.getCoordinates();
            JsonArrayBuilder coord = Json.createArrayBuilder();
            for (int m = 0; m < coordinates.size(); m++) {
                JsonArrayBuilder pairBuilder = Json.createArrayBuilder();
                for (int n = 0; n < coordinates.get(m).size(); n++) {
                    pairBuilder.add(coordinates.get(m).get(n));
                }
                coord.add(pairBuilder.build());
            }
            JsonObject connectorJson = Json.createObjectBuilder()
                    .add(JSON_BEGIN, connector.getBegin())
                    .add(JSON_END, connector.getEnd())
                    .add(JSON_TYPE, connector.getType())
                    .add(JSON_COORD, coord.build())
                    .build();
            connectorsBuilder.add(connectorJson);
        }
        JsonArray connectorsArray = connectorsBuilder.build();

        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add("boxes", boxesArray)
                .add("connectors", connectorsArray)
                .build();

        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();

        // INIT THE WRITER
        OutputStream os = new FileOutputStream(filePath);
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(filePath);
        pw.write(prettyPrinted);
        pw.close();
    }

    @Override
    public void loadData(AppDataComponent adc, String filePath) throws IOException {
        DataManager dataManager = (DataManager) adc;
        dataManager.reset();
        Workspace workspace = dataManager.getWorkspace();

        // LOAD THE JSON FILE WITH ALL THE DATA
        JsonObject json = loadJSONFile(filePath);

        JsonArray jsonBoxesArray = json.getJsonArray("boxes");
        for (int i = 0; i < jsonBoxesArray.size(); i++) {
            JsonObject box = jsonBoxesArray.getJsonObject(i);
            workspace.getTypes().add(box.getString(JSON_TYPE));
            VBox vbox = new VBox();
            vbox = new VBox();
            vbox.setStyle("-fx-background-color: #FFFFFF;"
                    + "-fx-padding: 10;"
                    + "-fx-border-width: 1px;"
                    + "-fx-border-color: #000000;");
            workspace.getNames().add(box.getString(JSON_NAME));
            workspace.getPackages().add(box.getString(JSON_PACKAGE));
            ArrayList<String> var = new ArrayList<>();
            for (int k = 0; k < box.getJsonArray(JSON_VARIABLE).size(); k++) {
                var.add(box.getJsonArray(JSON_VARIABLE).getString(k));
            }
            workspace.getVariables().add(var);
            ArrayList<String> method = new ArrayList<>();
            for (int j = 0; j < box.getJsonArray(JSON_METHOD).size(); j++) {
                method.add(box.getJsonArray(JSON_METHOD).getString(j));
            }
            workspace.getMethods().add(method);
            vbox.setAlignment(Pos.TOP_LEFT);
            vbox.setMinSize(125, 100);
            vbox.setLayoutX(box.getInt(JSON_X));
            vbox.setLayoutY(box.getInt(JSON_Y));
            workspace.getBoxes().add(vbox);
            workspace.getCanvas().getChildren().add(vbox);
        }

        JsonArray jsonConnectorsArray = json.getJsonArray("connectors");
        for (int i = 0; i < jsonConnectorsArray.size(); i++) {
            JsonObject connector = jsonConnectorsArray.getJsonObject(i);
            JsonArray a = connector.getJsonArray(JSON_COORD);
            ArrayList<ArrayList<Double>> coo = new ArrayList<>();
            ArrayList<Double> co = new ArrayList<>();
            for (int j = 0; j < a.size(); j++) {
                JsonArray b = a.getJsonArray(j);
                    ArrayList<Double> c = new ArrayList<>();
                for (int k = 0; k < b.size(); k++) {
                    c.add(b.getInt(k) + 0.0);
                }
                coo.add(c);
            }
            Connector connect = new Connector(connector.getInt(JSON_BEGIN), connector.getInt(JSON_END),
                    connector.getString(JSON_TYPE), coo);
            workspace.getConnectors().add(connect);
        }
        workspace.afterLoad();
    }

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }

    @Override
    public void exportData(AppDataComponent adc, String filePath) throws IOException {
        DataManager dataManager = (DataManager) adc;
        Workspace workspace = dataManager.getWorkspace();
        ArrayList<String> names = dataManager.getWorkspace().getNames();
        ArrayList<String> packages = dataManager.getWorkspace().getPackages();
        ArrayList<ArrayList> variables = dataManager.getWorkspace().getVariables();
        ArrayList<ArrayList> methods = dataManager.getWorkspace().getMethods();
        ArrayList<String> types = dataManager.getWorkspace().getTypes();
        ArrayList<String> parents = dataManager.getWorkspace().getParents();
        ArrayList<Connector> connectors = dataManager.getWorkspace().getConnectors();
        for(int j = 0;j < names.size();j++){
            File file = new File(filePath + "/" + packages.get(j)+"/"+names.get(j)+".java");
            if(!file.exists()){
                file.createNewFile();
            }
        }

    }

    @Override
    public void importData(AppDataComponent adc, String string) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
