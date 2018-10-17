package com.itelg.xpath.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

import com.itelg.xpath.exception.XPathValueConvertException;
import com.itelg.xpath.helper.test.support.XmlLoader;

import nu.xom.Element;

public class XPathHelperTest
{
    @Test(expected = IllegalAccessException.class)
    public void testPrivateConstructor() throws InstantiationException, IllegalAccessException
    {
        XPathHelper.class.newInstance();
        Assert.fail("Constructor should be private");
    }

    @Test
    public void testGetNodes() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertNotNull(XPathHelper.getNodes("nodes/node", rootElement));
        Assert.assertEquals(2, XPathHelper.getNodes("nodes/node", rootElement).size());
        Assert.assertNotNull(XPathHelper.getNodes("nodesEmpty/node", rootElement));
        Assert.assertEquals(0, XPathHelper.getNodes("nodesEmpty/node", rootElement).size());
        Assert.assertNotNull(XPathHelper.getNodes("nodesMissing/node", rootElement));
        Assert.assertEquals(0, XPathHelper.getNodes("nodesMissing/node", rootElement).size());
    }

    @Test
    public void testGetNodeList() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
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
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertTrue(XPathHelper.hasNodes(XPathHelper.getNodes("nodes/node", rootElement)));
        Assert.assertFalse(XPathHelper.hasNodes(XPathHelper.getNodes("nodesEmpty/node", rootElement)));
        Assert.assertFalse(XPathHelper.hasNodes(XPathHelper.getNodes("nodesMissing/node", rootElement)));
        Assert.assertFalse(XPathHelper.hasNodes(null));
    }

    @Test
    public void testHasNode() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertTrue(XPathHelper.hasNode("nodes/node", rootElement));
        Assert.assertTrue(XPathHelper.hasNode("nodesEmpty", rootElement));
        Assert.assertFalse(XPathHelper.hasNode("nodesMissing", rootElement));
        Assert.assertFalse(XPathHelper.hasNode("nodesMissing/node", rootElement));
    }

    @Test
    public void testGetFirstNode() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals("TEST1", XPathHelper.getFirstNode("nodes/node", rootElement).getValue());
        Assert.assertEquals("TEST", XPathHelper.getFirstNode("string", rootElement).getValue());
        Assert.assertNull(XPathHelper.getFirstNode("nodesMissing", rootElement));
    }

    @Test
    public void testGetFirstElement() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals("TEST1", XPathHelper.getFirstElement("nodes/node", rootElement).getValue());
        Assert.assertEquals("TEST", XPathHelper.getFirstElement("string", rootElement).getValue());
        Assert.assertNull(XPathHelper.getFirstElement("nodesMissing", rootElement));
    }

    @Test
    public void testGetLastNode() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals("TEST", XPathHelper.getLastNode("nodes/node", rootElement).getValue());
        Assert.assertEquals("TEST", XPathHelper.getLastNode("string", rootElement).getValue());
        Assert.assertNull(XPathHelper.getLastNode("nodesMissing", rootElement));
    }

    @Test
    public void testGetLastElement() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals("TEST", XPathHelper.getLastElement("nodes/node", rootElement).getValue());
        Assert.assertEquals("TEST", XPathHelper.getLastElement("string", rootElement).getValue());
        Assert.assertNull(XPathHelper.getLastElement("nodesMissing", rootElement));
    }

    @Test
    public void testGetString() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals("TEST", XPathHelper.getString("string", rootElement));
        Assert.assertEquals("", XPathHelper.getString("stringEmpty", rootElement));
        Assert.assertNull(XPathHelper.getString("stringMissing", rootElement));
    }

    @Test
    public void testGetNullableString() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals("TEST", XPathHelper.getNullableString("string", rootElement));
        Assert.assertNull(XPathHelper.getNullableString("emptyString", rootElement));
    }

    @Test
    public void testGetDouble() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals(Double.valueOf(12.1), XPathHelper.getDouble("double", rootElement));
        Assert.assertNull(XPathHelper.getDouble("doubleEmpty", rootElement));
        Assert.assertNull(XPathHelper.getDouble("doubleMissing", rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetDoubleNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getDouble("doubleNotConvertable", rootElement);
    }

    @Test
    public void testGetPDouble() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals(12.1, XPathHelper.getPDouble("double", rootElement), 0);
        Assert.assertEquals(0, XPathHelper.getPDouble("doubleEmpty", rootElement), 0);
        Assert.assertEquals(0, XPathHelper.getPDouble("doubleMissing", rootElement), 0);
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetPDoubleNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getPDouble("doubleNotConvertable", rootElement);
    }

    @Test
    public void testGetInteger() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals(Integer.valueOf(111), XPathHelper.getInteger("integer", rootElement));
        Assert.assertNull(XPathHelper.getInteger("integerEmpty", rootElement));
        Assert.assertNull(XPathHelper.getInteger("integerMissing", rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetIntegerNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getInteger("integerNotConvertable", rootElement);
    }

    @Test
    public void testGetInt() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals(111, XPathHelper.getInt("integer", rootElement));
        Assert.assertEquals(0, XPathHelper.getInt("integerEmpty", rootElement));
        Assert.assertEquals(0, XPathHelper.getInt("integerMissing", rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetIntNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getInt("integerNotConvertable", rootElement);
    }

    @Test
    public void testGetLong() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals(Long.valueOf(222), XPathHelper.getLong("long", rootElement));
        Assert.assertNull(XPathHelper.getLong("longEmpty", rootElement));
        Assert.assertNull(XPathHelper.getLong("longMissing", rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetLongNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getLong("longNotConvertable", rootElement);
    }

    @Test
    public void testGetPLong() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals(222, XPathHelper.getPLong("long", rootElement));
        Assert.assertEquals(0, XPathHelper.getPLong("longEmpty", rootElement));
        Assert.assertEquals(0, XPathHelper.getPLong("longMissing", rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetPLongNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getPLong("longNotConvertable", rootElement);
    }

    @Test
    public void testGetBoolean() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertTrue(XPathHelper.getBoolean("booleanTrue", rootElement).booleanValue());
        Assert.assertFalse(XPathHelper.getBoolean("booleanFalse", rootElement).booleanValue());
        Assert.assertTrue(XPathHelper.getBoolean("booleanTrueUppercase", rootElement).booleanValue());
        Assert.assertFalse(XPathHelper.getBoolean("booleanFalseUppercase", rootElement).booleanValue());
        Assert.assertTrue(XPathHelper.getBoolean("booleanTrueInteger", rootElement).booleanValue());
        Assert.assertFalse(XPathHelper.getBoolean("booleanFalseInteger", rootElement).booleanValue());
        Assert.assertNull(XPathHelper.getBoolean("booleanEmpty", rootElement));
        Assert.assertNull(XPathHelper.getBoolean("booleanMissing", rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetBooleanNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getBoolean("booleanNotConvertable", rootElement);
    }

    @Test
    public void testGetBool() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertTrue(XPathHelper.getBool("booleanTrue", rootElement));
        Assert.assertFalse(XPathHelper.getBool("booleanFalse", rootElement));
        Assert.assertTrue(XPathHelper.getBool("booleanTrueUppercase", rootElement));
        Assert.assertFalse(XPathHelper.getBool("booleanFalseUppercase", rootElement));
        Assert.assertTrue(XPathHelper.getBool("booleanTrueInteger", rootElement));
        Assert.assertFalse(XPathHelper.getBool("booleanFalseInteger", rootElement));
        Assert.assertFalse(XPathHelper.getBool("booleanEmpty", rootElement));
        Assert.assertFalse(XPathHelper.getBool("booleanMissing", rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetBoolNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getBool("booleanNotConvertable", rootElement);
    }

    @Test
    public void testGetZonedDateTime() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals(ZonedDateTime.of(2015, 7, 8, 12, 21, 30, 0, ZoneId.of("+02:00")), XPathHelper.getZonedDateTime("zonedDateTime", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement).withNano(0));
        Assert.assertNull(XPathHelper.getZonedDateTime("zonedDateTimeEmpty", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
        Assert.assertNull(XPathHelper.getZonedDateTime("zonedDateTimeMissing", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetZonedDateTimeNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getZonedDateTime("zonedDateTimeNotConvertable", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement);
    }

    @Test
    public void testGetLocalDateTime() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        LocalDateTime localDateTime = XPathHelper.getLocalDateTime("localDateTime", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement).withNano(0);
        Assertions.assertThat(localDateTime).isIn(LocalDateTime.of(2015, 7, 8, 10, 21, 30, 0), LocalDateTime.of(2015, 7, 8, 12, 21, 30, 0));
        Assert.assertNull(XPathHelper.getLocalDateTime("localDateTimeEmpty", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
        Assert.assertNull(XPathHelper.getLocalDateTime("localDateTimeMissing", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetLocalDateTimeNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getLocalDateTime("localDateTimeNotConvertable", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement);
    }

    @Test
    public void testGetLocalDate() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(LocalDate.of(2015, 7, 8), XPathHelper.getLocalDate("localDate", rootElement));
        assertNull(XPathHelper.getLocalDate("localDateEmpty", rootElement));
        assertNull(XPathHelper.getLocalDate("localDateMissing", rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetLocalDateNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        XPathHelper.getLocalDate("localDateNotConvertable", rootElement);
    }

    @Test
    public void testGetEnum() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertEquals(TestEnum.VALUE1, XPathHelper.getEnum("enum", TestEnum.class, rootElement));
        Assert.assertNull(XPathHelper.getEnum("enumEmpty", TestEnum.class, rootElement));
        Assert.assertNull(XPathHelper.getEnum("enumMissing", TestEnum.class, rootElement));
    }

    @Test(expected = XPathValueConvertException.class)
    public void testGetEnumNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        Assert.assertNull(XPathHelper.getEnum("enumNotConvertable", TestEnum.class, rootElement));
    }

	@Test
	public void testGetEnumOrDefault() throws Exception
	{
		Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
		assertEquals(TestEnum.DEFAULT,
				XPathHelper.getEnum("enumNotConvertable", TestEnum.class, TestEnum.DEFAULT, rootElement));
	}

	@Test
	public void testGetEnumWithEmpty() throws Exception
	{
		Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
		assertNull(XPathHelper.getEnum("enumEmpty", TestEnum.class, TestEnum.DEFAULT, rootElement));
		assertNull(XPathHelper.getEnum("enumMissing", TestEnum.class, TestEnum.DEFAULT, rootElement));
	}

	@Test
	public void testGetEnumWithKnownEnum() throws Exception
	{
		Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
		assertEquals(TestEnum.VALUE1, XPathHelper.getEnum("enum", TestEnum.class, TestEnum.DEFAULT, rootElement));
	}

    private static enum TestEnum
    {
		VALUE1, DEFAULT;
    }
}