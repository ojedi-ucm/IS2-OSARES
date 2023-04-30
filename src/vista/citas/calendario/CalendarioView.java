package vista.citas.calendario;

import java.awt.BorderLayout;
import java.text.DateFormatSymbols;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

public class CalendarioView extends JPanel {
	String _title;
	TableModel _model;
	
	String _mesYearFormat;
	
	JLabel _mesLabel;
	JButton _sigMesBtn;
	JButton _antMesBtn;
	
	public CalendarioView(String title) {
		_title = title;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		_model = new CalendarioTableModel();
		
		Border border = BorderFactory.createTitledBorder(_title);
		setBorder(border);
		
		JPanel infoPanel = new JPanel();
		_mesLabel = new JLabel(_mesYearFormat);
		_sigMesBtn = new JButton(">>");
		_antMesBtn = new JButton("<<");
		_antMesBtn.setEnabled(false);
		infoPanel.add(_antMesBtn);
		infoPanel.add(_mesLabel);
		infoPanel.add(_sigMesBtn);
		add(infoPanel, BorderLayout.NORTH);
		
		
		JTable table = new JTable(_model);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		table.setRowHeight(60);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        
        table.setShowGrid(true);
		
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
        table.setDefaultRenderer(table.getColumnClass(0), new CalendarTableCellRenderer());

		add(scrollPane, BorderLayout.CENTER);
	}
}
