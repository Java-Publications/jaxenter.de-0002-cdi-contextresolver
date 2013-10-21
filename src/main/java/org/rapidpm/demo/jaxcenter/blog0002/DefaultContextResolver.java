/*
 * Copyright [2013] [www.rapidpm.org / Sven Ruppert (sven.ruppert@rapidpm.org)]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.rapidpm.demo.jaxcenter.blog0002;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

/**
 * User: Sven Ruppert
 * Date: 12.08.13
 * Time: 10:40
 */
@Blog0002
public class DefaultContextResolver implements ContextResolver {

    @Inject BeanManager beanManager;


    public Set<ContextResolver> gettAllContextResolver(){
        final Set<ContextResolver> resultSet = new HashSet<>();
        final Set<Bean<?>> allBeans = beanManager.getBeans(ContextResolver.class, new AnnotationLiteral<Any>() {});
        for (final Bean<?> bean : allBeans) {
            final Set<Type> types = bean.getTypes();
            for (final Type type : types) {
                if(type.equals(ContextResolver.class)){
                    final ContextResolver t = ((Bean<ContextResolver>) bean).create(beanManager.createCreationalContext((Bean<ContextResolver>) bean));
                    resultSet.add(t);
                } else{
                    //
                }
            }
        }
      return resultSet;
    }


    @Override
    public AnnotationLiteral resolveContext(Class<?> targetClass) {

        final Set<ContextResolver> contextResolvers = gettAllContextResolver();

        for (final ContextResolver contextResolver : contextResolvers) {
            final AnnotationLiteral annotationLiteral = contextResolver.resolveContext(targetClass);
            if(annotationLiteral == null){

            } else{
                return annotationLiteral;
            }
        }



        return new AnnotationLiteral<Blog0002>() {
        };  //as Default Implementation
    }
}
