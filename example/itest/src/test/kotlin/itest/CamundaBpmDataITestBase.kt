package org.cibseven.community.bpm.data.itest

import com.fasterxml.jackson.annotation.JsonIgnore
import com.tngtech.jgiven.base.ScenarioTestBase
import com.tngtech.jgiven.integration.spring.EnableJGiven
import com.tngtech.jgiven.integration.spring.junit5.SpringScenarioTest
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.booleanVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.longVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.shortVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.customVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.dateVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.doubleVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.intVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.listOfMapsVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.listVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.mapVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.setVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.stringVariable
import org.cibseven.community.bpm.data.factory.VariableFactory
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.BOOLEAN
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.BOOLEAN_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.COMPLEX
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.COMPLEX_LIST
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.COMPLEX_LIST_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.COMPLEX_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.COMPLEX_MAP
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.COMPLEX_MAP_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.COMPLEX_SET
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.COMPLEX_SET_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.DATE
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.DATE_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.DOUBLE
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.DOUBLE_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.INT
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.INT_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.LIST_MAP_STRING_OBJECT
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.LIST_MAP_STRING_OBJECT_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.LIST_STRING
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.LIST_STRING_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.LONG
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.LONG_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.MAP_STRING_DATE_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.MAP_STRING_LONG
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.SET_STRING
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.SET_STRING_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.SHORT
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.SHORT_LOCAL
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.STRING
import org.cibseven.community.bpm.data.itest.CamundaBpmDataITestBase.Companion.Values.STRING_LOCAL
import org.cibseven.bpm.engine.delegate.JavaDelegate
import org.cibseven.bpm.engine.delegate.TaskListener
import org.cibseven.bpm.engine.delegate.VariableScope
import org.cibseven.bpm.engine.variable.VariableMap
import org.cibseven.bpm.engine.variable.Variables.createVariables
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * Alias for the when
 */
fun <G, W, T> ScenarioTestBase<G, W, T>.whenever(): W = `when`()

/**
 * Base for ITests.
 */
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [CamundaBpmDataITestBase.TestApplication::class])
@ActiveProfiles("itest")
abstract class CamundaBpmDataITestBase : SpringScenarioTest<ActionStage, ActionStage, AssertStage>() {

