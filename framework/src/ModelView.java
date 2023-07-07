package etu1831.framework;

import java.util.HashMap;

public class ModelView {
    private String vueRedirection;
    private HashMap<String, Object> data = new HashMap<>();
    //Ajout de valeur dans Hashmap data
    public void addItem(String stringToAdd, Object objectToAdd){
        this.getData().put(stringToAdd, objectToAdd);
    }

    public ModelView() {
    }

    public ModelView(String vueRedirection) {
        this.vueRedirection = vueRedirection;
    }

    public String getVueRedirection() {
        return vueRedirection;
    }

    public void setVueRedirection(String vueRedirection) {
        this.vueRedirection = vueRedirection;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

}
