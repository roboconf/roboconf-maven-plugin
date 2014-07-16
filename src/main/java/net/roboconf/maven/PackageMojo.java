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

import org.apache.maven.archiver.MavenArchiveConfiguration;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.archiver.jar.JarArchiver;

/**
 * The <strong>package</strong> mojo.
 * @author Vincent Zurczak - Linagora
 */
@Mojo( name="package", defaultPhase = LifecyclePhase.PACKAGE )
public class PackageMojo extends AbstractMojo {

	@Parameter( defaultValue = "${project}", readonly = true )
	private MavenProject project;

	@Parameter( required = true )
	private JarArchiver jarArchiver;

	private final MavenArchiveConfiguration archive = new MavenArchiveConfiguration();


	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

//	    String archiveName = this.project.getBuild().getFinalName() + ".zip";
//	    File archiveFile = new File(buildDirectory, archiveName);
//	    // Configure archiver
//	    MavenArchiver archiver = new MavenArchiver();
//	    archiver.setArchiver(this.jarArchiver);
//	    archiver.setOutputFile( archiveFile );
//
//	    try {
//	        // archive classes
//	        archiver.getArchiver().addDirectory(classesDirectory, "classes/");
//	        // archive libs
//	        archiver.getArchiver().addDirectory(libDirectory, "lib/");
//	        // create archive
//	        archiver.createArchive(this.project, this.archive);
//	        // set archive as artifact
//	        this.project.getArtifact().setFile(custFile);
//
//	    } catch (ArchiverException e) {
//	        throw new MojoExecutionException("Exception while packaging", e);
//
//	    } catch (ManifestException e) {
//	        throw new MojoExecutionException("Exception while packaging", e);
//
//	    } catch (IOException e) {
//	        throw new MojoExecutionException("Exception while packaging", e);
//
//	    } catch (DependencyResolutionRequiredException e) {
//	        throw new MojoExecutionException("Exception while packaging", e);
//	    }
	}
}