  companion object {

    val STRING_VAR: VariableFactory<String> = stringVariable("String Variable").nonNull
    val DATE_VAR: VariableFactory<Date> = dateVariable("Date Variable").nonNull
    val SHORT_VAR: VariableFactory<Short> = shortVariable("Short Variable").nonNull
    val INT_VAR: VariableFactory<Int> = intVariable("Int Variable").nonNull
    val LONG_VAR: VariableFactory<Long> = longVariable("Long Variable").nonNull
    val DOUBLE_VAR: VariableFactory<Double> = doubleVariable("Double Variable").nonNull
    val BOOLEAN_VAR: VariableFactory<Boolean> = booleanVariable("Boolean Variable").nonNull
    val COMPLEX_VAR: VariableFactory<ComplexDataStructure> = customVariable("Complex Variable")
    val LIST_STRING_VAR: VariableFactory<List<String>> = listVariable("List Of String Variable")
    val SET_STRING_VAR: VariableFactory<Set<String>> = setVariable("Set Of String Variable")
    val MAP_STRING_LONG_VAR: VariableFactory<Map<String, String>> = mapVariable("Map Of String to String Variable")
    val LIST_MAP_STRING_OBJECT_VAR: VariableFactory<List<Map<String, Any>>> = listOfMapsVariable("List Of Maps Of String to Any")
    val COMPLEX_SET_VAR: VariableFactory<Set<ComplexDataStructure>> = setVariable("Complex Set")
    val COMPLEX_LIST_VAR: VariableFactory<List<ComplexDataStructure>> = listVariable("Complex List")
    val COMPLEX_MAP_VAR: VariableFactory<Map<String, ComplexDataStructure>> = mapVariable("Complex Map")

    object Values {
      val now = Date.from(Instant.now())
      val yesterday = Date.from(Instant.now().minus(1, ChronoUnit.DAYS))

      val STRING = VariableValue(STRING_VAR, "value")
      val DATE = VariableValue(DATE_VAR, now)
      val SHORT = VariableValue(SHORT_VAR, 11.toShort())
      val INT = VariableValue(INT_VAR, 123)
      val LONG = VariableValue(LONG_VAR, 812L)
      val DOUBLE = VariableValue(DOUBLE_VAR, 12.0)
      val BOOLEAN = VariableValue(BOOLEAN_VAR, true)
      val COMPLEX = VariableValue(COMPLEX_VAR, ComplexDataStructure("string", 17, now))
      val LIST_STRING = VariableValue(LIST_STRING_VAR, listOf("Hello", "World"))
      val SET_STRING = VariableValue(SET_STRING_VAR, setOf("Kermit", "Piggy"))
      val MAP_STRING_LONG = VariableValue(MAP_STRING_LONG_VAR, mapOf("Twelve" to now.toString(), "Eleven" to now.toString()))
      val LIST_MAP_STRING_OBJECT = VariableValue(
        LIST_MAP_STRING_OBJECT_VAR, listOf(
          mapOf("1" to "one"),
          mapOf("2" to "two")
        )
      )
      val COMPLEX_SET = VariableValue(
        COMPLEX_SET_VAR, setOf(
          ComplexDataStructure("one", 1, now),
          ComplexDataStructure("two", 2, yesterday)
        )
      )
      val COMPLEX_LIST = VariableValue(
        COMPLEX_LIST_VAR, listOf(
          ComplexDataStructure("one", 1, now),
          ComplexDataStructure("two", 2, yesterday)
        )
      )
      val COMPLEX_MAP = VariableValue(
        COMPLEX_MAP_VAR, mapOf(
          "1" to ComplexDataStructure("one", 1, now),
          "2" to ComplexDataStructure("two", 2, yesterday)
        )
      )

      val STRING_LOCAL = VariableValue(STRING_VAR, "localValue")
      val DATE_LOCAL = VariableValue(DATE_VAR, yesterday)
      val SHORT_LOCAL = VariableValue(SHORT_VAR, 12.toShort())
      val INT_LOCAL = VariableValue(INT_VAR, 124)
      val LONG_LOCAL = VariableValue(LONG_VAR, 815L)
      val DOUBLE_LOCAL = VariableValue(DOUBLE_VAR, 14.0)
      val BOOLEAN_LOCAL = VariableValue(BOOLEAN_VAR, false)
      val COMPLEX_LOCAL = VariableValue(COMPLEX_VAR, ComplexDataStructure("foobar", 12, yesterday))
      val LIST_STRING_LOCAL = VariableValue(LIST_STRING_VAR, listOf("Foo", "Bar"))
      val SET_STRING_LOCAL = VariableValue(SET_STRING_VAR, setOf("Homer", "Marge"))
      val MAP_STRING_DATE_LOCAL = VariableValue(MAP_STRING_LONG_VAR, mapOf("Ten" to yesterday.toString(), "Nine" to yesterday.toString()))
      val LIST_MAP_STRING_OBJECT_LOCAL = VariableValue(
        LIST_MAP_STRING_OBJECT_VAR, listOf(
          mapOf("1" to "one local"),
          mapOf("2" to "two local")
        )
      )
      val COMPLEX_SET_LOCAL = VariableValue(
        COMPLEX_SET_VAR, setOf(
          ComplexDataStructure("one local", 1, now),
          ComplexDataStructure("two local", 2, yesterday)
        )
      )
      val COMPLEX_LIST_LOCAL = VariableValue(
        COMPLEX_LIST_VAR, listOf(
          ComplexDataStructure("one local", 1, now),
          ComplexDataStructure("two local", 2, yesterday)
        )
      )
      val COMPLEX_MAP_LOCAL = VariableValue(
        COMPLEX_MAP_VAR, mapOf(
          "1" to ComplexDataStructure("one local", 1, now),
          "2" to ComplexDataStructure("two local", 2, yesterday)
        )
      )
    }

    private val allValues = mapOf(
      COMPLEX_SET_VAR to COMPLEX_SET,
      COMPLEX_LIST_VAR to COMPLEX_LIST,
      COMPLEX_MAP_VAR to COMPLEX_MAP,
      STRING_VAR to STRING,
      DATE_VAR to DATE,
      SHORT_VAR to SHORT,
      INT_VAR to INT,
      LONG_VAR to LONG,
      DOUBLE_VAR to DOUBLE,
      BOOLEAN_VAR to BOOLEAN,
      COMPLEX_VAR to COMPLEX,
      LIST_STRING_VAR to LIST_STRING,
      SET_STRING_VAR to SET_STRING,
      MAP_STRING_LONG_VAR to MAP_STRING_LONG,
      LIST_MAP_STRING_OBJECT_VAR to LIST_MAP_STRING_OBJECT
    )

    private val allLocalValues = mapOf(
      COMPLEX_SET_VAR to COMPLEX_SET_LOCAL,
      COMPLEX_LIST_VAR to COMPLEX_LIST_LOCAL,
      COMPLEX_MAP_VAR to COMPLEX_MAP_LOCAL,
      STRING_VAR to STRING_LOCAL,
      DATE_VAR to DATE_LOCAL,
      SHORT_VAR to SHORT_LOCAL,
      INT_VAR to INT_LOCAL,
      LONG_VAR to LONG_LOCAL,
      DOUBLE_VAR to DOUBLE_LOCAL,
      BOOLEAN_VAR to BOOLEAN_LOCAL,
      COMPLEX_VAR to COMPLEX_LOCAL,
      LIST_STRING_VAR to LIST_STRING_LOCAL,
      SET_STRING_VAR to SET_STRING_LOCAL,
      MAP_STRING_LONG_VAR to MAP_STRING_DATE_LOCAL,
      LIST_MAP_STRING_OBJECT_VAR to LIST_MAP_STRING_OBJECT_LOCAL
    )

    fun createVariableMapUntyped(): VariableMap {
      val variables = createVariables()
      allValues.values.forEach {
        variables.putValue(it.variable.name, it.value)
      }
      return variables
    }

    fun createKeyValuePairs(): Set<Pair<VariableFactory<out Any>, Any>> {
      return allValues.entries.map { Pair(it.key, it.value.value) }.toSet()
    }

    fun createKeyLocalValuePairs(): Set<Pair<VariableFactory<out Any>, Any>> {
      return allLocalValues.entries.map { Pair(it.key, it.value.value) }.toSet()
    }
  }

