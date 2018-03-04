
package lab3;

/**
 *  Class Person to keep data about item in ListView more nicely.
 * @author Matoušs Synek
 */
public class Person {
    String firstName;
    String lastName;
    String birthDate;
    
    Person(String f, String l, String b){
        firstName = f;
        lastName = l;
        birthDate = b;
    }
    
    @Override
    public String toString(){
        return lastName +", " +firstName;
    }
    
    public boolean equals(Person p){
        return (firstName.equals(p.firstName)) && (lastName.equals(p.lastName));
    }
    
    public void setFirstName(String f){
        firstName = f;
    }
    public void setLastName(String l){
        lastName = l;
    }
    public void setBirthDate(String b){
        birthDate = b;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getBirthDate(){
        return birthDate;
    }
}
