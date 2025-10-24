package shojo.shopping.party.model;

public class Person extends Party {
    private String firstname;
    private String lastname;

    public Person (String firstname, String lastname, RoleType rol){
        super(firstname + " " + lastname ,  rol );
        this.firstname = firstname;
        this.lastname = lastname;

    }

    @Override
    public String getTipo(){
        return "Persona";
    }

    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
}
