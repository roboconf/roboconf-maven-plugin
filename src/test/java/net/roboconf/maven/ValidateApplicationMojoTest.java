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

import java.io.File;

import net.roboconf.core.utils.Utils;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vincent Zurczak - Linagora
 */
public class ValidateApplicationMojoTest extends ValidateProjectMojoTest {

	@Test( expected = MojoFailureException.class )
	public void testValidProjectButInvalidApp() throws Exception {

		findAndExecuteMojo( "project--invalid-app", true );
	}


	@Test
	public void testValidAppProjectWithWarnings() throws Exception {

		findAndExecuteMojo( "project--valid-with-warnings", true );
		// No exception thrown
	}


	@Test( expected = MojoExecutionException.class )
	public void testValidAppButNotInTarget() throws Exception {

		ValidateApplicationMojo mojo = (ValidateApplicationMojo) super.findMojo( "project--valid", "validate-application" );
		mojo.execute();
	}


	@Test
	public void testValidAppProject() throws Exception {

		findAndExecuteMojo( "project--valid", false );
		// No exception thrown
	}


	private void findAndExecuteMojo( String projectName, boolean expectTextFile ) throws Exception {

		ValidateApplicationMojo mojo = (ValidateApplicationMojo) super.findMojo( projectName, "validate-application" );
		MavenProject project = (MavenProject) this.rule.getVariableValueFromObject( mojo, "project" );
		Assert.assertNotNull( project );

		// Copy the resources
		Utils.copyDirectory(
				new File( project.getBasedir(), MavenPluginConstants.SOURCE_MODEL_DIRECTORY ),
				new File( project.getBuild().getOutputDirectory()));

		// Execute the mojo
		mojo.execute();

		// Should we have a result file?
		File resultsFile = new File( project.getBasedir(), MavenPluginConstants.VALIDATION_RESULT_PATH );
		Assert.assertEquals( expectTextFile, resultsFile.exists());
	}
}
