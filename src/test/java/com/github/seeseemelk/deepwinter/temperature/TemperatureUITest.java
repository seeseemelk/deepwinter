package com.github.seeseemelk.deepwinter.temperature;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TemperatureUITest
{

	@Test
	public void testHighTemperatureShouldBe1()
	{
		assertThat(TemperatureUI.getTemperatureBarProgression(50), is(1.0));
	}
	
	@Test
	public void testLowTemperatureShouldBe0()
	{
		assertThat(TemperatureUI.getTemperatureBarProgression(20), is(0.0));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMediumTemperatureShouldBeInBetween()
	{
		double temperature = TemperatureUI.getTemperatureBarProgression(39);
		assertThat(temperature, allOf(greaterThan(0.0), lessThan(1.0)));
	}

}
