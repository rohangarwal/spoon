/* 
 * Spoon - http://spoon.gforge.inria.fr/
 * Copyright (C) 2006 INRIA Futurs <renaud.pawlak@inria.fr>
 * 
 * This software is governed by the CeCILL-C License under French law and
 * abiding by the rules of distribution of free software. You can use, modify 
 * and/or redistribute the software under the terms of the CeCILL-C license as 
 * circulated by CEA, CNRS and INRIA at http://www.cecill.info. 
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the CeCILL-C License for more details.
 *  
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C license and that you accept its terms.
 */

package spoon.support.reflect.code;

import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCatch;
import spoon.reflect.code.CtCatchVariable;
import spoon.reflect.visitor.CtVisitor;

public class CtCatchImpl extends CtCodeElementImpl implements CtCatch {
	private static final long serialVersionUID = 1L;

	CtBlock<?> body;

	CtCatchVariable<? extends Throwable> parameter;

	public void accept(CtVisitor visitor) {
		visitor.visitCtCatch(this);
	}

	public CtBlock<?> getBody() {
		return body;
	}

	public CtCatchVariable<? extends Throwable> getParameter() {
		return parameter;
	}

	public void setBody(CtBlock<?> body) {
		body.setParent(this);
		this.body = body;
	}

	public void setParameter(CtCatchVariable<? extends Throwable> parameter) {
		parameter.setParent(this);
		this.parameter = parameter;
	}

}
