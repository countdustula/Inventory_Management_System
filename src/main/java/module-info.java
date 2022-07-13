module com.example.c482pa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.c482pa to javafx.fxml;
    exports com.example.c482pa;
}