import com.intellij.openapi.wm.WindowManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
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
        //setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.mCallBack=callBack;
        list1.setVisible(false);

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
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        DefaultListSelectionModel defaultListSelectionModel=new DefaultListSelectionModel();
        defaultListSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.setSelectionModel(defaultListSelectionModel);

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

        textField1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                Vector<Integer> list=new Vector<>();
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(5);
                list.add(6);
                list.add(7);
                list.add(8);
                list.add(9);
                list1.setListData(list);
                list1.setVisible(true);
            }});
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {
        boolean result=false;
        if (null != mCallBack){
           result=mCallBack.ok(textField1.getText());
        }
        if(result){
            dispose();
            //todo 测试期间先放开
          //  System.exit(0);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
        System.exit(0);
    }

    public interface DialogCallBack{
        boolean ok(String className);
    }
}
