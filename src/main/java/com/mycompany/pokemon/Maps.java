/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

/**
 *
 * @author User
 */
import java.util.*;

public class Maps {

    public static class City {

        String name;
        List<City> connections;

        public City(String name) {
            this.name = name;
            this.connections = new LinkedList<>();
        }

        public void connectTo(City city) {
            connections.add(city);
        }

        public List<City> getConnections() {
            return connections;
        }

        @Override
        public String toString() {
            return "[" + name + "]";
        }
    }

    private List<City> cities;

    private City palletTown;
    private City viridianCity;
    private City pewterCity;
    private City ceruleanCity;
    private City saffronCity;
    private City celadonCity;
    private City lavenderTown;
    private City vermilionCity;
    private City fuchsiaCity;
    private City cinnabarIsland;

    public Maps() {
        initializeMap();
    }

    private void initializeMap() {
        cities = new ArrayList<>();

        City palletTown = new City("PalletTown");
        City viridianCity = new City("ViridianCity");
        City pewterCity = new City("PewterCity");
        City ceruleanCity = new City("CeruleanCity");
        City saffronCity = new City("SaffronCity");
        City celadonCity = new City("CeladonCity");
        City lavenderTown = new City("LavenderTown");
        City vermilionCity = new City("VermilionCity");
        City fuchsiaCity = new City("FuchsiaCity");
        City cinnabarIsland = new City("CinnabarIsland");

        palletTown.connectTo(viridianCity);
        palletTown.connectTo(cinnabarIsland);
        viridianCity.connectTo(palletTown);
        viridianCity.connectTo(pewterCity);
        pewterCity.connectTo(viridianCity);
        pewterCity.connectTo(ceruleanCity);
        ceruleanCity.connectTo(pewterCity);
        ceruleanCity.connectTo(saffronCity);
        ceruleanCity.connectTo(lavenderTown);
        saffronCity.connectTo(ceruleanCity);
        saffronCity.connectTo(celadonCity);
        saffronCity.connectTo(lavenderTown);
        saffronCity.connectTo(vermilionCity);
        celadonCity.connectTo(saffronCity);
        celadonCity.connectTo(fuchsiaCity);
        lavenderTown.connectTo(saffronCity);
        lavenderTown.connectTo(vermilionCity);
        lavenderTown.connectTo(ceruleanCity);
        lavenderTown.connectTo(fuchsiaCity);
        vermilionCity.connectTo(lavenderTown);
        vermilionCity.connectTo(fuchsiaCity);
        vermilionCity.connectTo(saffronCity);
        fuchsiaCity.connectTo(vermilionCity);
        fuchsiaCity.connectTo(cinnabarIsland);
        fuchsiaCity.connectTo(lavenderTown);
        fuchsiaCity.connectTo(celadonCity);
        cinnabarIsland.connectTo(fuchsiaCity);
        cinnabarIsland.connectTo(palletTown);

        cities.add(palletTown);
        cities.add(viridianCity);
        cities.add(pewterCity);
        cities.add(ceruleanCity);
        cities.add(saffronCity);
        cities.add(celadonCity);
        cities.add(lavenderTown);
        cities.add(vermilionCity);
        cities.add(fuchsiaCity);
        cities.add(cinnabarIsland);
    }

    public List<City> checkConnection(String location) {
        for (City city : cities) {
            if (city.name.equals(location)) {
                return city.getConnections();
            }
        }
        return new ArrayList<>();
    }

    public void showMap(String currentLocation) {
    System.out.println(getCityNameWithAsterisk("PewterCity", currentLocation) + "-------------------" + getCityNameWithAsterisk("CeruleanCity", currentLocation) + "-----------------|");
    System.out.println("      |                                |                      |");
    System.out.println("      |                                |                      |");
    System.out.println("      |                                |                      |");
    System.out.println("      |                                |                      |");
    System.out.println("      |        " + getCityNameWithAsterisk("CeladonCity", currentLocation) + "----" + getCityNameWithAsterisk("SaffronCity", currentLocation) + "---------" + getCityNameWithAsterisk("LavenderTown", currentLocation));
    System.out.println("      |               |                |                      |");
    System.out.println(getCityNameWithAsterisk("ViridianCity", currentLocation) + "        |                |                      |");
    System.out.println("      |               |                |                      |");
    System.out.println("      |               |                |                      |");
    System.out.println("      |               |       " + getCityNameWithAsterisk("VermilionCity", currentLocation) + "----------------|");
    System.out.println("      |               |                                       |");
    System.out.println(getCityNameWithAsterisk("PalletTown", currentLocation) + "          |                                       |");
    System.out.println("      |               |                                       |");
    System.out.println("      |          " + getCityNameWithAsterisk("FuchsiaCity", currentLocation) + "--------------------------------|");
    System.out.println("      |               |");
    System.out.println("      |               |");
    System.out.println(getCityNameWithAsterisk("CinnabarIsland", currentLocation) + "------|");
    System.out.println("+----------------------------------------------------------------------+");
}

private String getCityNameWithAsterisk(String cityName, String currentLocation) {
    return cityName.equals(currentLocation) ? "[**" + cityName + "**]" : "[" + cityName + "]";
}
}
