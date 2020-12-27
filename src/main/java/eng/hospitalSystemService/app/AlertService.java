package eng.hospitalSystemService.app;

import java.util.ArrayList;
import java.util.List;

public class AlertService {

    private final List<Alert> inner = new ArrayList<>();

    public void add(Alert.Type type, String message){
        Alert alert = new Alert(type,message);
        this.inner.add(alert);
    }

public List<Alert> getAlerts(){
        List<Alert> ret = new ArrayList<>(this.inner);
        return ret;
}

public List<Alert> pull(){
    List<Alert> ret = new ArrayList<>(this.inner);
this.inner.clear();
return ret;
}

}
