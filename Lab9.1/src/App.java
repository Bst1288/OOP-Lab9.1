import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class App extends Application {
    TextField textfieldLoanAmount = new TextField();
    TextField textfieldNumOfYears = new TextField();
    TextArea textArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        EventHandler<ActionEvent> handler = event -> ComputeLoan();
        textfieldNumOfYears.setOnAction(handler);
        textfieldLoanAmount.setOnAction(handler);

        //topPane for user to input loanAmount and numOfYears
        HBox topPane = new HBox();
        topPane.setSpacing(30);
        topPane.setPadding(new Insets(7));

        //created label for input Loan Amount label
        Label Label_LoanAmount = new Label("Loan Amount:", textfieldLoanAmount);
        Label_LoanAmount.setContentDisplay(ContentDisplay.RIGHT); //ช่องที่เติมอยู่ทางขวา

       //created label for input numOfYears label
        Label Label_NumOfYears = new Label("Number Of Years:", textfieldNumOfYears);
        Label_NumOfYears.setContentDisplay(ContentDisplay.RIGHT);

        //created Show Table button
        Button showTable = new Button("Show Table");
        showTable.setOnAction(handler);

        topPane.getChildren().addAll(Label_LoanAmount, Label_NumOfYears, showTable);

        BorderPane borderPane = new BorderPane(textArea);
        borderPane.setTop(topPane);

        //created scene
        primaryStage.setScene(new Scene(borderPane, 650, 250));
        primaryStage.setTitle("Loan GUI");
        primaryStage.show();
    }

    private void ComputeLoan() {
        double annualInterestRate;
        //get user input
        double loanAmount = Double.parseDouble(textfieldLoanAmount.getText());
        double numberOfYears = Double.parseDouble(textfieldNumOfYears.getText());

        String s = String.format("%-1s %20s %20s\n","Interest Rate", "Monthly Payment", "Total Payment");

        for (annualInterestRate = 5.00 ; annualInterestRate <= 8.00; annualInterestRate += 0.25) {
            //calculating monthly and total payment
            double monthlyInterestRate = annualInterestRate / 1200;
            double monthlyPayment = loanAmount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
            double totalPayment = monthlyPayment * numberOfYears * 12;

            // Displaying formatted info
            s += String.format("%-1.2f %22.2f %24.2f\n", annualInterestRate
                                                       , ((int)(monthlyPayment * 100) / 100.0)
                                                       , ((int)(totalPayment * 100) / 100.0));
        }

        Font font = new Font("Lucida Console",15);
        textArea.setFont(font);
        textArea.setText(s);

    }
}

