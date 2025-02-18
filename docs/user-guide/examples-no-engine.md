## Use case

In seldom cases you may want to use the `cibseven-bpm-data` library without CIB seven BPM Engine. The most frequent
case for this is if you provide a third-party library that is used with or without the engine. In particular, there is no way to use 
functionality relying on the CIB seven engine without it, but there is some functionality working on simple `Map<String, Object>` or `VariableMap` 
(from a separate small library `org.cibseven.commons:cibseven-commons-typed-values`) which is still usable __WITHOUT__ having the entire CIB seven 
BPM Engine on the class path.

## Limitations

Due to limitations provided by the JVM, usage of `CamundaBpmData` convenience methods is __NOT POSSIBLE__ if the parts of the Java CIB seven API is not on the classpath (`RuntimeService`, `TaskService`, `LockedExternalTask`, ...).

## Default solution

The default solution for this problem would be to put the `org.cibseven.bpm:cibseven-engine` CIB seven BPM Engine JAR on the classpath, but don't initialize the CIB seven BPM Engine.

## Alternative solution

In order to make the JAR footprint lighter, we created a special artifact, which provides the CIB seven BPM API only (API classes only but no implementation). This artifact includes __ORIGINAL__ CIB seven BPM classes of the API.

In the same time, we started a discussion with CIB seven Team to provide a dedicated API JAR of the engine. We will produce the CIB seven BPM API for the upcoming releases. Instead of using the original engine JAR, you might want to put the following artifact on your classpath:

``` xml
<dependency>
      <groupId>org.cibseven.community.cibseven-api</groupId>
      <artifactId>cibseven-bpm-engine-api</artifactId>
      <version>${cibseven.version}</version>
</dependency>
```

For more information about the CIB seven BPM API, please check its GitHub project page: 
[https://github.com/cibseven-community-hub/cibseven-bpm-api/](https://github.com/cibseven-community-hub/cibseven-bpm-api/)
