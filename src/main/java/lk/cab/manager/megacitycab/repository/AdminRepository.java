package lk.cab.manager.megacitycab.repository;

import lk.cab.manager.megacitycab.entity.Admin;
import lk.cab.manager.megacitycab.util.CrudUtil;
import lk.cab.manager.megacitycab.util.QueryUtil;
import lk.cab.manager.megacitycab.util.ResultSetMapper;

import java.sql.ResultSet;

public class AdminRepository {

    public Admin findByUsername(String username) {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.SELECT_ALL_ADMIN_WHERE_USERNAME, username);
            if (rs.next()) {
                return ResultSetMapper.mapResultSetToEntity(rs, Admin.class);
            }
            System.out.println("No Admin found for this username:" + username);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.getStackTrace();
        }
        return null;
    }

}
