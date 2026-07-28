package com.itelg.xpath.helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import com.itelg.xpath.exception.XPathValueConvertException;
import com.itelg.xpath.helper.test.support.XmlLoader;

import nu.xom.Element;

class XPathHelperTest
{
    @Test
    void testGetNodes() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertNotNull(XPathHelper.getNodes("nodes/node", rootElement));
        assertEquals(2, XPathHelper.getNodes("nodes/node", rootElement).size());
        assertNotNull(XPathHelper.getNodes("nodesEmpty/node", rootElement));
        assertEquals(0, XPathHelper.getNodes("nodesEmpty/node", rootElement).size());
        assertNotNull(XPathHelper.getNodes("nodesMissing/node", rootElement));
        assertEquals(0, XPathHelper.getNodes("nodesMissing/node", rootElement).size());
    }

    @Test
    void testGetNodeList() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertNotNull(XPathHelper.getNodeList("nodes/node", rootElement));
        assertEquals(2, XPathHelper.getNodeList("nodes/node", rootElement).size());
        assertNotNull(XPathHelper.getNodeList("nodesEmpty/node", rootElement));
        assertEquals(0, XPathHelper.getNodeList("nodesEmpty/node", rootElement).size());
        assertNotNull(XPathHelper.getNodeList("nodesMissing/node", rootElement));
        assertEquals(0, XPathHelper.getNodeList("nodesMissing/node", rootElement).size());
    }

    @Test
    void testHasNodes() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertTrue(XPathHelper.hasNodes(XPathHelper.getNodes("nodes/node", rootElement)));
        assertFalse(XPathHelper.hasNodes(XPathHelper.getNodes("nodesEmpty/node", rootElement)));
        assertFalse(XPathHelper.hasNodes(XPathHelper.getNodes("nodesMissing/node", rootElement)));
        assertFalse(XPathHelper.hasNodes(null));
    }

    @Test
    void testHasNode() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertTrue(XPathHelper.hasNode("nodes/node", rootElement));
        assertTrue(XPathHelper.hasNode("nodesEmpty", rootElement));
        assertFalse(XPathHelper.hasNode("nodesMissing", rootElement));
        assertFalse(XPathHelper.hasNode("nodesMissing/node", rootElement));
    }

    @Test
    void testGetFirstNode() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals("TEST1", XPathHelper.getFirstNode("nodes/node", rootElement).getValue());
        assertEquals("TEST", XPathHelper.getFirstNode("string", rootElement).getValue());
        assertNull(XPathHelper.getFirstNode("nodesMissing", rootElement));
    }

    @Test
    void testGetFirstElement() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals("TEST1", XPathHelper.getFirstElement("nodes/node", rootElement).getValue());
        assertEquals("TEST", XPathHelper.getFirstElement("string", rootElement).getValue());
        assertNull(XPathHelper.getFirstElement("nodesMissing", rootElement));
    }

    @Test
    void testGetLastNode() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals("TEST", XPathHelper.getLastNode("nodes/node", rootElement).getValue());
        assertEquals("TEST", XPathHelper.getLastNode("string", rootElement).getValue());
        assertNull(XPathHelper.getLastNode("nodesMissing", rootElement));
    }

    @Test
    void testGetLastElement() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals("TEST", XPathHelper.getLastElement("nodes/node", rootElement).getValue());
        assertEquals("TEST", XPathHelper.getLastElement("string", rootElement).getValue());
        assertNull(XPathHelper.getLastElement("nodesMissing", rootElement));
    }

    @Test
    void testGetString() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals("TEST", XPathHelper.getString("string", rootElement));
        assertEquals("", XPathHelper.getString("stringEmpty", rootElement));
        assertNull(XPathHelper.getString("stringMissing", rootElement));
    }

    @Test
    void testGetNullableString() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals("TEST", XPathHelper.getNullableString("string", rootElement));
        assertNull(XPathHelper.getNullableString("emptyString", rootElement));
    }

    @Test
    void testGetDouble() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(Double.valueOf(12.1), XPathHelper.getDouble("double", rootElement));
        assertNull(XPathHelper.getDouble("doubleEmpty", rootElement));
        assertNull(XPathHelper.getDouble("doubleMissing", rootElement));
    }

    @Test
    void testGetDoubleNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getDouble("doubleNotConvertable", rootElement));
    }

    @Test
    void testGetPDouble() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(12.1, XPathHelper.getPDouble("double", rootElement), 0);
        assertEquals(0, XPathHelper.getPDouble("doubleEmpty", rootElement), 0);
        assertEquals(0, XPathHelper.getPDouble("doubleMissing", rootElement), 0);
    }

    @Test
    void testGetPDoubleNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getPDouble("doubleNotConvertable", rootElement));
    }

    @Test
    void testGetInteger() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(Integer.valueOf(111), XPathHelper.getInteger("integer", rootElement));
        assertNull(XPathHelper.getInteger("integerEmpty", rootElement));
        assertNull(XPathHelper.getInteger("integerMissing", rootElement));
    }

    @Test
    void testGetIntegerNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getInteger("integerNotConvertable", rootElement));
    }

    @Test
    void testGetInt() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(111, XPathHelper.getInt("integer", rootElement));
        assertEquals(0, XPathHelper.getInt("integerEmpty", rootElement));
        assertEquals(0, XPathHelper.getInt("integerMissing", rootElement));
    }

    @Test
    void testGetIntNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getInt("integerNotConvertable", rootElement));
    }

    @Test
    void testGetLong() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(Long.valueOf(222), XPathHelper.getLong("long", rootElement));
        assertNull(XPathHelper.getLong("longEmpty", rootElement));
        assertNull(XPathHelper.getLong("longMissing", rootElement));
    }

    @Test
    void testGetLongNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getLong("longNotConvertable", rootElement));
    }

    @Test
    void testGetPLong() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(222, XPathHelper.getPLong("long", rootElement));
        assertEquals(0, XPathHelper.getPLong("longEmpty", rootElement));
        assertEquals(0, XPathHelper.getPLong("longMissing", rootElement));
    }

    @Test
    void testGetPLongNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getPLong("longNotConvertable", rootElement));
    }

    @Test
    void testGetBoolean() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertTrue(XPathHelper.getBoolean("booleanTrue", rootElement).booleanValue());
        assertFalse(XPathHelper.getBoolean("booleanFalse", rootElement).booleanValue());
        assertTrue(XPathHelper.getBoolean("booleanTrueUppercase", rootElement).booleanValue());
        assertFalse(XPathHelper.getBoolean("booleanFalseUppercase", rootElement).booleanValue());
        assertTrue(XPathHelper.getBoolean("booleanTrueInteger", rootElement).booleanValue());
        assertFalse(XPathHelper.getBoolean("booleanFalseInteger", rootElement).booleanValue());
        assertNull(XPathHelper.getBoolean("booleanEmpty", rootElement));
        assertNull(XPathHelper.getBoolean("booleanMissing", rootElement));
    }

    @Test
    void testGetBooleanNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getBoolean("booleanNotConvertable", rootElement));
    }

    @Test
    void testGetBool() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertTrue(XPathHelper.getBool("booleanTrue", rootElement));
        assertFalse(XPathHelper.getBool("booleanFalse", rootElement));
        assertTrue(XPathHelper.getBool("booleanTrueUppercase", rootElement));
        assertFalse(XPathHelper.getBool("booleanFalseUppercase", rootElement));
        assertTrue(XPathHelper.getBool("booleanTrueInteger", rootElement));
        assertFalse(XPathHelper.getBool("booleanFalseInteger", rootElement));
        assertFalse(XPathHelper.getBool("booleanEmpty", rootElement));
        assertFalse(XPathHelper.getBool("booleanMissing", rootElement));
    }

    @Test
    void testGetBoolNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getBool("booleanNotConvertable", rootElement));
    }

    @Test
    void testGetZonedDateTime() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(ZonedDateTime.of(2015, 7, 8, 12, 21, 30, 0, ZoneId.of("+02:00")), XPathHelper
                .getZonedDateTime("zonedDateTime", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement)
                .withNano(0));
        assertNull(XPathHelper.getZonedDateTime("zonedDateTimeEmpty", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
        assertNull(XPathHelper.getZonedDateTime("zonedDateTimeMissing", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
    }

    @Test
    void testGetZonedDateTimeNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class,
                () -> XPathHelper.getZonedDateTime("zonedDateTimeNotConvertable", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
    }

    @Test
    void testGetLocalDateTime() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        LocalDateTime localDateTime = XPathHelper.getLocalDateTime("localDateTime", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement).withNano(0);
        assertThat(localDateTime).isIn(LocalDateTime.of(2015, 7, 8, 10, 21, 30, 0), LocalDateTime.of(2015, 7, 8, 12, 21, 30, 0));
        assertNull(XPathHelper.getLocalDateTime("localDateTimeEmpty", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
        assertNull(XPathHelper.getLocalDateTime("localDateTimeMissing", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
    }

    @Test
    void testGetLocalDateTimeNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class,
                () -> XPathHelper.getLocalDateTime("localDateTimeNotConvertable", DateTimeFormatter.ISO_OFFSET_DATE_TIME, rootElement));
    }

    @Test
    void testGetLocalDate() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(LocalDate.of(2015, 7, 8), XPathHelper.getLocalDate("localDate", rootElement));
        assertNull(XPathHelper.getLocalDate("localDateEmpty", rootElement));
        assertNull(XPathHelper.getLocalDate("localDateMissing", rootElement));
    }

    @Test
    void testGetLocalDateNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getLocalDate("localDateNotConvertable", rootElement));
    }

    @Test
    void testGetEnum() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(TestEnum.VALUE1, XPathHelper.getEnum("enum", TestEnum.class, rootElement));
        assertNull(XPathHelper.getEnum("enumEmpty", TestEnum.class, rootElement));
        assertNull(XPathHelper.getEnum("enumMissing", TestEnum.class, rootElement));
    }

    @Test
    void testGetEnumNotConvertable() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertThrows(XPathValueConvertException.class, () -> XPathHelper.getEnum("enumNotConvertable", TestEnum.class, rootElement));
    }

    @Test
    void testGetEnumOrDefault() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(TestEnum.DEFAULT, XPathHelper.getEnum("enumNotConvertable", TestEnum.class, TestEnum.DEFAULT, rootElement));
    }

    @Test
    void testGetEnumWithEmpty() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertNull(XPathHelper.getEnum("enumEmpty", TestEnum.class, TestEnum.DEFAULT, rootElement));
        assertNull(XPathHelper.getEnum("enumMissing", TestEnum.class, TestEnum.DEFAULT, rootElement));
    }

    @Test
    void testGetEnumWithKnownEnum() throws Exception
    {
        Element rootElement = DocumentHelper.getRootElement(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals(TestEnum.VALUE1, XPathHelper.getEnum("enum", TestEnum.class, TestEnum.DEFAULT, rootElement));
    }

    private static enum TestEnum
    {
        VALUE1,
        DEFAULT;
    }
}
