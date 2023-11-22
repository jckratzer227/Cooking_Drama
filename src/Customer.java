package finalProject;

public class Customer {
    private String RecipeOrder;

    public Customer () {};

    public Customer (String RecipeOrder) {
       this.RecipeOrder = RecipeOrder;
    }

    public void setOrder(String RecipeOrder) {
        this.RecipeOrder = RecipeOrder;
    }

    public String getOrder() {
        return RecipeOrder;
    }
}