  @Configuration
  class DelegateConfiguration {

    val vars = HashMap<String, Any>()
    val optionalVars = HashMap<String, Optional<*>>()
    var variableMap: VariableMap = createVariables()

    @Bean
    fun serviceWriteAdapter() = JavaDelegate { delegateExecution ->
      variableMap = delegateExecution.variablesTyped
    }

    @Bean
    fun readOptionalFromVariableScope() = JavaDelegate { delegateExecution ->
      optionalVars[STRING_VAR.name] = STRING_VAR.from(delegateExecution).getOptional()
      optionalVars[DATE_VAR.name] = DATE_VAR.from(delegateExecution).getOptional()
      optionalVars[SHORT_VAR.name] = SHORT_VAR.from(delegateExecution).getOptional()
      optionalVars[INT_VAR.name] = INT_VAR.from(delegateExecution).getOptional()
      optionalVars[LONG_VAR.name] = LONG_VAR.from(delegateExecution).getOptional()
      optionalVars[DOUBLE_VAR.name] = DOUBLE_VAR.from(delegateExecution).getOptional()
      optionalVars[BOOLEAN_VAR.name] = BOOLEAN_VAR.from(delegateExecution).getOptional()
      optionalVars[COMPLEX_VAR.name] = COMPLEX_VAR.from(delegateExecution).getOptional()
      optionalVars[LIST_STRING_VAR.name] = LIST_STRING_VAR.from(delegateExecution).getOptional()
      optionalVars[SET_STRING_VAR.name] = SET_STRING_VAR.from(delegateExecution).getOptional()
      optionalVars[MAP_STRING_LONG_VAR.name] = MAP_STRING_LONG_VAR.from(delegateExecution).getOptional()
      optionalVars[LIST_MAP_STRING_OBJECT_VAR.name] = LIST_MAP_STRING_OBJECT_VAR.from(delegateExecution).getOptional()
      optionalVars[COMPLEX_SET_VAR.name] = COMPLEX_SET_VAR.from(delegateExecution).getOptional()
      optionalVars[COMPLEX_LIST_VAR.name] = COMPLEX_LIST_VAR.from(delegateExecution).getOptional()
      optionalVars[COMPLEX_MAP_VAR.name] = COMPLEX_MAP_VAR.from(delegateExecution).getOptional()
    }

