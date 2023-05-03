package vista;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Utils {

	/*
	 * return the frame to which 'c' belongs
	 */
	public static Frame getWindow(Component c) {
		Frame w = null;
		if (c != null) {
			if (c instanceof Frame)
				w = (Frame) c;
			else
				w = (Frame) SwingUtilities.getWindowAncestor(c);
		}
		return w;
	}

	public static void showErrorMsg(String msg) {
		showErrorMsg(null, msg);
	}

	public static void showErrorMsg(Component c, String msg) {
		JOptionPane.showMessageDialog(getWindow(c), msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public static void quit(Component c) {

		int n = JOptionPane.showOptionDialog(getWindow(c), "Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (n == 0) {
			System.exit(0);
		}
	}
	
	public static ImageIcon resizedIcon(String fileName, int width, int height) {
		File sourceimage = new File("resources/icons/" + fileName);
		Image img = null;
		
		try {
			img = ImageIO.read(sourceimage);
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}

}
