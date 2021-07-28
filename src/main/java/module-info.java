module com.mycompany.chatvisual {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.chatvisual to javafx.fxml;
    exports com.mycompany.chatvisual;
    requires io.reactivex.rxjava3;
}
