package com.itelg.xpath.helper;

import nu.xom.Element;

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
	}
	
	@Test
	public void testGetNodeListEmpty() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertNotNull(XPathHelper.getNodeList("emptyNodes/node", rootElement));
		Assert.assertEquals(0, XPathHelper.getNodeList("emptyNodes/node", rootElement).size());
	}
	
	@Test
	public void testHasNodes() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertTrue(XPathHelper.hasNodes(XPathHelper.getNodeList("nodes/node", rootElement)));
		Assert.assertFalse(XPathHelper.hasNodes(XPathHelper.getNodeList("emptyNodes/node", rootElement)));
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
		Assert.assertEquals("", XPathHelper.getString("emptyString", rootElement));
	}
	
	@Test
	public void testGetNullableString() throws Exception
	{
		Element rootElement = XmlLoader.loadElement("valid.xml");
		Assert.assertEquals("TEST", XPathHelper.getNullableString("string", rootElement));
		Assert.assertNull(XPathHelper.getNullableString("emptyString", rootElement));
	}
}