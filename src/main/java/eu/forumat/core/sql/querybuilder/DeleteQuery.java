package eu.forumat.core.sql.querybuilder;

import eu.forumat.core.sql.MySQL;

public class DeleteQuery extends ExecuteQuery {

    public DeleteQuery(MySQL sql, String query) {
        super(sql, query);
        this.firstvalue = true;
    }
    public DeleteQuery where(String key, Object value) {
        if(and) {
            query += " AND";
        } else{
            query += " WHERE";
            and = true;
        }
        query += " "+key+"=";
        values.add(value);
        query += "?";
        return this;
    }
    public DeleteQuery whereLower(String key, Object value) {
        if(and) query += " AND";
        else{
            query += " WHERE";
            and = true;
        }
        query += " "+key+"<";
        values.add(value);
        query += "?";
        return this;
    }
}