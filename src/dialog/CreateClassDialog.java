package dialog;

import enums.NameTypeEnum;
import utils.TranslateUtil;

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
    private JTextField textField2;
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

                        textField2.setText(list1.getSelectedValue().toString());
                        list1.setVisible(false);
                    }
                }
            }
        });

        textField1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                Vector<String> results= TranslateUtil.translate2Vector(textField1.getText(), NameTypeEnum.CLASS);
                list1.setListData(results);
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
           result=mCallBack.ok(textField2.getText());
        }
        if(result){
            //保存
            TranslateUtil.saveChoice(textField2.getText(),textField1.getText(),NameTypeEnum.CLASS);
            dispose();
            //todo 测试期间先放开
          //  System.exit(0);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
       // System.exit(0);
    }

    public interface DialogCallBack{
        boolean ok(String className);
    }
}
