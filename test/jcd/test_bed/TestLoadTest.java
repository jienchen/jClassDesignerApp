/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.test_bed;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.layout.VBox;
import jcd.data.Connector;
import jcd.data.DataManager;
import jcd.file.FileManager;
import jcd.gui.Workspace;
import static jcd.test_bed.TestSave.createConnector;
import static jcd.test_bed.TestSave.createCoordinates;
import static jcd.test_bed.TestSave.resize;
import static jcd.test_bed.TestSave.setStyle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ji En Chen
 */
public class TestLoadTest {

    static DataManager dm;
    static FileManager fm;
    static Workspace ws;

    public TestLoadTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        dm = new DataManager();
        fm = new FileManager();
        ws = new Workspace();
        dm.setWorkspace(ws);
        addJUnit1(ws);
        try {
            fm.saveData(dm, "JUnitTest1.json");

            // Clear dm of all objects first
            dm.getWorkspace().setBoxes(new ArrayList<>());
            dm.getWorkspace().setNames(new ArrayList<>());
            dm.getWorkspace().setTypes(new ArrayList<>());
            dm.getWorkspace().setPackages(new ArrayList<>());
            dm.getWorkspace().setParents(new ArrayList<>());
            dm.getWorkspace().setVariables(new ArrayList<>());
            dm.getWorkspace().setMethods(new ArrayList<>());
            dm.getWorkspace().setConnectors(new ArrayList<>());

            fm.loadData(dm,"JUnitTest1.json");
               
        } catch (IOException e) {
            System.out.println("Something wrong with loading and saving");
        }
    }
    
    public static void addJUnit1(Workspace ws){
        VBox ThreadExample = new VBox();
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

        ws.getBoxes().add(ThreadExample);
        ws.getNames().add("ThreadExample");
        ws.getPackages().add("default");
        ws.getVariables().add(var);
        ws.getMethods().add(met);
        ws.getTypes().add("class");
        ws.getParents().add("");
        
        VBox Application = new VBox();
        setStyle(Application);
        resize(Application, 670, 0, 870, 140);

        var = new ArrayList<>();
        v = "";
        for (String variable : var) {
            v += variable;
            v += "\n";
        }

        met = new ArrayList<>();
        met.add("{abstract}");
        met.add("start(primaryStage : Stage) : void");
        m = "";
        for (String method : met) {
            m += method;
            m += "\n";
        }

        ws.getBoxes().add(Application);
        ws.getNames().add("{abstract}\nApplication");
        ws.getPackages().add("default");
        ws.getVariables().add(var);
        ws.getMethods().add(met);
        ws.getTypes().add("abstract");
        ws.getParents().add("");
        
        ArrayList<ArrayList<Double>> coords = new ArrayList<>();
        coords.add(createCoordinates(770.0, 140.0));
        coords.add(createCoordinates(770.0, 180.0));
        createConnector(ws, 0, 5, "triangle", coords);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of main method, of class TestLoad.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("Loading Test");
        DataManager dm = new DataManager();
        Workspace ws = new Workspace();
        dm.setWorkspace(ws);
        
    }

    @Test
    public void testThreadExampleName() {
        System.out.println("Testing ThreadExample name");
        assertEquals(dm.getWorkspace().getNames().get(0), "ThreadExample");
    }

    @Test
    public void testThreadExampleCooridnates() {
        System.out.println("Testing ThreadExample Cooridnates");
        assertEquals(50, dm.getWorkspace().getBoxes().get(0).getLayoutX(), 650);
        assertEquals(50, dm.getWorkspace().getBoxes().get(0).getLayoutY(), 650);
        assertEquals(50, dm.getWorkspace().getBoxes().get(0).getWidth(), 240);
        assertEquals(50, dm.getWorkspace().getBoxes().get(0).getHeight(), 500);
    }

    @Test
    public void testThreadExampleMethods() {
        System.out.println("Testing ThreadExample Methods");
        assertEquals(dm.getWorkspace().getMethods().get(0).get(0), "+start(primaryStage : Stage) : void");
        assertEquals(dm.getWorkspace().getMethods().get(0).get(3), "+doWork() : boolean");
    }

    @Test
    public void testApplicationType() {
        System.out.println("Testing Application type");
        assertEquals(dm.getWorkspace().getTypes().get(1), "abstract");
    }

    @Test
    public void testConnectorType() {
        System.out.println("Testing Connector type");
        assertEquals(dm.getWorkspace().getConnectors().get(0).getType(), "triangle");
    }

    @Test
    public void testFail() {
        System.out.println("Testing for an error");
        assertEquals(dm.getWorkspace().getConnectors().get(0).getType(), "false");
    }



}
