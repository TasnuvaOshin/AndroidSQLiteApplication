package khudrosoft.com.renataapplication.Download;

public class download_data {
    String Id;
    String Name;
    String Phoneno;
    String District;
    String Thana;
    String Gift;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getThana() {
        return Thana;
    }

    public void setThana(String thana) {
        Thana = thana;
    }

    public String getGift() {
        return Gift;
    }

    public void setGift(String gift) {
        Gift = gift;
    }

    public download_data() {
    }

    public download_data(String id, String name, String phoneno, String district, String thana, String gift) {
        Id = id;
        Name = name;
        Phoneno = phoneno;
        District = district;
        Thana = thana;
        Gift = gift;
    }
}
