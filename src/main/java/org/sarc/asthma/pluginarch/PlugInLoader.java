package org.sarc.asthma.pluginarch;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import io.github.ossnass.fx.ControlMaster;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlugInLoader {

    private static PlugInLoader pil;
    private final Map<String, Class<PlugIn>> plugins;
    private final Map<String, String> names2Ids;

    private PlugInLoader() {
        plugins = new HashMap<>();
        names2Ids = new HashMap<>();
    }

    public static PlugInLoader getInstance() {
        if (pil == null)
            pil = new PlugInLoader();
        return pil;
    }


    public void loadPlugIns() {
        try (ScanResult res = new ClassGraph().enableAnnotationInfo().scan()) {
            ClassInfoList cil = res.getClassesWithAnnotation(PlugInInfo.class.getCanonicalName());
            for (ClassInfo cinfo : cil) {

                PlugInInfo pii = (PlugInInfo) cinfo.getAnnotationInfo(PlugInInfo.class.getCanonicalName()).loadClassAndInstantiate();
                plugins.put(pii.value(), (Class<PlugIn>) cinfo.loadClass());
                names2Ids.put(
                        ControlMaster.getControlMaster().getLanguage().getString(pii.value() + ".name")
                        , pii.value());
            }
        }
    }

    public String getId(String name) {
        if (name == null)
            throw new NullPointerException();
        return names2Ids.get(name);
    }

    public PlugIn getPlugIn(String name) {
        if (name == null)
            throw new NullPointerException();
        try {
            PlugIn pi = (PlugIn) plugins.get(getId(name)).getConstructors()[0].newInstance(null);
            pi.setId(getId(name));
            return pi;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> getNames() {
        return names2Ids.keySet();
    }

    public Set<String> getKeys() {
        return plugins.keySet();
    }
}
