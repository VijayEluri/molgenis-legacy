package org.molgenis.fieldtypes;

import org.molgenis.model.MolgenisModelException;

public class HyperlinkField extends FieldType
{
	@Override
	public String getJavaPropertyType()
	{
		return "String";
	}
	
	@Override
	public String getJavaAssignment(String value)
	{
		if(value == null || value.equals("") ) return "null";
		return "\""+value+"\"";
	}
	
	@Override
	public String getJavaPropertyDefault()
	{
		return getJavaAssignment(f.getDefaultValue());
	}
	
	@Override
	public String getMysqlType() throws MolgenisModelException
	{
		return "VARCHAR(255)";
	}

	@Override
	public String getHsqlType()
	{
		return "TEXT";
	}
	@Override
	public String getXsdType()
	{
		return "url";
	}

	@Override
	public String getFormatString()
	{
		return "%s";
	}

}