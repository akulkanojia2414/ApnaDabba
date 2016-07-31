package example.akul.apnadabba;

/**
 * Created by akul on 05-07-2016.
 */
public class DishesMenu {
    public String name;
    public int product_id;
    public String Description;
    public int price;
    public int today;
    public int counter;
    public int likes;
    public String image;

    public DishesMenu() {
        counter = 0;
    }

    public DishesMenu(int product_id, String name, String Description, int price) {
        this.product_id = product_id;
        this.name = name;
        this.Description = Description;
        this.price = price;
        this.counter = 0;
    }

}
