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

package spoon.support.reflect.declaration;

import java.util.Set;
import java.util.TreeSet;

import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtPackageReference;
import spoon.reflect.visitor.CtVisitor;

/**
 * The implementation for {@link spoon.reflect.declaration.CtPackage}.
 * 
 * @author Renaud Pawlak
 */
public class CtPackageImpl extends CtNamedElementImpl implements CtPackage {
	private static final long serialVersionUID = 1L;

	private Set<CtPackage> packs = new TreeSet<CtPackage>();

	private Set<CtType<?>> types = new TreeSet<CtType<?>>();

	public static CtPackage ROOT_PACKAGE = new CtPackageImpl() {
		@Override
		public String getSimpleName() {
			return "";
		};
		
		@Override
		public String getQualifiedName() {
			return "";
		};

		@Override
		public CtElement getParent() {
			return null;
		};
	};
	
	public CtPackageImpl() {
		super();
		setParent(ROOT_PACKAGE);
	}

	public boolean addPackage(CtPackage pack) {
		pack.setParent(this);
		return packs.add(pack);
	}

	public boolean removePackage(CtPackage pack) {
		return packs.remove(pack);
	}

	public void accept(CtVisitor v) {
		v.visitCtPackage(this);
	}

	public CtPackage getDeclaringPackage() {
		if (parent == null) {
			setParent(ROOT_PACKAGE);
		}
		return getParent(CtPackage.class);
	}
	
	public CtPackage getPackage(String name) {
		for (CtPackage p : packs) {
			if (p.getSimpleName().equals(name))
				return p;
		}
		return null;
	}

	public Set<CtPackage> getPackages() {
		return packs;
	}

	public String getQualifiedName() {
		if (getDeclaringPackage() == null || getDeclaringPackage() == ROOT_PACKAGE)
			return getSimpleName();
		return getDeclaringPackage().getQualifiedName() + "." + getSimpleName();
	}

	@SuppressWarnings("unchecked")
	public <T extends CtType<?>> T getType(String simpleName) {
		for (CtType<?> t : types) {
			if (t.getSimpleName().equals(simpleName)) {
				return (T) t;
			}
		}
		return null;
	}

	public Set<CtType<?>> getTypes() {
		return types;
	}

	public void setPackages(Set<CtPackage> packs) {
		this.packs.clear();
		for (CtPackage p : packs) {
			addPackage(p);
		}
	}

	public void setTypes(Set<CtType<?>> types) {
		this.types.clear();
		for (CtType t : types) {
			addType(t);
		}
	}

	@Override
	public CtPackageReference getReference() {
		return getFactory().Package().createReference(this);
	}

	@Override
	public void addType(CtType<?> type) {
		type.setParent(this);
		types.add(type);
	}

	@Override
	public void removeType(CtType<?> type) {
		types.remove(type);
	}

	@Override
	public SourcePosition getPosition()
	{
		/*
		 * The super.getPosition() method returns the own position
		 * or if it's null the position of the parent element,
		 * but that isn't possible for packages.
		 * A package should return the position of the package-info file
		 * if it exists. The parent of a package is another package which
		 * needs to have an own package-info file.
		 */
		return this.position;
	}
	
	@Override
	public String toString() {
		return getQualifiedName();
	}
	
}
