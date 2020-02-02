package org.meanbean.test;

import org.meanbean.bean.info.BeanInformationFactory;
import org.meanbean.factories.FactoryCollection;
import org.meanbean.factories.util.FactoryLookupStrategy;
import org.meanbean.logging.$LoggerFactory;
import org.meanbean.util.RandomValueGenerator;
import org.meanbean.util.ValidationHelper;

public class BeanTesterBuilder {

	private int iterations = BeanTester.TEST_ITERATIONS_PER_BEAN;

	private RandomValueGenerator randomValueGenerator = RandomValueGenerator.getInstance();

	private FactoryCollection factoryCollection = FactoryCollection.getInstance();

	private FactoryLookupStrategy factoryLookupStrategy = FactoryLookupStrategy.getInstance();

	private BeanInformationFactory beanInformationFactory = BeanInformationFactory.getInstance();

	private BeanPropertyTester beanPropertyTester = new BeanPropertyTester();

	private ValidationHelper validationHelper = ValidationHelper.getInstance($LoggerFactory.getLogger(BeanTester.class));

	public int getIterations() {
		return iterations;
	}

	public BeanTesterBuilder setIterations(int iterations) {
		this.iterations = iterations;
		return this;
	}

	public RandomValueGenerator getRandomValueGenerator() {
		return randomValueGenerator;
	}

	public BeanTesterBuilder setRandomValueGenerator(RandomValueGenerator randomValueGenerator) {
		this.randomValueGenerator = randomValueGenerator;
		return this;
	}

	public FactoryCollection getFactoryCollection() {
		return factoryCollection;
	}

	public BeanTesterBuilder setFactoryCollection(FactoryCollection factoryCollection) {
		this.factoryCollection = factoryCollection;
		return this;
	}

	public FactoryLookupStrategy getFactoryLookupStrategy() {
		return factoryLookupStrategy;
	}

	public BeanTesterBuilder setFactoryLookupStrategy(FactoryLookupStrategy factoryLookupStrategy) {
		this.factoryLookupStrategy = factoryLookupStrategy;
		return this;
	}

	public BeanInformationFactory getBeanInformationFactory() {
		return beanInformationFactory;
	}

	public BeanTesterBuilder setBeanInformationFactory(BeanInformationFactory beanInformationFactory) {
		this.beanInformationFactory = beanInformationFactory;
		return this;
	}

	public BeanPropertyTester getBeanPropertyTester() {
		return beanPropertyTester;
	}

	public BeanTesterBuilder setBeanPropertyTester(BeanPropertyTester beanPropertyTester) {
		this.beanPropertyTester = beanPropertyTester;
		return this;
	}

	public ValidationHelper getValidationHelper() {
		return validationHelper;
	}

	public BeanTesterBuilder setValidationHelper(ValidationHelper validationHelper) {
		this.validationHelper = validationHelper;
		return this;
	}

	public BeanTester build() {
		return new BeanTester(iterations,
				randomValueGenerator,
				factoryCollection,
				factoryLookupStrategy,
				beanInformationFactory,
				beanPropertyTester, validationHelper);
	}
}