package my.library.models;

import java.io.Serializable;

public class Publisher implements Serializable {
    private int publishers_id;
    private String name;
    private String website;
    private String address;

    public Publisher() {}

    public int getPublishers_id() {
        return publishers_id;
    }

    public void setPublishers_id(int publishers_id) {
        this.publishers_id = publishers_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
