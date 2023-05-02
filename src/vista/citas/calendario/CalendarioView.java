package vista.citas.calendario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import control.ControlCita;
import vista.citas.*;
import modelo.Cita;

public class CalendarioView extends JPanel {
	CalendarioTableModel _model;
	
	Calendar _selFecha;
	String _mesYearFormat;
	
	JLabel _mesLabel;
	JButton _sigMesBtn;
	JButton _antMesBtn;
	
	ControlCita _ctrl;
	
	public CalendarioView(ControlCita ctrl) {
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		// ------- INIT --------
		_model = new CalendarioTableModel(_ctrl);
		_selFecha = Calendar.getInstance();
		_selFecha.set(Calendar.DAY_OF_MONTH, 1);
		_selFecha.set(Calendar.HOUR_OF_DAY, 0);
		Calendar hoy = Calendar.getInstance();
		
		CrearCitaDialog nuevaCitaDialog = new CrearCitaDialog(new JFrame(), _ctrl, _selFecha);
		
		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(border);
		
		// ------- Sel mes y año en calendario ---------
		
		JPanel infoPanel = new JPanel();
		_mesLabel = new JLabel(parseFecha());
		_sigMesBtn = new JButton(">>");
		_sigMesBtn.addActionListener((a) -> {
			_selFecha.add(Calendar.MONTH, 1);	
			_antMesBtn.setEnabled(true);
			_mesLabel.setText(parseFecha());
			_model.updateDays(_selFecha);
		});
		
		_antMesBtn = new JButton("<<");
		_antMesBtn.setEnabled(false);
		_antMesBtn.addActionListener((a) -> {
			_selFecha.add(Calendar.MONTH, -1);
			
			Calendar prev = Calendar.getInstance();
			prev.set(Calendar.MONTH, _selFecha.get(Calendar.MONTH)-1);
			
			_mesLabel.setText(parseFecha());
			_model.updateDays(_selFecha);
			
			if(!prev.after(hoy)) {
				_antMesBtn.setEnabled(false);
			}
		});
		
		infoPanel.add(_antMesBtn);
		infoPanel.add(_mesLabel);
		infoPanel.add(_sigMesBtn);
		add(infoPanel, BorderLayout.NORTH);
		
		// ------- Calendario --------
		
		JTable table = new JTable(_model);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.rowAtPoint(e.getPoint()); // Obtener la fila de la celda donde se hizo clic
		        int col = table.columnAtPoint(e.getPoint()); // Obtener la columna de la celda donde se hizo clic
		        
		        if (row >= 0 && col >= 0 && col < 5) { // Si se hizo clic en una celda válida
		        	Object[] valor = (Object[]) _model.getValueAt(row, col);
		        	Calendar fecha = Calendar.getInstance();
		        	
		        	fecha.set(Calendar.YEAR, _model.getYear());
		        	fecha.set(Calendar.MONTH, _model.getMonth());
		        	fecha.set(Calendar.DAY_OF_MONTH, (int) valor[1]);
		        	
		        	if(valor[0] == null) {
		        		nuevaCitaDialog.setFecha(fecha);
		        		nuevaCitaDialog.open();
		        	} else {
		        		Cita cita = (Cita) valor[0];
		        		new ModificarCitaDialog(new JFrame(), _ctrl, cita);
		        	}
		        }
		    }
		});
		
		table.setRowHeight(60);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
		
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
        table.setDefaultRenderer(table.getColumnClass(0), new CalendarTableCellRenderer());

		add(scrollPane, BorderLayout.CENTER);
	}
	
	private String parseFecha() {
		SimpleDateFormat formato = new SimpleDateFormat("MMMM 'de' yyyy", new Locale("es", "ES"));
        return formato.format(_selFecha.getTime());
	}
}
