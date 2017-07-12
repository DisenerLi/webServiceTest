package com.baj.dao.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceMXBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by liqiang on 2017/6/16.
 */
public class MysqlDataSourcePool implements DataSource, BasicDataSourceMXBean, MBeanRegistration, AutoCloseable {

	private static final Log log = LogFactory.getLog(MysqlDataSourcePool.class);

	public void close() throws Exception {

	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	public ObjectName preRegister(MBeanServer server, ObjectName name) throws Exception {
		return null;
	}

	public void postRegister(Boolean registrationDone) {

	}

	public void preDeregister() throws Exception {

	}

	public void postDeregister() {

	}

	public Connection getConnection() throws SQLException {
		return null;
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	public void setLoginTimeout(int seconds) throws SQLException {

	}

	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	public boolean getAbandonedUsageTracking() {
		return false;
	}

	public Boolean getDefaultAutoCommit() {
		return null;
	}

	public Boolean getDefaultReadOnly() {
		return null;
	}

	public int getDefaultTransactionIsolation() {
		return 0;
	}

	public String getDefaultCatalog() {
		return null;
	}

	public boolean getCacheState() {
		return false;
	}

	public String getDriverClassName() {
		return null;
	}

	public boolean getLifo() {
		return false;
	}

	public int getMaxTotal() {
		return 0;
	}

	public int getMaxIdle() {
		return 0;
	}

	public int getMinIdle() {
		return 0;
	}

	public int getInitialSize() {
		return 0;
	}

	public long getMaxWaitMillis() {
		return 0;
	}

	public boolean isPoolPreparedStatements() {
		return false;
	}

	public int getMaxOpenPreparedStatements() {
		return 0;
	}

	public boolean getTestOnCreate() {
		return false;
	}

	public boolean getTestOnBorrow() {
		return false;
	}

	public long getTimeBetweenEvictionRunsMillis() {
		return 0;
	}

	public int getNumTestsPerEvictionRun() {
		return 0;
	}

	public long getMinEvictableIdleTimeMillis() {
		return 0;
	}

	public long getSoftMinEvictableIdleTimeMillis() {
		return 0;
	}

	public boolean getTestWhileIdle() {
		return false;
	}

	public int getNumActive() {
		return 0;
	}

	public int getNumIdle() {
		return 0;
	}

	public String getPassword() {
		return null;
	}

	public String getUrl() {
		return null;
	}

	public String getUsername() {
		return null;
	}

	public String getValidationQuery() {
		return null;
	}

	public int getValidationQueryTimeout() {
		return 0;
	}

	public String[] getConnectionInitSqlsAsArray() {
		return new String[0];
	}

	public boolean isAccessToUnderlyingConnectionAllowed() {
		return false;
	}

	public long getMaxConnLifetimeMillis() {
		return 0;
	}

	public boolean getLogExpiredConnections() {
		return false;
	}

	public boolean getRemoveAbandonedOnBorrow() {
		return false;
	}

	public boolean getRemoveAbandonedOnMaintenance() {
		return false;
	}

	public int getRemoveAbandonedTimeout() {
		return 0;
	}

	public boolean getLogAbandoned() {
		return false;
	}

	public boolean isClosed() {
		return false;
	}

	public boolean getFastFailValidation() {
		return false;
	}

	public String[] getDisconnectionSqlCodesAsArray() {
		return new String[0];
	}
}
