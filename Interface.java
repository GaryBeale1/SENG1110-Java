/*Date: 30/5/2019
Name: Gary Beale
Student Number: 3328065
Course: Seng1110*/
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Interface
{
    //This is where I define all the arrays and counts that transfer methods
    static Depot[] depots = new Depot[4];
    static boolean [] checkDep = new boolean [4];
    static boolean [] checkPro = new boolean [5];
    static Scanner console = new Scanner(System.in);
    static int depCount = 0;

    private void run() throws IOException{

        //Here the depot arrays are created
        for(int i = 0; i < 4; i++)
            depots[i] = new Depot();

        int choice;

        //This is the menu function that will run until 11 is entered and calls all the methods
        //The do will run the program until the user inputs 11, where the loop will end and the program will terminate
        do{
            System.out.print(("\nMenu\n1.  Add Depot\n2.  Remove Depot\n3.  Add Product\n4.  Remove Products\n5.  Depot List\n")+
                ("6.  Product List\n7.  Product Search\n8.  Cumulative Value\n9.  Import\n10. Export\n11. Exit\n: "));
            choice = console.nextInt();
            switch(choice){

                case 1: setDepot();
                break;

                case 2: removeDepot();
                break;

                case 3: setProduct();
                break;

                case 4: removeProduct();
                break;

                case 5: queryDepot();
                break;  

                case 6: queryProduct();
                break;

                case 7: productSearch();
                break;

                case 8: cumulativeValue();
                break;

                case 9: importer();
                break;

                case 10: export();
                break;

                case 11: System.out.print("Terminated\n");
                System.out.print("***********************");
                break;

                default: System.out.print("***********************");
                System.out.print("Invalid Input\n");
                System.out.print("***********************");
                break;
            }
        }while(choice!=11);
    }

    //This method is where the program creates a depot and allows the user to set the name
    public static void setDepot()
    {
        //Here I define the variables that are specific to the method
        String input;       
        System.out.print("***********************");
        if (depCount < 4) {
            System.out.print("\nDepot name = ");
            input = console.next();
            //Here the for loop checks all depots and sees if there is a depot with the same name
            for (int i = 0; i < 4; i++){
                checkDep[i] = input.equalsIgnoreCase(depots[i].getDName());
                if (checkDep[i] == true){
                    System.out.print("Depot With Same Name Already Exists\n");
                    return;
                }
            }
            //If no depots exist with the same name then a new depot will be created
            depots[depCount].setDepotName(input);
            System.out.print("Depot Created\n");
            System.out.print("***********************");
            depCount++;

        }
        //If the user has already created 4 depots, there will be an error message
        else {
            System.out.print("\nMax Depots Reached\n");
            System.out.print("***********************");
        }
    }

    //Here the user can delete a depot
    public static void removeDepot()
    {
        String input;
        System.out.print("***********************");
        System.out.print("\nDelete Which Depot?: ");
        input = console.next();
        //Once again, the program checks the users input against the exisiting depots, and if it comes back true it continues
        for (int i = 0;i < 4;i++){
            checkDep[i] = depots[i].getDName().equalsIgnoreCase(input);
            if (checkDep[i] == true){
                //if true, the program sets the depot to new and reduces the depot count
                depots[i] = new Depot();
                --depCount;
                System.out.print("Depot Deleted\n");
                System.out.print("***********************");
                return;
            }
        }
        System.out.print("No depots with that name\n");
        System.out.print("***********************");
    }

    //This method allows the user to add a product to a depot 
    public static void setProduct() 
    {
        String pName;
        String input;
        int pQuantity;
        double pPrice;
        double pWeight; 
        //First the user is asked for the depot to add the product to
        System.out.print("***********************");
        System.out.print("\nAdd Product To Which Depot?: ");
        input = console.next();
        for (int i = 0;i < 4;i++){
            checkDep[i] = depots[i].getDName().equalsIgnoreCase(input);
            //If the depot does exist the user is prompted to enter the products name, where the name is then compared to all other products names
            //within the depot, to see if they match
            if (checkDep[i] == true){
                System.out.print("Product name: ");
                pName = console.next();
                for (int count = 0;count < 5;count++){
                    checkPro[count] = pName.equalsIgnoreCase(depots[i].products[count].getProdName());
                    //If they do match, the program will display a message and the user can update the name and quantity
                    if (checkPro[count] == true){
                        System.out.print("Product "+depots[i].products[count].getProdName()+" already exisits in "+depots[i].getDName()+
                            "\nUpdating Quantity\n");
                        do{
                            System.out.print("Product quantity: ");
                            pQuantity = console.nextInt();
                        } while(pQuantity <= 0);
                        depots[i].addProdData(count, depots[i].products[count].getProdName(), depots[i].products[count].getProdPrice(), 
                            depots[i].products[count].getProdWeight(), pQuantity);
                        System.out.print("***********************");
                        return;
                    }
                }
                //If the name does not match, the program will search for an empty product and will query the user for information, so long as it
                //is positive
                for (int count = 0;count < 5;count++){
                    if (depots[i].products[count].getProdName() == ""){ 
                        do{
                            System.out.print("Product price: ");
                            pPrice = console.nextDouble();
                        }  while (pPrice <= 0);
                        do{
                            System.out.print("Product weight: ");
                            pWeight = console.nextDouble();
                        }   while (pWeight <= 0);
                        do{
                            System.out.print("Product quantity: ");
                            pQuantity = console.nextInt();
                        } while(pQuantity <= 0);
                        depots[i].addProdData(count, pName, pPrice, pWeight, pQuantity);
                        System.out.print("***********************");
                        return;
                    }
                }
                //The program will print out this message if there are no empty products, meaning the maximum products have vbeen reached
                System.out.print("Max Products in This Depot Reached");
                System.out.print("***********************");
                return;
            }
        }
        //The program will print out this message if there are no empty depots, meaning the maximum depots have vbeen reached
        System.out.print("No Depot With That Name\n");
        System.out.print("***********************");
        return;
    }

    //This method works in a similar fashion to the previous method, with the user being asked for the depot name, checkin if it returns true,
    //then asking for the product name and checking again
    public static void removeProduct()
    {
        String input;
        int amount,total;
        System.out.print("***********************");
        System.out.print("\nRemove Product From Which Depot?: ");
        input = console.next();

        for (int i = 0;i < 4;i++){
            checkDep[i] = depots[i].getDName().equalsIgnoreCase(input);
            if (checkDep[i] == true){
                System.out.print("Product name: ");
                input = console.next();
                for (int count = 0; count < 5; count++){
                    checkPro[i] = input.equalsIgnoreCase(depots[i].products[count].getProdName());
                    if (checkPro[i] == true){
                        //Here the user is asked about the quantity to remove and will only accept a value that is greater than 0 and less than
                        //or equal to the amount within the product
                        do{
                            do{
                                System.out.print("Amount To Remove: ");
                                amount = console.nextInt();
                                if (amount > depots[i].products[count].getProdQuantity()){
                                    System.out.print("\nAmount Entered Is Greater Than Amount In Depot\n");
                                    System.out.print("Please Try Again\n");
                                }
                                if (amount < 0){
                                    System.out.print("\nAmount Entered Is Less Than 0\n");
                                    System.out.print("Please Try Again\n");
                                }
                            }  while (amount > depots[i].products[count].getProdQuantity());
                        }while (amount < 0);
                        //This code does the maths and sets the quantity
                        total = depots[i].products[count].getProdQuantity()-amount;
                        depots[i].addQuantity(i,total);
                        System.out.print(amount+" item(s) of Product "+depots[i].products[count].getProdName()
                            +" removed from "+depots[i].getDName()+"\n");
                        if (depots[i].products[count].getProdQuantity() != 0){    
                            System.out.print("***********************");
                        }
                        //The if here checks if the product quantity is 0, and if it is will inform the user and will delete the depot
                        if (depots[i].products[count].getProdQuantity() == 0) {
                            System.out.print(depots[i].products[count].getProdName()+" quantity has been reduced to 0, and has"+
                                " been removed\n");
                            depots[i].newProduct(count);
                            System.out.print("***********************");
                        }
                        return;
                    }
                }
                System.out.print("No Products With That Name In This Depot\n");
                System.out.print("***********************");
                return;
            }
        }
        System.out.print("No Depots With That Name\n");
        System.out.print("***********************");
        return;
    }

    //The method here will print out all depots names and how many products the contain
    public static void queryDepot()
    {
        int prodCount = 0;
        System.out.print("***********************");
        for(int i = 0; i < 4; i++)
        {
            //This if sees if a depot is empty or not, and if it is not, will print the name and count the products with a for and if
            if (depots[i].getDName() != ""){
                prodCount = 0;
                for(int count = 0; count < 5; count++){
                    if (depots[i].products[count].getProdName() != ""){
                        prodCount++;
                    }
                }
                System.out.print("\n"+depots[i].getDName()+" has "+prodCount+" product(s)\n");
            }
        }
        if (depCount == 0){
            System.out.print("\nNo Depots\n");
        }
        System.out.print("***********************");
    }

    //This method works very similar to the previous method except instead of counting the products it gets the products information and prints it
    public static void queryProduct()
    {
        int prodCount;
        for(int i = 0; i < 4; i++)
        {
            prodCount = 0;
            depCount = 0;
            if (depots[i].getDName() != ""){
                if (i == 0) {System.out.print("***********************");}
                System.out.print("\nDepot "+i+" Name: "+depots[i].getDName()+"\n");
                for(int count = 0; count < 5; count++){
                    if (depots[i].products[count].getProdName() != ""){
                        System.out.print("\nProduct "+count+" Name: "+depots[i].products[count].getProdName()+"\n");
                        System.out.print("Product "+count+" Price: "+depots[i].products[count].getProdPrice()+"\n");
                        System.out.print("Product "+count+" Weight: "+depots[i].products[count].getProdWeight()+"\n");
                        System.out.print("Product "+count+" Quantity: "+depots[i].products[count].getProdQuantity()+"\n");
                    }
                    if (depots[i].products[count].getProdName() == ""){
                        prodCount++;
                    }
                    if (prodCount == 5){
                        System.out.print(depots[i].getDName()+" has no products\n");
                    }
                }
                System.out.print("***********************");
            }
            if (depots[i].getDName() != ""){
                depCount++;
            }
            if (depCount == 4){
                System.out.print("No Depots");
            }
        }
    }

    //This product search works off taking the users input and comapring it to all products, in all depots
    public static void productSearch()
    {
        String input;
        System.out.print("***********************");
        System.out.print("\nProduct name = ");
        input = console.next();
        for (int i = 0; i < 4; i++){
            for (int count = 0; count < 5; count++){
                checkPro[i] = input.equalsIgnoreCase(depots[i].products[count].getProdName());
                //this if will be true if the product is found and it will display information about it
                if (checkPro[i] == true){
                    System.out.print(depots[i].products[count].getProdName()+" exists in "+depots[i].getDName()+" with a quantity of "
                        +depots[i].products[count].getProdQuantity());
                    System.out.print("\n***********************");
                    return;
                }
            }
        }
        //If the if requirments are not met, the system will print out this message
        System.out.print("No Products With That Name\n");
        System.out.print("***********************");
    }

    //The method here gathers the quantity and price of all products in a user specified depot, and multipys them to get the cumulitive value
    public static void cumulativeValue()
    {
        String input;
        double  amount = 0;
        System.out.print("***********************");
        System.out.print("\nFind The Value Of Which Depot?: ");
        input = console.next();
        for (int i = 0;i < 4;i++){
            checkDep[i] = depots[i].getDName().equalsIgnoreCase(input);
            if (checkDep[i] == true){
                //This for loop is doing the math, as it gets the quantity of a product, multiplys it by the price, then adds it to the other totals
                for (int count = 0; count < 5; count++){
                    amount = depots[i].products[count].getProdQuantity()*depots[i].products[count].getProdPrice()+amount;
                }
                System.out.print(depots[i].getDName()+" has a cumulitive value of $"+amount+"\n");
                System.out.print("***********************");
            }
        }
    }

    //This importer method will import a .txt file so long as it is in the correct format
    public static void importer()throws IOException
    {
        System.out.print("***********************");
        System.out.print("\nFile name: ");
        String data,dname,pname="",depot;
        int quantity;
        double weight,price;
        String fileName = console.next();

        //The try loop here will run unless it cannot find the file, which it will print an error message
        try{
            File f = new File(fileName+".txt");
            //This if will run if the file can be read
            if(f.canRead()) {
                //Change next line to where the project is saved on your pc
                FileReader reader = new FileReader("C:\\seng1110\\Get Started\\Real Assignment\\"+fileName+".txt");
                Scanner fScn = new Scanner(new File (fileName+".txt"));
                //This while runs until there are no more lines in the document
                while(fScn.hasNextLine() ){
                    data = fScn.nextLine();
                    //These arrays are how the program splits the information to be able to read and set as different variables
                    String[] space = data.split(" ");
                    String[] dash = data.split("-");
                    dname= dash[0];
                    //The lable here makes it so the program can break, but contiune within the while loop
                    outerloop:
                    //This if runs so long as the depot in the file has a product, otherwise it will only add a depot
                    if (space.length > 2){
                        try{
                            //Here the variables for the product are set depending on their location in the line
                            pname = space[1];
                            //The if here checks if the file is not correctly formatted
                            if (space.length != 5){
                                throw new IndexOutOfBoundsException();
                            }
                            price =  Double.parseDouble(space[2]);
                            weight =  Double.parseDouble(space[3]);
                            quantity = Integer.parseInt(space[4]);
                            //These for loops see if there is a depot or product in the file that already exisits in the program with the same name
                            //and if so, will merge them accordingly
                            for (int i = 0; i < 4; i++){
                                if (depots[i].getDName().equalsIgnoreCase(dname)){
                                    for (int count = 0; count < 5; count++){
                                        if (pname.equalsIgnoreCase(depots[i].products[count].getProdName())){
                                            quantity = quantity+depots[i].products[count].getProdQuantity();
                                            depots[i].addProdData(count, depots[i].products[count].getProdName(), depots[i].products[count].getProdPrice(), 
                                                depots[i].products[count].getProdWeight(), quantity);
                                            break outerloop;
                                        }
                                    }
                                    for (int count = 0; count < 5; count++){
                                        if (depots[i].products[count].getProdName() == ""){
                                            depots[i].addProdData(count, pname, price, weight, quantity);
                                            break outerloop;
                                        }                  
                                    }
                                    System.out.print("Max Products in "+depots[i].getDName()+"\n");
                                    System.out.print("***********************");
                                    break outerloop;
                                }
                            }
                            for (int i = 0; i < 4; i++){
                                if (depots[i].getDName() == ""){
                                    depots[i].setDepotName(dname);
                                    ++depCount;
                                    for (int count = 0;count < 5;count++){
                                        if (depots[i].products[count].getProdName() == ""){ 
                                            depots[i].addProdData(count, pname, price, weight, quantity);
                                            break outerloop;
                                        }
                                    }
                                }
                                else if (i == 3) {
                                    System.out.print("Max Depots Reached, Importing Interuppted\n");
                                    break outerloop;
                                }
                            }
                        }catch (IndexOutOfBoundsException e) {
                            System.out.print("Incorrect Formatting For Depot "+dname+", Product "+pname+"\n");
                        }

                    }
                    //The else here will be triggered if the Depot has no products in the file
                    else{
                        for (int i = 0; i < 4; i++){
                            if (depots[i].getDName() == ""){
                                depots[i].setDepotName(dname);
                                depCount++;
                                break outerloop;
                            }
                            //This else if will be triggered if a depot is trying to be added when there are already 4 depots in the program
                            else if (i == 3){
                                System.out.print("Max Depots Reached, Importing Interuppted\n");
                                break outerloop;
                            }
                        }
                    }
                }
                System.out.print("All Readable Data Imported\n");
                System.out.print("***********************");
                fScn.close();
            }

            else {
                throw new FileNotFoundException();
            }

        }catch (FileNotFoundException e){
            System.out.print("File Not Found or Unreadble\n");
            System.out.print("***********************");
        }
    }

    //This method allows the user to export their data to a txt file
    public static void export()throws IOException
    {
        System.out.print("***********************");
        System.out.print("\nFile name: ");
        String fileName = console.next();
        int prodCount;

        PrintWriter writer = new PrintWriter(fileName+".txt");

        //These for loops and ifs check to see if the program needs to print a depot and a product or just a depot to the file and do just that
        for(int i = 0; i < 4; i++)
        {
            for(int count = 0; count < 5; count++){
                if ((depots[i].getDName() != "")&&(depots[i].products[count].getProdName() != "")){
                    writer.println(depots[i].getDName()+"-depot "+depots[i].products[count].getProdName()+" "+depots[i].products[count].getProdPrice()
                        +" "+depots[i].products[count].getProdWeight()+" "+depots[i].products[count].getProdQuantity());
                }
                else if ((depots[i].getDName() != "")&&(depots[i].products[0].getProdName() == "")){
                    writer.println(depots[i].getDName()+"-depot");
                    break;
                }
            }
        }
        System.out.print("Data Written To File\n");
        System.out.print("***********************");
        writer.close();

    }
    
    //This is how the program runs
    public static void main(String[] args) throws IOException
    {
        Interface bin = new Interface();
        bin.run();
    }
}