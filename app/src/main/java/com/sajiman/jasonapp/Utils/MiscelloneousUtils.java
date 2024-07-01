package com.sajiman.jasonapp.Utils;

import com.sajiman.jasonapp.Dto.StudentDto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiscelloneousUtils {

    public static String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }

    public static void readyToDisplay(List<StudentDto> studentDtoList) {
        Collections.sort(studentDtoList, new Comparator<StudentDto>() {
            @Override
            public int compare(StudentDto o1, StudentDto o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
