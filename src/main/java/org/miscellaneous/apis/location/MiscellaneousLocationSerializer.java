package org.miscellaneous.apis.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

public class MiscellaneousLocationSerializer {

    public static String serializeLocation(Location location) {
        return location.getWorld().getName() + "; " + location.getX() + "; " + location.getY() + "; " + location.getZ() + "; " + location
                .getYaw() + "; " + location.getPitch();
    }

    public static Location deserializeLocation(String serialized) {
        String[] divPoints = serialized.split("; ");
        Location deserialized = new Location(Bukkit.getWorld(divPoints[0]), parseDouble(divPoints[1]), parseDouble(divPoints[2]), parseDouble(divPoints[3]));
        deserialized.setYaw(parseFloat(divPoints[4]));
        deserialized.setPitch(parseFloat(divPoints[5]));
        return deserialized;
    }

}
