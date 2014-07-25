/**
 * Copyright 2014 Linagora, Université Joseph Fourier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.roboconf.maven;

/**
 * @author Vincent Zurczak - Linagora
 */
public interface MavenPluginConstants {

	/**
	 * The src/main/model directory.
	 */
	String SOURCE_MODEL_DIRECTORY = "src/main/model";

	/**
	 * The Maven output of the model directory.
	 */
	String TARGET_MODEL_DIRECTORY = "target/roboconf-model";

	/**
	 * The relative path of the validation results.
	 */
	String VALIDATION_RESULT_PATH = "target/roboconf/roboconf-validation.txt";
}
