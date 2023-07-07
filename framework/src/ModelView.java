package etu1831.framework;

import java.util.HashMap;

public class ModelView {
    private String vueRedirection;
    private HashMap<String, Object> data = new HashMap<>();
    private HashMap<String, Object> session = new HashMap<>();
    //Ajout de valeur dans Hashmap data
    public void addItem(String stringToAdd, Object objectToAdd){
        this.getData().put(stringToAdd, objectToAdd);
    }
    //Ajout de valeur dans Hashmap session
    public void addSession(String stringToAdd, Object objectToAdd){
        this.getSession().put(stringToAdd, objectToAdd);
    }

    public ModelView() {
    }

    public ModelView(String vueRedirection) {
        this.setVueRedirection(vueRedirection);
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

    public HashMap<String, Object> getSession() {
        return session;
    }

    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }

}
