xpath-helper
============

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.itelg/xpath-helper/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.itelg/xpath-helper)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/d69f266924be4b68ba7fb24cb3d49c15)](https://www.codacy.com/app/eggers-julian/xpath-helper)
[![Coverage Status](https://coveralls.io/repos/julian-eggers/xpath-helper/badge.svg)](https://coveralls.io/r/julian-eggers/xpath-helper)
[![Build Status](https://travis-ci.org/julian-eggers/xpath-helper.svg?branch=master)](https://travis-ci.org/julian-eggers/xpath-helper)

XPath helpers based on [XOM](http://www.xom.nu/ "XOM")

#### Maven
```xml
<dependencies>
	<dependency>
		<groupId>com.itelg</groupId>
		<artifactId>xpath-helper</artifactId>
		<version>0.4.4-RELEASE</version>
	</dependency>
</dependencies>
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
