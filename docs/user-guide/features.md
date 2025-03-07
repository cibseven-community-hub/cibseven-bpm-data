* Process Variables
    - The library provides a way to construct a generic adapter for every process variable.
    - The adapter contains variable type.
    - The adapter can be applied in any context (`RuntimeService`, `TaskService`, `CaseService`, `DelegateExecution`, `DelegateTask`, `DelegateCaseExecution`, `VariableMap`, `LockedExternalTask`).
    - The adapter offers methods to read, write, update and remove variable values.
    - The adapter works for all types supported by CIB seven BPM. This includes primitive types, object and container types ( `List<T>`, `Set<T>`, `Map<K , V>` ).
    - The adapter supports global / local variables.
    - The adapter allows a default value or null in case a variable is not set.
    - The adapter support transient variables.
    - Fluent API helper are available in order to set, remove or update multiple variables in the same context (`VariableMapBuilder`, `VariableReader` and `GlobalVariableWriter`).

* Process Variable Guards
    - Generic support for `VariableGuard` for evaluation of a list of `VariableCondition`s
    - Condition to check if variable exists.
    - Condition to check if variable doesn't exist
    - Condition to check if variable has a predefined value.
    - Condition to check if variable has one of predefined values.
    - Condition to check if variable matches condition specified by a custom function.
    - Condition to check if variable matches provided regular expression.
    - Condition to check if variable is a valid email address.
    - Condition to check if variable is a valid UUID.
    - `DefaultGuardTaskListener` to construct variable conditions guards easily.
    - `DefaultGuardExecutionListener` to construct variable conditions guards easily.

* Anti-Corruption-Layer
    - Generic support for `AntiCorruptionLayer` for protection and influence of variable modification in signalling and message correlation.
    - Helper methods for the client to wrap variables in a transient carrier.
    - Execution listener to handle `VariableGuard`-based conditions and `VariableMapTransformer`-based modifications.
    - Task listener to handle `VariableGuard`-based conditions and `VariableMapTransformer`-based modifications.
    - Factory methods to create `AntiCorruptionLayer` with a `VariableGuard` (see `CamundaBpmDataACL`)
    - Factory methods to create `AntiCorruptionLayer` without a `VariableGuard` (see `CamundaBpmDataMapper`)
