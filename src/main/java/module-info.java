module kroryi.board {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;
    requires javafx.base;
    requires javafx.swing;
    requires java.desktop;


    opens kroryi.board to javafx.fxml,java.base ,javafx.graphics;
    opens kroryi.board.controller to javafx.fxml, javafx.base, javafx.controls;
    opens kroryi.board.dto to javafx.base;
    opens kroryi.board.util to javafx.base, javafx.fxml;
    exports kroryi.board;
}