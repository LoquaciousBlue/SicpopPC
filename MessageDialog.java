import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MessageDialog extends Dialog {
    Label messageLabel;
    Button okayButton;

    public MessageDialog(Frame owner, String message){
        super(owner);
        this.setTitle("Sicpop");
        this.addWindowListener(new ExtWindowAdapter());
        this.resize();

        super.setLayout(generateLayout(message));

        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.addNotify();  
    }

    private void resize(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int)(0.3 * d.getWidth()), (int)(0.2 * d.getHeight()));
        this.setFont(new Font("Cambria", Font.PLAIN, (int)(0.02 * d.getHeight())));
    }

    private GridBagLayout generateLayout(String message){
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        
        // init buttons and labels
        messageLabel = new WrappingLabel(message);
        messageLabel.setAlignment(Label.CENTER);
            okayButton = new Button("Ok");
        // Add Listeners
        okayButton.addActionListener(new OkayActionListener());

        // Apply bi-axial fill
        gbc.fill = GridBagConstraints.BOTH;

        // Message
        gbc.gridx = 3;
        gbc.gridy = 1;
        int padding = (int)(d.getHeight() * 0.01);
        gbc.insets = new Insets(padding,padding,padding,padding);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; 
        
        gbc.anchor = GridBagConstraints.NORTH;

        gbl.setConstraints(messageLabel, gbc);
        super.add(messageLabel);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridy = 4;
        gbc.weighty = 0.1;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbl.setConstraints(okayButton, gbc);
        super.add(okayButton);

        return gbl;
    }

    private class OkayActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.exit(-1);
        }
    }

    private class ExtWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(-1);
        }
    }

}
