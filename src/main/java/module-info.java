module com.example.cookingdrama {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cookingdrama to javafx.fxml;
    exports com.example.cookingdrama;
}