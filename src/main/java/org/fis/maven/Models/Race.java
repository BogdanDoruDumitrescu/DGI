package org.fis.maven.Models;

public class Race {
    private String driverUserName;
    private String clientUserName;
    private static int pricePerKm;
    private int km;
    private int totalPrice;
    private String state;

    public Race(String driverUserName, String clientUserName, int km, int totalPrice, String state) {
        this.driverUserName = driverUserName;
        this.clientUserName = clientUserName;
        this.km = km;
        this.totalPrice = totalPrice;
        this.state = state;
    }

    public String getDriverUserName() {
        return driverUserName;
    }

    public void setDriverUserName(String driverUserName) {
        this.driverUserName = driverUserName;
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    public static int getPricePerKm() {
        return pricePerKm;
    }

    public static void setPricePerKm(int pricePerKm) {
        Race.pricePerKm = pricePerKm;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return pricePerKm == race.pricePerKm &&
                totalPrice == race.totalPrice &&
                km == race.km &&
                driverUserName.equals(race.driverUserName) &&
                clientUserName.equals(race.clientUserName);
    }

    @Override
    public int hashCode() {
        int result = 31 * driverUserName.hashCode() + clientUserName.hashCode();
        result = 31 * result + pricePerKm;
        result = 31 * result + km;
        result = 31 * result + totalPrice;
        result = 31 * result + state.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Race{" +
                "driverUserName='" + driverUserName + '\'' +
                ", clientUserName='" + clientUserName + '\'' +
                ", pricePerKm=" + pricePerKm +
                ", km=" + km +
                ", totalPrice=" + totalPrice +
                ", state='" + state + '\'' +
                '}';
    }
}
