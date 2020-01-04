package utils;

import com.intellij.openapi.editor.*;
import enums.NameTypeEnum;

import java.util.regex.Pattern;

public class AnalysisNameTypeUtil {

    public static NameTypeEnum analysisNameType(Editor editor){
        CaretModel caretModel=editor.getCaretModel();
        LogicalPosition logicalPosition=caretModel.getLogicalPosition();
        VisualPosition visualPosition=caretModel.getVisualPosition();
        //拿到数据行
        Document document = editor.getDocument();
        String codeText = document.getText();
        String[] stringList = codeText.split("\n");
        String lineText = stringList[logicalPosition.line];
        //判断类型
        return buildNameType(lineText);
    }

    private static NameTypeEnum buildNameType(String lineText) {

        //判断是不是类
        boolean judgeClass=judgeClass(lineText);
        if(judgeClass){
            return NameTypeEnum.CLASS;
        }
        //判断是不是接口
        boolean judgeInterface=judgeInterface(lineText);
        if(judgeInterface){
            return NameTypeEnum.INTERFACE;
        }
        //判断是不是常量
        boolean judgeContant=judgeContant(lineText);
        if(judgeContant){
            return NameTypeEnum.CONSTANT;
        }
        //判断是不是方法
        boolean judgeMethod=judgeMethod(lineText);
        if(judgeMethod){
            return NameTypeEnum.METHOD;
        }
        //判断是不是变量
      /*  boolean judgeVarible=judgeVarible(lineText);
        if(judgeVarible){
            return NameTypeEnum.VARIABLE;
        }*/
        return NameTypeEnum.VARIABLE;
    }
    private static boolean judgeInterface(String lineText) {
        if(lineText.contains("interface ")){
            return true;
        }
        return false;
    }

    private static boolean judgeContant(String lineText) {
        if(lineText.contains("static") && lineText.contains("final")){
            return true;
        }
        return false;
    }

    private  static boolean judgeVarible(String lineText) {
        return false;
    }

    private static boolean judgeClass(String lineText) {
        if(lineText.contains("new ") || lineText.contains("class")){
            return true;
        }
        return false;
    }

    private static boolean judgeMethod(String lineText) {
        //todo 最好将选中字符传进来
        String pattern = "[\\w\\W]*\\([\\w\\W]*\\)[\\w\\W]*";
        boolean isMatch = Pattern.matches(pattern, lineText);
        if(isMatch){
            return true;
        }
        return false;
    }
}
