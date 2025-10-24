package shojo.shopping.party.model;

public class Organization extends Party {
    private String organizationName;

    public Organization(String organizationName, RoleType rol){
        super(organizationName,rol);
        this.organizationName = organizationName;

    }

    @Override
    public String getTipo(){
        return "Organizacion";
    }

    public String getOrganizationName(){
        return organizationName;
    }
}
