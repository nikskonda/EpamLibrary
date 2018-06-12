package by.epam.java.training.model;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String address;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String address, String city, String country) {
        this.id = 0;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public Address(Integer id, String address, String city, String country) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.country = country;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Address address = (Address) obj;

        if (id == null){
            if (address.id != null) return false;
        }
        else if (!id.equals(address.id)) return false;

        if (address == null){
            if (address.address != null) return false;
        }
        else if (!address.equals(address.address)) return false;

        if (city == null){
            if (address.city != null) return false;
        }
        else if (!city.equals(address.city)) return false;

        if (country == null){
            if (address.country != null) return false;
        }
        else if (!country.equals(address.country)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        hashCode = prime * hashCode + (id != null ? id.hashCode() : 0);
        hashCode = prime * hashCode + (address != null ? address.hashCode() : 0);
        hashCode = prime * hashCode + (city != null ? city.hashCode() : 0);
        hashCode = prime * hashCode + (country != null ? country.hashCode() : 0);
        return hashCode;
    }
}
