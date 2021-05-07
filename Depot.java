/*Date: 30/5/2019
Name: Gary Beale
Student Number: 3328065
Course: Seng1110*/
public class Depot
{
    //Here I define the array of products and the only depot variable, its name
    private String dname; 
    Product[] products = new Product[5];

    //This constructor sets the name to "" and creates 5 products in each depot
    public Depot()
    {
        dname = "";
        for(int i = 0; i < 5; i++)  
            products[i] = new Product();
    }

    //This is the method to set the depot name
    public void setDepotName(String dName)
    {
        dname = dName;
    }

    //The method here is called by the interface in order to show the private variable
    public String getDName()
    {
        return dname;
    }

    //Here the path for the variables for the products within the depots are set 
    public void setProdData(Product p, String name, double price, double weight, int quantity)
    {
        p.setName(name);
        p.setPrice(price);
        p.setWeight(weight);
        p.setQuantity(quantity);
    }

    //This is how the program sets the variables for products
    public void addProdData(int i, String newName, double newPrice, double newWeight, int newQuantity)
    {
        setProdData(products[i],newName,newPrice,newWeight,newQuantity);
    }

    //These 2 methods are just for setting the quantity of the product
    private void setQuantity(Product p, int quantity)
    {
        p.setQuantity(quantity);
    }    

    public void addQuantity(int i, int newQuantity)
    {
        setQuantity(products[i],newQuantity);

    }

    //These methods are just for calling the information of the specified products
    public void getProdName()
    {
        int i = 0;
        products[i].getProdName();
    }

    public void getProdPrice()
    {
        int i = 0;
        products[i].getProdPrice();
    }

    public void getProdWeight()
    {
        int i = 0;
        products[i].getProdWeight();
    }

    public void getProdQuantity()
    {
        int i = 0;
        products[i].getProdQuantity();
    }

    public void newProduct(int i)
    {
        products[i] = new Product();
    }
}
