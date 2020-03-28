package utils;


import com.code.naming.intelligent.api.NamingHandler;
import com.code.naming.intelligent.beans.NamingRequest;
import com.code.naming.intelligent.beans.Translation;
import com.code.naming.intelligent.beans.TranslationResult;
import com.code.naming.intelligent.enums.OptionEnum;
import com.code.naming.intelligent.enums.TypeEnum;
import enums.NameTypeEnum;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class TranslateUtil {

    public static Vector<String> translate2Vector(String word, NameTypeEnum nameTypeEnum){
        List<String> tanslateResults= translate2List(word,nameTypeEnum);
        Vector results = new Vector();
        results.addAll(tanslateResults);
        return results;
    }

    public static List<String> translate2List(String word, NameTypeEnum nameTypeEnum){
        System.out.println(word+"-----"+nameTypeEnum.getValue());
        NamingHandler namingHandler=new NamingHandler();
        NamingRequest namingRequest=new NamingRequest();
        //todo 判空
        namingRequest.setChineseWord(word);
        namingRequest.setOption(OptionEnum.QUERY);
        namingRequest.setType(convertBean(nameTypeEnum));
        TranslationResult translationResult=null;
        try{
            translationResult=namingHandler.translate(namingRequest);
        }catch (Exception ex){
            System.out.println(ex.getCause());
        }
        //todo 处理异常
        List<Translation>  translations=translationResult.getTranslations();
        List<String> tanslateResults=translations.stream().map(Translation::getWord).collect(Collectors.toList());
        System.out.println(tanslateResults);
        return tanslateResults;
    }

    public static void saveChoice(String saveChoice,String chineseWord,NameTypeEnum nameTypeEnum){

        NamingHandler namingHandler=new NamingHandler();
        NamingRequest namingRequest=new NamingRequest();
        //todo 判空
        Translation translation=new Translation();
        translation.setWord(saveChoice);
        namingRequest.setOption(OptionEnum.UPDATE);
        namingRequest.setType(convertBean(nameTypeEnum));
        namingRequest.setPersistentWord(translation);
        namingRequest.setChineseWord(chineseWord);
        namingHandler.translate(namingRequest);
    }

    private static TypeEnum convertBean(NameTypeEnum nameTypeEnum) {
        switch (nameTypeEnum){
            case ENUM:
                return TypeEnum.ENUM;
            case CLASS:
                return TypeEnum.CLASS;
            case METHOD:
                return TypeEnum.METHOD;
            case CONSTANT:
                return TypeEnum.CONSTANT;
            case VARIABLE:
                return  TypeEnum.VARIABLE;
            case INTERFACE:
                return TypeEnum.INTERFACE;
        }
        throw new RuntimeException("翻译类型转换异常");
    }
}
