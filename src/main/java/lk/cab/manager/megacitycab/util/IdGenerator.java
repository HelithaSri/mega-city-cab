package lk.cab.manager.megacitycab.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class IdGenerator {
    public static String generateNextId(String query, String startWith) throws SQLException {
        String lastId = getLastId(query);
        if (lastId == null) {
            return startWith.toUpperCase(Locale.ROOT) + "0001";
        }
        int numPart = Integer.parseInt(lastId.substring(1));
        return String.format(startWith.toUpperCase(Locale.ROOT) + "%04d", numPart + 1);
    }

    private static String getLastId(String query) throws SQLException {
        String id = null;
        try (ResultSet rs = CrudUtil.executeQuery(query)) {
            if (rs.next()) {
                id = rs.getString("id");
            }
        }
        return id;
    }
}
