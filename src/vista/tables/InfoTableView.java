package vista.tables;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

public class InfoTableView extends JPanel {
	String _title;
	TableModel _tableModel;
	
	public InfoTableView(String title, TableModel tableModel) {
		_title = title;
		_tableModel = tableModel;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		Border border = BorderFactory.createTitledBorder(_title);
		setBorder(border);
		
		
		JTable table = new JTable(_tableModel);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		

		add(scrollPane);
	}
}
