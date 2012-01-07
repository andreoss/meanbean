package org.meanbean.factories.equivalent;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meanbean.bean.info.BeanInformation;
import org.meanbean.bean.util.BasicBeanPopulator;
import org.meanbean.bean.util.BeanPopulator;
import org.meanbean.bean.util.BeanPropertyValuesFactory;
import org.meanbean.factories.BasicNewObjectInstanceFactory;
import org.meanbean.factories.beans.BeanCreationException;
import org.meanbean.factories.util.FactoryLookupStrategy;
import org.meanbean.lang.EquivalentFactory;
import org.meanbean.util.SimpleValidationHelper;
import org.meanbean.util.ValidationHelper;

/**
 * Factory that creates object instances based on provided BeanInformation, assigning every instance the same field
 * values.
 * 
 * @author Graham Williamson
 */
public class EquivalentPopulatedBeanFactory implements EquivalentFactory<Object> {

	/** Logging mechanism. */
	private final Log log = LogFactory.getLog(EquivalentPopulatedBeanFactory.class);

	/** Input validation helper. */
	private final ValidationHelper validationHelper = new SimpleValidationHelper(log);

	/** The BeanInformation that should be used to create instances of a bean. */
	private final BeanInformation beanInformation;

	/** The values every object instance created by this Factory should have. */
	private Map<String, Object> propertyValues;

	/** Creates values that can be used to populate the properties of a Bean. */
	private final BeanPropertyValuesFactory beanPropertyValuesFactory;

	/** Affords functionality to populate a bean (set its fields) with specified values. */
	private final BeanPopulator beanPopulator = new BasicBeanPopulator();

	/**
	 * Construct a new Factory that creates object instances based on provided BeanInformation, assigning every instance
	 * the same field values.
	 * 
	 * @param beanInformation
	 *            Information used to create instances of a bean.
	 * @param factoryLookupStrategy
	 *            Provides a means of acquiring Factories that can be used to create values for the fields of new object
	 *            instances.
	 * @throws IllegalArgumentException
	 *             If either the specified BeanInformation or the FactoryLookupStrategy is deemed illegal. For example,
	 *             if either is <code>null</code>.
	 */
	public EquivalentPopulatedBeanFactory(BeanInformation beanInformation, FactoryLookupStrategy factoryLookupStrategy)
	        throws IllegalArgumentException {
		validationHelper.ensureExists("beanInformation", "construct Factory", beanInformation);
		validationHelper.ensureExists("factoryLookupStrategy", "construct Factory", factoryLookupStrategy);
		this.beanInformation = beanInformation;
		beanPropertyValuesFactory = new BeanPropertyValuesFactory(beanInformation, factoryLookupStrategy);
	}

	/**
	 * Create a new instance of the Bean described in the provided BeanInformation. Every instance created will have the
	 * same field values.
	 * 
	 * @throws BeanCreationException
	 *             If an error occurs when creating an instance of the Bean.
	 */
	public Object create() throws BeanCreationException {
		log.debug("create: entering.");
		if (propertyValues == null) {
			log.debug("create: initialise property values cache.");
			propertyValues = beanPropertyValuesFactory.create();
		}
		BasicNewObjectInstanceFactory beanFactory = new BasicNewObjectInstanceFactory(beanInformation.getBeanClass());
		Object result = beanFactory.create();
		log.debug("create: created [" + result + "].");
		beanPopulator.populate(result, beanInformation, propertyValues);
		log.debug("create: populated [" + result + "].");
		return result;
	}
}