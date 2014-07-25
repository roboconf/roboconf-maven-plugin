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

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * The mojo in charge of configuring the Maven project.
 * @author Vincent Zurczak - Linagora
 */
@Mojo( name="initialize", defaultPhase = LifecyclePhase.INITIALIZE )
public class InitializeMojo extends AbstractMojo {

	@Parameter( defaultValue = "${project}", readonly = true )
	private MavenProject project;


	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		File appDirectory = new File( this.project.getBasedir(), MavenPluginConstants.SOURCE_MODEL_DIRECTORY );
		if( appDirectory.exists()) {
			getLog().info( "Adding " + MavenPluginConstants.SOURCE_MODEL_DIRECTORY + " to the list of filtered resources." );
			Resource res = new Resource();
			res.setDirectory( appDirectory.getAbsolutePath());
			res.setFiltering( true );
			this.project.addResource( res );
		}

		if( Utils.isEmptyOrWhitespaces( this.project.getBuild().getOutputDirectory())
				|| this.project.getBuild().getOutputDirectory().toLowerCase().endsWith( "/target/classes" )) {

			getLog().info( "Changing default output directory to " + MavenPluginConstants.TARGET_MODEL_DIRECTORY + "." );
			File outputDirectory = new File( this.project.getBasedir(), MavenPluginConstants.TARGET_MODEL_DIRECTORY );
			this.project.getBuild().setOutputDirectory( outputDirectory.getAbsolutePath());
		}
	}


	/**
	 * @param project the project to set
	 */
	public void setProject( MavenProject project ) {
		this.project = project;
	}
}