If you are interested in developing and building the project please follow the following instruction.

### Version control

To check out sources of the project, please execute from your command line:

```sh
git clone https://github.com/cibseven-community-hub/cibseven-bpm-data.git
cd cibseven-bpm-data
```

We are using gitflow in our git SCM. That means that you should start from `develop` branch, create a `feature/<name>` out of it and once it
is completed create a pull request containing it. Please squash your commits before submitting and use semantic commit messages, if
possible.

### Project Build

Perform the following steps to get a development setup up and running.

```sh
./mvnw clean install
```

### Integration Tests

The default build command above will NOT run `failsafe` Maven plugin executing the integration tests
(These are JUnit tests with class names ending with ITest). In order to run integration tests, please call from your command line:

```sh
./mvnw -Pitest
```

### Project build modes and profiles

#### Examples

If you want to skip the build of examples, please specify the `-DskipExamples` switch in your command line.

#### Documentation

We are using MkDocs for generation of a static site documentation and rely on markdown as much as possible.
To install it, please run once:

```sh
python3 -m pip install -r ./docs/requirements.txt
```

!!! note

    If you want to develop your docs in 'live' mode, run `mkdocs serve` and access
    the http://localhost:8000/ from your browser.

For creation of documentation locally, please run:

```sh
mkdocs build
```

#### Generation of JavaDoc and Sources

By default, the sources and javadoc API documentation are not generated from the source code. To enable this:

```sh
./mvnw clean install -Prelease -Dgpg.skip=true
```

#### Starting example application

To start applications, either use your IDE and create run configuration for the classes:

* `org.cibseven.community.bpm.data.example.CamundaBpmDataProcessApplication`
* `org.cibseven.community.bpm.data.example.kotlin.CamundaBpmDataKotlinExampleApplication`

Alternatively, you can run them from the command line:

```sh
./mvn spring-boot:run -f example/example-java
./mvn spring-boot:run -f example/example-kotlin
```

### Continuous Integration

GitHub Actions is building all branches on commit hook. In addition, a special action is building releases from master branch.

### Release Management

Release management has been set up for use of Sonatype Nexus (= Maven Central).

#### What modules get deployed to repository

Currently, the following modules are released to OSS Maven Central:

* cibseven-bpm-data-parent
* cibseven-bpm-data

#### Trigger new release

!!! note

    This operation requires special permissions.

We use gitflow for development (see [A successful git branching model](http://nvie.com/posts/a-successful-git-branching-model/)
for more details). You could use gitflow with native git commands, but then you would have to change the versions in the poms manually.
Therefor we use the
[mvn gitflow plugin](https://github.com/aleksandr-m/gitflow-maven-plugin/), which handles this and other things nicely.

You can build a release with:

```sh
./mvnw gitflow:release-start
./mvnw gitflow:release-finish
```

This will update the versions in the `pom.xml` s accordingly and push the release tag to the `master` branch and update the `develop` branch
for the new development version.

#### Create feature for development

You can create a feature branch for development using:

```sh
./mvnw gitflow:feature-start
```

!!!note

    This operation requires special permissions.

After the feature is complete, create a PR. To merge the PR into develop use the command:

```sh
./mvnw gitflow:feature-finish
```

#### Trigger a deploy

!!! note

    This operation requires special permissions.

Currently, CI allows for deployment of artifacts to Maven Central and is executed via Github Actions. This means, that a push to `master`
branch will start the corresponding build job, and if successful the artifacts will get into `Staging Repositories` of OSS Sonatype without
manual intervention. The repository gets automatically closed and released on successful upload.

If you still want to execute the deployment from your local machine, you need to execute the following command on the `master` branch:

```sh
./mvnw clean deploy -B -DskipTests -DskipExamples -Prelease
```
