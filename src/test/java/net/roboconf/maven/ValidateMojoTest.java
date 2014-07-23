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

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Vincent Zurczak - Linagora
 */
public class ValidateMojoTest {

	@Rule
	public MojoRule rule = new MojoRule();

	@Rule
	public TestResources resources = new TestResources();



	@Test( expected = MojoFailureException.class )
	public void testInvalidProject() throws Exception {

		File baseDir = this.resources.getBasedir( "project--invalid" );
		Assert.assertNotNull( baseDir );
		Assert.assertTrue( baseDir.exists());
		Assert.assertTrue( baseDir.isDirectory());

		this.rule.executeMojo( baseDir, "validate" );
	}


	@Test( expected = MojoFailureException.class )
	public void testValidProjectButInvalidApp() throws Exception {

		File baseDir = this.resources.getBasedir( "project--invalid-app" );
		Assert.assertNotNull( baseDir );
		Assert.assertTrue( baseDir.exists());
		Assert.assertTrue( baseDir.isDirectory());

		ValidateMojo mojo = (ValidateMojo) this.rule.lookupConfiguredMojo( baseDir, "validate" );
		Assert.assertNotNull( mojo );
		Assert.assertNotNull( this.rule.getVariableValueFromObject( mojo, "project" ));

		mojo.execute();
	}
}
