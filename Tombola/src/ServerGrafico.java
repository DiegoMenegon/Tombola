import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;

public class ServerGrafico {

	protected Shell shell;
	private Table table;
	int n=1;
	int nc;
	ArrayList<Integer> estratti=new ArrayList<>();
	Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerGrafico window = new ServerGrafico();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		 display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 310);
		shell.setText("SWT Application");
		
		Color yellow= display.getSystemColor(SWT.COLOR_YELLOW);
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 414, 212);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setResizable(false);
		tblclmnNewColumn.setWidth(41);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setResizable(false);
		tableColumn.setWidth(41);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setResizable(false);
		tableColumn_1.setWidth(41);
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setResizable(false);
		tableColumn_2.setWidth(41);
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setResizable(false);
		tableColumn_3.setWidth(41);
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setResizable(false);
		tableColumn_4.setWidth(41);
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setResizable(false);
		tableColumn_5.setWidth(41);
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setResizable(false);
		tableColumn_6.setWidth(41);
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setResizable(false);
		tableColumn_7.setWidth(41);
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setResizable(false);
		tableColumn_8.setWidth(41);
		
		Label lblNumeroEstratto = new Label(shell, SWT.NONE);
		lblNumeroEstratto.setBounds(252, 242, 28, 15);
		
		Button btnEstraiNumero = new Button(shell, SWT.NONE);
		btnEstraiNumero.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				while(true){
					if(estratti.size()==90){
						JOptionPane.showMessageDialog(null,"Tutti i numeri sono stati estratti","Avviso",JOptionPane.PLAIN_MESSAGE);
						btnEstraiNumero.setEnabled(false);
						break;
					}
					int nc=(int)((Math.random()*90)+1);
					if(!estratti.contains(nc)){
						lblNumeroEstratto.setText(""+nc);
						estratti.add(nc);
						System.out.println(table.getItems().length);
						for(int i=0;i<9;i++){
							TableItem item=table.getItem(i);
							for(int j=0;j<10;j++){
								if(item.getText(j).equals(""+nc)){
									item.setBackground(j, yellow);
								}
							}
						}
						break;
					}else{
						
					}
					
					
				}
			}
		});
		btnEstraiNumero.setBounds(10, 237, 85, 25);
		btnEstraiNumero.setText("Estrai numero");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(149, 242, 97, 15);
		lblNewLabel.setText("Numero estratto:");
		
		
		
		for(int i=0;i<9;i++){
			 TableItem tableItem= new TableItem(table, SWT.NONE);
			 for(int j=0;j<10;j++){
				 tableItem.setText(j,""+n); 
				 n++;
			 }
		}

	}
}
