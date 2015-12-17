package it.whitebox.event.integration.db;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * SPecific NamingStrategy
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
public class TcktNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = -7623306398849526172L;

	@Override
    public String columnName(String columnName)
    {
        return columnName;
    }

    @Override
    public String tableName(String tableName)
    {
        return tableName;
    }
}
