package jcd.test_bed;

import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import jcd.data.Connector;
import jcd.data.DataManager;
import jcd.file.FileManager;
import jcd.gui.Workspace;

/**
 *
 * @author Ji En Chen
 */
public class TestSave {

    public static void main(String[] args) throws IOException {
        DataManager dm = new DataManager();
        FileManager fm = new FileManager();
        Workspace workspace = new Workspace();

        createThreadExample(workspace);   //0
        createCounterTask(workspace);     //1
        createDateTask(workspace);        //2
        createPauseHandler(workspace);    //3
        createStartHandler(workspace);    //4
        createApplication(workspace);     //5
        createEventHandler(workspace);    //6
        createReferences(workspace);      //7-16
        createConnectors(workspace);

        dm.setWorkspace(workspace);
        fm.saveData(dm, "DesignTest.json");
    }

    public static void createThreadExample(Workspace workspace) {
        VBox ThreadExample = new VBox();
        setStyle(ThreadExample);
        resize(ThreadExample, 650, 180, 890, 680);

        ArrayList<String> var = new ArrayList<>();
        var.add("+$START_TEXT : String");
        var.add("+$PAUSE_TEXT : String");
        var.add("-window : Stage");
        var.add("-appPane : BorderPane");
        var.add("-topPane : FlowPane");
        var.add("-startButton : Button");
        var.add("-pauseButton : Button");
        var.add("-scrollPane : ScrollPane");
        var.add("=textArea : TextArea");
        var.add("-dateThread : Thread");
        var.add("-dateTask : Task");
        var.add("-counterThread : Thread");
        var.add("-counterTask : Task");
        var.add("-work : boolean");
        String v = "";
        for (String variable : var) {
            v += variable;
            v += "\n";
        }

        ArrayList<String> met = new ArrayList<>();
        met.add("+start(primaryStage : Stage) : void");
        met.add("+startWork() : void");
        met.add("+pauseWork() : void");
        met.add("+doWork() : boolean");
        met.add("+appendText(textToAppend : String) : void");
        met.add("+sleep(timeToSleep) : void");
        met.add("-initLayout() : void");
        met.add("-initHnadlers() : void");
        met.add("-initWindow(initPrimaryStage : Stage) : void");
        met.add("-initThreads() : void");
        met.add("+$main(args : String[]) : void");
        String m = "";
        for (String method : met) {
            m += method;
            m += "\n";
        }

        workspace.getBoxes().add(ThreadExample);
        workspace.getNames().add("ThreadExample");
        workspace.getPackages().add("default");
        workspace.getVariables().add(var);
        workspace.getMethods().add(met);
        workspace.getTypes().add("class");
        workspace.getParents().add("");
    }

    public static void createCounterTask(Workspace workspace) {
        VBox CounterTask = new VBox();
        setStyle(CounterTask);
        resize(CounterTask, 160, 250, 440, 410);

        ArrayList<String> var = new ArrayList<>();
        var.add("-app : ThreadExample");
        var.add("-counter : int");
        String v = "";
        for (String variable : var) {
            v += variable;
            v += "\n";
        }

        ArrayList<String> met = new ArrayList<>();
        met.add("+CounterTask(initApp : ThreadExample)");
        met.add("#call(): void");
        String m = "";
        for (String method : met) {
            m += method;
            m += "\n";
        }

        workspace.getBoxes().add(CounterTask);
        workspace.getNames().add("CounterTask");
        workspace.getPackages().add("default");
        workspace.getVariables().add(var);
        workspace.getMethods().add(met);
        workspace.getTypes().add("class");
        workspace.getParents().add("");
    }

    public static void createDateTask(Workspace workspace) {
        VBox DateTask = new VBox();
        setStyle(DateTask);
        resize(DateTask, 160, 450, 440, 610);

        ArrayList<String> var = new ArrayList<>();
        var.add("-app : ThreadExample");
        var.add("-now : Date");
        String v = "";
        for (String variable : var) {
            v += variable;
            v += "\n";
        }

        ArrayList<String> met = new ArrayList<>();
        met.add("+DateTask(initApp : ThreadExample)");
        met.add("#call(): void");
        String m = "";
        for (String method : met) {
            m += method;
            m += "\n";
        }

        workspace.getBoxes().add(DateTask);
        workspace.getNames().add("DateTask");
        workspace.getPackages().add("default");
        workspace.getVariables().add(var);
        workspace.getMethods().add(met);
        workspace.getTypes().add("class");
        workspace.getParents().add("");
    }

