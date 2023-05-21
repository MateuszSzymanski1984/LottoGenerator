module com.example.lottofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.rmi;

    opens com.example.lottofx to javafx.fxml;
    exports com.example.lottofx;
}