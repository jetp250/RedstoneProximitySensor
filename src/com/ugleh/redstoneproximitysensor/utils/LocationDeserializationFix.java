package com.ugleh.redstoneproximitysensor.utils;

import com.ugleh.redstoneproximitysensor.RedstoneProximitySensor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDeserializationFix {
    List<String> lines = new ArrayList<String>();
    String line = null;

    public LocationDeserializationFix() {
        try {
            File f1 = new File(RedstoneProximitySensor.getInstance().getDataFolder() + "/sensors.yml");
            boolean exists = f1.exists();
            if(!exists) return;
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (!line.contains("org.bukkit.Location"))
                {
                	lines.add(line);
                	lines.add(System.getProperty("line.separator"));
                }
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines)
                 out.write(s);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}