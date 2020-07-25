package org.sarc.bazinga.app;

import io.github.ossnass.fx.settings.StringSetting;

import java.nio.file.Paths;

public class Settings {
    public static String baseString = Paths.get(System.getProperty("user.home"), ".bazinga").toString();
    public static StringSetting DBPath = new StringSetting("DBPath"
            , Paths.get(baseString, "heartattack").toString());
}
