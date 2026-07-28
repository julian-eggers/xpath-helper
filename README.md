xpath-helper
============

[![Maven Central](https://img.shields.io/maven-central/v/com.itelg/xpath-helper.svg?label=maven-central)](https://search.maven.org/artifact/com.itelg/xpath-helper)
[![Release](https://github.com/julian-eggers/xpath-helper/actions/workflows/release.yml/badge.svg)](https://github.com/julian-eggers/xpath-helper/actions/workflows/release.yml)
[![Nightly build](https://github.com/julian-eggers/xpath-helper/actions/workflows/nightly.yml/badge.svg)](https://github.com/julian-eggers/xpath-helper/actions/workflows/nightly.yml)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=julian-eggers_xpath-helper&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=julian-eggers_xpath-helper)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=julian-eggers_xpath-helper&metric=coverage)](https://sonarcloud.io/summary/new_code?id=julian-eggers_xpath-helper)

XPath helpers based on [XOM](http://www.xom.nu/ "XOM")

#### Maven
```xml
<dependency>
	<groupId>com.itelg</groupId>
	<artifactId>xpath-helper</artifactId>
	<version>1.0.0</version>
</dependency>
```

#### Supported methods
```java
getNodes() : Nodes
getNodeList() : List<Node>
hasNodes() : boolean
hasNode() : boolean
getFirstNode() : Node
getFirstElement() : Element
getLastNode() : Node
getLastElement() : Element
getString() : String
getNullableString() : String
getDouble() : Double
getPDouble() : double
getInteger() : Integer
getInt() : int
getLong() : Long
getPLong() : long
getBoolean() : Boolean
getBool() : boolean
getZonedDateTime() : ZonedDateTime
getLocalDateTime() : LocalDateTime
getLocalDate() : LocalDate
getEnum() : Enum<E>
```

#### AbstractParser-Example
```java
public static void main(String[] args) throws Exception
{
	String xml = "<data><stringValue>Test</stringValue>"
			+ "<doubleValue>1.12</doubleValue>"
			+ "<testEnum>VALUE</testEnum></data>";
	TestClass testClass = new TestParser().parse(xml);
	System.out.println(testClass);
}

public static class TestParser extends AbstractParser<TestClass>
{
	@Override
	protected TestClass doParse(Element rootElement) throws Exception
	{
		TestClass test = new TestClass();
		test.stringValue = XPathHelper.getString("stringValue", rootElement);
		test.doubleValue = XPathHelper.getDouble("doubleValue", rootElement);
		test.testEnum = XPathHelper.getEnum("testEnum", TestClass.TestEnum.class, rootElement);
		return test;
	}
}

public static class TestClass
{
	private String stringValue;
	private Double doubleValue;
	private TestEnum testEnum;
	
	@Override
	public String toString()
	{
		return "TestClass [stringValue=" + stringValue + ", doubleValue=" + doubleValue + ", testEnum=" + testEnum + "]";
	}

	private enum TestEnum
	{
		VALUE;
	}
}
```

#### Plain-Example
```java
public static void main(String[] args) throws Exception
{
	String xml = "<data><stringValue>Test</stringValue>"
			+ "<doubleValue>1.12</doubleValue></data>";
	Element rootElement = DocumentHelper.getRootElement(xml);
	System.out.println(XPathHelper.getString("stringValue", rootElement));
	System.out.println(XPathHelper.getDouble("doubleValue", rootElement));
}
```



## Build & Release

### Build
```
mvn clean package
```

### Release
```
mvn clean deploy
```
