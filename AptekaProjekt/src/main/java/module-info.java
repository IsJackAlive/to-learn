module com.isjackalive {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.isjackalive.entity to org.hibernate.orm.core;
    opens com.isjackalive to javafx.fxml, javafx.graphics;
    opens com.isjackalive.controller to javafx.fxml;

    exports com.isjackalive.entity;
    exports com.isjackalive.controller;
}
