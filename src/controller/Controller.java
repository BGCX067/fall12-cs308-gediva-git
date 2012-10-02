package controller;

import model.Model;


public class Controller {
    Model myModel;

    public Controller () {
        myModel = new Model();
    }

    public void loadFile () {
        myModel.loadFile();
    }
}
