module org.hsd.inflab.se2fxclient {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    // commons.logging This is only important for vscode users
    requires commons.logging;
    requires org.json;

    opens org.hsd.inflab.se2fxclient.view to javafx.fxml;
    opens org.hsd.inflab.se2fxclient.controller to javafx.fxml;
    exports org.hsd.inflab.se2fxclient.controller;
    exports org.hsd.inflab.se2fxclient.view;
    exports org.hsd.inflab.se2fxclient.model;
}