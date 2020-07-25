package org.sarc.bazinga.app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.*;

public class LogManager {
    private static Logger logger;


    public static void setupLogger() {

        logger = Logger.getLogger("org.hibernate.SQL");

        logger.setUseParentHandlers(false);


        String logFileName = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".log";

        try {
            File f = new File(Paths.get(System.getProperty("user.home"), ".bazinga",
                    "log").toString());
            if (!f.exists()) {
                f.mkdirs();
            }
            f = new File(Paths.get(f.getAbsolutePath(), logFileName).toString());

            Handler file = new FileHandler(f.getAbsolutePath(), true);

            file.setEncoding("utf8");

            file.setFormatter(new Formatter() {

                public String format(LogRecord record) {

                    StringBuilder build = new StringBuilder();

                    SimpleDateFormat miliFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");

                    StringWriter sw = new StringWriter();

                    PrintWriter pw = new PrintWriter(sw);
                    if (record.getThrown() != null)
                        record.getThrown().printStackTrace(pw);

                    build.append(miliFormatter.format(new Date(record.getMillis())))
                            .append("\t")
                            .append(record.getLevel())
                            .append("\t")
                            .append(record.getSourceClassName())
                            .append("\t")
                            .append(record.getSourceMethodName())
                            .append("\t")
                            .append(record.getMessage())
                            .append("\t")
                            .append(sw.toString())
                            .append("\n");

                    return build.toString();

                }

            });

            logger.addHandler(file);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    public static final void addLog(Level level, Throwable thrown, String message) {

        LogRecord record = new LogRecord(level, (thrown != null) ? thrown.getLocalizedMessage() : message);

        record.setThrown(thrown);

        logger.log(record);

    }


    public static final Logger getLogger() {
        return logger;
    }

}