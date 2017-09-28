package codes.seanhenry.mockgenerator.usecases

import codes.seanhenry.mockgenerator.entities.TuplePropertyDeclaration
import codes.seanhenry.mockgenerator.util.StringDecorator

abstract class CreateParameterTuple {

  abstract fun getStringDecorator(): StringDecorator

  fun transform(name: String, parameterList: List<String>): TuplePropertyDeclaration? {
    val tupleParameters = parameterList
        .mapNotNull { transformParameter(it) }
    if (validateParameters(parameterList, tupleParameters)) {
      return createProperty(transformName(name), tupleParameters.filter { !isClosure(it) })
    }
    return null
  }

  private fun transformName(name: String): String {
    return getStringDecorator().process(name)
  }

  private fun validateParameters(parameterList: List<Any>, tupleParameters: List<Any>) =
      parameterList.size == tupleParameters.size

  private fun createProperty(name: String, tupleParameters: List<TuplePropertyDeclaration.TupleParameter>): TuplePropertyDeclaration? {
    if (tupleParameters.isEmpty()) {
      return null
    } else if (tupleParameters.size == 1) {
      val mutable = tupleParameters.toMutableList()
      mutable.add(TuplePropertyDeclaration.TupleParameter("", "Void"))
      return TuplePropertyDeclaration(name, mutable.toList())
    }
    return TuplePropertyDeclaration(name, tupleParameters)
  }

  private fun isClosure(parameter: TuplePropertyDeclaration.TupleParameter): Boolean {
    return parameter.type.contains("->")
  }

  private fun transformParameter(parameter: String): TuplePropertyDeclaration.TupleParameter? {
    val split = parameter.split(Regex(":"), 2)
    if (split.size < 2) {
      return null
    }
    val name = getTupleParameterName(split) ?: return null
    val type = split[1].trim()
    return TuplePropertyDeclaration.TupleParameter(name.trim(), replaceIUO(type))
  }

  private fun replaceIUO(type: String): String {
    if (type.endsWith("!")) {
      return type.removeSuffix("!") + "?"
    }
    return type
  }

  private fun getTupleParameterName(parameterLabels: List<String>): String? {
    return parameterLabels[0]
        .replace(Regex("\\s"), " ")
        .split(" ")
        .filter { it.isNotBlank() }
        .lastOrNull()
  }
}
