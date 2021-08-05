package Project1.Information.TaxiPackage;

import java.util.ArrayList;

public class InformationTaxiList {
    private static ArrayList<Taxi> taxiList = new ArrayList<>();
    private static ArrayList<SearchTaxi> searchTaxiList = new ArrayList<>();
    private static ArrayList<IdentificationIdTaxi> identificationIdTaxiList = new ArrayList<>();

    public InformationTaxiList() {
    }

    public void addTaxi(Taxi taxi){
        taxiList.add(taxi);
    }

    public void addSearchTaxi(SearchTaxi searchTaxi){
        searchTaxiList.add(searchTaxi);
    }

    public void addIdentificationIdTaxi(IdentificationIdTaxi identificationIdTaxi){
        identificationIdTaxiList.add(identificationIdTaxi);
    }

    public void deleteTaxi(){
        taxiList.clear();
    }

    public void deleteSearchTaxi(){
        searchTaxiList.clear();
    }

    public void deleteIdentificationIdTaxi(){
        identificationIdTaxiList.clear();
    }

    public static ArrayList<Taxi> getTaxiList() {
        return taxiList;
    }

    public static void setTaxiList(ArrayList<Taxi> taxiList) {
        InformationTaxiList.taxiList = taxiList;
    }

    public static ArrayList<SearchTaxi> getSearchTaxiList() {
        return searchTaxiList;
    }

    public static void setSearchTaxiList(ArrayList<SearchTaxi> searchTaxiList) {
        InformationTaxiList.searchTaxiList = searchTaxiList;
    }

    public static ArrayList<IdentificationIdTaxi> getIdentificationIdTaxiList() {
        return identificationIdTaxiList;
    }

    public static void setIdentificationIdTaxiList(ArrayList<IdentificationIdTaxi> identificationIdTaxiList) {
        InformationTaxiList.identificationIdTaxiList = identificationIdTaxiList;
    }
}
