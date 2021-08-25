package com.my.demo.bean.ioc;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @version:
 */
public class BeanMapableArrayList<K, E extends BeanMatcher<K>> extends ArrayList<E> implements BeanList<K, E>, InitializingBean {
    private Map<K, E> map = new HashMap<>();

    private Comparator<E> comparator = AnnotationAwareOrderComparator.INSTANCE::compare;

    private List<BeanCategory> beanCategories = new ArrayList<>();

    public BeanMapableArrayList() {
        super();
    }

    public BeanMapableArrayList(int size) {
        super(size);
    }

    @Override
    public E find(K factor) {
        return optional(factor).orElse(null);
    }

    @Override
    public Optional<E> optional(K factor) {
        return this.stream().filter(beanMatcher -> beanMatcher.match(factor)).findFirst();
    }

    @Override
    public List<E> list(K factor) {
        return this.stream().filter(b -> b.match(factor)).collect(Collectors.toList());
    }

    @Override
    public List<E> group(Class<?> group) {
        return Optional.ofNullable(beanCategories)
                .orElse(new ArrayList<>())
                .stream()
                .filter(beanCategory -> beanCategory.isCategory(group))
                .map(BeanCategory::getBean)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<E> groupOptional(Class<?> group) {
        return Optional.ofNullable(group(group))
                .orElse(new ArrayList<>())
                .stream()
                .findFirst();
    }

    @Override
    public E groupOne(Class<?> group) {
        return groupOptional(group).orElse(null);
    }

    @Override
    public E fetchOne(K key) {
        return map.get(key);
    }

    @Override
    public Optional<E> fetchOptional(K key) {
        return Optional.ofNullable(map.get(key));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            if (!CollectionUtils.isEmpty(this)) {
                this.stream()
                        .sorted(comparator)
                        .forEach(bean -> {
                            Category category = AopUtils.getTargetClass(bean).getAnnotation(Category.class);
                            if (category != null) {
                                BeanCategory beanCategory = new BeanCategory();
                                beanCategory.setCategories(Arrays.asList(category.value()));
                                beanCategory.setBean(bean);
                                beanCategories.add(beanCategory);
                            }

                            if (MapableBeanMatcher.class.isAssignableFrom(AopUtils.getTargetClass(bean))) {
                                MapableBeanMatcher<K> mapableBeanMatcher = (MapableBeanMatcher<K>) bean;
                                map.put(mapableBeanMatcher.mapKey(), bean);
                            }
                        });
            }
        } catch (Exception e) {
            //do nothing
        }
    }

    private class BeanCategory {
        private List<Class<?>> categories;
        private E bean;

        public void setCategories(List<Class<?>> list) {
            this.categories = list;
        }

        public E getBean() {
            return this.bean;
        }

        public void setBean(E bean) {
            this.bean = bean;
        }

        public boolean isCategory(Class<?> group) {
            return Optional.ofNullable(categories)
                    .orElse(new ArrayList<>())
                    .stream()
                    .anyMatch(category -> category.isAssignableFrom(group));
        }
    }
}
