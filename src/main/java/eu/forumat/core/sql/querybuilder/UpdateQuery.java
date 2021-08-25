package eu.forumat.core.sql.querybuilder;

import eu.forumat.core.sql.MySQL;

public class UpdateQuery extends ExecuteQuery {

    public UpdateQuery(MySQL sql, String query) {
        super(sql, query);
    }

    public UpdateQuery set(String field, Object value) {
        if (comma) query += ",";
        query += " `"+field+"`=?";
        values.add(value);
        comma = true;
        return this;
    }

    public UpdateQuery where(String key, Object value) {
        if(and) query += " AND";
        else query += " WHERE";
        query +=" "+key+"=?";
        values.add(value);
        and = true;
        return this;
    }
}