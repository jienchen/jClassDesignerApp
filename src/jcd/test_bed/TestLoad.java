package jcd.test_bed;

import java.io.IOException;
import jcd.data.DataManager;
import jcd.file.FileManager;
import jcd.gui.Workspace;

/**
 *
 * @author Ji En Chen
 */
public class TestLoad {

    public static void main(String[] args) throws IOException {
        DataManager dm = new DataManager();
        FileManager fm = new FileManager();
        Workspace workspace = new Workspace();
        dm.setWorkspace(workspace);
        fm.loadData(dm, "DesignTest.json");
        for (int i = 0; i < workspace.getBoxes().size(); i++) {
            System.out.println("Name: " + workspace.getNames().get(i));
            System.out.println("Variables: " + workspace.getVariables().get(i));
            System.out.println("Methods: " + workspace.getMethods().get(i));
            System.out.println();
        }
        for (int k = 0; k < workspace.getBoxes().size(); k++) {
            System.out.println("Connectors: " + workspace.getConnectors().get(k).getCoordinates());
            System.out.println();
        }
    }

}