    @Bean
    fun readFromVariableScope() = JavaDelegate { delegateExecution ->
      vars[STRING_VAR.name] = STRING_VAR.from(delegateExecution).get()
      vars[DATE_VAR.name] = DATE_VAR.from(delegateExecution).get()
      vars[SHORT_VAR.name] = SHORT_VAR.from(delegateExecution).get()
      vars[INT_VAR.name] = INT_VAR.from(delegateExecution).get()
      vars[LONG_VAR.name] = LONG_VAR.from(delegateExecution).get()
      vars[DOUBLE_VAR.name] = DOUBLE_VAR.from(delegateExecution).get()
      vars[BOOLEAN_VAR.name] = BOOLEAN_VAR.from(delegateExecution).get()
      vars[COMPLEX_VAR.name] = COMPLEX_VAR.from(delegateExecution).get()
      vars[LIST_STRING_VAR.name] = LIST_STRING_VAR.from(delegateExecution).get()
      vars[SET_STRING_VAR.name] = SET_STRING_VAR.from(delegateExecution).get()
      vars[MAP_STRING_LONG_VAR.name] = MAP_STRING_LONG_VAR.from(delegateExecution).get()
      vars[LIST_MAP_STRING_OBJECT_VAR.name] = LIST_MAP_STRING_OBJECT_VAR.from(delegateExecution).get()
      vars[COMPLEX_SET_VAR.name] = COMPLEX_SET_VAR.from(delegateExecution).get()
      vars[COMPLEX_LIST_VAR.name] = COMPLEX_LIST_VAR.from(delegateExecution).get()
      vars[COMPLEX_MAP_VAR.name] = COMPLEX_MAP_VAR.from(delegateExecution).get()
    }

    @Bean
    fun readLocalFromVariableScope() = JavaDelegate { delegateExecution ->
      readLocalVarsFromVariableScope(variableScope = delegateExecution)
    }

    @Bean
    fun readLocalFromDelegateTask() = TaskListener { delegateTask ->
      readLocalVarsFromVariableScope(variableScope = delegateTask)
    }

    /**
     * Writes local properties from variable scope.
     */
    private fun readLocalVarsFromVariableScope(variableScope: VariableScope) {
      vars[STRING_VAR.name] = STRING_VAR.from(variableScope).getLocal()
      vars[DATE_VAR.name] = DATE_VAR.from(variableScope).getLocal()
      vars[SHORT_VAR.name] = SHORT_VAR.from(variableScope).getLocal()
      vars[INT_VAR.name] = INT_VAR.from(variableScope).getLocal()
      vars[LONG_VAR.name] = LONG_VAR.from(variableScope).getLocal()
      vars[DOUBLE_VAR.name] = DOUBLE_VAR.from(variableScope).getLocal()
      vars[BOOLEAN_VAR.name] = BOOLEAN_VAR.from(variableScope).getLocal()
      vars[COMPLEX_VAR.name] = COMPLEX_VAR.from(variableScope).getLocal()
      vars[LIST_STRING_VAR.name] = LIST_STRING_VAR.from(variableScope).getLocal()
      vars[SET_STRING_VAR.name] = SET_STRING_VAR.from(variableScope).getLocal()
      vars[MAP_STRING_LONG_VAR.name] = MAP_STRING_LONG_VAR.from(variableScope).getLocal()
      vars[LIST_MAP_STRING_OBJECT_VAR.name] = LIST_MAP_STRING_OBJECT_VAR.from(variableScope).getLocal()
      vars[COMPLEX_SET_VAR.name] = COMPLEX_SET_VAR.from(variableScope).getLocal()
      vars[COMPLEX_LIST_VAR.name] = COMPLEX_LIST_VAR.from(variableScope).getLocal()
      vars[COMPLEX_MAP_VAR.name] = COMPLEX_MAP_VAR.from(variableScope).getLocal()

    }

