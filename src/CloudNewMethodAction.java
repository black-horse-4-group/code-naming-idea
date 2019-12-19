import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class CloudNewMethodAction extends AnAction {

    private Project project;
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        project = e.getData(PlatformDataKeys.PROJECT);
        Messages.showErrorDialog(project,"create mvp code success","title");
    }
}
