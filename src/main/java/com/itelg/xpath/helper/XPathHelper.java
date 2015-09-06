package com.itelg.xpath.helper;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import nu.xom.Node;
import nu.xom.Nodes;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class XPathHelper
{
	private static final String[] booleanValues = new String[]
	{ "1", "0", "true", "false" };

	public static Nodes getNodes(String xpath, Node node) throws Exception
	{
		return node.query(xpath);
	}
	
	public static List<Node> getNodeList(String xpath, Node node) throws Exception
	{
		Nodes nodes = getNodes(xpath, node);
		List<Node> nodeList = new LinkedList<Node>();
		
		for (int i = 0; i < nodes.size(); i++)
		{
			nodeList.add(nodes.get(i));
		}
		
		return nodeList;
	}

	public static boolean hasNodes(Nodes nodes)
	{
		return (nodes != null && nodes.size() > 0);
	}

	public static Node getFirstNode(String xpath, Node node) throws Exception
	{
		Nodes nodes = getNodes(xpath, node);

		if (hasNodes(nodes))
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
			if (ArrayUtils.contains(booleanValues, value.toLowerCase()))
			{
				if (StringUtils.isNumeric(value))
				{
					return Boolean.valueOf(value.equals("1"));
				}
			}
			else
			{
				throw new IllegalArgumentException("cannot parse boolean \"" + value + "\"");
			}

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

	public static DateTime getDateTime(String xpath, String format, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return DateHelper.toDateTime(value, format);
		}

		return null;
	}
	
	public static ZonedDateTime getZonedDateTime(String xpath, DateTimeFormatter formatter, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return DateHelper.toZonedDateTime(value, formatter);
		}

		return null;
	}
	
	public static LocalDateTime getLocalDateTime(String xpath, DateTimeFormatter formatter, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return DateHelper.toLocalDateTime(value, formatter);
		}

		return null;
	}

	public static <E extends Enum<E>> E getEnum(String xpath, Class<E> clazz, Node node) throws Exception
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return Enum.valueOf(clazz, value);
		}

		return null;
	}
}