    @Bean
    fun readNonExisting() = JavaDelegate { delegateExecution ->
      val nonExisting = stringVariable("non-existing")
      nonExisting.from(delegateExecution).get()
    }


    @Bean
    fun readFromVariableMap() = JavaDelegate { delegateExecution ->
      val variableMap = delegateExecution.variablesTyped

      vars[STRING_VAR.name] = STRING_VAR.from(variableMap).get()
      vars[DATE_VAR.name] = DATE_VAR.from(variableMap).get()
      vars[SHORT_VAR.name] = SHORT_VAR.from(variableMap).get()
      vars[INT_VAR.name] = INT_VAR.from(variableMap).get()
      vars[LONG_VAR.name] = LONG_VAR.from(variableMap).get()
      vars[DOUBLE_VAR.name] = DOUBLE_VAR.from(variableMap).get()
      vars[BOOLEAN_VAR.name] = BOOLEAN_VAR.from(variableMap).get()
      vars[COMPLEX_VAR.name] = COMPLEX_VAR.from(variableMap).get()
      vars[LIST_STRING_VAR.name] = LIST_STRING_VAR.from(variableMap).get()
      vars[SET_STRING_VAR.name] = SET_STRING_VAR.from(variableMap).get()
      vars[MAP_STRING_LONG_VAR.name] = MAP_STRING_LONG_VAR.from(variableMap).get()
      vars[LIST_MAP_STRING_OBJECT_VAR.name] = LIST_MAP_STRING_OBJECT_VAR.from(variableMap).get()
      vars[COMPLEX_SET_VAR.name] = COMPLEX_SET_VAR.from(variableMap).get()
      vars[COMPLEX_LIST_VAR.name] = COMPLEX_LIST_VAR.from(variableMap).get()
      vars[COMPLEX_MAP_VAR.name] = COMPLEX_MAP_VAR.from(variableMap).get()
    }

    @Bean
    fun writeVariablesToScope() = JavaDelegate { delegateExecution ->
      STRING_VAR.on(delegateExecution).set(STRING.value)
      DATE_VAR.on(delegateExecution).set(DATE.value)
      SHORT_VAR.on(delegateExecution).set(SHORT.value)
      INT_VAR.on(delegateExecution).set(INT.value)
      LONG_VAR.on(delegateExecution).set(LONG.value)
      DOUBLE_VAR.on(delegateExecution).set(DOUBLE.value)
      BOOLEAN_VAR.on(delegateExecution).set(BOOLEAN.value)
      COMPLEX_VAR.on(delegateExecution).set(COMPLEX.value)
      LIST_STRING_VAR.on(delegateExecution).set(LIST_STRING.value)
      SET_STRING_VAR.on(delegateExecution).set(SET_STRING.value)
      MAP_STRING_LONG_VAR.on(delegateExecution).set(MAP_STRING_LONG.value)
      LIST_MAP_STRING_OBJECT_VAR.on(delegateExecution).set(LIST_MAP_STRING_OBJECT.value)
      COMPLEX_SET_VAR.on(delegateExecution).set(COMPLEX_SET.value)
      COMPLEX_LIST_VAR.on(delegateExecution).set(COMPLEX_LIST.value)
      COMPLEX_MAP_VAR.on(delegateExecution).set(COMPLEX_MAP.value)

    }

