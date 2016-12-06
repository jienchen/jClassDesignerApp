/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.data;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Ji En Chen
 */
public class Box extends VBox{
    Text name;
    String pack;
    
    public Box(){
        super();
        name = new Text();
        pack = "default";
    }
    
    public Box(String text){
        setName(text);
        pack = "default";
    }
    
    public String getName(){
        return name.getText();
    }
    
    public String getPackage(){
        return pack;
    }
    
    public void setName(String name){
        this.name.setText(name);
    }
    
    public void setPackage(String pack){
        this.pack = pack;
    }
}
