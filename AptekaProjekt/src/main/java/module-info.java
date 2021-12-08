module com.isjackalive {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.isjackalive to javafx.fxml;
    opens com.isjackalive.dbs to org.hibernate.orm.core;

    exports com.isjackalive;
    exports com.isjackalive.dbs;
}
