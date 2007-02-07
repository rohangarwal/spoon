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

package spoon.reflect.factory;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import spoon.reflect.Factory;
import spoon.reflect.declaration.CompilationUnit;

/**
 * A factory to create some evaluation utilities on the Spoon metamodel.
 */
public class CompilationUnitFactory extends SubFactory {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates the evaluation factory.
	 */
	public CompilationUnitFactory(Factory factory) {
		super(factory);
	}

	Map<String, CompilationUnit> compilationUnits = new TreeMap<String, CompilationUnit>();

	/**
	 * Creates a compilation unit with no associated files.
	 */
	public CompilationUnit create() {
		CompilationUnit cu = factory.Core().createCompilationUnit();
		return cu;
	}

	/**
	 * Creates or gets a compilation unit for a given file path.
	 */
	public CompilationUnit create(String filePath) {
		CompilationUnit cu = compilationUnits.get(filePath);
		if (cu == null) {
			cu = factory.Core().createCompilationUnit();
			cu.setFile(new File(filePath));
			compilationUnits.put(filePath, cu);
		}
		return cu;
	}
}