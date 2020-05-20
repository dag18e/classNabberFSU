import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class GUI extends JFrame implements ActionListener {
    
    private static JTextArea status;
    private static LocalTime time;
    private static DateTimeFormatter timeFormat;

    public GUI() {
    	super("classNabber");
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        setLayout(layout);

        JButton run = new JButton("Run");
        JButton stop = new JButton("Stop");
        con.anchor = GridBagConstraints.NORTH;
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 50;
        con.weighty = 10;
        con.gridx = 0;
        con.gridy = 0;
        add(run, con);
        con.gridx = 1;
        add(stop, con);
        
        
        timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = LocalTime.now();
        
        
        status = new JTextArea("[" + time.format(timeFormat) + "]: Enabling hyperdrive...");
        status.setEditable(false);
        status.setLineWrap(true);
        JScrollPane statusBox = new JScrollPane(status);
        
        con.weighty = 80;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 2;
        add(statusBox, con);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        return;
    }

    public void setStatus(String s) {
    	time = LocalTime.now();
    	time.format(timeFormat);
    	
        status.append("\n[" + time.format(timeFormat) + "]: " + s);
        status.setCaretPosition(status.getDocument().getLength());
        return;
    }
}