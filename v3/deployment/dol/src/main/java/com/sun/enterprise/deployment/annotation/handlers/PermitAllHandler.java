/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.sun.enterprise.deployment.annotation.handlers;

import com.sun.enterprise.deployment.EjbDescriptor;
import com.sun.enterprise.deployment.MethodDescriptor;
import com.sun.enterprise.deployment.MethodPermission;
import com.sun.enterprise.deployment.WebComponentDescriptor;
import com.sun.enterprise.deployment.web.SecurityConstraint;
import org.jvnet.hk2.annotations.Service;

import javax.annotation.security.PermitAll;
import java.lang.annotation.Annotation;

/**
 * This handler is responsible for handling the
 * javax.annotation.security.PermitAll.
 *
 * @author Shing Wai Chan
 */
@Service
public class PermitAllHandler extends AbstractAuthAnnotationHandler {
    
    public PermitAllHandler() {
    }
    
    /**
     * @return the annoation type this annotation handler is handling
     */
    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return PermitAll.class;
    }    

    @Override
    protected void processEjbMethodSecurity(Annotation authAnnoation,
            MethodDescriptor md, EjbDescriptor ejbDesc) {

        ejbDesc.addPermissionedMethod(
                MethodPermission.getUncheckedMethodPermission(), md);
    }

    @Override
    protected void processSecurityConstraint(Annotation authAnnotation,
            SecurityConstraint securityConstraint, WebComponentDescriptor webCompDesc) { 

        securityConstraint.setAuthorizationConstraint(null);
    }
    /**
     * @return an array of annotation types this annotation handler would
     * require to be processed (if present) before it processes it's own
     * annotation type.
     */
    @Override
    public Class<? extends Annotation>[] getTypeDependencies() {
        return getEjbAndWebAnnotationTypes();
    }
}
