## Why should I use this?

If you are a software engineer and run process automation projects in your company or on behalf of the customer
based on CIB seven Process Engine, you probably are familiar with process variables. CIB seven offers an API to access
them and thereby manipulate the state of the process execution - one of the core features during process automation.

Unfortunately, as a user of the CIB seven API, you have to exactly know the variable type (so the Java class behind it).
For example, if you store a String in a variable `"orderId"` you must extract it as a String in every piece of code.
Since there is no code connection between the different code parts, but the BPMN process model orchestrates
these snippets to a single process execution, it makes refactoring and testing of process automation projects
error-prone and challenging.

This library helps you to overcome these difficulties and make access, manipulation and testing process variables really
easy and convenient. We leverage the CIB seven API and offer you not only a better API but also some 
[additional features](./user-guide/features.md).

## How to start?

A good starting point is the [Quick Start](./quick-start.md). For more detailed documentation, please have a look at
[User Guide](./user-guide/motivation.md).

## Get in touch

If you are missing a feature, have a question regarding usage or deployment, you should definitely get in touch
with the us.
