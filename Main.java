package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.io.IOException;
import java.io.File;
import java.net.URLClassLoader;
import java.net.URL;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {

        // Загружаем класс драйвера
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("НЕ удалось загрузить драйвер ДБ.");
            e.printStackTrace();
            System.exit(1);
        }

        // Cоздаем соединение, здесь dbpath это путь к папке где будут хранится
        // файлы БД. dbname имя базы данных. SA это имя пользователя который
        // создается автоматически при создании БД пароль для него пустой. Если
        // такой базы данных нет она будет автоматически создана.
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:hsqldb:file:C:\\Users\\ilya3\\OneDrive\\Documents\\заочник\\Заказ 1553316\\test4", "SA", "");
        } catch (SQLException e) {
            System.err.println("НЕ удалось оздать соединение.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            Statement statement = connection.createStatement();
            // создаем таблицу со столбцами id и value.
            String query = "CREATE TABLE mytable (id IDENTITY , name VARCHAR(32), type VARCHAR(32), income VARCHAR(32), tax VARCHAR(32))";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                // если таблица уже существует, будет исключение, игнорируем его.
            }
            // добавляю записи в таблицу.
            query = "INSERT INTO mytable (name, type, income, tax) VALUES('Машенька', 'Тип1', '123', '12')";
            statement.executeUpdate(query);
            // достаю записи из таблицы
            query = "SELECT id, name, type, income, tax FROM mytable";
            ResultSet resultSet = statement.executeQuery(query);

            // распечатываю
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " "
                        + resultSet.getString(2) + " "
                        + resultSet.getString(3) + " "
                        + resultSet.getString(4) + " "
                        + resultSet.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

   //     Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Label lbl = new Label();//для вывода налога в результате
        Label lb2 = new Label();
        Label lb3 = new Label();
        Label lb4 = new Label();
        Label lb5 = new Label();
        Label lb6 = new Label();

        lb2.setText("Имя предприятия:");
        lb3.setText("Тип предприятия:");
        lb4.setText("Годовой доход (р.):");
        lb5.setText("Налог (%):");

        TextField tF1 = new TextField();//для имени предприятия
        TextField tF2 = new TextField();//для типа предприятия
        TextField tF3 = new TextField();//для годового дохода
        TextField tF4 = new TextField();//для процента налога

        Button btn1 = new Button("Расчитать налог");//кнопка подсчета налога
        Button btn2 = new Button("Поиск по названию");//кнопка поиска в бд
        Button btn3 = new Button("Добавить в базу");//кнопка удаления из бд
        Button btn4 = new Button("Удалить из базы");//кнопка удаления из бд

        btn1.setMinWidth(150);
        btn2.setMinWidth(150);
        btn3.setMinWidth(150);
        btn4.setMinWidth(150);

        tF1.setPrefColumnCount(55);
        tF2.setPrefColumnCount(25);
        tF3.setPrefColumnCount(25);
        tF4.setPrefColumnCount(25);


        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    if (Double.parseDouble(tF4.getText())>0 && Double.parseDouble(tF4.getText())<100){
                    lbl.setText("Налог: " + Double.parseDouble(tF3.getText())*Double.parseDouble(tF4.getText())/100 + "р.");
                    }else{
                        lbl.setText("Налог должен быть от 0 до 100 %" );
                    }
                }
                catch(NumberFormatException e) {
                    lbl.setText("Неверно введен годовой доход или процент (дробные значения вводятся через точку)" );
                }
            }
        });


        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(
                            "jdbc:hsqldb:file:C:\\Users\\ilya3\\OneDrive\\Documents\\заочник\\Заказ 1553316\\test4", "SA", "");
                } catch (SQLException e) {
                    System.err.println("НЕ удалось оздать соединение.");
                    e.printStackTrace();
                    System.exit(1);
                }

                try {
                    Statement statement = connection.createStatement();
                    // создаем таблицу со столбцами id и value.
                    String query = "CREATE TABLE mytable (id IDENTITY , name VARCHAR(32), type VARCHAR(32), income VARCHAR(32), tax VARCHAR(32))";
                    try {
                        statement.executeUpdate(query);
                    } catch (SQLException e) {
                        // если таблица уже существует, будет исключение, игнорируем его.
                    }
                    // добавляю записи в таблицу.
                    String name = tF1.getText();
                    String type = tF2.getText();
                    String income = tF3.getText();
                    String tax = tF1.getText();
                    query = "INSERT INTO mytable (name, type, income, tax) VALUES('"+name+"', '"+type+"', '"+income+"', '"+tax+"')";
                    statement.executeUpdate(query);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(
                            "jdbc:hsqldb:file:C:\\Users\\ilya3\\OneDrive\\Documents\\заочник\\Заказ 1553316\\test4", "SA", "");
                } catch (SQLException e) {
                    System.err.println("НЕ удалось оздать соединение.");
                    e.printStackTrace();
                    System.exit(1);
                }

                try {
                    Statement statement = connection.createStatement();
                    // создаем таблицу со столбцами id и value.
                    String query = "CREATE TABLE mytable (id IDENTITY , name VARCHAR(32), type VARCHAR(32), income VARCHAR(32), tax VARCHAR(32))";
                    try {
                        statement.executeUpdate(query);
                    } catch (SQLException e) {
                        // если таблица уже существует, будет исключение, игнорируем его.
                    }
                    // добавляю записи в таблицу.
                    String name = tF1.getText().toString();

                    query = "SELECT name,type,income,tax FROM mytable WHERE name = '"+name+"'";
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        tF2.setText(resultSet.getString(2));
                        tF3.setText(resultSet.getString(3));
                        tF4.setText(resultSet.getString(4));
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        btn4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(
                            "jdbc:hsqldb:file:C:\\Users\\ilya3\\OneDrive\\Documents\\заочник\\Заказ 1553316\\test4", "SA", "");
                } catch (SQLException e) {
                    System.err.println("НЕ удалось оздать соединение.");
                    e.printStackTrace();
                    System.exit(1);
                }

                try {
                    Statement statement = connection.createStatement();
                    // создаем таблицу со столбцами id и value.
                    String query = "CREATE TABLE mytable (id IDENTITY , name VARCHAR(32), type VARCHAR(32), income VARCHAR(32), tax VARCHAR(32))";
                    try {
                        statement.executeUpdate(query);
                    } catch (SQLException e) {
                        // если таблица уже существует, будет исключение, игнорируем его.
                    }
                    // добавляю записи в таблицу.
                    String name = tF1.getText().toString();

                    query = "DELETE FROM mytable WHERE name = '"+name+"'";
                    ResultSet resultSet = statement.executeQuery(query);



                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 5, lb2, tF1, lb3, tF2, lb4, tF3, lb5, tF4, lbl, btn1, btn2, btn3, btn4, lb6);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Курсовая");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
