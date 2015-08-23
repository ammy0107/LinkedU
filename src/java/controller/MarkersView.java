package controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
public class MarkersView implements Serializable {

    private MapModel simpleModel;

    @PostConstruct
    public void init() {

        simpleModel = new DefaultMapModel();

        LoginController log = new LoginController();

        String getcoordinates = log.getStudent().getLatlong();
        String getName = log.getStudent().getFirstName();
        String[] parts = getcoordinates.split(",");
        String part1 = parts[0]; // 004
        String part2 = parts[1];
        Double lat = Double.parseDouble(part1);
        Double longi = Double.parseDouble(part2);
        LatLng coord1 = new LatLng(lat, longi);
        simpleModel.addOverlay(new Marker(coord1, getName));
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
}
