import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI2 extends Application {
	
	BankAccount ba = new BankAccount("0", "0", "0", "0");
	
	Label bb = new Label("Beginning Balance");
	Label dit = new Label("Total Deposits");
	Label oc = new Label("Total Withdrawals");
	Label eb = new Label ("Ending Balance");
	
	Label bba = new Label (ba.getBegingingBalance());
	Label dita = new Label (ba.getEndingBalance());
	Label oca = new Label (ba.getOutstandingChecks());
	Label eba = new Label (ba.getEndingBalance());
	
	Button addDeposit = new Button("New Deposit");
	Button addCheck = new Button ("New Payment");
	Button exit = new Button ("Exit");
	Button close = new Button("Close Month");
	
	HBox hb2 = new HBox(10, addDeposit, addCheck, close, exit);
	VBox vb1 = new VBox(10, bb, dit, oc, eb, hb2);
	VBox vb2 = new VBox(10, bba, dita, oca, eba);
	HBox hb1 = new HBox(10, vb1, vb2);

	TextInputDialog td = new TextInputDialog("0");
	
	Scene scene = new Scene(hb1, 400, 150);

	public static void main (String args []){
		

		
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		exit.setOnAction(new ButtonClick());
		close.setOnAction(new ButtonClick());
		addDeposit.setOnAction(new ButtonClick());
		addCheck.setOnAction(new ButtonClick());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bank reconciliation");
		primaryStage.show();
	}
	
	class ButtonClick implements EventHandler<ActionEvent> {


		public void handle(ActionEvent e) {
			
			if(e.getSource() == exit) {
				
				System.exit(0);
				
			}
			
			else if (e.getSource() == close) {

				ba.closeMonth();
				
				bba.setText(ba.getBegingingBalance());
				dita.setText(ba.getDepositsInTransit());
				oca.setText(ba.getOutstandingChecks());
				eba.setText(ba.getEndingBalance());
				
			}
			
			else if (e.getSource() == addDeposit) {
				
				ba.makeDeposit();
				dita.setText(ba.getDepositsInTransit());
				eba.setText(ba.getEndingBalance());
			}
			
			else if (e.getSource() == addCheck) {
				
				ba.makeWithdrawl();
				oca.setText(String.valueOf(ba.getOutstandingChecks()));
				eba.setText(String.valueOf(ba.getEndingBalance()));
			}
			
		}
		

	}
	
	class BankAccount {
		
		private double beginingBalance;
		private double endingBalance;
		private double depositsInTransit;
		private double outstandingChecks;
		
		String bb;
		String dit;
		String oc;
		String eb;
		
		
		BankAccount(String bb, String dit, String oc, String eb) {
			
				this.bb = bb;
				this.dit = dit;
				this.oc = oc;
				this.eb = eb;
		}
		
		public String getBegingingBalance() {
			
			String bb = String.format("%,.2f", beginingBalance);
			return bb;
			
		}
		
		public String getEndingBalance() {
			
			this.endingBalance = (this.beginingBalance + (this.depositsInTransit-this.outstandingChecks));
			
			String eb = String.format ("%,.2f", this.endingBalance);
			
			return eb;
		}
		
		public String getDepositsInTransit() {
			
			String dit = String.format("%,.2f", this.depositsInTransit);
			
			return dit;
		}
		
		public String getOutstandingChecks() {
			
			String oc = String.format("%,.2f", this.outstandingChecks);
			
			return oc;
		}
		
		public void makeDeposit () {
						
			String inputAmount = JOptionPane.showInputDialog("Enter amount");
			
			double amount = Double.parseDouble(inputAmount);
			
			depositsInTransit += amount;
		
			
			
		}
		
		public void makeWithdrawl () {
			
			String inputAmount = JOptionPane.showInputDialog("Enter amount to withdraw");
			
			double amount = Double.parseDouble(inputAmount);
			
			outstandingChecks += amount;
		
		}
		
		public void closeMonth() {
			
			this.beginingBalance = endingBalance;
			this.depositsInTransit = 0;
			this.outstandingChecks = 0;
			getEndingBalance();
		}

	}

	
}

