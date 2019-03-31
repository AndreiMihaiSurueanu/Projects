package view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameForAcceptanceHistoryStudent {

	private JFrame acceptanceHistory;

	
	public FrameForAcceptanceHistoryStudent(ArrayList<String[]> acceptenceHistoryStudent) {
		
		acceptanceHistory =new JFrame ("Acceptence history");
		acceptanceHistory.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		acceptanceHistory.getContentPane();
		acceptanceHistory.pack();
		   
		acceptanceHistory.setLayout(null);
		acceptanceHistory.setSize(387,255);
		acceptanceHistory.setLocationRelativeTo(null);
		
		
		Object rowData[][] = new String[acceptenceHistoryStudent.size()][2];
		
		for (int i = 0; i < rowData.length; i++) {
			for (int j = 0; j < rowData[i].length; j++) {
				String[] auxiliar = acceptenceHistoryStudent.get(i);
				rowData[i][j] = auxiliar[j];
			}
		}
			
		    Object columnNames[] = { "Response", "Company Name"};
		    JTable table = new JTable(rowData, columnNames);
		    JScrollPane scrollPane = new JScrollPane(table);
		    acceptanceHistory.add(scrollPane);
		    scrollPane.setBounds(10, 10, 350, 200);  //Size/position of the Jtable
		
		
		
	}

	
	
	public void display() {
		acceptanceHistory.setVisible(true);
		
	}
}
