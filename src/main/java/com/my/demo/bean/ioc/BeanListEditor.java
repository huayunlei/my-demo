package com.my.demo.bean.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

/**
 * @version:
 */
public class BeanListEditor extends CustomCollectionEditor {

    private Logger log = LoggerFactory.getLogger(BeanListEditor.class);

    public BeanListEditor() {
        super(BeanMapableArrayList.class,true);
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);

        Object collectionValue = super.getValue();

        if (collectionValue != null && InitializingBean.class.isAssignableFrom(collectionValue.getClass())) {
            try {
                ((InitializingBean) collectionValue).afterPropertiesSet();
            } catch (Exception e) {
                log.error("bean list editor afterPropertiesSet error",e);
            }
        }
    }
}