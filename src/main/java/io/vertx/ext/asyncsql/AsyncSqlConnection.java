package io.vertx.ext.asyncsql;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;

/**
 * @author <a href="http://www.campudus.com">Joern Bernhardt</a>.
 */
@VertxGen
@ProxyGen
public interface AsyncSqlConnection {
  /**
   * Executes the given SQL statement
   *
   * @param sql the SQL to execute. For example <code>CREATE TABLE IF EXISTS table ...</code>
   * @param resultHandler the handler which is called once this operation completes.
   * @see java.sql.Statement#execute(String)
   */
  @Fluent
  AsyncSqlConnection execute(String sql, Handler<AsyncResult<Void>> resultHandler);

  /**
   * Executes the given SQL <code>SELECT</code> statement which returns the results of the query.
   *
   * @param sql the SQL to execute. For example <code>SELECT * FROM table ...</code>.
   * @param params if the SQL statement is to be a prepared statement, these are the parameters to fill the statement. Pass null if
   * the statement is not a prepared statement.
   * @param resultHandler the handler which is called once the operation completes. It will return a list of <code>JsonObject</code>'s
   * which represent the ResultSet. So column names are keys, and values are of course values.
   *
   * @see java.sql.Statement#executeQuery(String)
   * @see java.sql.PreparedStatement#executeQuery(String)
   */
  @Fluent
  AsyncSqlConnection query(String sql, JsonArray params, Handler<AsyncResult<ResultSet>> resultHandler);

  /**
   * Executes the given SQL statement which may be an <code>INSERT</code>, <code>UPDATE</code>, or <code>DELETE</code>
   * statement.
   *
   * @param sql the SQL to execute. For example <code>INSERT INTO table ...</code>
   * @param params if the SQL statement is to be a prepared statement, these are the parameters to fill the statement. Pass null if
   * the statement is not a prepared statement.
   * @param resultHandler the handler which is called once the operation completes.
   *
   * @see java.sql.Statement#executeUpdate(String)
   * @see java.sql.PreparedStatement#executeUpdate(String)
   */
  @Fluent
  AsyncSqlConnection update(String sql, JsonArray params, Handler<AsyncResult<UpdateResult>> resultHandler);

  /**
   * Closes the connection. Important to always close the connection when you are done so it's returned to the pool.
   *
   * @param handler the handler called when this operation completes.
   */
  @ProxyClose
  void close(Handler<AsyncResult<Void>> handler);

  /**
   * Commits all changes made since the previous commit/rollback.
   *
   * @param handler the handler called when this operation completes.
   */
  @Fluent
  AsyncSqlConnection commit(Handler<AsyncResult<Void>> handler);

  /**
   * Rolls back all changes made since the previous commit/rollback.
   *
   * @param handler the handler called when this operation completes.
   */
  @Fluent
  AsyncSqlConnection rollback(Handler<AsyncResult<Void>> handler);
}