    public static void createPauseHandler(Workspace workspace) {
        VBox PauseHandler = new VBox();
        setStyle(PauseHandler);
        resize(PauseHandler, 950, 260, 1190, 400);

        ArrayList<String> var = new ArrayList<>();
        var.add("-app : ThreadExample");
        String v = "";
        for (String variable : var) {
            v += variable;
            v += "\n";
        }

        ArrayList<String> met = new ArrayList<>();
        met.add("+PauseHandler(initApp : ThreadExample)");
        met.add("+handle(event : Event): void");
        String m = "";
        for (String method : met) {
            m += method;
            m += "\n";
        }

        workspace.getBoxes().add(PauseHandler);
        workspace.getNames().add("PauseHandler");
        workspace.getPackages().add("default");
        workspace.getVariables().add(var);
        workspace.getMethods().add(met);
        workspace.getTypes().add("class");

        setStyle(PauseHandler);
        workspace.getParents().add("");
    }

    public static void createStartHandler(Workspace workspace) {
        VBox StartHandler = new VBox();
        setStyle(StartHandler);
        resize(StartHandler, 950, 460, 1190, 600);

        ArrayList<String> var = new ArrayList<>();
        var.add("-app : ThreadExample");
        String v = "";
        for (String variable : var) {
            v += variable;
            v += "\n";
        }

        ArrayList<String> met = new ArrayList<>();
        met.add("+StartHandler(initApp : ThreadExample)");
        met.add("+handle(event : Event) : void");
        String m = "";
        for (String method : met) {
            m += method;
            m += "\n";
        }

        workspace.getBoxes().add(StartHandler);
        workspace.getNames().add("StartHandler");
        workspace.getPackages().add("default");
        workspace.getVariables().add(var);
        workspace.getMethods().add(met);
        workspace.getTypes().add("class");
        workspace.getParents().add("");
    }

    public static void createApplication(Workspace workspace) {
        VBox Application = new VBox();
        setStyle(Application);
        resize(Application, 670, 0, 870, 140);

        ArrayList<String> var = new ArrayList<>();
        String v = "";
        for (String variable : var) {
            v += variable;
            v += "\n";
        }

        ArrayList<String> met = new ArrayList<>();
        met.add("{abstract}");
        met.add("start(primaryStage : Stage) : void");
        String m = "";
        for (String method : met) {
            m += method;
            m += "\n";
        }

        workspace.getBoxes().add(Application);
        workspace.getNames().add("{abstract}\nApplication");
        workspace.getPackages().add("default");
        workspace.getVariables().add(var);
        workspace.getMethods().add(met);
        workspace.getTypes().add("abstract");
        workspace.getParents().add("");
    }

    public static void createEventHandler(Workspace workspace) {
        VBox EventHandler = new VBox();
        setStyle(EventHandler);
        resize(EventHandler, 1250, 380, 1410, 480);

        ArrayList<String> var = new ArrayList<>();
        String v = "";
        for (String variable : var) {
            v += variable;
            v += "\n";
        }

        ArrayList<String> met = new ArrayList<>();
        met.add("+handle(event : Event) : void");
        String m = "";
        for (String method : met) {
            m += method;
            m += "\n";
        }

        workspace.getBoxes().add(EventHandler);
        workspace.getNames().add("EventHandler");
        workspace.getPackages().add("default");
        workspace.getVariables().add(var);
        workspace.getMethods().add(met);
        workspace.getTypes().add("interface");
        workspace.getParents().add("");
    }

    public static void createReferences(Workspace workspace) {
        createReference(workspace, "Stage", 300, 830, 375, 890);         //7
        createReference(workspace, "BorderPane", 440, 830, 515, 890);    //8
        createReference(workspace, "FlowPane", 580, 830, 655, 890);      //9
        createReference(workspace, "Button", 720, 830, 795, 890);        //10
        createReference(workspace, "ScrollPane", 860, 830, 935, 890);    //11
        createReference(workspace, "TextArea", 1000, 830, 1075, 890);    //12
        createReference(workspace, "Thread", 1140, 830, 1215, 890);      //13
        createReference(workspace, "Task", 0, 400, 100, 435);            //14
        createReference(workspace, "Date", 0, 540, 100, 575);            //15
        createReference(workspace, "Platform", 520, 400, 595, 460);      //16

    }

    public static void createReference(Workspace workspace, String reference,
            int x, int y, int endx, int endy) {
        VBox box = new VBox();
        setStyle(box);
        resize(box, x, y, endx, endy);
        ArrayList<String> var = new ArrayList<>();
        ArrayList<String> met = new ArrayList<>();
        workspace.getBoxes().add(box);
        workspace.getNames().add(reference);
        workspace.getPackages().add("default");
        workspace.getVariables().add(var);
        workspace.getMethods().add(met);
        workspace.getTypes().add("class");
        workspace.getParents().add("");
    }

