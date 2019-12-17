import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.Vector;

public class CreateClassDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JList list1;
    private DialogCallBack mCallBack;

    public CreateClassDialog(DialogCallBack callBack) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.mCallBack=callBack;
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        list1.setVisible(false);
        DefaultListSelectionModel defaultListSelectionModel=new DefaultListSelectionModel();
        defaultListSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.setSelectionModel(defaultListSelectionModel);
        textField1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                Vector<Integer> list=new Vector<>();
                list.add(1);
                list.add(2);
                list.add(3);
                list1.setListData(list);
                list1.setVisible(true);
            }});

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    if(list1.getSelectedValue()!=null){
                        textField1.setText(list1.getSelectedValue().toString());
                        list1.setVisible(false);
                    }
                }

            }
        });
    }

    private void onOK() {
        if (null != mCallBack){
            mCallBack.ok(textField1.getText());
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public interface DialogCallBack{
        void ok(String className);
    }

    public static void main(String[] args) {
        CreateClassDialog dialog = new CreateClassDialog(new DialogCallBack() {
            @Override
            public void ok(String className) {

            }
        });
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
