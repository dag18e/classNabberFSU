import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame implements ActionListener {
    
    private static JLabel status;

    public GUI() {
        super("classNabber");
        BorderLayout layout = new BorderLayout();
        setLayout(layout);


        //set up North bar
        JPanel northBar = new JPanel();
        JButton add = new JButton("add");
        JButton remove = new JButton("remove");
        add(northBar, BorderLayout.NORTH);
        northBar.add(add);
        northBar.add(remove);
        
        //set up South bar
        JPanel southBar = new JPanel();
        status = new JLabel("Status: Starting...");
        add(southBar, BorderLayout.SOUTH);
        southBar.add(status);

        //set up class list
        JPanel classList = new JPanel();
        // if(!userClasses.isEmpty()) {
            // for(int i = 0; i < userClasses.size(); i++) {
            //     JCheckBox classCheckBoxes[i] = new JCheckbox(userClasses.get(i));
            // }
        // }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        return;
    }

    public void setStatus(String s) {
        status.setText("Status: " + s);
        return;
    }
}