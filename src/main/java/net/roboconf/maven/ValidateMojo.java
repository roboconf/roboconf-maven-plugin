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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import net.roboconf.core.ErrorCode.ErrorCategory;
import net.roboconf.core.RoboconfError;
import net.roboconf.core.model.helpers.RoboconfErrorHelpers;
import net.roboconf.core.model.io.RuntimeModelIo;
import net.roboconf.core.model.io.RuntimeModelIo.ApplicationLoadResult;
import net.roboconf.core.utils.Utils;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * The <strong>validate</strong> mojo.
 * @author Vincent Zurczak - Linagora
 */
@Mojo( name="validate", defaultPhase = LifecyclePhase.VALIDATE )
public class ValidateMojo extends AbstractMojo {

	@Parameter( defaultValue = "${project}", readonly = true )
	private MavenProject project;


	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		// Check the existence of the root directory
		File appDirectory = new File( this.project.getBasedir(), "src/main/model" );
		if( ! appDirectory.exists())
			throw new MojoFailureException( "The src/main/model directory does not exist." );

		if( ! appDirectory.isDirectory())
			throw new MojoFailureException( "The src/main/model path does not point to a directory." );

		// Validate the application
		ApplicationLoadResult alr = RuntimeModelIo.loadApplication( appDirectory );

		// Analyze the result
		try {
			if( alr.getLoadErrors().size() > 0 ) {
				reportErrors( alr );
				if( RoboconfErrorHelpers.containsCriticalErrors( alr.getLoadErrors()))
					throw new MojoFailureException( "Errors were found in the application." );
			}

		} catch( IOException e ) {
			throw new MojoExecutionException( "A problem occurred during the validation.", e );
		}
	}


	private void reportErrors( ApplicationLoadResult alr ) throws IOException {

		// Generate the report
		StringBuilder sb = new StringBuilder();
		int length = -1;
		for( ErrorCategory cat : ErrorCategory.values())
			length = Math.max( length, cat.toString().length());

		for( RoboconfError error : alr.getLoadErrors()) {
			sb.append( "[ " );
			sb.append( String.format( "%" + length + "s", error.getErrorCode().getCategory().toString()));
			sb.append( " ]  " );
			sb.append( error.getErrorCode().getMsg());
			if( ! Utils.isEmptyOrWhitespaces( error.getDetails()))
				sb.append( " " + error.getDetails());
		}

		// TODO: add line and source file name

		// Write the report.
		// Reporting only makes sense when there is an error or a warning.
		File targetFile = new File( this.project.getBasedir(), "target/roboconf/roboconf-validation.txt" );
		if( ! targetFile.getParentFile().exists()
				&& ! targetFile.getParentFile().mkdirs())
			throw new IOException( "A directory could not be created: " + targetFile.getParentFile());

		InputStream in = new ByteArrayInputStream( sb.toString().getBytes( "UTF-8" ));
		Utils.copyStream( in, targetFile );
	}
}
