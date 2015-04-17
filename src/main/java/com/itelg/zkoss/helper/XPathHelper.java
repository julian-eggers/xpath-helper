package com.itelg.zkoss.helper;

import java.util.Date;

import nu.xom.Node;
import nu.xom.Nodes;

import org.apache.commons.lang3.StringUtils;

public class XPathHelper
{
	public static Nodes getNodeList(String xpath, Node node) throws Exception
	{
		return node.query(xpath);
	}

	public static boolean hasNodes(Nodes nodes)
	{
		return (nodes != null && nodes.size() > 0);
	}

	public static Node getFirstNode(String xpath, Node node) throws Exception
	{
		Nodes nodes = getNodeList(xpath, node);

		if (nodes.size() != 0)
		{
			return nodes.get(0);
		}

		return null;
	}

	public static String getString(String xpath, Node node) throws Exception
	{
		Node firstNode = getFirstNode(xpath, node);

		if (firstNode != null)
		{
			return firstNode.getValue().trim();
		}

		return null;
	}

	public static String getNullableString(String xpath, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return value;
		}

		return null;
	}

	public static Double getDouble(String xpath, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return Double.valueOf(value);
		}

		return null;
	}

	public static Integer getInteger(String xpath, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return Integer.valueOf(value);
		}

		return null;
	}

	public static Long getLong(String xpath, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return Long.valueOf(value);
		}

		return null;
	}

	public static Boolean getBoolean(String xpath, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return Boolean.valueOf(value);
		}

		return null;
	}

	public static Date getDate(String xpath, String format, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return DateHelper.toDate(value, format);
		}

		return null;
	}
}