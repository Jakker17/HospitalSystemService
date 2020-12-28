package eng.hospitalSystemService.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCheckService {
    public boolean doPatternCheck(String patternString,String whatToCheck)
    {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(whatToCheck);
        boolean isStringContainsSpecialCharacter = matcher.find();
        if(isStringContainsSpecialCharacter){
        return true;
        }
        return false;
    }
}