    @Bean
    fun writeVariablesToScopeAndLocal() = JavaDelegate { delegateExecution ->
      STRING_VAR.on(delegateExecution).set(STRING.value)
      DATE_VAR.on(delegateExecution).set(DATE.value)
      SHORT_VAR.on(delegateExecution).set(SHORT.value)
      INT_VAR.on(delegateExecution).set(INT.value)
      LONG_VAR.on(delegateExecution).set(LONG.value)
      DOUBLE_VAR.on(delegateExecution).set(DOUBLE.value)
      BOOLEAN_VAR.on(delegateExecution).set(BOOLEAN.value)
      COMPLEX_VAR.on(delegateExecution).set(COMPLEX.value)
      LIST_STRING_VAR.on(delegateExecution).set(LIST_STRING.value)
      SET_STRING_VAR.on(delegateExecution).set(SET_STRING.value)
      MAP_STRING_LONG_VAR.on(delegateExecution).set(MAP_STRING_LONG.value)
      LIST_MAP_STRING_OBJECT_VAR.on(delegateExecution).set(LIST_MAP_STRING_OBJECT.value)
      COMPLEX_SET_VAR.on(delegateExecution).set(COMPLEX_SET.value)
      COMPLEX_LIST_VAR.on(delegateExecution).set(COMPLEX_LIST.value)
      COMPLEX_MAP_VAR.on(delegateExecution).set(COMPLEX_MAP.value)

      STRING_VAR.on(delegateExecution).setLocal(STRING_LOCAL.value)
      DATE_VAR.on(delegateExecution).setLocal(DATE_LOCAL.value)
      SHORT_VAR.on(delegateExecution).setLocal(SHORT_LOCAL.value)
      INT_VAR.on(delegateExecution).setLocal(INT_LOCAL.value)
      LONG_VAR.on(delegateExecution).setLocal(LONG_LOCAL.value)
      DOUBLE_VAR.on(delegateExecution).setLocal(DOUBLE_LOCAL.value)
      BOOLEAN_VAR.on(delegateExecution).setLocal(BOOLEAN_LOCAL.value)
      COMPLEX_VAR.on(delegateExecution).setLocal(COMPLEX_LOCAL.value)
      LIST_STRING_VAR.on(delegateExecution).setLocal(LIST_STRING_LOCAL.value)
      SET_STRING_VAR.on(delegateExecution).setLocal(SET_STRING_LOCAL.value)
      MAP_STRING_LONG_VAR.on(delegateExecution).setLocal(MAP_STRING_DATE_LOCAL.value)
      LIST_MAP_STRING_OBJECT_VAR.on(delegateExecution).setLocal(LIST_MAP_STRING_OBJECT_LOCAL.value)
      COMPLEX_SET_VAR.on(delegateExecution).setLocal(COMPLEX_SET_LOCAL.value)
      COMPLEX_LIST_VAR.on(delegateExecution).setLocal(COMPLEX_LIST_LOCAL.value)
      COMPLEX_MAP_VAR.on(delegateExecution).setLocal(COMPLEX_MAP_LOCAL.value)
    }

    @Bean
    fun deleteVariablesFromScope() = JavaDelegate { delegateExecution ->
      STRING_VAR.on(delegateExecution).remove()
      LIST_STRING_VAR.on(delegateExecution).remove()
      SET_STRING_VAR.on(delegateExecution).remove()
      MAP_STRING_LONG_VAR.on(delegateExecution).remove()
      LIST_MAP_STRING_OBJECT_VAR.on(delegateExecution).remove()
    }
  }


  /**
   * Value holder.
   */
  data class VariableValue<T : Any?>(val variable: VariableFactory<T>, val value: T)

  /**
   * Complex data structure.
   */
  data class ComplexDataStructure(
    val someStringValue: String,
    val someIntValue: Int,
    val someDateValue: Date
  ) {
    @JsonIgnore
    val valueToIgnore: String = "some hidden value"
  }

  /**
   * Application to start
   */
  @EnableJGiven
  @ComponentScan
  @SpringBootConfiguration
  @EnableAutoConfiguration
  class TestApplication
}


