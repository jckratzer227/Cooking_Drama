import javafx.scene.image.Image;

public class Floorplan extends Item {
    private int capacity;
    private Image restaurantView;

    public Floorplan(String name, int price, int capacity, Image restaurantView) {
        super(name, price);
        this.capacity = capacity;
        this.restaurantView = restaurantView;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Image getRestaurantView() {
        return restaurantView;
    }

    public void setRestaurantView(Image restaurantView) {
        this.restaurantView = restaurantView;
    }
}
