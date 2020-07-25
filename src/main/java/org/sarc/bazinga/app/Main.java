package org.sarc.bazinga.app;

import io.github.ossnass.fx.ControlMaster;
import io.github.ossnass.fx.ResourceManager;
import io.github.ossnass.fx.SimpleController;
import io.github.ossnass.fx.settings.SettingsManager;
import io.github.ossnass.simplejpa.DBCommon;
import io.github.ossnass.simplejpa.H2DBAdapter;
import io.github.ossnass.simplejpa.UserManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.sarc.asthma.database.controllers.*;
import org.sarc.asthma.database.entities.*;
import org.sarc.asthma.pluginarch.PlugInLoader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;


public class Main extends Application {
    public static final String NULL = null;


    public static void main(String[] args) {
        LogManager.setupLogger();
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> LogManager.addLog(Level.SEVERE, e, null));
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        SimpleController frmMain = ControlMaster.getControlMaster().getController("FrmMain");
        frmMain.setStage(stage);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        loadSettings();
        loadGUI();
        loadPlugins();
        if (loadDatabase())
            notifyPreloader(new IntegerNotification(100));
        else {
            JOptionPane.showMessageDialog(null, "Cannot open database\nWill exit now", "error", JOptionPane.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    private boolean loadDatabase() {
        notifyPreloader(new IntegerNotification(75));
        UserManager.getInstance().setDBAdapter(new H2DBAdapter());
        UserManager.getInstance().setPersistanceUnitName("asthma");

        UserManager.getInstance().getDBAdapter().getExtraProperties().put("Mode", "standalone");
        UserManager.getInstance().setDBURL("", 0, Settings.DBPath.getData());
        if (UserManager.getInstance().logIn("", "")) {
            loadData();
            return true;
        } else {
            LogManager.addLog(Level.SEVERE, DBCommon.getLastException(), null);
            return false;
        }
    }

    private void loadData() {
        loadFamilyType();
        loadFamilyStatus();
        loadHousingType();
        loadHousingStatus();
        loadRelationships();
    }

    private void loadMemberStatus() {
        MemberStatusController msc = new MemberStatusController();
        if (msc.getCount() == 0) {
            LogManager.addLog(Level.WARNING, null, "No Member status found, will add to the database");
            try (Scanner scan = new Scanner(new File("memberstatus.csv"))) {
                while (scan.hasNextLine()) {
                    MemberStatusEntity item = new MemberStatusEntity();
                    item.setName(scan.nextLine());
                    msc.save(item);
                }
            } catch (Exception e) {
                LogManager.addLog(Level.SEVERE, e, null);
            }
        }
    }

    private void loadRelationships() {
        RelationshipController rsc = new RelationshipController();
        if (rsc.getCount() == 0) {
            LogManager.addLog(Level.WARNING, null, "No Relationships found, will add to the database");
            try (
                    Scanner scan = new Scanner(new File("relationship.csv"))) {
                while (scan.hasNextLine()) {
                    RelationshipEntity item = new RelationshipEntity();
                    String[] line = scan.nextLine().split(",");
                    item.setName(line[0]);
                    item.setSex(Integer.parseInt(line[1].trim()));
                    rsc.save(item);
                }

            } catch (Exception e) {
                LogManager.addLog(Level.SEVERE, e, null);
            }
        }
    }

    private void loadHousingStatus() {
        HousingStatusController hsc = new HousingStatusController();
        if (hsc.getCount() == 0) {
            LogManager.addLog(Level.WARNING, null, "No housing status, will add to the database");
            try (Scanner scan = new Scanner(new File("housingstatus.csv"))) {
                while (scan.hasNextLine()) {
                    HousingStatusEntity item = new HousingStatusEntity();
                    item.setName(scan.nextLine());
                    hsc.save(item);
                }
            } catch (Exception e) {
                LogManager.addLog(Level.SEVERE, e, null);
            }
        }
    }

    private void loadHousingType() {
        HousingTypeController htc = new HousingTypeController();
        if (htc.getCount() == 0) {
            LogManager.addLog(Level.WARNING, null, "No housing types found, will add to the database");
            try (Scanner scan = new Scanner(new File("housingtype.csv"))) {
                while (scan.hasNextLine()) {
                    HousingTypeEntity item = new HousingTypeEntity();
                    item.setName(scan.nextLine());
                    htc.save(item);
                }
            } catch (Exception e) {
                LogManager.addLog(Level.SEVERE, e, null);
            }
        }
    }

    private void loadFamilyStatus() {
        FamilyStatusController fsc = new FamilyStatusController();
        if (fsc.getCount() == 0) {
            LogManager.addLog(Level.WARNING, null, "No family status found, will add to the database");

            try (Scanner scan = new Scanner(new File("familystatus.csv"))) {
                while (scan.hasNextLine()) {
                    FamilyStatusEntity item = new FamilyStatusEntity();
                    item.setName(scan.nextLine());
                    fsc.save(item);
                }
            } catch (Exception e) {
                LogManager.addLog(Level.SEVERE, e, null);
            }
        }
    }

    private void loadFamilyType() {
        FamilyTypeController ftc = new FamilyTypeController();
        if (ftc.getCount() == 0) {
            LogManager.addLog(Level.WARNING, null, "No family type found, will add to the database");

            try (Scanner scan = new Scanner(new File("familytype.csv"))) {
                while (scan.hasNextLine()) {
                    FamilyTypeEntity item = new FamilyTypeEntity();
                    item.setName(scan.nextLine());
                    ftc.save(item);
                }
            } catch (Exception e) {
                LogManager.addLog(Level.SEVERE, e, null);
            }
        }
    }

    private void loadPlugins() {
        notifyPreloader(new IntegerNotification(50));
        PlugInLoader pil = PlugInLoader.getInstance();
        pil.loadPlugIns();
    }

    private void loadGUI() throws IOException {
        notifyPreloader(new IntegerNotification(25));
        ControlMaster.getControlMaster().initControlMaster("ar-sy.lang");
        ControlMaster.getControlMaster().getCSSes().add(ResourceManager.getURL("css/style.css").toExternalForm());
    }

    private void loadSettings() throws IOException {
        notifyPreloader(new IntegerNotification(0));
        SettingsManager.initSettingsManager(Paths.get(System.getProperty("user.home"), ".bazinga", "settings.prop").toString());
    }
}
