package page.object.try4;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.object.base.Page;

import org.testng.annotations.Test;

public class Test1 extends Page
{

	@Test
	public void testCase1()
	{
		System.out.println("this is test case1");
		log.info("this is test case1");
		log.error("this is test case1");
		log.fatal("this is test case1");
		
		
	}
	
	@Test
	public void testCase2()
	{
		System.out.println("this is test case2");		
		log.info("this is test case2");
		log.error("this is test case2");
		log.fatal("this is test case2");
	}
	
	@Test
	public void testCase3()
	{
		Assert.assertTrue(false);
	}
}
