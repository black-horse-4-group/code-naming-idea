package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.*;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.popup.list.ListPopupImpl;
import dialog.CreateClassDialog;
import enums.NameTypeEnum;
import utils.AnalysisNameTypeUtil;
import utils.TranslateUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Pattern;

import static com.intellij.ide.highlighter.JavaFileType.INSTANCE;
import static com.intellij.openapi.fileTypes.FileType.*;

public class CloudNewMethodAction extends AnAction {

    private Project project;
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor=e.getData(PlatformDataKeys.EDITOR);
        //初始化 list弹窗
        List<String> initList=initData(buildWord(editor), AnalysisNameTypeUtil.analysisNameType(editor));
        ListPopup listPopup= JBPopupFactory.getInstance().createListPopup(new MyListPopupStep<String>(e,"选择奇迹",initList));
        listPopup.showInBestPositionFor(e.getDataContext());


    }

    private String buildWord(Editor editor) {
        SelectionModel selectionModel = editor.getSelectionModel();
        return selectionModel.getSelectedText();
    }

    private List<String> initData(String word, NameTypeEnum nameTypeEnum) {
        List<String> list= TranslateUtil.translate2List(word,nameTypeEnum);
        return  list;
    }

    public static void main(String[] args) {
        String lineText="  public static void main(String[] args)";
        String pattern = "[\\w\\W]*\\([\\w\\W]*\\)[\\w\\W]*";
        boolean isMatch = Pattern.matches(pattern, lineText);
        System.out.println(isMatch);
    }
}
