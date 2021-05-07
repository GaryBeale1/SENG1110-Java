/*Date: 30/5/2019
Name: Gary Beale
Student Number: 3328065
Course: Seng1110*/

public class Product
{
    //Here I declare all the private variables to be used within this class
    private String name;
    private double price;
    private double weight;
    private int quantity;

    //This defines to java what a "product" must contain and sets everything to 0 or empty
    public Product()
    {
        name = "";
        price = 0;
        weight = 0;
        quantity = 0;
    }

    //The rest of this code just sets and retrieves the public methods so other classes can call on them
    public void setName(String newName)
    {
        name = newName;
    }

    public String getProdName()
    {
        return name;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }

    public double getProdPrice(){
        return price;
    }

    public void setWeight(double newWeight){
        weight = newWeight;
    }

    public double getProdWeight(){
        return weight;
    }

    public void setQuantity(int newQuantity){
        quantity = newQuantity;
    }

    public int getProdQuantity(){
        return quantity;
    }    
}