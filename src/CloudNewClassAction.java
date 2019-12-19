import com.intellij.ide.IdeView;
import com.intellij.ide.fileTemplates.JavaTemplateUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.IncorrectOperationException;

import java.awt.*;

public class CloudNewClassAction extends AnAction {

    private Project project;
    private String mClassName;//类名称
    private static  final JavaDirectoryService   myDirectoryService = JavaDirectoryService.getInstance();
    private PsiDirectory psiDirectory;

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        project = e.getData(PlatformDataKeys.PROJECT);

        IdeView ideView = e.getRequiredData(LangDataKeys.IDE_VIEW);
        psiDirectory = ideView.getOrChooseDirectory();
        init();
    }

    /**
     * 刷新项目
     * @param e
     */
    private void refreshProject(AnActionEvent e) {
        e.getProject().getBaseDir().refresh(false, true);
    }

    /**
     * 初始化Dialog
     */
    private void init(){
        CreateClassDialog myDialog = new CreateClassDialog(new CreateClassDialog.DialogCallBack() {
            @Override
            public boolean  ok(String className) {
                // 实例化ok事件
                mClassName=className;
                boolean result=createClassFiles();
                if(!result){
                    return false;
                }
                Messages.showInfoMessage(project,"create mvp code success","title");
                return true;
            }
        });
        myDialog.pack();
        myDialog.setVisible(true);
        myDialog.setMinimumSize(new Dimension(260, 120));
        myDialog.setLocationRelativeTo(WindowManager.getInstance().getFrame(project));
    }

    /**
     * 生成类文件
     */
    private boolean createClassFiles() {
        try{
            myDirectoryService.checkCreateClass(psiDirectory,mClassName);
        }catch (IncorrectOperationException e){
            Messages.showErrorDialog(project,e.getMessage(),"title");
            return false;
        }
        myDirectoryService.createClass(psiDirectory, mClassName, JavaTemplateUtil.INTERNAL_CLASS_TEMPLATE_NAME);
        return true;
    }
}