    public static void createConnectors(Workspace workspace) {
        ArrayList<ArrayList<Double>> coords = new ArrayList<>();
        coords.add(createCoordinates(770.0, 140.0));
        coords.add(createCoordinates(770.0, 180.0));
        createConnector(workspace, 0, 5, "triangle", coords);

        ArrayList<ArrayList<Double>> coords1 = new ArrayList<>();
        coords1.add(createCoordinates(440.0, 280.0));
        coords1.add(createCoordinates(650.0, 280.0));
        createConnector(workspace, 1, 0, "diamond", coords1);

        ArrayList<ArrayList<Double>> coords2 = new ArrayList<>();
        coords2.add(createCoordinates(440.0, 330.0));
        coords2.add(createCoordinates(545.0, 330.0));
        coords2.add(createCoordinates(545.0, 380.0));
        coords2.add(createCoordinates(650.0, 380.0));
        createConnector(workspace, 0, 1, "diamond", coords2);

        ArrayList<ArrayList<Double>> coords3 = new ArrayList<>();
        coords3.add(createCoordinates(440.0, 530.0));
        coords3.add(createCoordinates(545.0, 530.0));
        coords3.add(createCoordinates(545.0, 480.0));
        coords3.add(createCoordinates(650.0, 480.0));
        createConnector(workspace, 2, 0, "diamond", coords3);

        ArrayList<ArrayList<Double>> coords4 = new ArrayList<>();
        coords4.add(createCoordinates(440.0, 570.0));
        coords4.add(createCoordinates(650.0, 570.0));
        createConnector(workspace, 0, 2, "diamond", coords4);

        ArrayList<ArrayList<Double>> coords5 = new ArrayList<>();
        coords5.add(createCoordinates(160.0, 330.0));
        coords5.add(createCoordinates(130.0, 330.0));
        coords5.add(createCoordinates(130.0, 420.0));
        coords5.add(createCoordinates(100.0, 420.0));
        createConnector(workspace, 1, 14, "diamond", coords5);

        ArrayList<ArrayList<Double>> coords6 = new ArrayList<>();
        coords6.add(createCoordinates(160.0, 500.0));
        coords6.add(createCoordinates(130.0, 500.0));
        coords6.add(createCoordinates(130.0, 440.0));
        coords6.add(createCoordinates(100.0, 440.0));
        createConnector(workspace, 2, 14, "diamond", coords6);

        ArrayList<ArrayList<Double>> coords7 = new ArrayList<>();
        coords7.add(createCoordinates(160.0, 500.0));
        coords7.add(createCoordinates(130.0, 500.0));
        coords7.add(createCoordinates(130.0, 440.0));
        coords7.add(createCoordinates(100.0, 440.0));
        createConnector(workspace, 1, 16, "feather", coords7);

        ArrayList<ArrayList<Double>> coords8 = new ArrayList<>();
        coords8.add(createCoordinates(160.0, 500.0));
        coords8.add(createCoordinates(130.0, 500.0));
        coords8.add(createCoordinates(130.0, 440.0));
        coords8.add(createCoordinates(100.0, 440.0));
        createConnector(workspace, 2, 16, "feather", coords8);

        ArrayList<ArrayList<Double>> coords9 = new ArrayList<>();
        coords9.add(createCoordinates(260.0, 500.0));
        coords9.add(createCoordinates(330.0, 500.0));
        coords9.add(createCoordinates(430.0, 540.0));
        coords9.add(createCoordinates(500.0, 540.0));
        createConnector(workspace, 0, 3, "diamond", coords9);

        ArrayList<ArrayList<Double>> coords10 = new ArrayList<>();
        coords10.add(createCoordinates(260.0, 500.0));
        coords10.add(createCoordinates(330.0, 500.0));
        coords10.add(createCoordinates(430.0, 540.0));
        coords10.add(createCoordinates(500.0, 540.0));
        createConnector(workspace, 3, 0, "diamond", coords10);

        ArrayList<ArrayList<Double>> coords11 = new ArrayList<>();
        coords11.add(createCoordinates(360.0, 600.0));
        coords11.add(createCoordinates(340.0, 520.0));
        coords11.add(createCoordinates(510.0, 240.0));
        coords11.add(createCoordinates(240.0, 340.0));
        createConnector(workspace, 0, 4, "diamond", coords11);

        ArrayList<ArrayList<Double>> coords12 = new ArrayList<>();
        coords12.add(createCoordinates(260.0, 200.0));
        coords12.add(createCoordinates(330.0, 400.0));
        coords12.add(createCoordinates(230.0, 340.0));
        coords12.add(createCoordinates(200.0, 140.0));
        createConnector(workspace, 4, 0, "diamond", coords12);

        ArrayList<ArrayList<Double>> coords13 = new ArrayList<>();
        coords13.add(createCoordinates(360.0, 450.0));
        coords13.add(createCoordinates(130.0, 250.0));
        coords13.add(createCoordinates(230.0, 450.0));
        coords13.add(createCoordinates(300.0, 140.0));
        createConnector(workspace, 3, 6, "triangle", coords13);

        ArrayList<ArrayList<Double>> coords14 = new ArrayList<>();
        coords14.add(createCoordinates(260.0, 240.0));
        coords14.add(createCoordinates(330.0, 250.0));
        coords14.add(createCoordinates(330.0, 510.0));
        coords14.add(createCoordinates(200.0, 570.0));
        createConnector(workspace, 4, 6, "triangle", coords14);

        ArrayList<ArrayList<Double>> coords15 = new ArrayList<>();
        coords15.add(createCoordinates(560.0, 800.0));
        coords15.add(createCoordinates(630.0, 800.0));
        coords15.add(createCoordinates(730.0, 840.0));
        coords15.add(createCoordinates(600.0, 840.0));
        createConnector(workspace, 7, 0, "diamond", coords15);

        ArrayList<ArrayList<Double>> coords16 = new ArrayList<>();
        coords16.add(createCoordinates(160.0, 500.0));
        coords16.add(createCoordinates(230.0, 600.0));
        coords16.add(createCoordinates(330.0, 740.0));
        coords16.add(createCoordinates(400.0, 840.0));
        createConnector(workspace, 8, 0, "diamond", coords16);

        ArrayList<ArrayList<Double>> coords17 = new ArrayList<>();
        coords17.add(createCoordinates(460.0, 600.0));
        coords17.add(createCoordinates(430.0, 600.0));
        coords17.add(createCoordinates(430.0, 640.0));
        coords17.add(createCoordinates(400.0, 640.0));
        createConnector(workspace, 9, 0, "diamond", coords17);

        ArrayList<ArrayList<Double>> coords18 = new ArrayList<>();
        coords18.add(createCoordinates(360.0, 700.0));
        coords18.add(createCoordinates(430.0, 700.0));
        coords18.add(createCoordinates(730.0, 740.0));
        coords18.add(createCoordinates(600.0, 740.0));
        createConnector(workspace, 10, 0, "diamond", coords18);

        ArrayList<ArrayList<Double>> coords19 = new ArrayList<>();
        coords19.add(createCoordinates(560.0, 400.0));
        coords19.add(createCoordinates(530.0, 400.0));
        coords19.add(createCoordinates(530.0, 570.0));
        coords19.add(createCoordinates(500.0, 570.0));
        createConnector(workspace, 11, 0, "diamond", coords19);

        ArrayList<ArrayList<Double>> coords20 = new ArrayList<>();
        coords20.add(createCoordinates(660.0, 600.0));
        coords20.add(createCoordinates(630.0, 600.0));
        coords20.add(createCoordinates(630.0, 640.0));
        coords20.add(createCoordinates(600.0, 640.0));
        createConnector(workspace, 12, 0, "diamond", coords20);

        ArrayList<ArrayList<Double>> coords21 = new ArrayList<>();
        coords21.add(createCoordinates(760.0, 700.0));
        coords21.add(createCoordinates(730.0, 700.0));
        coords21.add(createCoordinates(730.0, 740.0));
        coords21.add(createCoordinates(700.0, 740.0));
        createConnector(workspace, 13, 0, "diamond", coords21);
    }

    public static ArrayList<Double> createCoordinates(Double x, Double y) {
        ArrayList<Double> c = new ArrayList<>();
        c.add(x);
        c.add(y);
        return c;
    }

    public static void createConnector(Workspace workspace, int begin,
            int end, String type, ArrayList<ArrayList<Double>> coordinates) {
        Connector connector = new Connector(begin, end, type, coordinates);
        workspace.getConnectors().add(connector);
    }

    public static void setStyle(VBox box) {
        box.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-padding: 10;"
                + "-fx-border-width: 1px;"
                + "-fx-border-color: #000000;");
        box.setAlignment(Pos.TOP_LEFT);
        box.setMinSize(100, 100);
    }

    public static void resize(VBox box, int x, int y, int endx, int endy) {
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.resize(endx - x, endy - y);
    }

}
