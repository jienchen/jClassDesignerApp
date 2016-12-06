/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.data;

import java.util.ArrayList;

/**
 *
 * @author Ji En Chen
 */
public class Connector {

    private int begin;
    private int end;
    private String type;
    private ArrayList<ArrayList<Double>> coordinates;

    public Connector(int begin, int end, String type, ArrayList<ArrayList<Double>> coordinates) {
        this.begin = begin;
        this.end = end;
        this.type = type;
        this.coordinates = coordinates;
    }

    /**
     * @return the begin
     */
    public int getBegin() {
        return begin;
    }

    /**
     * @param begin the begin to set
     */
    public void setBegin(int begin) {
        this.begin = begin;
    }

    /**
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the coordinates
     */
    public ArrayList<ArrayList<Double>> getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(ArrayList<ArrayList<Double>> coordinates) {
        this.coordinates = coordinates;
    }

}
