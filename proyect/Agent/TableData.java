package Agent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import Agent.TableSchema.Column;
import Exceptions.EmptySetException;
import Exceptions.NoValueException;

public class TableData {

	DbAccess db;

	public TableData(DbAccess db) {
		this.db = db;
	}

	public List<Example> getDistinctTransactions(String table) throws SQLException, EmptySetException {
		List<Example> distinctTransactions = new ArrayList<Example>();
		TableSchema tableSchema = new TableSchema(db, table);

		Connection con = db.getConnection();
		Statement s = con.createStatement();
		ResultSet r = s.executeQuery("SELECT DISTINCT * FROM " + table);

		if (!r.next()) {
			// ResultSet empty exception
			throw new EmptySetException("No distinct transactions found in the table.");
		} else {
			while (r.next()) {
				Example example = new Example();
				for (int i = 0; i < tableSchema.getNumberOfAttributes(); i++) {
					TableSchema.Column column = tableSchema.getColumn(i);
					if (column.isNumber()) {
						// Should add the Double value, as getDouble()?
						example.add(r.getFloat(column.getColumnName()));
					} else {
						example.add(r.getString(column.getColumnName()));
					}
				}
				distinctTransactions.add(example);
			}
		}
		r.close();
		s.close();

		return distinctTransactions;
	}

	public Set<Object> getDistinctColumnValues(String table, Column column) throws SQLException {
		Set<Object> distinctValues = new TreeSet<>(); // Tree map to order the inputs

		Connection con = db.getConnection();
		Statement s = con.createStatement();
		ResultSet r = s.executeQuery("SELECT DISTINCT " + column.getColumnName() + " FROM " + table + " ORDER BY "
				+ column.getColumnName() + " ASC");

		while (r.next()) {
			Object value = r.getObject(column.getColumnName());
			distinctValues.add(value);
		}
		r.close();
		s.close();

		return distinctValues;
	}

	public Object getAggregateColumnValue(String table, Column column, QUERY_TYPE aggregate)
			throws SQLException, NoValueException {
		Object result = null;
		Connection con = db.getConnection();
		Statement s = con.createStatement();
		String query = "";

		if (aggregate == QUERY_TYPE.MIN) {
			query = "SELECT MIN(" + column.getColumnName() + ") AS " + column.getColumnName() + " FROM " + table;
		} else if (aggregate == QUERY_TYPE.MAX) {
			query = "SELECT MAX(" + column.getColumnName() + ") AS " + column.getColumnName() + " FROM " + table;
		}

		ResultSet r = s.executeQuery(query);

		if (r.next()) {
			result = r.getObject(column.getColumnName());
		} else {
			// ResultSet empty NoValueException
			throw new NoValueException("No value found for aggregate " + aggregate);
		}

		r.close();
		s.close();

		if (result == null) {
			// ResultSet null: NoValueException
			throw new NoValueException("No value found for aggregate " + aggregate);
		}

		return result;
	}

}