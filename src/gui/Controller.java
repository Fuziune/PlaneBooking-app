package gui;

import domain.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.Service;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }


ObservableList<Booking> bookingObservableList;
    @FXML
    private ComboBox<String> City=new ComboBox<>();

    List<String> comb(){
        List<Booking> list = service.getAll();

        // Use a Set to store unique Scity values
        Set<String> uniqueCities = new HashSet<>();
        for (Booking booking : list) {
            uniqueCities.add(booking.getScity());
        }

        // Convert the Set to a List or ObservableList if needed
        List<String> cityList = new ArrayList<>(uniqueCities);
        return cityList;
    }

    @FXML
    void CityBox(ActionEvent event) {
        String searchText = City.getValue();
        //System.out.println(searchText);
        List<Booking> filteredDocuments = service.getAll().stream()
                .filter(booking -> booking.getScity().contains(searchText) )
                .sorted(Comparator.comparing(Booking::getScity))
                .collect(Collectors.toList());

        bookingObservableList=FXCollections.observableArrayList(filteredDocuments);
        listB.setItems(bookingObservableList);
    }
    @FXML
    private ListView<Booking> listB=new ListView<>();

    void PopulateList() {
        ObservableList<Booking> DocumentList = FXCollections.observableList(service.getAll());
        listB.setItems(DocumentList);
    }

    public void initialize()  {
        PopulateList();
        City.setItems(FXCollections.observableList(comb()));
    }

    @FXML
    private TextField Tickets;

    @FXML
    private TextField Dcit;

    @FXML
    private TextField Scit;

    @FXML
    private Button Order;

    @FXML
    private TextArea price;

    @FXML
    void OrderB(ActionEvent event) {
        String sCity= String.valueOf(Scit.getText());
        String cCity= String.valueOf(Dcit.getText());
        System.out.println(String.valueOf(Tickets.getText()));
        int tik= Integer.parseInt(String.valueOf(Tickets.getText()));
        Booking bok=new Booking();

        List<Booking> list=service.getAll();
        for(Booking booking:list)
            if(booking.getScity().equals(sCity) && booking.getDcity().equals(cCity))
                bok=booking;
        if(bok.getNr_seats()-tik>=0)
        {bok.setNr_seats(bok.getNr_seats()-tik);
        service.update(bok);

        price.setText(String.valueOf(tik*bok.getPrice_ticket()));
        bookingObservableList=FXCollections.observableArrayList(service.getAll());
        listB.setItems(bookingObservableList);}
        else
        {
            price.setText(String.valueOf("Not enough space"));
        }
    }
    @FXML
    private Button Routes;

    @FXML
    void Sroutes(ActionEvent event) {
        List<Booking> llist=new ArrayList<>();
        String sCity= String.valueOf(Scit.getText());
        String cCity= String.valueOf(Dcit.getText());
        for(Booking booking:service.getAll())
            if(booking.getScity().equals(sCity) && booking.getDcity().equals(cCity))
                llist.add(booking);
        bookingObservableList=FXCollections.observableArrayList(llist);
        listB.setItems(bookingObservableList);
    }
    @FXML
    private Button refresh;
    @FXML
    void Refresh(ActionEvent event) {
        bookingObservableList=FXCollections.observableArrayList(service.getAll());
        listB.setItems(bookingObservableList);
    }

}


