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

package org.rapidpm.demo.jaxcenter.blog0002.demo.demologic;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.rapidpm.demo.jaxcenter.blog0002.ContextResolver;
import org.rapidpm.demo.jaxcenter.blog0002.demo.demologic.context_a.DemoLogicContextA;
import org.rapidpm.demo.jaxcenter.blog0002.demo.demologic.context_b.DemoLogicContextB;

/**
 * User: Sven Ruppert
 * Date: 16.10.13
 * Time: 17:22
 */
public class DemoContextResolver implements ContextResolver {

    @Inject DemoContext demoContext;

    @Override public AnnotationLiteral resolveContext(Class<?> targetClass) {

        if(demoContext.getContextInfo()){
            return new AnnotationLiteral<DemoLogicContextA>() {};
        } else{
            return new AnnotationLiteral<DemoLogicContextB>() {};
        }
    }
}
