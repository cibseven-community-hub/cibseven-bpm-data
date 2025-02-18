# Package org.cibseven.community.bpm.data

Top-level package of the library.

check [org.cibseven.community.bpm.data.CamundaBpmData] as entry point for variable factory definition in Java.
check [org.cibseven.community.bpm.data.CamundaBpmDataKotlin] as entry point for variable factory definition in Kotlin.
@since 0.0.2

# Package org.cibseven.community.bpm.data.acl

Package providing resources for building of Anti-Corruption-Layer (ACL).
@since 1.0.0
@see [org.cibseven.community.bpm.data.acl.CamundaBpmDataACL] for more details.

# Package org.cibseven.community.bpm.data.adapter

Read and Write adapters for different factories to handle different contexts.
@see [org.cibseven.community.bpm.data.adapter.ReadAdapter]
@see [org.cibseven.community.bpm.data.adapter.WriteAdapter]
@since 0.0.2

# Package org.cibseven.community.bpm.data.adapter.basic

Read/Write adapters for basic variable factory.

@see [org.cibseven.community.bpm.data.factory.BasicVariableFactory] for more details
@since 0.0.2


# Package org.cibseven.community.bpm.data.adapter.list

Read/Write adapters for list variable factory.

@see [org.cibseven.community.bpm.data.factory.ListVariableFactory] for more details
@since 0.0.2

# Package org.cibseven.community.bpm.data.adapter.map

Read/Write adapters for map variable factory.

@see [org.cibseven.community.bpm.data.factory.MapVariableFactory] for more details
@since 0.0.2

# Package org.cibseven.community.bpm.data.adapter.set

Read/Write adapters for set variable factory.
@see [org.cibseven.community.bpm.data.factory.SetVariableFactory] for more details
@since 0.0.2

# Package org.cibseven.community.bpm.data.builder

Writers are used to create multiple variables inside a variable map.
@see [org.cibseven.community.bpm.data.CamundaBpmData#builder()] methods.
@since 0.0.5

# Package org.cibseven.community.bpm.data.factory

Process Variable Factories are used to define process variables.
@see org.cibseven.community.bpm.data.factory.VariableFactory as the main starting point.
@since 0.0.2

# Package org.cibseven.community.bpm.data.guard

Package providing resources for building Variable Guards.
@since 0.0.4
check [org.cibseven.community.bpm.data.guard.CamundaBpmDataGuards] for more details.

# Package org.cibseven.community.bpm.data.reader

Readers are used to interact with multiple variables in the same context.
@since 1.0.0
@see [org.cibseven.community.bpm.data.CamundaBpmData#reader()] methods.

# Package org.cibseven.community.bpm.data.writer

Writers are used to interact with multiple variables in the same context.
@since 0.0.5
@see [org.cibseven.community.bpm.data.CamundaBpmData#writer()] methods.
