package com.gk.test.sql;

import com.gk.test.framework.helpers.DatabaseHelper;
import com.gk.test.models.database.UserRegModel;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class UserRegDB extends DatabaseHelper {
    private static final Logger LOG = LoggerFactory.getLogger(UserRegDB.class);

    public List<UserRegModel> getUserRegResults(String sqlQuery) {
        ResultSetHandler<List<UserRegModel>> userRegBean = new BeanListHandler<UserRegModel>(UserRegModel.class);
        try {
            return getQueryRunner().query(setUpConnection(), sqlQuery, userRegBean);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }
}