package com.itelg.xpath.helper;

import nu.xom.Element;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;

import com.itelg.xpath.helper.test.support.XmlLoader;
import com.itelg.zkoss.helper.XPathHelper;

public class XPathHelperTest
{
	@Test
	public void testGetNodeList() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertNotNull(XPathHelper.getNodeList("nodes/node", rootElement));
		Assert.assertEquals(2, XPathHelper.getNodeList("nodes/node", rootElement).size());
		Assert.assertNotNull(XPathHelper.getNodeList("nodesEmpty/node", rootElement));
		Assert.assertEquals(0, XPathHelper.getNodeList("nodesEmpty/node", rootElement).size());
		Assert.assertNotNull(XPathHelper.getNodeList("nodesMissing/node", rootElement));
		Assert.assertEquals(0, XPathHelper.getNodeList("nodesMissing/node", rootElement).size());
	}
	
	@Test
	public void testHasNodes() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertTrue(XPathHelper.hasNodes(XPathHelper.getNodeList("nodes/node", rootElement)));
		Assert.assertFalse(XPathHelper.hasNodes(XPathHelper.getNodeList("nodesEmpty/node", rootElement)));
		Assert.assertFalse(XPathHelper.hasNodes(XPathHelper.getNodeList("nodesMissing/node", rootElement)));
		Assert.assertFalse(XPathHelper.hasNodes(null));
	}
	
	@Test
	public void testGetFirstNode() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals("TEST1", XPathHelper.getFirstNode("nodes/node", rootElement).getValue());
		Assert.assertEquals("TEST", XPathHelper.getFirstNode("string", rootElement).getValue());
	}
	
	@Test
	public void testGetString() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals("TEST", XPathHelper.getString("string", rootElement));
		Assert.assertEquals("", XPathHelper.getString("stringEmpty", rootElement));
		Assert.assertNull(XPathHelper.getString("stringMissing", rootElement));
	}
	
	@Test
	public void testGetNullableString() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals("TEST", XPathHelper.getNullableString("string", rootElement));
		Assert.assertNull(XPathHelper.getNullableString("emptyString", rootElement));
	}
	
	@Test
	public void testGetDouble() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals(Double.valueOf(12.1), XPathHelper.getDouble("double", rootElement));
		Assert.assertNull(XPathHelper.getDouble("doubleEmpty", rootElement));
		Assert.assertNull(XPathHelper.getDouble("doubleMissing", rootElement));
	}
	
	@Test(expected = NumberFormatException.class)
	public void testGetDoubleNotConvertable() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		XPathHelper.getDouble("doubleNotConvertable", rootElement);
	}
	
	@Test
	public void testGetInteger() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals(Integer.valueOf(111), XPathHelper.getInteger("integer", rootElement));
		Assert.assertNull(XPathHelper.getInteger("integerEmpty", rootElement));
		Assert.assertNull(XPathHelper.getInteger("integerMissing", rootElement));
	}
	
	@Test(expected = NumberFormatException.class)
	public void testGetIntegerNotConvertable() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		XPathHelper.getInteger("integerNotConvertable", rootElement);
	}
	
	@Test
	public void testGetLong() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals(Long.valueOf(222), XPathHelper.getLong("long", rootElement));
		Assert.assertNull(XPathHelper.getLong("longEmpty", rootElement));
		Assert.assertNull(XPathHelper.getLong("longMissing", rootElement));
	}
	
	@Test(expected = NumberFormatException.class)
	public void testGetLongNotConvertable() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		XPathHelper.getLong("longNotConvertable", rootElement);
	}
	
	@Test
	public void testGetBoolean() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertTrue(XPathHelper.getBoolean("booleanTrue", rootElement).booleanValue());
		Assert.assertFalse(XPathHelper.getBoolean("booleanFalse", rootElement).booleanValue());
		Assert.assertTrue(XPathHelper.getBoolean("booleanTrueUppercase", rootElement).booleanValue());
		Assert.assertFalse(XPathHelper.getBoolean("booleanFalseUppercase", rootElement).booleanValue());
		Assert.assertTrue(XPathHelper.getBoolean("booleanTrueInteger", rootElement).booleanValue());
		Assert.assertFalse(XPathHelper.getBoolean("booleanFalseInteger", rootElement).booleanValue());
		Assert.assertNull(XPathHelper.getBoolean("booleanEmpty", rootElement));
		Assert.assertNull(XPathHelper.getBoolean("booleanMissing", rootElement));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetBooleanNotConvertable() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		XPathHelper.getBoolean("booleanNotConvertable", rootElement);
	}
	
	@Test
	public void testGetDate() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals(new DateTime(2014, 2, 8, 0, 0).toDate(), XPathHelper.getDate("date", "yyyy-MM-dd", rootElement));
		Assert.assertEquals(new DateTime(2014, 2, 8, 3, 11, 57).toDate(), XPathHelper.getDate("dateTime", "yyyy-MM-dd HH:mm:ss", rootElement));
		Assert.assertEquals(new DateTime(2014, 4, 8, 3, 11, 57, 200, DateTimeZone.UTC).toDate(), XPathHelper.getDate("dateTimeZone", "yyyy-MM-dd HH:mm:ss.S z", rootElement));
		Assert.assertNull(XPathHelper.getDate("dateEmpty", "yyyy-MM-dd", rootElement));
		Assert.assertNull(XPathHelper.getDate("dateMissing", "yyyy-MM-dd", rootElement));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDateNotConvertable() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		XPathHelper.getDate("dateNotConvertable", "yyyy-MM-dd", rootElement);
	}
	
	@Test
	public void testGetDateTime() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals(new DateTime(2014, 2, 8, 0, 0), XPathHelper.getDateTime("date", "yyyy-MM-dd", rootElement));
		Assert.assertEquals(new DateTime(2014, 2, 8, 3, 11, 57), XPathHelper.getDateTime("dateTime", "yyyy-MM-dd HH:mm:ss", rootElement));
		Assert.assertEquals(new DateTime(2014, 4, 8, 3, 11, 57, 200, DateTimeZone.UTC), XPathHelper.getDateTime("dateTimeZone", "yyyy-MM-dd HH:mm:ss.S z", rootElement));
		Assert.assertNull(XPathHelper.getDateTime("dateEmpty", "yyyy-MM-dd", rootElement));
		Assert.assertNull(XPathHelper.getDateTime("dateMissing", "yyyy-MM-dd", rootElement));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDateTimeNotConvertable() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		XPathHelper.getDateTime("dateNotConvertable", "yyyy-MM-dd", rootElement);
	}
	
	@Test
	public void testGetEnum() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals(TestEnum.VALUE1, XPathHelper.getEnum("enum", TestEnum.class, rootElement));
		Assert.assertNull(XPathHelper.getEnum("enumEmpty", TestEnum.class, rootElement));
		Assert.assertNull(XPathHelper.getEnum("enumMissing", TestEnum.class, rootElement));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetEnumNotConvertable() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertNull(XPathHelper.getEnum("enumNotConvertable", TestEnum.class, rootElement));
	}
	
	private static enum TestEnum
	{
		VALUE1;
	}
}