package ru.job4j.filefounder.filenamefilters;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class MaskFilenameFilter implements FilenameFilter {
    private String regex;

    public MaskFilenameFilter(String mask) {
        this.regex = getRegex(mask);
    }

    @Override
    public boolean accept(File dir, String name) {
        return Pattern.matches(regex, name);
    }

    private String getRegex(String mask) {
        String rsl;
        if (mask.contains("*.")) {
            rsl = mask.replace("*.", ".*\\.");
        } else if (mask.contains("*")) {
            rsl = mask.replace("*", ".*?");
        } else {
            rsl = mask;
        }
        return rsl;
    }
}
