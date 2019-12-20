import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;

public class CloudNewMethodAction extends AnAction {

    private Project project;
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor=e.getData(PlatformDataKeys.EDITOR);
        CaretModel caretModel=editor.getCaretModel();
        LogicalPosition  logicalPosition=caretModel.getLogicalPosition();
        VisualPosition visualPosition=caretModel.getVisualPosition();
        Caret caret=caretModel.getCaretAt(visualPosition);
      /*  DefaultActionGroup actionGroup = (DefaultActionGroup) ActionManager.getInstance().getAction("Sample_JBPopupActionGroup");
        actionGroup.removeAll();
        actionGroup.add(new AnAction("Sample Action") {
            @Override
            public void actionPerformed(AnActionEvent e) {
                CreateMethodDialog createMethodDialog=new CreateMethodDialog();
                createMethodDialog.pack();
                createMethodDialog.setVisible(true);
            }
        });
        ListPopup listPopup = JBPopupFactory.getInstance().createActionGroupPopup("ListPopup Sample", actionGroup, e.getDataContext(), JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, false);
        listPopup.showInBestPositionFor(e.getDataContext());
*/
    }
}
