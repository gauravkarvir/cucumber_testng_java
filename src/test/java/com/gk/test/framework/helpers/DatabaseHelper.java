package com.gk.test.framework.helpers;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String jdbcUrl;
    private static final String jdbcDriver;
    private static final String jdbcUser;
    private static final String jdbcPwd;
    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    private static Connection conn = null;
    private static QueryRunner run;


    static {
        Props.loadRunConfigProps(RUN_CONFIG_PROPERTIES);

        jdbcUrl = Props.getProp("jdbcUrl");
        jdbcDriver = Props.getProp("jdbcDriver");
        jdbcUser = Props.getProp("jdbcUser");
        jdbcPwd = Props.getProp("jdbcPwd");

    }

    protected static Connection setUpConnection() {

        try {
            DbUtils.loadDriver(jdbcDriver);
            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
        } catch (SQLException se) {
            LOG.info(se.getMessage());


        } finally {
            DbUtils.closeQuietly(conn);
        }
        return conn;
    }

    /**
     * Executes the sql Query and returns the results in list format
     *
     * @param sqlQuery Specify sql query in String format
     */
    public List executeQuery(String sqlQuery) throws SQLException {
        return getQueryRunner().query(setUpConnection(), sqlQuery, new MapListHandler());
    }

    protected QueryRunner getQueryRunner() {
        return new QueryRunner();
    }
}