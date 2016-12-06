
package jcd.data;

import java.io.IOException;
import jcd.JClassDesigner;
import jcd.gui.Workspace;
import saf.AppTemplate;
import saf.components.AppComponentsBuilder;
import saf.components.AppDataComponent;

/**
 *
 * @author Ji En Chen
 */
public class DataManager implements AppDataComponent{
    AppTemplate app;
    Workspace workspace;

    public DataManager(AppTemplate initApp) throws Exception {
        app = initApp;
        workspace = (Workspace)app.getWorkspaceComponent();
    }

    public DataManager() {
        app = new JClassDesigner();
        workspace = new Workspace();
    }
    
    @Override
    public void reset(){
        if(workspace == null)workspace = (Workspace)app.getWorkspaceComponent();
        workspace.reset();
    }
    
    public Workspace getWorkspace(){
        return workspace;
    }
    
    public void setWorkspace(Workspace workspace){
        this.workspace = workspace;
    }

}
