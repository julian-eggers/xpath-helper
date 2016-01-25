package com.itelg.xpath.helper;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.itelg.xpath.exception.XPathValueConvertException;

import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;

public class XPathHelper
{
	private static final String[] booleanValues = new String[]
	{ "1", "0", "true", "false" };
	
	/**
	 * Prevent initialization
	 */
	private XPathHelper()
	{
		
	}

	public static Nodes getNodes(String xpath, Node node)
	{
		return node.query(xpath);
	}
	
	public static List<Node> getNodeList(String xpath, Node node)
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
	
	public static boolean hasNode(String xpath, Node node)
	{
		return getFirstNode(xpath, node) != null;
	}

	public static Node getFirstNode(String xpath, Node node)
	{
		Nodes nodes = getNodes(xpath, node);

		if (hasNodes(nodes))
		{
			return nodes.get(0);
		}

		return null;
	}
	
	public static Element getFirstElement(String xpath, Node node)
	{
		Node firstNode = getFirstNode(xpath, node);
		
		if (firstNode != null)
		{
			return (Element) firstNode;
		}
		
		return null;
	}

	public static String getString(String xpath, Node node)
	{
		Node firstNode = getFirstNode(xpath, node);

		if (firstNode != null)
		{
			return firstNode.getValue().trim();
		}

		return null;
	}

	public static String getNullableString(String xpath, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			return value;
		}

		return null;
	}

	public static Double getDouble(String xpath, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return Double.valueOf(value);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return null;
	}
	
	public static double getPDouble(String xpath, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return Double.parseDouble(value);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return 0;
	}

	public static Integer getInteger(String xpath, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return Integer.valueOf(value);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return null;
	}
	
	public static int getInt(String xpath, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return Integer.parseInt(value);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return 0;
	}

	public static Long getLong(String xpath, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return Long.valueOf(value);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return null;
	}
	
	public static long getPLong(String xpath, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return Long.parseLong(value);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return 0;
	}

	public static Boolean getBoolean(String xpath, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			if (ArrayUtils.contains(booleanValues, value.toLowerCase()))
			{
				if (StringUtils.isNumeric(value))
				{
					return Boolean.valueOf("1".equals(value));
				}
			}
			else
			{
				throw new XPathValueConvertException(value);
			}

			return Boolean.valueOf(value);
		}

		return null;
	}
	
	public static boolean getBool(String xpath, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			if (ArrayUtils.contains(booleanValues, value.toLowerCase()))
			{
				if (StringUtils.isNumeric(value))
				{
					return "1".equals(value);
				}
			}
			else
			{
				throw new XPathValueConvertException(value);
			}

			return Boolean.parseBoolean(value);
		}

		return false;
	}

	public static Date getDate(String xpath, String format, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return DateHelper.toDate(value, format);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return null;
	}

	public static DateTime getDateTime(String xpath, String format, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return DateHelper.toDateTime(value, format);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return null;
	}
	
	public static ZonedDateTime getZonedDateTime(String xpath, DateTimeFormatter formatter, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return DateHelper.toZonedDateTime(value, formatter);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return null;
	}
	
	public static LocalDateTime getLocalDateTime(String xpath, DateTimeFormatter formatter, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return DateHelper.toLocalDateTime(value, formatter);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return null;
	}

	public static <E extends Enum<E>> E getEnum(String xpath, Class<E> clazz, Node node)
	{
		String value = getString(xpath, node);

		if (StringUtils.isNotBlank(value))
		{
			try
			{
				return Enum.valueOf(clazz, value);
			}
			catch (Exception e)
			{
				throw new XPathValueConvertException(e, value);
			}
		}

		return null;
	}
}