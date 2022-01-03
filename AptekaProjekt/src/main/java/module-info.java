module com.isjackalive {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.isjackalive.entity to org.hibernate.orm.core;
    opens com.isjackalive to javafx.fxml;

    exports com.isjackalive;
    exports com.isjackalive.entity;
    exports com.isjackalive.Controller;
    opens com.isjackalive.Controller to javafx.fxml;
}
