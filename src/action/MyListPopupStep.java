package action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import utils.AnalysisNameTypeUtil;
import utils.TranslateUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MyListPopupStep<T> extends BaseListPopupStep<T> {

    private AnActionEvent e=null;

    @Override
    public PopupStep onChosen(T selectedValue, final boolean finalChoice) {
       //拿到选择值
       //替换
       Project project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor=e.getData(PlatformDataKeys.EDITOR);
        int start=editor.getSelectionModel().getSelectionStart();
        int end=editor.getSelectionModel().getSelectionEnd();
        String chineseWord=editor.getSelectionModel().getSelectedText();
        Document document=editor.getDocument();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //genGetterAndSetter为生成getter和setter函数部分
                document.replaceString(start,end,selectedValue.toString());
            }
        };
        WriteCommandAction.runWriteCommandAction(project, runnable);
        //保存
        TranslateUtil.saveChoice(selectedValue.toString(),chineseWord,AnalysisNameTypeUtil.analysisNameType(editor));
        return FINAL_CHOICE;
    }

    public MyListPopupStep(AnActionEvent e,@Nullable String title, List<? extends T> values) {
        super(title, values, new ArrayList<>());
        this.e=e;
    }
}
