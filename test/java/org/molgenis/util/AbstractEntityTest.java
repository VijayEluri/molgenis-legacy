package org.molgenis.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Test;

public class AbstractEntityTest
{
	@Test
	public void testSetValuesFromString_single() throws Exception
	{
		Entity entity = AbstractEntity.setValuesFromString("(a=b)", TestEntity.class);
		assertEquals("b", entity.get("a"));
	}

	@Test
	public void testSetValuesFromString_multiple() throws Exception
	{
		Entity entity = AbstractEntity.setValuesFromString("(a=b c=d)", TestEntity.class);
		assertEquals("b", entity.get("a"));
		assertEquals("d", entity.get("c"));
	}

	@Test
	public void testSetValuesFromString_escaping() throws Exception
	{
		Entity entity = AbstractEntity.setValuesFromString("(a='b')", TestEntity.class);
		assertEquals("b", entity.get("a"));
	}

	@Test
	public void testIsObjectRepresentation()
	{
		assertTrue(AbstractEntity.isObjectRepresentation("(obj)"));
		assertTrue(AbstractEntity.isObjectRepresentation("()"));
		assertFalse(AbstractEntity.isObjectRepresentation("(obj"));
		assertFalse(AbstractEntity.isObjectRepresentation("obj)"));
		assertFalse(AbstractEntity.isObjectRepresentation("obj"));
	}

	@Test(expected = ParseException.class)
	public void testString2date_exc() throws ParseException
	{
		AbstractEntity.string2date("this is not a date");
	}

	@Test
	public void testString2date_MMMMdyyyy() throws ParseException
	{
		Date date = AbstractEntity.string2date("October 4, 2012");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertEquals(2012, calendar.get(Calendar.YEAR));
		assertEquals(Calendar.OCTOBER, calendar.get(Calendar.MONTH));
		assertEquals(4, calendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testString2date_ddMMyyyy() throws ParseException
	{
		Date date = AbstractEntity.string2date("4-10-2012");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertEquals(2012, calendar.get(Calendar.YEAR));
		assertEquals(Calendar.OCTOBER, calendar.get(Calendar.MONTH));
		assertEquals(4, calendar.get(Calendar.DAY_OF_MONTH));
	}

	private static class TestEntity extends AbstractEntity
	{
		private static final long serialVersionUID = 1L;

		private Map<String, Object> map;

		@SuppressWarnings("unused")
		public TestEntity()
		{
			map = new HashMap<String, Object>();
		}

		@Override
		public void set(Tuple values, boolean strict) throws Exception
		{
			for (int i = 0; i < values.getNrColumns(); ++i)
				map.put(values.getColName(i), values.getObject(i));
		}

		@Override
		public Object get(String columnName)
		{
			return map.get(columnName);
		}

		@Override
		public String getIdField()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Object getIdValue()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public List<String> getLabelFields()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Vector<String> getFields()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Vector<String> getFields(boolean skipAutoIds)
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public String getFields(String sep)
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public void validate() throws Exception
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Entity create(Tuple tuple) throws Exception
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public String getXrefIdFieldName(String fieldName)
		{
			throw new UnsupportedOperationException();
		}
	}
}