public class Person {
    private String name;
    private String line1;
    private String town;
    private String county;
    private String postCode;
    
    public Person(String name, String line1, String town, String county, String postCode) {
        this.name = name;
        this.line1 = line1;
        this.town = town;
        this.county = county;
        this.postCode = postCode;
    }
    
    public String getName() {
        return this.name;
    }
    public String getLine1() {
        return this.line1;
    }
    public String getTown() {
        return this.town;
    }
    public String getCounty() {
        return this.county;
    }
    public String getPostCode() {
        return this.postCode;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setLine1(String line1) {
        this.line1 = line1;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    
    @Override
    public String toString() {
        return this.name + ",\n" + this.line1 + ",\n" + this.town + ",\n" + this.county + ",\n" + this.postCode;
    }
}
    