package etu001982.framework.Modelview;
public class ModelView {
    private HashMap<String, Object> data;
    String View;
    public ModelView(String View){
        setView(View);
        data = new HashMap<>();
    }
    public ModelView(){}
    public String getView() {
        return View;
    }
    public void setView(String view) {
        View = view;
    }
    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public void addItem(String key, Object value) {
        data.put(key, value);
    }

}