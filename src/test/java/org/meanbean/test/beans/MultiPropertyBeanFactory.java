package org.meanbean.test.beans;

import org.meanbean.lang.EquivalentFactory;

/**
 * Factory that creates logically equivalent MultiPropertyBean instances. This should only be used for testing.
 * 
 * @author Graham Williamson
 */
public class MultiPropertyBeanFactory implements EquivalentFactory<MultiPropertyBean> {

	public MultiPropertyBean create() {
		MultiPropertyBean bean = new MultiPropertyBean();
		bean.setFirstName("FIRST_NAME");
		bean.setLastName("LAST_NAME");
		return bean;
	}
}