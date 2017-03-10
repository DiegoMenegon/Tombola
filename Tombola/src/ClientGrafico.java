
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Label;

public class ClientGrafico extends Thread {

	protected Shell shell;
	private Table table;
	Label nestratto;
	ArrayList<Integer> vett = new ArrayList<>();
	String[] rigatab = new String[3];
	InputStreamReader isr;
	BufferedReader in;
	Socket s;
	Display display;
	Color yellow;

	
	

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ClientGrafico window = new ClientGrafico();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			isr = new InputStreamReader(s.getInputStream());
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		in = new BufferedReader(isr);

		while (true) {
			try {
				int numestratto=s.getInputStream().read();
				Display.getDefault().asyncExec(new Runnable() {
				    public void run() {
				       ClientGrafico.this.nestratto.setText(numestratto+"");
				       for(int i=0;i<3;i++){
							TableItem item=table.getItem(i);
							for(int j=0;j<10;j++){
								try{
									if(Integer.parseInt(item.getText(j))==numestratto){
										System.out.println("Numero!");
										item.setBackground(j, yellow);
									}
								}catch(Exception e){
									
								}
								
							}
						}
				    }
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		shell.setSize(490, 300);
		shell.setText("SWT Application");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 87, 454, 86);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		yellow= display.getSystemColor(SWT.COLOR_YELLOW);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(50);

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(50);

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(50);

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(50);

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(50);

		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(50);

		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(50);

		TableColumn tblclmnNewColumn_7 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_7.setWidth(50);

		TableColumn tblclmnNewColumn_8 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_8.setWidth(50);

		try {
			s = new Socket("localhost", 9999);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		org.eclipse.swt.widgets.Button btnRecuperaNumeri = new org.eclipse.swt.widgets.Button(shell, SWT.NONE);
		btnRecuperaNumeri.setBounds(10, 216, 111, 25);
		btnRecuperaNumeri.setText("Recupera numeri");
		
		Label lblNumeroEstratto = new Label(shell, SWT.NONE);
		lblNumeroEstratto.setBounds(10, 10, 99, 15);
		lblNumeroEstratto.setText("Numero estratto:");
		
		nestratto = new Label(shell, SWT.NONE);
		nestratto.setBounds(115, 10, 55, 15);
		btnRecuperaNumeri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Apre il socket
				try {
					s = new Socket("localhost", 9999);
					for (int i = 0; i < 15; i++) {
						// System.out.print(s.getInputStream().read() + " ");
						vett.add(s.getInputStream().read());
					}

					isr = new InputStreamReader(s.getInputStream());
					in = new BufferedReader(isr);
					for (int j = 0; j < 3; j++) {
						rigatab = in.readLine().split("-");
						for(int t=0;t<rigatab.length;t++){
							rigatab[t]=rigatab[t].trim();
						}
						System.out.println("lunghezza" + rigatab.length);
						for (int x = 0; x < rigatab.length - 1; x++) {
							System.out.println(rigatab[x + 1]);
						}
						TableItem tableItem = new TableItem(table, SWT.NONE);
						for (int x = 0; x < rigatab.length - 1; x++) {
							tableItem.setText(x, rigatab[x + 1]);
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnRecuperaNumeri.setEnabled(false);
				ClientGrafico.this.start();
				// Riceve i 15 numeri
				// Apre il thread di comunicazione che riceverà i comandi
				// successivi
				// Apre il thread di comunicazione che riceverà i comandi
				// successivi

			}
		});

	}
}
