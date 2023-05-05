package etu001982.framework.Modelview;
public class ModelView {
    String View;
    public ModelView(String View){
        setView(View);
    }
    public ModelView(){}
    public String getView() {
        return View;
    }
    public void setView(String view) {
        View = view;
    }
}