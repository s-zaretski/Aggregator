package com.sphincs.vo;

public class Vacancy {

    private String title;
    private String salary;
    private String city;
    private String companyName;
    private String url;

    public Vacancy() {
    }

    public Vacancy(String title, String salary, String city, String companyName, String url) {
        this.title = title;
        this.salary = salary;
        this.city = city;
        this.companyName = companyName;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Vacancy vacancy = (Vacancy) o;

        if (city != null ? !city.equals(vacancy.city) : vacancy.city != null)
            return false;
        if (companyName != null ? !companyName.equals(vacancy.companyName) : vacancy.companyName != null)
            return false;
        if (salary != null ? !salary.equals(vacancy.salary) : vacancy.salary != null)
            return false;
        if (title != null ? !title.equals(vacancy.title) : vacancy.title != null)
            return false;
        if (url != null ? !url.equals(vacancy.url) : vacancy.url != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        //@formatter:off
        return "Vacancy{ " +
                "\ntitle='" + title + '\'' +
                ", \nsalary='" + salary + '\'' +
                ", \ncity='" + city + '\'' + "" +
                ", \ncompanyName='" + companyName + '\'' +
                ", \nurl='" + url + '\'' +
                "} \n";
        //@formatter:on
    }
}
